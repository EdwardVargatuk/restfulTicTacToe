package home.edward.restfulTicTacToe.service;

import home.edward.restfulTicTacToe.game.TicTacToeGame;
import home.edward.restfulTicTacToe.game.WinnerSearcher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 19.09.2019 12:13
 *
 * @author Edward
 */

public class GameFlowService {

    private final TicTacToeGame ticTacToeGame;
    private final WinnerSearcher winnerSearcher;

    @Autowired
    public GameFlowService(TicTacToeGame ticTacToeGame, WinnerSearcher winnerSearcher) {
        this.ticTacToeGame = ticTacToeGame;
        this.winnerSearcher = winnerSearcher;
    }

    /**
     * @param cellNumber target cell that need to be processed
     * @return result of user step in string representation
     */
    public String getResultAfterUserStep(int cellNumber) {
        if (isGameContinue()) {
            String validationResult = ticTacToeGame.validateUserStep(String.valueOf(cellNumber), ticTacToeGame.getGameTable().getCells());
            if (validationResult.equals("ok")) {
                return singlePlayerGameStep(cellNumber);
            } else return validationResult;
        } else return "Game Over! Start new Game!";
    }

    /**
     * @return true if there is no winner
     */
    private boolean isGameContinue() {
        String winner = winnerSearcher.lookForWinner(ticTacToeGame.getGameTable());
        return winner.equals("");
    }

    /**
     * Checks if there is winner after user step, and if not, perform a computer step and also check for winner
     *
     * @param cellNumber for user's step
     * @return always pretty printed game table
     */
    private String singlePlayerGameStep(int cellNumber) {
        ticTacToeGame.markCell(cellNumber, TicTacToeGame.PLAYER_USER);
        if (isGameContinue()) {
            ticTacToeGame.computerMove();
        }
        return ticTacToeGame.getGameTable().getPrettyPrintedCells() +
                ticTacToeGame.getResult(winnerSearcher.lookForWinner(ticTacToeGame.getGameTable()));
    }
}
