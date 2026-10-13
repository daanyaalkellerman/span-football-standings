package com.spandigital.standings;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        System.exit(run(args, System.err));
    }

    static int run(String[] args, PrintStream errors) {
        if (args.length != 2) {
            errors.println("Usage: java -cp target/classes com.spandigital.standings.Main <input.csv> <output.csv>");
            return 2;
        }
        try {
            Path input = Path.of(args[0]).toAbsolutePath().normalize();
            Path output = Path.of(args[1]).toAbsolutePath().normalize();
            if (input.equals(output) || (Files.exists(output) && Files.isSameFile(input, output))) {
                throw new IllegalArgumentException("Input and output must be different files");
            }
            if (Files.isSymbolicLink(output)) throw new IllegalArgumentException("Output must not be a symbolic link");
            if (output.getParent() == null || (Files.exists(output) && !Files.isRegularFile(output))) {
                throw new IllegalArgumentException("Output must be a regular file path, not a directory or special file");
            }
            var rows = Standings.calculate(Csv.read(input));
            // Validate before touching the destination; use a sibling for atomic replacement.
            Path temporary = Files.createTempFile(output.getParent(), ".standings-", ".tmp");
            try {
                Csv.write(temporary, rows);
                try {
                    Files.move(temporary, output, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
                } catch (AtomicMoveNotSupportedException e) {
                    Files.move(temporary, output, StandardCopyOption.REPLACE_EXISTING);
                }
            } finally {
                Files.deleteIfExists(temporary);
            }
            return 0;
        } catch (IOException | IllegalArgumentException | ArithmeticException e) {
            errors.println("Error: " + e.getMessage());
            return 1;
        }
    }
}
