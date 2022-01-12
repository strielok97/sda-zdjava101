package pl.sdacademy.java.adv.school.domain.student.parsers.csv;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sdacademy.java.adv.school.domain.student.model.Student;
import pl.sdacademy.java.adv.school.domain.student.parsers.StudentsParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public abstract class AbstractCsvStudentParser implements StudentsParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCsvStudentParser.class);

    @Override
    public List<Student> parseData(InputStream studentsDataStream) throws IOException {

        if (studentsDataStream == null) {
            return Collections.emptyList();
        }

        List<Student> students = new LinkedList<>();
        Scanner scanner = new Scanner(studentsDataStream);
        int lineNumber = 0;
        while (scanner.hasNextLine()) {
            lineNumber++;
            String line = scanner.nextLine();
            if (StringUtils.isBlank(line)) {                        //line == null || line.length() == 0
                LOGGER.info("Skipping line [{}] ", lineNumber);
                continue;
            }
            LOGGER.info("Parsing line [{}]:{} ", lineNumber, line);
            //parseStudent(line).ifPresent(student -> students.add(student));
            parseStudent(line).ifPresent(students::add);
        }
        return students;
    }

    protected abstract Optional<Student> parseStudent(String line) throws IOException;
}