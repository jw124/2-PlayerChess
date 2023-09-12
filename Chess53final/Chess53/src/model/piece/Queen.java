package model.piece;

import controller.ChessController;
import model.board.Board;
import util.check.BishopTurnCheck;
import util.check.RookTurnCheck;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */
public class Queen extends ChessPiece {
    /**
     * @param isWhiteTurn .
     */
    public Queen(boolean isWhiteTurn) {
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
        BishopTurnCheck b = new BishopTurnCheck();
        RookTurnCheck r = new RookTurnCheck();

        if (start[1] == end[1] && start[0] == end[0]) {
            return false;
        }

        if (b.validTurn(start, end, board, isWhiteTurn())) {
            return true;
        }
        return r.validTurn(start, end, board, isWhiteTurn());
    }

    /**
     * @return print who moves.
     */
    @Override
    public String toString() {
        return super.isWhiteTurn() ? "wQ" : "bQ";
    }
}
