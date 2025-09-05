import java.util.Scanner;
public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0; i<n; i++) nums[i] = sc.nextInt();

        int left = findLeft(nums, target);
        int right = findRight(nums, target);

        System.out.println(left + " " + right);
    }

    static int findLeft(int[] nums, int target){
        int l = 0, r = nums.length - 1, res = -1;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(nums[mid] >= target){
                if(nums[mid] == target) res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }

    static int findRight(int[] nums, int target){
        int l = 0, r = nums.length - 1, res = -1;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(nums[mid] <= target){
                if(nums[mid] == target) res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }
}
// 複雜度 O(log n)

