/**
 * 題目：Longest Common Prefix
 * 解題思路：
 * 先以第一個字串做為初始前綴，然後逐一和其他字串比較前綴。
 * 若其他字串不包含目前前綴，則依序縮短前綴字串，直到所有字串都包含或縮短至空字串。
 */
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}


