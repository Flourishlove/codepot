// Implement a trie with insert, search, and startsWith methods.
// a-z 26 characters
// ASCii has 256 characters

class Trie {
    class TrieNode{
        private TrieNode[] children;
        private String item;
        public TrieNode(){
            children = new TrieNode[26];
            item = "";
        }
    }

    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode temp = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(temp.children[c - 'a'] == null) temp.children[c - 'a'] = new TrieNode();
            temp = temp.children[c - 'a'];
        }
        temp.item = word;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode temp = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(temp.children[c - 'a'] == null) return false;
            temp = temp.children[c - 'a'];
        }
        return temp.item.equals(word);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode temp = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(temp.children[c - 'a'] == null) return false;
            temp = temp.children[c - 'a'];
        }
        return true;
    }
}