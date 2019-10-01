package home.edward.restfulTicTacToe.game;

/**
 * 01.09.2019 21:03
 *
 * @author Edward
 */
public class WinnerSearcher {

    /**
     * method checks all possible cases for win according tic-tac-toe game rules
     *
     * @param gameTable that stores information about game process
     * @return winner symbol, tie or empty string
     */
    public String lookForWinner(GameTable gameTable) {

        String[] cells = gameTable.getCells();
        int emptySquaresLeft = gameTable.getEmptySquaresLeft();


        if (!cells[0].equals("") &&
                ((cells[0].equals(cells[1]) && cells[0].equals(cells[2])) ||
                        (cells[0].equals(cells[3]) && cells[0].equals(cells[6])) ||
                        (cells[0].equals(cells[4]) && cells[0].equals(cells[8])))) {
            return cells[0];

        } else if (!cells[2].equals("") &&
                ((cells[2].equals(cells[5]) && cells[2].equals(cells[8])) ||
                        (cells[2].equals(cells[4]) && cells[2].equals(cells[6])))) {
            return cells[2];

        } else if (!cells[4].equals("") &&
                ((cells[4].equals(cells[3]) && cells[4].equals(cells[5])) ||
                        (cells[4].equals(cells[1]) && cells[4].equals(cells[7])))) {
            return cells[4];

        } else if (!cells[6].equals("") && cells[6].equals(cells[7]) && cells[6].equals(cells[8])) {
            return cells[6];
        }

        if (emptySquaresLeft == 0) {
            return GameTable.TIE_SYMBOL;
        }
        return "";
    }

}
