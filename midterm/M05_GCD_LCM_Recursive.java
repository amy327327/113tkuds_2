
import java.util.Scanner;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long g = gcd(a, b);
        long l = (a / g) * b; // 避免乘法溢位先除後乘

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    // 遞迴計算 gcd
    private static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}

/*
 * Time Complexity: O(log(min(a,b)))
 * 說明：歐幾里得演算法在每一步至少減少一半的餘數，
 * 所以最大遞迴深度與對數有關，故複雜度為 O(log(min(a,b)))。
 */

