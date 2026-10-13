package com.spandigital.standings;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class Csv {
    static final String INPUT_HEADER = "date,home_team,away_team,home_goals,away_goals";
    static final String OUTPUT_HEADER = "position,team,played,won,drawn,lost,goals_for,goals_against,goal_average,points";
    private Csv() { }

    private record Fixture(LocalDate date, String home, String away) { }

    static List<Match> read(Path input) throws IOException {
        List<Match> matches = new ArrayList<>();
        Set<Fixture> seen = new HashSet<>();
        try (var reader = Files.newBufferedReader(input, StandardCharsets.UTF_8)) {
            String header = reader.readLine();
            if (header != null && header.startsWith("\uFEFF")) header = header.substring(1);
            if (header == null || !parseLine(header, 1).equals(List.of(INPUT_HEADER.split(",")))) {
                throw invalid(1, "Expected header: " + INPUT_HEADER);
            }
            String line;
            int row = 1;
            while ((line = reader.readLine()) != null) {
                row++;
                List<String> fields = parseLine(line, row);
                if (fields.size() != 5) throw invalid(row, "Expected 5 columns; found " + fields.size());
                LocalDate date;
                try {
                    if (!fields.get(0).matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
                        throw invalid(row, "Date must use YYYY-MM-DD");
                    }
                    date = LocalDate.parse(fields.get(0));
                } catch (DateTimeParseException e) {
                    throw invalid(row, "Invalid calendar date: " + fields.get(0));
                }
                String home = team(fields.get(1), row);
                String away = team(fields.get(2), row);
                if (home.equals(away)) throw invalid(row, "Home and away teams must differ");
                Match match = new Match(date, home, away, score(fields.get(3), row), score(fields.get(4), row));
                if (!seen.add(new Fixture(date, home, away))) {
                    throw invalid(row, "Duplicate fixture for " + home + " vs " + away + " on " + date);
                }
                matches.add(match);
            }
            if (matches.isEmpty()) throw invalid(2, "At least one match is required");
        }
        return List.copyOf(matches);
    }

    private static String team(String value, int row) {
        if (value.isBlank() || !value.equals(value.strip()) || value.chars().anyMatch(Character::isISOControl)) {
            throw invalid(row, "Team names must be nonempty with no surrounding whitespace or control characters");
        }
        return value;
    }

    private static int score(String value, int row) {
        try {
            if (!value.matches("[0-9]+")) throw new NumberFormatException();
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw invalid(row, "Score must be an integer from 0 to 2147483647: " + value);
        }
    }

    // One physical line per record; quoted commas and doubled quotes are supported.
    static List<String> parseLine(String line, int row) {
        List<String> fields = new ArrayList<>();
        int index = 0;
        while (true) {
            StringBuilder field = new StringBuilder();
            if (index < line.length() && line.charAt(index) == '"') {
                index++;
                boolean closed = false;
                while (index < line.length()) {
                    char c = line.charAt(index++);
                    if (c == '"') {
                        if (index < line.length() && line.charAt(index) == '"') {
                            field.append('"');
                            index++;
                        } else {
                            closed = true;
                            break;
                        }
                    } else field.append(c);
                }
                if (!closed) throw invalid(row, "Unclosed quoted field; multiline fields are unsupported");
                if (index < line.length() && line.charAt(index) != ',') {
                    throw invalid(row, "Unexpected character after closing quote");
                }
            } else {
                while (index < line.length() && line.charAt(index) != ',') {
                    char c = line.charAt(index++);
                    if (c == '"') throw invalid(row, "Quote inside unquoted field");
                    field.append(c);
                }
            }
            fields.add(field.toString());
            if (index == line.length()) return fields;
            index++; // A trailing comma creates an empty final field.
        }
    }

    static void write(Path output, List<Standings.Row> rows) throws IOException {
        try (var writer = Files.newBufferedWriter(output, StandardCharsets.UTF_8)) {
            writer.write(OUTPUT_HEADER + "\n");
            for (var row : rows) {
                var t = row.team();
                writer.write(row.position() + "," + quote(t.name()) + "," + t.played() + "," + t.won()
                        + "," + t.drawn() + "," + t.lost() + "," + t.goalsFor() + "," + t.goalsAgainst()
                        + "," + t.goalAverage() + "," + t.points() + "\n");
            }
        }
    }

    private static String quote(String value) {
        return value.contains(",") || value.contains("\"")
                ? "\"" + value.replace("\"", "\"\"") + "\"" : value;
    }

    private static IllegalArgumentException invalid(int row, String reason) {
        return new IllegalArgumentException("Row " + row + ": " + reason);
    }
}
