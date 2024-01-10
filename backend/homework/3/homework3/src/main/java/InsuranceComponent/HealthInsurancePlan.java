package InsuranceComponent;

public abstract class HealthInsurancePlan {
    private double coverage;
    private InsuranceBrand offeredBy;

    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    /**
     * computes monthly premium amount based on user's salary and insurance plan (overridden in child class)
     * @param salary salary of the user
     * @return double premium amount user needs to pay
     */
    public abstract double computeMonthlyPremium(double salary);

    /**
     * overloaded function which computes monthly premium amount based on user's salary, age and smoking habits
     * @param salary salary of the user
     * @param age age of the user
     * @param smoking user smoke or not
     * @return double premium amount user needs to pay
     */
    public abstract double computeMonthlyPremium(double salary,int age, boolean smoking);

}
