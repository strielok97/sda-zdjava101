package pl.sdacademy.java.adv.school;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        //try with resources
        List<Student> students = new LinkedList<>(); //mutable
        try(InputStream studentsDataStream = Main.class.getResourceAsStream("/students.csv")) {
            if (studentsDataStream == null) {
                return;
            }
            Scanner scanner = new Scanner(studentsDataStream);
            int lineNumber = 0;
            while(scanner.hasNextLine()){
                lineNumber++;
                String line = scanner.nextLine();
                if (StringUtils.isBlank(line)) {
                    LOGGER.info("Skipping line [{}]", lineNumber);
                    continue;
                }
                LOGGER.info("Parsing line [{}]: {}", lineNumber, line);
                //parseStudent(line).ifPresent(student -> students.add(student));
                parseStudent(line).ifPresent(students::add);
            }
        }

    }

    public static Optional<Student> parseStudent(String line) {
        return Optional.empty();
    }

}
