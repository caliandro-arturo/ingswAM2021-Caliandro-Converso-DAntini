package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.toClient.updates.GameUpdate;

public interface ToClientMessageVisitor {
    void visit(CreateGame createGame);
    void visit(ErrorMessage errorMessage);
    void visit(GameUpdate gameUpdate);
    void visit(GameIsFull gameIsFull);
    void visit(AskWhiteMarble askWhiteMarble);
    void visit(DisplayEndingScores displayEndingScores);
    void visit(WaitGameStart waitGameStart);
}
