import java.util.Stack;

public class it_32_Longest_Valid_Parentheses {
    // 求最长有效括号长度
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 初始化栈底索引
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i); // '('索引入栈
            } else {
                stack.pop(); // 遇右括号栈顶弹出
                if (stack.empty()) {
                    stack.push(i); // 栈空，压入当前索引
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek()); // 计算当前合法长度
                }
            }
        }
        return maxLen;
    }
}
  

