public class it_38_Count_and_Say {
    // 產生「報數」序列第 n 項
    public String countAndSay(int n) {
        if(n ==1) return "1";
        String prev = countAndSay(n-1);
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for(int i=1; i<prev.length(); i++) {
            if(prev.charAt(i) == prev.charAt(i-1)) count++;
            else {
                sb.append(count).append(prev.charAt(i-1));
                count =1;
            }
        }
        sb.append(count).append(prev.charAt(prev.length()-1));
        return sb.toString();
    }
}

