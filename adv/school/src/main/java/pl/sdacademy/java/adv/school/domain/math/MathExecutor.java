package pl.sdacademy.java.adv.school.domain.math;

public class MathExecutor {
    public static double divide(double x, double y){
        return execute(x, y, new MathOperation() {
            @Override
            public double operation(double x, double y) {
                return x/y;
            }
        });
    }

    public static double sum(double x, double y){
        return execute(x,y,new SumOperation());
    }

    public static double substract(double x, double y){
        return execute(x,y, (a,b)-> {
            return a-b;
        });
    }

    public static double multiply(double x, double y){
        return execute(x,y, (a,b) -> a*b);
    }

    public static double power(double x, double y){
        return execute(x,y,Math::pow);
    }

    private static double execute(double x, double y, MathOperation mathOperation){
        return mathOperation.operation(x,y);
    }

}
