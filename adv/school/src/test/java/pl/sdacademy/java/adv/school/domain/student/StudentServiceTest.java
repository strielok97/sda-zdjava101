package pl.sdacademy.java.adv.school.domain.student;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.adv.school.Main;
import pl.sdacademy.java.adv.school.domain.student.model.Student;
import pl.sdacademy.java.adv.school.domain.student.parsers.StudentsParser;
import pl.sdacademy.java.adv.school.domain.student.parsers.csv.CsvStudentsParserImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class StudentServiceTest {
    private static List<Student> students;

    private StudentService studentService;

    @BeforeAll
    static void beforeAll() throws IOException {
        final StudentsParser studentsParser = new CsvStudentsParserImpl();
        try(InputStream studentsDataStream = Main.class.getResourceAsStream("/students.csv")) {
            students = studentsParser.parseData(studentsDataStream);
        }
    }

    @BeforeEach
    void setUp() {
        studentService = new StudentService(new StudentListRepository(students));
    }

    @Test
    void getStudentsSortedByCityAndName() {
        //WHEN
        var students = studentService.getStudentsSortedByCityAndName();

        //THEN
        assertThat(students).first()
                .extracting(Student::getCity)
                .isEqualTo("Balice");

        assertThat(students).last()
                .extracting(Student::getCity)
                .isEqualTo("Zabierzów");

        assertThat(students).extracting(Student::getId)
                .containsExactly(
                        "00002002",
                        "00001001",
                        "00002003",
                        "00001298",
                        "00001009",
                        "00001004",
                        "00001008",
                        "00001007",
                        "00001003",
                        "00002005",
                        "00001005",
                        "00001002",
                        "00002001",
                        "00001006",
                        "00002004"
                );
    }

    @Test
    void getStudentsSortedByAge() {
        //WHEN
        var students = studentService.getStudentsSortedByAge();

        //THEN
        assertThat(students).extracting(Student::getId)
                .containsExactly(
                        "00002003",
                        "00002001",
                        "00002004",
                        "00002002",
                        "00002005",
                        "00001008",
                        "00001003",
                        "00001004",
                        "00001298",
                        "00001001",
                        "00001002",
                        "00001006",
                        "00001007",
                        "00001005",
                        "00001009"
                );
    }

    @Test
    void getStudentsSortedByAgeDesc() {
        //WHEN
        var students = studentService.getStudentsSortedByAgeDesc();

        //THEN
        assertThat(students).extracting(Student::getId)
                .containsExactly(
                        "00001009",
                        "00001005",
                        "00001007",
                        "00001006",
                        "00001002",
                        "00001001",
                        "00001298",
                        "00001004",
                        "00001003",
                        "00001008",
                        "00002005",
                        "00002002",
                        "00002004",
                        "00002001",
                        "00002003"
                );
    }


    @Test
    void getStudentsGroupedByCityAndSortedByName() {
        //WHEN
        Map<String,List<Student>> students = studentService.getStudentsGroupedByCityAndSortedByName();

        //THEN
        assertThat(students.get("Balice")).hasSize(1);
        assertThat(students.get("Zabierzów")).hasSize(1);
        assertThat(students.get("Kraków"))
                .hasSize(6)
                .extracting(Student::getId)
                .containsExactly("00001001", "00002003", "00001298", "00001009", "00001004", "00001008");
    }

    @Test
    void getStudentsMapByIdentifier() {
        //WHEN
        Map<String,Student> studentToIdMap = studentService.getStudentsMappedByIdentifier();

        //THEN
        assertThat(studentToIdMap).hasSize(15);
        studentToIdMap.forEach((studentId, student) -> assertThat(studentId).isEqualTo(student.getId()));
    }

    @Test
    void getOldestStudentMappedByCity() {
        //WHEN
        Map<String,Student> oldestStudentToCityMap = studentService.getOldestStudentMappedByCity();

        //THEN
        assertThat(oldestStudentToCityMap.get("Kraków").getId()).isEqualTo("00002003");
        assertThat(oldestStudentToCityMap.get("Krzeszowice").getId()).isEqualTo("00001003");
    }

    @Test
    void getStudentsMappedByClass() {
        //WHEN
        Map<String,List<Student>> students = studentService.getStudentsMappedByClass();

        //THEN
        assertThat(students.get("4A")).hasSize(5);
        assertThat(students.get("4B")).hasSize(5);
        assertThat(students.get("8A"))
                .hasSize(5)
                .extracting(Student::getId)
                .containsExactly("00002001", "00002002", "00002003", "00002004", "00002005");
    }

    @Test
    void getNumberOfStudentsMappedByCity() {
        //WHEN
        Map<String,Long> students = studentService.getNumberOfStudentsMappedByCity();

        //THEN
        assertThat(students.get("Kraków")).isEqualTo(6);
        assertThat(students.get("Wieliczka")).isEqualTo(1);
    }

    @Test
    void getPercentOfStudentNotFromCity(){
        //WHEN
        double result1 = studentService.getPercentOfStudentNotFromCity("Kraków");
        double result2 = studentService.getPercentOfStudentNotFromCity("Skawina");

        //THEN
        assertThat(result1).isEqualTo(60d);
        assertThat(result2).isEqualTo(86.66d, withPrecision(0.5d));
    }
}
