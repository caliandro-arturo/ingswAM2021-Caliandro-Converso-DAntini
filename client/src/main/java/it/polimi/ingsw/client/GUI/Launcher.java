package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.commonFiles.messages.toServer.GamesList;
import it.polimi.ingsw.commonFiles.messages.toServer.JoinGame;
import it.polimi.ingsw.commonFiles.messages.toServer.SetGame;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Launcher extends SceneHandler {
    @FXML
    public AnchorPane nicknamePane;

    @FXML
    public AnchorPane createGamePane;

    @FXML
    public AnchorPane waitPane;

    @FXML
    public AnchorPane connectionPane;

    @FXML
    public AnchorPane joinGame;

    @FXML
    public AnchorPane createOrJoinPane;

    @FXML
    public AnchorPane gamesListPane;

    @FXML
    public AnchorPane resumePane;

    @FXML
    public TableView<Lobby> gamesListTable;

    public ObservableList<Lobby> lobbies = FXCollections.observableArrayList();

    @FXML
    private TextField hostname;

    @FXML
    private TextField nameTextField;

    @FXML
    public Label waitLabel;

    @FXML
    private Button joinSelectedButton;

    private AnchorPane currentPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        App.setError(getOut());
        initTableView();
        currentPane = connectionPane;
        connectionPane.setOpacity(1);
        currentPane.setDisable(false);
    }

    public void initTableView() {
        gamesListTable.getSelectionModel().selectedItemProperty().addListener(e -> joinSelectedButton.setDisable(false));
        gamesListTable.setItems(lobbies);
    }

    public void setCurrentPane(AnchorPane pane) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(100), currentPane);
        fadeOut.setFromValue(100);
        fadeOut.setToValue(0);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(100), pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(100);
        currentPane.setDisable(true);
        SequentialTransition trans = new SequentialTransition(fadeOut, fadeIn);
        currentPane = pane;
        trans.play();
        pane.setDisable(false);
    }

    //main scene (connection)

    public void connect(ActionEvent actionEvent) {
        if (hostname.getText().equals("")) {
            getOut().setText("You must insert an ip address first.");
            return;
        }
        configureConnection(hostname.getText());
    }

    public void connectDefault(ActionEvent actionEvent) {
        configureConnection("");
    }

    private void configureConnection(String hostname) {
        getGui().configureConnection(hostname);
        if (getGui().isConnected()) {
            getGui().initializeClient();
            setCurrentPane(nicknamePane);
        }
    }

    //nickname scene

    public void setNickname(ActionEvent e) {
        if (nameTextField.getText().trim().equals(""))
            App.out.setText("You must insert a nickname.");
        else {
            App.out.setText("Logging with nickname: " + nameTextField.getText() + "...");
            getGui().getView().setNick(new String[]{nameTextField.getText()});
        }
    }

    //choosing between looking for open games and creating a new game

    public void showGamesList(ActionEvent actionEvent) {
        gamesListTable.getSelectionModel().clearSelection();
        joinSelectedButton.setDisable(true);
        setCurrentPane(gamesListPane);
        refreshGamesList(actionEvent);
    }

    public void createGame(ActionEvent actionEvent) {
        setCurrentPane(createGamePane);
    }


    //game list scene

    public void refreshGamesList(ActionEvent actionEvent) {
        lobbies.clear();
        getGui().getView().getController().sendMessage(new GamesList());
    }

    public void joinSelectedGame(ActionEvent actionEvent) {
        String lobby = gamesListTable.getSelectionModel().getSelectedItem().getLobbyName();
        App.out.setText("Joining " + lobby + "...");
        getGui().getView().getController().sendMessage(new JoinGame(lobby));
    }

    //create game

    public void setGame(ActionEvent e) {
        int playersNum = Integer.parseInt(((Button) e.getSource()).getText());
        getGui().getView().getController().sendMessage(new SetGame(playersNum));
    }



    //back button
    public void backOnCreateOrJoin(ActionEvent actionEvent) {
        setCurrentPane(createOrJoinPane);
    }


}
