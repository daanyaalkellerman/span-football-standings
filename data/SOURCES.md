# Historical input dataset provenance

Acquired and validated on 13 October 2026 for the English Football League First Division, 1974/75. The user approved cumulative standings as of **28 September 1974 inclusive** during Stage 6. That approval supersedes the pending-cutoff wording in the earlier research/checklist documents; it does not approve their unresolved exact-tie or zero-denominator proposals.

## Dataset and inclusion rule

[results.csv](results.csv) contains **107 completed league matches**, involving **22 clubs**, actually played from **1974-08-17 through 1974-09-28 inclusive**, using UK local match dates. The provisional 107 count is now reconciled to two fixture lists and two published retrospective tables. No rows were added to meet a target count.

The exact input header is `date,home_team,away_team,home_goals,away_goals`. The file uses UTF-8 without a BOM, LF line endings, ISO dates, integer final scores, and one match per data row. Rows are sorted by date ascending, then canonical home name and away name in ordinal alphabetical order. Same-day ordering does not assert kickoff times.

Include actual completed First Division results only. Exclude all cup, European, Charity Shield and friendly matches, other competitions/seasons, unplayed games and matches played after the cutoff. Scheduled round numbers are supporting metadata, not the inclusion filter. An unplayed match is never represented as 0-0.

## Sources and coverage

| ID | Exact source URL | Evidence used in this stage |
|---|---|---|
| D1 | https://www.bdfutbol.com/en/t/teng1974-75.html | Season-specific First Division page. Downloaded HTML contains 462 dated league results across the whole season; filtering all of them by actual date produced 107 eligible records. Used home/away names, final scores, dates and round labels. |
| D2 | https://www.historical-lineups.com/wp-content/uploads/2024/04/1974-75-Week-1-42.pdf | Downloaded 22-page compilation, reached through its English First Division season index. Pages 1-5 contain weeks 1-10 and 107 completed fixtures. All five match fields agree with D1 after explicit team-name normalization. Page 5 contains the week-10 table used for aggregate reconciliation. |
| D2 index | https://www.historical-lineups.com/english-first-division/seasons-1970-to-1979/season-1974-75.html | Establishes competition/season context and links the weeks 1-42 PDF. |
| D3 | https://www.lfchistory.net/games/818 | Directly read the Sheffield United-Liverpool match page and its dated league table. Independently checks all clubs' played counts and goals for/against. Its displayed Arsenal/Tottenham ordering is disputed below. |
| D4 | https://www.leeds-fans.org.uk/leeds/history/60.html | Additional check of all nine included Leeds league fixtures: dates, opponents, venues and final scores agree. Explicitly distinguishes interspersed cup/European games. Also confirms the excluded December Leeds-Tottenham fixture. |

**Coverage:** all 107 included matches were compared field by field between D1 and D2: date, home team, away team, home goals and away goals. There were zero unmatched rows in either direction and no disagreements in these fields. These are different publishers; their underlying data lineage has not been established, so agreement is not proof of independently collected contemporary evidence. D4 gives a third-publisher check for nine matches; D3 additionally checks Sheffield United-Liverpool on 28 September.

[provenance.csv](provenance.csv) has one row per input match, joined by `date,home_team,away_team`. It records competition, season, D1 URL/round, D2 URL/one-based PDF page, fields compared, additional source where checked, and access date. The season URL locates D1 evidence; individual match detail pages were not separately audited. Competition and season were established from the source collection contexts, not guessed from team names alone.

## Acquisition and verification method

