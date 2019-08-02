package basic;

public class MaxSubSum {

    private static int maxSubSum1 (int[] array){
        int max = 0;
        for(int i=0; i < array.length; i++){
            for(int j=i; j < array.length; j++){
                int tmp = 0;
                for(int k=i; k <= j; k++){
                    tmp += array[k];
                }
                if (tmp > max){
                    max = tmp;
                }
            }
        }
        return max;
    }

    private static int maxSubSum2(int[] array){
        int max = 0;
        int tmp = 0;
        for(int i=0; i < array.length; i++){
            tmp = 0;
            for(int j=i; j < array.length; j++){
                tmp += array[j];
                if (tmp > max){
                    max = tmp;
                }
            }
        }
        return max;
    }

    private static int maxSubSum3(int[] array){
        int max = 0;
        int tmp = 0;
        for(int i=0; i < array.length; i++){
            tmp += array[i];
            if(tmp > max){
                max = tmp;
            } else if(tmp < 0){
                tmp = 0;
            }
        }
        return max;
    }

    public static void testMax(){
        int[] array = {3, -5, 9, -1, 7, 8, 21, -64, 42, -32};
        System.out.println("max1=" + maxSubSum1(array));
        System.out.println("max2=" + maxSubSum2(array));
        System.out.println("max3=" + maxSubSum3(array));
    }
}
