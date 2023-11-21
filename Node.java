public class Node {
    protected String item;
    protected Node next = null;
    protected Node previous = null;

    Node(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}
