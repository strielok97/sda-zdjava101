package pl.sdacademy.java.adv.school.domain.student;

import org.apache.commons.lang3.StringUtils;
import pl.sdacademy.java.adv.school.domain.student.model.Student;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StudentService {
    private final StudentRepository studentRepository;
    private final Clock clock;

    public StudentService(StudentRepository studentRepository, Clock clock) {
        this.studentRepository = studentRepository;
        this.clock = clock;
    }

    public Optional<Student> getStudentById(String studentId) {
        return studentRepository.findAllStudents().stream()
                .filter(student -> student.getId().equals(studentId))
                .findAny();
    }

    public List<Student> getStudentsSortedByCityAndName() {

        List<Student> allStudents = studentRepository.findAllStudents();

        return allStudents.stream()
                .sorted(Comparator.comparing(Student::getCity)
                        .thenComparing(Student::getLastName)
                        .thenComparing(Student::getFirstName))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Student> getStudentsSortedByAge() {

        List<Student> allStudentsAge = studentRepository.findAllStudents();

        return allStudentsAge.stream()
                .sorted(Comparator.comparing(Student::getBirthDate))
                .collect(Collectors.toUnmodifiableList());

    }

    public List<Student> getStudentsSortedByAgeDesc() {
        List<Student> allStudentsAge = studentRepository.findAllStudents();

        return allStudentsAge.stream()
                .sorted(Comparator.comparing(Student::getBirthDate).reversed())
                .collect(Collectors.toUnmodifiableList());
    }

  public Map<String, List<Student>> getStudentsGroupedByCityAndSortedByName() {

    Map<String, List<Student>> groupedStudents =
        studentRepository.findAllStudents().stream()
            .sorted(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName))
            .collect(Collectors.groupingBy(Student::getCity));
    return groupedStudents;
  }

    public Map<String, Student> getStudentsMappedByIdentifier() {
        return studentRepository.findAllStudents().stream()
                //.collect(Collectors.toMap(Student::getId, student -> student))
                .collect(Collectors.toMap(Student::getId, Function.identity()));
    }

    public Map<String, Student> getOldestStudentMappedByCity() {
        return studentRepository.findAllStudents().stream()
                .collect(Collectors.toMap(Student::getCity, Function.identity(), (student1, student2) -> {
                    if (student1.getBirthDate().isBefore(student2.getBirthDate())){
                        return student1;
                    } else{
                        return student2;
                    }
                }));
    }

    public Map<String, List<Student>> getStudentsMappedByClass() {
        return studentRepository.findAllStudents().stream()
                .collect(Collectors
                        .groupingBy(student -> StringUtils.join(student.getSchoolYear(), student.getClassCode())));
    }

    public Map<String, Long> getNumberOfStudentsMappedByCity(){
        return studentRepository.findAllStudents().stream()
                .collect(Collectors.groupingBy(Student::getCity, Collectors.counting()));
    }

    public Double getPercentOfStudentNotFromCity(String city){
        long numberOfStudentsNotFromCity = studentRepository.findAllStudents().stream()
                .filter(student -> !student.getCity().equals(city))
                .count();
        int numberOfStudents = studentRepository.findAllStudents().size();

        return numberOfStudentsNotFromCity / (double) numberOfStudents * 100;
    }

    public Map<String, Period> getStudentsMappedToAge() {
        return studentRepository.findAllStudents().stream()
                .collect(Collectors
                        .toMap(Student::getId, student -> Period.between(student.getBirthDate(), LocalDate.now(clock))));
    }

    public Map<String, Integer> studentsToSkippedYears() {
        int currentYear = LocalDate.now(clock).getYear();
        return studentRepository.findAllStudents().stream()
                .filter(student -> student.getStartYear() + student.getSchoolYear() - currentYear > 0)
                .collect(Collectors
                        .toMap(Student::getId, student -> student.getStartYear() + student.getSchoolYear() - currentYear));
    }

    public Map<String, Integer> studentsToRepeatedYears() {
        int currentYear = LocalDate.now(clock).getYear();
        return studentRepository.findAllStudents().stream()
                .filter(student -> currentYear - student.getStartYear() > student.getSchoolYear())
                .collect(Collectors
                        .toMap(Student::getId, student -> currentYear - student.getStartYear() - student.getSchoolYear()));
    }
}
