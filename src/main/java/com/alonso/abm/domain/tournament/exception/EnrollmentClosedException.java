package com.alonso.abm.domain.tournament.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//TODO: Not sure if it is the proper response code...
@ResponseStatus(value= HttpStatus.LOCKED)
public class EnrollmentClosedException extends RuntimeException{
}
