package org.example.insurancecomponent;

public class BronzePlan extends HealthInsurancePlan {
    public BronzePlan() {
        setCoverage(0.6);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.05 * salary;
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return computeMonthlyPremium(salary) + getOfferedBy().computeMonthlyPremium(this,age,smoking);
    }
}
