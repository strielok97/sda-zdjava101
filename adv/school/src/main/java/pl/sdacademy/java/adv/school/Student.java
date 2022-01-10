package pl.sdacademy.java.adv.school;

import java.time.LocalDate;

public class Student {

    private String id;
    private String lastName;
    private String firstName;
    private short startYear;
    private byte schoolYear;
    private char classCode;
    private LocalDate birthDate;
    private String city;

    public Student(String id, String lastName, String firstName, short startYear, byte schoolYear, char classCode, LocalDate birthDate, String city) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.startYear = startYear;
        this.schoolYear = schoolYear;
        this.classCode = classCode;
        this.birthDate = birthDate;
        this.city = city;
    }

    public Student () {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public short getStartYear() {
        return startYear;
    }

    public void setStartYear(short startYear) {
        this.startYear = startYear;
    }

    public byte getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(byte schoolYear) {
        this.schoolYear = schoolYear;
    }

    public char getClassCode() {
        return classCode;
    }

    public void setClassCode(char classCode) {
        this.classCode = classCode;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name= '" + lastName + " " + firstName + '\'' +
                ", startYear=" + startYear +
                ", class=" + schoolYear  + classCode +
                ", birthDate=" + birthDate +
                ", city='" + city + '\'' +
                '}';
    }
}
