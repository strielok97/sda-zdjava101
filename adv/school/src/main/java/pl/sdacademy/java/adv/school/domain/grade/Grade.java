package pl.sdacademy.java.adv.school.domain.grade;

import java.math.BigDecimal;

public class Grade {
    private String studentId;
    private String schoolSubjectCode;
    private GradeWeight gradeWeight;
    private BigDecimal value;

    public String getStudentId() {
        return studentId;
    }

    public String getSchoolSubjectCode() {
        return schoolSubjectCode;
    }

    public GradeWeight getGradeWeight() {
        return gradeWeight;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setSchoolSubjectCode(String schoolSubjectCode) {
        this.schoolSubjectCode = schoolSubjectCode;
    }

    public void setGradeWeight(GradeWeight gradeWeight) {
        this.gradeWeight = gradeWeight;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "studentId='" + studentId + '\'' +
                ", schoolSubjectCode='" + schoolSubjectCode + '\'' +
                ", gradeWeight=" + gradeWeight +
                ", value=" + value +
                '}';
    }
}
