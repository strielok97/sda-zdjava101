package pl.sdacademy.java.adv.school.domain.grade;

import java.math.BigDecimal;

public enum GradeWeight {
    EGZ(new BigDecimal("2")),
    PYT(new BigDecimal("1.5")),
    AKT(new BigDecimal("1.0"));

    private final BigDecimal weight;

    GradeWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getWeight() {
        return weight;
    }
}
