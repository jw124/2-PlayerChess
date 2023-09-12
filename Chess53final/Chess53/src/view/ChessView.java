package view;

import controller.ChessController;
import model.piece.ChessPiece;
import model.piece.Pawn;
import util.ChessUtil;

import java.util.Scanner;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */

public class ChessView {
    private final ChessController chessController;
    private boolean draw = false;
    /**
     * @param chessController .
     */
    public ChessView(ChessController chessController) {
        this.chessController = chessController;
    }

    /**
     * @param input .
     */
    public void analysisInput(String input) {
        if ("resign".equals(input)) {
            System.out.println(chessController.isWhiteTurn() ? "Black wins" : "White wins");
            chessController.gameOver();
            return;
        } else if (input.contains("draw")) {
            if (draw) {
                System.out.println("Draw.");
                System.exit(0);
            } else {
                draw = true;
            }
        } else {
            draw = false;
        }

        String start = input.substring(0, 2);
        String end = input.substring(3);

        int[] starting = ChessUtil.getIndex(start);
        int[] ending = ChessUtil.getIndex(end);

        while (!chessController.move(starting, ending, chessController.isWhiteTurn())) {
            System.out.print("Illegal move, try again: ");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            while (!chessController.isCorrectInput(input)) {
                System.out.print("Input error. Please input again: ");
                input = scanner.nextLine();
            }

            if ("resign".equals(input)) {
                System.out.println(chessController.isWhiteTurn() ? "Black wins" : "White wins");
                chessController.gameOver();
                return;
            } else if (input.contains("draw")) {
                if (draw) {
                    System.out.println("Draw.");
                    System.exit(0);
                } else {
                    draw = true;
                }
            } else {
                draw = false;
            }

            start = input.substring(0, 2);
            end = input.substring(3);

            starting = ChessUtil.getIndex(start);
            ending = ChessUtil.getIndex(end);
        }

        ChessPiece chessPieceDisplaced = chessController.getBoard().getBoard()[ending[0]][ending[1]];

        if (chessPieceDisplaced instanceof Pawn) {
            char player = '0';
            char type = '0';
            if (input.length() == 7) {
                type = input.charAt(6);
            }
            if (ending[0] == 0 && chessPieceDisplaced.isWhiteTurn()) {
                player = '1';
            } else if (ending[0] == 7 && !chessPieceDisplaced.isWhiteTurn()) {
                player = '2';
            }

            ChessPiece promoted = ChessUtil.pawnUpgrade(type, player);
            if (promoted != null) {
                chessController.getBoard().getBoard()[ending[0]][ending[1]] = promoted;
            }
        }

        if (chessController.isCheck(ending, false) && chessController.isWhiteTurn()) {
            System.out.println("Check");
            if (chessController.isCheckMate(false)) {
                printBoard();
                System.out.println("Checkmate");
                System.out.print("White wins.");
                chessController.gameOver();
                return;
            }
        } else if (chessController.isCheck(ending, true) && !chessController.isWhiteTurn()) {
            System.out.println("Check");
            if (chessController.isCheckMate(true)) {
                printBoard();
                System.out.println("Checkmate");
                System.out.print("Black wins");
                chessController.gameOver();
                return;
            }
        }
        chessController.setWhiteTurn(!chessController.isWhiteTurn());
    }

    public void printBoard() {
        System.out.println(chessController.getBoard());
    }

    public void printTurn() {
        System.out.print(chessController.isWhiteTurn() ? "White's move: " : "Black's move: ");
    }
}
