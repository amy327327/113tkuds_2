import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LC23_MergeKLists_Hospitals {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v){ val = v;}
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a->a.val));
        for(int i=0; i<k; i++){
            ListNode head = readList(sc);
            if(head != null) pq.offer(head);
        }

        ListNode dummy = new ListNode(0), tail = dummy;
        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            if(node.next != null) pq.offer(node.next);
        }

        printList(dummy.next);
    }

    static ListNode readList(Scanner sc){
        ListNode dummy = new ListNode(0), tail = dummy;
        while(true){
            int v = sc.nextInt();
            if(v == -1) break;
            tail.next = new ListNode(v);
            tail = tail.next;
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
// 複雜度 O(N log k)

