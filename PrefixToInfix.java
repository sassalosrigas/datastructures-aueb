import java.util.Scanner;

public class PrefixToInfix {
    private String prefix;

    public PrefixToInfix(String prefix) {
        this.prefix = prefix;
    }

    public boolean isOperator(String item) {
        switch (item) {
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
        }
        return false;
    }

    public boolean isValid(String[] prefixArray) {
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

    public String convert() {
        String[] prefixArray = prefix.split("");
        if (isValid(prefixArray)) {
            StringDoubleEndedQueueImpl queue = new StringDoubleEndedQueueImpl();
            for (int i = 0; i < prefixArray.length; i++) {
                queue.addLast(prefixArray[i]);
            }
            String infix = "";
            while (queue.size() > 1) {
                String item = queue.removeFirst();
                if (isOperator(item)) {
                    String operand1 = queue.removeFirst();
                    String operand2 = queue.removeFirst();
                    infix = "(" + operand1 + item + operand2 + ")";
                    queue.addFirst(infix);
                } else {
                    queue.addLast(item);
                }
            }
            return queue.getFirst();
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