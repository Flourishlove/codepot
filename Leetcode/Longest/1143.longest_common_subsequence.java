// 给两个String, 求它们的最长公共子序列长度,比如text1 = "abcde", text2 = "ace" ，最长公共子序列为“ace”
// 核心思想dp，dp[i][j]表示第一个字符串i位置之前的和第二个字符串j位置之前的最长公共子序列长度
// 这里dp数组的长度多设置1，用于初始化dp[0][0]

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length();
        int n = text2.length();

        char[] ch1 = text1.toCharArray();
        char[] ch2 = text2.toCharArray();

        int max = 0;
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(ch1[i] == ch2[j]) dp[i+1][j+1] = dp[i][j]+1;
                else{
                    dp[i+1][j+1] = Math.max(dp[i][j], Math.max(dp[i][j+1], dp[i+1][j]));
                }
                max = Math.max(max, dp[i+1][j+1]);
            }
        }
        return max;
    }
}