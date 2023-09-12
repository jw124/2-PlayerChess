package controller;

import model.board.Board;
import model.piece.ChessPiece;
import model.piece.King;

import java.util.ArrayList;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */

public class ChessController {
    private final Board board;
    private boolean isOver = false;
    private boolean checking = false;
    private boolean isWhiteTurn = true;



    public ChessController() {
        this.board = new Board();
    }



    /**
     * @param starting .
     * @param ending .
     * @param whiteTurn .
     * @return if the move can be successful or not.
     */
    public boolean move(int[] starting, int[] ending, boolean whiteTurn) {
        ChessPiece currentPiece = board.getBoard()[starting[0]][starting[1]];
        if (currentPiece == null) {
            return false;
        } else if ((currentPiece.isWhiteTurn() && whiteTurn) || (!currentPiece.isWhiteTurn() && !whiteTurn)) {
            if (currentPiece.validTurn(starting, ending, board, this)) {
                ChessPiece player2Piece = board.getBoard()[ending[0]][ending[1]];
                board.getBoard()[ending[0]][ending[1]] = currentPiece;
                board.getBoard()[starting[0]][starting[1]] = null;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        int[] tempStart = {i, j};
                        if (isCheck(tempStart, whiteTurn)) {
                            board.getBoard()[starting[0]][starting[1]] = currentPiece;
                            board.getBoard()[ending[0]][ending[1]] = player2Piece;
                            return false;
                        }
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    /**
     * @return isWhiteTurn.
     */
    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }


    /**
     * @param isWhiteTurn .
     */
    public void setWhiteTurn(boolean isWhiteTurn) {
        this.isWhiteTurn = isWhiteTurn;
    }


    /**
     * @return board.
     */
    public Board getBoard() {
        return board;
    }


    /**
     * @return isOver.
     */
    public boolean isOver() {
        return isOver;
    }


    /**
     * @param input .
     * @return judge the input is legal or not.
     */
    public boolean isCorrectInput(String input) {
        input = input.toLowerCase();
        if (input.length() == 0) {
            return false;
        }

        if ("draw".equals(input)) {
            return true;
        }
        if ("resign".equals(input)) {
            return true;
        }

        if (input.charAt(0) < 'a' || input.charAt(0) > 'h') {
            return false;
        }
        if (input.charAt(4) < '1' || input.charAt(4) > '8') {
            return false;
        }
        if (input.charAt(3) < 'a' || input.charAt(3) > 'h') {
            return false;
        }
        if (input.charAt(1) < '1' || input.charAt(1) > '8') {
            return false;
        }
        if (!Character.isAlphabetic(input.charAt(0))) {
            return false;
        }
        if (!Character.isAlphabetic(input.charAt(3))) {
            return false;
        }
        if (!Character.isDigit(input.charAt(1))) {
            return false;
        }
        return Character.isDigit(input.charAt(4));
    }



    public void gameOver() {
        isOver = true;
    }


    /**
     * @param starting .
     * @param player1 .
     * @return can check or not.
     */
    public boolean isCheck(int[] starting, boolean player1) {
        int[] ending = getCoordinatesOfKing(player1);
        ChessPiece sourcePiece = board.getBoard()[starting[0]][starting[1]];
        if (sourcePiece == null) {
            return false;
        }

        checking = true;
        if (sourcePiece.validTurn(starting, ending, board, this)) {
            checking = false;
            return true;
        }
        checking = false;
        return false;
    }


    /**
     * @param player1 .
     * @return king's place.
     */
    public int[] getCoordinatesOfKing(boolean player1) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece currentPiece = board.getBoard()[i][j];
                if (currentPiece != null) {
                    if (currentPiece instanceof King) {
                        int[] place = {i, j};
                        if (player1 && currentPiece.isWhiteTurn()) {
                            return place;
                        } else if (!player1 && !currentPiece.isWhiteTurn()) {
                            return place;
                        }
                    }
                }
            }
        }
        return null;
    }


    /**
     * @return checking.
     */
    public boolean checkStatus() {
        return checking;
    }


    /**
     * @param isWhiteTurn .
     * @return can checkmate or not.
     */
    public boolean isCheckMate(boolean isWhiteTurn) {
        int[] kingPos = getCoordinatesOfKing(isWhiteTurn);
        int[] tempPos = {kingPos[0], kingPos[1]};

        tempPos[1] = tempPos[1] - 1;
        if (kingsMove(kingPos, tempPos, isWhiteTurn)) {
            return false;
        }
        tempPos[0] = tempPos[0] - 1;
        if (kingsMove(kingPos, tempPos, isWhiteTurn)) {
            return false;
        }
        tempPos[1] = tempPos[1] + 1;
        if (kingsMove(kingPos, tempPos, isWhiteTurn)) {
            return false;
        }
        tempPos[1] = tempPos[1] + 1;
        if (kingsMove(kingPos, tempPos, isWhiteTurn)) {
            return false;
        }
        tempPos[0] = tempPos[0] + 1;
        if (kingsMove(kingPos, tempPos, isWhiteTurn)) {
            return false;
        }
        tempPos[0] = tempPos[0] + 1;
        if (kingsMove(kingPos, tempPos, isWhiteTurn)) {
            return false;
        }
        tempPos[1] = tempPos[1] - 1;
        if (kingsMove(kingPos, tempPos, isWhiteTurn)) {
            return false;
        }
        tempPos[1] = tempPos[1] - 1;
        if (kingsMove(kingPos, tempPos, isWhiteTurn)) {
            return false;
        }
        ArrayList<int[]> cantMove = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int[] tempStart = {i, j};
                ChessPiece dontMove = board.getBoard()[i][j];
                if (dontMove != null && dontMove.validTurn(tempStart, kingPos, board, this)) cantMove.add(tempStart);
            }
        }

        if (cantMove.size() > 1) {
            return true;
        }
        if (!cantMove.isEmpty()) {
            int[] danger = cantMove.get(0);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    int[] saving = {i, j};
                    ChessPiece save = board.getBoard()[i][j];
                    if (save != null && save.validTurn(saving, danger, board, this)) return false;
                }
            }
        }
        return true;
    }


    /**
     * @param starting .
     * @param ending .
     * @param player1 .
     * @return king can escape or not.
     */
    public boolean kingsMove(int[] starting, int[] ending, boolean player1) {
        if (ending[0] < 0 || ending[0] > 7 || ending[1] < 0 || ending[1] > 7) {
            return false;
        }
        ChessPiece otherPiece = board.getBoard()[ending[0]][ending[1]];
        ChessPiece currentPiece = board.getBoard()[starting[0]][starting[1]];
        if (otherPiece != null) {
            return false;
        }
        checking = true;
        if (currentPiece.validTurn(starting, ending, board, this)) {
            checking = false;
            board.getBoard()[ending[0]][ending[1]] = currentPiece;
            board.getBoard()[starting[0]][starting[1]] = null;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    int[] tempStart = {i, j};
                    if (isCheck(tempStart, player1)) {
                        board.getBoard()[ending[0]][ending[1]] = null;
                        board.getBoard()[starting[0]][starting[1]] = currentPiece;
                        return false;
                    }
                }
            }
            checking = false;
            board.getBoard()[ending[0]][ending[1]] = null;
            board.getBoard()[starting[0]][starting[1]] = currentPiece;
            return true;
        } else {
            checking = false;
            return false;
        }

    }
}
