// 3sum, 在一个有重复数字的数组里，找出所有和为0的unique组合
// 核心思想，排序，然后用一个外层for循环，把target变成(0-nums[i])
// 内层循环用two pointers，注意避开重复数字

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();

        Arrays.sort(nums);
        for(int i = 0; i < nums.length-2; i++){
            if(i == 0 || (i > 0 && nums[i] != nums[i-1])){ //to avoid duplicate
                int low = i + 1, high = nums.length - 1;
                int sum = 0 - nums[i];
                while(low < high){
                    if(nums[low]+nums[high] == sum){
                        res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while(low < high && nums[low] == nums[low+1]) low++;
                        while(low < high && nums[high] == nums[high-1]) high--;
                        low++;high--;
                    }
                    else if(nums[low]+nums[high] < sum){
                        low++;
                    }
                    else high--;
                }
            }
        }
        return res;
    }
}