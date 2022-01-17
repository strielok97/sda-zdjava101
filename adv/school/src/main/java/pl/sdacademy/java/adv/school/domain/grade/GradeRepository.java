package pl.sdacademy.java.adv.school.domain.grade;

import pl.sdacademy.java.adv.school.domain.student.model.Student;

import java.util.List;

public interface GradeRepository {
    List<Grade> findAllGrades();
}