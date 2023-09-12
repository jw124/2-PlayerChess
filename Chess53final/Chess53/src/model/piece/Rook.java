package model.piece;

import controller.ChessController;
import model.board.Board;
import util.check.RookTurnCheck;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */
public class Rook extends ChessPiece {
    /**
     * @param isWhiteTurn .
     */
    public Rook(boolean isWhiteTurn) {
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
        RookTurnCheck r = new RookTurnCheck();
        return r.validTurn(start, end, board, isWhiteTurn());
    }

    /**
     * @return print who moves.
     */
    @Override
    public String toString() {
        return super.isWhiteTurn() ? "wR" : "bR";
    }
}
