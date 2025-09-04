public class it_25_Reverse_Nodes_in_k_Group {
    // 逆轉鏈表中每k個節點一組
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy, nex = dummy, pre = dummy;

        // 計算節點總數
        int count = 0;
        while(cur.next != null) {
            cur = cur.next;
            count++;
        }

        // 按組逆轉
        while(count >= k) {
            cur = pre.next;
            nex = cur.next;
            for(int i = 1; i < k; i++) {
                cur.next = nex.next;
                nex.next = pre.next;
                pre.next = nex;
                nex = cur.next;
            }
            pre = cur;
            count -= k;
        }
        return dummy.next;
    }
}

class ListNode {
    int val; ListNode next;
    ListNode(int x) { val = x; }
}

