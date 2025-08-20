
import java.util.Scanner;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // 清洗字串：只保留英文字母並轉小寫
        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        String str = cleaned.toString();

        if (isPalindrome(str, 0, str.length() - 1)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    // 遞迴回文判斷
    private static boolean isPalindrome(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindrome(s, left + 1, right - 1);
    }
}

