// It_07_reverseInteger.java

public class It_07_reverseInteger {

    // 方法：反轉整數
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            // 檢查溢位（32位有符號整數）
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;

            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        // 測試範例
        int[] tests = {123, -123, 120, 0, 1534236469};

        for (int test : tests) {
            System.out.println("Input: " + test + " -> Output: " + reverse(test));
        }
    }
}