1. Read the assessment PDF, AGENTS.md, requirements checklist, historical research and Maven configuration. The application remains the existing scaffold.
2. Download D1 HTML and D2 PDF to temporary storage. Extract D1's team dictionary and structured fixture records as data; do not execute its website scripts. Read dates as day/month/year and explicitly map names to the roster below.
3. Extract D2's text, pairing each fixture heading's full-time score with the next dated match line on pages 1-5. Half-time scores, lineups and attendances are not input fields. Remove the suffix ` FC` only for the known clubs that use it in D2.
4. Compare complete normalized `(date, home, away, home goals, away goals)` sets, including duplicate checks in both sources. All 107 agree. An initial attempt to parse the entire PDF stopped on a later-season date-format mismatch; the successful comparison deliberately covers pages 1-5, which contain the approved period. No claim is made to have audited all later-season PDF fixtures.
5. Validate the selected rows and independently reconcile each team's appearances and goals for/against against D2's week-10 table and D3's dated table. The D3 values were transcribed from the retrieved table for this comparison. All 22 clubs agree on all three fields. No rankings or final standings CSV were generated.
6. Write the requested CSV and provenance, then read the files back for structural and source checks. Research commands and their actual output are preserved in the genuine Codex session. Temporary extraction/audit commands are not application code or a committed test suite.

## Canonical roster and per-team appearances

Every team's dataset appearance count equals both D2's and D3's published played count. Names not listed as aliases below are unchanged from D1. D2 uses ` FC` for Arsenal, Burnley, Chelsea, Everton, Liverpool and Middlesbrough; D3 uses `Wolves` for Wolverhampton Wanderers.

| Canonical name | D1 name | Dataset games | D2/D3 games |
|---|---|---:|---:|
| Arsenal | Arsenal | 9 | 9 |
| Birmingham City | Birmingham | 10 | 10 |
| Burnley | Burnley | 10 | 10 |
| Carlisle United | Carlisle | 10 | 10 |
| Chelsea | Chelsea | 10 | 10 |
| Coventry City | Coventry | 10 | 10 |
| Derby County | Derby County | 10 | 10 |
| Everton | Everton | 10 | 10 |
| Ipswich Town | Ipswich Town | 10 | 10 |
| Leeds United | Leeds | 9 | 9 |
| Leicester City | Leicester | 9 | 9 |
| Liverpool | Liverpool | 10 | 10 |
| Luton Town | Luton | 10 | 10 |
| Manchester City | Manchester City | 10 | 10 |
| Middlesbrough | Middlesbrough | 9 | 9 |
| Newcastle United | Newcastle | 9 | 9 |
| Queens Park Rangers | Queens Park Rangers | 10 | 10 |
| Sheffield United | Sheffield United | 10 | 10 |
| Stoke City | Stoke City | 10 | 10 |
| Tottenham Hotspur | Tottenham | 9 | 9 |
| West Ham United | West Ham | 10 | 10 |
| Wolverhampton Wanderers | Wolverhampton | 10 | 10 |

These are explicit aliases, not fuzzy matching rules for the future CLI. Six clubs have nine games and sixteen have ten: `(6 * 9 + 16 * 10) / 2 = 107` matches, or **214 team appearances**. Do not add fixtures to make every club reach ten.

## Date counts

| Actual date | Matches |
|---|---:|
| 1974-08-17 | 11 |
| 1974-08-19 | 1 |
| 1974-08-20 | 5 |
| 1974-08-21 | 5 |
| 1974-08-24 | 11 |
| 1974-08-27 | 7 |
| 1974-08-28 | 4 |
| 1974-08-31 | 11 |
| 1974-09-07 | 11 |
| 1974-09-14 | 11 |
| 1974-09-21 | 11 |
| 1974-09-24 | 6 |
| 1974-09-25 | 2 |
| 1974-09-28 | 11 |
| **Total** | **107** |

The observed round structure contains 88 games in rounds 1-8, eight September games in round 9 and eleven in round 10. This is a reconciliation observation after actual-date filtering, not the selection method.

## Disputes, exclusions and corrections

No included fixture required a date or score correction between D1 and D2. Only team names and date formatting were normalized.

The following D1 round-9 results are excluded because their actual dates are outside the approved interval:

| Actual date | Home | Score | Away |
|---|---|---|---|
| 1974-12-04 | Leeds United | 2-1 | Tottenham Hotspur |
| 1974-12-10 | Middlesbrough | 3-0 | Leicester City |
| 1975-04-23 | Newcastle United | 3-1 | Arsenal |

