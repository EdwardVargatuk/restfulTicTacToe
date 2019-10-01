package home.edward.restfulTicTacToe.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 02.09.2019 22:38
 *
 * @author Edward
 */
class WinnerSearcherTest {

    private WinnerSearcher winnerSearcher;
    private TicTacToeGame ticTacToeGame;
    private GameTable gameTable;
    private static final String PLAYER_USER_SYMBOL = "X";
    private static final String PLAYER_COMPUTER_SYMBOL = "O";

    @BeforeEach
    void setUp() {
        winnerSearcher = new WinnerSearcher();
        ticTacToeGame = new TicTacToeGame();
        gameTable = ticTacToeGame.getGameTable();
    }

    @Test
    void shouldFindWinnerInCaseFirstRow() {
        WinnerSearcher winnerSearcher = new WinnerSearcher();
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(1, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(2, TicTacToeGame.PLAYER_USER);

        String actual = winnerSearcher.lookForWinner(gameTable);

        assertEquals(PLAYER_USER_SYMBOL, actual);

    }

    @Test
    void shouldFindWinnerInCaseSecondRow() {
        ticTacToeGame.markCell(3, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(5, TicTacToeGame.PLAYER_COMPUTER);

        String actual = winnerSearcher.lookForWinner(gameTable);

        assertEquals(PLAYER_COMPUTER_SYMBOL, actual);
    }

    @Test
    void shouldFindWinnerInCaseThirdRow() {
        ticTacToeGame.markCell(6, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(7, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(8, TicTacToeGame.PLAYER_USER);

        String actual = winnerSearcher.lookForWinner(gameTable);

        assertEquals(PLAYER_USER_SYMBOL, actual);
    }

    @Test
    void shouldFindWinnerInCaseFirstColumn() {
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(3, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(6, TicTacToeGame.PLAYER_USER);

        String actual = winnerSearcher.lookForWinner(gameTable);

        assertEquals(PLAYER_USER_SYMBOL, actual);
    }

    @Test
    void shouldFindWinnerInCaseSecondColumn() {
        ticTacToeGame.markCell(1, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(7, TicTacToeGame.PLAYER_COMPUTER);

        String actual = winnerSearcher.lookForWinner(gameTable);

        assertEquals(PLAYER_COMPUTER_SYMBOL, actual);
    }

    @Test
    void shouldFindWinnerInCaseThirdColumn() {
        ticTacToeGame.markCell(2, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(5, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(8, TicTacToeGame.PLAYER_USER);

        String actual = winnerSearcher.lookForWinner(gameTable);

        assertEquals(PLAYER_USER_SYMBOL, actual);
    }

    @Test
    void shouldFindWinnerInCaseFromTopLeftToBottomRightDiagonal() {
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(8, TicTacToeGame.PLAYER_COMPUTER);

        String actual = winnerSearcher.lookForWinner(gameTable);

        assertEquals(PLAYER_COMPUTER_SYMBOL, actual);
    }

    @Test
    void shouldFindWinnerInCaseFromTopRightToBottomLeftDiagonal() {
        ticTacToeGame.markCell(2, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(6, TicTacToeGame.PLAYER_COMPUTER);

        String actual = winnerSearcher.lookForWinner(gameTable);

        assertEquals(PLAYER_COMPUTER_SYMBOL, actual);
    }

    @Test
    void shouldNotFindAnyWinnerInCaseNotTheSameMarkedCellsOneAfterAnotherAndThereIsNoEmptySquareLeft() {
        String expected = "T";
        int expectedEmptySquareLeft = 0;
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(2, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(1, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(3, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(8, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(6, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(7, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(5, TicTacToeGame.PLAYER_USER);

        String actual = winnerSearcher.lookForWinner(gameTable);

        assertEquals(expected, actual);
        assertEquals(expectedEmptySquareLeft, gameTable.getEmptySquaresLeft());
    }

    @Test
    void shouldReturnEmptyResultIfNotFindAnyWinnerAndThereIsEmptySquareLeft() {
        String expected = "";
        int unexpectedEmptySquareLeft = 0;
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(2, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(1, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(3, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(8, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(6, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(7, TicTacToeGame.PLAYER_COMPUTER);

        String actual = winnerSearcher.lookForWinner(gameTable);

        assertEquals(expected, actual);
        assertNotEquals(unexpectedEmptySquareLeft, gameTable.getEmptySquaresLeft());
    }
}