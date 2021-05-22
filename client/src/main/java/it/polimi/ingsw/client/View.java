package it.polimi.ingsw.client;

/**
 * View element of the MVC architectural pattern applied to the client.
 */
public abstract class View {
    private ClientController controller;
    private ClientModel model;

    public void setController(ClientController controller) {
        this.controller = controller;
        model = controller.getModel();
    }

    public ClientController getController() {
        return controller;
    }

    public ClientModel getModel() {
        return model;
    }

    /**
     * Processes the input.
     *
     * @param input input to process
     */
    public abstract void process(String input);

    /**
     * Shows elements to the user.
     *
     * @param element the element to show
     */
    public abstract void show(String element);

    /**
     * Shows an error message
     *
     * @param error the error to show
     */
    public abstract void showError(String error);

    /**
     * Shows an update.
     *
     * @param update the update to show
     */
    public abstract void showUpdate(String update);


    /**
     * Refresh the visual representation of an element if it's currently shown.
     *
     * @param elements the element to refresh if shown
     */
    public abstract void refresh(String... elements);

    /**
     * Reminds the player what he must do in a certain moment.
     * @param toDo the action to do
     */
    public abstract void setToDo(String toDo);

    public abstract void resetToDo();
}
