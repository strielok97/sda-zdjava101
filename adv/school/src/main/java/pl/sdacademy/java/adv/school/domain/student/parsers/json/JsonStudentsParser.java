package pl.sdacademy.java.adv.school.domain.student.parsers.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.sdacademy.java.adv.school.domain.student.model.Student;
import pl.sdacademy.java.adv.school.parsers.RecordsParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class JsonStudentsParser implements RecordsParser<Student> {

    private final ObjectMapper objectMapper;

    public JsonStudentsParser() {
        this.objectMapper = new ObjectMapper();
    }



    @Override
    public List<Student> parseData(InputStream inputStream) throws IOException {
        objectMapper.registerModule(new JavaTimeModule());
        Student[] jsonStudentsTable = objectMapper.readValue(inputStream,Student[].class);
        return Arrays.asList(jsonStudentsTable);
    }
}
