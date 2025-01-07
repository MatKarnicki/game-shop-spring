package ug.edu.game.rest.exception;

public class GameDetailsNotFoundException extends RuntimeException {
    public GameDetailsNotFoundException(String message) {
        super(message);
    }
}
