package it.polimi.ingsw.model;

/**
 * This enumeration contains all the colors used by marbles and cards
 */
public enum Color {
    BLUE {
        @Override
        void action(Player player) {
            player.getBoard().addResource(Resource.SHIELD);
        }
    },
    GREEN {
        void action(Player player) {
            /*only for cards*/
        };
    },
    GREY {
        @Override
        void action(Player player) {
            player.getBoard().addResource(Resource.STONE);
        }
    },
    PURPLE {
        @Override
        void action(Player player) {
            player.getBoard().addResource(Resource.SERF);
        }
    },
    RED {
        @Override
        void action(Player player) {
            FaithTrack path = player.getBoard().getPersonalPath();
            path.setPosition(path.getPosition() + 1);
        }
    },
    WHITE {
        @Override
        void action(Player player) {
            /*checks player's leaderCards and choose between 3 options:
                1. no leaderCard with WhiteMarbleConversion -> do nothing;
                2. one leaderCard with WhiteMarbleConversion -> return resource;
                3. two leaderCards //          //            -> ask the player which leader to use
             */
        }
    },
    YELLOW {
        @Override
        void action(Player player) {
            player.getBoard().addResource(Resource.COIN);
        }
    };
    abstract void action(Player player);
}
