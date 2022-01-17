package pl.sdacademy.java.adv.school.domain.grade.parsers;

import pl.sdacademy.java.adv.school.domain.grade.Grade;
import pl.sdacademy.java.adv.school.domain.grade.GradeRepository;
import pl.sdacademy.java.adv.school.domain.grade.GradeUtils;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<String, BigDecimal> averagePerStudentId() {

        List<Grade> allGrades = gradeRepository.findAllGrades();

        return allGrades.stream()
                .collect(Collectors.groupingBy(
                        Grade::getStudentId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                grades -> GradeUtils.gradesAverage(grades).orElse(null)
                        )
                ));  //toMap przyporządkowuje kluczowi wartość. lepiej użyć innego, żeby po jednej stronie mieć id a po drugiej liste jego ocen -> groupingBy. orElse jest po to żeby był na końcu BigDecimal
    }
}
