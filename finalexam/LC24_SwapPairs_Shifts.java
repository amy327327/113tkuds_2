import java.util.Scanner;
public class LC24_SwapPairs_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v){ val = v;}
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ListNode head = buildList(sc);
        head = swapPairs(head);
        printList(head);
    }

    static ListNode buildList(Scanner sc){
        ListNode dummy = new ListNode(0), tail=dummy;
        while(sc.hasNextInt()){
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }
        return dummy.next;
    }

    static ListNode swapPairs(ListNode head){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while(prev.next != null && prev.next.next != null){
            ListNode a = prev.next, b = a.next;
            prev.next = b;
            a.next = b.next;
            b.next = a;
            prev = a;
        }
        return dummy.next;
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

