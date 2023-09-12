package model.piece;

import controller.ChessController;
import model.board.Board;
import util.check.BishopTurnCheck;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */
public class Bishop extends ChessPiece {

    /**
     * @param isWhiteTurn .
     */
    public Bishop(boolean isWhiteTurn) {
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
        BishopTurnCheck bishopTurnCheck = new BishopTurnCheck();
        return bishopTurnCheck.validTurn(start, end, board, isWhiteTurn());
    }


    /**
     * @return print who moves.
     */
    @Override
    public String toString() {
        return super.isWhiteTurn() ? "wB" : "bB";
    }
}
