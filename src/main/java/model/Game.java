package model;

import enums.GameState;
import enums.PlayerType;
import exception.DuplicateSymbolException;
import exception.InvalidBotCountException;
import exception.InvalidDimensionException;
import exception.InvalidNumberOfPlayers;
import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(List<Player> players, Board board, List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.board = board;
        this.moves = new ArrayList<Move>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    public static class Builder {
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        public static Builder builder() {
            return new Builder();
        }

        private Builder() {
            this.players = new ArrayList<Player>();
            this.winningStrategies = new ArrayList<WinningStrategy>();
            this.dimension = 0;
        }

        public void setPlayers(List<Player> players) {
            this.players = players;
        }

        public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
        }

        public void setDimension(int dimension) {
            this.dimension = dimension;
        }

        public void addPlayer(Player player) {
            this.players.add(player);
        }

        public void addWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategies.add(winningStrategy);
        }

        private void validateBotCount() {
            int botCount = 0;
            for(Player player: players) {
                if(player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }

            if(botCount > 1) {
                throw new InvalidBotCountException("Bot count exceeded");
            }
        }

        private void validateDimension() {
            if (dimension < 3 || dimension > 10) {
                throw new InvalidDimensionException("Dimension can either be more than 2 or less than 11");
            }
        }

        private void validateNumberOfPlayers() {
            if(players.size() != dimension - 1) {
                throw new InvalidNumberOfPlayers("Players should be 1 less than dimension");
            }
        }

        private void validateUniqueSymbolForAllPlayers() {
            HashSet<Character> set = new HashSet<>();
            for (Player player: players) {
                set.add(player.getSymbol().getSymbolChar());
            }

            if (set.size() != players.size()) {
                throw new DuplicateSymbolException("Every Player should have unique symbol");
            }
        }

        private void validate() {
            validateBotCount();
            validateDimension();
            validateNumberOfPlayers();
            validateUniqueSymbolForAllPlayers();
        }

        private Game build() {
            return new Game(players,new Board(dimension),winningStrategies);
        }
    }
}
