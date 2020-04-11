// 给定一个数组nums和一个数字K，求一共有多少种连续的子数组和为K
// 我们希望求得sum[i,j], 那么就可以利用做差法，sum[0,i-1], sum[0,j]来求任意sum[i，j]

class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;

        int sum = 0;
        int result = 0;
        // 充分利用hash减小复杂度
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum-k)){
                result += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum,0)+1);
        }
        return result;
    }
}