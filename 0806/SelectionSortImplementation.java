// 檔名: SelectionSortImplementation.java

import java.util.Arrays;
import java.util.Scanner;

public class SelectionSortImplementation {

    // 選擇排序 (帶有過程顯示、計數功能)
    public static int[] selectionSort(int[] arr, boolean verbose) {
        int n = arr.length;
        int[] a = Arrays.copyOf(arr, n); // 避免影響原陣列
        int comparisons = 0;
        int swaps = 0;

        System.out.println("=== 選擇排序過程 ===");
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (a[j] < a[minIdx]) minIdx = j;
            }
            // 僅當 i 不是最小才交換
            if (minIdx != i) {
                int temp = a[i];
                a[i] = a[minIdx];
                a[minIdx] = temp;
                swaps++;
            }
            if (verbose) {
                System.out.println("第 " + (i + 1) + " 輪: " + Arrays.toString(a));
            }
        }
        System.out.println("總比較次數：" + comparisons);
        System.out.println("總交換次數：" + swaps);
        return a;
    }

    // 氣泡排序 (帶計數)
    public static int[] bubbleSort(int[] arr, boolean verbose) {
        int n = arr.length;
        int[] a = Arrays.copyOf(arr, n);
        int comparisons = 0;
        int swaps = 0;

        System.out.println("=== 氣泡排序過程 ===");
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                comparisons++;
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            if (verbose) {
                System.out.println("第 " + (i + 1) + " 輪: " + Arrays.toString(a));
            }
            if (!swapped) break; // 提前結束
        }
        System.out.println("總比較次數：" + comparisons);
        System.out.println("總交換次數：" + swaps);
        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("請輸入要排序的整數陣列 (用空白分隔):");
        String[] tokens = sc.nextLine().split("\\s+");
        int[] nums = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) nums[i] = Integer.parseInt(tokens[i]);

        System.out.println("\n1. 選擇排序");
        int[] selectionSorted = selectionSort(nums, true);

        System.out.println("\n2. 氣泡排序");
        int[] bubbleSorted = bubbleSort(nums, true);

        System.out.println("\n=== 結果比較 ===");
        System.out.println("選擇排序結果：" + Arrays.toString(selectionSorted));
        System.out.println("氣泡排序結果：" + Arrays.toString(bubbleSorted));
        System.out.println("（你可比較兩者的比較次數及交換次數）");

        sc.close();
    }
}

