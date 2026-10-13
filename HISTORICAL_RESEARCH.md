# Historical research: English First Division 1974/75

Research date: 2026-10-13, updated during the approved historical-research stage. Read AGENTS.md, REQUIREMENTS_CHECKLIST.md and BE Coding Test 2026.pdf. This is a research report for approval, not a completed historical input dataset or an implemented standings calculation. Sources were consulted through the web tool; access limitations are recorded below. No application code, tests, historical CSV files, Maven changes, Git staging, commits, pushes or publication are part of this stage.

Project timing confirmed by the user: assessment started 13 October 2026 at 14:00 SAST; deadline 14 October at 14:00 SAST; internal submission target 14 October at 13:00 SAST. All are UTC+02:00. Git is initialized on main without commits. Initial AI-record verification/backup/review steps are recorded in the checklist; final evidence clearance and recruiter acceptance of the Codex mapping remain pending.

## Findings and recommendation

Use **2 points for a win, 1 for a draw, 0 for a loss**, then **goal average (goals scored / goals conceded)** to separate equal points. These are well supported by the season tables and results discussed below. Goal difference and three-point wins would be historically wrong for this task.

Recommend interpreting week 10 as the **cumulative standings after the tenth scheduled round, through 28 September 1974 inclusive**, subject to user/recruiter approval. Select games by verified actual playing date, not just a provider's round number. This is a reasoned interpretation of an ambiguous assessment phrase, not a finding about SPAN's intent.

FBref is useful as a cross-reference, but it is not a safe sole source. Its indexed schedule contains late-played fixtures tagged with early round numbers and an apparent date error. Complete historical tie-break rules beyond equal points and goal average remain unresolved. These limitations must remain visible in the README and design decisions.

## Sources and what was actually inspected

All access dates below are 2026-10-13. Different publishers do not guarantee independently collected underlying data; no shared-data lineage was established.

