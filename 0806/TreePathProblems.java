

import java.util.*;

public class TreePathProblems {

    // 樹節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    // 1. 找出所有從根節點到葉節點的路徑（回傳 List<List<Integer>>）
    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        LinkedList<Integer> path = new LinkedList<>();
        dfsAllPaths(root, path, result);
        return result;
    }

    private static void dfsAllPaths(TreeNode node, LinkedList<Integer> path, List<List<Integer>> result) {
        path.add(node.val);

        if (node.left == null && node.right == null) {
            // 是葉節點，加入路徑結果
            result.add(new ArrayList<>(path));
        } else {
            if (node.left != null) dfsAllPaths(node.left, path, result);
            if (node.right != null) dfsAllPaths(node.right, path, result);
        }

        // 回溯
        path.removeLast();
    }

    // 2. 判斷是否存在和為目標值的根到葉路徑
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 3. 找出樹中和最大的根到葉路徑的和
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE; // 空樹
        if (root.left == null && root.right == null) return root.val;
        int leftMax = maxRootToLeafSum(root.left);
        int rightMax = maxRootToLeafSum(root.right);
        return root.val + Math.max(leftMax, rightMax);
    }

    // 4. 計算樹中任意兩節點間的最大路徑和（也稱樹的直徑，這裡是節點值和最大路徑）
    static int maxPathSumAns;

    public static int maxPathSum(TreeNode root) {
        maxPathSumAns = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return maxPathSumAns;
    }

    // Helper: 回傳經過此節點的最大「單邊」路徑和（左或右），並更新maxPathSumAns
    private static int maxPathSumHelper(TreeNode node) {
        if (node == null) return 0;
        // 可選擇不經過子樹(如果子樹路徑和為負數，則視為0)
        int left = Math.max(0, maxPathSumHelper(node.left));
        int right = Math.max(0, maxPathSumHelper(node.right));

        // 更新最大路徑和，此路徑通過node包含左右子樹
        int currentSum = node.val + left + right;
        maxPathSumAns = Math.max(maxPathSumAns, currentSum);

        // 回傳「單邊」路徑最大和，用於父節點計算
        return node.val + Math.max(left, right);
    }

    // ===== 範例主程式 =====
    public static void main(String[] args) {
        /*
              範例樹：
                  10
                 /  \
                5   -3
               / \    \
              3   2    11
             / \   \
            3  -2   1
         */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        // 1. 所有根至葉路徑
        System.out.println("所有根到葉路徑:");
        List<List<Integer>> paths = allRootToLeafPaths(root);
        for (List<Integer> p : paths) {
            System.out.println(p);
        }

        // 2. 是否有根到葉路徑和為 18
        int targetSum = 18;
        System.out.println("是否存在和為 " + targetSum + " 的根到葉路徑: " + hasPathSum(root, targetSum));

        // 3. 最大根到葉路徑和
        int maxSum = maxRootToLeafSum(root);
        System.out.println("最大根到葉路徑和: " + maxSum);

        // 4. 樹中任意兩點最大路徑和
        int maxPath = maxPathSum(root);
        System.out.println("任意兩節點間最大路徑和: " + maxPath);
    }
}

