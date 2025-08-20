import java.util.Scanner;

public class M07_BinaryTreeLeftView {
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

        TreeNode root = buildTree(vals);
        List<Integer> leftView = getLeftView(root);

        System.out.print("LeftView:");
        for (int v : leftView) {
            System.out.print(" " + v);
        }
        System.out.println();
    }

    private static TreeNode buildTree(int[] vals) {
        if (vals.length == 0 || vals[0] == -1) return null;

        TreeNode root = new TreeNode(vals);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < vals.length) {
            TreeNode curr = q.poll();
            if (curr == null) continue;

            // 左子節點
            if (i < vals.length && vals[i] != -1) {
                curr.left = new TreeNode(vals[i]);
                q.offer(curr.left);
            }
            i++;

            // 右子節點
            if (i < vals.length && vals[i] != -1) {
                curr.right = new TreeNode(vals[i]);
                q.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    private static List<Integer> getLeftView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i == 0) {
                    result.add(node.val);
                }
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }
        return result;
    }
}
