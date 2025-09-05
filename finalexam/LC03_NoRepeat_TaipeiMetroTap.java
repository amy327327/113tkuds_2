import java.util.Scanner;
import java.util.HashMap;
public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int ans = 0;
        // Map: key = 字元, value = 最後一次出現索引+1
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                // 若字符曾出現，更新左界到上次出現的位置+1（或維持不回退）
                start = Math.max(map.get(c), start);
            }
            ans = Math.max(ans, end - start + 1); // 當前無重複子字串長度
            map.put(c, end + 1); // 記錄字符最新索引+1
        }
        System.out.println(ans);
    }
}
// Time: O(n); Space: O(k)

