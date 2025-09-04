import java.util.*;

public class it_39_Combination_Sum {
    // 組合求和，回溯所有加和等於 target 的組合（元素可重複）
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
        for(int i=start;i<candidates.length;i++) {
            if(candidates[i] > target) break;
            current.add(candidates[i]);
            backtrack(ans, current, candidates, target - candidates[i], i);
            current.remove(current.size()-1);
        }
    }
}

