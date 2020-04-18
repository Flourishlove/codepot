// 在一个matrix里面求最大的“1”矩阵面积
// DP思想，一行一行处理，有三个变量，left，right，height
// height[i] record the current number of countinous '1' in column i;
// left[i] record the left most index j which satisfies that for any index k from j to  i, height[k] >= height[i];
// right[i] record the right most index j which satifies that for any index k from i to  j, height[k] >= height[i];

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, maxArea = 0;
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n - 1);

        for(int i = 0; i < m; i++){
            int rB = n-1;
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], rB);
                } else {
                    right[j] = n - 1;
                    rB = j - 1;
                }
            }

            int lB = 0;
            for(int j = 0; j < n; j++){
                if (matrix[i][j] == '1') {
                    height[j]++;
                    left[j] = Math.max(left[j], lB);
                    maxArea = Math.max(maxArea, (right[j]-left[j]+1)*height[j]);
                }else{
                    height[j] = 0;
                    left[j] = 0;
                    lB = j+1;
                }
            }
        }
        return maxArea;
    }
}