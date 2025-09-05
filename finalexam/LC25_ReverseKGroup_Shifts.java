import java.util.Scanner;
public class LC25_ReverseKGroup_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v){ val = v;}
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        ListNode head = buildList(sc);

        head = reverseKGroup(head, k);
        printList(head);
    }

    static ListNode buildList(Scanner sc){
        ListNode dummy = new ListNode(0), tail = dummy;
        while(sc.hasNextInt()){
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }
        return dummy.next;
    }

    static ListNode reverseKGroup(ListNode head, int k){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while(true){
            ListNode kth = prevGroupEnd;
            for(int i=0; i<k; i++){
                kth = kth.next;
                if(kth == null) return dummy.next;
            }

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;

            // 反轉區間
            ListNode prev = nextGroupStart, curr = groupStart;

            while(curr != nextGroupStart){
                ListNode nxt = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nxt;
            }
            prevGroupEnd.next = kth;
            prevGroupEnd = groupStart;
        }
    }

    static void printList(ListNode head){
        ListNode cur = head;
        while(cur != null){
            System.out.print(cur.val);
            cur = cur.next;
            if(cur != null) System.out.print(" ");
        }
    }
}
// 複雜度 O(n)

