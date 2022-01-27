package pl.sdacademy.java.adv.school.domain.grade;

import java.util.Objects;

/**
 * Klasa pomocnicza do zadania {@link GradeService#averagePerSchoolGroupAndSubjectCode()}
 *
 * Wstępnie zadeklarowane metody muszą zostać, ale można (i trzeba) je uzupełnić, trzeba też będzie dodać
 * implementacje innych metod (ważnych w sytuacjach, gdy obiekt ma być używany jako klucz w mapie)
 */
public final class SchoolGroupToSubject {

    private final String schoolGroup;
    private final String subjectCode;

    public SchoolGroupToSubject(String schoolGroup, String subjectCode) {
        this.schoolGroup = schoolGroup;
        this.subjectCode = subjectCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolGroupToSubject that = (SchoolGroupToSubject) o;
        return schoolGroup.equals(that.schoolGroup) && subjectCode.equals(that.subjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolGroup, subjectCode);
    }
}
