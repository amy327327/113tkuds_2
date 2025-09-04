public class it_19_Remove_Nth_Node_From_End_of_List {
    // 主函式，刪除鏈表中倒數第n個節點
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // 虛擬頭節點處理邊界情況
        dummy.next = head;
        ListNode first = dummy, second = dummy;

        // 第一個指針先走 n+1 步，保持間距
        for (int i=0; i<=n; i++) first = first.next;

        // 兩指針同時往前走，直到第一指針到尾
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // 第二指針指向目標節點前一個，刪除目標節點
        second.next = second.next.next;

        return dummy.next; // 返回新頭節點
    }
}

// 鏈表節點定義
class ListNode {
    int val; ListNode next;
    ListNode(int x) { val = x; }
}
