package model.piece;

import controller.ChessController;
import model.board.Board;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */
public abstract class ChessPiece {
    private boolean isWhiteTurn;
    private boolean first = true;
    private boolean enPassant = false;
    private boolean isDead = false;


    public ChessPiece(boolean isWhiteTurn) {
        this.isWhiteTurn = isWhiteTurn;
    }


    /**
     * @return isWhiteTurn.
     */
    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }


    /**
     * @return first.
     */
    public boolean getFirst() {
        return first;
    }


    /**
     * @param first .
     */
    public void setFirst(boolean first) {
        this.first = first;
    }


    /**
     * @return enPassant.
     */
    public boolean getEnPassant() {
        return enPassant;
    }


    /**
     * @param enPassant .
     */
    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }


    /**
     * @return isDead.
     */
    public boolean isDead() {
        return isDead;
    }


    /**
     * @param isDead .
     */
    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }


    /**
     * @param start .
     * @param end .
     * @param board .
     * @param chessController .
     * @return work as the parent method.
     */
    public abstract boolean validTurn(int[] start, int[] end, Board board, ChessController chessController);
}
