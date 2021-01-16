/*
 * Queue implemented with resizing array
 */
public class ArrayQueue<T> {
  private int head;
  private int tail;
  private T[] q;

  public ArrayQueue() {
    q = (T[]) new Object[1];
    head = 0;
    tail = 0;
  }

  private boolean isEmpty() {
    return q[head] == null;
  }

  public void enqueue(T value) {
    if (tail >= q.length) {
      resize(q.length * 2 + 1);
    }
    if (isEmpty()) {
      q[head] = value;
      tail++;
    } else {
      q[tail++] = value;
    }
  }

  public T dequeue() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    } else {
      T data = q[head];
      q[head++] = null;
      if (head > q.length / 4) {
        compressNull(q);
      } else if (head == tail && q[head] != null) {
        resize(q.length * 2 + 1);
      }
      return data;
    }
  }

  private void resize(int capacity) {
    T[] copy = java.util.Arrays.copyOf(q, capacity);
    q = copy;
  }

  private void compressNull(T[] array) {
    int j = 0;
    for (int i = 0; i < array.length; i++) {
      if (array[i] != null) {
        T temp = array[j];
        array[j] = array[i];
        array[i] = temp;
        j++;
      }
    }
    head = 0;
    tail = j;
  }

  // ----------------- testing methods -------------------
  // public String toString() {
  // String s = "[";
  // for (int i = 0; i < q.length; i++) {
  // s += q[i];
  // s += ", ";
  // }
  // s += "]";
  // return s;
  // }

  // public String getTail() {
  // return String.valueOf(tail);
  // }

  // public static void main(String[] args) {
  // ArrayQueue<String> test = new ArrayQueue<>();
  // test.enqueue("first");
  // test.enqueue("second");
  // test.enqueue("third");
  // test.enqueue("fourth");
  // test.dequeue();
  // test.dequeue();
  // test.dequeue();
  // test.enqueue("fifth");
  // test.dequeue();
  // test.dequeue();
  // test.enqueue("sixth");
  // test.enqueue("seven");
  // test.enqueue("eight");
  // test.dequeue();
  // test.dequeue();
  // // System.out.println(test.getTail());
  // System.out.println(test.toString());
  // }
}