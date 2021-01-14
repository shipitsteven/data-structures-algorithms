public class LinkedQueue<T> {
    ListNode front;
    ListNode back;

    public void enqueue(T value){
        ListNode current = front;
        while(current != null){
            current = current.next;
        }
        // is anything pointing at current?
        current = new ListNode(value);
    }

    private class ListNode {
        T data;
        ListNode next;

        public ListNode(T value){
            data = value;
        }
    }

}
