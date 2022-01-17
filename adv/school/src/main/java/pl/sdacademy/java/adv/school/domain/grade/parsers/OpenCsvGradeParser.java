package pl.sdacademy.java.adv.school.domain.grade.parsers;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sdacademy.java.adv.school.domain.grade.Grade;
import pl.sdacademy.java.adv.school.domain.grade.GradeWeight;
import pl.sdacademy.java.adv.school.domain.student.parsers.csv.OpenCsvStudentParser;
import pl.sdacademy.java.adv.school.parsers.AbstractCsvParser;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public class OpenCsvGradeParser extends AbstractCsvParser<Grade> {

    private static final CSVParser CSV_PARSER = new CSVParserBuilder().build();
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCsvGradeParser.class);

    @Override
    protected Optional<Grade> parseRecord(String line) throws IOException {

        String[] strings = CSV_PARSER.parseLine(line);

        if(strings.length != 4){
            LOGGER.warn("Skipped line: {}", line);
            return Optional.empty();
        }

        Grade grade = new Grade();

        try {
            grade.setStudentId(strings[0]);
            grade.setSchoolSubjectCode(strings[1]);
            grade.setGradeWeight(GradeWeight.valueOf(strings[2]));
            grade.setValue(BigDecimal.valueOf(Double.parseDouble(strings[3])));
        } catch (Exception e){
            LOGGER.error("Parsing error", e);
            return Optional.empty();
        }
        return Optional.of(grade);

//        throw new UnsupportedOperationException("Zadanie domowe!");
    }

}