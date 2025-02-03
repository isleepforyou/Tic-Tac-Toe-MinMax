import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        CPUPlayer cpu = new CPUPlayer(Mark.O); // L'IA joue avec 'O'

        System.out.println("Bienvenue dans Tic-Tac-Toe !");
        printBoard(board);

        while (!board.isTerminal()) {
            // Tour du joueur humain (X)
            System.out.println("Votre tour ! Entrez ligne et colonne (0-2) :");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Move playerMove = new Move(row, col);

            if (!isValidMove(board, playerMove)) {
                System.out.println("Coup invalide ! Essayez encore.");
                continue;
            }

            board.play(playerMove, Mark.X);
            printBoard(board);

            if (board.isTerminal()) break;

            // Tour de l'IA (O)
            System.out.println("L'IA joue...");
            ArrayList<Move> bestMoves = cpu.getNextMoveMinMax(board);
            Move cpuMove = bestMoves.get(0); // Prend le premier meilleur coup
            board.play(cpuMove, Mark.O);
            printBoard(board);
        }

        // Affichage du résultat
        int result = board.evaluate(Mark.X);
        if (result == 100) {
            System.out.println("Bravo ! Vous avez gagné !");
        } else if (result == -100) {
            System.out.println("L'IA a gagné !");
        } else {
            System.out.println("Match nul !");
        }
    }

    // Vérifie si le coup est valide
    private static boolean isValidMove(Board board, Move move) {
        ArrayList<Move> availableMoves = board.getAvailableMoves();
        for (Move m : availableMoves) {
            if (m.getRow() == move.getRow() && m.getCol() == move.getCol()) {
                return true;
            }
        }
        return false;
    }

    // Affiche le plateau
    private static void printBoard(Board board) {
        Mark[][] grid = board.getGrid(); // Ajoute une méthode `getGrid()` à `Board`
        System.out.println("Plateau :");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

