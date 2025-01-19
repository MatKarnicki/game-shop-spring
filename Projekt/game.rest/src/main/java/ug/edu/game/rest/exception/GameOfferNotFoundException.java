package ug.edu.game.rest.exception;

public class GameOfferNotFoundException extends RuntimeException {
    public GameOfferNotFoundException() {
        super("Game offer not found");
    }
}
