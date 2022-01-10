package pl.sdacademy.java.adv.school;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
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
                LOGGER.info("Parsing line [{}]: {}", lineNumber, scanner.nextLine()); //najszybsza metoda korzystania z logera

            }

        }

    }
}
