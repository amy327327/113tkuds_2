

import java.util.Arrays;
import java.util.Scanner;

public class AdvancedArrayRecursion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\n=== 陣列遞迴操作進階選單 ===");
            System.out.println("1. 遞迴快速排序");
            System.out.println("2. 遞迴合併兩個已排序的陣列");
            System.out.println("3. 遞迴尋找第 k 小元素");
            System.out.println("4. 遞迴判斷是否存在子序列和等於目標值");
            System.out.println("0. 離開");
            System.out.print("請選擇功能：");

            int choice = sc.nextInt();
            if(choice == 0) {
                System.out.println("程式結束。");
                break;
            }

            switch(choice) {
                case 1:
                    System.out.println("請輸入陣列（空白分隔）：");
                    int[] arr1 = readArray(sc);
                    quickSort(arr1, 0, arr1.length - 1);
                    System.out.println("排序結果：" + Arrays.toString(arr1));
                    break;

                case 2:
                    System.out.println("請輸入第一個已排序陣列（空白分隔）：");
                    int[] sortedA = readArray(sc);
                    System.out.println("請輸入第二個已排序陣列（空白分隔）：");
                    int[] sortedB = readArray(sc);
                    int[] merged = mergeSortedRecursive(sortedA, 0, sortedB, 0);
                    System.out.println("合併結果：" + Arrays.toString(merged));
                    break;

                case 3:
                    System.out.println("請輸入陣列（空白分隔）：");
                    int[] arr3 = readArray(sc);
                    System.out.print("請輸入 k (第 k 小元素 k從1開始)：");
                    int k = sc.nextInt();
                    if(k < 1 || k > arr3.length) {
                        System.out.println("k 必須介於 1 和陣列長度之間");
                    } else {
                        // 利用快速排序後取第 k 小元素
                        quickSort(arr3, 0, arr3.length -1);
                        System.out.printf("第 %d 小元素為: %d\n", k, arr3[k-1]);
                    }
                    break;

                case 4:
                    System.out.println("請輸入陣列（空白分隔）：");
                    int[] arr4 = readArray(sc);
                    System.out.print("請輸入目標和：");
                    int target = sc.nextInt();
                    boolean exists = subsequenceSum(arr4, 0, target);
                    System.out.println("陣列中 " + (exists ? "存在" : "不存在") + "子序列和等於 " + target);
                    break;

                default:
                    System.out.println("無效選項，請重新輸入。");
            }
        }
        sc.close();
    }

    // 輔助讀取整數陣列
    private static int[] readArray(Scanner sc) {
        sc.nextLine(); // 清掉換行
        String[] tokens = sc.nextLine().trim().split("\\s+");
        int[] arr = new int[tokens.length];
        for(int i=0; i < tokens.length; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }
        return arr;
    }

    // 1. 遞迴快速排序實作
    private static void quickSort(int[] arr, int left, int right) {
        if(left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex -1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left -1;
        for(int j = left; j < right; j++) {
            if(arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 2. 遞迴合併兩個已排序陣列
    private static int[] mergeSortedRecursive(int[] a, int indexA, int[] b, int indexB) {
        // 利用輔助方法來遞迴合併
        int lenA = a.length, lenB = b.length;
        int[] merged = new int[lenA + lenB];
        mergeHelper(a, indexA, b, indexB, merged, 0);
        return merged;
    }
    private static void mergeHelper(int[] a, int i, int[] b, int j, int[] merged, int k) {
        if(i == a.length && j == b.length) return;
        if(i == a.length) {
            merged[k] = b[j];
            mergeHelper(a, i, b, j+1, merged, k+1);
        } else if(j == b.length) {
            merged[k] = a[i];
            mergeHelper(a, i+1, b, j, merged, k+1);
        } else if(a[i] <= b[j]) {
            merged[k] = a[i];
            mergeHelper(a, i+1, b, j, merged, k+1);
        } else {
            merged[k] = b[j];
            mergeHelper(a, i, b, j+1, merged, k+1);
        }
    }

    // 4. 遞迴判斷陣列中是否存在子序列和等於目標值
    private static boolean subsequenceSum(int[] arr, int start, int target) {
        if(target == 0) return true;           // 已達目標
        if(start == arr.length) return false;  // 權限用完且未達成
    
        // 選擇包含當前元素
        if(arr[start] <= target && subsequenceSum(arr, start + 1, target - arr[start]))
            return true;
        // 不包含當前元素，繼續往後找
        return subsequenceSum(arr, start + 1, target);
    }
}

