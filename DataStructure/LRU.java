public class LRUCache {
    int capacity;
    HashMap<Integer, DLinkedNode> cache;
    DLinkedNode head;
    DLinkedNode tail;

    public LRUCache(int capacity) {
        cache = new HashMap<>(capacity);
        this.head = new DLinkedNode();
        this.tail = new DLinkedNode();
        head.next = tail;  tail.pre = head;
        this.capacity = capacity;
    }

    public void addToHead(DLinkedNode node) {
        node.next = head.next;
        node.next.pre = node;
        node.pre = head;
        head.next = node;
    }

    public void deleteNode(DLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public int get(int key) {
        if(cache.containsKey(key)){
            DLinkedNode temp = cache.get(key);
            deleteNode(temp);
            addToHead(temp);
            return temp.val;
        }
        else{
            return -1;
        }
    }

    public void set(int key, int value) {
        if(cache.containsKey(key)){
            DLinkedNode temp = cache.get(key);
            temp.val = value;
            deleteNode(temp);
            addToHead(temp);
        }
        else{
            DLinkedNode temp = new DLinkedNode(key, value);
            if(cache.size() < capacity){
                cache.put(key, temp);
                addToHead(temp);
            }
            else{
                cache.remove(tail.pre.key);
                cache.put(key, temp);
                deleteNode(tail.pre);
                addToHead(temp);
            }
        }
    }
}

class DLinkedNode {
    int val;
    int key;
    DLinkedNode pre;
    DLinkedNode next;
    DLinkedNode(){}
    DLinkedNode(int key, int value){this.key = key; this.val = value;}
}