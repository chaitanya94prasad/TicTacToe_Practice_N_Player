package strategies.winningStrategy;

import exception.GameDrawException;
import model.Board;
import model.Move;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy{

    private int dimension;
    private int symbolsAdded;
    private List<HashMap<Character,Integer>> rowSymbolCount = new ArrayList<>();
    private List<HashMap<Character,Integer>> colSymbolCount = new ArrayList<>();
    private HashMap<Character,Integer> topLeftSymbolCount = new HashMap<>();
    private HashMap<Character,Integer> bottomLeftSymbolCount = new HashMap<>();
    private HashMap<Character,Integer> cornerSymbolCount = new HashMap<>();

    public OrderOneWinningStrategy(int dimension) {
        this.symbolsAdded = 0;
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
    public Player checkWinner(Board board, Move lastMove) {
        symbolsAdded++;
        Player lastMovePlayer = lastMove.getPlayer();
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();
        int dimension = board.getSize();

        if(checkForRows(row,col,symbol,lastMove) != null) {
            return lastMovePlayer;
        } else if(checkForColumns(row,col,symbol,lastMove) != null) {
            return lastMovePlayer;
        } else if(checkForCorner(row,col,symbol,lastMove) != null) {
            return lastMovePlayer;
        } else if(checkForDiagonalWins(row,col,symbol,lastMove) != null) {
            return lastMovePlayer;
        }

        if(symbolsAdded == (dimension*dimension)) {
            board.printBoard();
            throw new GameDrawException("Game is drawn as cells are full");
        }

        return null;
    }

    private Player checkForDiagonalWins(int row, int col, char symbol, Move lastMove) {

//        bottom left
        if(isCellBottomLeft(row,col)) {
            if(!bottomLeftSymbolCount.containsKey(symbol)) {
                bottomLeftSymbolCount.put(symbol,0);
            }

            bottomLeftSymbolCount.put(symbol,bottomLeftSymbolCount.get(symbol) + 1);

            if (bottomLeftSymbolCount.get(symbol) == dimension) {
                return lastMove.getPlayer();
            }
        }

//        check for diagonals

        if(isCellTopLeftDiagonal(row,col)) {
            if(!topLeftSymbolCount.containsKey(symbol)) {
                topLeftSymbolCount.put(symbol,0);
            }

            topLeftSymbolCount.put(symbol,topLeftSymbolCount.get(symbol) + 1);

            if (topLeftSymbolCount.get(symbol) == dimension) {
                return lastMove.getPlayer();
            }
        }

        return null;
    }

    private Player checkForCorner(int row, int col, char symbol, Move lastMove) {
        //        corner
        if(isCornerCell(row,col)) {
            if(!cornerSymbolCount.containsKey(symbol)) {
                cornerSymbolCount.put(symbol,0);
            }

            cornerSymbolCount.put(symbol,cornerSymbolCount.get(symbol) + 1);

            if (cornerSymbolCount.get(symbol) == dimension) {
                return lastMove.getPlayer();
            }
        }

        return null;
    }

    private Player checkForRows(int row, int col, char symbol, Move lastMove) {
        //        check for row
        if(!rowSymbolCount.get(row).containsKey(symbol)) {
            rowSymbolCount.get(row).put(symbol,0);
        }

        rowSymbolCount.get(row).put(symbol,rowSymbolCount.get(row).get(symbol) + 1);

        if(rowSymbolCount.get(row).get(symbol) == dimension) {
            return lastMove.getPlayer();
        }

        return null;
    }

    private Player checkForColumns(int row, int col, char symbol, Move lastMove) {
        //        check for column
        if(!colSymbolCount.get(col).containsKey(symbol)) {
            colSymbolCount.get(col).put(symbol,0);
        }

        colSymbolCount.get(col).put(symbol,colSymbolCount.get(col).get(symbol) + 1);

        if(colSymbolCount.get(col).get(symbol) == dimension) {
            return lastMove.getPlayer();
        }

        return null;
    }

    //TODO :
/*
    Implement 4 classes for Winning Strategy
    1. RowWinningStrategy
    2. ColumnWinningStrategy
    3. DiagonalWinningStrategy
    4. CornerWinningStrategy
 */
}
