package pl.sdacademy.java.adv.school.domain.math;

@FunctionalInterface
public interface MathOperation {  //z interfejsu funkcyjnego można zrobić lambde, bo ma jedną metode1
    double operation (double x, double y);
}
