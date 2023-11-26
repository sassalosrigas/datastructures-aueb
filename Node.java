/*
 * course: Data Structures (INF231) (aueb, Informatics, 2023)
 * file: Node.java
 * authors: Rigas Sassalos (3220178), Evgenia Lazana (3220104)
 */


public class Node {
    protected String item;
    protected Node next = null;
    protected Node previous = null;

    Node(String item) { // constructor
        this.item = item;
    }

    public String getItem() { // returns the item of the node
        return item;
    }

    public Node getNext() { // returns the next node
        return next;
    }

    public void setNext(Node next) { // sets the next node
        this.next = next;
    }

    public Node getPrevious() { // returns the previous node
        return previous;
    }

    public void setPrevious(Node previous) { // sets the previous node
        this.previous = previous;
    }
}
