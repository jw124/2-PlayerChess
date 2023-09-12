package util.check;

import model.board.Board;
import model.piece.ChessPiece;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */
public class BishopTurnCheck extends TurnCheck {
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
        boolean up;
        boolean left;
        boolean down;
        ChessPiece bishopDest = board.getBoard()[end[0]][end[1]];

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

        if (start[1] == end[1] || start[0] == end[0]) {
            return false;
        }

        if (Math.abs(start[1] - end[1]) != Math.abs(start[0] - end[0])) {
            return false;
        }

        if (bishopDest == null) {
            if (!down) {
                if (!left) {
                    int tempY = start[0] - 1;
                    int x = start[1] + 1;
                    for (int tempX = x; tempX < end[1]; tempX++) {
                        if (board.getBoard()[tempY][tempX] != null) {
                            return false;
                        }
                        tempY--;
                    }
                } else {
                    int tempY = start[0] - 1;
                    int x = start[1] - 1;
                    for (int tempX = x; tempX > end[1]; tempX--) {
                        if (board.getBoard()[tempY][tempX] != null) {
                            return false;
                        }
                        tempY--;
                    }
                }
            } else {
                if (right) {
                    int tempY = start[0] + 1;
                    int X = start[1] + 1;
                    for (int tempX = X; tempX < end[1]; tempX++) {
                        if (board.getBoard()[tempY][tempX] != null) {
                            return false;
                        }
                        tempY++;
                    }
                } else {
                    int tempY = start[0] + 1;
                    int t = start[1] - 1;
                    for (int tempX = t; tempX > end[1]; tempX--) {
                        if (board.getBoard()[tempY][tempX] != null) {
                            return false;
                        }
                        tempY++;
                    }
                }
            }

            //	attacking
        } else {
            if (isWhiteTurn && bishopDest.isWhiteTurn()) {
                return false;
            }
            if (!isWhiteTurn && !bishopDest.isWhiteTurn()) {
                return false;
            }
            if (up) {
                if (!left) {
                    int tempY = start[0] - 1;
                    int t = start[1] + 1;
                    for (int tempX = t; tempX < end[1]; tempX++) {
                        if (board.getBoard()[tempY][tempX] != null) {
                            return false;
                        }
                        tempY--;
                    }
                } else {
                    int tempY = start[0] - 1;
                    int tx = start[1] - 1;
                    for (int tempX = tx; tempX > end[1]; tempX--) {
                        if (board.getBoard()[tempY][tempX] != null) {
                            return false;
                        }

                    }
                }
            } else {
                if (right) {
                    int tempY = start[0] + 1;
                    int ty = start[1] + 1;
                    for (int tempX = ty; tempX < end[1]; tempX++) {
                        if (board.getBoard()[tempY][tempX] != null) {
                            return false;
                        }
                        tempY++;
                    }
                } else {
                    int tempY = start[0] + 1;
                    int ty = start[1] - 1;
                    for (int tempX = ty; tempX > end[1]; tempX--) {
                        if (board.getBoard()[tempY][tempX] != null) {
                            return false;
                        }
                        tempY++;
                    }
                }
            }
        }
        return true;
    }
}
