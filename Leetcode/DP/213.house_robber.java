// house robber with cycle, 首尾的房子认为是相连
// 把问题拆解成去掉第一个和最后一个房子，两种方案取最大
// 在无cycle的问题中，用两个变量，保存前一个房子是否被抢

public class Solution {
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        return Math.max(robNoCycle(nums, 0, nums.length-2), robNoCycle(nums, 1, nums.length-1));
    }

    public int robNoCycle(int[] nums, int low, int high){
        int preYes = 0;
        int preNo = 0;
        for(int i = low; i <= high; i++){
            int temp_y = preYes;
            preYes = preNo + nums[i];
            preNo = Math.max(temp_y, preNo);
        }
        return Math.max(preYes, preNo);
    }
}