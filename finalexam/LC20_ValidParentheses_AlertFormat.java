import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // 用 HashMap 初始化括號配對
        HashMap<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (pairs.containsValue(c)) {
                stack.push(c); // 左括號進棧
            } else if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) {
                    System.out.println("false");
                    return;
                }
            }
        }
        System.out.println(stack.isEmpty() ? "true" : "false");
    }
}
