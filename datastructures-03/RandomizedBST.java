import java.util.Scanner;

import javax.swing.tree.TreeNode;

class RandomizedBST implements TaxEvasionInterface {
    private class TreeNode {
        public TreeNode(LargeDepositor item) {
            this.item = item;
            this.N = 1;
        }
        LargeDepositor item;
        TreeNode left;
        TreeNode right;
        int N;
    }

    private TreeNode root;

    public void insert(LargeDepositor item) {
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

        if (Math.random() * (size(node) + 1) <  1.0) {
            return insertAtRoot(node, item);
        }

        if (item.key() < node.item.key()) {
            node.left = insert(node.left, item);
        } else {
            node.right = insert(node.right, item);
        }

        updateSize(node);
        return node;
    }

    private TreeNode insertAtRoot(TreeNode node, LargeDepositor item) {
        if (node == null) {
            return new TreeNode(item);
        }

        if (item.key() < node.item.key()) {
            node.left = insertAtRoot(node.left, item);
            node = rotateRight(node);
        } else {
            node.right = insertAtRoot(node.right, item);
            node = rotateLeft(node);
        }

        return node;
    }

    private int size(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return node.N;
    }

    private void updateSize(TreeNode node) {
        node.N = size(node.left) + size(node.right) + 1;
    }

    private TreeNode rotateRight(TreeNode node) {
        TreeNode x = node.left;
        node.left = x.right;
        x.right = node;
        updateSize(node);
        updateSize(x);
        return x;
    }

    private TreeNode rotateLeft(TreeNode node) {
        TreeNode x = node.right;
        node.right = x.left;
        x.left = node;
        updateSize(node);
        updateSize(x);
        return x;
    }

    public void load(String filename) {
        Scanner scanner = new Scanner(filename);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            int AFM = Integer.parseInt(tokens[0]);
            String firstName = tokens[1];
            String lastName = tokens[2];
            double savings = Double.parseDouble(tokens[3]);
            double taxedIncome = Double.parseDouble(tokens[4]);
            LargeDepositor depositor = new LargeDepositor(AFM, firstName, lastName, savings, taxedIncome);
            insert(depositor);
        }
    }

    public void updateSavings(int AFM, double savings) {
        LargeDepositor depositor = searchByAFM(AFM);
        if (depositor == null) {
            System.out.println("Error: Depositor with AFM " + AFM + " does not exist.");
            System.exit(1);
        } else {
            depositor.setSavings(depositor.getSavings()+savings);
        }
    }

    public LargeDepositor searchByAFM(int AFM) {
        TreeNode node = searchByAFM(root, AFM);
        if (node == null) {
            System.out.println("Error: Depositor with AFM " + AFM + " does not exist.");
            return null;
        } else {
            System.out.println("Depositor with AFM " + AFM + " found.");
            System.out.println(node.item.getFirstName() + " " + node.item.getLastName() + " " + node.item.getSavings() + " " + node.item.getTaxedIncome());
            return node.item;
        }
    }

    private TreeNode searchByAFM(TreeNode node, int AFM) {
        if (node == null) {
            return null;
        }

        if (AFM == node.item.key()) {
            return node;
        }

        if (AFM < node.item.key()) {
            return searchByAFM(node.left, AFM);
        } else {
            return searchByAFM(node.right, AFM);
        }
    }

    public List searchByLastName(String lastName) {
        List list = new List();
        searchByLastName(root, lastName, list);
        if (list.isEmpty()) {
            System.out.println("Error: Depositor with last name " + lastName + " does not exist.");
        } else {
            System.out.println("Depositors with last name " + lastName + " found.");
            list.print();
        }
        return list;
    }

    private void searchByLastName(TreeNode node, String lastName, List list) {
        if (node == null) {
            return;
        }

        if (lastName.compareTo(node.item.getLastName()) < 0) {
            searchByLastName(node.left, lastName, list);
        } else if (lastName.compareTo(node.item.getLastName()) > 0) {
            searchByLastName(node.right, lastName, list);
        } else {
            list.add(node.item);
            searchByLastName(node.left, lastName, list);
            searchByLastName(node.right, lastName, list);
        }
    }

    public void remove(int AFM) {
        root = remove(root, AFM);
    }

    private TreeNode remove(TreeNode node, int AFM) {
        if (node == null) {
            System.out.println("Error: Depositor with AFM " + AFM + " does not exist.");
            System.exit(1);
        }

        if (AFM < node.item.key()) {
            node.left = remove(node.left, AFM);
        } else if (AFM > node.item.key()) {
            node.right = remove(node.right, AFM);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                TreeNode x = node;
                node = min(x.right);
                node.right = deleteMin(x.right);
                node.left = x.left;
            }
        }

        updateSize(node);
        return node;
    }

    private TreeNode min(TreeNode node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    private TreeNode deleteMin(TreeNode node) {
        if (node.left == null) {
            return node.right;
        } else {
            node.left = deleteMin(node.left);
            updateSize(node);
            return node;
        }
    }

    

    



    
}
