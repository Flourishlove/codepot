// given an array, find the subarray which has the largest sum
// 核心思想，遍历array，如果之前的最大和加上当前的数字还不如当前数字，则重新开始记sum

class Solution {
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int result = nums[0];
        for(int i = 1; i < nums.length; i++){
            sum = Math.max(nums[i], sum+nums[i]);
            result = Math.max(result, sum);
        }
        return result;
    }
}