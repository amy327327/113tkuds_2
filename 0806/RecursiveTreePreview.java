

import java.util.*;

public class RecursiveTreePreview {

    // 模擬檔案系統節點
    static class FileNode {
        String name;
        boolean isFile;
        List<FileNode> children;

        public FileNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            if (!isFile) this.children = new ArrayList<>();
        }

        public void addChild(FileNode node) {
            if (!isFile) {
                children.add(node);
            }
        }
    }

    // 1. 遞迴計算資料夾的總檔案數
    public static int countFiles(FileNode node) {
        if (node.isFile) return 1;
        int count = 0;
        for (FileNode child : node.children) {
            count += countFiles(child);
        }
        return count;
    }

    // 2. 遞迴列印多層選單結構 (使用 Map<String, Object> 模擬選單)
    public static void printMenu(Map<String, Object> menu, int level) {
        for (String key : menu.keySet()) {
            for (int i = 0; i < level; i++) System.out.print("  ");
            System.out.println("- " + key);
            Object sub = menu.get(key);
            if (sub instanceof Map) {
                // 遞迴列印子選單
                printMenu((Map<String, Object>) sub, level + 1);
            }
        }
    }

    // 3. 遞迴展平巢狀陣列（List<Object> 可能巢狀混雜整數與 List）
    public static List<Integer> flattenNestedList(List<?> nestedList) {
        List<Integer> result = new ArrayList<>();
        for (Object obj : nestedList) {
            if (obj instanceof Integer) {
                result.add((Integer) obj);
            } else if (obj instanceof List) {
                result.addAll(flattenNestedList((List<?>) obj));
            }
        }
        return result;
    }

    // 4. 遞迴計算巢狀清單的最大深度
    public static int maxDepth(List<?> nestedList) {
        int depth = 1;
        int maxChildDepth = 0;
        for (Object obj : nestedList) {
            if (obj instanceof List) {
                int childDepth = maxDepth((List<?>) obj);
                if (childDepth > maxChildDepth)
                    maxChildDepth = childDepth;
            }
        }
        return depth + maxChildDepth;
    }

    // 範例主程式示範操作
    public static void main(String[] args) {
        // 1. 模擬檔案系統測試
        FileNode root = new FileNode("root", false);
        FileNode folderA = new FileNode("folderA", false);
        FileNode file1 = new FileNode("file1.txt", true);
        FileNode file2 = new FileNode("file2.txt", true);
        FileNode folderB = new FileNode("folderB", false);
        FileNode file3 = new FileNode("file3.txt", true);

        folderA.addChild(file1);
        folderA.addChild(file2);
        folderB.addChild(file3);
        root.addChild(folderA);
        root.addChild(folderB);

        int totalFiles = countFiles(root);
        System.out.println("總檔案數: " + totalFiles);

        // 2. 多層選單結構示例
        Map<String, Object> menu = new LinkedHashMap<>();
        Map<String, Object> fileMenu = new LinkedHashMap<>();
        fileMenu.put("新建", null);
        fileMenu.put("開啟", null);
        fileMenu.put("儲存", null);
        Map<String, Object> editMenu = new LinkedHashMap<>();
        editMenu.put("剪下", null);
        editMenu.put("複製", null);
        editMenu.put("貼上", null);
        menu.put("檔案", fileMenu);
        menu.put("編輯", editMenu);
        menu.put("說明", null);

        System.out.println("\n多層選單結構：");
        printMenu(menu, 0);

        // 3. 巢狀陣列展平示例
        List<Object> nestedList = Arrays.asList(1, 2, Arrays.asList(3, 4, Arrays.asList(5)), 6);
        List<Integer> flatList = flattenNestedList(nestedList);
        System.out.println("\n展平後的陣列：" + flatList);

        // 4. 巢狀清單最大深度示例
        int depth = maxDepth(nestedList);
        System.out.println("巢狀清單最大深度：" + depth);
    }
}

