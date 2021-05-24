package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.toClient.updates.GameUpdate;

public interface ToClientMessageVisitor {
    void visit(CreateGame createGame);
    void visit(ErrorMessage errorMessage);
    void visit(WaitGameCreation waitGameCreation);
    void visit(GameUpdate gameUpdate);
    void visit(GameIsFull gameIsFull);
    void visit(InitialResourcesAmount initialResourcesAmount);
    void visit(AskWhiteMarble askWhiteMarble);
    void visit(DisplayEndingScores displayEndingScores);
}
