package pl.sdacademy.java.adv.school.domain.student.parsers.csv;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sdacademy.java.adv.school.domain.student.model.Student;
import pl.sdacademy.java.adv.school.parsers.AbstractCsvParser;

import java.time.LocalDate;
import java.util.*;

public class CsvStudentsParserImpl extends AbstractCsvParser<Student> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvStudentsParserImpl.class);

    protected Optional<Student> parseRecord(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(",");
        List<String> strings = new ArrayList<>();
        while (scanner.hasNext()) {
            String next = scanner.next();
            strings.add(StringUtils.strip(next, "\""));
        }
        if (strings.size() != 10) {
            LOGGER.warn("Skipped line: {}", line);
            return Optional.empty();
        }
        Student student = new Student();
        try {
            student.setId(strings.get(0));
            student.setLastName(strings.get(1));
            student.setFirstName(strings.get(2));
            student.setStartYear(Short.parseShort(strings.get(3)));
            student.setSchoolYear(Byte.parseByte(strings.get(4)));
            student.setClassCode(strings.get(5).charAt(0));
            LocalDate birthDate = LocalDate.of(Integer.parseInt(strings.get(6)), Integer.parseInt(strings.get(7)),
                    Integer.parseInt(strings.get(8)));
            student.setBirthDate(birthDate);
            student.setCity(strings.get(9));
        } catch (Exception e) {
            LOGGER.error("Parsing error", e);
            return Optional.empty();
        }
        return Optional.of(student);

    }
}
