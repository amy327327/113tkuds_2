import java.util.PriorityQueue;

public class it_23_Merge_k_Sorted_Lists {
    // 使用優先隊列合併k個排好序的鏈表
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> a.val - b.val);
        for (ListNode node : lists) if(node != null) pq.offer(node);

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while(!pq.isEmpty()) {
            ListNode node = pq.poll();
            current.next = node;
            current = current.next;
            if(node.next != null) pq.offer(node.next);
        }
        return dummy.next;
    }
}

class ListNode {
    int val; ListNode next;
    ListNode(int x) { val = x; }
}

