package pl.sdacademy.java.adv.school.domain.grade;

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
}
