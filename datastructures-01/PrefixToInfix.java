/*
 * course: Data Structures (INF231) (aueb, Informatics, 2023)
 * file: PrefixToInfix.java
 * authors: Rigas Sassalos (3220178), Evgenia Lazana (3220104)
 */


import java.util.Scanner;

public class PrefixToInfix {
    private String prefix;

    public PrefixToInfix(String prefix) { // constructor
        this.prefix = prefix;
    }

    public boolean isOperator(String item) { // checks if the item is an operator
        switch (item) {
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
        }
        return false;
    }

    public boolean isValid(String[] prefixArray) { // checks if the prefix expression is valid
        boolean flag = false;
        if (isOperator(prefixArray[0])) {
            flag = true;
        }
        for (int i = 0; i < prefixArray.length; i++) {
            if (!prefixArray[i].matches("-?[0-9]|9") && !isOperator(prefixArray[i])) {
                flag = false;
            }
        }
        return flag;
    }

    private void clear() { // clears the queue
        StringDoubleEndedQueueImpl queue = new StringDoubleEndedQueueImpl();
        while (!queue.isEmpty()) {
            queue.removeFirst();
        }
    }

    public String convert() { // converts the prefix expression to infix
        clear();

        String[] prefixArray = prefix.split(""); // splits the prefix expression into an array

        if (isValid(prefixArray)) {
            StringDoubleEndedQueueImpl queue = new StringDoubleEndedQueueImpl(); // creates a queue

            for (int i = 0; i < prefixArray.length; i++) {
                queue.addLast(prefixArray[i]); // adds the items of the array to the queue
            }

            String infix = "";
            for(int i = prefix.length() - 1; i >= 0; i--) {
                String item = prefixArray[i];

                if (isOperator(item)) {
                    String operand1 = queue.removeFirst();
                    String operand2 = queue.removeFirst();
                    infix = "(" + operand1 + item + operand2 + ")";
                    queue.addFirst(infix); // adds the infix expression to the front of the queue
                } else {
                    queue.addFirst(item); // adds the item to the front of the queue
                }
            }

            return queue.removeFirst(); // returns the infix expression
        } else {
            return "Invalid prefix expression";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a prefix expression: ");
        String prefix = sc.nextLine().trim();

        PrefixToInfix pti = new PrefixToInfix(prefix);
        System.out.println(pti.convert());
    }
}