package pl.sdacademy.java.adv.school.domain.grade;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.adv.school.Main;
import pl.sdacademy.java.adv.school.domain.grade.parsers.csv.OpenCsvGradeParser;
import pl.sdacademy.java.adv.school.parsers.RecordsParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GradeServiceTest {

    private static List<Grade> grades;

    private GradeService gradeService;

    @BeforeAll
    static void beforeAll() throws IOException {
        final RecordsParser<Grade> gradesParser = new OpenCsvGradeParser();
        try (InputStream gradesDataStream = Main.class.getResourceAsStream("/grades.csv")) {
            grades = gradesParser.parseData(gradesDataStream);
        }
    }

    @BeforeEach
    void setUp() {
        gradeService = new GradeService(new GradeListRepository(grades));
    }

    @Test
    void countMathGrades() {
        //WHEN
        long result = gradeService.countMathGrades();

        //THEN
        assertThat(result).isEqualTo(48);
    }
}
