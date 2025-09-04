import java.util.ArrayList;
import java.util.List;

public class it_17_Letter_Combinations_of_a_Phone_Number {
    // 按照數字映射到字母的對照表
    String[] map = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    // 主函式，給定數字字串返回所有可能字母組合
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) return ans; // 空輸入返回空列表
        backtrack(ans, "", digits, 0); // 啟動回溯算法
        return ans;
    }

    // 回溯函式，生成字母組合
    private void backtrack(List<String> ans, String combination, String digits, int index) {
        if (index == digits.length()) { // 結束條件，加入字串組合
            ans.add(combination);
            return;
        }
        String letters = map[digits.charAt(index) - '0']; // 當前數字對應字母
        for (char c : letters.toCharArray()) {
            backtrack(ans, combination + c, digits, index + 1); // 遞迴生成下一字符
        }
    }
}


