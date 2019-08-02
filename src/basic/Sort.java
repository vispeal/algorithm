package basic;
import java.util.ArrayList;
import java.util.List;

public class Sort {
    public static void selectSort(List<Integer> list){
        int min = 0;
        int tmp = 0;
        for (int i=0; i < list.size(); i++){
            min = i;
            for (int j=i; j < list.size(); j++){
                if (list.get(j) < list.get(min)){
                    min = j;
                }
            }
            tmp = list.get(i);
            list.set(i, list.get(min));
            list.set(min, tmp);
        }
    }

    public static void arraySelectSort(Integer[] array){
        int min = 0;
        int tmp = 0;
        for (int i=0; i < array.length; i++){
            min = i;
            for (int j=i; j < array.length; j++){
                if (array[j] < array[min]){
                    min = j;
                }
            }
            tmp = array[i];
            array[i] = array[min];
            array[min] = tmp;
        }
    }

    public static void insertSort(Integer[] array){
        int tmp = 0;
        int j = 0;
        for (int i=0; i < array.length; i++){
            tmp = array[i];
            for (j=i-1; j >= 0 && tmp < array[j]; j--){
                array[j+1] = array[j];
            }
            array[j+1] = tmp;
        }
    }

    public static void bubbleSort(Integer[] array){
        int tmp = 0;
        for(int i=1; i < array.length; i++){
            for(int j=0; j < array.length - i; j++){
                if (array[j] > array[j+1]){
                    tmp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public static void mergeSort(int[] array, int start, int end){
        if (start >= end){
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid+1, end);
        merge(array, mid, start, end);
    }

    public static void merge(int[] array, int mid, int start, int end){
        int current1 = start;
        int current2 = mid+1;
        int current3 = 0;
        int tmp[] = new int[end-start+1];
        while(current1 <= mid && current2 <= end){
            if (array[current1] < array[current2]){
                tmp[current3++] = array[current1++];
            } else {
                tmp[current3++] = array[current2++];
            }
        }
        while(current1 <= mid){
            tmp[current3++] = array[current1++];
        }
        while(current2 <= end){
            tmp[current3++] = array[current2++];
        }
        for(current3=0; current3 < end - start + 1; current3++){
            array[start + current3] = tmp[current3];
        }
    }

    public static void quickSort(int[] array, int start, int end){
        if (start < end){
            int pivotIndex = partition(array, start, end);
            quickSort(array, start, pivotIndex-1);
            quickSort(array, pivotIndex+1, end);
        }
    }

    public static int partition(int[] array, int start, int end){
        int pivot = array[start];
        int low = start+1;
        int high = end;
        int tmp = 0;
        while(low < high){
            while(low <= high && array[low] <= pivot){
                low++;
            }
            while(low <= high && array[high] >= pivot){
                high--;
            }
            if(high > low){
                tmp = array[low];
                array[low] = array[high];
                array[high] = tmp;
            }

        }
        while(high > start && array[high] >= pivot){
            high--;
        }
        if (pivot > array[high]){
            array[start] = array[high];
            array[high] = pivot;
            return high;
        } else {
            return start;
        }

    }

    public static void testSort(){
//        Integer[] array = {9, 2, 7, 5, 8, 6, 3, 21};
        int[] array = {9, 2, 7, 5, 8, 6, 3, 21};
//        arraySelectSort(array);
//        insertSort(array);
//        bubbleSort(array);
//        mergeSort(array, 0, array.length-1);
        quickSort(array, 0, array.length-1);
        for(int value: array){
            System.out.print(value + " ");
        }
    }

    public static <E extends Comparable<E>> void heapSort(E[] list){
        Heap<E> heap = new Heap<>();
        for(E e: list){
            heap.add(e);
        }
        for(int i=list.length-1; i>=0; i--){
            list[i] = heap.remove();
        }
    }

    public static void testHeapSort(){
        Integer[] array = {9, 2, 7, 5, 8, 6, 3, 21};
//        int[] array = {9, 2, 7, 5, 8, 6, 3, 21};
//        arraySelectSort(array);
//        insertSort(array);
//        bubbleSort(array);
//        mergeSort(array, 0, array.length-1);
        heapSort(array);
        for(int value: array){
            System.out.print(value + " ");
        }
    }
}
