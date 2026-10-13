package com.spandigital.standings;

import java.time.LocalDate;

record Match(LocalDate date, String home, String away, int homeGoals, int awayGoals) {
    Match {
        if (date == null || home == null || away == null || home.isBlank() || away.isBlank()) {
            throw new IllegalArgumentException("Date and team names are required");
        }
        if (home.equals(away)) throw new IllegalArgumentException("Home and away teams must differ");
        if (homeGoals < 0 || awayGoals < 0) throw new IllegalArgumentException("Goals must be nonnegative");
    }
}
