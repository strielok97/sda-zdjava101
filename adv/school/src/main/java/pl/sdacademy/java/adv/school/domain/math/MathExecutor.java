package pl.sdacademy.java.adv.school.domain.math;

public class MathExecutor {

    public static double sum(double x, double y) { //pełnoprawna impplementacja interfejsu
        return execute(x, y, new SumOperation());
    }

    public static double substract(double x, double y) { //lambda, sposób dłuższy
        return execute(x, y, (a ,b) -> {
            return a - b;
        });
    }

    public static double divide(double x, double y) {
        return execute(x, y, new MathOperation() { //klasa anonimowa - musi zaimplementować wszystkie metody abstrakcyjne
            @Override
            public double operation(double x, double y) {
                return x / y;
            }
        });
    }

    public static double multiply(double x, double y) { //lambda, krótszy sposób
        return execute(x, y, (a, b) -> a * b);
    }

    public static double power(double x, double y) { //przez referencje na metodę. implementacji metody MathOperation
        return execute(x, y, Math::pow);  //ona pasuje do naszego interfejsu więc moemy zrobić w ten sposób
    }

    private static double execute(double x, double y, MathOperation mathOperation) { //prywatne metody na koncu, stworzona jest tak, żeby można było użyc lambdy i zaimplementować iterfejs funkcyjny
        return mathOperation.operation(x, y);
    }

    //lambda - skrócona implementacja interfejsu
}
