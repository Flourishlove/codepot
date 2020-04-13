// 橘子每分钟腐蚀旁边的橘子，求最快多少分钟腐蚀完全部橘子
// BFS搜索，有几点trick。
// 一是先计数新鲜的橘子，用于判断BFS终止条件；二是每层BFS需要计数，类似求tree的最大深度

class Solution {
    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> que = new LinkedList<>();

        int count_fresh = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2) que.offer(new int[]{i,j});
                if(grid[i][j] == 1) count_fresh++;
            }
        }
        if(count_fresh == 0) return 0;

        int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
        int count = 0;
        while(!que.isEmpty()){
            count++;
            int size = que.size();
            for(int k = 0; k < size; k++){
                int[] cur = que.poll();
                for(int[] dir : dirs){
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0 || grid[x][y] == 2) continue;
                    grid[x][y] = 2;
                    que.offer(new int[]{x,y});
                    count_fresh--;
                }
            }

        }

        return count_fresh == 0 ? count-1 : -1;
    }
}