package home.edward.restfulTicTacToe.game;

import lombok.Data;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 25.08.2019 14:00
 *
 * @author Edward
 */

@Data
public class TicTacToeGame {

    private  GameTable gameTable;
    public static final String PLAYER_USER = "user";
    public static final String PLAYER_COMPUTER = "pc";

    public TicTacToeGame() {
        gameTable = new GameTable();
    }

    public void markCell(int cellIndex, String player) {
        switch (player) {
            case PLAYER_USER:
                gameTable.getCells()[cellIndex] = "X";
                break;
            case PLAYER_COMPUTER:
                gameTable.getCells()[cellIndex] = "O";
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
            selectedSquare = random.nextInt(9);

            if (gameTable.getCells()[selectedSquare].equals("")) {
                gotEmptySquare = true;
            }
        } while (!gotEmptySquare);

        return selectedSquare;
    }


    public String getResult(String winner) {
        switch (winner) {
            case "X":
                return "You win!";
            case "O":
                return "You loose!";
            case "T":
                return "Tie!";
            default:
                return null;
        }
    }

    public String validateUserStep(String userInput, String[] cells) {
        Pattern pattern = Pattern.compile("[0-8]");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.matches()) {
            Integer numberOfCell = Integer.valueOf(userInput);
            if (cells[numberOfCell].equals("")) {
                return "ok";
            } else {
                return "Choose free cell and enter its number";
            }
        } else {
            return "Please enter number of cell from 0 to 8";
        }
    }


}
