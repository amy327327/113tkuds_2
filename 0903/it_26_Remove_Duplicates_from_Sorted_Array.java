public class it_26_Remove_Duplicates_from_Sorted_Array {
    // 移除排序陣列中多餘重複元素，返回新長度
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int i = 0; // 慢指針
        for (int j = 1; j < nums.length; j++) {
            if(nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j]; // 將不同元素移到前方
            }
        }
        return i + 1; // 新長度
    }
}

