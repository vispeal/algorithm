package review;

public class QuickSort {
    public static void sort(int[] array){
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int start, int end){
        if (start < end){
            int mid = partition(array, start, end);
            sort(array, start, mid -1);
            sort(array, mid + 1, end);
        }
    }

    private static int partition(int[] array, int start, int end){
        int value = array[start];
        int low = start + 1;
        int high = end;
        while(low < high){
            while (low <= high && array[low] <= value)
                low++;
            while (low <= high && array[high] > value)
                high--;
            if (low < high){
                int tmp = array[low];
                array[low] = array[high];
                array[high] = tmp;
            }
        }
//        while (high > start && array[high] >= value)
//            high--;

        if (array[high] < value){
            array[start] = array[high];
            array[high] = value;
            return high;
        } else {
            return start;
        }

    }

    public static void test(){
        int[] array1 = {7, 4, 21, 3, 8, 9, 2, 5};
        int[] array = {7, 4, 21, 3, 8, 9, 2, 55};
//        int[] array = {77, 4, 21, 3, 8, 9, 2, 55};
        sort(array);
        for (int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
    }
}
