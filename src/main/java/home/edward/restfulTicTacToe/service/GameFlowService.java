package home.edward.restfulTicTacToe.service;

import home.edward.restfulTicTacToe.game.TicTacToeGame;
import home.edward.restfulTicTacToe.game.WinnerSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 19.09.2019 12:13
 *
 * @author Edward
 */
@Service
public class GameFlowService {
    private final TicTacToeGame ticTacToeGame;

    private final WinnerSearcher winnerSearcher;

    @Autowired
    public GameFlowService(TicTacToeGame ticTacToeGame, WinnerSearcher winnerSearcher) {
        this.ticTacToeGame = ticTacToeGame;
        this.winnerSearcher = winnerSearcher;
    }

    public String getResultAfterUserStep(int cellNumber){
        String validationResult = ticTacToeGame.validateUserStep(String.valueOf(cellNumber), ticTacToeGame.getGameTable().getCells());
        String winner = winnerSearcher.lookForWinner(ticTacToeGame);
        if (winner.equals("")) {
            if (validationResult.equals("ok")) {
                ticTacToeGame.markCell(cellNumber, TicTacToeGame.PLAYER_USER);
                winner = winnerSearcher.lookForWinner(ticTacToeGame);
                if (winner.equals("")) {
                    ticTacToeGame.computerMove();
                    winner = winnerSearcher.lookForWinner(ticTacToeGame);
                    if (!winner.equals("")) {
                        return ticTacToeGame.getGameTable().getPrettyPrintedCells() + ticTacToeGame.getResult(winner);
                    }
                } else {
                    return ticTacToeGame.getGameTable().getPrettyPrintedCells() + ticTacToeGame.getResult(winner);
                }
            } else {
                return validationResult;
            }
        } else {
            return "Game Over! Start new Game!";
        }

        return ticTacToeGame.getGameTable().getPrettyPrintedCells();
    }

}
