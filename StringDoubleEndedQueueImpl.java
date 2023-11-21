import org.w3c.dom.Node;

public class StringDoubleEndedQueueImpl implements StringDoubleEndedQueue {

    private Node head = null;
    private Node tail = null;

    public StringDoubleEndedQueueImpl() {
    }

    public boolean isEmpty() {
        if(head == null) {
            return true;
        } else {
            return false;
        }
    }

    public void addFirst(String item) {
        Node n = new Node(item);
        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            n.setNext(head);
            head = n;
        }
    }
}