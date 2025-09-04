public class it_29_Divide_Two_Integers {
    // 實現兩整數除法，不使用乘除法和模運算符
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE; // 溢位處理

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;

        int result = 0;
        while(a >= b) {
            long temp = b, multiple = 1;
            while(a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;
            result += multiple;
        }
        return sign * result;
    }
}

