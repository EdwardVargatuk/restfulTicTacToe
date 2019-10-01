package home.edward.restfulTicTacToe.game;

import lombok.Data;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Single player Game TIC-TAC-TOE
 * 25.08.2019 14:00
 *
 * @author Edward
 */

@Data
public class TicTacToeGame {

    private GameTable gameTable;
    public static final String PLAYER_USER = "user";
    public static final String PLAYER_COMPUTER = "pc";

    public TicTacToeGame() {
        gameTable = new GameTable();
    }

    /**
     * Mark cell by player symbol
     * decrement gameTable's emptySquaresLeft
     *
     * @param cellIndex target to be marked
     * @param player    who makes step
     */
    public void markCell(int cellIndex, String player) {
        switch (player) {
            case PLAYER_USER:
                gameTable.getCells()[cellIndex] = GameTable.X_SYMBOL;
                break;
            case PLAYER_COMPUTER:
                gameTable.getCells()[cellIndex] = GameTable.O_SYMBOL;
                break;
        }
        getGameTable().setEmptySquaresLeft(getGameTable().getEmptySquaresLeft() - 1);
    }

    public void computerMove() {
        int selectedSquare;
        selectedSquare = getRandomSquare();

        markCell(selectedSquare, PLAYER_COMPUTER);

    }

    private int getRandomSquare() {
        boolean gotEmptySquare = false;

        int selectedSquare;
        Random random = new Random();

        do {
            selectedSquare = random.nextInt(GameTable.MAX_NUMBER_OF_CELLS);

            if (gameTable.getCells()[selectedSquare].equals("")) {
                gotEmptySquare = true;
            }
        } while (!gotEmptySquare);

        return selectedSquare;
    }


    /**
     * @param winner can be empty
     * @return result according single player mode
     */
    public String getResult(String winner) {
        switch (winner) {
            case GameTable.X_SYMBOL:
                return "You win!";
            case GameTable.O_SYMBOL:
                return "You loose!";
            case GameTable.TIE_SYMBOL:
                return "Tie!";
            default:
                return "";
        }
    }

    /**
     * Validate that user enter only digits from 0 to 8 in empty square
     *
     * @param userInput symbol that enter user
     * @param cells     of current game table
     * @return string result of validation
     */
    public String validateUserStep(String userInput, String[] cells) {
        Pattern pattern = Pattern.compile("[0-8]");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.matches()) {
            Integer numberOfCell = Integer.valueOf(userInput);
            return cells[numberOfCell].equals("") ? "ok" : "Choose free cell and enter its number";
        } else return "Please enter number of cell from 0 to 8";
    }

}
