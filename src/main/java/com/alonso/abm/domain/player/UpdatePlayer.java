package com.alonso.abm.domain.player;

import java.time.LocalDate;

public record UpdatePlayer(Long id, String firstName, String lastName, String email, LocalDate dateOfBirth) {
}
