/* file: PQ.java
 * authors: Rigas Sassalos (3220178)
 *          Evgenia Lazana (3220104)
 */

import java.util.Arrays;

public class PQ {
    private City[] array;
    private int size;
    private int[] position;

    public PQ(int capacity) {
        array = new City[capacity];
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

    void insert(City x) { // inserts a city in the priority queue
        if((double)size() >= array.length * 0.75) {
            resize();
        }
        array[size] = x;
        position[x.getID()] = size;
        swim(size);
        size++;
    }

    void resize() { // resizes the array
        size = size();
        City[] newArray = new City[2*array.length];
        for(int i = 0; i < size; i++) {
            newArray[i] = array[i];
            position[newArray[i].getID()] = i;
        }
        array = newArray;
    }

    City min() { // returns the city with the minimum density
        if(isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        } else {
            return array[0];
        }

    }

    City getmin() { // returns the city with the minimum density and removes it from the priority queue
        if(isEmpty()) {
            throw new IllegalStateException("Priority queue is empty"); 
        } else {
            City min = array[0];
            swap(0, size-1);
            position[min.getID()] = -1;
            array[size-1] = null;
            size--;
            if(!isEmpty()) {
                sink(0);
            }
            return min;
        }
    }   
    
    City findMaxCity(PQ pq) { // returns the city with the maximum density
        City maxCity = array[0];
        for(int i = 1; i < size; i++) {
            if(array[i].calculateDensity() > maxCity.calculateDensity()) {
                maxCity = array[i];
            }
        }
        return maxCity;
    }

    void remove(int ID) { // removes a city from the priority queue
        if(isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        } else {
            int index = position[ID];
            if(index == -1) {
                throw new IllegalStateException("City not found");
            } else {
                swap(index, size()-1);
                array[size()-1] = null;
                position[ID] = -1;
                size--;
                sink(index);
            }
        }
    }
    
    private void swim(int index) { // moves the city up in the priority queue
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

    private void sink(int index) { // moves the city down in the priority queue
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
        City temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}