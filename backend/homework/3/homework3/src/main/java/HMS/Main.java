package HMS;

import InsuranceComponent.*;
import org.example.Logging;

public class Main {
    public static void main(String[] args) {
        Logging logger = new Logging();

        //question 2 calculate payment for patient
        Patient patient = getPatient();
        double[] payments = Billing.computePaymentAmount(patient, 1000.0);
        logger.logInfo("Patient billing details:- ");
        logger.logInfo("Patient id: " + patient.getPatientId());
        logger.logInfo("Name: " + patient.getFirstName() + " " + patient.getLastName());
        logger.logInfo("Age: " + patient.getAge());
        logger.logInfo("Gender: " + patient.getGender());
        logger.logInfo("Email: " + patient.getEmail());
        logger.logInfo("Has Insurance: " + patient.isInsured());
        logger.logInfo("Total bill for customer: " + payments[1]);
        logger.logInfo("Total bill paid by company: " + payments[0]);

        //question 3 calculate premium from salary
        Doctor doctor = new Doctor();
        HealthInsurancePlan insurancePlan2 = new GoldPlan();
        doctor.setInsurancePlan(insurancePlan2);
        doctor.setId(2);
        doctor.setSalary(93829);
        doctor.setDoctorId(201);
        doctor.setSpecialization("MBBS");
        logger.logInfo("Doctor premium details:- ");
        logger.logInfo("Doctor id: " + doctor.getDoctorId());
        logger.logInfo("Specialization: " + doctor.getSpecialization());
        logger.logInfo("Total premium to be paid: " + insurancePlan2.computeMonthlyPremium(doctor.getSalary()));

        //question 4 calculate premium based on salary age and smoking habits
        User staff = new User();
        staff.setId(3);
        staff.setSmoking(true);
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan3 = new PlatinumPlan();
        insurancePlan3.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan3);
        logger.logInfo("User premium details:- ");
        logger.logInfo("User id: " + staff.getId());
        logger.logInfo("Total premium to be paid: " + insurancePlan3.computeMonthlyPremium(5000, 56, staff.isSmoking()));
    }

    private static Patient getPatient() {
        Patient patient = new Patient();
        patient.setId(1);
        patient.setPatientId(101);
        patient.setFirstName("rahul");
        patient.setLastName("sharma");
        patient.setGender("male");
        patient.setEmail("rahul@gmail.com");
        patient.setInsured(true);
        HealthInsurancePlan insurancePlan = new PlatinumPlan();
        patient.setInsurancePlan(insurancePlan);
        patient.setAge(22);
        patient.setSmoking(false);
        return patient;
    }
}