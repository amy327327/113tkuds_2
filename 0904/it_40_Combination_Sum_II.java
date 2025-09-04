import java.util.*;

public class it_40_Combination_Sum_II {
    // 組合求和 II，每個元素只能使用一次，去除重複組合
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(ans, new ArrayList<>(), candidates, target, 0);
        return ans;
    }

    private void backtrack(List<List<Integer>> ans, List<Integer> current, int[] candidates, int target, int start) {
        if(target == 0) {
            ans.add(new ArrayList<>(current));
            return;
        }
        for(int i = start; i < candidates.length; i++) {
            if(i > start && candidates[i] == candidates[i-1]) continue; // 去重
            if(candidates[i] > target) break;
            current.add(candidates[i]);
            backtrack(ans, current, candidates, target - candidates[i], i + 1);
            current.remove(current.size()-1);
        }
    }
}

