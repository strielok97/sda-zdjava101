package pl.sdacademy.java.adv.school.domain.math;

@FunctionalInterface
public interface MathOperation {  //z interfejsu funkcyjnego można zrobić lambde, bo ma jedną metode
    double operation (double x, double y);
}
