// 找到最长的字符串链，满足前一个字符串加一个字符可以得到后一个字符串
// 这题非常类似于longest increasing path in 2D matrix，也是使用DFS加上memories

class Solution {
    public int longestStrChain(String[] words) {
        if(words == null || words.length == 0) return 0;
        Set<String> set = new HashSet<>();
        Map<String, Integer> map  = new HashMap<>();

        int result = 1;
        for(String w : words) set.add(w);
        for(String w : words) result = Math.max(result, DFS(map, set, w));
        return result;
    }
    public int DFS(Map<String, Integer> map, Set<String> set, String word){
        if(map.containsKey(word)) return map.get(word);
        int count = 0;
        for(int i = 0; i < word.length(); i++){
            String next = word.substring(0, i) + word.substring(i+1);
            if(set.contains(next)){
                count = Math.max(count, DFS(map, set, next));
            }
        }
        map.put(word, count+1);
        return count+1;
    }
}