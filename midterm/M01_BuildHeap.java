
import java.util.Scanner;

public class M01_BuildHeap {
   



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        int n = sc.nextInt();
        int[] heap = new int[n];
        for (int i = 0; i < n; i++) heap[i] = sc.nextInt();
        if (type.equals("max")) {
            buildMaxHeap(heap, n);
        } else {
            buildMinHeap(heap, n);
        }
        for (int i = 0; i < n; i++) {
            System.out.print(heap[i]);
            if (i != n-1) System.out.print(" ");
        }
    }

    private static void buildMaxHeap(int[] arr, int n) {
        for (int i = n/2 - 1; i >= 0; i--) {
            heapifyDownMax(arr, n, i);
        }
    }

    private static void heapifyDownMax(int[] arr, int n, int i) {
        int largest = i;
        int l = 2*i + 1, r = 2*i + 2;
        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;
        if (largest != i) {
            int tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            heapifyDownMax(arr, n, largest);
        }
    }

    private static void buildMinHeap(int[] arr, int n) {
        for (int i = n/2 - 1; i >= 0; i--) {
            heapifyDownMin(arr, n, i);
        }
    }

    private static void heapifyDownMin(int[] arr, int n, int i) {
        int smallest = i;
        int l = 2*i + 1, r = 2*i + 2;
        if (l < n && arr[l] < arr[smallest]) smallest = l;
        if (r < n && arr[r] < arr[smallest]) smallest = r;
        if (smallest != i) {
            int tmp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = tmp;
            heapifyDownMin(arr, n, smallest);
        }
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上的建堆，對每個非葉節點呼叫 heapifyDown，
 * 每層需要操作的次數呈指數減半，所以總體複雜度為 O(n)。
 */

    

