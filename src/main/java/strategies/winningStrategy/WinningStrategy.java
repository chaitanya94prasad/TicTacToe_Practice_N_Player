package strategies.winningStrategy;

import model.Board;
import model.Move;
import model.Player;

public interface WinningStrategy {
    public Player checkWinner(Board board, Move lastMove);
}
