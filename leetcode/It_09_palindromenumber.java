// It_09_palindromenumber.java

public class It_09_palindromenumber {

    // 方法：判斷整數是否為迴文
    public static boolean isPalindrome(int x) {
        // 負數或最後一位為0（但整數不為0）一定不是迴文
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversedHalf = 0;
        while (x > reversedHalf) {
            int pop = x % 10;
            reversedHalf = reversedHalf * 10 + pop;
            x /= 10;
        }

        // 如果長度為偶數，x == reversedHalf
        // 如果長度為奇數，reversedHalf / 10 去掉中間數字
        return x == reversedHalf || x == reversedHalf / 10;
    }

    public static void main(String[] args) {
        // 測試範例
        int[] tests = {121, -121, 10, 0, 12321, 123321};

        for (int test : tests) {
            System.out.println("Input: " + test + " -> Output: " + isPalindrome(test));
        }
    }
}

