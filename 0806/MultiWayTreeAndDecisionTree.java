

import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // 多路樹節點定義，使用 List<TreeNode> 儲存任意子節點
    static class TreeNode {
        String val;
        List<TreeNode> children;

        TreeNode(String val) {
            this.val = val;
            children = new ArrayList<>();
        }

        // 添加子節點
        public void addChild(TreeNode child) {
            children.add(child);
        }

        // 取得度數（子節點數量）
        public int getDegree() {
            return children.size();
        }
    }

    // 多路樹深度優先搜尋（DFS）
    public static void dfs(TreeNode root) {
        if (root == null) return;
        System.out.println("節點：" + root.val + "，度數：" + root.getDegree());
        for (TreeNode child : root.children) {
            dfs(child);
        }
    }

    // 多路樹廣度優先搜尋（BFS）
    public static void bfs(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println("節點：" + node.val + "，度數：" + node.getDegree());
            for (TreeNode child : node.children) {
                queue.offer(child);
            }
        }
    }

    // 計算多路樹高度（從 root 開始）
    public static int height(TreeNode root) {
        if (root == null) return 0;
        int maxHeight = 0;
        for (TreeNode child : root.children) {
            maxHeight = Math.max(maxHeight, height(child));
        }
        return maxHeight + 1;
    }

    // ====== 簡單模擬猜數字決策樹 =======
    // 範例：節點代表問題或答案，葉節點代表猜的數字，內部節點代表提問的問題
    static class DecisionTreeNode {
        String questionOrAnswer;
        Map<String, DecisionTreeNode> branches; // 決策分支，key為答案(如 "是","否")

        DecisionTreeNode(String val) {
            questionOrAnswer = val;
            branches = new LinkedHashMap<>();
        }

        public void addBranch(String answer, DecisionTreeNode node) {
            branches.put(answer, node);
        }

        public boolean isLeaf() {
            return branches.isEmpty();
        }
    }

    // 執行決策樹，從根節點開始，透過 Scanner 與使用者互動
    public static void runDecisionTree(DecisionTreeNode root) {
        Scanner sc = new Scanner(System.in);
        DecisionTreeNode current = root;
        while (true) {
            if (current.isLeaf()) {
                System.out.println("猜測結果：" + current.questionOrAnswer);
                break;
            }
            System.out.println(current.questionOrAnswer + "？（請輸入回答文字）");
            // 顯示可接受回答選項
            System.out.println("選項：" + current.branches.keySet());
            String input = sc.nextLine().trim();
            if (current.branches.containsKey(input)) {
                current = current.branches.get(input);
            } else {
                System.out.println("無效的回答，請重試。");
            }
        }
        // sc.close();  // 不關閉Scanner避免影響System.in
    }

    // 範例建立多路樹與決策樹
    public static void main(String[] args) {
        System.out.println("=== 多路樹示範 ===");
        TreeNode root = new TreeNode("根節點");

        TreeNode child1 = new TreeNode("子節點1");
        TreeNode child2 = new TreeNode("子節點2");
        TreeNode child3 = new TreeNode("子節點3");

        TreeNode grandChild11 = new TreeNode("孫節點1-1");
        TreeNode grandChild12 = new TreeNode("孫節點1-2");
        child1.addChild(grandChild11);
        child1.addChild(grandChild12);

        TreeNode grandChild21 = new TreeNode("孫節點2-1");
        child2.addChild(grandChild21);

        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);

        System.out.println("\n深度優先搜尋 (DFS) 結果：");
        dfs(root);

        System.out.println("\n廣度優先搜尋 (BFS) 結果：");
        bfs(root);

        System.out.println("\n多路樹高度: " + height(root));

        System.out.println("\n=== 決策樹猜數字遊戲示範 ===");
        DecisionTreeNode dtRoot = new DecisionTreeNode("這個數字大於50嗎");
        DecisionTreeNode yesNode = new DecisionTreeNode("這個數字大於75嗎");
        DecisionTreeNode noNode = new DecisionTreeNode("這個數字小於20嗎");

        dtRoot.addBranch("是", yesNode);
        dtRoot.addBranch("否", noNode);

        yesNode.addBranch("是", new DecisionTreeNode("是90"));
        yesNode.addBranch("否", new DecisionTreeNode("是60"));

        noNode.addBranch("是", new DecisionTreeNode("是10"));
        noNode.addBranch("否", new DecisionTreeNode("是30"));

        System.out.println("請回答問題，引導猜測數字：");
        runDecisionTree(dtRoot);
    }
}

