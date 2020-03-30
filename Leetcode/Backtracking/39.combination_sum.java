// 从候选数组中找出所有和为target的数字组合，数字可以重复使用
// 两个注意点 1.在backtrack中可以通过改变传递的target来替代当前的sum; 2.为了避免重复需要传递一个每次开始的index

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    public void backtrack(int[] candidates, int target, int start, List<Integer> curList, List<List<Integer>> result){
        if(target < 0) return;
        if(target == 0){
            result.add(new ArrayList<>(curList));
            return;
        }
        for(int i = start; i < candidates.length; i++){
            curList.add(candidates[i]);
            backtrack(candidates, target-candidates[i], i, curList, result);
            curList.remove(curList.size()-1);
        }
    }
}