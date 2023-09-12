package chess;

import controller.ChessController;
import view.ChessView;

import java.util.Scanner;

/**
 * @author Ji Wu
 * @author Haitong Liu
 */
public class Chess {

    /**
     * This method demonstrates the whole program Chess.
     * @param args Unused.
     */

    public static void main(String[] args) {
        ChessController controller = new ChessController();
        ChessView view = new ChessView(controller);

        Scanner scanner = new Scanner(System.in);

        while (!controller.isOver()) {
            view.printBoard();
            view.printTurn();
            String input = scanner.nextLine();
            while (!controller.isCorrectInput(input)) {
                System.out.print("Input error. Please input again: ");
                input = scanner.nextLine();
            }
            view.analysisInput(input);
        }
    }
}
