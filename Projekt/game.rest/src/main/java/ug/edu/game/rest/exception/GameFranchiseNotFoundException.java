package ug.edu.game.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameFranchiseNotFoundException extends RuntimeException {
    public GameFranchiseNotFoundException() {
        super("Game franchise with given ID not found");
    }
}