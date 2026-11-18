package mn.edu.must.sqat;

/** Calculates a student's letter grade and weighted course score. */
public class GradeCalculator {

    public String letterGrade(double score) {
        validateRange(score, 0, 100, "score");
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    public double totalScore(double att, double lab, double quiz1, double quiz2, double exam) {
        validateRange(att, 0, 10, "attendance");
        validateRange(lab, 0, 40, "laboratory");
        validateRange(quiz1, 0, 10, "quiz1");
        validateRange(quiz2, 0, 10, "quiz2");
        validateRange(exam, 0, 30, "exam");
        return att + lab + quiz1 + quiz2 + exam;
    }

    private void validateRange(double value, double minimum, double maximum, String name) {
        if (!Double.isFinite(value) || value < minimum || value > maximum) {
            throw new IllegalArgumentException(name + " must be between " + minimum + " and " + maximum);
        }
    }
}
