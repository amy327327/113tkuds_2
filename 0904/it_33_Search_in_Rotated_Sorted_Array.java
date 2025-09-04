public class it_33_Search_in_Rotated_Sorted_Array {
    // 在旋轉排序陣列中搜尋目標並返回索引，找不到返回-1
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] == target) return mid;

            // 左半段有序
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) right = mid -1;
                else left = mid+1;
            } 
            // 右半段有序
            else {
                if (nums[mid] < target && target <= nums[right]) left = mid+1;
                else right = mid-1;
            }
        }
        return -1;
    }
}

