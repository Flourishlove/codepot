// 找到无序数组中最长的连续序列长度，比如[100, 4, 200, 1, 3, 2]， 返回4, 要求O(n)
// 两种思路：1.用hashmap，每插入一个数字n，看map中是否已有n-1和n+1，如果有，把自己和两边的长度都算上，插入到map中。
//          同时更新当前这个新组成的序列的端点长度，用于下一次插入
//         2.union-find来解决，把相邻的数字都union起来，最后看最大的union的size。每插入一个值n,看n-1和n+1是否已经在数组中。
//          如果在，就找到它们index进行union

class Solution1 {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])) continue;

            int left = map.containsKey(nums[i]-1) ? map.get(nums[i]-1) : 0;
            int right = map.containsKey(nums[i]+1) ? map.get(nums[i]+1) : 0;
            map.put(nums[i], left+right+1);

            map.put(nums[i]-left, left+right+1);
            map.put(nums[i]+right, left+right+1);
            max = Math.max(max, left+right+1);
        }
        return max;
    }
}


class Solution2 {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] root = new int[nums.length];
        for(int i = 0; i < nums.length; i++) root[i] = i;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])) continue;

            map.put(nums[i], i);
            if(map.containsKey(nums[i]-1)){
                union(root, i, map.get(nums[i]-1));
            }
            if(map.containsKey(nums[i]+1)){
                union(root, i, map.get(nums[i]+1));
            }
        }

        int[] count = new int[nums.length];
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            count[find(root, i)]++;
        }
        for(int i = 0; i < nums.length; i++) max = Math.max(max, count[i]);

        return max;
    }
    public void union(int[] root, int id1, int id2){
        int p = find(root, id1);
        int q = find(root, id2);
        root[p] = q;
    }
    public int find(int[] root, int id){
        while(root[id] != id){
            id = root[id];
        }
        return id;
    }
}