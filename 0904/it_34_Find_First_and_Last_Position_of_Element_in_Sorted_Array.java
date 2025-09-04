public class it_34_Find_First_and_Last_Position_of_Element_in_Sorted_Array {
    // 返回目標在排序陣列中起始與結束索引
    public int[] searchRange(int[] nums, int target) {
        return new int[]{findBound(nums, target, true), findBound(nums, target, false)};
    }

    // 二分法尋找左或右邊界
    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length -1, bound = -1;
        while(left <= right) {
            int mid = left + (right-left)/2;
            if(nums[mid] == target) {
                bound = mid;
                if(isFirst) right = mid -1;  // 找左邊界
                else left = mid +1;           // 找右邊界
            } else if(nums[mid] < target) {
                left = mid +1;
            } else {
                right = mid -1;
            }
        }
        return bound;
    }
}

