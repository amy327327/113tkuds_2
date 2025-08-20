
import java.util.Scanner;

public class M10_RBPropertiesCheck {
    static class Node {
        int val;
        char color; // 'B' or 'R'
        Node(int val, char color) {
            this.val = val;
            this.color = color;
        }
    }

    static int BLACK = 1, RED = 0;

    static boolean violationFound = false;
    static String violationMsg = "";
    static int violationIndex = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            String cStr = sc.next();
            char color = (val == -1) ? 'B' : cStr.charAt(0); // 空節點視為黑
            nodes[i] = new Node(val, color);
        }

        // 1) 根節點黑檢查
        if (nodes.length > 0 && nodes[0].val != -1 && nodes[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 2) 紅紅違規檢查
        for (int i = 0; i < n; i++) {
            if (nodes[i].val == -1) continue;
            if (nodes[i].color == 'R') {
                int left = 2*i+1;
                int right = 2*i+2;
                if (left < n && nodes[left].val != -1 && nodes[left].color == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
                if (right < n && nodes[right].val != -1 && nodes[right].color == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
            }
        }

        // 3) 黑高是否一致檢查（從根開始）
        int blackHeight = checkBlackHeight(nodes, 0);
        if (blackHeight == -1) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

    // 回傳此子樹黑高，錯誤回傳 -1
    private static int checkBlackHeight(Node[] nodes, int idx) {
        if (idx >= nodes.length || nodes[idx].val == -1) {
            return 1; // NIL節點視為黑高1
        }
        int left = 2*idx+1;
        int right = 2*idx+2;
        int leftBlack = checkBlackHeight(nodes, left);
        if (leftBlack == -1) return -1;
        int rightBlack = checkBlackHeight(nodes, right);
        if (rightBlack == -1) return -1;
        if (leftBlack != rightBlack) return -1;

        return leftBlack + (nodes[idx].color == 'B' ? 1 : 0);
    }
}

