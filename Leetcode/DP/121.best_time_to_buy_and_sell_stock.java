// 交易一次，求最大利润
// 方法一：遍历数组，maintain一个当前见到的最小值min，同时看当前price-min是否为最大
// 方法二：类似于求最大子数组，只不过这次是先对数组做差，求出每两天之间的利润，最大的子数组就是最大利润

class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < prices.length; i++){
            min = Math.min(prices[i], min);
            max = Math.max(max, prices[i]-min);
        }
        return max;
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        int sum = 0;
        for(int i = 1; i < prices.length; i++){
            sum = Math.max(0, sum + prices[i] - prices[i-1]);
            max = Math.max(sum, max);
        }
        return max;
    }
}