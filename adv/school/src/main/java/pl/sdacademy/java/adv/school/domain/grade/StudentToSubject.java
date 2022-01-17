package pl.sdacademy.java.adv.school.domain.grade;

import java.util.Objects;

public final class StudentToSubject {

    private final String studentId;
    private final String subjectCode;

    public StudentToSubject(String studentId, String subjectCode) {
        this.studentId = studentId;
        this.subjectCode = subjectCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentToSubject that = (StudentToSubject) o;
        return studentId.equals(that.studentId) && subjectCode.equals(that.subjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, subjectCode);
    }
}
