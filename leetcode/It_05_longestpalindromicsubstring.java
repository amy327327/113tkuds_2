// It_05_longestpalindromicsubstring.java

public class It_05_longestpalindromicsubstring {

    // 方法：找最長回文子字串
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);     // 奇數長度回文
            int len2 = expandAroundCenter(s, i, i + 1); // 偶數長度回文
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    // 輔助方法：從中心擴展回文
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        // 測試範例
        String[] tests = {"babad", "cbbd", "a", "ac"};

        for (String test : tests) {
            System.out.println("Input: " + test + " -> Output: " + longestPalindrome(test));
        }
    }
}
