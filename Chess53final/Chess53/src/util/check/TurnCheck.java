package util.check;

import model.board.Board;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */
public abstract class TurnCheck {
    /**
     * @param start start position。
     * @param end endpostion.
     * @param board board.
     * @param isWhiteTurn see it is white turn or not.
     * @return work as the parent method.
     */
    public abstract boolean validTurn(int[] start, int[] end, Board board, boolean isWhiteTurn);
}
