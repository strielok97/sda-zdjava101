package pl.sdacademy.java.adv.school.domain.grade;

import java.util.List;

public interface GradeRepository {
    List<Grade> findAllGrades();
}
