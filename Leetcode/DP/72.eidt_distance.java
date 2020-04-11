// 求最小的操作数，把word1变成word2(增加，更改，删除)
// 经典dp, dp[i][j]表示把word1前i个字符变成word2前j个字符需要的操作数
// 非常类似于longest common sequence

public class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(); int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];

        for(int i = 1; i <= len1; i++){
            dp[i][0] = i;
        }
        for(int j = 1; j <= len2; j++){
            dp[0][j] = j;
        }

        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else{
                    dp[i][j] = Math.min(dp[i-1][j-1]+1, Math.min(dp[i-1][j]+1, dp[i][j-1]+1));//replace, insert, delect
                }
            }
        }
        return dp[len1][len2];
    }
}