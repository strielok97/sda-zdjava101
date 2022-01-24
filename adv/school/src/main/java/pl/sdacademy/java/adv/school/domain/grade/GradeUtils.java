package pl.sdacademy.java.adv.school.domain.grade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GradeUtils {

    // Czasem spotykaną praktyką jest rzucanie wyjątku w PRYWATNYM konstruktorze klasy UŻYTKOWEJ.
    // Zapobiega to przypadkowemu stworzeniu nowej instancji nawet przy pomocy użyciu mechanizmu refleksji.
    // NIE należy modyfikować tego konstruktora!
    private GradeUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Wylicza średnią ważoną podanych ocen. Średnia jest wyliczana z dokładnością do <b>dwóch</b> miejsc po przecinku
     * i zaokrąglana w górę w standardowy sposób (sprawdź: {@link RoundingMode#HALF_UP}).
     * @param grades oceny, z których średnią należy wyliczyć.
     * @return {@code Optional} ze średnią ważoną lub <b>pusty</b> gdy:
     * suma wag jest mniejsza lub równa {@code 0} lub suma wyważonych ocen (po wymnożeniu z wagą) jest mniejsza od {@code 1}.
     */
    public static Optional<BigDecimal> gradesAverage(Collection<Grade> grades) {


        //BigDecimal - ma wartość i precyzje. typy jak float, double są szybkie do użycia
        BigDecimal nominator = grades.stream()
                .map(t -> t.getGradeWeight().getWeight().multiply(t.getValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add); //reduce tutaj zaczyna z 0, i za każdym razem dodaje kolejną

        BigDecimal denominator = grades.stream()
                .map(t -> t.getGradeWeight().getWeight())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (nominator.compareTo(BigDecimal.ONE)< 0 || denominator.compareTo(BigDecimal.ZERO) <= 0) {
            return Optional.empty();
        }
        BigDecimal result = nominator.divide(denominator, 2, RoundingMode.HALF_UP);

        return Optional.of(result);











        /*
        Zadanie to może zostać zrealizowane zarówno przy użyciu tradycyjnej pętli, jak i Stream API (zalecane!)
        Wagę oceny jako wartość BigDecimal można pobrać z grade.getGradeWeight().getWeight()
        Uwaga, do porównywania wartości BigDecimal należy bezwzględnie używać metody compareTo()! Przykład:
        (BigDecimal.ONE.equals(new BigDecimal("1.0"))) ---> false
        (BigDecimal.ONE.compareTo(new BigDecimal("1.0")) == 0) ---> true
        */

    }
}