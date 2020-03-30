// Input: nums = [1,2,3]
// Output:
// [
//   [3],
//   [1],
//   [2],
//   [1,2,3],
//   [1,3],
//   [2,3],
//   [1,2],
//   []
// ]

// 对于backtrack中有for循环的，开始的序号一定要注意；这里的start传入的是i+1,而不是start+1
// 可以根据画树形图理解，如果用start+1，会多出很多分支；因为为了防止重复，我们是只需要加入这个数字之后的数字

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }
    public void backtrack(int start, int[] nums, List<Integer> curList, List<List<Integer>> result){
        result.add(new ArrayList<>(curList));
        for(int i = start; i < nums.length; i++){
            curList.add(nums[i]);
            backtrack(i+1, nums, curList, result);
            curList.remove(curList.size()-1);
        }
    }
}