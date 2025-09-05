import java.util.Scanner;
public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String haystack = sc.nextLine();
        String needle = sc.nextLine();

        if(needle.length() == 0){
            System.out.println(0);
            return;
        }
        System.out.println(strStr(haystack, needle));
    }

    static int strStr(String haystack, String needle){
        int n = haystack.length(), m = needle.length();
        for(int i=0; i <= n-m; i++){
            if(haystack.substring(i, i+m).equals(needle)){
                return i;
            }
        }
        return -1;
    }
}
// 複雜度 O(n*m) 暴力法

