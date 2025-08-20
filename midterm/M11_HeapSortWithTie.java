

import java.util.Scanner;

public class M11_HeapSortWithTie {
    static class Student implements Comparable<Student> {
        int score;
        int index;

        Student(int score, int index) {
            this.score = score;
            this.index = index;
        }

        // Max-Heap 比較條件：
        // 分數高者優先，分數相同索引小者優先（輸入較早者優先）
        public int compareTo(Student o) {
            if (this.score != o.score) 
                return this.score - o.score; // 小者優先 -> MinHeap若要MaxHeap反向
            else
                return o.index - this.index; // 輸入較早（index 小）優先 -> MaxHeap時反向比較
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            arr[i] = new Student(s, i);
        }

        // 使用自訂 Max-Heap 實作 Heap Sort
        heapSort(arr);

        // 輸出遞增排序結果（score相同時輸入早者優先）
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score);
            if (i != n - 1) System.out.print(" ");
        }
    }

    private static void heapSort(Student[] arr) {
        int n = arr.length;

        // 先建 Max-Heap (從最後一個非葉節點開始)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 將堆頂（最大元素）與最後元素交換，縮小堆大小，再調整堆
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    // 調整堆：以 index 為根，維持 Max Heap 屬性
    private static void heapify(Student[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compare(arr[left], arr[largest]) > 0)
            largest = left;
        if (right < n && compare(arr[right], arr[largest]) > 0)
            largest = right;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    // 根據分數與索引比較 (Max-Heap)
    private static int compare(Student a, Student b) {
        if (a.score != b.score)
            return a.score - b.score; // score 大者為最大
        else
            return b.index - a.index;  // index 小者優先 (輸入較早)
    }

    private static void swap(Student[] arr, int i, int j) {
        Student tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：Heap Sort 包含建堆 O(n) 與每次取出最大值調整堆 O(log n)，
 * 共需進行 n-1 次，總體複雜度為 O(n log n)。
 */

