# SPAN Digital Backend Coding Test 2026

## Scope and technology

- Build a production-ready command-line football standings calculator for the English First Division, week 10 of the 1974/75 season.
- Use Java, Apache Maven, and JUnit 5. Prefer Java 21 LTS, subject to the installed environment and assessment requirements.
- Read input and write output as text CSV files. Verify the assessment's required schemas and command-line interface before implementing them.
- Keep execution portable to macOS and Linux. Avoid platform-specific paths, shell assumptions, and reliance on an IDE.
- Keep the application minimal, maintainable, and easy to explain. Avoid Spring Boot, databases, web servers, and unnecessary dependencies.

## Assessment and historical accuracy

- Read the assessment PDF when available before implementing assessment behaviour. If it is unavailable, say so and distinguish user-provided requirements from verified PDF requirements.
- Verify historical scoring and ranking rules before implementing them, including points awarded and tie-breaking rules. Do not assume modern rules apply.
- Do not invent historical fixtures, scores, or references. Record sources for historical claims and clearly label synthetic test data.
- Document assumptions and ambiguities, including the meaning of week 10 and any uncertainty about input, output, or ranking. Seek clarification when an ambiguity materially affects correctness.
- The user reports that SPAN's PDF permits equivalent AI artefacts for assistants other than Claude Code. Recruiter confirmation of the proposed Codex mapping is pending; do not present it as approved.

## Implementation and verification

- Make focused changes within the requested scope. Avoid unrelated refactoring and premature abstractions.
- Validate input and report meaningful errors with enough context to identify the problem.
- Write automated JUnit 5 tests for important behaviour and edge cases, including verified scoring and ranking rules, CSV handling, and invalid input.
- Run relevant tests after implementation. Verify packaging and command-line execution when affected by a change, using portable instructions.
- Never claim a command ran or tests passed unless verified. Report failures, skipped checks, and environmental limitations accurately.
- Explain major design decisions and trade-offs in plain language. Help the user understand the solution and how to build, run, test, and explain it.

## Genuine AI collaboration and repository safety

- Use Codex exclusively as the AI coding assistant for this assessment.
- Preserve genuine AI collaboration records throughout the assessment, including available local Codex JSONL session records. Do not overwrite or silently alter original evidence.
- Never fabricate AI sessions, exports, command output, or reflection content. Base any reflection assistance on actual collaboration and the user's own understanding.
- Review AI records for sensitive information before they are committed. If redaction is needed, use clearly identified redacted copies, preserve provenance, and keep sensitive originals out of commits.
- Do not stage, commit, push, or publish without the user's explicit approval. Approval for one action does not imply approval for the others.
- Do not expose secrets or personal information in explanations, logs, or shared artefacts.

## Current phase

- Only the root AGENTS.md is authorized for creation in this setup task. Do not create Java source code, Maven files, or other project artefacts until the user requests the next phase.
