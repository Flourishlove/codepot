// 扎气球赚金币的游戏，每扎破一个气球i，得到的钱是num[i-1]*nums[i]*nums[i+1],最后一个气球得到的钱是自己本身
// dp思路，dp[i][j]表示的是i到j能够挣到的最大金币，其中i和j表示左右边界。
// 这里有一点需要注意，由于每扎破一个气球，就会改变相邻的结构，导致难以把问题变成左右两个，所以我们需要反向思路，以i位置为最后一个扎破的气球。
// 因为i位置最后一个扎破，不会再影响左右两边的金币收入，所有可以有递推关系。

public class Solution {
    public int maxCoins(int[] nums) {
        int[] numbers = new int[nums.length+2];

        int n = 1;
        for(int x : nums){
            if(x > 0) numbers[n++] = x; // 0 won't affect result
        }
        numbers[0] = numbers[n++] = 1;

        int[][] dp = new int[n][n];
        for(int k = 2; k < n; k++){
            for(int left = 0; left < n-k; left++){
                int right = left + k;
                for(int i = left + 1; i < right; i++){
                    dp[left][right] = Math.max(dp[left][right], numbers[left] * numbers[i] * numbers[right] + dp[left][i] + dp[i][right]);
                }
            }
        }
        return dp[0][n-1];
    }
}