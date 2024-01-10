package org.example.insurancecomponent;

public class SilverPlan extends HealthInsurancePlan {
    public SilverPlan() {
        setCoverage(0.7);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.06 * salary;
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return computeMonthlyPremium(salary) + getOfferedBy().computeMonthlyPremium(this,age,smoking);
    }
}
