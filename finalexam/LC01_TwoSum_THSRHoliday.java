import java.util.HashMap;
import java.util.Scanner;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] seats = new int[n];
        for (int i = 0; i < n; i++) seats[i] = sc.nextInt();

        // HashMap: key=需要的數, value=索引i
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(seats[i])) {
                // 找到答案：座位 seats[i] 是前面班次「需要的數」
                System.out.println(map.get(seats[i]) + " " + i);
                return; // 只要一組即可
            }
            // 記錄「需要 target-seats[i]」
            map.put(target - seats[i], i);
        }
        System.out.println("-1 -1");
    }
}
// Time: O(n); Space: O(n)