| ID | Source URL | Use and limitations |
|---|---|---|
| S1 | [GoonerGrove: final table](https://goonergrove.co.uk/table/1970_1979/table_1974_75.php) | Direct page read. Explicit two-point wins and goal-average definition. Retrospective fan archive, not a contemporary rulebook or week-10 table. |
| S2 | [FBref: season schedule](https://fbref.com/en/comps/9/1974-1975/schedule/1974-1975-Premier-League-Scores-and-Fixtures) | Direct request returned HTTP 403. Search-indexed rows were readable; findings about this page refer to that indexed representation, not a verified fresh full export. |
| S3 | [BDFutbol: First Division 1974/75](https://www.bdfutbol.com/en/t/teng1974-75.html) | Read dated results and round groupings, particularly rounds 9 and 10. Useful independent publisher for fixture comparison; not an official rulebook. Full season was not exhaustively reconciled. |
| S4 | [RSSSF: 1974/75 tables](https://www.rsssf.org/engpaul/FLA/1974-75.html) | Direct page read. Season-end team list, home/away totals and points; credits Stuart Jackson. Validates roster and scoring arithmetic, not September standings. |
| S5 | [Soccerbase: 28 September results](https://www.soccerbase.com/matches/results.sd?date=1974-09-28) | Direct page read. Ten top-flight results visible in retrieved text; Leicester–Coventry absent from that rendering. Do not assume a date page is complete. |
| S6 | [Leeds supporters' season archive](https://www.leeds-fans.org.uk/leeds/history/60.html) | Direct page read. Dated league and cup results and cumulative points. Distinguishes a club's games played from scheduled rounds. |
| S7 | [EFL history: innovation](https://www.efl.com/about-the-efl/history/innovation/) | Official league site's indexed history dates introduction of three-point wins to 1981. Supports rejecting modern scoring; not a complete 1974 rule source. |
| S8 | [Footballsite: final table](https://www.footballsite.co.uk/Statistics/FullTables/1974-75Div1Full.htm) | Search-indexed table and goal-average note read; direct open failed. Corroborates S1 but includes presentation defects; do not import displayed rounded ratios blindly. |
| S9 | [11v11: table-generator explanation](https://www.11v11.com/league-tables/league-division-four/01-January-1974/) | Direct page read. Generic methodology, including historic Division One; not the requested season's regulations. Contains assumptions and internal errors discussed below. |
| S10 | [History of Arsenal: exact goal-average ties](https://blog.woolwicharsenal.co.uk/archives/17748) | Direct article read. Secondary discussion of earlier seasons and a printed reference; useful counter-evidence, not proof of 1974/75 rules. |
| S11 | [Historical Lineups: current full-season PDF](https://www.historical-lineups.com/wp-content/uploads/2024/04/1974-75-Week-1-42.pdf) | Text inspected for opening fixtures and week-10 results/table on PDF page 5. Modern compilation. Screenshot retrieval failed with cache miss, so visual table layout was not verified. |
| S12 | [Historical Lineups: older partial-season PDF](https://www.historical-lineups.com/wp-content/uploads/2012/07/1974-75-Week-11-206.pdf) | Text on PDF page 5 confirms the December Middlesbrough–Leicester fixture. Screenshot failed. Same publisher as S11, so not a separate independent source. |
| S13 | [Derby County: history of the Football League](https://www.dcfc.co.uk/page/history-of-the-football-league) | Official club history read directly. Describes two-point wins/one-point draws and the 1976 change from goal average to difference. Historical account, not modern rules used retroactively. |
| S14 | [The City Ground: points deductions](https://www.thecityground.com/points_deducted.php?order_by=games) | Direct archive read. Its 1974/75 entry concerns Aldershot in Division Three, not this First Division. Not an exhaustive official disciplinary register. |
| S15 | [LFChistory: Sheffield United–Liverpool, 28 September 1974](https://lfchistory.net/games/818) | Direct fetch failed, but search-indexed full dated table was retrieved. Independent publisher for aggregate checks; Arsenal/Tottenham order is inconsistent with the ratios in its own rows. |
| S16 | [Beyond The Last Man: goal-average history](https://beyondthelastman.com/2023/05/24/by-the-laws-of-averages-eight-decades-of-the-goal-average-rule/) | Direct retrospective article read. Discusses Liverpool/Ipswich in this season; used only for that corroboration, not as authority for all historical rule details. |

S11 was reached from the publisher's [1974/75 season index](https://www.historical-lineups.com/english-first-division/seasons-1970-to-1979/season-1974-75.html). The archive is a promising source for a subsequent complete fixture ledger. A historical Statto cutoff-table request and a dated 11v11 table request failed; neither is claimed as verified evidence.

On the follow-up pass, direct FBref access still failed, and a fresh open of S11 failed too. Earlier retrieved PDF text remains evidence from this session, but no successful fresh download or visual inspection is claimed. Search-indexed material may lag the live site and should be captured/rechecked during acquisition. Do not substitute modern Premier League matches merely because a historical page uses that competition name in its URL.

## Scoring and ranking

### Verified core rules

S1 explicitly states two points per win and goal average for teams level on points. S8 agrees on the ratio definition. S4's Derby totals give 21 wins, 11 draws and 53 points: `2 × 21 + 11 = 53`. Everton's 16 wins and 18 draws give 50. S6's early Leeds sequence shows losses leaving points unchanged, a win adding two, and a draw adding one. Together these support the 2/1/0 system with high confidence. S7 places the three-point change later, in 1981.

Order by total points descending, then by goal average descending. This is **GF / goals conceded**, not GF / games played and not GF minus goals conceded. Use unambiguous column names: some archives use GA for goal average while others use GA for goals against.

S1's Coventry/Newcastle example illustrates precision: both have 39 points and display 0.82, but Coventry's 51/62 exceeds Newcastle's 59/72. Their distinct exact ratios explain their order without an extra tie-break. S1 also misspells Coventry as “Covenrty”; normalize that name with S4 rather than copying the typo.

**Engineering recommendation, not a historical rule:** compare ratios exactly when denominators are positive and round only for display. This prevents precision-induced false ties. A display precision must still be chosen and documented. No algorithm has been written here.

For two teams A and B with positive goals-conceded totals, compare `GF_A × conceded_B` with `GF_B × conceded_A`. This is ordinary fraction comparison: no floating-point division or rounded decimal is necessary. Equal cross-products mean an exact ratio tie. Check arithmetic overflow in the eventual implementation; either prove numeric bounds or use exact integer arithmetic. For the Coventry/Newcastle example, the cross-products are 3672 and 3658, verified by a research-only arithmetic command. This is a proposed comparison method, not Java implementation.

Goal difference can produce the wrong order even when points agree. S16 highlights Liverpool finishing above Ipswich in 1974/75 despite Ipswich having the better difference. S13 independently places the switch to goal difference in 1976, after this season. Thus a GD display column, if retained, must not control ranking.

### What remains unverified

| Situation | Evidence assessment | Recommended next decision |
|---|---|---|
| Equal points and exactly equal positive-denominator ratios | No 1974/75 official rulebook located. S9's generator uses further criteria, while S10 describes earlier equal-ratio teams sharing positions. | Do not assert goals scored, goal difference, wins, head-to-head, alphabetical order or a playoff as a verified subsequent rule. Seek period regulations; otherwise approve an explicit fallback assumption. |
| Goals scored as another tie-break | S9 describes it, but provides no season-specific rule citation. S10 discusses Birmingham and Chelsea sharing 12th in 1956/57 despite different goals totals. | This is conflicting secondary evidence, not sufficient verification for 1974/75. |
| Head-to-head / mini-league | Present in S9's generic generator methodology. | Do not import it as an established historical rule. |
| GF > 0, conceded = 0 | Finite arithmetic division is undefined; an extended-ratio convention can rank it as infinity. Historic treatment not established. | Approve and document a deliberate policy for generic/early-season input; do not silently substitute an arbitrary numeric value. |
| GF = 0, conceded > 0 | The mathematical ratio is exactly zero. | Preserve that mathematical meaning unless period evidence establishes a different ordering convention. |
| GF = 0, conceded = 0 | Ratio is indeterminate. | Decide how no-games/all-scoreless records are displayed and ranked; historical authority remains missing. |
| Deductions or awarded/abandoned results | No season-specific adjustment was established in this research. | Reconcile a complete cutoff ledger with an independent table before concluding ordinary match points explain every club. |

S9 explicitly admits uncertainty about historical zero handling. Its text also incorrectly calls zero divided by a positive number undefined and gives a goal-average date range conflicting with its own 1976/77 transition statement. Treat its fallback rules as software choices, not historical authority. S10 is about earlier seasons, so it cannot settle the later rule by itself.

If a fully tied row must be displayed before stronger evidence is found, the proposed fallback is a shared sporting position with alphabetical ordering only for stable display. This needs approval and must be labelled an assumption, not a discovered league rule.

### Proposed zero-conceded policy, for approval only

No source reviewed establishes a definitive 1974/75 policy for every zero case. A small, explicit application convention would be:

- Positive GF with zero conceded: represent an infinite ratio; compare above finite ratios on equal points. Two such records remain equal on this criterion; do not substitute 100 or rank by goals scored without an approved further rule.
- Zero GF with positive conceded: ratio is zero.
- Zero GF and zero conceded: display `N/A` and, if a total ordering is required, provisionally compare as zero, then use the approved exact-tie display convention. This last choice is an application assumption, not a mathematical identity or historical finding. An alternative is to leave these positions unresolved; the user must approve the policy before implementation.

The retrieved September table has positive conceded totals for every team, so these zero cases do not affect the proposed historical snapshot. They still matter for a general calculator and its later synthetic edge-case tests. No tests for them exist yet.

### Deductions and adjustments: evidence checked

A research-only arithmetic check of all 22 published final-table rows in S1 found zero differences between points and `2W + D`. The same check on all 22 indexed cutoff rows in S15 found zero differences and a total of 214 points. This supports **no net points deductions in those published snapshots**, rather than proving that no disciplinary or result adjustment ever occurred.

S14 lists a one-point deduction for Aldershot in Division Three in 1974/75. That is outside this competition and must not be imported. No First Division deduction was identified in the searched evidence. Recommended working assumption: no points adjustments for the September snapshot, pending the full result/table reconciliation. Awarded results, expunged games, or subsequently reversed sanctions could escape a points-only check; none has been verified here.

## What could “week 10” mean?

Opening-day evidence is 17 August 1974 (S6 and S11). The following calendar dates are arithmetic interpretations, not competing historical fixture claims. Date arithmetic was checked with Node.js; no standings were calculated.

| Interpretation | Endpoint / selection | Assessment |
|---|---|---|
| Tenth scheduled matchweek, as-of table | All completed league games from season start through 28 September 1974 inclusive. S2/S3 label that day's fixtures round/week 10. | Recommended interpretation. Midweek rounds explain why this is only six elapsed weeks after opening day. |
| All fixtures assigned rounds 1–10, whenever eventually played | Includes later dates, even into April 1975. | A retrospective round-completion dataset, not the standings that existed in September. Reject unless explicitly requested. |
| Ten opening-date-anchored seven-day blocks | Week 1 = 17–23 August; week 10 = 19–25 October. End-of-week cutoff = 25 October inclusive. | Defensible literal calendar interpretation, materially different from matchweek 10. |
| Ten Monday–Sunday calendar weeks, counting the opening week | Week 1 = 12–18 August; week 10 = 14–20 October. Cutoff = 20 October inclusive. | Also defensible, but requires that convention to be explicitly selected. |
| Tenth Saturday of the season | 19 October, counting 17 August as Saturday one. | Not automatically the end of either calendar-week definition. |
| Exactly ten elapsed weeks after opening | 26 October is 70 days after 17 August. | Including that day's matches differs from stopping before the anniversary; this boundary must not be implicit. |
| First ten actual games of every club | Club-specific endpoints. | Not a coherent as-of league table and can produce inconsistent opponent inclusion. Do not use without explicit instruction. |
| D: a table at an identifiable historical date | S11's week-10 table follows the 28 September results; S15 attaches a table to that day's Liverpool fixture. | Both are retrospective reconstructions, not verified newspaper tables published in 1974. They provide a dated cross-check, not proof of the original publication date. |

Mapping to the requested alternatives: **A** has two distinct meanings, a literal dataset-round filter and the actual-date snapshot after the round. **B** covers the calendar conventions above. **C**, approximately ten games played, fits the nine/ten-game September snapshot, but does not uniquely determine a date; it is supporting evidence, not a selection rule. **D** is strongest if a period publication with an explicit as-of date can be located. That primary document remains outstanding. The recommended choice combines A's identifiable scheduled round with D's reconstructed as-of date and C's observed played counts; it does not claim the evidence removes the assessment's ambiguity.

S6 supplies a concrete distinction: Leeds had played nine league games through 28 September; its tenth was the 2–0 win over Arsenal on 5 October. Cup/European fixtures interspersed in the archive must not be counted as league games.

Neither the external databases nor these calculations determine SPAN's intended meaning. The chosen endpoint and inclusion rule need approval. Use UK local match dates; do not reinterpret dates through the developer machine's South African timezone.

## FBref reliability and date discrepancies

S2 supports identifying the tenth scheduled round, but direct access was blocked and full completeness was not established. Its indexed record puts Newcastle–Arsenal, 3–1, on 23 April 1975 under week 9. It also places Middlesbrough–Leicester, 3–0, on 12 October 1974, despite showing both clubs in other fixtures that day. S3 and S12 instead date that result to 10 December. A day/month transposition is plausible, but the cause was not verified.

Known round-9 results to exclude from a September cutoff, as listed by S3:

| Actual date | Home | Score | Away |
|---|---|---|---|
| 1974-12-04 | Leeds United | 2–1 | Tottenham Hotspur |
| 1974-12-10 | Middlesbrough | 3–0 | Leicester City |
| 1975-04-23 | Newcastle United | 3–1 | Arsenal |

The Leeds December date/result also agrees with S6. The Middlesbrough date agrees with S12. A round-only filter would include future knowledge; a date filter using an incorrect date could contaminate an October dataset too. Any correction must retain the original source value, replacement evidence and rationale. Do not quietly edit an imported row or assume this is the only discrepancy.

The observed Wk column is therefore a scheduled-round grouping, not elapsed calendar weeks or each team's chronological game count. Reasons for individual rearrangements were not established: a later playing date alone does not prove weather, cup congestion or any particular postponement cause. Midweek rounds are ordinary included league results, not automatically outliers to exclude.

### Exact proposed inclusion rule

If the September recommendation is approved, include a match if and only if it is a completed **English Football League First Division, 1974/75** result whose independently verified actual UK match date is **1974-08-17 through 1974-09-28 inclusive**. Include all such games once, for both home and away participants; the Wk label is retained as metadata, not the deciding filter. Exclude cups, European games, the Charity Shield, friendlies, other divisions/seasons, unplayed fixtures and results played after the cutoff. Do not insert an unplayed game as 0–0.

In the observed round structure, this means the earlier rounds plus the eight September results in round 9 and all eleven round-10 games listed below. The eight round-9 inclusions are:

| Date | Included fixture (home first) |
|---|---|
| 24 September | Carlisle United–Manchester City; Wolverhampton Wanderers–Sheffield United; Queens Park Rangers–Everton; Liverpool–Burnley; Ipswich Town–Stoke City; Coventry City–Luton Town |
| 25 September | Derby County–Chelsea; West Ham United–Birmingham City |

These dates/pairings are present in the indexed S2 schedule and S3. The three later-played round-9 matches above are excluded regardless of their Wk label. A literal A selection would add those three and yield a different, retrospective round set. The provisional September count is 107, but no complete 107-row fixture audit is claimed: exact eligibility is defined here, while full row-by-row enumeration and verification remain the acquisition task. Do not assert the raw dataset has no other anomalies before that audit.

## The 22 participating teams

Roster confirmed against S4 and S1. Proposed canonical names, alphabetically ordered, are:

| | Team | | Team |
|---|---|---|---|
| 1 | Arsenal | 12 | Liverpool |
| 2 | Birmingham City | 13 | Luton Town |
| 3 | Burnley | 14 | Manchester City |
| 4 | Carlisle United | 15 | Middlesbrough |
| 5 | Chelsea | 16 | Newcastle United |
| 6 | Coventry City | 17 | Queens Park Rangers |
| 7 | Derby County | 18 | Sheffield United |
| 8 | Everton | 19 | Stoke City |
| 9 | Ipswich Town | 20 | Tottenham Hotspur |
| 10 | Leeds United | 21 | West Ham United |
| 11 | Leicester City | 22 | Wolverhampton Wanderers |

Record explicit aliases such as QPR, Wolves, Spurs, shortened city names, and RSSSF's “Queen's Park Rangers”. This is data normalization, not a reason to merge arbitrary near-matching input strings. S4 shows Manchester United in Division Two; it is not part of this roster.

## Relevant match results checked

The following is the **complete round-10 day**, not the complete cumulative input. All dates are 28 September 1974 and scores are home–away. S5 confirms the ten rows marked A. The final row is confirmed by S3 and S11. S3 also agrees with the other ten rows.

| Home | Score | Away | Verification |
|---|---|---|---|
| Birmingham City | 3–1 | Arsenal | A |
| Burnley | 3–5 | West Ham United | A |
| Chelsea | 0–1 | Wolverhampton Wanderers | A |
| Everton | 3–2 | Leeds United | A; also S6 |
| Luton Town | 3–1 | Carlisle United | A |
| Manchester City | 1–0 | Queens Park Rangers | A |
| Newcastle United | 1–0 | Ipswich Town | A |
| Sheffield United | 1–0 | Liverpool | A |
| Stoke City | 1–1 | Derby County | A |
| Tottenham Hotspur | 1–2 | Middlesbrough | A |
| Leicester City | 0–1 | Coventry City | S3 + S11 |

S11's extracted week-10 table shows six teams with nine games: Arsenal, Leeds, Leicester, Middlesbrough, Newcastle and Tottenham; the others have ten. S15's indexed table independently shows the same played-count distribution. That implies `(6 × 9 + 16 × 10) / 2 = 107` played fixtures, a **provisional reconciliation target**, not an audited fixture-row count. S6 corroborates Leeds' nine games. The complete cumulative ledger has not been assembled.

S15 places Tottenham above Arsenal despite equal six-point totals and ratios 11/15 and 9/12 respectively. Arithmetic gives `9 × 15 = 135 > 11 × 12 = 132`, so Arsenal should precede Tottenham under the verified goal-average rule, consistent with S11. This is a source-ordering discrepancy, not evidence for a further tie-break: the ratios are unequal. The cause of the publisher's ordering is unknown. Do not copy its full ordering into expected output unchecked.

Do not manufacture 110 results to make every team reach ten games. Earlier rounds are required for cumulative standings; the eleven rows above alone are insufficient. S3 and S11 provide the sources for assembling that ledger in a subsequent approved task. Their season-wide completeness and every early fixture have not been certified here.

## Documentation and validation approach

After approval:

1. State the competition, season, interpretation, inclusive cutoff and actual-date selection rule in README and beside the input data. Preserve dates even if the calculator ultimately consumes only teams and scores.
2. Maintain a fixture provenance ledger: original/canonical names, actual date, home/away score, provider round label, primary URL, cross-check URL, access date, and any discrepancy decision. Retain uncertainty rather than fill gaps from inference.
3. Cross-check every selected fixture against another publisher or period record. Treat mirrored copies and versions from the same publisher as one source. Investigate duplicate club/date entries, missing results, and rearranged games.
4. Reconcile the 22 clubs and provisional 107-fixture count for the September proposal. Check per-team appearances, total appearances = twice matches, total goals for = goals against, and total wins = losses. These are proposed checks, not tests run in this stage.
5. Validate standings against an independent dated table, not only totals computed from the same imported results. Compare S11 with S15 field by field, retaining the known ordering discrepancy; seek a contemporary newspaper/yearbook with its as-of date, issue date and page recorded. Season-end S1/S4 cannot directly validate a September table. Provider-generated historic tables are not contemporary publications.
6. Record the two verified ranking stages separately from approved fallback behaviour. Document exact ties, zero denominators, display rounding and ranking labels explicitly. Do not use a rounded published ratio as an expected exact value.
7. Identify source errors and corrections in the research record. Prefer links and a compact factual ledger over copying whole archives. Preserve genuine Codex collaboration and review evidence for sensitive information before any authorized public submission.

## Approval decisions and remaining uncertainty

- Approve the recommended 28 September inclusive as-of interpretation, or select a calendar-week convention / obtain recruiter clarification.
- Approve a further fixture-audit stage to assemble the cumulative dataset from S3/S11 and cross-check it; this report does not establish a fully verified input CSV.
- Seek period rules for exact goal-average ties. If unavailable within the assessment window, explicitly approve a disclosed fallback rather than claim complete historical certainty.
- Approve or revise the proposed zero-denominator convention before generic calculator implementation. It is separate from the ordinary positive-denominator historical case.
- Verify the no-adjustment working assumption through the final fixture/table reconciliation. Published points checks found no net deduction, but this is not an exhaustive disciplinary-history audit.
- Work to the confirmed deadline above. Recruiter approval of the Codex artefact mapping remains pending; historical research does not resolve it.

Stop for user approval. No standings algorithm or new automated tests were implemented, and no files were committed, pushed or published.
