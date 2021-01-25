/* *****************************************************************************
 *  Name: Steven Wang
 *  Date: 1/17/2021
 *  Description:
 *  Randomized Queue that dequeue elements at random. Comes with an iterator
 *  that returns random elements inside the queue. Each iterator's output is
 *  mutually exclusive to each other.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] r;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        r = (Item[]) new Object[2];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == r.length) resize(r.length * 2 + 1);
        r[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            int randomNumber = StdRandom.uniform(size);
            Item chosen = r[randomNumber];
            r[randomNumber] = r[size - 1];
            r[--size] = null;
            if (!isEmpty() && size < r.length / 4) {
                resize(r.length / 2);
            }
            return chosen;
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomNumber = StdRandom.uniform(size);
        return r[randomNumber];
    }

    // return an independent iterator over items in random order
    public java.util.Iterator<Item> iterator() {
        return new RandomIterator<Item>();
    }

    private class RandomIterator<Item> implements java.util.Iterator<Item> {

        private Item[] itArray;
        private int current;

        public RandomIterator() {
            current = 0;
            itArray = (Item[]) new Object[size];
            System.arraycopy(r, 0, itArray, 0, size);
            StdRandom.shuffle(itArray);
        }

        public boolean hasNext() {
            return current != itArray.length;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return itArray[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        if (capacity <= r.length / 2) {
            System.arraycopy(r, 0, copy, 0, size);
        }
        else {
            System.arraycopy(r, 0, copy, 0, r.length);
        }
        r = copy;
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }
}