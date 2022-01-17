package pl.sdacademy.java.adv.school.domain.grade;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GradeService {
    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public long countMathGrades() {
        return gradeRepository.findAllGrades().stream()
                .filter(t -> "MAT".equals(t.getSchoolSubjectCode()))
                .count();
    }

    public Map<String, BigDecimal> averagePerStudentId(){
        List<Grade> allGrades = gradeRepository.findAllGrades();
        return allGrades.stream()
                .collect(Collectors.groupingBy(
                        Grade::getStudentId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                grades -> GradeUtils.gradesAverage(grades).orElse(null)
                        )
                ));

    }

    public Map<StudentToSubject, BigDecimal> averagePerStudentIdAndSubjectCode() {

        List<Grade> allGrades = gradeRepository.findAllGrades();
        return allGrades.stream()
                .collect(Collectors.groupingBy(
                        grade -> new StudentToSubject(grade.getStudentId(), grade.getSchoolSubjectCode()),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                grades -> GradeUtils.gradesAverage(grades).orElse(null)
                        )
                ));
    }
}
