// 最长回文子串，遍历每个位置，每个位置分为奇偶往两边扩展，看最大扩展长度

public class Solution {
    public String longestPalindrome(String s) {
        int tail = s.length()-1;
        if(s.length() < 2) return s;
        int max_len = 1;
        int start = 0; //start and end record the indexs of longestPalindrome
        for(int index = 1; index <= tail; index++){
            int i = index;
            int j = index;
            while(s.charAt(i) == s.charAt(j) && i > 0 && j < tail){
                i--; j++;
            }
            if(s.charAt(i) != s.charAt(j)){ i++; j--;}
            if(j-i+1 > max_len){
                start = i;
                max_len = j-i+1;
            }

            i = index-1; j = index;
            while(s.charAt(i) == s.charAt(j) && i > 0 && j < tail){
                i--; j++;
            }
            if(s.charAt(i) != s.charAt(j)){ i++; j--;}
            if(j-i+1 > max_len){
                start = i;
                max_len = j-i+1;
            }
        }
        return s.substring(start, start + max_len);
    }
}