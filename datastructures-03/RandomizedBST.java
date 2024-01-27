import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.tree.TreeNode;

class RandomizedBST implements TaxEvasionInterface {
    private class TreeNode {
        LargeDepositor item;
        TreeNode left;
        TreeNode right;
        int N;
    }

    private TreeNode root;

    private int size(TreeNode node) {
        if (node == null) return 0;
        return node.N;
    }

    private void updateSize(TreeNode node) {
        if (node != null){
            node.N = size(node.left) + size(node.right) + 1;
        }
    }

    void insert(LargeDepositor item) {
        root = insert(root, item);
    }

    private TreeNode insert(TreeNode node, LargeDepositor item) {
        if (node == null) {
            return new TreeNode(item);
        }

        if (item.key() == node.item.key()) {
            System.out.println("Error: Depositor with AFM " + item.key() + " already exists.");
            System.exit(1);
        }

        if (Math.random() * (size(node) + 1) < 1.0) {
            return insertAsRoot(item, node);
        }

        if (item.key() < node.item.key()) {
            node.left = insert(node.left, item);
        } else {
            node.right = insert(node.right, item);
        }

        updateSize(node);
        return node;
    }

    private TreeNode insertAsRoot(LargeDepositor item, Node h) {
        TreeNode x = new TreeNode(item);
        x.left = h;
        x.right = h.right;
        h.right = null;
        updateSize(x);
        return x;
    }

    void load(String filename) {
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            int AFM = Integer.parseInt(tokens[0]);
            String firstName = tokens[1];
            String lastName = tokens[2];
            double savings = Double.parseDouble(tokens[3]);
            double taxedIncome = Double.parseDouble(tokens[4]);
            LargeDepositor item = new LargeDepositor(AFM, firstName, lastName, savings, taxedIncome);
            insert(item);
        }
        
    }

    void updateSavings(int AFM, double savings) {
        LargeDepositor depositor = searchByAFM(AFM);
        if (depositor != null) {
            depositor.setSavings(savings);
        }
    }

    LargeDepositor searchByAFM(int AFM) {
        return searchByAFM(root, AFM);
    }  

    private LargeDepositor searchByAFM(TreeNode node, int AFM) {
        while (node!=null) {
            if (AFM < node.item.key()) {
                node = node.left;
            } else if (AFM > node.item.key()) {
                node = node.right;
            } else {
                return node.item;
            }
        }
    }

    List searchByLastName(String lastName) {
        List<LargeDepositor> result = new ArrayList<>();
        searchByLastName(root, lastName, result);
        return result;
    }

    private void searchByLastName(TreeNode node, String lastName, List<LargeDepositor> result) {
        if (node != null) {
            searchByLastName(node.left, lastName, result);
            if (node.item.getLastName().equals(lastName)) {
                result.add(node.item);
            }
            searchByLastName(node.right, lastName, result);
        }
    }

    void remove(int AFM) {
        root = remove(root, AFM);
    }

    private TreeNode remove(TreeNode node, int AFM) {
        if (node == null) {
            return null;
        }

        if (AFM < node.item.key()) {
            node.left = remove(node.left, AFM);
        } else if (AFM > node.item.key()) {
            node.right = remove(node.right, AFM);
        } else {
            node = join(node.left, node.right);
        }
        updateSize(node);
        return node;
    }

    private TreeNode join(TreeNode left, TreeNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if (Math.random() * (size(left) + size(right)) < size(left)) {
            left.right = join(left.right, right);
            updateSize(left);
            return left;
        } else {
            right.left = join(left, right.left);
            updateSize(right);
            return right;
        }
    }

    double getMeanSavings() {
        if (root == null) {
            return 0.0;
        } else {
            return getSumSavings(root) / root.N;
        }
    }

    private double getSumSavings(TreeNode node) {
        if (node == null) {
            return 0.0;
        } else {
            return getSumSavings(node.left) + getSumSavings(node.right) + node.item.getSavings();
        }
    }

    void printΤopLargeDepositors(int k) {
        List<LargeDepositor> result = new ArrayList<>();
        printΤopLargeDepositors(root, result);
        Collections.sort(result, (a, b) -> Double.compare(b.getSavings(), a.getSavings()));

        System.out.println("Top " + k + " large depositors:");
        for (int i = 0; i < k; i++) {
            System.out.println(result.get(i));
        }
    }

    private void printΤopLargeDepositors(TreeNode node, List<LargeDepositor> result) {
        if (node != null) {
            printΤopLargeDepositors(node.left, result);
            result.add(node.item);
            printΤopLargeDepositors(node.right, result);
        }
    }

    void printByAFM() {
        printByAFM(root);
    }

    private void printByAFM(TreeNode node) {
        if (node != null) {
            printByAFM(node.left);
            System.out.println(node.item);
            printByAFM(node.right);
        }
    }

    public static void main(String[] args) {
        RandomizedBST tree = new RandomizedBST();
        tree.load("datastructures-03/large-depositors.txt");
        tree.printByAFM();
        System.out.println("Mean savings: " + tree.getMeanSavings());
        tree.printΤopLargeDepositors(10);
        tree.updateSavings(123456789, 1000000.0);
        tree.printΤopLargeDepositors(10);
    }
}
