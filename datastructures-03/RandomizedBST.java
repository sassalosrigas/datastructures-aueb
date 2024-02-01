import java.util.Scanner;

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
            depositor.setSavings(savings);
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

    public double getMeanSavings() {
        if (root == null) {
            return 0.0;
        }

        SumAndCount result = new SumAndCount();
        calculateSumAndCount(root, result);

        if (result.count == 0) {
            return 0.0;
        }

        return result.sum / result.count;
    }

    private void calculateSumAndCount(TreeNode node, SumAndCount result) {
        if (node != null) {
            calculateSumAndCount(node.left, result);
            result.sum += node.item.getSavings();
            result.count++;
            calculateSumAndCount(node.right, result);
        }
    }

    private class SumAndCount {
        double sum;
        int count;
    }

    public void printΤopLargeDepositors(int k) {
        if (root == null) {
            System.out.println("Error: Tree is empty.");
            return;
        }

        PQ topDepositors = new PQ(k);

        traverseAndPopulatePQ(root, topDepositors, k);

        System.out.println("Top " + k + " large depositors:");
        while (!topDepositors.isEmpty()) {
            System.out.println(topDepositors.getmin());
        }
    }

    private void traverseAndPopulatePQ(TreeNode node, PQ topDepositors, int k) {
        if (node != null) {
            traverseAndPopulatePQ(node.left, topDepositors, k);
        }

        if (topDepositors.size() < k) {
            topDepositors.offer(node.item);
        } else {
            LargeDepositor lowestScoreDepositor = topDepositors.peek();
            if (compareSuspicionScore(node.item, lowestScoreDepositor) > 0) {
                topDepositors.poll();
                topDepositors.offer(node.item);
            }
        }

        traverseAndPopulatePQ(node.right, topDepositors, k);
    }

    private int compareSuspicionScore(LargeDepositor depositor1, LargeDepositor depositor2) {
        if (depositor1.getTaxedIncome() < 8000 && depositor2.getTaxedIncome() < 8000) {
            return Double.compare(Double.MAX_VALUE, Double.MAX_VALUE);
        } else {
            double score1 = depositor1.getSavings() - depositor1.getTaxedIncome();
            double score2 = depositor2.getSavings() - depositor2.getTaxedIncome();
            return Double.compare(score1, score2);
        }
    }

    public void printByAFM() {
        if (root == null) {
            System.out.println("Tree is empty.");
        } else {
            System.out.println("Printing tree by AFM:");
            inOrderTraversal(root);
        }
    }

    private void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.item);
            inOrderTraversal(node.right);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RandomizedBST tree = new RandomizedBST();

        while(true) {
            System.out.println("Menu:");
            System.out.println("1. Insert Large Depositor");
            System.out.println("2. Load Large Depositors from file");
            System.out.println("3. Update savings of Large Depositor");
            System.out.println("4. Search Large Depositor by AFM");
            System.out.println("5. Search Large Depositors by last name");
            System.out.println("6. Remove Large Depositor by AFM");
            System.out.println("7. Get mean savings");
            System.out.println("8. Print top Large Depositors");
            System.out.println("9. Print Large Depositors by AFM");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter AFM, first name, last name, savings, taxed income:");
                    int AFM = scanner.nextInt();
                    scanner.nextLine();
                    String firstName = scanner.nextLine();
                    String lastName = scanner.nextLine();
                    double savings = scanner.nextDouble();
                    double taxedIncome = scanner.nextDouble();
                    LargeDepositor depositor = new LargeDepositor(AFM, firstName, lastName, savings, taxedIncome);
                    tree.insert(depositor);
                    break;
                case 2:
                    System.out.println("Enter filename:");
                    String filename = scanner.nextLine();
                    tree.load(filename);
                    break;
                case 3:
                    System.out.println("Enter AFM and savings:");
                    AFM = scanner.nextInt();
                    savings = scanner.nextDouble();
                    tree.updateSavings(AFM, savings);
                    break;
                case 4:
                    System.out.println("Enter AFM:");
                    AFM = scanner.nextInt();
                    tree.searchByAFM(AFM);
                    break;
                case 5:
                    System.out.println("Enter last name:");
                    lastName = scanner.nextLine();
                    tree.searchByLastName(lastName);
                    break;
                case 6:
                    System.out.println("Enter AFM:");
                    AFM = scanner.nextInt();
                    tree.remove(AFM);
                    break;
                case 7:
                    System.out.println("Mean savings: " + tree.getMeanSavings());
                    break;
                case 8:
                    System.out.println("Enter k:");
                    int k = scanner.nextInt();
                    tree.printΤopLargeDepositors(k);
                    break;
                case 9:
                    tree.printByAFM();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }    



    
}
