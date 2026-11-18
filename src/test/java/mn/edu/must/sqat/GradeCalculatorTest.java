package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GradeCalculatorTest {

    private final GradeCalculator calculator = new GradeCalculator();

    @ParameterizedTest(name = "score {0} -> {1}")
    @CsvSource({"95,A", "90,A", "89.99,B", "85,B", "75,C", "70,C", "65,D", "60,D", "59.99,F", "30,F", "0,F", "100,A"})
    @DisplayName("Ердийн болон заагийн оноо зөв үсгэн дүн гаргана")
    void letterGradeBoundaries(double score, String expected) {
        assertEquals(expected, calculator.letterGrade(score));
    }

    @Test
    @DisplayName("90 оноо яг A байх ёстой")
    void ninetyIsExactlyA() {
        assertEquals("A", calculator.letterGrade(90.0));
    }

    @Test
    @DisplayName("0 оноо F байх ёстой")
    void zeroIsF() {
        assertEquals("F", calculator.letterGrade(0));
    }

    @Test
    @DisplayName("100 оноо A байх ёстой")
    void oneHundredIsA() {
        assertEquals("A", calculator.letterGrade(100));
    }

    @Test
    @DisplayName("Сөрөг үсгэн дүнгийн оноо exception шиднэ")
    void negativeLetterGradeScoreIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(-1));
    }

    @Test
    @DisplayName("101 оноо exception шиднэ")
    void scoreAboveOneHundredIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(101));
    }

    @ParameterizedTest(name = "оноонуудын нийлбэр {5}")
    @CsvSource({"10,40,10,10,30,100", "0,0,0,0,0,0", "5,20,8,7,25,65"})
    @DisplayName("Нийт оноог зөв нийлбэрлэнэ")
    void totalScoreIsCalculated(double att, double lab, double quiz1, double quiz2, double exam, double expected) {
        assertEquals(expected, calculator.totalScore(att, lab, quiz1, quiz2, exam), 0.0001);
    }

    @Test
    @DisplayName("Ирцийн сөрөг утга exception шиднэ")
    void negativeAttendanceIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> calculator.totalScore(-5, 40, 10, 10, 30));
    }

    @Test
    @DisplayName("Лабораторийн дээд хязгаараас давсан утга exception шиднэ")
    void laboratoryAboveMaximumIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> calculator.totalScore(10, 41, 10, 10, 30));
    }

    @Test
    @DisplayName("Шалгалтын дээд хязгаараас давсан утга exception шиднэ")
    void examAboveMaximumIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> calculator.totalScore(10, 40, 10, 10, 31));
    }
}
