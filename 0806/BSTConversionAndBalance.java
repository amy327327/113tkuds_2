

import java.util.*;

public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    // ----------------------
    // 1. 將BST轉換為排序的雙向鏈結串列（in-order doubly linked list）
    // 利用中序遍歷，調整節點的 left 指向前驅，right 指向後繼
    private TreeNode head = null;  // 雙向鏈結串列頭
    private TreeNode prev = null;  // 前一節點
    public TreeNode bstToDoublyLinkedList(TreeNode root) {
        head = null;
        prev = null;
        inorderConvert(root);
        return head;
    }

    private void inorderConvert(TreeNode node) {
        if (node == null) return;
        inorderConvert(node.left);

        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;

        inorderConvert(node.right);
    }

    // ----------------------
    // 2. 將排序陣列轉換為平衡BST (中間值為根，分治法建立)
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return buildBSTFromSortedArray(nums, 0, nums.length - 1);
    }

    private TreeNode buildBSTFromSortedArray(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBSTFromSortedArray(nums, left, mid - 1);
        root.right = buildBSTFromSortedArray(nums, mid + 1, right);
        return root;
    }

    // ----------------------
    // 3. 檢查BST是否平衡並計算平衡因子（左右子樹高度差）
    // 返回平衡狀態，同時用helper回傳高度
    public boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }

    // 回傳節點高度，若不平衡回傳 -1
    private int checkBalance(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = checkBalance(node.left);
        if (leftHeight == -1) return -1;
        int rightHeight = checkBalance(node.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        // 可計算並印出平衡因子 (height difference)
        int balanceFactor = leftHeight - rightHeight;
        // System.out.printf("節點 %d 的平衡因子: %d\n", node.val, balanceFactor);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 如需取得每個節點的平衡因子，可以用以下方法生成 Map
    public Map<TreeNode, Integer> getBalanceFactors(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        computeBalanceFactors(root, map);
        return map;
    }

    private int computeBalanceFactors(TreeNode node, Map<TreeNode, Integer> map) {
        if (node == null) return 0;
        int leftHeight = computeBalanceFactors(node.left, map);
        int rightHeight = computeBalanceFactors(node.right, map);
        map.put(node, leftHeight - rightHeight);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // ----------------------
    // 4. 將 BST 中每個節點值改為所有 >= 該節點值的總和 （Greater Tree）
    // 使用反中序遍歷累加
    private int sumAccum = 0;
    public void convertBSTtoGreaterTree(TreeNode root) {
        sumAccum = 0;
        reverseInorder(root);
    }

    private void reverseInorder(TreeNode node) {
        if (node == null) return;
        reverseInorder(node.right);
        sumAccum += node.val;
        node.val = sumAccum;
        reverseInorder(node.left);
    }

    // ----------------------
    // 輔助方法: 中序走訪印出BST節點
    public void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    // 輔助：印出雙向鏈結串列（從head開始用right指標往後列印）
    public void printDoublyLinkedList(TreeNode head) {
        TreeNode curr = head;
        System.out.print("雙向鏈結串列: ");
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
        System.out.println();
    }

    // ----------------------
    // 範例主程式示範上述各項功能
    public static void main(String[] args) {
        BSTConversionAndBalance bstUtil = new BSTConversionAndBalance();

        // 建立範例BST
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(10);

        System.out.print("原BST中序: ");
        bstUtil.inorderPrint(root);
        System.out.println();

        // 1. BST轉雙向雙向鏈結串列
        TreeNode dllHead = bstUtil.bstToDoublyLinkedList(root);
        bstUtil.printDoublyLinkedList(dllHead);

        // 2. 排序陣列轉平衡BST
        int[] sortedArr = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balancedRoot = bstUtil.sortedArrayToBST(sortedArr);
        System.out.print("排序陣列轉換平衡BST中序: ");
        bstUtil.inorderPrint(balancedRoot);
        System.out.println();

        // 3. 檢查BST是否平衡並印出平衡因子
        boolean balanced = bstUtil.isBalanced(balancedRoot);
        System.out.println("BST是否平衡: " + (balanced ? "是" : "否"));

        Map<TreeNode, Integer> balanceFactors = bstUtil.getBalanceFactors(balancedRoot);
        System.out.println("各節點平衡因子：");
        for (Map.Entry<TreeNode, Integer> entry : balanceFactors.entrySet()) {
            System.out.printf("節點 %d -> 平衡因子: %d\n", entry.getKey().val, entry.getValue());
        }

        // 4. 將BST轉換為Greater Tree
        System.out.print("原BST中序（用於轉Greater Tree）: ");
        bstUtil.inorderPrint(root);
        System.out.println();

        bstUtil.convertBSTtoGreaterTree(root);
        System.out.print("轉換後Greater Tree中序: ");
        bstUtil.inorderPrint(root);
        System.out.println();
    }
}

