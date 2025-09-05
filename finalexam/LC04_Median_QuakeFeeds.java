import java.util.Scanner;
public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        double[] A = new double[n], B = new double[m];
        for (int i = 0; i < n; i++) A[i] = sc.nextDouble();
        for (int i = 0; i < m; i++) B[i] = sc.nextDouble();

        // 確保 A 為較短數列
        if (n > m) {
            double[] tmp = A; A = B; B = tmp;
            int tmpN = n; n = m; m = tmpN;
        }

        int total = n + m;
        int half = (total + 1) / 2;
        int l = 0, r = n;
        while (l <= r) {
            int i = (l + r) / 2;
            int j = half - i;

            double lA = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double rA = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double lB = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double rB = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            if (lA <= rB && lB <= rA) {
                double median;
                if ((n + m) % 2 == 1)
                    median = Math.max(lA, lB);
                else
                    median = (Math.max(lA, lB) + Math.min(rA, rB)) / 2.0;
                // 輸出一位小數
                System.out.printf("%.1f\n", median);
                return;
            } else if (lA > rB) {
                r = i - 1;
            } else {
                l = i + 1;
            }
        }
    }
}
// Time: O(log(min(n, m))); Space: O(1)

