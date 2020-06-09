// 搜索自动补全，主要的思想在于使用trie。
// 关键的几点：
// 1. 构建的时候对于每个sentence，找到对应的节点之后，要对搜索路径上的所有节点进行topk更新，这样在查询的时候就可以不用遍历所有子孙；这里的技巧是每个节点保存的topk其实是对应着自己的某个子节点
// 2. 每个节点需要记录该节点对应的句子和相应的词频，为了方便
// 3. 进行搜索的同时，也是在往词库中添加句子的过程，词频为1
// 4. 每个节点可以使用priorityQueue，主要是用最小堆

class TrieNode{
    TrieNode[] children;
    List<TrieNode> top;
    String sentence = "";
    int time = 0;
    public TrieNode(){
        children = new TrieNode[128];
        top = new LinkedList<>();
    }
    
    public void update(TrieNode node){
        if(!top.contains(node)) top.add(node);
        Collections.sort(top, new Comparator<TrieNode>(){
            public int compare(TrieNode a, TrieNode b){
                return a.time == b.time ? a.sentence.compareTo(b.sentence) : Integer.compare(b.time, a.time); 
            }
        });
        
        if(top.size() > 3) top.remove(top.size()-1);
    }
}

class AutocompleteSystem {

    TrieNode root;
    StringBuilder sb;
    TrieNode cur;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        sb = new StringBuilder();
        cur = root;
        for(int i = 0; i < times.length; i++){
            add(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        
        if (c == '#') {
            add(sb.toString(), 1);
            sb = new StringBuilder();
            cur = root;
            return result;
        }
        
        sb.append(c);
        if(cur != null){
            cur = cur.children[c];
        }

        if(cur == null) return result;
        for(TrieNode n : cur.top){
            result.add(n.sentence);
        }
        return result;
    }
    
    public void add(String sentence, int time){
        char[] chs = sentence.toCharArray();
        TrieNode tmp = root;
        List<TrieNode> visited = new ArrayList<>();
        
        for(char c : chs){
            if(tmp.children[c] == null) tmp.children[c] = new TrieNode();
            tmp = tmp.children[c];
            visited.add(tmp);
        }
        
        tmp.sentence = sentence;
        tmp.time += time;
        
        for(TrieNode node : visited){
            node.update(tmp);
        }
    }
    
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */