package model.piece;

import controller.ChessController;
import model.board.Board;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */
public class Pawn extends ChessPiece {
    /**
     * @param isWhiteTurn .
     */
    public Pawn(boolean isWhiteTurn) {
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
        ChessPiece endDestination = board.getBoard()[end[0]][end[1]];
        int comparison = start[0] - 1;
        if (endDestination == null && end[1] == start[1]) {
            if (isWhiteTurn()) {
                if (getFirst() && (end[0] == comparison || end[0] == comparison - 1)) {
                    if (end[0] == comparison - 1 && board.getBoard()[end[0] + 1][end[1]] != null) {
                        return false;
                    }
                    setEnPassant(true);
                    setFirst(false);
                    return true;
                }
            } else {
                comparison = start[0] + 1;
                if (getFirst() && (end[0] == comparison || end[0] == comparison + 1)) {
                    if (end[0] == comparison + 1 && board.getBoard()[end[0] - 1][end[1]] != null) {
                        return false;
                    }
                    setEnPassant(true);
                    setFirst(false);
                    return true;
                }
            }
            return end[0] == comparison;
        } else {
            if (isWhiteTurn() && start[0] == 3 && Math.abs(start[1] - end[1]) == 1 && start[0] - end[0] == 1) {
                if (board.getBoard()[end[0]][end[1]] == null) {
                    ChessPiece temp = board.getBoard()[end[1] + 1][end[1]];
                    if (temp.getEnPassant() && !temp.isWhiteTurn()) {
                        board.getBoard()[end[0] + 1][end[1]].setIsDead(true);
                        board.getBoard()[end[0] + 1][end[1]] = null;
                        return true;
                    }
                }
            } else if (!isWhiteTurn() && start[0] == 4 && Math.abs(start[1] - end[1]) == 1 && end[0] - start[0] == 1) {
                if (board.getBoard()[end[0]][end[1]] == null) {
                    ChessPiece temp = board.getBoard()[end[1] - 1][end[1]];
                    if (temp.getEnPassant() && temp.isWhiteTurn()) {
                        board.getBoard()[end[0] - 1][end[1]].setIsDead(true);
                        board.getBoard()[end[0] - 1][end[1]] = null;
                        return true;
                    }
                }
            }
            if (board.getBoard()[end[0]][end[1]] == null) {
                return false;
            }
            if (isWhiteTurn()) {
                assert endDestination != null;
                if (endDestination.isWhiteTurn()) {
                    return false;
                }
            }
            if (!isWhiteTurn()) {
                assert endDestination != null;
                if (!endDestination.isWhiteTurn()) {
                    return false;
                }
            }
            if ((start[1] + 1) == end[1] || (start[1] - 1) == end[1]) {
                return (isWhiteTurn() && end[0] == (start[0] - 1)) || (!isWhiteTurn() && end[0] == (start[0] + 1));
            }
        }

        return false;
    }

    /**
     * @return print who moves.
     */
    @Override
    public String toString() {
        return super.isWhiteTurn() ? "wp" : "bp";
    }
}
