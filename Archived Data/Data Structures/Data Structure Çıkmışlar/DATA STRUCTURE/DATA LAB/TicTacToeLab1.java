import java.util.*;

/* 
 * kullanıcı girişi eklenmeli +
 * kullanıcı sayıları girdikçe oyun devam etmeli +
 * oyun devam ettikçe kazanma koşulları kontrol edilmeli. +
 * 
 * */
public class TicTacToeLab1 {

    private int board[][];
    private static final int X = 1, O = -1, EMPTY = 0;
    private int currentPlayer;
    private boolean winner = true;

    private int winnerPosition[][][] = {
            { { 0, 0 }, { 0, 1 }, { 0, 2 } },
            { { 1, 0 }, { 1, 1 }, { 1, 2 } },
            { { 2, 0 }, { 2, 1 }, { 2, 2 } },
            { { 0, 0 }, { 1, 0 }, { 2, 0 } },
            { { 0, 1 }, { 1, 1 }, { 2, 1 } },
            { { 0, 2 }, { 1, 2 }, { 2, 2 } },
            { { 0, 0 }, { 1, 1 }, { 2, 2 } },
            { { 0, 2 }, { 1, 1 }, { 2, 0 } }
    };

    public TicTacToeLab1() {
        this.board = new int[3][3];
        this.currentPlayer = X;
    }

    public void clear() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = EMPTY;
                currentPlayer = X;

            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == X) {
                    System.out.print(" X");
                } else if (board[i][j] == O) {
                    System.out.print(" O");
                } else {
                    System.out.print(" -");
                }
                if (j < 2) {
                    System.out.print(" |");
                }
            }
            if (i < 2) {
                System.out.print("\n----------");
            }
        }
        /* FOREACH İLE YAZIMI 
         * for (int[] line : board) {
         * System.out.println();
         * 
         * for (int cell : line) {
         * if(cell == X){
         * System.out.print(" X");
         * }else if(cell == O){
         * System.out.print(" O");
         * }else{
         * System.out.print(" -");
         * }
         * }
         * }
         */
    }

    public void putMark(int x, int y) {
        if (x < 0 || y < 0 || x > 2 || y > 2) {
            throw new IllegalArgumentException("X and O must be between 0 and 2.");
        } else if (board[x][y] != EMPTY) {
            throw new IllegalArgumentException("The position is not empty.");
        }
        board[x][y] = currentPlayer;
        currentPlayer = -1 * currentPlayer;
        printBoard();
        System.out.println();

        if (isWinner(currentPlayer * -1)) {
            System.out.println("\nWinner : " + getWinner());
            winner = false;
        }else if(!(isPlayablePositionExit())){
            System.out.println("\nTie");
        }

    }

    public boolean getWinCheck() {
        return winner;
    }

    private boolean isWinner(int player) {
        for (int[][] positions : winnerPosition) {
            int sum = 0;
            for (int[] position : positions) {
                sum += board[position[0]][position[1]];
                if (sum == player * 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPlayablePositionExit() {
        for (int[] line : board) {
            for (int cell : line) {
                if (cell == EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getWinner() {
        if (isWinner(X)) {
            return "X";
        } else if (isWinner(O)) {
            return "O";
        }
            return "";
        
        
    }

    class deneme {
        public static void main(String[] args) {
            TicTacToeLab1 t = new TicTacToeLab1();
            Scanner scan = new Scanner(System.in);

            while (t.isPlayablePositionExit() && t.getWinCheck()) {

                System.out.print("\nPlease enter the line position : ");
                int line = scan.nextInt();
                System.out.print("Please enter the column position : ");
                int column = scan.nextInt();
                t.putMark(line, column);

            }
            scan.close();
        }

        /* 
         * DENEMELER
         * t.putMark(1, 0);
         * t.putMark(0, 0);
         * t.putMark(1,1);
         * t.putMark(0, 2);
         * t.putMark(1, 2);
         * System.out.println("is X Winner : "+ t.isWinner(1));
         * System.out.println("is O Winner : "+ t.isWinner(-1));
         * t.putMark(3, 5); X and O must be between 0 and 2.
         * t.clear();
         */

    }

}
