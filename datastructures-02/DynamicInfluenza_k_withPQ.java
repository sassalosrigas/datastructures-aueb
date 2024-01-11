/* file: DynamicInfluenza_k_withPQ.java
 * authors: Rigas Sassalos (3220178)
 *          Evgenia Lazana (3220104)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DynamicInfluenza_k_withPQ {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java DynamicInfluenza_k_withPQ <k> <file>");
            System.exit(1);
        } 

        int k = Integer.parseInt(args[0]);
        String file = args[1];

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // creates a priority queue and insert the first k cities
        PQ pq = new PQ(k);
        for (int i = 0; i < k; i++) {
            String line = br.readLine();

            String[] data = line.trim().split("\\s+");
            int id = Integer.parseInt(data[0]);
            String name = String.join(" ", Arrays.copyOfRange(data, 1, data.length - 2));
            int population = Integer.parseInt(data[data.length-2]);
            int influenzaCases = Integer.parseInt(data[data.length-1]);

            City city = new City(id, name, population, influenzaCases);
            pq.insert(city);
        }

        String line;
        while ((line = br.readLine()) != null) {

            String[] data = line.trim().split("\\s+");
            int id = Integer.parseInt(data[0]);
            String name = String.join(" ", Arrays.copyOfRange(data, 1, data.length - 2));
            int population = Integer.parseInt(data[data.length-2]);
            int influenzaCases = Integer.parseInt(data[data.length-1]);

            City city = new City(id, name, population, influenzaCases);
            City maxCity = pq.findMaxCity(pq);

            if (maxCity.calculateDensity() > city.calculateDensity()) {
                pq.remove(maxCity.getID());
                pq.insert(city);
            }

        }

        br.close();
        
        // Print the top k cities
        System.out.println("The top " + k + " cities are:");
        for (int i = 0; i < k; i++) {
            City c = pq.getmin();
            System.out.println(c.getName());
        }

    }
}
