import java.util.Arrays;

public class it_16_3Sum_Closest {
    // 主函式，尋找三數之和最接近目標值
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 對陣列排序，方便雙指針策略
        int ans = nums[0] + nums[1] + nums[2]; // 初始化答案為前三個元素的和
        
        // 遍歷每個元素作為第一數
        for (int i = 0; i < nums.length - 2; ++i) {
            int left = i + 1; // 左指針，從當前元素下一位開始
            int right = nums.length - 1; // 右指針，從陣列末尾開始

            // 雙指針向中間移動尋找最佳組合
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right]; // 三數和
                
                if (sum == target) return sum; // 精確匹配則直接返回
                
                // 若當前三數和比現有答案更接近目標，更新答案
                if (Math.abs(sum - target) < Math.abs(ans - target)) ans = sum;
                
                // 根據當前和與目標比較，調整雙指針
                if (sum < target) ++left; // 和偏小，左指針右移增加和
                else --right; // 和偏大，右指針左移降低和
            }
        }
        return ans; // 返回最接近目標的和
    }
}
