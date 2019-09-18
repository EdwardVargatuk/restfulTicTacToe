package home.edward.restfulTicTacToe.game;

import lombok.Data;

/**
 * 15.09.2019 22:21
 *
 * @author Edward
 */
@Data
public class GameTable {

    private final String[] cells = new String[9];
    private int emptySquaresLeft;

    public GameTable() {
        this.emptySquaresLeft = 9;
        for (int i = 0; i < cells.length; i++) {
            cells[i] = "";
        }
    }

    public String getPrettyPrintedCells() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("     |     |     \n");
        for (int i = 0; i < cells.length; i++) {
            if (i != 0) {
                if (i % 3 == 0) {
                    stringBuilder.append("\n_____|_____|_____\n").append("     |     |     \n");
                } else
                    stringBuilder.append("|");
            }
            if (cells[i].equals("")) stringBuilder.append("  ").append(" ").append("  ");
            if (cells[i].equals("X")) stringBuilder.append("  X  ");
            if (cells[i].equals("O")) stringBuilder.append("  O  ");
        }
        stringBuilder.append("\n     |     |     \n");
        return stringBuilder.toString();
    }
}
