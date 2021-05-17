package it.polimi.ingsw.server.model;

/**
 * Custom exceptions used for handling not-allowed moves.
 */
public class GameException {

    public abstract static class BaseException extends Exception {
        private final String message;

        public BaseException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

    /**
     * Thrown when a game is full and a player is trying to enter the game.
     */
    public static class GameAlreadyFull extends BaseException {
        public GameAlreadyFull() {
            super("the game is already full.");
        }
    }

    /**
     * Thrown when a player tries to enter the game but another player in the game has taken his nickname.
     */
    public static class NicknameAlreadyTaken extends BaseException {
        public NicknameAlreadyTaken() {
            super("your nickname has already been taken by another player in the game.");
        }
    }

    /**
     * Thrown when a move is not allowed in that moment.
     */
    public static class IllegalMove extends BaseException {
        public IllegalMove() {
            super("you cannot do this now.");
        }
    }

    /**
     * Thrown when a player tries to make a move out of his turn.
     */
    public static class MoveMadeOutsideOfHisTurn extends BaseException {
        public MoveMadeOutsideOfHisTurn() {
            super("It's not your turn: wait for the others to finish.");
        }
    }
}
