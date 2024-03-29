package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.model.Utility;
import it.polimi.ingsw.commonFiles.messages.toServer.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;

import java.util.*;

/**
 * GUI implementation of the view.
 */
public class GUIView extends View {

    /**
     * Shows messages in a label.
     *
     * @param element the element to show
     */
    @Override
    public void show(String element) {
        Platform.runLater(() -> {
            switch (element) {
                case "hand", "board", "market" -> {
                }
                default -> {
                    App.out.setStyle("-fx-background-color: null");
                    App.out.setText(element);
                }
            }
        });
    }

    /**
     * Shows an error.
     *
     * @param error the error to show
     */
    @Override
    public void showError(String error) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                show("");
            }
        };
        Timer timer = new Timer();
        Platform.runLater(() -> {
            App.out.setStyle("-fx-background-color: #d03c3c");
            App.out.setText(error);
        });
        timer.schedule(task, 4000);
    }

    @Override
    public void refresh(String... elements) {
    }

    /**
     * Sets the action to do, as a tip.
     *
     * @param id   the id of the action to do
     * @param toDo the action to do
     */
    @Override
    public void setToDo(String id, String toDo) {
        Platform.runLater(() -> {
            try {
                GamePanel gamePanel = (GamePanel) App.controller;
                gamePanel.getToolTipHelp().setText(toDo);
            } catch (ClassCastException ignore) {
            }
        });
    }

    @Override
    public void showHandler(String[] commandSlice) {
    }

    /**
     * Sends a setNickname message.
     */
    @Override
    public void setNick(String[] commandSlice) {
        String nickname;
        nickname = commandSlice[1].trim();
        if (nickname.equals("")) {
            App.out.setText("Empty nickname");
            return;
        }
        getController().sendMessage(new SetNickname(nickname));
    }

    /**
     * Sends a JoinGame message.
     */
    @Override
    public void joinGame(String[] commandSlice) {
        Platform.runLater(() -> App.out.setText("Joining " + commandSlice[1] + "..."));
        getController().sendMessage(new JoinGame(commandSlice[1]));
    }

    /**
     * Sends a SetGame message.
     */
    @Override
    public void setGame(String[] commandSlice) {
        getController().sendMessage(new SetGame(Integer.parseInt(commandSlice[1])));
    }

    /**
     * Sends the choice for the choose action phase.
     */
    @Override
    public void choose(String[] commandSlice) {
        String turnPhase;
        try {
            turnPhase = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        getController().sendMessage(new ChooseTurnPhase(turnPhase.trim().toLowerCase()));
    }

    /**
     * Activates a production.
     */
    @Override
    public void activateProduction(String[] commandSlice) {
        String argument;
        try {
            argument = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        String[] arguments = argument.split("\\s*,\\s*");
        String[] elements;
        ArrayList<Integer> cost = new ArrayList<>();
        int ID = Integer.parseInt(arguments[0]);
        if (ID < 0)
            throw new IllegalArgumentException("Invalid ID");
        if (ID == 0) {
            elements = arguments[3].split("\\s");
            for (String element : elements) {
                cost.add(Integer.parseInt(element));
            }
            elements = arguments[1].split("\\s");
            getController().sendMessage(new StartProduction(ID, cost, arguments[2], elements));
        } else if (ID <= 3) {
            elements = arguments[1].split("\\s");
            for (String element : elements) {
                cost.add(Integer.parseInt(element));
            }
            getController().sendMessage(new StartProduction(ID, cost));
        } else if (ID <= 5) {
            cost.add(Integer.parseInt(arguments[1]));
            getController().sendMessage(new StartProduction(ID, cost, arguments[2]));
        }

    }

    /**
     * Buys a card.
     */
    @Override
    public void buyDevCard(String[] commandSlice) {
        String arg = commandSlice[1];
        String[] arguments = arg.split("\\s*,\\s*");
        int level, space;
        String color;
        ArrayList<Integer> stores = new ArrayList<>();
        level = Integer.parseInt(arguments[0]);
        color = arguments[1];
        space = Integer.parseInt(arguments[2]);
        arguments = arguments[3].split("\\s");
        for (String argument : arguments) {
            stores.add(Integer.parseInt(argument));
        }
        getController().sendMessage(new BuyCard(level, color, space, stores));
    }

    /**
     * Uses the market.
     */
    @Override
    public void useMarket(String[] commandSlice) {
        String argument = commandSlice[1];
        String[] args = argument.split("\\s*,\\s*");

        if (args[0].toLowerCase().matches("[rc]") && args.length == 2) {
            getController().sendMessage(new UseMarket(args[0].charAt(0), Integer.parseInt(args[1])));
        } else
            showError("Wrong parameter");
    }

    /**
     * Chooses the white marble resource to get.
     */
    @Override
    public void chooseWhite(String[] commandSlice) {
        getModel().setWhiteMarbleQuantity(getModel().getWhiteMarbleQuantity() - 1);
        getController().sendMessage(new ChooseWhiteMarble(Integer.parseInt(commandSlice[1].trim())));
    }

    /**
     * Uses the leader.
     */
    @Override
    public void useLeader(String[] commandSlice) {
        getController().sendMessage(new UseLeader(Integer.parseInt(commandSlice[1])));
    }

    /**
     * Deploys a resource.
     */
    @Override
    public void deployRes(String[] commandSlice) {
        String argument = commandSlice[1];
        String[] args = argument.split("\\s*,\\s*");
        getController().sendMessage(new DeployRes(Utility.mapResource.get(args[0].trim().toLowerCase()), Integer.parseInt(args[1])));
    }

    /**
     * Discards a leader card from the hand.
     */
    @Override
    public void discardLeader(String[] commandSlice) {
        getController().sendMessage(new DiscardLeader(Integer.parseInt(commandSlice[1])));
    }

    /**
     * Takes a resource from a depot.
     */
    @Override
    public void takeRes(String[] commandSlice) {
        int depot;
        depot = Integer.parseInt(commandSlice[1]);
        getController().sendMessage(new TakeRes(depot));
    }

    /**
     * Gets a initial resource.
     */
    @Override
    public void getRes(String[] commandSlice) {
        Resource res = Utility.mapResource.get(commandSlice[1].toLowerCase());
        getController().sendMessage(new GetResource(res));
    }

    @Override
    public void printHead() {
    }

    @Override
    public void deleteToDo(String id) {
    }

    /**
     * Goes back to the choose action phase.
     */
    @Override
    public void back() {
        getController().sendMessage(new Back());
    }

    /**
     * Discards a resource.
     */
    @Override
    public void discardRes(String[] commandSlice) {
        String resource;
        resource = commandSlice[1].toLowerCase();
        if (Utility.mapResource.get(resource) != null) {
            if (Utility.isStorable(Utility.mapResource.get(resource))) {
                getController().sendMessage(new DiscardRes(Utility.mapResource.get(resource)));
                show("You have discarded a resource, each of your opponent earn a faith resource");
            }
        }
    }

    /**
     * Shows the end game pane with the final result, the ranking and specific victory points.
     *
     * @param scores  scores of the player
     * @param ranking final ranking with victory points for each player
     */
    @Override
    public void displayEndingScore(int[] scores, LinkedHashMap<String, Integer> ranking) {
        Platform.runLater(() -> {
            GamePanel gamePanel = (GamePanel) App.controller;
            gamePanel.closePopup(null);
            gamePanel.setOver(true);
            int playerScore = ranking.get(getModel().getPlayerUsername());
            int position = ranking.keySet().stream().toList().indexOf(getModel().getPlayerUsername());
            boolean hasWon;
            if (playerScore < 0) {
                hasWon = playerScore == -2;
                gamePanel.setSpecificResultLabel("You " + (hasWon ? "win" : "lose") + ".");
                gamePanel.getSpecificResultLabel().setTranslateY(80);
            } else {
                hasWon = position == 0;
                List<String> ordinals = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth"));
                gamePanel.setSpecificResultLabel("You got the " + ordinals.get(position) + " place.");
                ObservableList<PlayerScorePair> rankingForTableView = FXCollections.observableArrayList();
                ranking.forEach((player, score) -> rankingForTableView.add(new PlayerScorePair(player, score)));
                gamePanel.getRankingTabView().setDisable(false);
                gamePanel.getRankingTabView().setOpacity(1);
                gamePanel.getRankingTabView().setItems(rankingForTableView);
            }
            gamePanel.setMainResultLabel(hasWon ? "Victory" : "Defeat");
            gamePanel.getMainResultLabel().setStyle("-fx-text-fill: " + (hasWon ? "#2fff00" : "#c70303"));
            for (int i = 0; i < scores.length; i++) {
                gamePanel.getScores().get(i).setText(Integer.toString(scores[i]));
            }
            gamePanel.goFront(gamePanel.getEndGamePane());
        });
    }

    /**
     * Fill the table view with available matches.
     *
     * @param lobbiesName                          a list with matches names
     * @param lobbiesCurrentConnectedClientsNumber a list with the number of clients currently connected to the game
     * @param lobbiesMaxPlayersNum                 a list with the max number of players allowed in a game
     */
    @Override
    public void showGamesList(List<String> lobbiesName, List<Integer> lobbiesCurrentConnectedClientsNumber, List<Integer> lobbiesMaxPlayersNum) {
        Launcher launcher = (Launcher) App.controller;
        for (int i = 0; i < lobbiesName.size(); i++) {
            launcher.lobbies.add(new Lobby(lobbiesName.get(i), lobbiesCurrentConnectedClientsNumber.get(i), lobbiesMaxPlayersNum.get(i)));
        }

    }

    @Override
    public void showWhiteMarble() {
    }

    /**
     * Shows the Lorenzo action announcement.
     *
     * @param announcement the action of Lorenzo.
     */
    @Override
    public void showLorenzoAction(String announcement) {
        Platform.runLater(() -> ((GamePanel) App.controller).showSoloAction(announcement));
    }

    /**
     * Shows that the nickname has been set.
     */
    @Override
    public void showNicknameSet() {
        Platform.runLater(() -> App.out.setText("Your nickname has been set."));
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.createOrJoinPane);
    }

    /**
     * Shows that the player joined the game.
     */
    @Override
    public void showGameJoined() {
        show("You joined the game.");
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.waitPane);
    }

    /**
     * Starts the game when the player has reconnected.
     */
    @Override
    public void showResume() {
        Launcher launcher = (Launcher) App.controller;
        Platform.runLater(() -> {
            launcher.getNicknameLabel().setText(getModel().getPlayerUsername());
            launcher.setCurrentPane(launcher.resumePane);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4),
                    event -> App.setScene("board", "Masters of Renaissance")));
            timeline.play();
        });
    }

    /**
     * Shows that the game has been set.
     *
     * @param playersNum the number of players allowed in the game
     */
    @Override
    public void showGameSet(int playersNum) {
        show("Your game has been set.");
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.waitPane);
    }

    /**
     * Shows a waiting message.
     */
    @Override
    public void showWaitGameStart() {
        Launcher launcher = (Launcher) App.controller;
        Platform.runLater(() -> launcher.waitLabel.setText("Waiting the start of the game..."));
        launcher.setCurrentPane(launcher.waitPane);
    }

    /**
     * Launch the game interface.
     */
    @Override
    public void showGameStarted() {
        Platform.runLater(() -> App.setScene("board", "Masters of Renaissance"));
    }


    /**
     * Shows that a new player joined the game.
     *
     * @param playerNick the nickname of the new player
     */
    @Override
    public void showNewPlayer(String playerNick) {
        show(playerNick + " joined the game.");
    }

    /**
     * Prints that a player has lost the connection.
     *
     * @param playerNick the nick of the player that has lost the connection
     */
    @Override
    public void showPlayerLeft(String playerNick) {
        show(playerNick + " has disconnected.");
    }

    /**
     * Shows a pane that asks the player to select initial resources.
     */
    @Override
    public void showGetInitialResource() {
        show("Choose one resource to get");
        Platform.runLater(() -> {
            GamePanel controller = (GamePanel) App.controller;
            controller.getInitialResourcesButton().setDisable(false);
            controller.getInitialResourcesButton().setOpacity(1);
            controller.getTotalInitialResourcesAmount().setText(Integer.toString(getModel().getResourcesToGet()));
            controller.getInitialResourceIndex().setText("1");
            controller.goFront(controller.getGetInitialResourcesPane());
        });
    }

    /**
     * Shows that a resource has been added to the player's hand.
     *
     * @param resource the resource added to the player's hand
     */
    @Override
    public void showGotResource(String resource) {
        show("A " + resource + " has been added to your hand. Remember to deploy it!");
    }

    /**
     * Shows that the resource has been taken.
     */
    @Override
    public void showResourceTaken() {
        show("Resource taken.");
    }

    /**
     * Shows that a card has been bought, and goes to the next turn.
     */
    @Override
    public void showCardBought() {
        show("Card bought successfully.");
        Platform.runLater(() -> {
            GamePanel controller = (GamePanel) App.controller;
            controller.emptyPaymentBuffer();
            controller.closePopup(null);
            controller.getPaymentBox().setDisable(true);
            controller.getNextButton().fire();
        });
    }

    /**
     * Shows that the market has been used.
     */
    @Override
    public void showMarketUsed() {
        show("You have used the market: deploy or discard the resources in your hand.");
    }

    /**
     * Shows the result of a vatican report.
     *
     * @param reportNum the vatican report number
     * @param isPassed  the result of the vatican report
     */
    @Override
    public void showVaticanReport(int reportNum, boolean isPassed) {
        show("You "
                + (isPassed ? "passed" : "didn't pass")
                + " the vatican report number " + reportNum + ".");
    }

    /**
     * Shows that someone has reached an end game condition, and the reason.
     *
     * @param reason the reached condition
     */
    @Override
    public void showLastTurns(String reason) {
        show("Last turns: " + reason);
    }

    /**
     * Closes the actual scene and shows that the connection has been lost.
     */
    @Override
    public void showConnectionLost() {
        Platform.runLater(() -> App.setScene("connectionLost", "Masters of Renaissance"));
    }

    /**
     * Interrupts the game while the re-entering player has not received the actual status of the game.
     *
     * @param timeIsUp if the player is to wait or not
     */
    @Override
    public void showTimeUp(boolean timeIsUp) {
        if (timeIsUp) {
            Platform.runLater(() -> ((GamePanel) App.controller).startTimeUp());
        } else {
            Platform.runLater(() -> ((GamePanel) App.controller).removeTimeUp());
        }
    }

}
