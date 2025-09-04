public class it_28_Implement_strStr {
    // 返回字串 needle 在 haystack 中的首次出現位置，無則返回 -1
    public int strStr(String haystack, String needle) {
        if(needle.isEmpty()) return 0; // 空字串預設返回0

        for(int i=0; i<=haystack.length()-needle.length(); i++) {
            if(haystack.substring(i, i+needle.length()).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}

