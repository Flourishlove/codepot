// top k frequent words，要求按出现次数输出出现次数top k的word。如果两个词频率相同，则优先输出string排序在前的
// 经典priorityqueue题型，先用map计数每个word出现的次数，然后再遍历放入到最小堆中，，始终保持堆的大小为k
// 需要注意的点：
// 1. 最小堆，因为我们是需要保留频数最大的k，相当于我们最后需要的结果仍然是在堆中的，需要一个最小堆来不断丢弃最小值
// 2. 输出的结果优先string排序在前的，也就是说优先丢弃string比较大的，所以b.getKey().compareTo(a.getKey())
// 3. result需要选用linkedlist，因为我们需要将pq.poll()输出的结果reverse，而linkedlist的插入为O(1)

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new LinkedList<>();
        if(words == null || words.length <= 0) return result;

        Map<String, Integer> map = new HashMap<>();
        for(String word : words){
            map.put(word, map.getOrDefault(word,0)+1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b){
                return a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()): Integer.compare(a.getValue(), b.getValue());
            }
        });

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            pq.add(entry);
            if(pq.size() > k) pq.poll();
        }

        while(!pq.isEmpty()) result.add(0, pq.poll().getKey());
        return result;
    }
}