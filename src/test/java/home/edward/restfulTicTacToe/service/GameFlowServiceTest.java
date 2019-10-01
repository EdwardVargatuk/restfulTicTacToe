package home.edward.restfulTicTacToe.service;

import home.edward.restfulTicTacToe.game.GameTable;
import home.edward.restfulTicTacToe.game.TicTacToeGame;
import home.edward.restfulTicTacToe.game.WinnerSearcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 01.10.2019 20:58
 *
 * @author Edward
 */
class GameFlowServiceTest {

    private TicTacToeGame ticTacToeGame;
    private GameTable gameTable;
    private GameFlowService gameFlowService;

    @BeforeEach
    void setUp() {
        ticTacToeGame = new TicTacToeGame();
        gameTable = ticTacToeGame.getGameTable();
        WinnerSearcher winnerSearcher = new WinnerSearcher();
        gameFlowService = new GameFlowService(ticTacToeGame, winnerSearcher);

    }

    @Test
    void shouldReturnCongratulationsIfUserWinAfterUserStep() {
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(2, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(1, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(3, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_USER);


        String actualResult = gameFlowService.getResultAfterUserStep(8);

        assertTrue(actualResult.contains("You win!"));

    }

    @Test
    void shouldReturnJeerIfUserLooseAfterUserStep() {
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(2, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(1, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(3, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(4, TicTacToeGame.PLAYER_COMPUTER);
        ticTacToeGame.markCell(8, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(5, TicTacToeGame.PLAYER_COMPUTER);

        String actualResult = gameFlowService.getResultAfterUserStep(6);

        assertTrue(actualResult.contains("You loose!"));
    }

    @Test
    void shouldNotReturnAnythingExceptGameTableIfThereIsEmptyStepsForMoveAfterUserStep() {
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_COMPUTER);

        String actualResult = gameFlowService.getResultAfterUserStep(4);

        assertFalse(actualResult.contains("You win!"));
        assertFalse(actualResult.contains("You loose!"));
        assertFalse(actualResult.contains("Tie!"));
    }

    @Test
    void shouldReturnValidationAttentionAfterUserStepAndDontMarkAnything() {
        ticTacToeGame.markCell(0, TicTacToeGame.PLAYER_USER);
        ticTacToeGame.markCell(2, TicTacToeGame.PLAYER_COMPUTER);
        int expected = gameTable.getEmptySquaresLeft();

        String actualResult = gameFlowService.getResultAfterUserStep(0);
        int actual = gameTable.getEmptySquaresLeft();

        assertTrue(actualResult.contains("Choose free cell and enter its number"));
        assertEquals(expected, actual);
    }
}