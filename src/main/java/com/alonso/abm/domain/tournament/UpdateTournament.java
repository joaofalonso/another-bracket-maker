package com.alonso.abm.domain.tournament;

import com.alonso.abm.domain.tournament.TournamentState;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UpdateTournament(Long id, String name, LocalDateTime startDate, LocalDateTime finalDate, TournamentState state) {
}
