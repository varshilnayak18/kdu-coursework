package org.example.insurancecomponent;

public interface InsuranceBrand {
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking);
}
