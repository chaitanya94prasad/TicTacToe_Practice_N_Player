package strategies.botPlayingStrategy;

import enums.CellState;
import model.Board;
import model.Cell;
import model.Move;
import model.Player;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Player player, Board board) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getBoard().get(i).get(j).equals(CellState.EMPTY)) {
                    return new Move(new Cell(i,j),player);
                }
            }
        }
        return null;
    }
}
