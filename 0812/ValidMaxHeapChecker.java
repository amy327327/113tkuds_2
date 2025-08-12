public class ValidMaxHeapChecker {

    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        // 最後一個非葉節點索引
        int lastParentIndex = (n - 2) / 2;

        for (int i = 0; i <= lastParentIndex; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // 左子節點檢查
            if (left < n && arr[i] < arr[left]) {
                System.out.println("違反規則：索引 " + left + " 的 " + arr[left] + 
                                   " 大於父節點索引 " + i + " 的 " + arr[i]);
                return false;
            }
            // 右子節點檢查
            if (right < n && arr[i] < arr[right]) {
                System.out.println("違反規則：索引 " + right + " 的 " + arr[right] + 
                                   " 大於父節點索引 " + i + " 的 " + arr[i]);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65},
            {100, 90, 80, 95, 60, 75, 65},
            {50},
            {}
        };

        for (int[] testCase : testCases) {
            boolean result = isValidMaxHeap(testCase);
            System.out.print("陣列: ");
            for (int num : testCase) {
                System.out.print(num + " ");
            }
            System.out.println("→ " + result);
            System.out.println("---------------------");
        }
    }
}