The earlier research records an apparent FBref indexed date of 12 October for Middlesbrough-Leicester; D1 instead gives 10 December, as corroborated during research by the older Historical Lineups PDF at https://www.historical-lineups.com/wp-content/uploads/2012/07/1974-75-Week-11-206.pdf. Both dates fall after this cutoff. FBref was not imported or used to fill gaps in this dataset. The prior direct-access failure and indexed discrepancy are documented in [HISTORICAL_RESEARCH.md](../HISTORICAL_RESEARCH.md); no successful full FBref acquisition is claimed here.

D3 displays Tottenham ahead of Arsenal on six points despite goal averages `11/15` and `9/12`. Arsenal's ratio is greater (`9 * 15 = 135`, versus `11 * 12 = 132`), and D2 orders Arsenal above Tottenham. This is a table-ordering discrepancy, not a disputed match result. Do not copy D3's ordering into future expected output. Dataset appearances and goal totals nevertheless agree with both tables for all clubs.

## Validation results and limits

- **Passed:** exact requested header, five fields per result, valid ISO dates in the inclusive interval, canonical 22-club roster, different home/away teams, and nonnegative integer goals.
- **Passed:** 107 unique fixtures, no repeated home/away pairing in the same direction, no club appearing twice on one date, deterministic chronological/name ordering, and matching provenance for each row.
- **Passed:** 107/107 five-field matches between D1 and D2, sourced from their 1974/75 First Division collections; no non-league records selected.
- **Passed:** all 22 appearance counts against D2 and D3; 214 total appearances equals twice 107 matches.
- **Passed:** per-club goals for/against against both tables; 288 total goals (170 home, 118 away). Sum of club goals for and sum of goals against each equal 288. These are data-audit totals, not final standings output.
- **Not claimed:** a contemporary 1974 rulebook/newspaper audit, independently established source lineage, complete season verification, exhaustive disciplinary/awarded-result history, or an implemented/tested standings calculator. The previous no-net-deduction finding remains subject to eventual points reconciliation by the application.
- Access limitations: direct web-tool PDF opens and screenshot retrieval failed intermittently. Following the publisher link and downloading the PDF succeeded; extracted text was used, without a successful visual screenshot audit. The bare LFChistory domain failed to fetch, while the `www` URL above succeeded. Initial restricted shell downloads failed; approved retries succeeded. These failures did not require missing fixtures to be inferred.
- Maven/JUnit were not run because no application or build files changed. Dataset audit checks are distinct from application tests.

SHA-256 of the exact UTF-8/LF `results.csv` bytes at creation:

`4a679af2afb8e148d1810a1f12f3ab17d3e296e742d2e47f26cc2dc7a3f0e567`

Final verification on 13 October 2026 re-read the actual CSV and re-extracted both previously downloaded source archives. All 107 five-field records still match both sources, and all 22 appearance counts match the PDF's week-10 table. No missing fields, invalid records, duplicate fixtures, self-play or noncanonical names were found. Provenance keys match every input row. This comparison used the existing downloaded archives, not new full downloads.

The current CSV has no terminating newline. Its SHA-256 is now `40ade5133f32db26f04acafd34492e7f2ebb847558a14d0be7697ac570eaba65`. Adding only a final LF in memory reproduces the creation checksum above, confirming this is solely a trailing-newline difference. The CSV remains valid UTF-8 without a BOM, with LF separators; no fixture correction or CSV rewrite was needed. The creation checksum is retained as historical evidence, not presented as the current file checksum.

SHA-256 of the downloaded D2 PDF used for comparison:

`02109e87e60fc2b2a6b465da2254433c371fbe560b9cc5d6ff12ab02ea3cbda8`

The full downloaded archives remain temporary research material, not submission files. The repository deliverables for this stage are `results.csv`, `provenance.csv` and this source/validation record. Review is pending; no staging, commits, pushes or publication were performed.
