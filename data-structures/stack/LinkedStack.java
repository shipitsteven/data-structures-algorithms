/**
 * Stack implemented with LinkedList and Generics
 */
public class LinkedStack<T>
{
    private Node first;
    
    public void push(T value){
        Node oldFirst = first;
        first = new Node(value);
        first.next = oldFirst;
    }
    
    public T pop(){
        if(first != null){
            T value = first.data;
            first = first.next;
            return value;
        } else {
            throw new java.util.EmptyStackException();
        }
    }
    
    private class Node {        
        T data;
        Node next;
        
        public Node(T value){
            data = value;
        }
    }
}
