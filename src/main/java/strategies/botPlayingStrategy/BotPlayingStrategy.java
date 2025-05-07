package strategies.botPlayingStrategy;

import model.Board;
import model.Move;
import model.Player;

public interface BotPlayingStrategy {
    Move makeMove(Player player, Board board);
}
