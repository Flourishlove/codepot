// 经典topological sort, 核心思想是记录下所有节点的入度是多少。
// 每当入度为0，说明没有了前置条件，便加入到queue中
// queue中的循环更新入度

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> matrix = new ArrayList<>();
        int[] indegree = new int[numCourses];
        Queue<Integer> qu = new LinkedList<>();

        for(int i = 0; i < numCourses; i++) matrix.add(new ArrayList<Integer>());

        for(int i = 0; i < prerequisites.length; i++){
            int pre = prerequisites[i][1];
            int ready = prerequisites[i][0];
            indegree[ready]++;
            matrix.get(pre).add(ready);
        }

        int count = 0;
        for(int i = 0; i < numCourses; i++) if(indegree[i] == 0){
            qu.offer(i);
        }

        while(!qu.isEmpty()){
            count++;
            int course = qu.poll();
            List<Integer> list = matrix.get(course);
            for(int n : list){
                indegree[n]--;
                if(indegree[n] == 0) qu.offer(n);
            }
        }

        return count == numCourses;
    }
}