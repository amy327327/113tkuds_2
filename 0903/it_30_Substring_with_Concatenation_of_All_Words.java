import java.util.*;

public class it_30_Substring_with_Concatenation_of_All_Words {
    // 尋找字串中所有是 words 串聯的起始索引
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if(s.length() == 0 || words.length == 0) return res;
        int wordLen = words[0].length(), wordsNum = words.length;
        Map<String, Integer> wordCount = new HashMap<>();
        for(String w : words) wordCount.put(w, wordCount.getOrDefault(w, 0) + 1);

        for(int i=0; i<s.length()-wordLen*wordsNum+1; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            for(; j<wordsNum; j++) {
                int wordIndex = i + j * wordLen;
                String sub = s.substring(wordIndex, wordIndex + wordLen);
                if(!wordCount.containsKey(sub)) break;
                seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                if(seen.get(sub) > wordCount.get(sub)) break;
            }
            if(j == wordsNum) res.add(i);
        }
        return res;
    }
}

