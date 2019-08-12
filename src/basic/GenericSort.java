package basic;

public class GenericSort {
    public static void test(){
        Integer[] intArray = {2, 4, 3};
        Double[] doubleArray = {3.4, 1.3, -22.1};
        Character[] charArray = {'a', 'J', 'r'};
        String [] stringArray = {"Tom", "Susan", "Kim"};

        sort(intArray);
        sort(doubleArray);
        sort(charArray);
        sort(stringArray);

        printList(intArray);
        printList(doubleArray);
        printList(charArray);
        printList(stringArray);


    }

    public static <E extends Comparable<E>> void sort(E[] array){
        E currentMin;
        int currentMinIndex;
        for(int i = 0; i < array.length - 1; i++){
            currentMin = array[i];
            currentMinIndex = i;
            for(int j = i + 1; j < array.length; j++){
                if (array[j].compareTo(currentMin) < 0){
                    currentMin = array[j];
                    currentMinIndex = j;
                }
            }
            if (currentMinIndex != i) {
                array[currentMinIndex] = array[i];
                array[i] = currentMin;
            }

        }
    }

    public static <E> void printList(E[] array){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
