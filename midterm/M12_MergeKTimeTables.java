
import java.util.Scanner;

public class M12_MergeKTimeTables {
    static class Entry implements Comparable<Entry> {
        int time;
        int listIdx;
        int pos;

        Entry(int time, int listIdx, int pos) {
            this.time = time;
            this.listIdx = listIdx;
            this.pos = pos;
        }

        @Override
        public int compareTo(Entry o) {
            return this.time - o.time; // Min-Heap 排序用
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                arr[j] = sc.nextInt();
            }
            lists.add(arr);
        }

        PriorityQueue<Entry> pq = new PriorityQueue<>();
        // 每個列表先加入第一筆
        for (int i = 0; i < K; i++) {
            if (lists.get(i).length > 0) {
                pq.offer(new Entry(lists.get(i)[0], i, 0));
            }
        }

        List<Integer> merged = new ArrayList<>();
        while (!pq.isEmpty()) {
            Entry top = pq.poll();
            merged.add(top.time);
            int nextPos = top.pos + 1;
            if (nextPos < lists.get(top.listIdx).length) {
                pq.offer(new Entry(lists.get(top.listIdx)[nextPos], top.listIdx, nextPos));
            }
        }

        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i));
            if (i != merged.size() - 1) System.out.print(" ");
        }
    }
}

