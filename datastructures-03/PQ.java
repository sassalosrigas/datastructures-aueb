/* file: PQ.java
 * authors: Rigas Sassalos (3220178)
 *          Evgenia Lazana (3220104)
 */

 import java.util.Arrays;

 public class PQ {
     private LargeDepositor[] array;
     private int size;
     private int[] position;
 
     public PQ(int capaLargeDepositor) {
         array = new LargeDepositor[capaLargeDepositor];
         size = 0;
         position = new int[1000000];
         Arrays.fill(position, -1);
     }
 
     boolean isEmpty() { // checks if the priority queue is empty
         if(size() == 0) {
             return true;
         }
         else {
             return false;
         }
     }
 
     int size() { // returns the size of the priority queue
         return size;
     }
 
     void insert(LargeDepositor x) { // inserts a LargeDepositor in the priority queue
         if((double)size() >= array.length * 0.75) {
             resize();
         }
         array[size] = x;
         position[x.key()] = size;
         swim(size);
         size++;
     }
 
     void resize() { // resizes the array
         size = size();
         LargeDepositor[] newArray = new LargeDepositor[2*array.length];
         for(int i = 0; i < size; i++) {
             newArray[i] = array[i];
             position[newArray[i].key()] = i;
         }
         array = newArray;
     }
 
     LargeDepositor min() { // returns the LargeDepositor with the minimum density
         if(isEmpty()) {
             throw new IllegalStateException("Priority queue is empty");
         } else {
             return array[0];
         }
 
     }
 
     LargeDepositor getmin() { // returns the LargeDepositor with the minimum density and removes it from the priority queue
         if(isEmpty()) {
             throw new IllegalStateException("Priority queue is empty"); 
         } else {
             LargeDepositor min = array[0];
             swap(0, size-1);
             position[min.key()] = -1;
             array[size-1] = null;
             size--;
             if(!isEmpty()) {
                 sink(0);
             }
             return min;
         }
     }   
 
     void remove(int ID) { // removes a LargeDepositor from the priority queue
         if(isEmpty()) {
             throw new IllegalStateException("Priority queue is empty");
         } else {
             int index = position[ID];
             if(index == -1) {
                 throw new IllegalStateException("LargeDepositor not found");
             } else {
                 swap(index, size()-1);
                 array[size()-1] = null;
                 position[ID] = -1;
                 size--;
                 sink(index);
             }
         }
     }
     
     private void swim(int index) { // moves the LargeDepositor up in the priority queue
         while (index > 0) {
             int parentIndex = (index - 1) / 2;
             if (array[index].compareTo(array[parentIndex]) < 0) {
                 swap(index, parentIndex);
                 index = parentIndex;
             } else {
                 break;
             }
         }
     }
 
     private void sink(int index) { // moves the LargeDepositor down in the priority queue
         while (true) {
             int leftChildIndex = 2 * index + 1;
             int rightChildIndex = 2 * index + 2;
             int smallestIndex = index;
 
             if (leftChildIndex < size && array[leftChildIndex].compareTo(array[smallestIndex]) < 0) {
                 smallestIndex = leftChildIndex;
             }
 
             if (rightChildIndex < size && array[rightChildIndex].compareTo(array[smallestIndex]) < 0) {
                 smallestIndex = rightChildIndex;
             }
 
             if (smallestIndex != index) {
                 swap(index, smallestIndex);
                 index = smallestIndex;
             } else {
                 break;
             }
         }
     }
 
     private void swap(int i, int j) { // swaps two cities
         LargeDepositor temp = array[i];
         array[i] = array[j];
         array[j] = temp;
     }
     
     boolean offer(LargeDepositor x) {
        if ((double) size() >= array.length * 0.75) {
            resize();
        }
        array[size] = x;
        position[x.key()] = size;
        swim(size);
        size++;
        return true;
     }

        LargeDepositor poll() {
            if (isEmpty()) {
                return null;
            } else {
                LargeDepositor min = array[0];
                swap(0, size - 1);
                position[min.key()] = -1;
                array[size - 1] = null;
                size--;
                if (!isEmpty()) {
                    sink(0);
                }
                return min;
            }
        }

        LargeDepositor peek() {
            if (isEmpty()) {
                return null;
            } else {
                return array[0];
            }
        }
 }
