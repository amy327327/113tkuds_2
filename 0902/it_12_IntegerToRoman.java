/**
 * 題目：Integer to Roman
 * 解題思路：
 * 建立數字與羅馬字母對應表，自大到小依次嘗試將整數減去表中數值，
 * 每被減一次即加上相對應的羅馬字母，直到全部處理完。
 * 此法有效處理特殊減法（如4,9,40,90等）。
 */
class Solution {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] reps = {
            "M", "CM", "D", "CD", "C", "XC", "L",
            "XL", "X", "IX", "V", "IV", "I"
        };
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(reps[i]);
            }
        }
        return sb.toString();
    }
}

