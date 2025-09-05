import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class LC18_4Sum_Procurement {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0; i<n; i++) nums[i] = sc.nextInt();

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for(int i=0; i<n-3; i++){
            if(i > 0 && nums[i]==nums[i-1]) continue;
            for(int j = i+1; j<n-2; j++){
                if(j > i+1 && nums[j]==nums[j-1]) continue;
                int left = j+1, right = n-1;
                while(left < right) {
                    int sum = nums[i]+nums[j]+nums[left]+nums[right];
                    if(sum == target){
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while(left < right && nums[left] == nums[left+1]) left++;
                        while(left < right && nums[right] == nums[right-1]) right--;
                        left++; right--;
                    } else if(sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        for(List<Integer> quad : res){
            System.out.println(quad.get(0) + " " + quad.get(1) + " " + quad.get(2) + " " + quad.get(3));
        }
    }
}
// 複雜度 O(n^3)

