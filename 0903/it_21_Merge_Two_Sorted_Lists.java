public class it_21_Merge_Two_Sorted_Lists {
    // 合併兩個已排序鏈表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        // 比較 l1 與 l2 當前節點，接到新鏈表尾部
        while (l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        // 接剩下未合併部分
        current.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}

class ListNode {
    int val; ListNode next;
    ListNode(int x) { val = x; }
}

