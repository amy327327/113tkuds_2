// It_04_medianoftwosortedarrays.java

public class It_04_medianoftwosortedarrays {

    // 方法：找兩個已排序陣列的中位數
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] merged = new int[m + n];
        int i = 0, j = 0, k = 0;

        // 合併兩個排序陣列
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        while (i < m) merged[k++] = nums1[i++];
        while (j < n) merged[k++] = nums2[j++];

        // 計算中位數
        int totalLength = m + n;
        if (totalLength % 2 == 1) {
            return merged[totalLength / 2];
        } else {
            return (merged[totalLength / 2 - 1] + merged[totalLength / 2]) / 2.0;
        }
    }

    public static void main(String[] args) {
        // 測試範例 1
        int[] nums1_1 = {1, 3};
        int[] nums2_1 = {2};
        System.out.println("Input: [1,3], [2] -> Output: " + findMedianSortedArrays(nums1_1, nums2_1));

        // 測試範例 2
        int[] nums1_2 = {1, 2};
        int[] nums2_2 = {3, 4};
        System.out.println("Input: [1,2], [3,4] -> Output: " + findMedianSortedArrays(nums1_2, nums2_2));
    }
}
