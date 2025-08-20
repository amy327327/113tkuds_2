

import java.util.*;

public class M03_TopKConvenience {
    static class Item implements Comparable<Item> {
        String name;
        int qty;
        int order; // for stable ordering: smaller means earlier input

        Item(String name, int qty, int order) {
            this.name = name;
            this.qty = qty;
            this.order = order;
        }

        // Min-Heap: qty小者優先，qty相同時order大者先出（保留輸入順序的穩定性）
        public int compareTo(Item o) {
            if (this.qty != o.qty) return this.qty - o.qty;
            return o.order - this.order; // order大先pop掉，保留前面的
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        PriorityQueue<Item> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            pq.offer(new Item(name, qty, i));
            if (pq.size() > k) pq.poll();
        }
        List<Item> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            ans.add(pq.poll());
        }
        // 從高到低排序，數值大者在前，數值相同時order小者先
        ans.sort((a, b) -> b.qty != a.qty
                ? b.qty - a.qty
                : a.order - b.order);

        for (Item item : ans) {
            System.out.println(item.name + " " + item.qty);
        }
    }
}

/*
 * Time Complexity: O(n log k)
 * 說明：依序將 n 個商品插入大小為 k 的 Min-Heap，
 * 每次插入與移除維持 O(log k)，總體複雜度為 O(n log k)。
 */




