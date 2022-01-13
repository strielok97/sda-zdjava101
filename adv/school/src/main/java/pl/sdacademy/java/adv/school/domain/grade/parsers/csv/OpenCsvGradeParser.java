package pl.sdacademy.java.adv.school.domain.grade.parsers.csv;

import pl.sdacademy.java.adv.school.domain.grade.Grade;
import pl.sdacademy.java.adv.school.parsers.AbstractCsvParser;

import java.io.IOException;
import java.util.Optional;

public class OpenCsvGradeParser extends AbstractCsvParser<Grade> {
    @Override
    protected Optional<Grade> parseRecord(String line) throws IOException {
        throw new UnsupportedOperationException("Zadanie domowe!");
    }
}
