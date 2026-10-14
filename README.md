# Football standings CLI

Java 21 command-line calculator for the SPAN Digital Backend Coding Test 2026, using English First Division 1974/75 scoring: two points for a win, one for a draw and zero for a loss. The supplied dataset produces cumulative standings as of 28 September 1974 inclusive. No third-party runtime dependencies or network access are needed by the application.

## Build and run

Install a JDK 21 and Apache Maven, with `java` and `mvn` on PATH. Ensure Maven uses JDK 21 (check `java -version` and `mvn -version`; set JAVA_HOME to your JDK if necessary). No IDE is required. From the project root, these commands use the same syntax on macOS, Linux and Windows:

```sh
mvn clean verify
java -cp target/classes com.spandigital.standings.Main data/results.csv data/standings.csv
```

`mvn clean verify` compiles, runs the JUnit 5 tests and packages the JAR. To compile and run tests without packaging, use `mvn clean test`. Maven downloads build/test dependencies on the first build. Compilation produces `target/classes`; the CLI uses that directory directly, without a separate executable-JAR setup. Quote filenames containing spaces. The code uses Java paths, explicit UTF-8 and locale-independent numeric formatting. These commands are expected to work on macOS/Linux with the prerequisites installed, but execution has only been checked on Windows.

The Java command above **regenerates and replaces `data/standings.csv` from `data/results.csv`**. To preserve the supplied output while experimenting, choose another output filename, such as `target/standings.csv`.

Exactly two positional filenames are required. Exit status is 0 for success, 2 for incorrect argument count, and 1 for invalid data or file errors. Diagnostics go to stderr; successful execution is silent and writes the requested output file.

1. Input CSV filename: an existing readable file.
2. Output CSV filename: a different file in an existing writable directory.

## Project structure

```text
pom.xml                              Maven build and JUnit configuration
src/main/java/com/spandigital/standings/
  Main.java                          CLI arguments, errors and output replacement
  Csv.java                           CSV parsing, validation and formatting
  Match.java                         Match data record
  Standings.java                     Statistics and exact ranking
src/test/java/com/spandigital/standings/
  MainTest.java                      CSV and CLI tests
  StandingsTest.java                  Calculation and ranking tests
  HistoricalIntegrationTest.java     Fixed historical expectations and real CLI
data/results.csv                     Historical match input
data/standings.csv                   Generated historical output
data/SOURCES.md                      Acquisition, coverage and limitations
data/provenance.csv                  Per-match source references
AGENTS.md                            Codex project instructions
ai/codex-sessions/                   Genuine session record copies
ai/SESSION_EXPORT_NOTES.md       Codex export and privacy documentation
HISTORICAL_RESEARCH.md               Research and earlier decision alternatives
AI_REFLECTION.md                  Personal AI collaboration reflection
REQUIREMENTS_CHECKLIST.md            Requirements and acceptance status
CODE_REVIEW.md                       Technical review and verification limits
target/                             Generated build output (not submission source)
```

`AI_REFLECTION.md` contains my personal reflection on using Codex, my reasons for choosing Java, historical football research decisions, and the lessons learned from testing and reviewing the application.

## Input and output

Input header, with these columns in this order:

```csv
date,home_team,away_team,home_goals,away_goals
```

Each record represents one completed match. Dates must be valid `YYYY-MM-DD` calendar dates. They are metadata: every supplied match is included, regardless of date or input order. The calculator does not hardcode the historical cutoff or roster.

Input is UTF-8 (an optional leading BOM is accepted), with LF or CRLF lines and an optional final newline. Quoted fields support embedded commas and doubled double quotes. Each record must occupy one physical line; embedded newlines, malformed quotes, extra/missing fields and blank records are rejected. Headers are required, including for quoted CSV. Empty and header-only files are rejected.

