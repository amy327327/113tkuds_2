

import java.util.*;

public class AdvancedStringRecursion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\n=== 字串遞迴處理進階選單 ===");
            System.out.println("1. 遞迴產生字串的所有排列組合");
            System.out.println("2. 遞迴實作字串匹配演算法");
            System.out.println("3. 遞迴移除字串中的重複字符");
            System.out.println("4. 遞迴計算字串的所有子字串組合");
            System.out.println("0. 離開");
            System.out.print("請選擇功能：");

            int choice = sc.nextInt();
            sc.nextLine(); // 吃掉換行符號
            if(choice == 0) {
                System.out.println("程式結束。");
                break;
            }

            switch(choice) {
                case 1:
                    System.out.print("輸入字串以產生排列組合：");
                    String str1 = sc.nextLine();
                    Set<String> permutations = new LinkedHashSet<>();
                    permute(str1.toCharArray(), 0, permutations);
                    System.out.println("所有排列組合：");
                    for(String p : permutations) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.print("輸入主字串：");
                    String text = sc.nextLine();
                    System.out.print("輸入模式字串（子串）：");
                    String pattern = sc.nextLine();
                    boolean found = recursiveMatch(text, pattern, 0, 0);
                    System.out.println("遞迴字串匹配結果：" + (found ? "有匹配" : "無匹配"));
                    break;

                case 3:
                    System.out.print("輸入字串以遞迴移除重複字符：");
                    String str3 = sc.nextLine();
                    String noDup = removeDuplicatesRec(str3, 0, new HashSet<>());
                    System.out.println("移除重複後字串：" + noDup);
                    break;

                case 4:
                    System.out.print("輸入字串以產生所有子字串組合：");
                    String str4 = sc.nextLine();
                    List<String> substrings = new ArrayList<>();
                    generateSubstrings(str4, 0, "", substrings);
                    System.out.println("所有子字串組合：");
                    for(String sub : substrings) {
                        System.out.println(sub);
                    }
                    break;

                default:
                    System.out.println("無效選項，請重新輸入。");
            }
        }

        sc.close();
    }

    // 1. 遞迴產生所有排列組合（避免重複使用 Set 集合）
    private static void permute(char[] arr, int index, Set<String> result) {
        if(index == arr.length) {
            result.add(new String(arr));
            return;
        }
        for(int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            permute(arr, index +1, result);
            swap(arr, index, i); // 還原
        }
    }
    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 2. 遞迴字串匹配演算法（簡單的遞迴子串搜尋）
    private static boolean recursiveMatch(String text, String pattern, int tIndex, int pIndex) {
        if(pIndex == pattern.length()) return true;        // 完全匹配
        if(tIndex == text.length()) return false;          // 主字串跑完仍未匹配

        if(text.charAt(tIndex) == pattern.charAt(pIndex)) {
            // 當前字符相符，繼續檢查下一個字符
            return recursiveMatch(text, pattern, tIndex + 1, pIndex + 1);
        } else {
            // 字符不符，嘗試主字串向右滑動一格繼續尋找匹配（模式字串指針回歸0）
            return recursiveMatch(text, pattern, tIndex + 1, 0);
        }
    }

    // 3. 遞迴移除字串中的重複字符（保留第一次出現）
    private static String removeDuplicatesRec(String str, int index, Set<Character> seen) {
        if(index == str.length()) return "";
        char c = str.charAt(index);
        if(seen.contains(c)) {
            // 重複出現，跳過此字符
            return removeDuplicatesRec(str, index + 1, seen);
        } else {
            seen.add(c);
            return c + removeDuplicatesRec(str, index + 1, seen);
        }
    }

    // 4. 遞迴產生字串所有子字串組合（包含空串）
    private static void generateSubstrings(String str, int index, String current, List<String> result) {
        if(index == str.length()) {
            result.add(current);
            return;
        }
        // 選擇包含當前字符
        generateSubstrings(str, index + 1, current + str.charAt(index), result);
        // 選擇不包含當前字符
        generateSubstrings(str, index + 1, current, result);
    }
}

