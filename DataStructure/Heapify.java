// use array to store heap, (this is maxHeap)
// 建堆（把一个无序数组转为堆）主要有两种方式，1.top-down; 2.bottom-up; heapify特指第二种方式
// 1. 自顶而下的方法思想是从根节点开始，把数组一个一个插入到堆中，每次插入都向上调整，保持最大堆的性质，时间复杂度O(nlogn)。
//    如果从数组角度理解，就是从左往右
// 2. 自底而上的方法思想是从底部的节点开始，不断找到它们的父亲节点，看父亲节点是否需要往下调整，时间复杂度O(n)。
//    这里的时间复杂度可以用等比数列相加推导得出


public class Solution{
    private void shifUp(ArrayList<T> origin, int index){
        int k = index;
        while(k > 0){
            int p = (k-1)/2;
            T item = ArrayList.get(k);
            T parent = ArrayList.get(p);
            if(item.compareTo(parent) > 0){
                origin.set(k, parent);
                origin.set(p, item);
                k = p;
            }
            else{
                break;
            }
        }
    }

    private void shifDown(ArrayList<T> origin, int index){
        int k = 0;
        int l = 2*k+1;
        int r = l+1;

        while(l < items.size()){
            int r = l+1;
            int max = l;
            if(r < items.size()){. //has right child
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

    public ArrayList<T> createHeap(ArrayList<T> origin){
        // 从第二个节点开始调整，从左往右
        for(int i = 1; i < origin.size(); i++){
            shifUp(origin, i);
        }
    }

    public ArrayList<T> heapify(ArrayList<T> origin){
        for(int i = origin.size()-1; i >= 0; i--){
            shifDown(origin, i);
        }
    }


}