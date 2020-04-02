// The HashMap class is roughly equivalent to Hashtable, except that it is unsynchronized and permits nulls
// Hashmap put and get operation time complexity is O(1) with assumption that key-value pairs are well distributed across the buckets. It means hashcode implemented is good. (best case O(1), worse case O(n))

public class HashMap {
    private capacity = 256;
    private Node[] bucketArray;

    public HashMap(){
        bucketArray = new Node[capacity]; //dafault value is null
    }

    public HashMap(int capacity){
        this.capacity = capacity;
        bucketArray = new Node[capacity];
    }

    public void put(String key, String value){
        int hash = Math.abs(key.hashCode() % capacity);
        Node temp = new Node(key, value);

        if(bucketArray[hash] == null) bucketArray[hash] = temp;
        else{
            Node cur = bucketArray[hash];
            while(cur.next != null){
                if(cur.getKey().equals(key)){
                    cur.setValue(value);
                    return;
                }
                cur = cur.next;
            }
            cur.next = temp;
        }
    }

    public String get(String key){
        int hash = Math.abs(key.hashCode() % capacity);
        Node cur = bucketArray[hash];
        while(cur != null){
            if(cur.getKey().equals(key)) return cur.getValue();
            cur = cur.next;
        }
        return null;
    }

    class Node {
        private String key;
        private String value;
        private Node next;

        public Node(){}

        public Node(String key, String value){
            this.key = key;
            this.value = value;
        }

        public getKey(){
            return this.key;
        }

        public getValue(){
            return this.value;
        }

        public getNext(){
            return this.next;
        }

        public setNext(Node next){
            this.next = next;
        }

        public setValue(String value){
            this.value = value;
        }
    }
}
