/*
 * course: Data Structures (INF231) (aueb, Informatics, 2023)
 * file: DNAPalindrome.java
 * authors: Rigas Sassalos (3220178), Evgenia Lazana (3220***)
 */


import java.util.Scanner;

public class DNAPalindrome {
    private String dna;

    public DNAPalindrome(String dna) {
        this.dna = dna;
    }

    public boolean isPalindrome() {

        String[] dnaArray = dna.split("");
        StringDoubleEndedQueueImpl queue = new StringDoubleEndedQueueImpl();

        for (int i = 0; i < dnaArray.length; i++) {
            if (dnaArray[i].equals("A")) {
                queue.addLast("T");
            } else if (dnaArray[i].equals("T")) {
                queue.addLast("A");
            } else if (dnaArray[i].equals("C")) {
                queue.addLast("G");
            } else if (dnaArray[i].equals("G")) {
                queue.addLast("C");
            }
        }

        String reverse = "";
        while (!queue.isEmpty()) {
            reverse += queue.removeLast();
        }

        if (dna.equals(reverse)) {
            return true;
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter DNA sequence: ");
        String dna = sc.nextLine();
        DNAPalindrome dnaPalindrome = new DNAPalindrome(dna);
        boolean flag = true;
        for (int i = 0; i < dna.length(); i++) {
            if (!dna.substring(i, i + 1).matches("[ATCG]")) {
                flag = false;
                break;
            }
        }
        if (flag) {
            if (dnaPalindrome.isPalindrome()) {
                System.out.println("Palindrome");
            } else {
                System.out.println("Not a palindrome");
            }
        } else {
            System.out.println("Invalid DNA sequence");
        }

    }
}
