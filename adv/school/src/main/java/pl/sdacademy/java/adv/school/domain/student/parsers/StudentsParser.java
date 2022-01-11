package pl.sdacademy.java.adv.school.domain.student.parsers;

import pl.sdacademy.java.adv.school.domain.student.model.Student;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface StudentsParser {
    List<Student> parseData(InputStream inputStream) throws IOException;
}
