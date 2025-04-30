package strategies;

import model.Board;
import model.Move;

public interface BotPlayingStrategy {
    public Move makeMove(Board board);
}
