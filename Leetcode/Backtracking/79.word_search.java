// 在一个字符2D matrix里面查找是否有目标单词
// 是backtrack也是DFS。核心在于从每个字符开始，不断DFS搜索每一条路径
// 有两点需要注意，一是inpalce方法是每检查一个char，就把board对应的char置为"*"，表示访问过了，这样就不用新建visited矩阵
// 二是要先判断是否return true，这和backtrack设置的边界条件有关，一旦已经检查完了最后一个字符，就表示已经成功了

class Solution {
    public boolean exist(char[][] board, String word) {
        char[] chs = word.toCharArray();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(backtrack(board, chs, 0, i, j)) return true;
            }
        }
        return false;
    }
    public boolean backtrack(char[][] board, char[] chs, int index, int row, int col){
        if(index == chs.length) return true;
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || index >= chs.length) return false;
        if(chs[index] != board[row][col]) return false;

        char tmp = board[row][col];
        board[row][col] = '*';
        boolean result = backtrack(board, chs, index+1, row-1, col)
            || backtrack(board, chs, index+1, row+1, col)
            || backtrack(board, chs, index+1, row, col-1)
            || backtrack(board, chs, index+1, row, col+1);
        board[row][col] = tmp;
        return result;
    }
}