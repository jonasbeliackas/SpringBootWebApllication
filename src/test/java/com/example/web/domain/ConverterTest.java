package com.example.web.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    @ParameterizedTest
    @MethodSource("testWithMethodSourceGetWeatherByLocationMethod")
    void convertToEurTest(String expected,double otherCurrencyValue, String value ) {
        Converter converter = new Converter();
        converter.setCurrencyValue(otherCurrencyValue);
        converter.setValueInTextField(value);
        assertEquals(expected,converter.convertToEur());
    }

    private static Stream<Arguments> testWithMethodSourceGetWeatherByLocationMethod()
    {
        return Stream.of(
                Arguments.of("2.0",1.0,"2"),
                Arguments.of("3.070876",1.6282,"5"),
                Arguments.of("1.515881",659.682380,"1000"),
                Arguments.of("0.367423",4.245790,"1.56"),
                Arguments.of("Error",4.245790,"1.a688"),
                Arguments.of("Error",4.245790,"1-56")
        );
    }
}