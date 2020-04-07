// 最长回文子序列（和最长回文子串不一样）
// 核心思想是DP，dp[i][j]表示位置在i，j之间的最长回文子序列长度
// 从长度为1开始扩展

class Solution {
    public int longestPalindromeSubseq(String s) {
        if(s.length() == 0) return 0;

        int[][] dp = new int[s.length()][s.length()];

        for(int i = 0; i < s.length(); i++) dp[i][i] = 1;

        int max = 1;
        for(int len = 1; len < s.length(); len++){
            for(int i = 0; i+len < s.length(); i++){
                if(s.charAt(i) == s.charAt(i+len)) dp[i][i+len] = dp[i+1][i+len-1]+2;
                else dp[i][i+len] = Math.max(dp[i+1][i+len-1], Math.max(dp[i][i+len-1], dp[i+1][i+len]));
                max = Math.max(max, dp[i][i+len]);
            }
        }
        return max;
    }
}