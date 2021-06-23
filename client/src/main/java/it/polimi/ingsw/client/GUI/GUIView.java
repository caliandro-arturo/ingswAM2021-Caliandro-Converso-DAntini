package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.model.Utility;
import it.polimi.ingsw.commonFiles.messages.toServer.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GUIView extends View {

    private boolean isInGame = false;

    @Override
    public void show(String element) {
        switch (element) {
            case "hand": {}
        }
        Platform.runLater(() -> App.out.setText(element));
    }

    @Override
    public void showError(String error) {
        Platform.runLater(() -> App.error.setText(error));
    }

    @Override
    public void refresh(String... elements) {
        if (elements[0].equals("")) return;
        GamePanel gamePanel = (GamePanel) App.controller;
        switch (elements[0]) {
            case "hand" -> {}
            case "board" -> {}
            case "market" -> {}
        }
    }

    @Override
    public void setToDo(String id, String toDo) {

    }

    @Override
    public void showHandler(String[] commandSlice) {

    }

    @Override
    public void setNick(String[] commandSlice) {
        getController().sendMessage(new SetNickname(commandSlice[0]));
    }

    @Override
    public void joinGame(String[] commandSlice) {

    }

    @Override
    public void setGame(String[] commandSlice) {

    }

    @Override
    public void choose(String[] commandSlice) {
        String turnPhase;
        try {
            turnPhase = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"CHOOSE: <turnphasename>\".");
            return;
        }
        getController().sendMessage(new ChooseTurnPhase(turnPhase.trim().toLowerCase()));
    }

    @Override
    public void activateProduction(String[] commandSlice) {
        String argument;
        try {
            argument = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"ACTIVATEPRODUCTION: <id>, <arg1>, ...\".");
            return;
        }
        String[] arguments = argument.split("\\s*,\\s*");
        String[] elements;
        ArrayList<Integer> cost = new ArrayList<>();
        int ID = Integer.parseInt(arguments[0]);
        try {
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
        } catch (NumberFormatException e) {
            System.err.println("You must insert a number.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void buyDevCard(String[] commandSlice) {
        String arg;
        try {
            arg = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"BUYDEVCARD: <arg1>, <arg2>, ...\".");
            return;
        }
        String[] arguments = arg.split("\\s*,\\s*");
        int level, space;
        String color;
        ArrayList<Integer> stores = new ArrayList<>();
        try {
            level = Integer.parseInt(arguments[0]);
            color = arguments[1];
            space = Integer.parseInt(arguments[2]);
            arguments = arguments[3].split("\\s");
            for (String argument : arguments) {
                stores.add(Integer.parseInt(argument));
            }
            getController().sendMessage(new BuyCard(level, color, space, stores));
        } catch (NumberFormatException e) {
            System.err.println("You must insert a number.");
        }
    }

    @Override
    public void useMarket(String[] commandSlice) {
        String argument;
        try {
            argument = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"USEMARKET: <rowOrColumn>, <number>\".");
            return;
        }
        String[] args = commandSlice[1].split("\\s*,\\s*");
        if (args[0].toLowerCase().matches("[rc]") && args.length == 2) {
            try {
                getController().sendMessage(new UseMarket(args[0].charAt(0), Integer.parseInt(args[1])));
            } catch (NumberFormatException e) {
                System.err.println("Wrong parameter");
            }
        } else
            System.err.println("Wrong parameter");
    }

    @Override
    public void chooseWhite(String[] commandSlice) {
        try {
            getController().sendMessage(new ChooseWhiteMarble(Integer.parseInt(commandSlice[1].trim())));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.err.println("You must insert a leader position (1 or 2).");
        }
    }

    @Override
    public void useLeader(String[] commandSlice) {
        int pos;
        String argument;
        try {
            argument = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"USELEADER: <position number>\".");
            return;
        }
        try {
            pos = Integer.parseInt(commandSlice[1]);
            getController().sendMessage(new UseLeader(pos));
        } catch (NumberFormatException e) {
            System.err.println("You must insert a number.");
        }
    }

    @Override
    public void deployRes(String[] commandSlice) {
        String argument;
        try {
            argument = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"DEPLOYRES: <resource name, depot position number>\".");
            return;
        }
        String[] args = argument.split("\\s*,\\s*");
        Optional<Resource> toGet = Optional.ofNullable(Utility.mapResource.get(args[0].trim().toLowerCase()));
        toGet.ifPresentOrElse(res -> {
            try {
                getController().sendMessage(new DeployRes(res, Integer.parseInt(args[1])));
            } catch (NumberFormatException e) {
                System.err.println("Wrong parameter: you must insert the position in which you want to deploy the resource.");
            }
        }, () -> System.err.println("Wrong parameter: you must insert a resource name."));
    }

    @Override
    public void discardLeader(String[] commandSlice) {
        getController().sendMessage(new DiscardLeader(Integer.parseInt(commandSlice[1])));
    }

    @Override
    public void takeRes(String[] commandSlice) {
        int depot;
        try{
            depot=Integer.parseInt(commandSlice[1]);
            getController().sendMessage(new TakeRes(depot));
        }catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
            System.err.println("you must insert a number");
        }
    }

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

    @Override
    public void back() {
        getController().sendMessage(new Back());
    }

    @Override
    public void discardRes(String[] commandSlice) {
        String resource;
        try {
            resource = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"DISCARDRES: <resource name>\".");
            return;
        }
        if (Utility.mapResource.get(commandSlice[1]) != null){
            if (Utility.isStorable(Utility.mapResource.get(commandSlice[1]))){
                getController().sendMessage(new DiscardRes(Utility.mapResource.get(commandSlice[1])));
            }
        }
    }

    @Override
    public void displayEndingScore(String[] categories, int[] scores, int ranking) {

    }

    @Override
    public void showGamesList(List<String> lobbiesName, List<Integer> lobbiesCurrentConnectedClientsNumber, List<Integer> lobbiesMaxPlayersNum) {
        Launcher launcher = (Launcher) App.controller;
        for (int i = 0; i < lobbiesName.size(); i++) {
            launcher.lobbies.add(new Lobby(lobbiesName.get(i), lobbiesCurrentConnectedClientsNumber.get(i), lobbiesMaxPlayersNum.get(i)));
        }

    }

    @Override
    public void showLorenzoAction(String announcement) {

    }

    @Override
    public void showTablePosition(int position) {

    }

    @Override
    public void showNicknameSet() {
        Platform.runLater(() -> App.out.setText("Your nickname has been set."));
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.createOrJoinPane);
    }

    @Override
    public void showGameJoined() {
        show("You joined the game.");
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.waitPane);
    }

    @Override
    public void showResume() {
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.resumePane);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4),
                event -> Platform.runLater(() -> App.setScene("board", "Masters of Renaissance"))));
        timeline.play();
    }

    @Override
    public void showCreateGame() {
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.createGamePane);
    }

    @Override
    public void showGameSet(int playersNum) {
        show("Your game has been set.");
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.waitPane);
    }

    @Override
    public void showWaitGameStart() {
        Launcher launcher = (Launcher) App.controller;
        Platform.runLater(() -> launcher.waitLabel.setText("Waiting the start of the game..."));
        launcher.setCurrentPane(launcher.waitPane);
    }

    @Override
    public void showGameStarted() {
        Platform.runLater(() -> App.setScene("board", "Masters of Renaissance"));
    }

    @Override
    public void showNewPlayer(String playerNick) {
        show(playerNick + " joined the game.");
    }

    @Override
    public void showPlayerLeft(String playerNick) {
        show(playerNick + " has disconnected.");
    }

    @Override
    public void showGotResource(String resource) {

    }

    @Override
    public void showResourceTaken() {

    }

    @Override
    public void showMarketUsed() {

    }

    @Override
    public void showVaticanReport(int reportNum, boolean isPassed) {

    }

    @Override
    public void showLastTurns(String reason) {

    }

    @Override
    public void showConnectionLost() {
        Platform.runLater(() -> App.setScene("connectionLost", "Masters of Renaissance"));
    }

    @Override
    public void showTimeUp(boolean timeIsUp) {
        /*Platform.runLater(() -> ((GamePanel) App.controller).goFront(WaitingPane));*/
    }
}
