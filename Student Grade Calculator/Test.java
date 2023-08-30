import java.util.Scanner;
public class Test
{
    public static void main(String[] args) {
        StudentGradeCalculator sgc = new StudentGradeCalculator();
        Scanner s = new Scanner(System.in);

        sgc.inputSubjectNamesAndMarks(s);

        int total_marks = 0;
        int marks;
        for (int j = 0; j < sgc.total_subjects; j++) {
            total_marks = total_marks + sgc.subjectMarks[j];
        }
        {
            for (int i = 0; i < sgc.total_subjects; i++) {
                marks = sgc.subjectMarks[i];
                sgc.calculateGradePoints(marks);
            }
        }
        sgc.WeightedAverage();
        sgc.calculateGPA();
        sgc.grade_calculation();
        sgc.subjectsDisplay();
        sgc.display(total_marks);

        sgc.HighestAndLowestMarks();
        sgc.PassedAndFailedSubjects();
        sgc.PassOrFail();
        sgc.AdditionalDisplay();
    }
}