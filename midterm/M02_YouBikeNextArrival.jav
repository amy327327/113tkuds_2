

import java.util.Scanner;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] times = new int[n];
        String[] timeStrs = new String[n];
        for (int i = 0; i < n; i++) {
            timeStrs[i] = sc.nextLine();
            times[i] = toMinute(timeStrs[i]);
        }
        String queryStr = sc.nextLine();
        int query = toMinute(queryStr);

        // 二分搜尋「第一個大於查詢」的位置
        int left = 0, right = n-1, ans = -1;
        while (left <= right) {
            int mid = (left + right)/2;
            if (times[mid] > query) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (ans != -1) {
            System.out.println(timeStrs[ans]);
        } else {
            System.out.println("No bike");
        }
    }

    // 將 HH:mm 轉為自 00:00 起的分鐘數
    private static int toMinute(String s) {
        String[] parts = s.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts);
    }
}
