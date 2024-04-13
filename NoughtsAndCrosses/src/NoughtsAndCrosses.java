import java.util.Scanner;

public class NoughtsAndCrosses {
    public char[][] board = new char[3][3];

    public NoughtsAndCrosses() {
        clearBoard(board);
    }

    public void clearBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' '; // Initialize all elements to space
            }
        }
    }    

    public void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            if (i < 2) System.out.println("\n ---|---|---");
        }
        System.out.println("\n  1   2   3");
    }

    public boolean canMakeMove(char[][] board, int x, int y) {
        return board[x][y] == ' ';
    }

    public void makeMove(char[][] board, char player, int x, int y) {
        if (canMakeMove(board, x, y)) {
            board[x][y] = player;
        }
    }

    public boolean isBoardFull(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public char winner(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0];
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i];
        }
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0];
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2];

        return ' ';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NoughtsAndCrosses game = new NoughtsAndCrosses();
        game.printBoard(game.board);
        char currentPlayer = 'X';

        while (!game.isBoardFull(game.board)) {
            System.out.println("Enter move for " + currentPlayer + ":");
            String move = scanner.nextLine();
            int x = move.charAt(0) - 'A';
            int y = move.charAt(1) - '1';

            if (game.canMakeMove(game.board, x, y)) {
                game.makeMove(game.board, currentPlayer, x, y);
                game.printBoard(game.board);
                
                if (game.winner(game.board) != ' ') {
                    System.out.println(game.winner(game.board) + " Wins!");
                    break;
                }
                
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Invalid move, try again.");
            }
        }

        if (game.winner(game.board) == ' ') {
            System.out.println("The board is full! It's a draw.");
        }
        
        scanner.close();
    }
}
