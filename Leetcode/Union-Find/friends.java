class Solution {
    public int numIslands2(int f1, int f2, int n, int[][] friends) {
        int[] roots = new int[n];
        int[] nums = new int[n];

        for(int i = 0; i < n; i++) roots[i] = i;
        Arrays.fill(nums, 1);

        for(int[] friend : friends){
            union(roots, nums, friend[0], friend[1]);
        }

        int first = find(roots, f1);
        int second = find(roots, f2);
        return first == second ? nums[first] : nums[first] + nums[second];
    }

    public void union(int[] roots, int[] nums, int val1, int val2){
        int p = find(val1);
        int q = find(val2);
        if(p == q) return;

        roots[q] = p;
        nums[p] += nums[q];
    }

    public int find(int[] roots, int id){
        while(id != roots[id]) id = roots[id];
        return id;
    }
}