// add, poll for contant time; get, remove for O(n)
// ArrayList is not synchronized (Vector is synchronized)
// permits all elements (including null)
// clear(), remove(int index),
// use offerFirst, offerLast, pollFirst, pollLast, peekFirst, peekLast when deque

public class LinkedList <T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    public class Node<T>{
        private Node<T> pre;
        private Node<T> next;
        private T data;

        public Node(T data, Node <T> prev, Node <T> next) {
          this.data = data;
          this.prev = prev;
          this.next = next;
        }
    }

    public void add(T t){
        offerLast(t);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void offerLast(T t){
        if(isEmpty()){
            head = tail = new Node<T>(t, null, null);
        }
        else{
            tail.next = new Node<T>(t, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public void offerFirst(T t){
        if(isEmpty()){
            head = tail = new Node<T>(t, null, null);
        }
        else{
            head.pre = new Node<T>(t, null, head);
            head = head.pre;
        }
        size++;
    }

    public T peekFirst(){
        return head.data;
    }

    public T peekLast(){
        return tail.data;
    }

    public T pollFirst(){
        if(isEmpty()) return null;
        T data = head.data;
        head = head.next;
        head.pre = null
        size--;
        return data;
    }

    public T pollLast(){
        if(isEmpty()) return null;
        T data = head.data;
        tail = tail.pre;
        tail.next = null;
        size--;
        return data;
    }

    // O(n)
    public T remove(int index){
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> trav;
        if (index < size/2) {
            trav = head;
            for(int i = 0; i < index; i++){
                trav = trav.next;
            }
        }
        else{
            trav = tail;
            for(int i = 0; i < size-index-1; i--){
                trav = trav.pre;
            }
        }
        trav.pre.next = trav.next;
        trav.next.pre = trav.pre;
        size--;
        return trav.data;
    }

}