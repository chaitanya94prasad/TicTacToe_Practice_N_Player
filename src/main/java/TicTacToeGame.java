import controller.GameController;
import enums.BotDifficultyLevel;
import enums.GameState;
import enums.PlayerType;
import model.Bot;
import model.Game;
import model.Player;
import model.Symbol;
import strategies.botPlayingStrategy.BotPlayingStrategyFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();
        System.out.println("Please enter the dimension of the game");
        int dimension = sc.nextInt();
        System.out.println("Will there be any bot in the game?(Y/N)");
        String isBotPresent = sc.next();

        List<Player> players = new ArrayList<>();
        int iteratorNumber = dimension - 1;

        if(isBotPresent.equals("Y")) {
            iteratorNumber = dimension - 2;
        }

        for (int i = 0; i < iteratorNumber; i++) {
            System.out.println("What is the name of the player number:" + i+1);
            String playerName = sc.next();
            System.out.println("What is the character symbol of the player");
            String characterSymbol = sc.next();
            players.add(new Player(new Symbol(characterSymbol.charAt(0)),playerName, PlayerType.HUMAN));
        }

        if(isBotPresent.equals("Y")) {
            System.out.println("What is the name of the BOT");
            String botName = sc.next();
            System.out.println("What is the character symbol of the BOT");
            String characterSymbol = sc.next();
//            TODO: take input for bot difficulty level and create object accordingly
            Bot bot = new Bot(new Symbol(characterSymbol.charAt(0)),botName,
                    BotDifficultyLevel.EASY,
                    BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel.EASY));
            players.add(bot);
        }

//        randomise the players
        Collections.shuffle(players);

        Game game = gameController.createGame(dimension,players);

        while(game.getGameState().equals(GameState.IN_PROGRESS)) {
            System.out.println("CURRENT BOARD STATUS");
            gameController.displayBoard(game);

            gameController.executeMove(game);

//            TODO: Logic for undo
        }

        System.out.println("GAME HAS ENDED, RESULT WAS : ");
        if (gameController.getGameState(game).equals(GameState.DRAW)) {
            System.out.println("GAME WAS A DRAW");
        } else {
            System.out.println("GAME IS WON BY : " + gameController.getWinner(game));
        }
    }
}
