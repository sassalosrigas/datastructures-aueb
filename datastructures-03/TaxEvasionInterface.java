// Purpose: interface for TaxEvasion class
public interface TaxEvasionInterface {
    void insert(LargeDepositor item);
    void load(String filename);
    void updateSavings(int AFM, double savings); 
    LargeDepositor searchByAFM(int AFM);
    List searchByLastName(String lastName); 
    void remove(int AFM);
    double getMeanSavings();
    void printΤopLargeDepositors(int k); 
    void printByAFM();
}