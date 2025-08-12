import java.util.ArrayList;

public class PriorityQueueWithHeap {
    // 把 Task 放成內部類別，避免檔名/visibility 的問題
    private static class Task {
        String name;
        int priority;
        long timestamp; // 用於相同優先級時的先後順序

        public Task(String name, int priority, long timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return name + " (優先級: " + priority + ")";
        }
    }

    private final ArrayList<Task> heap;
    private long timeCounter = 0;

    public PriorityQueueWithHeap() {
        heap = new ArrayList<>();
    }

    public void addTask(String name, int priority) {
        Task t = new Task(name, priority, timeCounter++);
        heap.add(t);
        heapifyUp(heap.size() - 1);
    }

    public Task executeNext() {
        if (isEmpty()) throw new IllegalStateException("沒有任務可以執行");
        Task top = heap.get(0);
        Task last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return top;
    }

    public Task peek() {
        if (isEmpty()) throw new IllegalStateException("佇列為空");
        return heap.get(0);
    }

    public void changePriority(String name, int newPriority) {
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).name.equals(name)) {
                heap.get(i).priority = newPriority;
                // 變更後往上或往下調整都做一次，保險
                heapifyUp(i);
                heapifyDown(i);
                return;
            }
        }
        System.out.println("找不到任務：" + name);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void heapifyUp(int idx) {
        while (idx > 0) {
            int parent = (idx - 1) / 2;
            if (compare(heap.get(idx), heap.get(parent)) > 0) {
                swap(idx, parent);
                idx = parent;
            } else break;
        }
    }

    private void heapifyDown(int idx) {
        int n = heap.size();
        while (true) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int largest = idx;

            if (left < n && compare(heap.get(left), heap.get(largest)) > 0) largest = left;
            if (right < n && compare(heap.get(right), heap.get(largest)) > 0) largest = right;

            if (largest != idx) {
                swap(idx, largest);
                idx = largest;
            } else break;
        }
    }

    // 返回正數表示 a 比 b "大"（優先執行）
    private int compare(Task a, Task b) {
        if (a.priority != b.priority) {
            return Integer.compare(a.priority, b.priority); // 優先級大的較大
        }
        // 同優先級時，timestamp 小（先加入）的應該優先 -> 我們要讓早加入的被視為 "較大"
        return Long.compare(b.timestamp, a.timestamp);
    }

    private void swap(int i, int j) {
        Task tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    // 範例測試
    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();
        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("peek: " + pq.peek());
        System.out.println("execute: " + pq.executeNext());
        System.out.println("execute: " + pq.executeNext());
        System.out.println("execute: " + pq.executeNext());

        pq.addTask("清理", 2);
        pq.addTask("檢查", 4);
        pq.changePriority("清理", 6);
        System.out.println("execute: " + pq.executeNext()); // 清理
        System.out.println("execute: " + pq.executeNext()); // 檢查
    }
}
