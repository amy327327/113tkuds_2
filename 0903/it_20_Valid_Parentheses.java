import java.util.Stack;

public class it_20_Valid_Parentheses {
    // 判斷括號字串是否有效
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            // 遇到左括號入棧
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else {
                // 遇到右括號時判斷棧頂元素是否匹配
                if (stack.isEmpty() || stack.pop() != c) return false;
            }
        }
        // 棧空表示全部匹配成功
        return stack.isEmpty();
    }
}

