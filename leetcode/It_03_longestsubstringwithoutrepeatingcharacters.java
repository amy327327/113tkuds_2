

import java.util.HashSet;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int left = 0, right = 0;
        HashSet<Character> set = new HashSet<>();

        while (right < n) {
            char c = s.charAt(right);
            if (!set.contains(c)) {
                set.add(c);
                right++;
                maxLength = Math.max(maxLength, right - left);
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }

        return maxLength;
    }
}

public class It_03_longestsubstringwithoutrepeatingcharacters {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 測試範例
        String test1 = "abcabcbb";
        String test2 = "bbbbb";
        String test3 = "pwwkew";

        System.out.println("Input: " + test1 + " -> Output: " + solution.lengthOfLongestSubstring(test1));
        System.out.println("Input: " + test2 + " -> Output: " + solution.lengthOfLongestSubstring(test2));
        System.out.println("Input: " + test3 + " -> Output: " + solution.lengthOfLongestSubstring(test3));
    }
}

