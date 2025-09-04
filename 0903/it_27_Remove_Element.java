public class it_27_Remove_Element {
    // 移除陣列中指定元素，返回新長度
    public int removeElement(int[] nums, int val) {
        int i = 0; // 慢指針
        for(int j=0; j<nums.length; j++) {
            if(nums[j] != val) {
                nums[i++] = nums[j]; // 不等於 val 的元素往前搬移
            }
        }
        return i;
    }
}
