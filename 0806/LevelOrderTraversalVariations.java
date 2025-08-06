

import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 1. 每一層分別儲存到不同的List
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    // 2. 之字形層序走訪
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (leftToRight) {
                    level.add(node.val);
                } else {
                    level.add(0, node.val); // 插在最前 形成反向
                }
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            result.add(level);
            leftToRight = !leftToRight;
        }
        return result;
    }

    // 3. 只列印每層的最後一個節點
    public static List<Integer> lastNodesPerLevel(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
                if (i == size - 1) result.add(node.val); // 此層最後一個
            }
        }
        return result;
    }

    // 4. 垂直層序走訪（Vertical Order Traversal）
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        // Map<column, List<value>>
        TreeMap<Integer, List<Integer>> columnMap = new TreeMap<>();
        Queue<TreeNode> nodeQ = new LinkedList<>();
        Queue<Integer> colQ = new LinkedList<>();
        nodeQ.offer(root);
        colQ.offer(0);
        while (!nodeQ.isEmpty()) {
            TreeNode node = nodeQ.poll();
            int col = colQ.poll();
            columnMap.computeIfAbsent(col, x -> new ArrayList<>()).add(node.val);
            if (node.left != null) {
                nodeQ.offer(node.left); colQ.offer(col - 1);
            }
            if (node.right != null) {
                nodeQ.offer(node.right); colQ.offer(col + 1);
            }
        }
        for (List<Integer> colNodes : columnMap.values()) {
            result.add(colNodes);
        }
        return result;
    }

    // ====== 主程式示範 ======
    public static void main(String[] args) {
        /*
                1
               / \
              2   3
             / \   \
            4   5   6
                 \
                  7
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.right.right = new TreeNode(7);

        // 每層分組
        System.out.println("層序分組: " + levelOrder(root));

        // 之字型
        System.out.println("之字形層序: " + zigzagLevelOrder(root));

        // 每層最後一個節點
        System.out.println("每層最後節點: " + lastNodesPerLevel(root));

        // 垂直層序
        System.out.println("垂直層序分組: " + verticalOrder(root));
    }
}


