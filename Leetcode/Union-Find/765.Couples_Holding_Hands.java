// 编号为0->(n-1)共n个人，0和1是couple，2和3是couple...坐成一排，求最小的交换次数让所有couple都能坐在相邻
// 这题的核心思想类似于对数组nums，求最小的交换次数让数组有序。如果是排序的的话，条件就是 i == nums[i],即每个位置的值都等于index
// 实际写代码的时候，就是对于每一个pivot位置，不断地看i == nums[i]是否成立，如果不成立，交换i和nums[i]位置上的值。
// 在本题中，我们判断的条件不是看i == nums[i]，而是看 i == par[pos[par[row[i]]]]
// 这里的par[i] 指的是编号为i的人对应的partner，比如0对应了1，3对应了2; pos[i]指的是编号为i的人现在的位置
// pos[par[row[i]]]指的是找到当前人的partner对应的座位，par[pos[par[row[i]]]]指的是找到这个座位应该对应的partner。
// 如果i已经在这个位置上了，就说明已经达到条件。

class Solution {
    public int minSwapsCouples(int[] row) {
        int[] pos = new int[row.length];
        int[] par = new int[row.length];

        for(int i = 0; i < row.length; i++){
            pos[row[i]] = i;
            par[i] = i % 2 == 0 ? i+1 : i-1;
        }

        int count = 0;
        for(int i = 0; i < row.length; i++){
            for(int j = par[pos[par[row[i]]]]; i != j; j = par[pos[par[row[i]]]]){
                swap(row, i, j);
                swap(pos, row[i], row[j]);
                count++;
            }
        }
        return count;
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}