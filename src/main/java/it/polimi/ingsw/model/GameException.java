package it.polimi.ingsw.model;

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

    public static class IllegalGameSetting extends BaseException {
        public IllegalGameSetting(String message) {
            super(message);
        }
    }
    public static class GameAlreadyFull extends BaseException {
        public GameAlreadyFull() {
            super("the game is already full.");
        }
    }
    public static class NicknameAlreadyTaken extends BaseException {
        public NicknameAlreadyTaken() {
            super("your nickname has already been taken by another player in the game.");
        }
    }
    public static class IllegalMove extends BaseException {
        public IllegalMove() {
            super("you cannot do this now.");
        }
    }
    public static class MoveMadeOutsideOfHisTurn extends BaseException {
        public MoveMadeOutsideOfHisTurn() {
            super("It's not your turn: wait for the others to finish.");
        }
    }
    public static class InvalidArgument extends BaseException {
        public InvalidArgument(String message) {
            super(message);
        }
    }
}
