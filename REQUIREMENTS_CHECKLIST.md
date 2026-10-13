# SPAN Digital Backend Coding Test 2026 — Requirements Checklist

## Evidence, scope, and status

Analysis date: 2026-10-13. This document records requirements and proposed verification; it does not certify a completed assessment.

Reviewed during requirements analysis: `BE Coding Test 2026.pdf`, `AGENTS.md`, `pom.xml`, `.gitignore`, `src/main/java/com/spandigital/standings/Main.java`, and `src/test/java/com/spandigital/standings/MainTest.java`. The PDF and project instructions were read again for historical research, now documented in [HISTORICAL_RESEARCH.md](HISTORICAL_RESEARCH.md). README now exists and was reviewed during Stage 8. The recruiter email requirements come from the user's supplied summary; the original email was not independently inspected.

Confirmed project status, updated 2026-10-13:

- Assessment start: **13 October 2026, 14:00 SAST (UTC+02:00)**.
- Strict deadline: **14 October 2026, 14:00 SAST**.
- Internal submission target: **14 October 2026, 13:00 SAST**, leaving one hour before the deadline.
- Git is initialized on `main`, with no commits. Read-only Git checks confirm this; no Git mutations were performed in this stage.
- The user confirms that the initial Codex session was verified against the project path and original prompt; an original private backup and reviewed JSONL copy were created, and a reviewed copy was placed inside `ai/`. These completed steps are recorded as user-confirmed facts, not claimed as a new audit by this assistant.
- Final verification of all AI evidence before public release remains outstanding. Recruiter acceptance of the proposed Codex artefact mapping is still pending.

Current implementation (Stage 8, 2026-10-13): Java release 21, Maven, JUnit Jupiter 5.13.4, Compiler Plugin 3.14.1 and Surefire 3.5.4. The implemented CLI was reviewed and one output-path defect corrected. Actual mvn clean verify passed 43 tests with zero failures/errors/skips. Regenerated historical output is byte-identical to data/standings.csv. See [CODE_REVIEW.md](CODE_REVIEW.md) for evidence and limitations. No macOS/Linux execution is claimed.

The user's later approvals supersede earlier setup/research-only restrictions. The current stage authorizes code review, necessary fixes/tests and this report, but no staging, commits, pushes or publication.

## Requirement levels

