package com.alonso.abm.domain.player;

import java.time.LocalDate;

public record CreatePlayer(String firstName, String lastName, String email, String nickName, LocalDate dateOfBirth) {
}
