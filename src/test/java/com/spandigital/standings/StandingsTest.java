package com.spandigital.standings;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// All fixtures in this class are synthetic, not historical claims.
class StandingsTest {
    private Match match(String home, String away, int hg, int ag) {
        return new Match(LocalDate.of(2026, 1, 1), home, away, hg, ag);
    }
    private Standings.Team team(String name, long gf, long ga, long points) {
        return new Standings.Team(name, 0, 0, 0, 0, gf, ga, points);
    }
    @Test void homeWin() {
        var rows = Standings.calculate(List.of(match("A", "B", 3, 1)));
        assertEquals(new Standings.Team("A", 1, 1, 0, 0, 3, 1, 2), rows.get(0).team());
        assertEquals(new Standings.Team("B", 1, 0, 0, 1, 1, 3, 0), rows.get(1).team());
    }
    @Test void awayWin() {
        var rows = Standings.calculate(List.of(match("A", "B", 0, 2)));
        assertEquals(new Standings.Team("B", 1, 1, 0, 0, 2, 0, 2), rows.get(0).team());
        assertEquals(new Standings.Team("A", 1, 0, 0, 1, 0, 2, 0), rows.get(1).team());
    }
    @Test void drawSharesRankAndSortsNames() {
        var rows = Standings.calculate(List.of(match("Z", "A", 2, 2)));
        assertEquals(new Standings.Team("A", 1, 0, 1, 0, 2, 2, 1), rows.get(0).team());
        assertEquals(new Standings.Team("Z", 1, 0, 1, 0, 2, 2, 1), rows.get(1).team());
        assertEquals(List.of(1, 1), rows.stream().map(Standings.Row::position).toList());
    }
    @Test void multipleMatchesAccumulateAllStatistics() {
        var rows = Standings.calculate(List.of(match("A", "B", 3, 1), match("B", "C", 0, 2), match("C", "A", 1, 1)));
        assertEquals(new Standings.Team("C", 2, 1, 1, 0, 3, 1, 3), rows.get(0).team());
        assertEquals(new Standings.Team("A", 2, 1, 1, 0, 4, 2, 3), rows.get(1).team());
        assertEquals(new Standings.Team("B", 2, 0, 0, 2, 1, 5, 0), rows.get(2).team());
    }
    @Test void pointsTakePriorityOverRatio() {
        assertTrue(Standings.compareSporting(team("A", 1, 10, 3), team("B", 10, 0, 2)) < 0);
    }
    @Test void exactRatiosBeatRoundedValuesAndGoalDifference() {
        var a = team("Z", 51, 62, 39);
        var b = team("A", 59, 72, 39);
        assertEquals("0.82", a.goalAverage());
        assertEquals("0.82", b.goalAverage());
        assertTrue(Standings.compareSporting(a, b) < 0);
        assertTrue(Standings.compareSporting(team("A", 3, 1, 2), team("B", 10, 5, 2)) < 0);
    }
    @Test void exactRatioTiesIgnoreGoalsScored() {
        assertEquals(0, Standings.compareSporting(team("A", 2, 1, 2), team("Z", 4, 2, 2)));
        var rows = Standings.calculate(List.of(match("Z", "X", 4, 2), match("A", "B", 2, 1)));
        assertEquals(List.of("A", "Z", "B", "X"), rows.stream().map(r -> r.team().name()).toList());
        assertEquals(List.of(1, 1, 3, 3), rows.stream().map(Standings.Row::position).toList());
    }
    @Test void infiniteAndZeroRatiosFollowApprovedConventions() {
        var infinity = team("A", 1, 0, 2);
        assertEquals("Infinity", infinity.goalAverage());
        assertTrue(Standings.compareSporting(infinity, team("B", 1000, 1, 2)) < 0);
        assertEquals(0, Standings.compareSporting(infinity, team("B", 9, 0, 2)));
        var undefined = team("A", 0, 0, 1);
        assertEquals("N/A", undefined.goalAverage());
        assertEquals(0, Standings.compareSporting(undefined, team("B", 0, 4, 1)));
        assertTrue(Standings.compareSporting(undefined, team("B", 1, 4, 1)) > 0);
        var rows = Standings.calculate(List.of(match("Z", "A", 0, 0)));
        assertEquals(List.of(1, 1), rows.stream().map(Standings.Row::position).toList());
        assertEquals("N/A", rows.get(0).team().goalAverage());
    }
    @Test void crossProductsDoNotOverflowLong() {
        long max = Long.MAX_VALUE;
        assertTrue(Standings.compareSporting(team("A", max, max - 1, 1), team("B", max - 1, max, 1)) < 0);
    }
    @Test void goalsAccumulateBeyondIntegerRange() {
        var rows = Standings.calculate(List.of(match("A", "B", Integer.MAX_VALUE, 1), match("A", "C", Integer.MAX_VALUE, 1)));
        assertEquals(4294967294L, rows.getFirst().team().goalsFor());
        assertEquals(4, rows.getFirst().team().points());
    }
}
