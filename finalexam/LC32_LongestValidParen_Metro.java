import java.util.Scanner;
import java.util.Stack;
public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 基準索引

        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i); // 進站，索引入棧
            } else {
                stack.pop(); // 出站，彈出配對 '(' 索引
                if (stack.isEmpty()) {
                    // 無對應進站，當前索引為新基準
                    stack.push(i);
                } else {
                    // 計算合法子串長度更新最大值
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        System.out.println(maxLen);
    }
}
// Time: O(n); Space: O(n)

