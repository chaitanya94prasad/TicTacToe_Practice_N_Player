package strategies.winningStrategy;

import model.Board;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy{

    private int dimension;
    private List<HashMap<Character,Integer>> rowSymbolCount = new ArrayList<>();
    private List<HashMap<Character,Integer>> colSymbolCount = new ArrayList<>();
    private HashMap<Character,Integer> topLeftSymbolCount = new HashMap<>();
    private HashMap<Character,Integer> bottomLeftSymbolCount = new HashMap<>();
    private HashMap<Character,Integer> cornerSymbolCount = new HashMap<>();

    public OrderOneWinningStrategy(int dimension) {
        this.dimension = dimension;
        for(int i = 0; i < dimension; i++) {
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }

    public Boolean isCellTopLeftDiagonal(int row, int col) {
        return (row == col);
    }

    public Boolean isCellBottomLeft(int row, int col) {
        return (row + col == dimension -1);
    }

    public Boolean isCornerCell(int row,int col) {
        if(row == 0 || row == dimension - 1) {
            return (col == 0 || col == dimension - 1);
        }
        return false;
    }

    @Override
    public Player checkWinner(Board board) {
        return null;
    }
}
