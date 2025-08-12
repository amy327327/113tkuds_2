import java.util.*;

public class MovingAverageStream {
    private final int size;
    private final Queue<Integer> window;
    private long sum;

    // 用於中位數的兩個 heap
    private PriorityQueue<Integer> maxHeap; // 小的一半 (最大堆)
    private PriorityQueue<Integer> minHeap; // 大的一半 (最小堆)

    // 用於 min/max 查詢
    private TreeMap<Integer, Integer> countMap; // 儲存數字頻率

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;

        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.countMap = new TreeMap<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;
        addNumber(val);
        countMap.put(val, countMap.getOrDefault(val, 0) + 1);

        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;
            removeNumber(removed);
            countMap.put(removed, countMap.get(removed) - 1);
            if (countMap.get(removed) == 0) {
                countMap.remove(removed);
            }
        }

        return sum * 1.0 / window.size();
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public int getMin() {
        return countMap.firstKey();
    }

    public int getMax() {
        return countMap.lastKey();
    }

    private void addNumber(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeNumber(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));   // 1.0
        System.out.println(ma.next(10));  // 5.5
        System.out.println(ma.next(3));   // 4.67
        System.out.println(ma.next(5));   // 6.0
        System.out.println("Median: " + ma.getMedian()); // 5.0
        System.out.println("Min: " + ma.getMin());       // 3
        System.out.println("Max: " + ma.getMax());       // 10
    }
}

