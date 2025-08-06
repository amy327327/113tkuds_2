

import java.util.Scanner;
import java.util.Stack;

public class RecursionVsIteration {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\n=== 遞迴與迭代比較選單 ===");
            System.out.println("1. 計算二項式係數 C(n, k)");
            System.out.println("2. 尋找陣列中所有元素的乘積");
            System.out.println("3. 計算字串中元音字母的數量");
            System.out.println("4. 檢查括號是否配對正確");
            System.out.println("0. 離開");
            System.out.print("請選擇功能：");

            int choice = sc.nextInt();
            if(choice == 0) {
                System.out.println("程式結束。");
                break;
            }

            switch(choice) {
                case 1:
                    System.out.print("輸入 n：");
                    int n = sc.nextInt();
                    System.out.print("輸入 k：");
                    int k = sc.nextInt();
                    if(k < 0 || k > n) {
                        System.out.println("k 必須介於 0 到 n 之間");
                        break;
                    }
                    long startRec = System.nanoTime();
                    long recRes = binomialCoefficientRec(n, k);
                    long endRec = System.nanoTime();

                    long startIter = System.nanoTime();
                    long iterRes = binomialCoefficientIter(n, k);
                    long endIter = System.nanoTime();

                    System.out.printf("遞迴結果：C(%d, %d) = %d，耗時：%d ns\n", n, k, recRes, (endRec - startRec));
                    System.out.printf("迭代結果：C(%d, %d) = %d，耗時：%d ns\n", n, k, iterRes, (endIter - startIter));
                    break;

                case 2:
                    System.out.println("請輸入陣列（空白分隔）：");
                    sc.nextLine(); // 清除換行
                    String[] tokens = sc.nextLine().trim().split("\\s+");
                    int[] arr = new int[tokens.length];
                    for(int i=0; i<tokens.length; i++) arr[i] = Integer.parseInt(tokens[i]);

                    long startRec2 = System.nanoTime();
                    long recProduct = productRec(arr, arr.length);
                    long endRec2 = System.nanoTime();

                    long startIter2 = System.nanoTime();
                    long iterProduct = productIter(arr);
                    long endIter2 = System.nanoTime();

                    System.out.printf("遞迴結果：乘積 = %d，耗時：%d ns\n", recProduct, (endRec2 - startRec2));
                    System.out.printf("迭代結果：乘積 = %d，耗時：%d ns\n", iterProduct, (endIter2 - startIter2));
                    break;

                case 3:
                    System.out.print("請輸入字串：");
                    sc.nextLine(); // 清除換行
                    String inputStr = sc.nextLine();

                    long startRec3 = System.nanoTime();
                    int recCount = countVowelsRec(inputStr, 0);
                    long endRec3 = System.nanoTime();

                    long startIter3 = System.nanoTime();
                    int iterCount = countVowelsIter(inputStr);
                    long endIter3 = System.nanoTime();

                    System.out.printf("遞迴結果：元音字母數量 = %d，耗時：%d ns\n", recCount, (endRec3 - startRec3));
                    System.out.printf("迭代結果：元音字母數量 = %d，耗時：%d ns\n", iterCount, (endIter3 - startIter3));
                    break;

                case 4:
                    System.out.print("請輸入包含括號的字串：");
                    sc.nextLine(); // 清除換行
                    String bracketStr = sc.nextLine();

                    long startRec4 = System.nanoTime();
                    boolean recValid = checkBracketsRec(bracketStr, 0, 0);
                    long endRec4 = System.nanoTime();

                    long startIter4 = System.nanoTime();
                    boolean iterValid = checkBracketsIter(bracketStr);
                    long endIter4 = System.nanoTime();

                    System.out.printf("遞迴結果：括號配對%s，耗時：%d ns\n", recValid ? "正確" : "錯誤", (endRec4 - startRec4));
                    System.out.printf("迭代結果：括號配對%s，耗時：%d ns\n", iterValid ? "正確" : "錯誤", (endIter4 - startIter4));
                    break;

                default:
                    System.out.println("無效選項，請重新輸入。");
            }
        }

        sc.close();
    }


    // 1. 二項式係數 - 遞迴 (C(n,k) = C(n-1,k-1)+C(n-1,k), 基底條件 k=0 或 k=n)
    public static long binomialCoefficientRec(int n, int k) {
        if(k == 0 || k == n) return 1;
        return binomialCoefficientRec(n-1, k-1) + binomialCoefficientRec(n-1, k);
    }

    // 二項式係數 - 迭代 (使用動態規劃)
    public static long binomialCoefficientIter(int n, int k) {
        long[] C = new long[k+1];
        C[0] = 1; // C(n,0) = 1
        for(int i=1; i<=n; i++) {
            int j = Math.min(i, k);
            while(j > 0) {
                C[j] = C[j] + C[j-1];
                j--;
            }
        }
        return C[k];
    }

    // 2. 陣列所有元素乘積 - 遞迴
    public static long productRec(int[] arr, int n) {
        if(n == 0) return 1;
        return productRec(arr, n-1) * arr[n-1];
    }

    // 陣列所有元素乘積 - 迭代
    public static long productIter(int[] arr) {
        long result = 1;
        for(int val : arr) {
            result *= val;
        }
        return result;
    }

    // 3. 字串元音字母數量計算 - 遞迴
    public static int countVowelsRec(String s, int index) {
        if(index >= s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(index));
        int count = (c=='a' || c=='e' || c=='i' || c=='o' || c=='u') ? 1 : 0;
        return count + countVowelsRec(s, index+1);
    }

    // 字串元音字母數量計算 - 迭代
    public static int countVowelsIter(String s) {
        int count = 0;
        for(char c : s.toLowerCase().toCharArray()) {
            if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u') {
                count++;
            }
        }
        return count;
    }

    // 4. 括號配對判斷 - 遞迴
    // 只考慮小括號 ()，利用計數器，index 為目前字元位置，count 為目前待閉合左括號數量
    public static boolean checkBracketsRec(String s, int index, int count) {
        if(index == s.length()) return count == 0;
        char c = s.charAt(index);
        if(c == '(') {
            return checkBracketsRec(s, index+1, count+1);
        } else if(c == ')') {
            if(count == 0) return false; // 提早遇到閉合但無對應左括號
            return checkBracketsRec(s, index+1, count-1);
        } else {
            return checkBracketsRec(s, index+1, count);
        }
    }

    // 括號配對判斷 - 迭代 (使用棧)
    public static boolean checkBracketsIter(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c == '(') {
                stack.push(c);
            } else if(c == ')') {
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}


