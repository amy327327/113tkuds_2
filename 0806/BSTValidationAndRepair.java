

import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    /** 1. 驗證是否為有效BST (遞迴法) */
    public static boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }
    private static boolean validate(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max))
            return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    /** 2. 找出 BST 中不符合規則的節點（給出所有違規節點的值） */
    public static List<TreeNode> findInvalidNodes(TreeNode root) {
        List<TreeNode> invalids = new ArrayList<>();
        findInvalidNodes(root, null, null, invalids);
        return invalids;
    }
    private static void findInvalidNodes(TreeNode node, Integer min, Integer max, List<TreeNode> res) {
        if (node == null) return;
        boolean invalid = (min != null && node.val <= min) || (max != null && node.val >= max);
        if (invalid) res.add(node);
        findInvalidNodes(node.left, min, node.val, res);
        findInvalidNodes(node.right, node.val, max, res);
    }

    /** 3. 修復有兩個節點錯位的 BST（如 leetcode 99） */
    public static void recoverBST(TreeNode root) {
        TreeNode[] prev = new TreeNode[1];
        TreeNode[] swap = new TreeNode[2];
        inorderRecover(root, prev, swap);
        if (swap[0] != null && swap[1] != null) {
            int tmp = swap[0].val;
            swap[0].val = swap[1].val;
            swap[1].val = tmp;
        }
    }
    private static void inorderRecover(TreeNode node, TreeNode[] prev, TreeNode[] swap) {
        if (node == null) return;
        inorderRecover(node.left, prev, swap);
        if (prev[0] != null && prev[0].val > node.val) {
            if (swap[0] == null) { swap[0] = prev[0]; }
            swap[1] = node;
        }
        prev[0] = node;
        inorderRecover(node.right, prev, swap);
    }

    /** 4. 最少移除幾個節點才能變成BST（型同 最長遞增子序列 LIS 的做法 in-order 上） */
    public static int minRemovalToBST(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        inOrder(root, values);
        int lis = lengthOfLIS(values);
        return values.size() - lis;
    }
    private static void inOrder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inOrder(node.left, values);
        values.add(node.val);
        inOrder(node.right, values);
    }
    // LIS: 最長遞增子序列長度
    private static int lengthOfLIS(List<Integer> arr) {
        List<Integer> dp = new ArrayList<>();
        for (int v : arr) {
            int idx = Collections.binarySearch(dp, v);
            if (idx < 0) idx = -idx - 1;
            if (idx == dp.size()) dp.add(v);
            else dp.set(idx, v);
        }
        return dp.size();
    }

    // ========= 示範用 main =========
    public static void main(String[] args) {
        // 範例: 有兩個錯位節點的BST
        /*
                 3
                / \
               1   4
                  /
                 2   (2和3交換)
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        // 驗證 BST
        System.out.println("是否為BST: " + isValidBST(root));

        // 找出不合法節點
        List<TreeNode> bad = findInvalidNodes(root);
        System.out.print("不合法節點值: ");
        for (TreeNode t : bad) System.out.print(t.val + " ");
        System.out.println();

        // 修復錯位節點
        recoverBST(root);
        System.out.print("修復後 (中序): ");
        printInOrder(root);
        System.out.println("\n是否為BST: " + isValidBST(root));

        // 範例：需移除幾個節點才能成為BST
        // e.g. 節點 [5,1,4,null,null,3,6] → in-order [1,5,3,4,6]，要拿掉3、4才能成BST（或其他組合），最少刪2個
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);

        System.out.print("原樹中序: ");
        printInOrder(root2); System.out.println();
        int minRemove = minRemovalToBST(root2);
        System.out.println("最少需移除節點數: " + minRemove);
    }

    // 輔助：印中序
    private static void printInOrder(TreeNode node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }
}

