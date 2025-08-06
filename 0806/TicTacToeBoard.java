

import java.util.Scanner;

public class TicTacToeBoard {
    private char[][] board;
    private static final int SIZE = 3;
    private static final char EMPTY = ' ';

    public TicTacToeBoard() {
        board = new char[SIZE][SIZE];
        initBoard();
    }

    // 初始化棋盤，全部設為空格
    public void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // 顯示棋盤
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // 放置棋子，player 為 'X' 或 'O'
    // row, col 從 0 開始
    // 回傳成功放置為 true，失敗（位置無效或已被佔用）為 false
    public boolean placePiece(int row, int col, char player) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            System.out.println("無效的位置，請輸入 0~2 之間的數字");
            return false;
        }
        if (board[row][col] != EMPTY) {
            System.out.println("該位置已被佔用");
            return false;
        }
        board[row][col] = player;
        return true;
    }

    // 判斷是否有人獲勝，獲勝回傳該玩家符號 'X' 或 'O'，無則回傳空格 ' '
    public char checkWinner() {
        // 檢查每一行
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] != EMPTY
                    && board[i][0] == board[i][1]
                    && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }

        // 檢查每一列
        for (int j = 0; j < SIZE; j++) {
            if (board[0][j] != EMPTY
                    && board[0][j] == board[1][j]
                    && board[1][j] == board[2][j]) {
                return board[0][j];
            }
        }

        // 檢查兩條對角線
        if (board[0][0] != EMPTY
                && board[0][0] == board[1][1]
                && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != EMPTY
                && board[0][2] == board[1][1]
                && board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return EMPTY; // 無人獲勝
    }

    // 判斷遊戲是否結束（有人獲勝或棋盤已滿平手）
    public boolean isGameOver() {
        if (checkWinner() != EMPTY) {
            return true;
        }
        // 檢查是否還有空位
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false; // 還有空位，遊戲未結束
                }
            }
        }
        return true; // 無空位且無人獲勝，為平手結束
    }

    // 主程式示範一個簡單雙人遊玩流程
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicTacToeBoard game = new TicTacToeBoard();

        char currentPlayer = 'X';
        while (!game.isGameOver()) {
            game.printBoard();
            System.out.println("玩家 " + currentPlayer + " 的回合");
            System.out.print("請輸入行 (0-2)：");
            int row = sc.nextInt();
            System.out.print("請輸入列 (0-2)：");
            int col = sc.nextInt();

            if (game.placePiece(row, col, currentPlayer)) {
                char winner = game.checkWinner();
                if (winner != EMPTY) {
                    game.printBoard();
                    System.out.println("玩家 " + winner + " 獲勝!");
                    break;
                } else if (game.isGameOver()) {
                    game.printBoard();
                    System.out.println("遊戲平手!");
                    break;
                }
                // 換玩家
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("請重新輸入位置。");
            }
        }
        sc.close();
    }
}
