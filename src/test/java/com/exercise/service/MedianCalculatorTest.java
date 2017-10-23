package com.exercise.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MedianCalculatorTest {

    private MedianCalculator medianCalculator;

    @Test
    public void should_calculate_median_odd_number_of_measurements() {
        // given
        List<BigDecimal> input = createInput(10.10, 16.16, 2.22, 3.33, 5.55);

        // when
        BigDecimal median = medianCalculator.calculateMedian(input);

        // then
        assertThat(median).isEqualTo(5.55);
    }

    @Test
    public void should_calculate_median_even_number_of_measurements() {
        // given
        List<BigDecimal> input = createInput(10.10, 2.22, 3.33, 5.55);

        // when
        BigDecimal median = medianCalculator.calculateMedian(input);

        // then
        assertThat(median).isEqualTo(4.44);
    }

    private List<BigDecimal> createInput(double... inputs) {
        return Arrays.stream(inputs)
                .mapToObj(BigDecimal::new)
                .collect(toList());
    }


    @Before
    public void setUp() {
        medianCalculator = new MedianCalculator();
    }

}
