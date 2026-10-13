package com.spandigital.standings;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class Standings {
    private Standings() { }

    record Team(String name, long played, long won, long drawn, long lost,
                long goalsFor, long goalsAgainst, long points) {
        Team add(int scored, int conceded) {
            return new Team(name, Math.addExact(played, 1), won + (scored > conceded ? 1 : 0),
                    drawn + (scored == conceded ? 1 : 0), lost + (scored < conceded ? 1 : 0),
                    Math.addExact(goalsFor, scored), Math.addExact(goalsAgainst, conceded),
                    Math.addExact(points, scored > conceded ? 2 : scored == conceded ? 1 : 0));
        }

        String goalAverage() {
            if (goalsAgainst == 0) return goalsFor == 0 ? "N/A" : "Infinity";
            return BigDecimal.valueOf(goalsFor).divide(BigDecimal.valueOf(goalsAgainst),
                    2, RoundingMode.HALF_UP).toPlainString();
        }
    }

    record Row(int position, Team team) { }

    // Sporting comparison deliberately excludes the alphabetical display fallback.
    static int compareSporting(Team a, Team b) {
        int points = Long.compare(b.points, a.points);
        if (points != 0) return points;
        boolean aInfinite = a.goalsAgainst == 0 && a.goalsFor > 0;
        boolean bInfinite = b.goalsAgainst == 0 && b.goalsFor > 0;
        if (aInfinite || bInfinite) return Boolean.compare(bInfinite, aInfinite);
        // Approved convention: 0/0 ranks as 0/1 but displays N/A.
        BigInteger left = BigInteger.valueOf(a.goalsFor)
                .multiply(BigInteger.valueOf(b.goalsAgainst == 0 ? 1 : b.goalsAgainst));
        BigInteger right = BigInteger.valueOf(b.goalsFor)
                .multiply(BigInteger.valueOf(a.goalsAgainst == 0 ? 1 : a.goalsAgainst));
        return right.compareTo(left);
    }

    static List<Row> calculate(List<Match> matches) {
        Map<String, Team> teams = new HashMap<>();
        for (Match match : matches) {
            add(teams, match.home(), match.homeGoals(), match.awayGoals());
            add(teams, match.away(), match.awayGoals(), match.homeGoals());
        }
        List<Team> ordered = new ArrayList<>(teams.values());
        ordered.sort(((Comparator<Team>) Standings::compareSporting).thenComparing(Team::name));
        List<Row> result = new ArrayList<>();
        int rank = 0;
        for (int i = 0; i < ordered.size(); i++) {
            if (i == 0 || compareSporting(ordered.get(i - 1), ordered.get(i)) != 0) rank = i + 1;
            result.add(new Row(rank, ordered.get(i)));
        }
        return List.copyOf(result);
    }

    private static void add(Map<String, Team> teams, String name, int scored, int conceded) {
        Team before = teams.getOrDefault(name, new Team(name, 0, 0, 0, 0, 0, 0, 0));
        teams.put(name, before.add(scored, conceded));
    }
}
