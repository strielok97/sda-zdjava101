package pl.sdacademy.java.adv.school.domain.grade;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.adv.school.Main;
import pl.sdacademy.java.adv.school.domain.grade.parsers.csv.OpenCsvGradeParser;
import pl.sdacademy.java.adv.school.domain.student.StudentListRepository;
import pl.sdacademy.java.adv.school.domain.student.StudentService;
import pl.sdacademy.java.adv.school.domain.student.model.Student;
import pl.sdacademy.java.adv.school.domain.student.parsers.csv.OpenCsvStudentParser;
import pl.sdacademy.java.adv.school.parsers.RecordsParser;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GradeServiceTest {

    private static List<Grade> grades;
    private static List<Student> students;

    private GradeService gradeService;

    @BeforeAll
    static void beforeAll() throws IOException {
        final RecordsParser<Grade> gradesParser = new OpenCsvGradeParser();
        try (InputStream gradesDataStream = Main.class.getResourceAsStream("/grades.csv")) {
            grades = gradesParser.parseData(gradesDataStream);
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

    @Test
    void averagePerStudentIdAndSubjectCode() {
        //WHEN
        Map<StudentToSubject, BigDecimal> result = gradeService.averagePerStudentIdAndSubjectCode();

        //THEN
        assertThat(result.get(new StudentToSubject("00001003", "MAT"))).isEqualByComparingTo("1.91");
        assertThat(result.get(new StudentToSubject("00001003", "POL"))).isEqualByComparingTo("2.7");
        assertThat(result.get(new StudentToSubject("00001003", "HIS"))).isEqualByComparingTo("3.14");
        assertThat(result.get(new StudentToSubject("00001003", "ANG"))).isEqualByComparingTo("3.22");
    }

    @Test
    void averagePerSchoolGroup() {
        //WHEN
        Map<String, BigDecimal> result = gradeService.averagePerSchoolGroup();

        //THEN
        assertThat(result.get("4A")).isEqualByComparingTo("3.83");
        assertThat(result.get("4B")).isEqualByComparingTo("3.8");
        assertThat(result.get("8A")).isEqualByComparingTo("4.09");
    }

    @Test
    void threatenedStudents() {
        //WHEN
        Map<String,BigDecimal> result = gradeService.threatenedStudents(new BigDecimal("3.0"));

        //THEN
        assertThat(result).hasSize(3);

        assertThat(result.get("00001003")).isEqualByComparingTo("2.68");
        assertThat(result.get("00001008")).isEqualByComparingTo("1.84");
        assertThat(result.get("00002004")).isEqualByComparingTo("2.77");
    }

    @Test
    void topStudents() {
        //WHEN
        Map<String,BigDecimal> result = gradeService.topStudents(new BigDecimal("4.5"));

        //THEN
        assertThat(result).hasSize(2);

        assertThat(result.get("00001009")).isEqualByComparingTo("5.02");
        assertThat(result.get("00002005")).isEqualByComparingTo("5.14");
    }

    @Test
    void averagePerSchoolGroupAndSubjectCode() {
        //WHEN
        var result = gradeService.averagePerSchoolGroupAndSubjectCode();

        //THEN
        assertThat(result.get(new SchoolGroupToSubject("4A","MAT"))).isEqualByComparingTo("3.86");
        assertThat(result.get(new SchoolGroupToSubject("4B","MAT"))).isEqualByComparingTo("3.90");
        assertThat(result.get(new SchoolGroupToSubject("8A","MAT"))).isEqualByComparingTo("4.82");
    }

    @Test
    void mostToLeastActiveStudents() {
        //WHEN
        var result = gradeService.mostToLeastActiveStudentsOrderedById();

        //THEN
        assertThat(result).containsExactly(
            "00001009",
            "00002002",
            "00002005",
            "00001001",
            "00001004",
            "00002001",
            "00002003",
            "00001002",
            "00001003",
            "00001005",
            "00001006",
            "00001008",
            "00001298"
        );
    }
}
