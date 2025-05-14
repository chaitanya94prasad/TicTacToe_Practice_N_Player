package controller;

import enums.GameState;
import model.Game;
import model.Move;
import model.Player;
import strategies.winningStrategy.OrderOneWinningStrategy;
import strategies.winningStrategy.WinningStrategy;

import java.util.List;

public class GameController {

//    , List<WinningStrategy> winningStrategies -> we removed this from argument that was supposed to be passed tp setWinningStrategies
//    as right now we are having only one winning strategy
    public Game createGame(int dimension, List<Player> players) {
        try {

            return Game.builder().setDimension(dimension)
                    .setPlayers(players)
                    .setWinningStrategies(List.of(new OrderOneWinningStrategy(dimension))).build();
        } catch (Exception e) {
            System.out.println("Could not start the Game");
        }

        return null;
    }

    public void displayBoard(Game game) {
        game.getBoard().printBoard();
    }

    public GameState getGameState(Game game) {
        return game.getGameState();
    }

    public Move executeMove(Game game, Player player) {
        Move move = player.makeMove(game.getBoard());
        updateGameMoves(game,move);
        return move;
    }

    private void updateGameMoves(Game game, Move move) {
        game.getMoves().add(move);
    }

    public Player checkWinner(Game game, Move recentMove)
    {
        for (WinningStrategy winningStrategy : game.getWinningStrategies()) {
            Player player = winningStrategy.checkWinner(game.getBoard(), recentMove);
            if (player != null) {
                return player;
            }
        }
        return null;
    }

    public String getWinner(Game game) {
        return game.getWinner().getName();
    }
}
