import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.LinkedList;

public class StringDoubleEndedQueueImpl implements StringDoubleEndedQueue {
    private LinkedList<String> queue;

    public StringDoubleEndedQueueImpl() {
        this.queue = new LinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void addFirst(String item) {
        queue.addFirst(item);
    }

    @Override
    public String removeFirst() throws NoSuchElementException {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue.removeFirst();
    }

    @Override
    public void addLast(String item) {
        queue.addLast(item);
    }

    @Override
    public String removeLast() throws NoSuchElementException {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue.removeLast();
    }

    @Override
    public String getFirst() throws NoSuchElementException {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue.getFirst();
    }

    @Override
    public String getLast() throws NoSuchElementException {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue.getLast();
    }

    @Override
    public void printQueue(PrintStream stream) {
        for (String item : queue) {
            stream.println(item);
        }
    }

    @Override
    public int size() {
        return queue.size();
    }
}
