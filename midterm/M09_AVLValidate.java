
import java.util.*;

public class M09_AVLValidate {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static class Result {
        boolean isBST;
        boolean isAVL;
        int height;
        int minVal;
        int maxVal;

        Result(boolean isBST, boolean isAVL, int height, int minVal, int maxVal) {
            this.isBST = isBST;
            this.isAVL = isAVL;
            this.height = height;
            this.minVal = minVal;
            this.maxVal = maxVal;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
        }

        TreeNode root = buildTree(vals);
        Result res = validateAVL(root, Long.MIN_VALUE, Long.MAX_VALUE);
        if (!res.isBST) {
            System.out.println("Invalid BST");
        } else if (!res.isAVL) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
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
            } else {
                q.offer(null);
            }
            i++;

            if (i < vals.length && vals[i] != -1) {
                curr.right = new TreeNode(vals[i]);
                q.offer(curr.right);
            } else {
                q.offer(null);
            }
            i++;
        }
        return root;
    }

    private static Result validateAVL(TreeNode node, long min, long max) {
        if (node == null) 
            return new Result(true, true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        if (node.val <= min || node.val >= max) 
            return new Result(false, true, 0, 0, 0); // 不合法 BST，但 AVL 還算正常(不繼續檢查)

        Result left = validateAVL(node.left, min, node.val);
        Result right = validateAVL(node.right, node.val, max);

        boolean isBST = left.isBST && right.isBST;
        if (!isBST) return new Result(false, true, 0, 0, 0);

        boolean isAVL = left.isAVL && right.isAVL && Math.abs(left.height - right.height) <= 1;
        int height = 1 + Math.max(left.height, right.height);

        int minVal = node.left == null ? node.val : left.minVal;
        int maxVal = node.right == null ? node.val : right.maxVal;

        return new Result(isBST, isAVL, height, minVal, maxVal);
    }
}

/*
 * Time Complexity: O(n)
 * 說明：對每個節點遞迴檢查 BST 與 AVL，整棵樹遍歷一次，
 * 所以複雜度為 O(n)。
 */

