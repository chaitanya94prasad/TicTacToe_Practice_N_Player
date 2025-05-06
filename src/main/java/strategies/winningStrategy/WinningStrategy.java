package strategies.winningStrategy;

import model.Board;
import model.Player;

public interface WinningStrategy {
    public Player checkWinner(Board board);
}
