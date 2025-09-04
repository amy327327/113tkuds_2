public class it_35_Search_Insert_Position {
    // 搜尋插入位置，若不存在則插入後返回位置
    public int searchInsert(int[] nums, int target) {
        int left =0, right = nums.length-1;
        while(left <= right) {
            int mid = left + (right-left)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) left = mid+1;
            else right= mid-1;
        }
        return left;
    }
}

