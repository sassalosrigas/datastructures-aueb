/* file: Influenza_k.java
 * authors: Rigas Sassalos (3220178)
 *          Evgenia Lazana (3220104)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Influenza_k {

    public static void swap(City array[], int i, int j) { // swaps two elements of an array
        City temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static int partition(City cities[], int p, int r) { // partitions the array
        
        int i = p -1;
        int j = r; 
        City v = cities[r];
            
        while (i < j) {
            while (cities[++i].compareTo(v) < 0)
                ;
            while (v.compareTo(cities[--j]) < 0)
                if (j == p)
                    break;

            if (i >= j)
                break;

            swap(cities, i, j);
        }

        swap(cities, i, r);
        return i;

    }

    public static void quicksort(City cities[], int p, int r) { // sorts the array
        if (r > p) {
            int i = partition(cities, p, r);
            // splits the array and puts the pivot element in position a[i]
            quicksort(cities, p, i-1);
            quicksort(cities, i+1, r);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java Influenza_k <k> <file>");
            System.exit(1);
        } 

        int k = Integer.parseInt(args[0]);
        String file = args[1];

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        ArrayList<Integer> ID = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> population = new ArrayList<>();
        ArrayList<Integer> influenzaCases = new ArrayList<>(); 

        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.trim().split("\\s+");
            ID.add(Integer.parseInt(data[0]));
            name.add(String.join(" ", Arrays.copyOfRange(data, 1, data.length - 2)));
            population.add(Integer.parseInt(data[data.length-2]));
            influenzaCases.add(Integer.parseInt(data[data.length-1]));
        }
        
        bufferedReader.close();

        City[] cities = new City[ID.size()];

        for (int j = 0; j < ID.size(); j++) {
            cities[j] = new City(ID.get(j), name.get(j), population.get(j), influenzaCases.get(j)); 
        }
 

        // ensures that k is not greater than the number of cities
        if (k > cities.length) { 
            System.out.println("Error!");
            System.exit(0);
        }
        
        quicksort(cities, 0, cities.length - 1); // sorts the array

        System.out.println("The top " + k + " cities are:");
        for (int i = 0; i < k; i++) {
            System.out.println(cities[i].getName());
        }

    }

}