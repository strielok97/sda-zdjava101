package pl.sdacademy.java.adv.school.domain.student;

import pl.sdacademy.java.adv.school.domain.student.model.Student;

import java.util.List;

public class StudentListRepository implements StudentRepository {
    private final List<Student> students;

    public StudentListRepository(List<Student> students) {
        this.students = students;
    }

    @Override
    public List<Student> findAllStudents() {
        return students;
    }
}
