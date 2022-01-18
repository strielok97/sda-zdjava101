package pl.sdacademy.java.adv.school.domain.math;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MathExecutorTest {
    @Test
    void sum(){
        //WHEN
        double result = MathExecutor.sum(1,2);
        //THEN
        assertThat(result).isEqualTo(3);
    }

    @Test
    void divide(){
        //WHEN
        double result = MathExecutor.divide(4,2);
        //THEN
        assertThat(result).isEqualTo(2);
    }

    @Test
    void substract(){
        //WHEN
        double result = MathExecutor.substract(4,1);
        //THEN
        assertThat(result).isEqualTo(3);
    }

    @Test
    void multiply(){
        //WHEN
        double result = MathExecutor.multiply(4,3);
        //THEN
        assertThat(result).isEqualTo(12);
    }
    @Test
    void power(){
        //WHEN
        double result = MathExecutor.power(2,5);
        //THEN
        assertThat(result).isEqualTo(32);
    }

}