/* *****************************************************************************
 *  Name: Steven Wang
 *  Date: 1/17/2021
 *  Description:
 *  Client class that reads args as k, where k is an integer
 *  reads a sequence of strings from standard input using StdIn.readString();
 *  and prints exactly k of them, uniformly at random.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomQ = new RandomizedQueue<>();
        int num = Integer.parseInt(args[0]);
        String str;
        while (!StdIn.isEmpty()) {
            str = StdIn.readString();
            randomQ.enqueue(str);
        }
        Iterator<String> iterator = randomQ.iterator();
        for (int i = 0; i < num; i++)
            System.out.println(iterator.next());
    }
}