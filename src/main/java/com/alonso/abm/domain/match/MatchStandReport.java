package com.alonso.abm.domain.match;

public record MatchStandReport(Long playerId, Long matchId, Long winnerId, int winnerScore, int looserScore) {
}
