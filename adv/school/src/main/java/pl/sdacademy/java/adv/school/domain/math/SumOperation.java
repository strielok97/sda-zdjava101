package pl.sdacademy.java.adv.school.domain.math;

public class SumOperation implements MathOperation{
    @Override
    public double operation(double x, double y) {
        return x+y;
    }
}
