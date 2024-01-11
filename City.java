/* file: City.java
 * authors: Rigas Sassalos (3220178)
 *          Evgenia Lazana (3220104)
 */

public class City implements CityInterface, Comparable<City> {

    private int ID;
    private String name;
    private int population;
    private int InfluenzaCases;

    public City(int ID, String name, int population, int InfluenzaCases) { // Constructor
        this.ID = ID;
        this.name = name;
        this.population = population;
        this.InfluenzaCases = InfluenzaCases;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getInfluenzaCases() {
        return InfluenzaCases;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setInfluenzaCases(int InfluenzaCases) {
        this.InfluenzaCases = InfluenzaCases;
    }

    public float calculateDensity() { // Calculates the density of the city
        float cases = (float) InfluenzaCases / population;
        float density = cases * 50000;

        String formattedDensity = String.format("%.2f", density).replace(",", ".");
        density = Float.parseFloat(formattedDensity);

        return density;
    }

    public int compareTo(City city) { // Compares the density of two cities
        int densityCompare = Float.compare(this.calculateDensity(), city.calculateDensity());

        if (densityCompare != 0) {
            return densityCompare;
        } else {
            int nameCompare = this.name.compareTo(city.name);
            if (nameCompare != 0) {
                return nameCompare;
            } else {
                return Integer.compare(this.ID, city.ID);
            }
        }
    }
}