import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer
{

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;

    // Le symbole du CPU (le joueur MAX) et celui de son adversaire.
    private Mark cpuMark;
    private Mark opponentMark;

    private static final int INF = 1000;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){
        this.cpuMark = cpu;
        this.opponentMark = (cpu == Mark.X ? Mark.O : Mark.X);
    }

    // Ne pas changer cette méthode
    public int  getNumOfExploredNodes(){
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board) {
        numExploredNodes = 0;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestValue = -INF;
        ArrayList<Move> moves = board.getAvailableMoves();

        for (Move move : moves) {
            Board newBoard = board.copy();
            newBoard.play(move, cpuMark);
            int moveValue = minimax(newBoard, false);

            // Si ce coup garantit une victoire immédiate le retourner directement
            if (moveValue == 100) {
                bestMoves.clear();
                bestMoves.add(move);
                return bestMoves;
            }

            if (moveValue > bestValue) {
                bestValue = moveValue;
                bestMoves.clear();
                bestMoves.add(move);
            } else if (moveValue == bestValue) {
                bestMoves.add(move);
            }
        }
        return bestMoves;
    }


    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes = 0;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestValue = -INF;
        ArrayList<Move> moves = board.getAvailableMoves();

        for (Move move : moves) {
            Board newBoard = board.copy();
            newBoard.play(move, cpuMark);
            int moveValue = alphaBeta(newBoard, false, -INF, INF);
            if (moveValue > bestValue) {
                bestValue = moveValue;
                bestMoves.clear();
                bestMoves.add(move);
            } else if (moveValue == bestValue) {
                bestMoves.add(move);
            }
        }
        return bestMoves;
    }


    // Implémentation récursive de l'algorithme MinMax.
    // isMaximizing vaut true lorsque c'est le tour du joueur MAX (cpuMark)
    private int minimax(Board board, boolean isMaximizing) {
        numExploredNodes++; // incrémente à chaque appel
        if (board.isTerminal()) {
            return board.evaluate(cpuMark);
        }
        ArrayList<Move> moves = board.getAvailableMoves();
        if (isMaximizing) {
            int best = -INF;
            for (Move move : moves) {
                Board newBoard = board.copy();
                newBoard.play(move, cpuMark);
                int value = minimax(newBoard, false);
                best = Math.max(best, value);
            }
            return best;
        } else {
            int best = INF;
            for (Move move : moves) {
                Board newBoard = board.copy();
                newBoard.play(move, opponentMark);
                int value = minimax(newBoard, true);
                best = Math.min(best, value);
            }
            return best;
        }
    }


    // Implémentation récursive de l'algorithme Alpha-Beta.
    private int alphaBeta(Board board, boolean isMaximizing, int alpha, int beta) {
        numExploredNodes++; // incrémente à chaque appel
        if (board.isTerminal()) {
            return board.evaluate(cpuMark);
        }
        ArrayList<Move> moves = board.getAvailableMoves();
        if (isMaximizing) {
            int best = -INF;
            for (Move move : moves) {
                Board newBoard = board.copy();
                newBoard.play(move, cpuMark);
                int value = alphaBeta(newBoard, false, alpha, beta);
                best = Math.max(best, value);
                alpha = Math.max(alpha, best);
                if (beta <= alpha) {  // coupure beta
                    break;
                }
            }
            return best;
        } else {
            int best = INF;
            for (Move move : moves) {
                Board newBoard = board.copy();
                newBoard.play(move, opponentMark);
                int value = alphaBeta(newBoard, true, alpha, beta);
                best = Math.min(best, value);
                beta = Math.min(beta, best);
                if (beta <= alpha) {  // coupure alpha
                    break;
                }
            }
            return best;
        }
    }

}
