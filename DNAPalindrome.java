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

        String reverse = "";
        for (int i = 0; i < dnaArray.length; i++) {

            String complementaryNucleotide = "";

            if (dnaArray[i].equals("A")) {
                complementaryNucleotide = "T";
            } else if (dnaArray[i].equals("T")) {
                complementaryNucleotide = "A";
            } else if (dnaArray[i].equals("C")) {
                complementaryNucleotide = "G";
            } else if (dnaArray[i].equals("G")) {
                complementaryNucleotide = "C";
            }

            queue.addLast(complementaryNucleotide);

            reverse = complementaryNucleotide + reverse;
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
