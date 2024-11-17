package com.alonso.abm.domain.player.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "invalid email format")
public class InvalidEmailException extends RuntimeException{
}