The PDF explicitly requires use of [RFC 2119](https://www.rfc-editor.org/rfc/rfc2119). MUST means mandatory; MUST NOT means prohibited. SHOULD and SHOULD NOT allow a justified exception after considering its consequences. MAY/OPTIONAL means discretionary.

The tables preserve uppercase keywords where present. Lowercase “should” and ordinary prose are identified separately rather than silently rewritten as uppercase MUST. Core deliverables and the explicitly mandatory AI artefact categories remain acceptance obligations even where their sentences lack an uppercase keyword. We plan to satisfy all PDF recommendations. User/email obligations are binding project constraints, not quotations of PDF RFC keywords.

Checkboxes indicate completed acceptance evidence, not merely that a requirement has been understood. All verification below is proposed unless explicitly identified as existing evidence.

## PDF requirements and traceability

| ID | Level and PDF section | Requirement | Proposed implementation/deliverable | Verification / status |
|---|---|---|---|---|
| P01 | Instruction — Problem Statement & Instructions | Read the problem, create a working solution, and write supporting tests. | CLI implementation plus tests. | [x] Working CLI inspected; 43 tests passed under mvn clean verify. |
| P02 | Lowercase should — Instructions | Use Java, Python, Go, C# (.NET Core on Linux), or Scala. | Java 21 Maven project, as selected by user. | [x] Existing Java source and pom.xml inspected; Java is an allowed language. |
| P03 | Instruction — Instructions | Be prepared to explain the solution during review. | Small design, documented decisions, user walkthrough. | [ ] User can explain data flow, rules, errors, tests, and AI-assisted decisions. |
| P04 | MUST — Instructions | Apply RFC 2119 definitions. | Preserve requirement levels and document any justified SHOULD exceptions. | [x] RFC reviewed and levels recorded here. |
| P05 | Core deliverable — The Problem | Provide a production-ready command-line football league standings calculator. | Minimal CLI, calculation logic, validation, clear errors and reproducible build; production-ready criteria are proposed below. | [x] CLI reviewed, root-output defect fixed, valid/invalid cases tested; documented limitations in CODE_REVIEW.md. |
| P06 | Core deliverable — The Problem | Use the program to calculate English First Division standings in the 10th week of 1974/75. | Verified season dataset, defined cutoff, generated standings file. | [x] Approved 28 September cumulative snapshot: all 22 rows match fixed source-based expectations; source limitations remain documented. |
| P07 | Lowercase should — The rules | Use standings rules from that league and season. | Use researched 2/1/0 scoring and points then goal average; resolve further ties/zero cases explicitly. | [x] 2/1/0 and exact goal average implemented/tested; approved tie/zero conventions are documented assumptions. |
| P08 | SHOULD — Input/output | Input and output are text CSV files. | CSV reader/writer with documented schemas. | [x] Five-column input and ten-column output exercised, including UTF-8 and quoting. |
| P09 | Permitted alternatives — Input/output | Use stdin/stdout or filenames on the command line. | Two positional filename arguments, approved and implemented. | [x] Two filename arguments exercised through the real Java main process. |
| P10 | MUST — Input/output | Include input and output files in the submission. | Actual verified historical input CSV and output CSV generated by this program. | [ ] Regenerate output from submitted input; compare with committed output. |
| P11 | SHOULD — Input/output | Input contains game results, one per line. | One game per CSV data row; header policy documented. | [x] One game per physical CSV record; parser and historical dataset exercised. |
| P12 | Lowercase should — Input/output | Output is a league standings table in conventional format. | Ordered rows with agreed conventional columns; schema approved and implemented. | [x] All 22 output rows, columns, statistics and exact ranking verified. |
| P13 | SHOULD NOT, conditional — Guidelines | Do not commit package-manager-installed libraries. | Maven dependency declarations; ignore target and keep downloaded dependencies outside source control. | [ ] Review eventual tracked-file list for downloaded JARs/classes/caches. |
| P14 | MUST — Guidelines | Include automated tests in the submission. | JUnit tests under src/test/java. | [ ] 43 automated tests pass; final repository inclusion still outstanding. |
| P15 | Conditional instruction — Guidelines | Document complicated setup steps if required. | README prerequisites and exact build/run/test commands; keep setup simple. | [ ] Follow instructions from a clean checkout and note any unverified platform. |
| P16 | Platform constraint / instruction — Platform | Solution will run on OS X; use platform-agnostic constructs where possible, including for compiled languages. | Java 21 bytecode, portable paths/encoding, no Windows-only execution steps. | [ ] Test on macOS if available; otherwise explicitly report absence of macOS verification. |
| P17 | Stated expectation — AI Collaboration | Use AI coding assistance; collaboration ability is evaluated. | Use Codex only and preserve actual interactions. | [ ] Evidence spans real requirements, implementation, review, and verification work. |
| P18 | Explicitly mandatory — AI Collaboration | Submit all AI artefact categories through the Git repository; other assistants must supply equivalents. Missing categories make the submission incomplete. | Codex mapping below, with README explaining provenance and equivalence. | [ ] Check every category and resolve pending recruiter confirmation. |
| P19 | Mandatory category — Project configuration / instructions | Root CLAUDE.md in the example, or equivalent project instructions. | Existing root AGENTS.md as proposed Codex equivalent. | [ ] File exists; equivalence confirmation and final relevance review remain. |
| P20 | Mandatory category — AI session data | Project .claude/ in the example, or equivalent genuine session data. | Genuine Codex JSONL records, currently under ai/codex-sessions/. | [ ] Initial session origin verified and private backup/reviewed copy created, as confirmed by user. Final full-evidence review and mapping acceptance remain; no fake Claude files. |
| P21 | Mandatory category — Conversation history | Copy assistant project conversation records into ./ai/ (Claude example names its local projects directory). | Genuine Codex conversation/session exports in ai/. | [ ] User confirms reviewed initial copy in ai/. Verify final coverage/provenance and acceptance of JSONL for both evidence categories. |
| P22 | Mandatory category — AI reflection | Root AI_REFLECTION.md: a few paragraphs about an AI mistake/disagreement and the user's response, or a meaningful user decision if nothing went wrong. | User-grounded reflection supported by actual collaboration. | [ ] User reviews factual accuracy and personal account; no invented incident or response. |
| P23 | Lowercase should — Submission tree | Include README.md with setup/run instructions alongside the solution and AI artefacts. | Root README with prerequisites, commands, schemas, assumptions, historical sources, and Codex mapping. | [ ] Documentation review and reproducibility check. |

The PDF does not require sports expertise. It notes research may be necessary; research is a means of resolving P06/P07, not a separate prescribed deliverable. No optional product feature is explicitly mandated. Libraries are permitted, not required. Choosing one allowed language and one allowed I/O mode is sufficient; the alternatives are not cumulative obligations.

## Email and user constraints

| ID | Source / obligation | Proposed action | Verification / status |
|---|---|---|---|
| E01 | Email summary: strict 24-hour window. | Work within 13 Oct 2026 14:00 to 14 Oct 2026 14:00 SAST; aim to submit by 14 Oct 13:00 SAST. | [ ] Timeline confirmed by user; actual on-time submission remains outstanding. |
| E02 | Email summary: finished implementation version-controlled in Git. | Track final deliverables when explicitly authorized. | [ ] Git initialized on main; no commits exist. Final committed implementation remains outstanding. |
| E03 | Email summary: public GitHub repository with final version committed. | After privacy review and explicit approval, commit and publish final version. | [ ] Verify public access and that remote final commit contains tested deliverables. |
| E04 | Email summary: recruiter receives repository URL. | Prepare correct URL for user delivery or explicitly authorized sending. | [ ] Confirm delivery; publication alone does not satisfy this. No message authorized now. |
| E05 | Email/user: AI artefacts mandatory; Codex only; equivalents permitted but specific mapping approval pending. | Use mapping in P18–P22 and submission inventory. | [ ] Obtain/record recruiter response; do not imply approval or fabricate Claude files. |
| U01 | User/AGENTS: Java, Maven, JUnit 5; prefer Java 21 if compatible. | Retain current scaffold. | [x] Java 21.0.12.1 and Maven 3.10.0; release 21 and JUnit 5; clean verify passed. |
| U02 | User/AGENTS: minimal, maintainable, explainable; no Spring Boot, database, web server, or unnecessary dependencies. | Small CLI with calculation and CSV responsibilities separated only as needed. | [x] Four small production classes, no third-party runtime dependencies; design reviewed in CODE_REVIEW.md. |
| U03 | User/AGENTS: portable to macOS and Linux. | Platform-neutral Java and documented terminal commands. | [ ] Platform checks where available, with honest limitations. |
| U04 | User/AGENTS: validate input and meaningful errors. | Define validation/error contract before coding; proposed cases below. | [x] Malformed input, invalid paths and output preservation tested; permission/disk-full fault injection not performed. |
| U05 | User/AGENTS: important behaviour and edge-case tests; relevant tests after implementation. | JUnit cases derived from verified rules and approved interface. | [x] 43 tests passed, zero failures/errors/skips in Stage 8. |
| U06 | User/AGENTS: verified historical rules; no invented fixtures, scores, sources, or research. | Cite evidence; label synthetic unit-test fixtures clearly. | [ ] Source review and independent data/output comparison. |
| U07 | User/AGENTS: document ambiguities and assumptions; focused changes and design explanations. | Checklist now; approved decisions and usage in README later. | [ ] Review assumptions with user and explain implementation incrementally. |
| U08 | User/AGENTS: genuine collaboration, no fabricated sessions/exports/reflections; review sensitive information before committing. | Preserve originals privately; use identified redacted copies when needed with provenance. | [ ] Review intended public artefacts and all eventual commits for sensitive content. Current records have not been cleared for publication. |
| U09 | User: no staging, commits, pushes, or publication without explicit approval. | Keep authorization gates separate from future submission obligations. | [ ] Obtain relevant approval before those actions. No Git mutations in this stage. |
| U10 | Current task: historical research only; update both Markdown documents and stop for approval. | Update REQUIREMENTS_CHECKLIST.md and HISTORICAL_RESEARCH.md only. | [x] Earlier research-only stage completed; later user approvals authorized implementation and Stage 8 review. |

## Approved and implemented contract

Invocation: java -cp target/classes com.spandigital.standings.Main <input.csv> <output.csv>.

Input header: date,home_team,away_team,home_goals,away_goals. Output header: position,team,played,won,drawn,lost,goals_for,goals_against,goal_average,points. UTF-8, one record per physical line, LF/CRLF input, optional initial BOM, quoted commas/doubled quotes supported. Multiline fields are rejected. Dates are validated metadata; every supplied game is counted. Invalid/header-only/empty input is rejected. Names are exact case-sensitive strings; duplicate date/home/away keys are rejected. Existing regular output files may be replaced after validation. Exit codes: success 0, data/I/O failure 1, usage 2. See README.md and CODE_REVIEW.md for full policies.

## Historical acceptance evidence

The user approved all actual league games from 17 August through 28 September 1974 inclusive. data/results.csv has 107 matches and 22 clubs; six clubs have nine games and sixteen have ten. Two-point scoring and exact goal-average ordering are tested. Shared sporting positions, alphabetical display and zero-denominator conventions were expressly approved as application assumptions, not historical discoveries.

The 22-row fixed historical expectation is source-transcribed; the final generated output matches it. Independent arithmetic checks also passed, but do not prove historical accuracy. Fixture-source coverage, shared data lineage uncertainty and reference-table discrepancies remain documented in data/SOURCES.md, HISTORICAL_RESEARCH.md and CODE_REVIEW.md. No new historical source audit is claimed by Stage 8.

## Required submission inventory and Codex mapping

Names for historical data files below are proposals; the PDF requires their contents and inclusion, not these filenames.

| File / directory | Basis and purpose | Current state / acceptance condition |
|---|---|---|
| `pom.xml`, `src/main/java/` | Java/Maven working solution; P01/P05, U01. | Implemented and reviewed; clean verify passed. |
| `src/test/java/` | Mandatory automated tests; P14. | 43 passing behavioural/CLI/historical test invocations. |
| `data/results.csv` | Mandatory actual input; P10. | Exists: 107 records; approved input with documented source limitations. |
| `data/standings.csv` | Mandatory actual output; P10. | Exists: 22 rows; regenerated byte-identically in Stage 8. |
| `README.md` | PDF submission tree and setup guidance; P15/P23. | Exists and reviewed; final documentation and clean-checkout verification remain. |
| `AGENTS.md` | Proposed equivalent of root CLAUDE.md; P19. | Exists; recruiter mapping approval pending. |
| Genuine Codex session JSONL, currently `ai/codex-sessions/` | Proposed equivalent of .claude/ session category; P20. | User confirms initial origin verification, original private backup and reviewed JSONL copy. Final verification and mapping acceptance remain open. |
| `ai/` conversation exports | Mandatory conversation-history category; P21. | User confirms reviewed initial copy placed here. Final coverage and dual-category mapping acceptance remain open; no fake records. |
| `AI_REFLECTION.md` | Mandatory reflection; P22. | Missing; write later from actual user experience. |
| `.gitignore` | Supporting project hygiene, P13/U08. | Exists; inspect final tracked contents as ignoring a file does not remove it from Git history. |
| `REQUIREMENTS_CHECKLIST.md` | Explicit current user deliverable, not named by PDF. | This document. |
| `HISTORICAL_RESEARCH.md` | Explicit user research deliverable, not named by PDF. | Research preserved; user approved cutoff/fallbacks. Data provenance and limitations are in data/SOURCES.md. |

No fake CLAUDE.md or .claude/ is required for the proposed Codex submission. Do not assume a Codex folder alone proves equivalence. Explain actual source locations, export/redaction provenance and category coverage. The PDF itself, downloaded libraries, build output, GitHub Actions, Docker, Maven Wrapper and a prebuilt JAR are not explicitly required submission files. Whether to redistribute the assessment PDF is a separate decision, not permission inferred from its presence locally.

## Outstanding acceptance and risks

- Recruiter acceptance of the Codex artefact mapping remains pending.
- Final README review, genuine user-grounded AI_REFLECTION.md, and complete AI evidence/privacy review remain outstanding.
- macOS/Linux execution and clean-checkout setup were not tested. Reviewer needs Java 21 and Maven.
- Historical source limitations and non-authoritative exact-tie/zero conventions must remain visible.
- Final tracked-file review, committed deliverables, public GitHub repository, URL delivery and on-time submission are not complete or authorized by this review.

## Next steps

Review CODE_REVIEW.md and the minimal correction. After approval, finalize documentation and genuine evidence, then seek explicit authorization for Git/publication actions. Stop here for user review; no staging, commits, pushes or publication were performed.
