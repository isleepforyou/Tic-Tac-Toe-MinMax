import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class Board
{
    private Mark[][] board;

    // Ne pas changer la signature de cette méthode
    public Board() {
        board = new Mark[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
    }

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){
        int r = m.getRow();
        int c = m.getCol();
        if (r >= 0 && r < 3 && c >= 0 && c < 3 && board[r][c] == Mark.EMPTY) {
            board[r][c] = mark;
        }

    }


    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark){
        Mark winner = checkWinner();
        if (winner == mark) {
            return 100;
        } else if (winner != Mark.EMPTY && winner != mark) {
            return -100;
        } else {
            return 0;
        }
    }


    public boolean isTerminal() {
        if (checkWinner() != Mark.EMPTY) {
            return true;
        }
        // Vérifie s'il reste des coups possibles
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Mark.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


    public ArrayList<Move> getAvailableMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Mark.EMPTY) {
                    moves.add(new Move(i, j));
                }
            }
        }
        return moves;
    }


    private Mark checkWinner() {
        // Vérification des lignes
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != Mark.EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }
        // Vérification des colonnes
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != Mark.EMPTY && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return board[0][j];
            }
        }
        // Vérification des diagonales
        if (board[0][0] != Mark.EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != Mark.EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        return Mark.EMPTY;
    }


    public Board copy() {
        Board newBoard = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newBoard.board[i][j] = this.board[i][j];
            }
        }

        return newBoard;
    }

    public Mark[][] getGrid() {
        return board;
    }
}
