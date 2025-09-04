public class it_24_Swap_Nodes_in_Pairs {
    // 兩兩交換鏈表節點
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while(prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;

            // 交換操作
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // 更新 prev 指向下一組節點前的節點
            prev = first;
        }
        return dummy.next;
    }
}

class ListNode {
    int val; ListNode next;
    ListNode(int x) { val = x; }
}

