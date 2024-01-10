package org.example.insurancecomponent;

/**
 * use of inheritance and abstraction
 * abstract method of abstract class HealthInsurancePlan overridden here
 */
public class PlatinumPlan extends HealthInsurancePlan {
    public PlatinumPlan() {
        setCoverage(0.9);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.08 * salary;
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return computeMonthlyPremium(salary) + getOfferedBy().computeMonthlyPremium(this,age,smoking);
    }
}

