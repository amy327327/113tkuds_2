import java.util.Scanner;
public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0; i<n; i++) nums[i] = sc.nextInt();

        int write = 1;
        for(int i=1; i<n; i++){
            if(nums[i] != nums[i-1]){
                nums[write++] = nums[i];
            }
        }
        System.out.print(write);
        for(int i=0; i<write; i++){
            System.out.print(" " + nums[i]);
        }
    }
}
// 複雜度 O(n)

