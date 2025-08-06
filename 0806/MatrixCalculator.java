
// 檔名: MatrixCalculator.java

import java.util.Scanner;

public class MatrixCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入第一個矩陣
        System.out.print("請輸入矩陣 A 的列數: ");
        int rowsA = sc.nextInt();
        System.out.print("請輸入矩陣 A 的行數: ");
        int colsA = sc.nextInt();
        int[][] matrixA = new int[rowsA][colsA];
        System.out.println("請依序輸入矩陣 A 的元素：");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                matrixA[i][j] = sc.nextInt();
            }
        }

        // 輸入第二個矩陣
        System.out.print("請輸入矩陣 B 的列數: ");
        int rowsB = sc.nextInt();
        System.out.print("請輸入矩陣 B 的行數: ");
        int colsB = sc.nextInt();
        int[][] matrixB = new int[rowsB][colsB];
        System.out.println("請依序輸入矩陣 B 的元素：");
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                matrixB[i][j] = sc.nextInt();
            }
        }

        while (true) {
            System.out.println("\n========== 矩陣運算選單 ==========");
            System.out.println("1. 矩陣加法 (A+B)");
            System.out.println("2. 矩陣乘法 (A*B)");
            System.out.println("3. 矩陣轉置 (A 或 B)");
            System.out.println("4. 尋找最大值與最小值 (A 或 B)");
            System.out.println("0. 離開");
            System.out.print("請選擇操作：");
            int option = sc.nextInt();

            if (option == 0) {
                System.out.println("程式結束。");
                break;
            }

            switch (option) {
                case 1:
                    // 矩陣加法
                    if (rowsA != rowsB || colsA != colsB) {
                        System.out.println("錯誤：兩個矩陣必須同維度才能加法！");
                    } else {
                        System.out.println("A + B =");
                        int[][] sum = addMatrix(matrixA, matrixB);
                        printMatrix(sum);
                    }
                    break;
                case 2:
                    // 矩陣乘法
                    if (colsA != rowsB) {
                        System.out.println("錯誤：A 的行數需等於 B 的列數才可乘法！");
                    } else {
                        System.out.println("A * B =");
                        int[][] product = multiplyMatrix(matrixA, matrixB);
                        printMatrix(product);
                    }
                    break;
                case 3:
                    // 矩陣轉置
                    System.out.print("請選擇要轉置 A(1) 或 B(2)：");
                    int t = sc.nextInt();
                    if (t == 1) {
                        System.out.println("A 的轉置：");
                        printMatrix(transposeMatrix(matrixA));
                    } else if (t == 2) {
                        System.out.println("B 的轉置：");
                        printMatrix(transposeMatrix(matrixB));
                    } else {
                        System.out.println("輸入錯誤。");
                    }
                    break;
                case 4:
                    // 尋找最大最小值
                    System.out.print("請選擇要查詢 A(1) 或 B(2)：");
                    int m = sc.nextInt();
                    if (m == 1) {
                        int[] mm = findMaxMin(matrixA);
                        System.out.println("A 的最大值: " + mm[0] + ", 最小值: " + mm[1]);
                    } else if (m == 2) {
                        int[] mm = findMaxMin(matrixB);
                        System.out.println("B 的最大值: " + mm[0] + ", 最小值: " + mm[1]);
                    } else {
                        System.out.println("輸入錯誤。");
                    }
                    break;
                default:
                    System.out.println("請輸入正確選項！");
            }
        }
        sc.close();
    }

    // 矩陣加法
    public static int[][] addMatrix(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    // 矩陣乘法
    public static int[][] multiplyMatrix(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;
        int[][] result = new int[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    // 矩陣轉置
    public static int[][] transposeMatrix(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;
        int[][] t = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                t[j][i] = m[i][j];
            }
        }
        return t;
    }

    // 尋找矩陣中的最大值與最小值
    public static int[] findMaxMin(int[][] m) {
        int max = m[0][0];
        int min = m[0][0];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] > max) max = m[i][j];
                if (m[i][j] < min) min = m[i][j];
            }
        }
        return new int[] { max, min };
    }

    // 輔助函數: 印出矩陣
    public static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.printf("%5d", m[i][j]);
            }
            System.out.println();
        }
    }
}

