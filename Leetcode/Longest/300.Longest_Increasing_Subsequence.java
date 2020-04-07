// 求数组中最长递增子序列（不是subarray）， 比如[10,9,2,5,3,7,101,18]，最长递增子序列为[2,3,7,101]
// 方法一：用一个数组存储每个位置结尾的最长递增子序列，之后的每一个位置，都遍历之前的所有位置，找到最大的加1，时间复杂度O(n^2)
// 方法二：遍历数组，用一个数组保存当前位置对应的各长度序列的最小末尾，tail[size]。
// tail是一个递增的数组，我们可以用二分法找到下一个数字之前的最长序列对应的值



class Solution1 {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] cache = new int[nums.length];
        Arrays.fill(cache, 1);
        int max = 0;

        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]) cache[i] = Math.max(cache[i], cache[j]+1);
            }
            max = Math.max(max, cache[i]);
        }
        return max;
    }
}

class Solution2 {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;

        int[] tail = new int[nums.length];
        int size = 0;
        for(int n : nums){
            int i = 0; int j = size;
            while(i < j){
                int mid = i + (j-i)/2;
                if(tail[mid] < n) i = mid + 1;
                else j = mid;
            }
            tail[i] = n;
            if(i == size) size++;
        }

        return size;
    }
}