// count the number of islands
// 1.可以用dfs inplce的方法做，每找到一个岛，count++, 并且dfs把棋盘上所有1变为0
// 2.可以用Union-Find，可以用Arrays.fill(root, 1)来初始化数组

class Solution1 {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length, n = grid[0].length;
        int count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int row, int col){
        if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == '0') return;
        grid[row][col] = '0';
        dfs(grid, row-1, col);
        dfs(grid, row+1, col);
        dfs(grid, row, col-1);
        dfs(grid, row, col+1);
    }
}

// 使用union-find注意：1.转成1D数组的时候，是i*n+j,而不是i*m+j；2.对棋盘中每一个字符，只需要检查它的上面和左边
class Solution2 {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;

        int[] root = new int[m*n];
        Arrays.fill(root, -1);

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    root[i*n+j] = i*n+j;
                    if(i > 0 && grid[i-1][j] == '1') union(root, (i-1)*n+j, i*n+j);
                    if(j > 0 && grid[i][j-1] == '1') union(root, i*n+j-1, i*n+j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < m*n; i++){
            if(root[i] != -1) set.add(find(root, i));
        }

        return set.size();
    }
    public void union(int[] root, int id1, int id2){
        int val1 = find(root, id1);
        int val2 = find(root, id2);
        if(val1 == val2) return;
        else{
            root[val1] = val2;
        }
    }

    public int find(int[] root, int id){
        while(root[id] != id) id = root[id];
        return id;
    }
}