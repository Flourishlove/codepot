// constant time for set, get, isEmpty, size, add(almost), other operation in O(n)
// ArrayList is not synchronized (Vector is synchronized)
// permits all elements (including null)

import java.util.Arrays;

public class ArrayList {
    private size = 0;
    private static final DEFAULT_CAPACITY = 10;
    private Object[] elements;

    public ArrayList(){
        elements = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity){
        elements = new Object[capacity];
    }

    public void add(E e){
        if(size == elements.length){
            ensureCap();
        }
        elements[size++] = e;
    }

    private void ensureCap(){
        int newCap = elements.length * 2;  //reduce the amount of incremental reallocation.
        elements = Arrays.copyOf(elements, newSize);
    }

    public E get(int index){
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i );
        return (E) elements[i];
    }

    public E remove(int index){
        size--;
        for(int i = index; i < size; i++){
            elements[i] = elements[i+1];
        }
    }
}