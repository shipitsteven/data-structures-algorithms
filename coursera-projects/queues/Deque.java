/* *****************************************************************************
 *  Name: Steven Wang
 *  Date: 1/17/2021
 *  Description:
 *  Double ended Queue that supports adding and remove from the front and back
 *  Comes with an iterator for client to go through the queue from front to back
 **************************************************************************** */

import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
        head = null;
        tail = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> newHead = new Node<>();
        newHead.data = item;
        if (isEmpty()) {
            head = newHead;
            tail = head;
        } else {
            newHead.next = head;
            head.prev = newHead;
            head = newHead;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> newTail = new Node<>();
        newTail.data = item;
        if (isEmpty()) {
            tail = newTail;
            head = tail;
        } else {
            newTail.prev = tail;
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            size--;
            Node<Item> first = head;
            if (head.equals(tail)) {
                head = null;
                tail = null;
                return first.data;
            }
            head = head.next;
            first.next = null;
            head.prev = null;
            return first.data;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            size--;
            Node<Item> last = tail;
            if (tail.equals(head)) {
                head = null;
                tail = null;
                return last.data;
            }
            tail = tail.prev;
            tail.next = null;
            last.prev = null;
            return last.data;
        }
    }

    // return an iterator over items in order from front to back
    public java.util.Iterator<Item> iterator() {
        return new DequeIterator<Item>();
    }

    private class DequeIterator<Item> implements java.util.Iterator<Item> {
        private Node<Item> current;

        public DequeIterator() {
            current = (Node<Item>) head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item value = current.data;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node<Item> {
        Item data;
        Node<Item> next;
        Node<Item> prev;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> test = new Deque<>();
        test.addFirst(1);
        test.addFirst(2);
        test.addFirst(3);
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
    }

}
