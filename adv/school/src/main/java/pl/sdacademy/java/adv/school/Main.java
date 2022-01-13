package pl.sdacademy.java.adv.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sdacademy.java.adv.school.domain.student.model.Student;
import pl.sdacademy.java.adv.school.domain.student.parsers.csv.CsvStudentsParserImpl;
import pl.sdacademy.java.adv.school.domain.student.parsers.csv.OpenCsvStudentParser;
import pl.sdacademy.java.adv.school.domain.student.parsers.json.JsonStudentsParser;
import pl.sdacademy.java.adv.school.parsers.RecordsParser;

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
        // final RecordsParser<Student> studentsParser = new CsvStudentsParserImpl();
        final RecordsParser<Student> studentsParser = new JsonStudentsParser();
        final List<Student> students;
        try (InputStream studentsDataStream = Main.class.getResourceAsStream("/students.json")) {
            students = studentsParser.parseData(studentsDataStream);
        }


        students.forEach(s -> LOGGER.info(s.toString()));
        Clock clock = Clock.systemDefaultZone();
        LOGGER.info(LocalDateTime.now(clock).toString());
        clock = Clock.fixed(ZonedDateTime.parse("2022-01-10T12:00:00Z").toInstant(), ZoneId.of("UTC"));
        LOGGER.info(LocalDateTime.now(clock).toString());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.writeValue(System.out, students);



    }

}
