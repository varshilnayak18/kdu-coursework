package InsuranceComponent;

/**
 * use of interface (method implements in this class)
 */
public class BlueCrossBlueShield implements InsuranceBrand {

    /** computes monthly premium amount (different for different plans) for the user based on his age and does he smoke or not
     * @param insurancePlan insurance plan of the user
     * @param age age of the user
     * @param smoking user smoke or not
     * @return double premium amount user needs to pay
     */
    @Override
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {
        double premium = 0;
        boolean checkAge = age > 55;

        if(insurancePlan instanceof PlatinumPlan){
            if(checkAge){
                premium += 200;
            }
            if(smoking){
                premium += 100;
            }
        }
        else if (insurancePlan instanceof GoldPlan) {
            if(checkAge){
                premium += 150;
            }
            if(smoking){
                premium += 90;
            }
        }
        else if (insurancePlan instanceof SilverPlan) {
            if(checkAge){
                premium += 100;
            }
            if(smoking){
                premium += 80;
            }
        }
        else if (insurancePlan instanceof BronzePlan) {
            if(checkAge){
                premium += 50;
            }
            if(smoking){
                premium += 70;
            }
        }
        return premium;
    }
}
