import java.util.Scanner;

public class PrefixToInfix {

    Scanner sc = new Scanner(System.in);

    System.out.println("Enter a prefix expression: ");
    String prefic = sc.nextLine();

    public PrefixToInfix(String prefix) {
        this.prefix = prefix;
    }

    static boolean isOperator(String token) {
        switch(token) {
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
        }
        return false;
    }


}