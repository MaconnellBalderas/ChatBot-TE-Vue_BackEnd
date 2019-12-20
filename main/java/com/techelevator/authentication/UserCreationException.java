package com.techelevator.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UserCreationException
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserCreationException extends Exception {

    public UserCreationException(String messages) {
        super(messages);
    }

    private static final long serialVersionUID = 7187525188973772939L;
}