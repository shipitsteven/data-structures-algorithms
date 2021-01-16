/**
 * Queue implemented with LinkedList and Generics
 */
public class LinkedQueue<T> {
    Node first;
    Node last;

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(T value) {
        Node oldLast = last;
        last = new Node(value);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        } else {
            T value = first.data;
            first = first.next;
            if (isEmpty()) {
                last = null;
            }
            return value;
        }
    }

    private class Node {
        T data;
        Node next;

        Node(T value) {
            data = value;
        }
    }

    // public static void main(String[] args) {
    // LinkedQueue<String> test = new LinkedQueue<>();
    // test.enqueue("first");
    // test.enqueue("second");
    // System.out.println(test.dequeue()); // first
    // System.out.println(test.dequeue()); // second
    // System.out.println(test.dequeue()); // NoSuchElementException
    // }
}
