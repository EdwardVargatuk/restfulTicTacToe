package home.edward.restfulTicTacToe.controllers;

import home.edward.restfulTicTacToe.game.GameTable;
import home.edward.restfulTicTacToe.game.TicTacToeGame;
import home.edward.restfulTicTacToe.game.WinnerSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 17.09.2019 7:41
 *
 * @author Edward
 */

@RequestMapping("game/")
@RestController
public class TicTacToeGameController {
    private final TicTacToeGame ticTacToeGame;

    private final WinnerSearcher winnerSearcher;

    @Autowired
    public TicTacToeGameController(TicTacToeGame ticTacToeGame, WinnerSearcher winnerSearcher) {
        this.ticTacToeGame = ticTacToeGame;
        this.winnerSearcher = winnerSearcher;
    }

    @GetMapping("start-new-game/")
    public ResponseEntity<Object> startNewGame() {
        ticTacToeGame.setGameTable(new GameTable());
        return ResponseEntity.ok().build();
    }

    @PostMapping("step/{cell-number}")
    public String makeStep(@PathVariable("cell-number") int cellNumber) {
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
