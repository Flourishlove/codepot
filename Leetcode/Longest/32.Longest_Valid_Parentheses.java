// 求一个字符串中最长的合理括号长度
// 核心思想是用stack来判断是否合理，保存的是括号的index
// 需要注意的是类似于“(()()”这种情况，如果是'(',直接push；
// 如果是')',需要判断stack是不是已经空了，空了表明到了一个不是valid的位置，重置start，没空则弹出一个再判断有没有空

class Solution {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0) return 0;
        char[] chs = s.toCharArray();

        int max = 0;
        int start = 0;
        Deque<Integer> sk = new LinkedList<>();
        for(int i = 0; i < chs.length; i++){
            if(chs[i] == '(') sk.push(i);
            else{
                if(sk.isEmpty()) start = i+1;
                else{
                    int temp = sk.pop();
                    if(sk.isEmpty()) max = Math.max(max, i-start+1);
                    else max = Math.max(max, i-sk.peek());
                }
            }
        }
        return max;
    }
}