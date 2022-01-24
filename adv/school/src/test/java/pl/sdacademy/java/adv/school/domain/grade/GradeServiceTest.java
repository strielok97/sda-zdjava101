package pl.sdacademy.java.adv.school.domain.grade;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.adv.school.Main;
import pl.sdacademy.java.adv.school.domain.grade.parsers.GradeService;
import pl.sdacademy.java.adv.school.domain.grade.parsers.OpenCsvGradeParser;
import pl.sdacademy.java.adv.school.domain.student.StudentListRepository;
import pl.sdacademy.java.adv.school.domain.student.StudentService;
import pl.sdacademy.java.adv.school.domain.student.model.Student;
import pl.sdacademy.java.adv.school.domain.student.parsers.csv.OpenCsvStudentParser;
import pl.sdacademy.java.adv.school.parsers.RecordsParser;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GradeServiceTest {  //TODO ogarnąć to z mastera po dokończeniu PD!
    private static List<Grade> grades;
    private static List<Student> students;
    private GradeService gradeService;

    @BeforeAll
    static void beforeAll() throws IOException {
        final RecordsParser<Grade> gradeParser = new OpenCsvGradeParser();
        try(InputStream studentsDataStream = Main.class.getResourceAsStream("/grades.csv")) {
            grades = gradeParser.parseData(studentsDataStream);
        }

        final RecordsParser<Student> studentsParser = new OpenCsvStudentParser();
        try(InputStream studentsDataStream = Main.class.getResourceAsStream("/students.csv")) {
            students = studentsParser.parseData(studentsDataStream);
        }
    }

    @BeforeEach
    void setUp() {
        StudentService studentService = new StudentService(new StudentListRepository(students), null);
        gradeService = new GradeService(new GradeListRepository(grades), studentService);
    }

    @Test
    void countMathGrades() {
        //WHEN
        long result = gradeService.countMathGrades();

        //THEN
        assertThat(result).isEqualTo(48);
    }

    @Test
    void averagePerStudentId() {
        //WHEN
        Map<String, BigDecimal> result = gradeService.averagePerStudentId();
        //THEN
        assertThat(result.get("00001003")).isEqualByComparingTo("2.68");
    }



}
