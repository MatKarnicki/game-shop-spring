package ug.edu.game.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameDetailsNotFoundException extends RuntimeException {
    public GameDetailsNotFoundException() {
        super("Game details not found");
    }
}
