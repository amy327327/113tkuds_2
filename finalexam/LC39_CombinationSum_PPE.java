import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LC39_CombinationSum_PPE {
    static List<List<Integer>> res;
    static int[] candidates;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        candidates = new int[n];
        for(int i=0; i<n; i++) candidates[i] = sc.nextInt();

        Arrays.sort(candidates);
        res = new ArrayList<>();

        backtrack(0, target, new ArrayList<>());

        for(List<Integer> comb : res){
            for(int i=0; i<comb.size(); i++){
                System.out.print(comb.get(i));
                if(i < comb.size()-1) System.out.print(" ");
            }
            System.out.println();
        }
    }

    static void backtrack(int start, int remain, List<Integer> temp){
        if(remain == 0){
            res.add(new ArrayList<>(temp));
            return;
        }
        if(remain < 0) return;

        for(int i=start; i<candidates.length; i++){
            temp.add(candidates[i]);
            backtrack(i, remain-candidates[i], temp); // 允許重複使用同一元素
            temp.remove(temp.size()-1);
        }
    }
}
// 複雜度指數級

