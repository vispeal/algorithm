package basic;

public class Small {

    public static int fib(int n){
        int fib0 = 0;
        int fib1 = 1;
        int fib2 = 1;

        if (n <= 0){
            return fib0;
        } else if (n == 1){
            return fib1;
        } else if (n == 2){
            return fib2;
        }
        for(int i = 3; i <= n; i++){
            fib0 = fib1;
            fib1 = fib2;
            fib2 = fib0 + fib1;
        }
        return fib2;
    }
}
