import java.util.*;

public class StockMaximizer {

    public static int maxProfit(int[] prices, int k) {
        List<Integer> profits = new ArrayList<>();
        int buy = -1;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) { // 開始上漲
                if (buy == -1) buy = prices[i];
            }
            if (buy != -1 && prices[i] > prices[i + 1]) { // 賣出
                profits.add(prices[i] - buy);
                buy = -1;
            }
        }
        if (buy != -1) {
            profits.add(prices[prices.length - 1] - buy);
        }

        // 選擇最大的 k 個交易
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(profits);

        int totalProfit = 0;
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            totalProfit += maxHeap.poll();
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {2,4,1};
        System.out.println(maxProfit(prices1, 2)); // 2

        int[] prices2 = {3,2,6,5,0,3};
        System.out.println(maxProfit(prices2, 2)); // 7

        int[] prices3 = {1,2,3,4,5};
        System.out.println(maxProfit(prices3, 2)); // 4
    }
}

