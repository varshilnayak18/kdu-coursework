package InsuranceComponent;

import HMS.Patient;

public class Billing {

    /**
     * computes payment amount that patient needs to pay after calculating amount insurance company will pay
     * @param patient Patient object
     * @param amount amount to be paid to the hospital
     * @return double[] array of size 2 0th index indicates amount insurance company will pay while 1st index indicates amount patient will pay
     */
    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];

        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        double coverage = 0;
        if(patientInsurancePlan != null){
            coverage = patientInsurancePlan.getCoverage();
        }
        else{
            payments [1] = amount;
            return payments;
        }

        double discount = getDiscount(coverage);
        double patientAmount = amount - (coverage*amount) - discount;
        payments[0] = amount - patientAmount - discount;
        payments[1] = patientAmount;
        return payments;
    }

    /**
     * calculates discount hospital will give to patient based on his/her insurance plan
     * @param coverage coverage percentage of insurance plan
     * @return double discount according to insurance plan
     */
    private static double getDiscount(double coverage) {
        double discount = 0;
        if(coverage == 0){
            discount = 20;
        }
        else if (coverage == 60) {
            discount = 25;
        }
        else if (coverage == 70) {
            discount = 30;
        }
        else if (coverage == 80) {
            discount = 40;
        }
        else {
            discount = 50;
        }
        return discount;
    }
}
