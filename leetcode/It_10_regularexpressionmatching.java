// It_10_regularexpressionmatching.java

public class It_10_regularexpressionmatching {

    // 方法：正則表達式匹配
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp[i][j] 表示 s[0..i-1] 與 p[0..j-1] 是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // 空字串匹配空字串

        // 初始化 p 對應空字串的情況 (處理 '*' 可以匹配零次)
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    char prev = p.charAt(j - 2);
                    // '*' 表示零次
                    dp[i][j] = dp[i][j - 2];
                    // '*' 表示多次
                    if (prev == '.' || prev == sc) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        // 測試範例
        String[][] tests = {
            {"aa", "a"},
            {"aa", "a*"},
            {"ab", ".*"},
            {"mississippi", "mis*is*p*"},
            {"ab", ".*c"}
        };

        for (String[] test : tests) {
            System.out.println("Input: s=\"" + test[0] + "\", p=\"" + test[1] + "\" -> Output: " + isMatch(test[0], test[1]));
        }
    }
}
