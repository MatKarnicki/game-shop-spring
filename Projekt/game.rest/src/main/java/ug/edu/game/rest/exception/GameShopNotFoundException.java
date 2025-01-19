package ug.edu.game.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameShopNotFoundException extends RuntimeException {
    public GameShopNotFoundException() {
        super("Game shop with given ID not found");
    }
}