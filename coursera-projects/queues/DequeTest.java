import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
class DequeTest {

    @Test
    void isEmpty() {
        Deque q = new Deque();
        assertEquals(true, q.isEmpty());

        Deque<Integer> q2 = new Deque<>();
        q2.addFirst(1);
        assertEquals(false, q2.isEmpty());

        Deque<Integer> q3 = new Deque<>();
        q3.addFirst(1);
        q3.addFirst(2);
        assertEquals(false, q3.isEmpty());
    }

    @Test
    void size() {
        Deque q = new Deque();
        assertEquals(0, q.size());

        Deque<Integer> q2 = new Deque<>();
        q2.addFirst(1);
        assertEquals(1, q2.size());

        Deque<Integer> q3 = new Deque<>();
        q3.addFirst(1);
        q3.addFirst(2);
        assertEquals(2, q3.size());
    }

    @Test
    void addFirst() {
        Deque<Integer> q = new Deque<>();
        Integer[] a = { 1, 3, 8, 9, 12, 2 };
        ArrayList<Integer> l = new ArrayList<Integer>(Arrays.asList(a));

        int n = l.size();
        for (int i = n - 1; i >= 0; i--) {
            q.addFirst(l.get(i));
        }

        for (int i = 0; i < n; i++) {
            assertEquals(q.removeFirst(), l.get(i));
        }

    }

    @Test
    void addLast() {
        Deque<Integer> q = new Deque<>();
        Integer[] a = { 1, 3, 8, 9, 12, 2 };
        ArrayList<Integer> l = new ArrayList<Integer>(Arrays.asList(a));

        int n = l.size();
        for (int i = 0; i < n; i++) {
            q.addLast(l.get(i));
        }

        for (int i = 0; i < n; i++) {
            assertEquals(q.removeFirst(), l.get(i));
        }
    }

    @Test
    void removeFirst() {
        Deque<Integer> q = new Deque<>();
        assertThrows(NoSuchElementException.class, () -> {
            q.removeFirst();
        });

        ArrayList<Integer> a = new ArrayList<>();
        int n = 100;
        int value;
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            value = r.nextInt(100);
            a.add(value);
            q.addLast(value);
        }

        for (int i = 0; i < n; i++) {
            assertEquals(a.get(i), q.removeFirst());
        }

    }

    @Test
    void removeLast() {
        Deque<Integer> q2 = new Deque<>();
        assertThrows(NoSuchElementException.class, () -> {
            q2.removeLast();
        });

        ArrayList<Integer> a2 = new ArrayList<>();
        int n = 10;
        int value;
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            value = r.nextInt(100);
            a2.add(value);
            q2.addLast(value);
        }

        for (int i = n - 1; i >= 0; i--) {
            assertEquals(a2.get(i), q2.removeLast());
        }
    }

    @Test
    void iterator() {
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();
        Deque<Integer> q = new Deque<>();
        int n = 50;
        int value;
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            value = r.nextInt(100);
            q.addLast(value);
            ans.add(value);
        }

        for (Object num : q) {
            values.add((Integer) num);
        }

        for (int i = 0; i < n; i++) {
            assertEquals(ans.get(i), values.get(i));
        }


    }
}