package model.piece;

import controller.ChessController;
import model.board.Board;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */
public class Knight extends ChessPiece {
    /**
     * @param isWhiteTurn .
     */
    public Knight(boolean isWhiteTurn) {
        super(isWhiteTurn);
    }

    /**
     * @param start .
     * @param end .
     * @param board .
     * @param chessController .
     * @return confirm this step.
     */
    @Override
    public boolean validTurn(int[] start, int[] end, Board board, ChessController chessController) {
        ChessPiece knight = board.getBoard()[end[0]][end[1]];
        int locationX = start[1] + 1;
        int locationY = start[0] - 2;

        if (knight != null) {
            if (isWhiteTurn() && knight.isWhiteTurn()) {
                return false;
            }
            if (!isWhiteTurn() && !knight.isWhiteTurn()) {
                return false;
            }
        }

        if (locationX == end[1] || locationX - 2 == end[1]) {
            return locationY == end[0] || locationY + 4 == end[0];
        }
        if (locationX + 1 == end[1] || locationX - 3 == end[1]) {
            return locationY + 1 == end[0] || locationY + 3 == end[0];
        }
        return false;
    }

    /**
     * @return print who moves.
     */
    @Override
    public String toString() {
        return super.isWhiteTurn() ? "wN" : "bN";
    }
}
