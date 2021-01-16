/**
 * Stack implemented with an array
 */
public class ArrayStack<T>
{
    private T[] array;
    private int N;
    
    public ArrayStack(){
        array = (T[]) new Object[1];
    }
    
    public void push(T value){
      if(N == array.length){
         resize(2 * array.length + 1);
      }
      array[N++] = value;
    }
    
    public T pop(){
      if(N > 0){
         T data = array[--N];
         array[N] = null;
         if(N > 0 && N == array.length / 4){
            resize(array.length /2);
         }
         return data;
      } else {
         throw new java.util.EmptyStackException();
      }
    }
    
    private void resize(int capacity){
      T[] copy = java.util.Arrays.copyOf(array, capacity);
      array = copy;
    }
    
//     public static void main(String[] args){
//       ArrayStack<String> test = new ArrayStack<>();
//       test.push("first");
//       test.push("second");
//       System.out.println(test.pop()); // second
//       System.out.println(test.pop()); // first
//       System.out.println(test.pop()); // EmptyStackException     
//     }
}
