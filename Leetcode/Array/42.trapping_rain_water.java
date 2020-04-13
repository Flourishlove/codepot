// trap water, 计算柱形里面可以蓄多少水
//approach 1 : stack
//approach 2 : two pointer

public class Solution1 {
    public int trap(int[] height) {
        int result = 0;
        Stack<Integer> sk = new Stack<>();
        int i = 0;
        while(i < height.length){
            if(sk.isEmpty() || height[i] <= height[sk.peek()]){
                sk.push(i);
                i++;
            }
            else{
                int bot = sk.pop();
                result += sk.isEmpty() ? 0 : (Math.min(height[i], height[sk.peek()]) - height[bot]) * (i-1-sk.peek());
            }
        }
        return result;
    }
}

class Solution {
    public int trap(int[] height) {
        if(height == null || height.length <= 2) return 0;

        int left = 0;
        int right = height.length-1;
        int result = 0;
        int leftMax = height[left];
        int rightMax = height[right];
        while(left < right){
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if(leftMax < rightMax){
                result += leftMax - height[left];
                left++;
            }
            else{
                result += rightMax - height[right];
                right--;
            }
        }
        return result;
    }
}