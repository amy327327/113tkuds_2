
// 檔名: NumberArrayProcessor.java

import java.util.*;

public class NumberArrayProcessor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("請輸入數字陣列（用空白分隔）：");
        String[] input = sc.nextLine().split("\\s+");
        int[] nums = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }

        while (true) {
            System.out.println("\n====== 數字陣列處理器選單 ======");
            System.out.println("1. 移除重複元素");
            System.out.println("2. 合併兩個已排序的陣列");
            System.out.println("3. 找出出現頻率最高的元素");
            System.out.println("4. 陣列分割成兩個相等（或近似）子陣列");
            System.out.println("0. 離開");
            System.out.print("請選擇操作：");
            int choice = sc.nextInt();
            sc.nextLine(); // 吃換行

            switch (choice) {
                case 0:
                    System.out.println("程式結束。");
                    return;
                case 1:
                    int[] noDup = removeDuplicates(nums);
                    System.out.println("移除重複後的結果：" + Arrays.toString(noDup));
                    break;
                case 2:
                    System.out.println("請再輸入第二個已排序的陣列（用空白分隔）：");
                    String[] input2 = sc.nextLine().split("\\s+");
                    int[] arr2 = new int[input2.length];
                    for (int i = 0; i < arr2.length; i++) arr2[i] = Integer.parseInt(input2[i]);
                    int[] merged = mergeSortedArrays(nums, arr2);
                    System.out.println("合併後的結果：" + Arrays.toString(merged));
                    break;
                case 3:
                    int freq = mostFrequentElement(nums);
                    int count = countFrequency(nums, freq);
                    System.out.println("出現最頻繁元素：" + freq + "（共 " + count + " 次）");
                    break;
                case 4:
                    int[][] split = splitIntoTwoEqualParts(nums);
                    System.out.println("子陣列 1：" + Arrays.toString(split[0]));
                    System.out.println("子陣列 2：" + Arrays.toString(split[1]));
                    int sum1 = Arrays.stream(split[0]).sum();
                    int sum2 = Arrays.stream(split[1]).sum();
                    System.out.println("分組後的總和：" + sum1 + "、" + sum2);
                    break;
                default:
                    System.out.println("請輸入正確選項！");
            }
        }
    }

    // 1. 移除陣列中的重複元素
    public static int[] removeDuplicates(int[] arr) {
        // LinkedHashSet 保留順序又自動去重
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) set.add(num);
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) result[i++] = num;
        return result;
    }

    // 2. 合併兩個已排序的陣列（經典 merge）
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                merged[k++] = a[i++];
            } else {
                merged[k++] = b[j++];
            }
        }
        while (i < a.length) merged[k++] = a[i++];
        while (j < b.length) merged[k++] = b[j++];
        return merged;
    }

    // 3. 找出陣列中出現頻率最高的元素
    public static int mostFrequentElement(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxFreq = 0, result = arr[0];
        for (int num : arr) {
            int freq = freqMap.getOrDefault(num, 0) + 1;
            freqMap.put(num, freq);
            if (freq > maxFreq || (freq == maxFreq && num < result)) {
                maxFreq = freq;
                result = num;
            }
        }
        return result;
    }
    // 輔助: 計算某元素出現次數
    public static int countFrequency(int[] arr, int target) {
        int count = 0;
        for (int num : arr) if (num == target) count++;
        return count;
    }

    // 4. 將陣列分割為2個總和最相近的子陣列（貪婪近似法）
    public static int[][] splitIntoTwoEqualParts(int[] arr) {
        // 先排序，貪婪法：從大到小交替放進兩組
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        int leftSum = 0, rightSum = 0;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = sorted.length - 1; i >= 0; i--) {
            if (leftSum <= rightSum) {
                left.add(sorted[i]);
                leftSum += sorted[i];
            } else {
                right.add(sorted[i]);
                rightSum += sorted[i];
            }
        }
        int[] res1 = left.stream().mapToInt(Integer::intValue).toArray();
        int[] res2 = right.stream().mapToInt(Integer::intValue).toArray();
        return new int[][]{res1, res2};
    }
}

