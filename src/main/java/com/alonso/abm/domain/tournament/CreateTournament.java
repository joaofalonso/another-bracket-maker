package com.alonso.abm.domain.tournament;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateTournament(String name, LocalDateTime startDate) {
}
