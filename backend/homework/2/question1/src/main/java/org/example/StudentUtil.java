package org.example;

public class StudentUtil {

    /**
     * converts grade into its corresponding numeric value
     * @param grade to be converted to numeric value
     * @return numeric value according to the grade
     */
    public static double convertGrade(char grade){
        if(grade == 'A'){
            return 4;
        }
        else if(grade == 'B'){
            return 3;
        }
        else{
            return 2;
        }
    }

    /**
     * calculates GPA of each student from the list of students
     * @param studentIdList list containing id of students
     * @param studentsGrades list of grades of each student from the list of students
     * @return double[] list containing grades of each student
     */
    public static double[] calculateGPA(int[] studentIdList, char[][]
            studentsGrades) {
        double[] studentGPA = new double[studentIdList.length];
        double sum = 0;
        int subjects = 0;
        for(int i = 0; i < studentIdList.length; i++){
            sum = 0;
            subjects = studentsGrades[i].length;
            for(int j = 0; j < subjects; j++){
                sum += convertGrade(studentsGrades[i][j]);
            }
            studentGPA[i] = sum/subjects;
        }
        return studentGPA;
    }

    /**
     * finds id of students whose GPA falls between lower and higher by taking help of calculateGPA method
     * @param lower lower limit of GPA
     * @param higher upper limit of GPA
     * @param studentIdList list containing id of students
     * @param studentsGrades list of grades of each student from the list of students
     * @return int[] list containing ids of students having GPA in range
     */
    public static int[] getStudentsByGPA(double lower, double higher, int[]
            studentIdList, char[][] studentsGrades) {
        if(lower>higher || lower<0 || higher<0){
            return null;
        }
        double[] studentGPA = calculateGPA(studentIdList, studentsGrades);
        int count = 0;
        for (int i = 0; i < studentIdList.length; i++) {
            double gpa = studentGPA[i];
            if (gpa <= higher && gpa >= lower) {
                count++;
            }
        }
        int[] studentWithinRangeOfGPA = new int[count];
        int j = 0;
        for (int i = 0; i < studentIdList.length; i++) {
            double gpa = studentGPA[i];
            if (gpa <= higher && gpa >= lower) {
                studentWithinRangeOfGPA[j++] = studentIdList[i];
            }
        }
        return studentWithinRangeOfGPA;
    }
}