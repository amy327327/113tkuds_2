
public class it_31_Next_Permutation {
    // 將陣列改為字典序下的一個較大的排列，若無則變為最小排列
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 從右往左找到第一個升序位置
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) {
            int j = nums.length - 1;
            // 從右邊找到第一個比 nums[i] 大的元素
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j); // 交換
        }
        reverse(nums, i + 1, nums.length - 1); // 反轉後面的序列，取得下個排列
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i]; nums[i] = nums[j]; nums[j] = t;
    }

    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            swap(nums, start++, end--);
        }
    }
}
