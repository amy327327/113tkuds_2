

public class TreeMirrorAndSymmetry {

    // 二元樹節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 判斷是否為對稱樹（鏡像對稱）
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    // 判斷兩棵樹是否鏡像
    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    // 將一棵樹轉換為鏡像樹（原地修改）
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode leftMirrored = mirrorTree(root.left);
        TreeNode rightMirrored = mirrorTree(root.right);
        root.left = rightMirrored;
        root.right = leftMirrored;
        return root;
    }

    // 比較兩棵樹是否為互相鏡像
    public static boolean areMirrors(TreeNode root1, TreeNode root2) {
        return isMirror(root1, root2);
    }

    // 判斷是否為子樹
    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSameTree(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    // 判斷兩棵樹是否相同（值和結構皆相同）
    private static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    // 輔助：印出樹的中序遍歷
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // 範例主程式示範功能
    public static void main(String[] args) {
        /*
              建立範例樹：
                      1
                     / \
                    2   2
                   /     \
                  3       3
         */
        TreeNode symmetricRoot = new TreeNode(1);
        symmetricRoot.left = new TreeNode(2);
        symmetricRoot.right = new TreeNode(2);
        symmetricRoot.left.left = new TreeNode(3);
        symmetricRoot.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + (isSymmetric(symmetricRoot) ? "是" : "否"));

        System.out.print("原始樹中序遍歷: ");
        printInorder(symmetricRoot);
        System.out.println();

        mirrorTree(symmetricRoot);
        System.out.print("鏡像樹中序遍歷: ");
        printInorder(symmetricRoot);
        System.out.println();

        // 再建一個樹用來比較是否鏡像
        TreeNode tree1 = new TreeNode(4);
        tree1.left = new TreeNode(2);
        tree1.right = new TreeNode(7);

        TreeNode tree2 = new TreeNode(4);
        tree2.left = new TreeNode(7);
        tree2.right = new TreeNode(2);

        System.out.println("兩樹是否互為鏡像: " + (areMirrors(tree1, tree2) ? "是" : "否"));

        // 測試子樹判斷
        TreeNode s = new TreeNode(3);
        s.left = new TreeNode(4);
        s.right = new TreeNode(5);
        s.left.left = new TreeNode(1);
        s.left.right = new TreeNode(2);

        TreeNode t = new TreeNode(4);
        t.left = new TreeNode(1);
        t.right = new TreeNode(2);

        System.out.println("t 是否為 s 的子樹: " + (isSubtree(s, t) ? "是" : "否"));
    }
}

