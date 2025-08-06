
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BSTKthElement {

    static class TreeNode {
        int val;
        TreeNode left, right;
        int size; // 節點子樹大小（含自身）

        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }

        // 更新節點大小（左子樹大小 + 右子樹大小 + 1）
        void updateSize() {
            int leftSize = (left != null) ? left.size : 0;
            int rightSize = (right != null) ? right.size : 0;
            this.size = leftSize + rightSize + 1;
        }
    }

    private TreeNode root;

    // 插入節點，並更新 size
    public TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) {
            node.left = insert(node.left, val);
        } else {
            node.right = insert(node.right, val);
        }
        node.updateSize();
        return node;
    }

    // 刪除節點，並更新 size
    public TreeNode delete(TreeNode node, int val) {
        if (node == null) return null;
        if (val < node.val) {
            node.left = delete(node.left, val);
        } else if (val > node.val) {
            node.right = delete(node.right, val);
        } else {
            // 找到節點刪除
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // 找右子樹最小節點替代
            TreeNode minNode = findMin(node.right);
            node.val = minNode.val;
            node.right = delete(node.right, minNode.val);
        }
        node.updateSize();
        return node;
    }

    // 找右子樹最小節點
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // 找第 k 小元素，假設 1-based index，node != null，k合法
    public int kthSmallest(TreeNode node, int k) {
        int leftSize = (node.left != null) ? node.left.size : 0;
        if (k == leftSize + 1) return node.val;
        else if (k <= leftSize) return kthSmallest(node.left, k);
        else return kthSmallest(node.right, k - leftSize - 1);
    }

    // 找第 k 大元素，轉成找第 (size - k + 1) 小元素
    public int kthLargest(TreeNode node, int k) {
        if (node == null) throw new IllegalArgumentException("樹結點為空");
        int n = node.size;
        if (k < 1 || k > n)
            throw new IllegalArgumentException("k 超出範圍");
        return kthSmallest(node, n - k + 1);
    }

    // 找出第 k 小到第 j 小之間所有元素，包含 k 和 j
    public List<Integer> rangeKthElements(int k, int j) {
        if (root == null) return Collections.emptyList();
        int n = root.size;
        if (k < 1 || j < k || j > n) {
            throw new IllegalArgumentException("k 或 j 超出範圍");
        }
        List<Integer> res = new ArrayList<>();
        collectKthRange(root, k, j, res);
        return res;
    }

    // 輔助：以中序遞迴收集 [k,j] 範圍的第 k 到第 j 小元素
    private int currentRank = 0; // 外部使用前請重置或使用 wrapper 函式
    private void collectKthRange(TreeNode node, int k, int j, List<Integer> res) {
        if (node == null) return;
        collectKthRange(node.left, k, j, res);
        currentRank++;
        if (currentRank >= k && currentRank <= j) {
            res.add(node.val);
        }
        if (currentRank > j) return; // 早停優化
        collectKthRange(node.right, k, j, res);
    }

    // public wrapper 用於收集範圍元素並重置狀態
    public List<Integer> getRangeKthElements(int k, int j) {
        currentRank = 0;
        return rangeKthElementsWithReset(k, j);
    }

    // 實作範圍收集並重置 currentRank
    private List<Integer> rangeKthElementsWithReset(int k, int j) {
        List<Integer> res = new ArrayList<>();
        collectKthRange(root, k, j, res);
        return res;
    }

    // ----- 外部呼叫用的插入 / 刪除  -----

    public void insert(int val) {
        root = insert(root, val);
    }

    public void delete(int val) {
        root = delete(root, val);
    }

    public int kthSmallest(int k) {
        if (root == null) throw new IllegalStateException("BST為空");
        if (k < 1 || k > root.size)
            throw new IllegalArgumentException("k 超出範圍");
        return kthSmallest(root, k);
    }

    public int kthLargest(int k) {
        if (root == null) throw new IllegalStateException("BST為空");
        if (k < 1 || k > root.size)
            throw new IllegalArgumentException("k 超出範圍");
        return kthLargest(root, k);
    }

    public List<Integer> rangeKthElementsWrapper(int k, int j) {
        if (root == null) return Collections.emptyList();
        if (k < 1 || j < k || j > root.size)
            throw new IllegalArgumentException("k 或 j 超出範圍");
        return getRangeKthElements(k, j);
    }

    // 範例展示
    public static void main(String[] args) {
        BSTKthElement bst = new BSTKthElement();

        // 插入元素
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        for (int v : values) bst.insert(v);

        // 查詢第 k 小元素
        System.out.println("第 3 小元素: " + bst.kthSmallest(3)); // 15

        // 查詢第 k 大元素
        System.out.println("第 2 大元素: " + bst.kthLargest(2)); // 30

        // 查詢第 k 小到第 j 小之間的元素
        List<Integer> rangeList = bst.rangeKthElementsWrapper(2, 5);
        System.out.println("第 2 小到第 5 小元素: " + rangeList); // [10,15,20,25]

        // 動態插入刪除測試
        bst.insert(12);
        System.out.println("插入12後第 4 小元素: " + bst.kthSmallest(4)); // 15

        bst.delete(10);
        System.out.println("刪除10後第 3 小元素: " + bst.kthSmallest(3)); // 12

        // 印出整棵樹大小表示動態更新正確
        System.out.println("BST節點數: " + bst.root.size);
    }
}


