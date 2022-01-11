package pl.sdacademy.java.adv.school.domain.student;

import pl.sdacademy.java.adv.school.domain.student.model.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAllStudents();
}
