package home.edward.restfulTicTacToe.game;

import lombok.Data;

/**
 * 15.09.2019 22:21
 *
 * @author Edward
 */
@Data
public class GameTable {

    static final int MAX_NUMBER_OF_CELLS = 9;
    static final String X_SYMBOL = "X";
    static final String O_SYMBOL = "O";
    static final String TIE_SYMBOL = "T";
    private final String[] cells = new String[MAX_NUMBER_OF_CELLS];
    private int emptySquaresLeft;

    /**
     * initialize game table with length of max number of cells by empty strings
     */
    public GameTable() {
        this.emptySquaresLeft = MAX_NUMBER_OF_CELLS;
        for (int i = 0; i < cells.length; i++) {
            cells[i] = "";
        }
    }

    /**
     * @return drawn with current situation game table
     */
    public String getPrettyPrintedCells() {
        StringBuilder stringBuilder = new StringBuilder();
        int rowLength = 3;
        stringBuilder.append("     |     |     \n");
        for (int i = 0; i < cells.length; i++) {
            if (i != 0) {
                if (i % rowLength == 0) {
                    stringBuilder.append("\n_____|_____|_____\n").append("     |     |     \n");
                } else
                    stringBuilder.append("|");
            }
            if (cells[i].equals("")) stringBuilder.append("  ").append(" ").append("  ");
            if (cells[i].equals(X_SYMBOL)) stringBuilder.append("  " + X_SYMBOL + "  ");
            if (cells[i].equals(O_SYMBOL)) stringBuilder.append("  " + O_SYMBOL + "  ");
        }
        stringBuilder.append("\n     |     |     \n");
        return stringBuilder.toString();
    }
}
