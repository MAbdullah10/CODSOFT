import java.util.Scanner;
public class StudentGradeCalculator {
    int total_subjects;
    int averagePercentage;
    String grade;
    String[] subjectNames;
    int[] subjectMarks;
    int[] subjectCredits;
    double gpa;
    int highestMarks;
    int lowestMarks;
    int passedSubjects = 0;
    int failedSubjects = 0;
    String status;

    public void inputSubjectNamesAndMarks(Scanner s) {
        System.out.println("How many subjects do you want to enter: ");
        total_subjects = s.nextInt();
        s.nextLine();
        subjectNames = new String[total_subjects];
        subjectMarks = new int[total_subjects];
        subjectCredits = new int[total_subjects];

        System.out.println("Enter Subject Names, Marks and Credits: ");
        for (int i = 0; i < total_subjects; i++) {
            System.out.println(i + 1 + "th Subject Name: ");
            subjectNames[i] = s.nextLine();

            System.out.println(i + 1 + "th Subject Marks: ");
            subjectMarks[i] = s.nextInt();
            while (subjectMarks[i] < 0 || subjectMarks[i] > 100) {
                System.out.println("Invalid Input! Marks should be between 0 and 100");
                System.out.println(i + 1 + "th Subject Marks: ");
                subjectMarks[i] = s.nextInt();
            }

            System.out.println(i + 1 + "th Subject Credits: ");
            subjectCredits[i] = s.nextInt();
            while (subjectCredits[i] <= 0) {
                System.out.println("Invalid Input! Credits must be more than zero");
                System.out.println(i + 1 + "th Subject Credits: ");
                subjectCredits[i] = s.nextInt();
            }
            s.nextLine();
        }
    }

    public void WeightedAverage() {
        int weightedMarks = 0;
        int totalCredits = 0;
        for (int i = 0; i < total_subjects; i++) {
            weightedMarks = weightedMarks + (subjectMarks[i] * subjectCredits[i]);
            totalCredits = totalCredits + subjectCredits[i];
        }
        averagePercentage = weightedMarks / totalCredits;
    }

    public void calculateGPA() {
        double gradePoints = 0.0;
        int totalCredits = 0;
        for (int i = 0; i < total_subjects; i++) {
            gradePoints = gradePoints + (calculateGradePoints(subjectMarks[i]) * subjectCredits[i]);
            totalCredits = totalCredits + subjectCredits[i];
        }
        gpa = gradePoints / totalCredits;
    }

    public double calculateGradePoints(int marks) {
        if (marks >= 85)
            return 4.0;
        else if (marks >= 80)
            return 3.66;
        else if (marks >= 75)
            return 3.33;
        else if (marks >= 72)
            return 3.0;
        else if (marks >= 68)
            return 2.66;
        else if (marks >= 65)
            return 2.33;
        else if (marks >= 61)
            return 2.0;
        else if (marks >= 57)
            return 1.66;
        else if (marks >= 53)
            return 1.33;
        else if (marks >= 50)
            return 1.0;
        else
            return 0.0;
    }

    public void grade_calculation() {
        if (gpa >= 4.0)
            grade = "A";
        else if (gpa >= 3.66)
            grade = "A-";
        else if (gpa >= 3.33)
            grade = "B+";
        else if (gpa >= 3.0)
            grade = "B";
        else if (gpa >= 2.66)
            grade = "B-";
        else if (gpa >= 2.33)
            grade = "C+";
        else if (gpa >= 2.0)
            grade = "C";
        else if (gpa >= 1.66)
            grade = "C-";
        else if (gpa >= 1.33)
            grade = "D+";
        else if (gpa >= 1.0)
            grade = "D";
        else
            grade = "F";
    }

    public void subjectsDisplay() {
        System.out.println("SubjectNames\tMarks\tCredits");
        System.out.println("----------------------------------");
        for (int i = 0; i < total_subjects; i++) {
            //System.out.println(subjectNames[i] + "\t\t" + subjectMarks[i] + "\t\t" + subjectCredits[i]);
            System.out.printf("%-15s%-7d%-8d%n", subjectNames[i], subjectMarks[i], subjectCredits[i]);
        }
        System.out.println("----------------------------------");
    }

    public void display(int total_marks) {
        System.out.println("\tTOTAL MARKS        :\t " + total_marks);
        System.out.println("\tPERCENTAGE         :\t " + averagePercentage + "%");
        System.out.println("\tGRADE              :\t " + grade);
        System.out.println("\tGPA                :\t" + gpa);
        System.out.println("-----------------------------------");
    }
    public void HighestAndLowestMarks() {
        highestMarks = subjectMarks[0];
        lowestMarks = subjectMarks[0];
        for (int i = 0; i < total_subjects; i++) {
            if (subjectMarks[i] > highestMarks) {
                highestMarks = subjectMarks[i];
            }
            if (subjectMarks[i] < lowestMarks) {
                lowestMarks = subjectMarks[i];
            }
        }
    }
    public void PassedAndFailedSubjects() {
        for (int i = 0; i < total_subjects; i++) {
            if (subjectMarks[i] >= 50) {
                passedSubjects++;
            } else
                failedSubjects++;
        }
    }
    public void PassOrFail() {
        int passingPercentage = 50;
        if (averagePercentage >= passingPercentage) {
            status = "PASS";
        } else
            status = "FAIL";
    }
    public void AdditionalDisplay() {
        System.out.println();
        System.out.println("HIGHEST MARKS     :   " + highestMarks);
        System.out.println("LOWEST MARKS      :   " + lowestMarks);
        System.out.println("PASSED SUBJECTS   :   " + passedSubjects);
        System.out.println("FAILED SUBJECTS   :   " + failedSubjects);
        System.out.println("GAS               :   " + status);
    }
}