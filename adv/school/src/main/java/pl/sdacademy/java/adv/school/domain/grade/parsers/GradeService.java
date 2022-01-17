package pl.sdacademy.java.adv.school.domain.grade.parsers;

import pl.sdacademy.java.adv.school.domain.grade.Grade;
import pl.sdacademy.java.adv.school.domain.grade.GradeRepository;

import java.time.Clock;
import java.util.List;

public class GradeService {
    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public long countMathGrades() {

        List<Grade> allGrades = gradeRepository.findAllGrades();

        return allGrades.stream()
                .filter(t -> t.getSchoolSubjectCode().equals("MAT"))
                .count();
    }
}
