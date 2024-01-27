class LargeDepositor {
    private int AFM;
    private String firstName;
    private String lastName;
    private double savings;
    private double taxedIncome;

    int key() { 
        return AFM; 
    }

    String getFirstName() { 
        return firstName; 
    }

    String getLastName() { 
        return lastName; 
    }

    double getSavings() { 
        return savings; 
    }

    void setSavings(double savings) { 
        this.savings = savings; 
    }

    double getTaxedIncome() { 
        return taxedIncome; 
    }

    void setTaxedIncome(double taxedIncome) { 
        this.taxedIncome = taxedIncome; 
    }

    LargeDepositor(int AFM, String firstName, String lastName, double savings, double taxedIncome) {
        this.AFM = AFM;
        this.firstName = firstName;
        this.lastName = lastName;
        this.savings = savings;
        this.taxedIncome = taxedIncome;
    }

    public String toString() {
        return String.format("%d %s %s %.2f %.2f", AFM, firstName, lastName, savings, taxedIncome);
    }
}
