
import java.util.Scanner;

public class M08_BSTRangedSum {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
        }
        int L = sc.nextInt();
        int R = sc.nextInt();

        TreeNode root = buildTree(vals);
        int sum = rangeSumBST(root, L, R);
        System.out.println("Sum: " + sum);
    }

    private static TreeNode buildTree(int[] vals) {
        if (vals.length == 0 || vals[0] == -1) return null;

        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < vals.length) {
            TreeNode curr = q.poll();
            if (curr == null) continue;

            if (i < vals.length && vals[i] != -1) {
                curr.left = new TreeNode(vals[i]);
                q.offer(curr.left);
            }
            i++;

            if (i < vals.length && vals[i] != -1) {
                curr.right = new TreeNode(vals[i]);
                q.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    private static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        } else if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        } else {
            return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
        }
    }
}

