// matrix里面最长的递增序列长度
// 用DFS的想法，对每一个节点开始搜索，用cache记录下当前节点开始的最长序列长度，终结点是数值都大于邻居的点

public class Solution {
    private int m, n;
    private final static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        int[][] cache = new int[m][n];
        int result = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                result = Math.max(result, DFS(matrix, i, j, cache));
            }
        }
        return result;
    }
    public int DFS(int[][] matrix, int i, int j, int[][] cache){
        if(cache[i][j] != 0) return cache[i][j];
        for(int[] d : dir){
            int x = i + d[0];
            int y = j + d[1];
            if(x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j])cache[i][j] = Math.max(cache[i][j], DFS(matrix, x, y, cache));
        }
        return ++cache[i][j];
    }
}