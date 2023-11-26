/*
 * course: Data Structures (INF231) (aueb, Informatics, 2023)
 * file: StringDoubleEndedQueueImpl.java
 * authors: Rigas Sassalos (3220178), Evgenia Lazana (3220104)
 */


import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringDoubleEndedQueueImpl implements StringDoubleEndedQueue {

    private Node head = null;
    private Node tail = null;
    private int count = 0;

    public boolean isEmpty() { // checks if the queue is empty
        if(head == null) {
            return true;
        } else {
            return false;
        }
    }

    public void addFirst(String item) { // adds an item at the front of the queue
        Node n = new Node(item);
        if (isEmpty()) {
            head = n;
            tail = n;
            count = 1;
        } else {
            n.setNext(head); // sets the next node of the new head to the previous head
            head = n;
            n.setPrevious(null); // sets the previous node of the new head to null
            count++;
        }
    }

    public String removeFirst() throws NoSuchElementException { // removes and returns the item at the front of the queue
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Node n = head;
            head = head.getNext();
            if (head == null) { // if there is only one node in the queue
                tail = null;
                count = 0;
            } else {
                head.setPrevious(null); // sets the previous node of the new head to null
                count--;
            }
            return n.getItem(); // returns the item of the removed node
        }
    }

    public void addLast(String item) {
        Node n = new Node(item);
        if (isEmpty()) {
            head = n;
            tail = n;
            count = 1;
        } else {
            tail.setNext(n); // sets the next node of the previous tail to the new tail
            n.setPrevious(tail); // sets the previous node of the new tail to the previous tail
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
            if (tail == null) { // if there is only one node in the queue
                head = null;
                count = 0;
            } else {
                tail.setNext(null); // sets the next node of the new tail to null
                count--;
            }
            return n.getItem(); // returns the item of the removed node
        }
    }

    public String getFirst() throws NoSuchElementException { // returns the item at the front of the queue
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return head.getItem();
        }
    }

    public String getLast() throws NoSuchElementException { // returns the item at the end of the queue
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return tail.getItem();
        }
    }

    public void printQueue(PrintStream stream) { // prints the items of the queue
        Node n = head;
        while (n != null) {
            stream.println(n.getItem());
            n = n.getNext();
        }
    }

    public int size() { // returns the size of the queue
        return count;
    }


}