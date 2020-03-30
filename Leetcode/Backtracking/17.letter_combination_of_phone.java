// 九键数字对应的字母组合, 类似输入法
// 1. BackTrack中不要拆开字符串，直接传递当前翻译到的index可以提高效率
// 2. 对于判断一个字符是数字几，可以直接减去‘0’

class Solution {
    String[] table = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits == null || digits.length() == 0) return result;
        backtrack(digits, 0, "", result);
        return result;
    }
    public void backtrack(String digits, int index, String cur, List<String> result){
        if(index >= digits.length()){
            result.add(cur);
            return;
        }
        String tmp = table[digits.charAt(index)-'0'];
        for(int i = 0; i < tmp.length(); i++){
            backtrack(digits, index+1, cur+tmp.charAt(i), result);
        }
    }
}