import java.util.ArrayList;
import java.util.List;

public class it_22_Generate_Parentheses {
    // 生成所有有效括號組合
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    // 回溯法，left和right是已加括號數量限制
    private void backtrack(List<String> ans, String current, int left, int right, int max) {
        if (current.length() == max * 2) {
            ans.add(current);
            return;
        }
        if (left < max) backtrack(ans, current + "(", left + 1, right, max);
        if (right < left) backtrack(ans, current + ")", left, right + 1, max);
    }
}

