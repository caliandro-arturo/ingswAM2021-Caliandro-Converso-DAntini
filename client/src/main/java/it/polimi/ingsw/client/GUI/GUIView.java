package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GUIView extends View {
    @FXML
    TextField text;

    @Override
    public void process(String input) {

    }

    @Override
    public void show(String element) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showUpdate(String update) {

    }

    @Override
    public void refresh(String... elements) {

    }

    @Override
    public void setToDo(String id, String toDo) {

    }

    @Override
    public void deleteToDo(String id) {

    }

    @Override
    public void displayEndingScore(String[] categories, int[] scores, int ranking) {

    }

    @Override
    public void showTablePosition(int position) {

    }

    public void takeDefault(ActionEvent actionEvent) {
    }

    public void takeText(ActionEvent actionEvent) {
        System.out.println("ciao");
    }
}
