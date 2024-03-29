package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.toClient.updates.GameUpdate;

public interface ToClientMessageVisitor {
    void visit(CreateGame createGame);
    void visit(ErrorMessage errorMessage);
    void visit(GameUpdate gameUpdate);
    void visit(EndingScores endingScores);
    void visit(WaitGameStart waitGameStart);
}
