import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringDoubleEndedQueueImpl implements StringDoubleEndedQueue {

    private Node head = null;
    private Node tail = null;
    private int count = 0;

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
            count = 1;
        } else {
            n.setNext(head);
            head = n;
            n.setPrevious(null);
            count++;
        }
    }

    public String removeFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Node n = head;
            head = head.getNext();
            if (head == null) {
                tail = null;
                count = 0;
            } else {
                head.setPrevious(null);
                count--;
            }
            return n.getItem();
        }
    }

    public void addLast(String item) {
        Node n = new Node(item);
        if (isEmpty()) {
            head = n;
            tail = n;
            count = 1;
        } else {
            tail.setNext(n);
            n.setPrevious(tail);
            tail = n;
            count++;
        }       
    }

    public String removeLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Node n = tail;
            tail = tail.getPrevious();
            if (tail == null) {
                head = null;
                count = 0;
            } else {
                tail.setNext(null);
                count--;
            }
            return n.getItem();
        }
    }

    public String getFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return head.getItem();
        }
    }

    public String getLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return tail.getItem();
        }
    }

    public void printQueue(PrintStream stream) {
        Node n = head;
        while (n != null) {
            stream.println(n.getItem());
            n = n.getNext();
        }
    }

    public int size() {
        return count;
    }


}