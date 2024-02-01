public class List {
    private Node head;

    private static class Node {
        LargeDepositor item;
        Node next;

        Node(LargeDepositor item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public void add(LargeDepositor item) {
        if (head == null) {
            head = new Node(item, null);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(item, null);
        }
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

}
