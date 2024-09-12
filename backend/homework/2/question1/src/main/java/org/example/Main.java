package org.example;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Logging logging = new Logging();
        int[] studentIdList = {1001, 1002};
        char[][] studentsGrades = { { 'A', 'A', 'A', 'B' }, { 'A', 'B', 'B' } };
        double[] studentGPA = StudentUtil.calculateGPA(studentIdList, studentsGrades);
        logging.logInfo("Printing student GPA");
        for(double gpa: studentGPA){
            logging.logInfo("" + gpa);
        }
        int[] studentWithinRangeOfGPA = StudentUtil.getStudentsByGPA(3.2,3.5,studentIdList,studentsGrades);
        if(studentWithinRangeOfGPA == null){
            logging.logWarn("No student's GPA exist between given range");
        }
        else {
            logging.logInfo("Printing student's id whose CPA is in given range");
            for (int id : studentWithinRangeOfGPA) {
                logging.logInfo("" + id);
            }
        }
    }
}