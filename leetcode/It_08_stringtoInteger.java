// It_08_stringtoInteger.java

public class It_08_stringtoInteger {

    public static int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        int index = 0;
        int n = s.length();
        // 忽略前導空白
        while (index < n && s.charAt(index) == ' ') index++;

        if (index == n) return 0; // 全是空白

        // 處理符號
        int sign = 1;
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = (s.charAt(index) == '-') ? -1 : 1;
            index++;
        }

        int result = 0;
        // 讀取數字
        while (index < n && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';

            // 檢查是否溢位
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            index++;
        }

        return result * sign;
    }

    public static void main(String[] args) {
        // 測試範例
        String[] tests = {"42", "   -042", "1337c0d3", "0-1", "words and 987", "+0", "-91283472332"};

        for (String test : tests) {
            System.out.println("Input: \"" + test + "\" -> Output: " + myAtoi(test));
        }
    }
}

