

import java.util.*;

public class BSTRangeQuerySystem {

    // BST節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 新增節點 (BST插入)
    public TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    // 範圍查詢，回傳List包含所有值在 [min, max] 範圍的節點值 (中序遍歷)
    public List<Integer> rangeQuery(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    private void rangeQueryHelper(TreeNode node, int min, int max, List<Integer> result) {
        if (node == null) return;
        if (node.val > min) rangeQueryHelper(node.left, min, max, result);
        if (node.val >= min && node.val <= max) result.add(node.val);
        if (node.val < max) rangeQueryHelper(node.right, min, max, result);
    }

    // 範圍計數：計算 [min, max] 範圍內節點個數
    public int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeCount(root.right, min, max);
        if (root.val > max) return rangeCount(root.left, min, max);
        // 範圍內才計數
        return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    // 範圍總和：計算 [min, max] 範圍內節點值加總
    public int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeSum(root.right, min, max);
        if (root.val > max) return rangeSum(root.left, min, max);
        return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    // 最接近查詢：找出節點值最接近target的節點
    public int closestValue(TreeNode root, int target) {
        TreeNode cur = root;
        int closest = root.val;
        while (cur != null) {
            if (Math.abs(cur.val - target) < Math.abs(closest - target)) {
                closest = cur.val;
            }
            if (target < cur.val) cur = cur.left;
            else if (target > cur.val) cur = cur.right;
            else break;  // 完全相等，直接回傳
        }
        return closest;
    }

    // 示範建立BST並測試功能
    public static void main(String[] args) {
        BSTRangeQuerySystem bstSystem = new BSTRangeQuerySystem();

        // 範例插入節點
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        TreeNode root = null;
        for (int val : values) {
            root = bstSystem.insert(root, val);
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== BST 範圍查詢系統選單 ===");
            System.out.println("1. 範圍查詢");
            System.out.println("2. 範圍計數");
            System.out.println("3. 範圍總和");
            System.out.println("4. 最接近查詢");
            System.out.println("0. 離開");
            System.out.print("請選擇功能：");
            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("程式結束。");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("輸入最小值 min：");
                    int min = sc.nextInt();
                    System.out.print("輸入最大值 max：");
                    int max = sc.nextInt();
                    List<Integer> rangeList = bstSystem.rangeQuery(root, min, max);
                    System.out.println("範圍內節點值：" + rangeList);
                    break;
                case 2:
                    System.out.print("輸入最小值 min：");
                    min = sc.nextInt();
                    System.out.print("輸入最大值 max：");
                    max = sc.nextInt();
                    int count = bstSystem.rangeCount(root, min, max);
                    System.out.println("範圍內節點數量：" + count);
                    break;
                case 3:
                    System.out.print("輸入最小值 min：");
                    min = sc.nextInt();
                    System.out.print("輸入最大值 max：");
                    max = sc.nextInt();
                    int sum = bstSystem.rangeSum(root, min, max);
                    System.out.println("範圍內節點值總和：" + sum);
                    break;
                case 4:
                    System.out.print("輸入查詢目標值：");
                    int target = sc.nextInt();
                    int closest = bstSystem.closestValue(root, target);
                    System.out.println("最接近 " + target + " 的節點值為：" + closest);
                    break;
                default:
                    System.out.println("無效選項，請重新輸入。");
            }
        }

        sc.close();
    }
}

