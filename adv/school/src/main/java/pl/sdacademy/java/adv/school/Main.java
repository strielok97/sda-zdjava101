package pl.sdacademy.java.adv.school;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sdacademy.java.adv.school.domain.student.model.Student;
import pl.sdacademy.java.adv.school.domain.student.parsers.StudentsParser;
import pl.sdacademy.java.adv.school.domain.student.parsers.csv.CsvStudentsParserImpl;


import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        final StudentsParser studentsParser = new CsvStudentsParserImpl();
        final List<Student> students;
        try(InputStream studentsDataStream = Main.class.getResourceAsStream("/students.csv")) {
            students = studentsParser.parseData(studentsDataStream);
        }

        students.forEach(s -> LOGGER.info(s.toString()));
    }
}
