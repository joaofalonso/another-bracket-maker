package com.alonso.abm.domain.player.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends RuntimeException{
}
