package pl.sdacademy.java.adv.school;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sdacademy.java.adv.school.domain.student.model.Student;
import pl.sdacademy.java.adv.school.domain.student.parsers.StudentsParser;
import pl.sdacademy.java.adv.school.domain.student.parsers.csv.CsvStudentsParserImpl;
import pl.sdacademy.java.adv.school.domain.student.parsers.csv.OpenCsvStudentParser;

import java.io.IOException;
import java.io.InputStream;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        final StudentsParser studentsParser = new CsvStudentsParserImpl();
        final List<Student> students;
        try(InputStream studentsDataStream = Main.class.getResourceAsStream("/students.csv")) {
            students = studentsParser.parseData(studentsDataStream);
        }

        students.forEach(s -> LOGGER.info(s.toString()));
        Clock clock = Clock.systemDefaultZone();
        LOGGER.info(LocalDateTime.now(clock).toString());
        clock = Clock.fixed(ZonedDateTime.parse("2022-01-10T12:00:00Z").toInstant(), ZoneId.of("UTC"));
        LOGGER.info(LocalDateTime.now(clock).toString());
    }
}
