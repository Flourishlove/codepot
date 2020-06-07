import java.io.*;
import java.util.*;

class TrieNode{
    TrieNode[] children;
    Map<String, Integer> topN;
    public TrieNode(){
        children = new TrieNode[256];
        topN = new PriorityQueue<>();
    }
}

class Solution{
    public void add(TrieNode root, String query){
        char[] chs = query.toCharArray();
        TrieNode cur = root;
        for(int i = 0; i < chs.length; i++){
            if(cur.children[chs[i].hashCode()] == null){
                cur.children[chs[i].hashCode()] = new TrieNode();
            }
            cur = cur.children[chs[i].hashCode()];
        }
        Map<String, Integer> topN = cur.topN;
        if(topN.containsKey(query)) topN.put(query, topN.get(query)+1);
        else topN.put(query, 1);
    }

    public List<String> returnQuery(String query, int k){
        List<String> result = new ArrayList<>();
        char[] chs = query.toCharArray();
        TrieNode cur = root;
        for(int i = 0; i < chs.length; i++){
            if(cur.children[chs[i].hashCode()] == null){
                cur.children[chs[i].hashCode()] = new TrieNode();
            }
            cur = cur.children[chs[i].hashCode()];
        }
        Map<String, Integer> topN = cur.topN;
        Collections.sort(topN, new Comparator<>(){

        });

        for(int i = 0; i < k; i++){
            result.add(topN);
        }
        return result;
    }
}

public class Query{
    public static void main(String args[]){
        String[] news = {'tencent_games', 'tencent_news', 'tencet_music'};

    }
}
