package pl.sdacademy.java.adv.school.domain.grade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GradeUtilsTest {

    @Test
    void shouldReturnEmptyOnEmptyArray() {
        //WHEN
        final List<Grade> grades = Collections.emptyList();
        final var result = GradeUtils.gradesAverage(grades);

        //THEN
        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnEmptyOnInvalidGrades() {
        //WHEN
        final List<Grade> grades = List.of(
                grade(GradeWeight.AKT,"0.25"),
                grade(GradeWeight.EGZ,"0.0")
        );

        final var result = GradeUtils.gradesAverage(grades);

        //THEN
        assertThat(result).isEmpty();
    }

    @ParameterizedTest
    @MethodSource
    void shouldReturnExpectedAvarage(List<Grade> grades, BigDecimal expectedResult) {
        //WHEN
        final var result = GradeUtils.gradesAverage(grades);

        //THEN
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualByComparingTo(expectedResult);
    }

    static List<Arguments> shouldReturnExpectedAvarage() {
        return List.of(
            Arguments.of(
                List.of(grade(GradeWeight.EGZ, "4.75")),
                new BigDecimal("4.75")
            ),
            Arguments.of(
                List.of(grade(GradeWeight.EGZ, "5.0"), grade(GradeWeight.EGZ, "2.0")),
                new BigDecimal("3.5")
            ),
            Arguments.of(
                List.of(grade(GradeWeight.AKT, "5.0"), grade(GradeWeight.EGZ, "2.0")),
                new BigDecimal("3.0")
            ),
            Arguments.of(
                List.of(grade(GradeWeight.AKT, "5.0"), grade(GradeWeight.EGZ, "1.0")),
                new BigDecimal("2.33")
            ),
            Arguments.of(
                List.of(
                    grade(GradeWeight.AKT, "5.0"),
                    grade(GradeWeight.PYT, "3.0"),
                    grade(GradeWeight.EGZ, "1.0")
                ),
                new BigDecimal("2.56")
            )
        );
    }

    private static Grade grade(GradeWeight gradeWeight, String value) {
        final var grade = new Grade();
        grade.setGradeWeight(gradeWeight);
        grade.setValue(new BigDecimal(value));
        return grade;
    }
}
