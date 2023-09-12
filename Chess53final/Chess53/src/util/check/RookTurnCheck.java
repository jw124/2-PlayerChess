package util.check;

import model.board.Board;
import model.piece.ChessPiece;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */
public class RookTurnCheck extends TurnCheck {
    /**
     * @param start .
     * @param end .
     * @param board .
     * @param isWhiteTurn .
     * @return confirm this step.
     */
    @Override
    public boolean validTurn(int[] start, int[] end, Board board, boolean isWhiteTurn) {
        boolean right;
        boolean left;
        boolean up;
        boolean lateral;
        boolean down;

        if (start[1] == end[1] && start[0] == end[0]) {
            return false;
        }
        if (start[1] != end[1] && start[0] != end[0]) {
            return false;
        }

        ChessPiece destPiece = board.getBoard()[end[0]][end[1]];
        lateral = start[0] != end[0];
        if (start[1] > end[1]) {
            right = false;
            left = true;
        } else {
            right = true;
            left = false;
        }
        if (start[0] > end[0]) {
            up = true;
            down = false;
        } else {
            up = false;
            down = true;
        }


        if (destPiece == null) {
            if (lateral) {
                if (!down) {
                    for (int i = start[0] - 1; i > end[0]; i--) {
                        if (board.getBoard()[i][end[1]] != null) {
                            return false;
                        }
                    }

                    /*
                     * look downward laterally to see if any pieces in the way
                     */
                } else {
                    for (int i = start[0] + 1; i < end[0]; i++) {
                        if (board.getBoard()[i][end[1]] != null) {
                            return false;
                        }
                    }
                }
            } else {
                if (!left) {
                    for (int i = start[1] + 1; i < end[1]; i++) {
                        if (board.getBoard()[end[0]][i] != null) {
                            return false;
                        }
                    }
                } else {  //looking left
                    for (int i = start[1] - 1; i > end[1]; i--) {
                        if (board.getBoard()[end[0]][i] != null) {
                            return false;
                        }
                    }
                }
            }
        } else {
            if (isWhiteTurn && destPiece.isWhiteTurn()) {
                return false;
            }
            if (!isWhiteTurn && !destPiece.isWhiteTurn()) {
                return false;
            }
            if (lateral) {
                if (up) {
                    for (int i = start[0] - 1; i > end[0]; i--) {
                        if (board.getBoard()[i][end[1]] != null) {
                            return false;
                        }
                    }

                } else {
                    for (int i = start[0] + 1; i < end[0]; i++) {
                        if (board.getBoard()[i][end[1]] != null) {
                            return false;
                        }
                    }
                }
            } else {
                if (right) {
                    for (int i = start[1] + 1; i < end[1]; i++) {
                        if (board.getBoard()[end[0]][i] != null) {
                            return false;
                        }
                    }
                } else {
                    for (int i = start[1] - 1; i > end[1]; i--) {
                        if (board.getBoard()[end[0]][i] != null) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
