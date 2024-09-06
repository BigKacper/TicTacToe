import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char player = 'O';
        char cpu = 'X';
        char[][] logicBoard = {{' ',' ',' '},
                               {' ',' ',' '},
                               {' ',' ',' '}};

        char[][] board = {{logicBoard[0][0],'|',logicBoard[0][1],'|', logicBoard[0][2]},
                          {'-','+','-','+','-',},
                          {logicBoard[1][0],'|',logicBoard[1][1],'|', logicBoard[1][2]},
                          {'-','+','-','+','-',},
                          {logicBoard[2][0],'|',logicBoard[2][1],'|', logicBoard[2][2]}};

        printGameBoard(board);
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter the free spot (1-9):");
            int playerPos = scan.nextInt();
            placePiece(logicBoard, playerPos, "player");
            updateBoard(logicBoard, board);
            if(didYouWin(logicBoard, player)) {
                printGameBoard(board);
                System.out.println("Congratulations, you win.");
                break;
            }
            if(checkDraw(logicBoard)) {
                printGameBoard(board);
                System.out.println("It's a draw!");
                break;
            }
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            placePiece(logicBoard, cpuPos, "cpu");
            updateBoard(logicBoard, board);
            if(didYouWin(logicBoard, cpu)) {
                printGameBoard(board);
                System.out.println("You lose...");
                break;
            }
            printGameBoard(board);
        }
    }

    public static void printGameBoard(char[][] board){
        for(char[] row : board){
            for(char c: row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static boolean checkDraw(char[][] board){
        for(char[] row : board){
            for(char c: row) {
                if(c==' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void placePiece(char[][] board, int pos, String user) {
        char symbol = ' ';
        if(user.equals("player")) {
            symbol = 'O';
        } else if(user.equals("cpu")) {
            symbol = 'X';
        }

        boolean validMove = false;

        while(!validMove) {
            switch(pos){
                case 1:
                    if(board[0][0] == ' ') {
                        board[0][0] = symbol;
                        validMove = true;
                    }
                    break;
                case 2:
                    if(board[0][1] == ' ') {
                        board[0][1] = symbol;
                        validMove = true;
                    }
                    break;
                case 3:
                    if(board[0][2] == ' ') {
                        board[0][2] = symbol;
                        validMove = true;
                    }
                    break;
                case 4:
                    if(board[1][0] == ' ') {
                        board[1][0] = symbol;
                        validMove = true;
                    }
                    break;
                case 5:
                    if(board[1][1] == ' ') {
                        board[1][1] = symbol;
                        validMove = true;
                    }
                    break;
                case 6:
                    if(board[1][2] == ' ') {
                        board[1][2] = symbol;
                        validMove = true;
                    }
                    break;
                case 7:
                    if(board[2][0] == ' ') {
                        board[2][0] = symbol;
                        validMove = true;
                    }
                    break;
                case 8:
                    if(board[2][1] == ' ') {
                        board[2][1] = symbol;
                        validMove = true;
                    }
                    break;
                case 9:
                    if(board[2][2] == ' ') {
                        board[2][2] = symbol;
                        validMove = true;
                    }
                    break;
                default:
                    break;
            }


            if (!validMove && user.equals("player")) {
                Scanner scan = new Scanner(System.in);
                System.out.println("That spot is already taken, please choose another: ");
                pos = scan.nextInt();
            }


            if (!validMove && user.equals("cpu")) {
                Random rand = new Random();
                pos = rand.nextInt(9) + 1;
            }
        }
    }

    public static void updateBoard(char[][] logicBoard, char[][] board) {
        //update the graphical board to reflect the underlying logic
        board[0][0] = logicBoard[0][0];
        board[0][2] = logicBoard[0][1];
        board[0][4] = logicBoard[0][2];

        board[2][0] = logicBoard[1][0];
        board[2][2] = logicBoard[1][1];
        board[2][4] = logicBoard[1][2];

        board[4][0] = logicBoard[2][0];
        board[4][2] = logicBoard[2][1];
        board[4][4] = logicBoard[2][2];
    }

    public static boolean didYouWin(char[][] board, char player){
        //check rows for a winning combination
        for(int row = 0; row <board.length; row++) {
            if(board[row][0] == player  && board[row][1] == player && board[row][2] == player){
                return true;
        }
    }
        //check columns for a winning combination
        for(int col=0; col<board[0].length; col++) {
        if(board[0][col] == player  && board[1][col] == player && board[2][col] == player){
            return true;
        }
        }
        //check diagonally for a winning combination
        if(board[0][0] == player && board[1][1] == player && board[2][2] == player){
            return true;
        }
        if(board[2][0] == player && board[1][1] == player && board[0][2] == player){
            return true;
        }
        return false;
    }
}

