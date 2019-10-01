package home.edward.restfulTicTacToe.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 25.08.2019 14:03
 *
 * @author Edward
 */
class TicTacToeGameTest {

    private TicTacToeGame ticTacToeGame;
    private GameTable gameTable;

    @BeforeEach
    void setUp() {
        ticTacToeGame = new TicTacToeGame();
        gameTable = ticTacToeGame.getGameTable();

    }


    @Test
    void isAllCellsHaveEmptyStringInNewGameTable() {
        String expected = "";

        String[] cells = gameTable.getCells();

        for (String cell : cells) {

            assertEquals(expected, cell);
        }
    }

    @Test
    void shouldGameTableHaveCorrectSize() {
        int expectedSize = GameTable.MAX_NUMBER_OF_CELLS;

        String[] cells = gameTable.getCells();

        assertEquals(expectedSize, cells.length);
    }

    @Test
    void shouldCountOfEmptySquaresInNewGameMatchTableSize() {
        int expectedSize = GameTable.MAX_NUMBER_OF_CELLS;

        int actualEmptySquaresLeft = gameTable.getEmptySquaresLeft();

        assertEquals(expectedSize, actualEmptySquaresLeft);
    }

    @Test
    void shouldPlayerSetSymbolX() {
        String expected = GameTable.X_SYMBOL;

        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_USER);
        String[] cells = gameTable.getCells();
        String actual = cells[4];

        assertEquals(expected, actual);
    }

    @Test
    void shouldComputerSetSymbolO() {
        String expected = GameTable.O_SYMBOL;

        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_COMPUTER);
        String[] cells = gameTable.getCells();
        String actual = cells[4];

        assertEquals(expected, actual);
    }

    @Test
    void shouldPCmakeStepsInRandomCell() {
        int expectedEmptySquares = 6;
        int expectedCountStingO = 3;
        int countStingO = 0;

        ticTacToeGame.computerMove();
        ticTacToeGame.computerMove();
        ticTacToeGame.computerMove();

        String[] cells = gameTable.getCells();
        for (String cell : cells) {
            if (cell.equals(GameTable.O_SYMBOL)) {
                countStingO++;
            }
        }
        assertEquals(expectedEmptySquares, gameTable.getEmptySquaresLeft());
        assertEquals(expectedCountStingO, countStingO);
    }


    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void doesUserCanEnterCorrectValuesOfNumberOfCell(int testInts) {
        String expectedString = "ok";
        String[] cells = gameTable.getCells();

        String actualString = ticTacToeGame.validateUserStep(String.valueOf(testInts), cells);

        assertEquals(expectedString, actualString);
    }


    @ParameterizedTest
    @ValueSource(strings = {"a", "Y", "?", "ЮЮ", "9", "11"})
    void doesUserCanEnterAnySymbolExceptNumbersFromZeroToEight(String strings) {
        String expectedString = "Please enter number of cell from 0 to 8";
        String[] cells = gameTable.getCells();

        String actualString = ticTacToeGame.validateUserStep(strings, cells);

        assertEquals(expectedString, actualString);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 8})
    void doesUserCanEnterOnlyNumberOfFreeCells(int testInts) {
        String expectedString = "Choose free cell and enter its number";
        String[] cells = gameTable.getCells();

        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(8, TicTacToeGame.PLAYER_COMPUTER);
        String actualString = ticTacToeGame.validateUserStep(String.valueOf(testInts), cells);

        assertEquals(expectedString, actualString);
    }


    @Test
    void shouldGetResultWhenGameOver() {
        String expectedCase1 = "You win!";
        String expectedCase2 = "You loose!";
        String expectedCase3 = "Tie!";
        String expectedDefaultCase = "";

        String actualCase1 = ticTacToeGame.getResult(GameTable.X_SYMBOL);
        String actualCase2 = ticTacToeGame.getResult(GameTable.O_SYMBOL);
        String actualCase3 = ticTacToeGame.getResult(GameTable.TIE_SYMBOL);
        String actualCase4 = ticTacToeGame.getResult("x");
        assertEquals(expectedCase1, actualCase1);
        assertEquals(expectedCase2, actualCase2);
        assertEquals(expectedCase3, actualCase3);
        assertEquals(expectedDefaultCase, actualCase4);
    }

}