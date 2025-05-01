package controller;

import enums.GameState;
import model.Game;
import model.Player;
import strategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        try {
            Game.Builder builder = Game.builder();
            builder.setDimension(dimension);
            builder.setPlayers(players);
            builder.setWinningStrategies(winningStrategies);
            return builder.build();
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

    public void executeMove(Game game) {
        int nextPlayerIndex = game.getNextPlayerIndex();
        Player nextPlayerToPlay = game.getPlayers().get(nextPlayerIndex);
        nextPlayerToPlay.makeMove(game.getBoard());
    }

    public String getWinner(Game game) {
        return game.getWinner().getName();
    }
}
