package com.spandigital.standings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @TempDir Path directory;
    private final ByteArrayOutputStream errors = new ByteArrayOutputStream();
    private int run(String... args) {
        return Main.run(args, new PrintStream(errors, true, StandardCharsets.UTF_8));
    }
    private Path input(String content) throws Exception {
        return Files.writeString(directory.resolve("input.csv"), content);
    }
    @Test void incorrectArguments() {
        assertEquals(2, run());
        assertEquals(2, run("one"));
        assertEquals(2, run("one", "two", "three"));
        assertTrue(errors.toString(StandardCharsets.UTF_8).contains("Usage:"));
    }
    @Test void successfulQuotedUtf8InputAndOutput() throws Exception {
        Path in = input("\uFEFF" + Csv.INPUT_HEADER + "\r\n2026-01-01,\"Caf\u00e9, United\",\"The \"\"Blues\"\"\",2,0");
        Path out = directory.resolve("output.csv");
        Files.writeString(out, "old output");
        assertEquals(0, run(in.toString(), out.toString()));
        assertEquals(Csv.OUTPUT_HEADER + "\n1,\"Caf\u00e9, United\",1,1,0,0,2,0,Infinity,2\n"
                + "2,\"The \"\"Blues\"\"\",1,0,0,1,0,2,0.00,0\n", Files.readString(out));
        assertEquals("", errors.toString(StandardCharsets.UTF_8));
    }
    @Test void invalidInputPreservesExistingOutputAndCreatesNoNewOutput() throws Exception {
        Path in = input(Csv.INPUT_HEADER + "\n2026-01-01,A,B,2,0\n2026-01-02,B,C,-1,0");
        Path out = directory.resolve("output.csv");
        Files.writeString(out, "preserve me");
        assertEquals(1, run(in.toString(), out.toString()));
        assertEquals("preserve me", Files.readString(out));
        assertEquals(1, run(in.toString(), directory.resolve("new.csv").toString()));
        assertFalse(Files.exists(directory.resolve("new.csv")));
        assertTrue(errors.toString(StandardCharsets.UTF_8).contains("Row 3"));
        try (var files = Files.list(directory)) { assertEquals(2, files.count()); }
    }
    @Test void sameInputAndOutputRejected() throws Exception {
        Path in = input(Csv.INPUT_HEADER + "\n2026-01-01,A,B,1,0");
        String original = Files.readString(in);
        assertEquals(1, run(in.toString(), in.toString()));
        assertEquals(original, Files.readString(in));
    }
    @Test void ioFailuresReturnNonzero() throws Exception {
        assertEquals(1, run(directory.resolve("missing.csv").toString(), directory.resolve("out.csv").toString()));
        Path in = input(Csv.INPUT_HEADER + "\n2026-01-01,A,B,1,0");
        assertEquals(1, run(in.toString(), directory.resolve("missing/out.csv").toString()));
        assertEquals(1, run(in.toString(), directory.toString()));
        try (var files = Files.list(directory)) { assertEquals(1, files.count()); }
    }
    @ParameterizedTest
    @ValueSource(strings = {
        "", "2026-01-01,A,B,1", "2026-01-01,A,B,1,0,extra",
        "2026-01-01,,B,1,0", "2026-01-01,A, ,1,0", ",A,B,1,0",
        "2026-02-30,A,B,1,0", "2026-1-01,A,B,1,0", "2026-01-01,A,A,1,0",
        "2026-01-01,A,B,-1,0", "2026-01-01,A,B,1.5,0", "2026-01-01,A,B,one,0",
        "2026-01-01,A,B,2147483648,0", "2026-01-01,A,B,1,", "2026-01-01,A,B,+1,0",
        "2026-01-01, A,B,1,0", "2026-01-01,A\"bad,B,1,0",
        "2026-01-01,\"A\"bad,B,1,0", "2026-01-01,\"A,B,1,0",
        "2026-01-01,\"A\nB\",C,1,0"
    })
    void invalidRecordsIdentifyRow(String record) throws Exception {
        Path in = input(Csv.INPUT_HEADER + "\n" + record);
        var e = assertThrows(IllegalArgumentException.class, () -> Csv.read(in));
        assertTrue(e.getMessage().contains("Row 2"), e.getMessage());
    }
    @Test void emptyFileAndWrongHeaderRejected() throws Exception {
        for (String contents : new String[]{"", "home_team,away_team,home_goals,away_goals\nA,B,1,0", "date,away_team,home_team,home_goals,away_goals"}) {
            Path in = input(contents);
            assertTrue(assertThrows(IllegalArgumentException.class, () -> Csv.read(in)).getMessage().contains("Row 1"));
        }
    }
    @Test void duplicateFixtureIncludingConflictingScoreRejected() throws Exception {
        Path in = input(Csv.INPUT_HEADER + "\n2026-01-01,A,B,1,0\n2026-01-01,A,B,2,0");
        assertTrue(assertThrows(IllegalArgumentException.class, () -> Csv.read(in)).getMessage().contains("Row 3: Duplicate"));
    }
    @Test void malformedUtf8Rejected() throws Exception {
        Path in = directory.resolve("invalid.csv");
        Files.write(in, new byte[]{(byte) 0xC3, 0x28});
        assertEquals(1, run(in.toString(), directory.resolve("out.csv").toString()));
        assertFalse(Files.exists(directory.resolve("out.csv")));
    }
    @Test void realEntryPointReturnsUsageExitCode() throws Exception {
        String java = Path.of(System.getProperty("java.home"), "bin", "java").toString();
        Process process = new ProcessBuilder(java, "-cp", Path.of("target/classes").toAbsolutePath().toString(), Main.class.getName())
                .redirectErrorStream(true).start();
        String message = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        assertEquals(2, process.waitFor());
        assertTrue(message.contains("Usage:"));
    }
    @Test void rootAndEmptyDirectoryOutputsAreRejectedClearly() throws Exception {
        Path in = input(Csv.INPUT_HEADER + "\n2026-01-01,A,B,1,0");
        Path emptyDirectory = Files.createDirectory(directory.resolve("empty"));
        assertEquals(1, run(in.toString(), in.toAbsolutePath().getRoot().toString()));
        assertEquals(1, run(in.toString(), emptyDirectory.toString()));
        assertTrue(Files.isDirectory(emptyDirectory));
        assertTrue(errors.toString(StandardCharsets.UTF_8).contains("Output must be a regular file path"));
    }
    @Test void quotedHeaderLeapDateAndDistinctReturnFixturesAccepted() throws Exception {
        Path in = input("\"date\",\"home_team\",\"away_team\",\"home_goals\",\"away_goals\"\n"
                + "2024-02-29,A,B,1,0\n2024-03-01,A,B,0,1\n2024-03-02,B,A,0,0\n");
        var matches = Csv.read(in);
        assertEquals(3, matches.size());
        assertEquals(java.time.LocalDate.of(2024, 2, 29), matches.getFirst().date());
        assertEquals("B", matches.getLast().home());
    }
}
