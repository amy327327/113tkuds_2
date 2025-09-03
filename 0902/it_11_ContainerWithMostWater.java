/**
 * 題目：Container With Most Water
 * 解題思路：
 * 此題可用雙指針法：一個指標在頭，一個在尾，每次計算兩指標形成的容器容量，
 * 並將較短邊的指標向內移動（因不可能超過當前容量），直到兩指標相遇。
 * 時間複雜度O(n)，空間複雜度O(1)。
 */
class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, max = 0;
        while(left < right) {
            int h = Math.min(height[left], height[right]);
            max = Math.max(max, h * (right - left));
            if(height[left] < height[right]) left++;
            else right--;
        }
        return max;
    }
}

