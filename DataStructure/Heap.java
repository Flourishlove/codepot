// use array to store heap, (this is maxHeap)
// heap 使用的树形结构必须是完全二叉树，以maxheap为例，任意子树根节点要大于其所有孩子
// 这里我们使用array来作为存储结构，因为我们构造的是完全二叉树，假设父节点序号为i，那么左孩子为2*i,右孩子为2*i+1 （从1开始,代码是从0开始）
// insert操作是把新节点直接放在array最后一个，再经过shiftUp操作不断上移
// delete操作是把根节点拿走后，array最后一个元素放到根节点，再和左右孩子比较，shiftDown操作

// heap sort 的思想本质上就是delete的思想，每次拿掉根节点之后，放在数组的末尾，这样当把整个堆都拿掉一次之后，就有了inplace的sorted list

public class Heap<T extends Comparable<T>>{
    private ArrayList<T> items;

    public Heap(){
        items = new ArrayList<T>();
    }

    private void shifUp(){
        int k = items.size()-1;
        while(k > 0){
            int p = (k-1)/2;
            T item = ArrayList.get(k);
            T parent = ArrayList.get(p);
            if(item.compareTo(parent) > 0){
                items.set(k, parent);
                items.set(p, item);
                k = p;
            }
            else{
                break;
            }
        }
    }

    public void insert(T item){
        items.add(item);
        shifUp();
    }

    private void shifDown(){
        int k = 0;
        int l = 2*k+1;
        int r = l+1;

        while(l < items.size()){
            int r = l+1;
            int max = l;
            if(r < items.size()){ //has right child
                if(items.get(r).compareTo(items.get(l)) > 0) max++;
            }

            if(items.get(max).compareTo(items.get(k)) > 0){
                T temp = items.get(k);
                items.set(k, items.get(max));
                items.set(max, temp);
                k = max;
                l = 2*k+1;
            }
            else{
                break;
            }
        }
    }

    public T delete() throws NoSuchElementException{
        if(items.size() == 0) throw new NoSuchElementException();
        if(items.size() == 1) return items.remove(0);
        T hold = items.get(0);
        items.set(0, items.remove(items.size()-1));
        shifDown();
        return hold;
    }

    public int size(){
        return items.size();
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }
}