Names are case-sensitive and preserved exactly. Empty names, surrounding whitespace and control characters are rejected; aliases are not automatically merged. Input providers must use consistent names. Scores must contain ASCII digits only and fit 0 through 2147483647. Totals use `long`; ratio cross-products use `BigInteger`. Self-play and repeated `(date, home_team, away_team)` fixtures, including conflicting scores for that identity, are rejected. A return fixture on another date or with reversed venues is a different identity.

Output header:

```csv
position,team,played,won,drawn,lost,goals_for,goals_against,goal_average,points
```

Output is UTF-8 without a BOM, with LF lines and a final newline. Names containing commas or quotes are escaped. The entire input is validated before writing. Output is written to a temporary sibling and replaces the destination after successful writing. Existing output is replaced on success and preserved on input-validation failure. Same-file input/output paths, including existing file aliases, are rejected; output symbolic links are rejected. The output parent directory must already exist. If atomic moves are unsupported, replacement uses the standard non-atomic move fallback; crash-atomic replacement is not guaranteed in that case.

Output roots, directories and existing non-regular files are rejected. Parsing errors identify the CSV row where useful (the header is row 1); filesystem and encoding failures return an error rather than producing a partial standings table.

### Worked example (synthetic, not historical data)

Input:

```csv
date,home_team,away_team,home_goals,away_goals
2026-01-01,Example A,Example B,3,1
```

Output:

```csv
position,team,played,won,drawn,lost,goals_for,goals_against,goal_average,points
1,Example A,1,1,0,0,3,1,3.00,2
2,Example B,1,0,0,1,1,3,0.33,0
```

## Ranking and application conventions

Teams sort by points descending, then **exact goals for / goals against** descending. Cross-multiplication with `BigInteger` avoids overflow and rounding-induced ties. Goal difference never determines rank. Display uses two decimal places with HALF_UP rounding and a decimal point; displayed values do not affect ordering.

The user approved these application conventions in Stage 7. They are not asserted to be verified historical regulations:

- Equal points and exact ratios share a sporting position. Positions use competition ranking, e.g. `1,1,3`. Case-sensitive alphabetical name order makes tied rows deterministic without breaking the sporting tie.
- Positive goals for and zero conceded ranks as positive infinity and displays `Infinity`. Two infinite ratios remain tied when points agree, regardless of goals scored.
- Zero goals for and zero conceded displays `N/A` and ranks as ratio zero.
- Zero goals for with positive conceded is zero and displays `0.00`.

Only teams appearing in the input are included. Points adjustments, abandoned/unplayed matches and deductions are not modeled.

## Historical evidence and tests

The approved input contains 107 matches among 22 clubs, actually played from 17 August through 28 September 1974 inclusive. Six clubs played nine games and sixteen played ten. Source coverage, aliases, excluded rearranged fixtures and limitations are recorded in [data/SOURCES.md](data/SOURCES.md), with per-match links in [data/provenance.csv](data/provenance.csv). Earlier research and checklist documents record earlier decision stages; the Stage 6 cutoff approval and Stage 7 conventions above supersede their pending-decision wording.

“Week 10” is interpreted as the cumulative snapshot after the tenth scheduled round, selected by actual UK match dates, not ten elapsed calendar weeks or each club's first ten games. Later-played fixtures labelled with early round numbers are excluded. This is the user's approved interpretation of the assessment ambiguity, not a claim of recruiter confirmation.

