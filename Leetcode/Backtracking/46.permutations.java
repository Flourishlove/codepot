// permutations
// 输入一个数组，枚举所有可能的排列
// backtrack函数中存放当前的排列，同时maintain一个set，存放当前已经有的数组，用于判断下一个数字是否已有

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 0) return result;
        backtrack(nums, new HashSet<>(), new ArrayList<>(), result);
        return result;
    }
    public void backtrack(int[] nums, HashSet<Integer> set, List<Integer> cur, List<List<Integer>> result){
        if(cur.size() == nums.length) result.add(new ArrayList<Integer>(cur));
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])) continue;

            cur.add(nums[i]);
            set.add(nums[i]);
            backtrack(nums, set, cur, result);
            cur.remove(cur.size()-1);
            set.remove(nums[i]);
        }
    }
}