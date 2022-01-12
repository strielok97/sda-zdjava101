package pl.sdacademy.java.adv.school.domain.student;

import pl.sdacademy.java.adv.school.domain.student.model.Student;

import java.util.Comparator;
import java.util.List;


public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentsSortedByCityAndName() {
        List<Student> allStudents = studentRepository.findAllStudents();

        return allStudents.stream()
                .sorted(Comparator.comparing(Student::getCity))
                .toList();
    }
}