public class DataLab {
    public static void main(String[] args) throws Exception {
        Board game1 = new Board();
        game1.printBoard();
        game1.putMark(1, 1);
        game1.printBoard();
        game1.putMark(1, 2);
        game1.printBoard();
    }
}

class Board {
    public static final int X = 1, O = -1, EMPTY = 0;
    private int[][] board = new int[3][3];
    private int player;

    Board() {
        clearBoard();
    }

    public void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = EMPTY;
            }
        }
        player = X;
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1)
                    System.out.print("X  ");
                else if (board[i][j] == -1)
                    System.out.print("O  ");
                else
                    System.out.print("-  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isWin(int a) {
        if (board[0][0] + board[0][1] + board[0][2] == 3 * a) {
            return true;
        } else if (board[1][0] + board[1][1] + board[1][2] == 3 * a) {
            return true;
        } else if (board[2][0] + board[2][1] + board[2][2] == 3 * a) {
            return true;
        } else if (board[0][0] + board[1][0] + board[2][0] == 3 * a) {
            return true;
        } else if (board[0][1] + board[1][1] + board[2][1] == 3 * a) {
            return true;
        } else if (board[0][2] + board[1][2] + board[2][2] == 3 * a) {
            return true;
        } else if (board[0][0] + board[1][1] + board[2][2] == 3 * a) {
            return true;
        } else if (board[2][0] + board[1][1] + board[0][2] == 3 * a) {
            return true;
        } else
            return false;

    }

    public void putMark(int i, int j) throws IllegalAccessException {
        if (i < 0 || i > 2 || j < 0 || j > 2)
            throw new IllegalArgumentException("Invalid board position!");
        if (board[i][j] != EMPTY)
            throw new IllegalArgumentException("Board position occupied!");
        board[i][j] = player;
        player = -player;
    }

    public int winner() {
        if (isWin(X))
            return (X);
        else if (isWin(O))
            return (O);
        else
            return (0);
    }
}