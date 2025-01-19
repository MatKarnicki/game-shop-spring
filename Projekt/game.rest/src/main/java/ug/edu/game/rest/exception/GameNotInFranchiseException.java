package ug.edu.game.rest.exception;

public class GameNotInFranchiseException extends RuntimeException {
    public GameNotInFranchiseException() {
        super("Game is not part of specified franchise");
    }
}
