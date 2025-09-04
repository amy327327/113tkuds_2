public class it_36_Valid_Sudoku {
    // 判斷9x9數獨盤是否合法
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] columns = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if(board[i][j] == '.') continue;
                int num = board[i][j] - '1';
                int k = (i/3)*3 + j/3; // 箱子索引
                if(rows[i][num] || columns[j][num] || boxes[k][num]) return false;
                rows[i][num] = true;
                columns[j][num] = true;
                boxes[k][num] = true;
            }
        }
        return true;
    }
}

