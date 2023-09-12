package util;

import model.piece.*;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */
public class ChessUtil {
    /**
     * @param str .
     * @return coordinate.
     */
    public static int[] getIndex(String str) {
        int x = 0, y;
        int[] coordinates = new int[2];

        if (str.charAt(0) == 'b') {
            x = 1;
        } else if (str.charAt(0) == 'c') {
            x = 2;
        } else if (str.charAt(0) == 'd') {
            x = 3;
        } else if (str.charAt(0) == 'e') {
            x = 4;
        } else if (str.charAt(0) == 'f') {
            x = 5;
        } else if (str.charAt(0) == 'g') {
            x = 6;
        } else if (str.charAt(0) == 'h') {
            x = 7;
        }

        y = 8 - (str.charAt(1) - '0');

        coordinates[0] = y;
        coordinates[1] = x;

        return coordinates;
    }

    /**
     * @param type .
     * @param player .
     * @return the pawn.
     */
    public static ChessPiece pawnUpgrade(char type, char player) {
        if (player == '0') {
            return null;
        }
        boolean white = player == '1';

        if (Character.toUpperCase(type) == '2') {
            return new Bishop(white);
        }
        if (Character.toUpperCase(type) == 'R') {
            return new Rook(white);
        }
        if (Character.toUpperCase(type) == 'P') {
            return new Pawn(white);
        }
        if (Character.toUpperCase(type) == 'N') {
            return new Knight(white);
        }

        return new Queen(white);
    }
}
