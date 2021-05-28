package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.View;
import javafx.application.Platform;

public class GUIView extends View {

    @Override
    public void process(String input) {
    }

    @Override
    public void show(String element) {
        //Platform.runLater(() -> App.out.setText(element));
    }

    @Override
    public void showError(String error) {
        Platform.runLater(() -> App.error.setText(error));
    }

    @Override
    public void showUpdate(String update) {
        //Platform.runLater(() -> App.out.setText(update));
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
}
