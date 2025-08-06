

import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 1. 根據前序(preorder)和中序(inorder)走訪重建二元樹
    public static TreeNode buildTreeFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return buildPreIn(preorder, 0, preorder.length -1, inorder, 0, inorder.length -1, inorderIndexMap);
    }

    private static TreeNode buildPreIn(int[] preorder, int preStart, int preEnd,
                                       int[] inorder, int inStart, int inEnd,
                                       Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) return null;
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = inMap.get(rootVal);
        int leftTreeSize = rootIndex - inStart;
        root.left = buildPreIn(preorder, preStart + 1, preStart + leftTreeSize, inorder, inStart, rootIndex - 1, inMap);
        root.right = buildPreIn(preorder, preStart + leftTreeSize + 1, preEnd, inorder, rootIndex + 1, inEnd, inMap);
        return root;
    }

    // 2. 根據後序(postorder)和中序(inorder)走訪重建二元樹
    public static TreeNode buildTreeFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return buildPostIn(postorder, 0, postorder.length -1, inorder, 0, inorder.length -1, inorderIndexMap);
    }

    private static TreeNode buildPostIn(int[] postorder, int postStart, int postEnd,
                                        int[] inorder, int inStart, int inEnd,
                                        Map<Integer, Integer> inMap) {
        if (postStart > postEnd || inStart > inEnd) return null;
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = inMap.get(rootVal);
        int leftTreeSize = rootIndex - inStart;
        root.left = buildPostIn(postorder, postStart, postStart + leftTreeSize - 1, inorder, inStart, rootIndex - 1, inMap);
        root.right = buildPostIn(postorder, postStart + leftTreeSize, postEnd -1, inorder, rootIndex + 1, inEnd, inMap);
        return root;
    }

    // 3. 根據層序走訪重建完全二元樹
    public static TreeNode buildCompleteTreeFromLevelOrder(int[] levelOrder) {
        if (levelOrder.length == 0) return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode current = queue.poll();
            // 左子樹
            if (i < levelOrder.length) {
                current.left = new TreeNode(levelOrder[i++]);
                queue.offer(current.left);
            }
            // 右子樹
            if (i < levelOrder.length) {
                current.right = new TreeNode(levelOrder[i++]);
                queue.offer(current.right);
            }
        }
        return root;
    }

    // 4. 驗證重建的樹是否正確（比較兩棵樹是否相同結構和值）
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // 輔助函式：中序走訪樹並回傳結果串列
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(TreeNode node, List<Integer> res) {
        if (node == null) return;
        inorderHelper(node.left, res);
        res.add(node.val);
        inorderHelper(node.right, res);
    }

    // 輔助函式：前序走訪樹並回傳結果串列
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root,result);
        return result;
    }

    private static void preorderHelper(TreeNode node, List<Integer> res) {
        if (node == null) return;
        res.add(node.val);
        preorderHelper(node.left,res);
        preorderHelper(node.right,res);
    }

    // 輔助函式：後序走訪樹並回傳結果串列
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root,result);
        return result;
    }

    private static void postorderHelper(TreeNode node, List<Integer> res) {
        if (node == null) return;
        postorderHelper(node.left,res);
        postorderHelper(node.right,res);
        res.add(node.val);
    }

    // 輔助函式：層序走訪並回傳結果串列
    public static List<Integer> levelOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            result.add(node.val);
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
        return result;
    }

    // 範例示範 main
    public static void main(String[] args) {
        // 範例前序與中序，用於重建
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode rootFromPreIn = buildTreeFromPreIn(preorder, inorder);
        System.out.println("重建樹 - 前中序驗證：");
        System.out.println("前序: " + preorderTraversal(rootFromPreIn));
        System.out.println("中序: " + inorderTraversal(rootFromPreIn));

        // 範例後序與中序，用於重建
        int[] postorder = {9,15,7,20,3};
        TreeNode rootFromPostIn = buildTreeFromPostIn(postorder, inorder);
        System.out.println("\n重建樹 - 後中序驗證：");
        System.out.println("後序: " + postorderTraversal(rootFromPostIn));
        System.out.println("中序: " + inorderTraversal(rootFromPostIn));

        // 範例層序 (完全二元樹)
        int[] levelOrder = {1, 2, 3, 4, 5, 6};
        TreeNode rootFromLevel = buildCompleteTreeFromLevelOrder(levelOrder);
        System.out.println("\n重建樹 - 層序走訪結果：");
        System.out.println("層序: " + levelOrderTraversal(rootFromLevel));
        System.out.println("中序: " + inorderTraversal(rootFromLevel));

        // 驗證兩棵由前中序與後中序重建的樹是否相同
        System.out.println("\n兩棵樹是否相同: " + isSameTree(rootFromPreIn, rootFromPostIn));

        // 驗證層序重建的樹是否為完整的完全二元樹（示範，這裡直接用之前曾做過的類似方法）
        System.out.println("根據層序重建的是否為完全二元樹: " + isCompleteBinaryTree(rootFromLevel));
    }

    // 簡單判斷是否為完全二元樹（可重用之前的練習）
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean mustBeLeaf = false;
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
}

