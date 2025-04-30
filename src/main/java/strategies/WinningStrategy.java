package strategies;

import model.Board;
import model.Player;

public interface WinningStrategy {
    Player checkWinner(Board board);
}
