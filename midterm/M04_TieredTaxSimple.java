import java.util.Scanner;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] income = new int[n];
        int totalTax = 0;
        for (int i = 0; i < n; i++) {
            income[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int tax = calcTax(income[i]);
            totalTax += tax;
            System.out.println("Tax: " + tax);
        }
        int avg = totalTax / n;
        System.out.println("Average: " + avg);
    }

    // 級距稅額計算 (分段累加)
    private static int calcTax(int x) {
        int tax = 0;
        if (x > 1_000_000) {
            tax += (x - 1_000_000) * 30 / 100;
            x = 1_000_000;
        }
        if (x > 500_000) {
            tax += (x - 500_000) * 20 / 100;
            x = 500_000;
        }
        if (x > 120_000) {
            tax += (x - 120_000) * 12 / 100;
            x = 120_000;
        }
        tax += x * 5 / 100;
        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：每筆收入計算稅額為常數時間，共 n 筆輸入，所以總體為 O(n)。
 */

    

