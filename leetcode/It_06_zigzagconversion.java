// It_06_zigzagconversion.java

public class It_06_zigzagconversion {

    // 方法：Z字形轉換
    public static String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);
            // 遇到第一行或最後一行時改變方向
            if (currentRow == 0 || currentRow == numRows - 1) goingDown = !goingDown;
            currentRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // 測試範例
        String s1 = "PAYPALISHIRING";
        int numRows1 = 3;
        System.out.println("Input: " + s1 + ", numRows = " + numRows1 + " -> Output: " + convert(s1, numRows1));

        String s2 = "PAYPALISHIRING";
        int numRows2 = 4;
        System.out.println("Input: " + s2 + ", numRows = " + numRows2 + " -> Output: " + convert(s2, numRows2));

        String s3 = "A";
        int numRows3 = 1;
        System.out.println("Input: " + s3 + ", numRows = " + numRows3 + " -> Output: " + convert(s3, numRows3));
    }
}

