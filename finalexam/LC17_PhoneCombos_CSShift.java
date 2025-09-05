import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class LC17_PhoneCombos_CSShift {
    static String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    static List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine();
        if(digits.length() == 0) return; // 空字串無輸出
        
        backtrack(digits, 0, new StringBuilder());
        for(String s : res)
            System.out.println(s);
    }

    static void backtrack(String digits, int index, StringBuilder sb){
        if(index == digits.length()){
            res.add(sb.toString());
            return;
        }
        int digit = digits.charAt(index) - '2';
        String letters = map[digit];
        for(int i=0; i<letters.length(); i++){
            sb.append(letters.charAt(i));
            backtrack(digits, index+1, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
// 時間複雜度O(4^n)，空間O(n)

