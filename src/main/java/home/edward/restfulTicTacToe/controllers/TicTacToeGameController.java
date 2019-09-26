package home.edward.restfulTicTacToe.controllers;

import home.edward.restfulTicTacToe.game.GameTable;
import home.edward.restfulTicTacToe.game.TicTacToeGame;
import home.edward.restfulTicTacToe.service.GameFlowService;
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

    private final GameFlowService gameFlowService;

    @Autowired
    public TicTacToeGameController(TicTacToeGame ticTacToeGame, GameFlowService gameFlowService) {
        this.ticTacToeGame = ticTacToeGame;
        this.gameFlowService = gameFlowService;
    }

    @GetMapping("start-new-game/")
    public ResponseEntity<Object> startNewGame() {
        ticTacToeGame.setGameTable(new GameTable());
        return ResponseEntity.ok().build();
    }

    @PostMapping("step/{cell-number}")
    public String makeStep(@PathVariable("cell-number") int cellNumber) {
        return gameFlowService.getResultAfterUserStep(cellNumber);
    }
}