The acquisition record documents agreement on all five match fields for all 107 records between [BDFutbol's season page](https://www.bdfutbol.com/en/t/teng1974-75.html) and [Historical Lineups, pages 1–5](https://www.historical-lineups.com/wp-content/uploads/2024/04/1974-75-Week-1-42.pdf), after explicit name normalization. The [Leeds season archive](https://www.leeds-fans.org.uk/leeds/history/60.html) provides an additional check of nine fixtures. FBref was not imported: access problems and an indexed date discrepancy are recorded in [HISTORICAL_RESEARCH.md](HISTORICAL_RESEARCH.md). This README audit does not claim a fresh retrieval of these historical sources.

The historical JUnit test uses fixed expected values transcribed from [LFChistory's dated table](https://www.lfchistory.net/games/818) and the [Historical Lineups compilation, page 5](https://www.historical-lineups.com/wp-content/uploads/2024/04/1974-75-Week-1-42.pdf). Expected values are not calculated by production code. All 22 rows' played/won/drawn/lost/goals/points and ordering are compared with generated output. LFChistory places Tottenham above Arsenal despite unequal goal averages; the expected order follows exact ratios and Historical Lineups. The PDF's displayed Leicester `0.77` and Chelsea `0.55` are not adopted: the documented HALF_UP calculation gives `13/17 = 0.76` and `10/18 = 0.56`. These display differences do not alter order or aggregate statistics.

Other tests use synthetic data for outcomes, accumulated totals, precise ratios, ties, zero denominators, large cross-products, malformed CSV, quoting/UTF-8, CLI errors and output preservation. Internal arithmetic checks supplement source comparisons; they do not establish historical truth. The sources are retrospective and their underlying independence is unestablished. No contemporary newspaper or comprehensive disciplinary-history audit is claimed.

## Verification and known limitations

The Stage 9 documentation audit ran `mvn clean verify` and the historical CLI command above on Windows: BUILD SUCCESS, 43 tests, zero failures, errors or skips. The regenerated output matched the previous CSV byte for byte: 22 clubs, led by Ipswich Town (16 points), Manchester City (14) and Liverpool (13). Input records remained unchanged. The dataset contains 107 matches and 288 goals. See [CODE_REVIEW.md](CODE_REVIEW.md) for the preceding code review and test breakdown.

The calculator holds input in memory and targets small league datasets. It does not infer missing fixtures, enforce a competition or roster, filter dates, normalize aliases, or apply disciplinary adjustments. Multiline CSV fields are unsupported. Exact-tie and zero-denominator historical regulations remain unverified; the explicit application conventions above resolve those cases. Filesystem permission failures, disk-full conditions and the non-atomic replacement fallback have not been fault-injected. A clean checkout on macOS/Linux remains untested.

## AI collaboration and evidence provenance

OpenAI Codex was the only AI coding assistant used. The assessment PDF permits equivalent artefacts for other assistants. The following is our **proposed mapping; recruiter acceptance is still pending**. It is not a claim that SPAN has formally accepted the mapping.

| Requested category/example | Actual Codex equivalent and status |
|---|---|
| `CLAUDE.md`: project instructions | [AGENTS.md](AGENTS.md), the project's Codex instructions. |
| `.claude/`: session data | Two redacted Codex JSONL session copies are committed in `ai/codex-sessions/`. The user manually reviewed both copies for privacy before publication. Their substantive completeness has not been independently verified. |
| `ai/`: conversation history | The genuine JSONL copies under `ai/` also serve as conversation exports. No separate readable export is currently present. |
| `AI_REFLECTION.md`: reflection | [AI_REFLECTION.md](AI_REFLECTION.md), my completed personal reflection on using Codex, choosing Java, historical rules and testing. |
| `README.md`: setup and evidence explanation | This document describes commands, conventions, the mapping and evidence process. |

Two genuine Codex JSONL session files are currently included in `ai/codex-sessions/`. The user's PowerShell validation reported zero invalid JSONL records. The original session files were preserved in private backups, while personal Windows paths and username-related information were redacted from the reviewed submission copies.

The session preservation process and redaction details are documented in [ai/SESSION_EXPORT_NOTES.md](ai/SESSION_EXPORT_NOTES.md). My completed reflection is available in [AI_REFLECTION.md](AI_REFLECTION.md).

The repository is public, and the reviewed implementation commit is `99d94e3`. The user confirms that both redacted Codex session copies were manually reviewed for privacy before publication. This review does not establish substantive completeness of the conversation evidence, which has not been independently verified. SPAN has not confirmed acceptance of the proposed Codex-to-Claude artefact mapping.