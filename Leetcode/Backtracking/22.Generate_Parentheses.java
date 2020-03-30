// 给定正整数n，生成全部合理的括号对，例如“((()))”, "(())()"
// 回溯法，核心是在回溯函数中计数左括号和右括号剩余个数和当前字符串

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList();
        if(n <= 0) return result;
        backtrack(n-1, n, "(", result);

        return result;
    }
    public void backtrack(int open, int close, String cur, List<String> result){
        if(close < open || open < 0 || close < 0) return;
        if(open == 0 && close == 0) result.add(cur);
        backtrack(open-1, close, cur+"(", result);
        backtrack(open, close-1, cur+")", result);
    }
}