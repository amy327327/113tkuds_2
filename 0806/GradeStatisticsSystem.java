
import java.util.Scanner;
public class GradeStatisticsSystem {
   



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入學生人數
        System.out.print("請輸入學生人數: ");
        int n = sc.nextInt();
        int[] grades = new int[n];

        // 輸入每位學生的成績
        for (int i = 0; i < n; i++) {
            System.out.printf("請輸入第 %d 位學生的成績: ", i + 1);
            grades[i] = sc.nextInt();
            // 檢查成績合理性（0~100）
            if (grades[i] < 0 || grades[i] > 100) {
                System.out.println("成績需介於 0 到 100，請重新輸入。");
                i--;
            }
        }

        // 1. 計算平均值、最高分、最低分
        int sum = 0, max = grades[0], min = grades[0];
        for (int i = 0; i < n; i++) {
            sum += grades[i];
            if (grades[i] > max) max = grades[i];
            if (grades[i] < min) min = grades[i];
        }
        double average = (double)sum / n;

        // 2. 統計各等第人數
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;
        for (int i = 0; i < n; i++) {
            int score = grades[i];
            if (score >= 90) countA++;
            else if (score >= 80) countB++;
            else if (score >= 70) countC++;
            else if (score >= 60) countD++;
            else countF++;
        }

        // 3. 找出高於平均分的學生人數
        int countAboveAverage = 0;
        for (int i = 0; i < n; i++) {
            if (grades[i] > average) {
                countAboveAverage++;
            }
        }

        // 4. 列印完整成績報表
        System.out.println("\n====== 成績報表 ======");
        System.out.printf("平均分: %.2f\n", average);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("A (90~100): " + countA + " 人");
        System.out.println("B (80~89): " + countB + " 人");
        System.out.println("C (70~79): " + countC + " 人");
        System.out.println("D (60~69): " + countD + " 人");
        System.out.println("F (0~59): " + countF + " 人");
        System.out.println("高於平均分人數: " + countAboveAverage + " 人");

        // 加碼印出每位學生的成績（可依需要加入學生姓名欄位）
        System.out.println("\n各學生成績：");
        for (int i = 0; i < n; i++) {
            System.out.printf("學生 %d: %d 分\n", i + 1, grades[i]);
        }

        sc.close();
    }
}

    

