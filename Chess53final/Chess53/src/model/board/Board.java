package model.board;

import model.piece.*;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */

public class Board {
    private final ChessPiece[][] board;

    public Board() {
        board = new ChessPiece[8][8];

        board[0][0] = new Rook(false);
        board[0][1] = new Knight(false);
        board[0][2] = new Bishop(false);
        board[0][3] = new Queen(false);
        board[0][4] = new King(false);
        board[0][5] = new Bishop(false);
        board[0][6] = new Knight(false);
        board[0][7] = new Rook(false);

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(false);
        }
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(true);
        }

        board[7][0] = new Rook(true);
        board[7][1] = new Knight(true);
        board[7][2] = new Bishop(true);
        board[7][3] = new Queen(true);
        board[7][4] = new King(true);
        board[7][5] = new Bishop(true);
        board[7][6] = new Knight(true);
        board[7][7] = new Rook(true);
    }


    /**
     * @return board.
     */
    public ChessPiece[][] getBoard() {
        return board;
    }


    /**
     * @return output the situation of the board.
     */
    @Override
    public String toString() {
        boolean printBlack = false;
        StringBuilder str = new StringBuilder("\n");

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    str.append(board[i][j].toString()).append(" ");
                } else {
                    if (printBlack) {
                        str.append("##" + " ");
                    } else {
                        str.append("  " + " ");
                    }
                }
                printBlack = !printBlack;
            }
            str.append(8 - i).append("\n");
            printBlack = !printBlack;
        }

        str.append(" a  b  c  d  e  f  g  h\n");

        return str.toString();
    }
}
