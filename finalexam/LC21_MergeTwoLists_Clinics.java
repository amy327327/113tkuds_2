import java.util.Scanner;
public class LC21_MergeTwoLists_Clinics {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v){ val = v; }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        ListNode l1 = buildList(sc, n);
        ListNode l2 = buildList(sc, m);

        ListNode merged = mergeTwoLists(l1, l2);

        printList(merged);
    }

    static ListNode buildList(Scanner sc, int size){
        ListNode dummy = new ListNode(0), tail = dummy;
        for(int i=0; i<size; i++){
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }
        return dummy.next;
    }

    static ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0), tail = dummy;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = (l1 != null)? l1 : l2;
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
// 複雜度 O(n+m)

