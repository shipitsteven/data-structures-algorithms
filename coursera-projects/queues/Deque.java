public class Deque<Item> implements Iterable<Item> {

    private int head;
    private int tail;
    private Item[] d;

    // construct an empty deque
    public Deque() {
        d = (Item[]) new Object[1];
        head = 0;
        tail = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return d[head] == null && d[tail] == null;
    }

    // return the number of items on the deque
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        else if (head == 0 && tail == 0) {
            return 1;
        }
        else {
            return tail - head;
        }
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        else {
            if (d[head] == null) {
                d[head] = item;
            }
            else if (head - 1 >= 0) {
                d[--head] = item;
            }
            else {
                d = expandFront(d);
                d[head] = item;
            }
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        else {
            if (tail + 1 >= d.length || size() >= d.length) {
                d = extendEnd(d);
            }
            if (isEmpty()) {
                d[tail] = item;
            }
            else {
                d[++tail] = item;
            }
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        else {
            Item data = d[head];
            d[head] = null;
            if (head != tail) {
                head++;
            }
            return data;
        }

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        else {
            Item data = d[tail];
            d[tail] = null;
            if (tail != head) {
                tail--;
            }
            return data;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>();
    }

    private class Iterator<Item> implements java.util.Iterator<Item> {
        private int current = head;

        public boolean hasNext() {
            return current != tail;
        }

        public Item next() {
            if (current > tail) {
                throw new java.util.NoSuchElementException();
            }
            else {
                Item item = (Item) d[current];
                current++;
                return item;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Item[] expandFront(Item[] a) {
        Item[] copy = (Item[]) new Object[a.length * 2 + 1];
        System.arraycopy(a, 0, copy, copy.length / 2 + 1, a.length);
        int size = size();
        head = copy.length / 2;
        tail = head + size;
        return copy;
    }

    private Item[] extendEnd(Item[] a) {
        Item[] copy = (Item[]) new Object[a.length * 2 + 1];
        System.arraycopy(a, 0, copy, 0, a.length);
        return copy;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < d.length; i++) {
            s += d[i];
            s += ", ";
        }
        s += "]";
        return s;
    }

    public String getHead() {
        String h = "Head is " + String.valueOf(head) + ", value: " + d[head];
        return h;
    }

    public String getTail() {
        String t = "Tail is " + String.valueOf(tail) + ", value: " + d[tail];
        return t;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> test = new Deque<>();
        test.addLast("one");
        test.addFirst("1");
        test.addLast("2");
        test.addFirst("3");
        test.addFirst("4");
        test.addLast("5");
        test.addLast("6");
        // for (int i = 0; i <= 2; i++) {
        //     test.removeFirst();
        // }
        // for (int i = 0; i <= 2; i++) {
        //     test.removeLast();
        // }
        // System.out.println(test.getHead());
        // System.out.println(test.getTail());
        System.out.println(test.toString());
        java.util.Iterator<String> it = test.iterator();
        it.remove();
    }

}