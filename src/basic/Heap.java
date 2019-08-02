package basic;
import java.util.List;
import java.util.ArrayList;

public class Heap<E extends Comparable<E>> {
    private List<E> list = new ArrayList<>();

    public Heap(){
    }

    public Heap(Object[] e){
        for(int i=0; i < e.length; i++){
            add((E)e[i]);
        }
    }

    public void add(E newObject){
        int current = getSize();
        list.add(newObject);
        int parent = (current - 1) / 2;
        E tmp;
        while(list.get(current).compareTo(list.get(parent)) > 0 && current > 0){
            tmp = list.get(current);
            list.set(current, list.get(parent));
            list.set(parent, tmp);
            current = parent;
            parent = (current - 1) / 2;
        }
    }

    public E remove(){
        if(getSize() == 0){
            return null;
        }
        E root = list.get(0);
        int size = getSize()-1;
        list.set(0, list.get(size));
        list.remove(size);
        int current = 0;
        E tmp;
        while(current < size){
            int leftChild = current * 2 +1;
            if(leftChild >= size){
                break;
            }
            int rightChild = current * 2 + 2;
            int maxNode = leftChild;
            if(rightChild < size && list.get(maxNode).compareTo(list.get(rightChild)) < 0){
                maxNode = rightChild;
            }
            if (list.get(current).compareTo(list.get(maxNode)) < 0){
                tmp = list.get(current);
                list.set(current, list.get(maxNode));
                list.set(maxNode, tmp);
                current = maxNode;
            } else {
                break;
            }

        }

        return root;
    }

    public int getSize(){
        return list.size();
    }

}
