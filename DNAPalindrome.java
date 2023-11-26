/*
 * course: Data Structures (INF231) (aueb, Informatics, 2023)
 * file: DNAPalindrome.java
 * authors: Rigas Sassalos (3220178), Evgenia Lazana (3220104)
 */


import java.util.Scanner;

public class DNAPalindrome {
    private String dna;

    public DNAPalindrome(String dna) { // constructor
        this.dna = dna;
    }

    public boolean isPalindrome() {

        String[] dnaArray = dna.split(""); // splits the dna sequence into an array
        StringDoubleEndedQueueImpl queue = new StringDoubleEndedQueueImpl(); // creates a queue

        for (int i = 0; i < dnaArray.length; i++) {
            switch (dnaArray[i]) { // checks if the dna sequence is valid
                case "A":
                    queue.addLast("T");
                    break;
                case "T":
                    queue.addLast("A");
                    break;
                case "C":
                    queue.addLast("G");
                    break;
                case "G":
                    queue.addLast("C");
                    break;
            }
        }

        String reverse = "";
        while (!queue.isEmpty()) {
            reverse += queue.removeLast(); // adds the items of the queue to a string from the end to the front
        }

        if (dna.equals(reverse)) { // checks if the dna sequence is a palindrome
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
            if (!dna.substring(i, i + 1).matches("[ATCG]")) { // checks if the dna sequence is valid
                flag = false;
                break; // if the dna sequence is invalid, the loop stops
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
