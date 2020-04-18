// 找到一个数字组中下一个更大值，数组是循环数组
// 核心思想有两个：1.用stack，如果没有遇到更大的值，就先把当前的序号压进栈；遇到更大的值就while循环不断弹出
// 2. 解决循环数组用两个for循环，第二个循环数组不压栈，只出栈
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> sk = new LinkedList<>();
        int[] result = new int[nums.length];
        for(int i = 0; i < nums.length; i++) result[i] = -1;   // Arrays.fill(result, -1);

        for(int i = 0; i < nums.length; i++){
            while(!sk.isEmpty() && nums[sk.peek()] < nums[i]) result[sk.pop()] = nums[i];
            sk.push(i);
        }

        for(int i = 0; i < nums.length; i++){
            while(!sk.isEmpty() && nums[sk.peek()] < nums[i]) result[sk.pop()] = nums[i];
        }
        return result;
    }
}