/*
 * course: Data Structures (INF231) (aueb, Informatics, 2023)
 * file: Node.java
 * authors: Rigas Sassalos (3220178), Evgenia Lazana (3220104)
 */


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

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}
