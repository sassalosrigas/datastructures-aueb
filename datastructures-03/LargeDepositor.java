class LargeDepositor {
    private int AFM;
    private String first_name;
    private String last_name;
    private double savings;
    private double taxedIncome;

    int key() { 
        return AFM; 
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

    LargeDepositor(int AFM, String first_name, String last_name, double savings, double taxedIncome) {
        this.AFM = AFM;
        this.first_name = first_name;
        this.last_name = last_name;
        this.savings = savings;
        this.taxedIncome = taxedIncome;
    }

    public String toString() {
        return String.format("%d %s %s %.2f %.2f", AFM, first_name, last_name, savings, taxedIncome);
    }
}
