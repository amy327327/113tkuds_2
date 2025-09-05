import java.util.Scanner;
public class LC19_RemoveNth_Node_Clinic {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int v){val=v;}
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for(int i=0; i<n; i++){
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }
        int k = sc.nextInt();

        dummy.next = removeNthFromEnd(dummy.next, k);

        cur = dummy.next;
        while(cur != null){
            System.out.print(cur.val);
            cur = cur.next;
            if(cur != null) System.out.print(" ");
        }
    }

    static ListNode removeNthFromEnd(ListNode head, int n){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        for(int i=0; i<n; i++) fast = fast.next;

        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
// 時間複雜度 O(n)，空間 O(1)

