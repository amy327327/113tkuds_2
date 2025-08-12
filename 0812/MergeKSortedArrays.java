import java.util.*;

public class MergeKSortedArrays {

    static class Element implements Comparable<Element> {
        int value;
        int arrayIndex; // 來自哪個陣列
        int elementIndex; // 該陣列中的位置

        public Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(Element other) {
            return Integer.compare(this.value, other.value);
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        // 初始化，每個陣列放第一個元素
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            Element smallest = minHeap.poll();
            result.add(smallest.value);

            int nextIndex = smallest.elementIndex + 1;
            if (nextIndex < arrays[smallest.arrayIndex].length) {
                minHeap.offer(new Element(
                    arrays[smallest.arrayIndex][nextIndex],
                    smallest.arrayIndex,
                    nextIndex
                ));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] arrays1 = {{1,4,5}, {1,3,4}, {2,6}};
        System.out.println(mergeKSortedArrays(arrays1)); // [1,1,2,3,4,4,5,6]

        int[][] arrays2 = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(mergeKSortedArrays(arrays2)); // [1,2,3,4,5,6,7,8,9]

        int[][] arrays3 = {{1}, {0}};
        System.out.println(mergeKSortedArrays(arrays3)); // [0,1]
    }
}

