package basic;
import java.util.Stack;


public class EvaluateExpression {
    private Stack<Integer> operandStack;

    private Stack<String> operatorStack;
    private String expression;

    private EvaluateExpression(String expression){
        this.expression = expression;
        operatorStack = new Stack<>();
        operandStack = new Stack<>();
    }

    public static void test() {
        String ex = "( ( 7 * ( 4 / 2 ) - ( 2 * 5 ) ) * 6 - 9 ) * 2 + 9 - 5 + 3 * 7";
        System.out.println(ex);
        int value = new EvaluateExpression(ex).getValue();
        System.out.print(value);
    }

    private int getValue(){
        String[] tokens = expression.split(" ");
        String operator;
        for (String token: tokens){
            if (token.equals("(")){
                operatorStack.push(token);
            }
            else if (token.equals(")")){
                while (!operatorStack.isEmpty()){
                    operator = operatorStack.peek();
                    if (operator.equals("(")){
                        operatorStack.pop();
                        break;
                    }
                    else {
                        processOperator();
                    }
                }
            }
            else if (token.equals("-") || token.equals("+")){
                if (!operatorStack.isEmpty()) {
                    operator = operatorStack.peek();
                    if (!operator.equals("(")){
                        processOperator();
                    }
                }
                operatorStack.push(token);
            }
            else if (token.equals("*") || token.equals("/")){
                if (!operatorStack.isEmpty()) {
                    operator = operatorStack.peek();
                    if (operator.equals("*") || operator.equals("/")){
                        processOperator();
                    }
                }
                operatorStack.push(token);
            }
            else {
                operandStack.push(new Integer(token));
            }
        }
        while (!operatorStack.isEmpty()){
            processOperator();
        }

        return operandStack.pop();
    }

    private void processOperator(){
        String op = operatorStack.pop();
        int op2 = operandStack.pop();
        int op1 = operandStack.pop();
        switch (op){
            case "+": operandStack.push(op1 + op2); break;
            case "-": operandStack.push(op1 - op2); break;
            case "*": operandStack.push(op1 * op2); break;
            case "/": operandStack.push(op1 / op2); break;
        }
    }
}
