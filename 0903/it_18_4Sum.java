import java.util.*;

public class it_18_4Sum {
    // 主函式，尋找所有四數和等於目標值的組合
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); // 排序陣列，方便避免重複和雙指針處理

        // 遍歷第一個數字
        for (int i = 0; i < nums.length - 3; i++) {
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            
            // 遍歷第二個數字
            for (int j = i+1; j < nums.length - 2; j++) {
                if(j > i+1 && nums[j] == nums[j-1]) continue; // 去重
                
                int left = j+1, right = nums.length-1; // 雙指針
                while (left < right) {
                    int sum = nums[i]+nums[j]+nums[left]+nums[right]; // 四數和
                    
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right])); // 新解
                        
                        while (left < right && nums[left] == nums[left+1]) left++; // 跳過重複
                        while (left < right && nums[right] == nums[right-1]) right--; // 跳過重複
                        
                        left++; right--; // 移動雙指針尋找新組合
                    } else if (sum < target) left++; // 和偏小，左指針右移
                    else right--; // 和偏大，右指針左移
                }
            }
        }
        return ans; // 返回所有四數組合
    }
}
