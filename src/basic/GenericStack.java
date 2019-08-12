package basic;

import java.util.ArrayList;

public class GenericStack<E> {
    private ArrayList<E> list;

    public GenericStack(){
        list = new ArrayList<>();
    }

    public int getSize(){
        return list.size();
    }

    public E peek(){
        return list.get(getSize() - 1);
    }

    public E pop(){
        return list.remove(getSize() - 1);
    }

    public void push(E e){
        list.add(e);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public static double max(GenericStack<? extends Number> stack){
        double max = stack.pop().doubleValue();
        while(!stack.isEmpty()){
            double value = stack.pop().doubleValue();
            if(value > max){
                max = value;
            }
        }
        return max;
    }

    @Override
    public String toString(){
        return "stack: " + list.toString();
    }

    public static void testStack(){
        GenericStack<String> stack = new GenericStack<>();
        stack.push("London");
        stack.push("Paris");
        stack.push("Berlin");
        System.out.println(stack.toString());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        GenericStack<Double> s2 = new GenericStack<>();
        s2.push(2.5);
        s2.push(3.9);
        s2.push(-7.0);

        System.out.print(max(s2));
    }

}
