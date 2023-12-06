import java.util.Scanner;

public class CrossesZeroes {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static int currentPlayerXWins = 0;
    private static int currentPlayerOWins = 0;

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();

        while (!isGameFinished()) {
            makeMove();
            displayBoard();
            switchPlayer();
        }

        displayResult();
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void makeMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] column[1-3]):");
        int row = scanner.nextInt() - 1;
        int col = scanner.nextInt() - 1;

        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer;
        } else {
            System.out.println("Invalid move! Try again.");
            makeMove();
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    private static boolean isGameFinished() {
        return isWin() || isBoardFull();
    }

    private static boolean isWin() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private static boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonals() {
        return (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]);
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void displayResult() {
        if (isWin()) {
            System.out.println("Player " + currentPlayer + " wins!");
            if (currentPlayer == 'X') {
                currentPlayerXWins++;
            } else {
                currentPlayerOWins++;
            }
        } else {
            System.out.println("It's a draw!");
        }
        System.out.println("Player X wins: " + currentPlayerXWins + "  Player O wins: " + currentPlayerOWins);
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}
