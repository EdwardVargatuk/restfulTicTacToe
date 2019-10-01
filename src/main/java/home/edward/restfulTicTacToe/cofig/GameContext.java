package home.edward.restfulTicTacToe.cofig;

import home.edward.restfulTicTacToe.game.GameTable;
import home.edward.restfulTicTacToe.game.TicTacToeGame;
import home.edward.restfulTicTacToe.game.WinnerSearcher;
import home.edward.restfulTicTacToe.service.GameFlowService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 15.09.2019 22:27
 *
 * @author Edward
 */
@Configuration
public class GameContext {

    @Bean
    public GameTable gameTable() {
        return new GameTable();
    }

    @Bean
    public TicTacToeGame getTicTacToeGame() {
        return new TicTacToeGame();
    }

    @Bean
    public WinnerSearcher getWinnerSearcher() {
        return new WinnerSearcher();
    }

    @Bean
    public GameFlowService getGameFlowService(){
        return new GameFlowService(getTicTacToeGame(), getWinnerSearcher());
    }
}
