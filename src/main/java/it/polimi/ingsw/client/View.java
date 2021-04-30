package it.polimi.ingsw.client;

/**
 * View element of the MVC architectural pattern applied to the client.
 */
public abstract class View {
    private ClientController controller;

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    public ClientController getController() {
        return controller;
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
}
