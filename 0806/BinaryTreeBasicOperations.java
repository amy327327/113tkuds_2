

import java.util.*;

public class BinaryTreeBasicOperations {

    // 節點類別
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 計算所有節點值的總和與平均值
    public static int sumNodes(TreeNode root) {
        if (root == null) return 0;
        return root.val + sumNodes(root.left) + sumNodes(root.right);
    }

    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public static double averageNodes(TreeNode root) {
        int sum = sumNodes(root);
        int count = countNodes(root);
        if (count == 0) return 0;
        return (double) sum / count;
    }

    // 2. 找出樹中的最大值節點
    public static int maxNodeValue(TreeNode root) {
        if (root == null) 
            return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(maxNodeValue(root.left), maxNodeValue(root.right)));
    }

    // 找出樹中的最小值節點
    public static int minNodeValue(TreeNode root) {
        if (root == null) 
            return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(minNodeValue(root.left), minNodeValue(root.right)));
    }

    // 3. 計算樹的寬度 (每層節點數的最大值)
    public static int maxWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            maxWidth = Math.max(maxWidth, size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return maxWidth;
    }

    // 4. 判斷是否為完全二元樹
    // 定義：完全二元樹是除了最後一層之外，每一層節點都是滿的，且最後一層節點都靠左排列
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean mustBeLeaf = false;  // 是否之後節點必須為葉節點
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                if (mustBeLeaf) return false;
                queue.offer(node.left);
            } else {
                mustBeLeaf = true;
            }

            if (node.right != null) {
                if (mustBeLeaf) return false;
                queue.offer(node.right);
            } else {
                mustBeLeaf = true;
            }
        }
        return true;
    }

    // 示範建立一棵簡單的二元樹
    private static TreeNode sampleTree() {
        /*
              10
             /  \
            5    20
           / \     \
          3   7     30
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(30);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = sampleTree(); // 你也可自行建立或讀入樹的節點

        int sum = sumNodes(root);
        int count = countNodes(root);
        double avg = averageNodes(root);
        int maxVal = maxNodeValue(root);
        int minVal = minNodeValue(root);
        int width = maxWidth(root);
        boolean isComplete = isCompleteBinaryTree(root);

        System.out.println("節點值總和: " + sum);
        System.out.println("節點數量: " + count);
        System.out.printf("節點值平均值: %.2f\n", avg);
        System.out.println("最大節點值: " + maxVal);
        System.out.println("最小節點值: " + minVal);
        System.out.println("二元樹最大寬度: " + width);
        System.out.println("是否為完全二元樹: " + (isComplete ? "是" : "否"));
    }
}

