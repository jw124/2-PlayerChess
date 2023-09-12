package model.piece;

import controller.ChessController;
import model.board.Board;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */
public class King extends ChessPiece {
    /**
     * @param isWhiteTurn .
     */
    public King(boolean isWhiteTurn) {
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
        ChessPiece destPiece = board.getBoard()[end[0]][end[1]];
        int m = start[1] + 2;
        int n = start[1] + 1;
        int o = start[1] - 2;
        int p = start[1] - 1;
        if (getFirst() && start[0] == end[0]) {
            if (end[1] == m) {
                for (int i = n; i < 7; i++) {
                    ChessPiece tempPiece = board.getBoard()[end[0]][i];
                    if (tempPiece != null) {
                        return false;
                    }
                }
                ChessPiece rookPiece = board.getBoard()[end[0]][7];
                if (!rookPiece.getFirst()) {
                    return false;
                }

                board.getBoard()[end[0]][end[1] - 1] = rookPiece;
                board.getBoard()[end[0]][7] = null;
                setFirst(false);
                rookPiece.setFirst(false);
                return true;
            } else if (end[1] == o) {
                for (int i = p; i > 0; i--) {
                    ChessPiece tempPiece = board.getBoard()[end[0]][i];
                    if (tempPiece != null) {
                        return false;
                    }
                }
                ChessPiece rookPiece = board.getBoard()[end[0]][0];
                if (!rookPiece.getFirst()) {
                    return false;
                }

                board.getBoard()[end[0]][end[1] + 1] = rookPiece;
                board.getBoard()[end[0]][0] = null;
                setFirst(false);
                rookPiece.setFirst(false);
                return true;
            }
        }

        if (start[1] == end[1] && start[0] == end[0]) {
            return false;
        }


        if (destPiece != null) {
            if (isWhiteTurn() && destPiece.isWhiteTurn()) {
                return false;
            }
            if (!isWhiteTurn() && !destPiece.isWhiteTurn()) {
                return false;
            }
        }
        if (Math.abs(start[0] - end[0]) > 1) {
            return false;
        }
        if (Math.abs(start[1] - end[1]) > 1) {
            return false;
        }

        if (start[1] == end[1]) {
            if (start[0] != end[0] + 1 && start[0] != end[0] - 1) {
                return false;
            }

        } else if (start[0] == end[0]) {
            if (start[1] != end[1] + 1 && start[1] != end[1] - 1) {
                return false;
            }
        } else if (start[0] == end[0] + 1 || start[0] == end[0] - 1) {
            if (start[1] != end[1] + 1 && start[1] != end[1] - 1) {
                return false;
            }
        }

        if (!chessController.checkStatus()) {
            setFirst(false);
        }
        return true;
    }

    /**
     * @return print who moves.
     */
    @Override
    public String toString() {
        return super.isWhiteTurn() ? "wK" : "bK";
    }
}
