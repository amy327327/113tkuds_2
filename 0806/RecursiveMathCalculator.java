

import java.util.Scanner;

public class RecursiveMathCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== 遞迴數學計算器選單 ===");
            System.out.println("1. 計算組合數 C(n, k)");
            System.out.println("2. 計算卡塔蘭數 C(n)");
            System.out.println("3. 計算漢諾塔移動步數 hanoi(n)");
            System.out.println("4. 判斷數字是否為回文數");
            System.out.println("0. 離開");
            System.out.print("請選擇操作：");
            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("程式結束。");
                break;
            }
        }
    }
}

