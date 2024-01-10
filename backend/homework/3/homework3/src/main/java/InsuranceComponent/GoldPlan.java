package InsuranceComponent;

public class GoldPlan extends HealthInsurancePlan {
    public GoldPlan() {
        setCoverage(0.8);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.07 * salary;
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return computeMonthlyPremium(salary) + getOfferedBy().computeMonthlyPremium(this,age,smoking);
    }
}

