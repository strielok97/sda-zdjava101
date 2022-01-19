package pl.sdacademy.java.adv.school.domain.grade;

import org.apache.commons.lang3.StringUtils;
import pl.sdacademy.java.adv.school.domain.student.StudentService;
import pl.sdacademy.java.adv.school.domain.student.model.Student;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentService studentService;

    public GradeService(GradeRepository gradeRepository, StudentService studentService) {
        this.gradeRepository = gradeRepository;
        this.studentService = studentService;
    }

    public long countMathGrades() {
        return gradeRepository.findAllGrades().stream()
                .filter(t -> "MAT".equals(t.getSchoolSubjectCode()))
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

    public Map<String, BigDecimal> averagePerSchoolGroup() {

        List<Grade> allGrades = gradeRepository.findAllGrades();
        return allGrades.stream()
                .collect(Collectors.groupingBy(
                        //grade -> gradeToSchoolGroup(grade),
                        this::gradeToSchoolGroup,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                grades -> GradeUtils.gradesAverage(grades).orElse(null)
                        )
                ));
    }

    private String gradeToSchoolGroup(Grade grade) {
        Student student = studentService.getStudentById(grade.getStudentId())
                .orElseThrow(); //warto rzucic wlasny wyjatek z Id ucznia

        return StringUtils.join(student.getSchoolYear(), student.getClassCode());
    }

    public Map<String, BigDecimal> threatenedStudents(BigDecimal threshold) {
        Predicate<Map.Entry<String, BigDecimal>> predicate = entry -> entry.getValue().compareTo(threshold) <= 0;
        return studentByAveragePredicate(predicate);
    }

    public Map<String, BigDecimal> topStudents(BigDecimal threshold) {
        return studentByAveragePredicate(entry -> entry.getValue().compareTo(threshold) >= 0);
    }

    private Map<String, BigDecimal> studentByAveragePredicate(Predicate<Map.Entry<String, BigDecimal>> predicate) {
        return averagePerStudentId().entrySet().stream()
                .filter(predicate)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Zwraca mapę zawierającą informacje o wynikach danych klas dla poszczególnych przedmiotów.
     * Średnie wyliczone mają być dla klucza z klasą i przedmiotem (czyli parą wartości, np. {@code 4A,MAT}).
     */
    public Map<SchoolGroupToSubject, BigDecimal> averagePerSchoolGroupAndSubjectCode() {
        throw new UnsupportedOperationException("Zadanie domowe!");
    }

    /**
     * Zwraca listę uczniów posortowanych MALEJĄCO wg liczby ocen za aktywność oraz ROSNĄCO wg identyfikatora ucznia.
     * Lista zawiera uczniów mających PRZYNAJMNIEJ JEDNĄ ocenę za aktywność (typ oceny: {@code AKT}).
     */
    public List<String> mostToLeastActiveStudentsOrderedById() {
        throw new UnsupportedOperationException("Zadanie domowe!");
    }
}
