package ru.teamscore.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VatTest {

    @ParameterizedTest
    @MethodSource("provideGetAmountWithVat")
    void getAmountWithVat(Vat vat, BigDecimal expected) {
        assertEquals(expected, vat.getAmountWithVat());
    }

    private static Stream<Arguments> provideGetAmountWithVat() {
        return Stream.of(
            Arguments.of(Vat.valueOf(new BigDecimal("99.99"), VatPriceOption.EXCLUDING_VAT),
                new BigDecimal("119.99")),
            Arguments.of(Vat.valueOf(new BigDecimal("92.40"), VatPriceOption.INCLUDING_VAT),
                new BigDecimal("92.40")),
            Arguments.of(Vat.valueOf(new BigDecimal("83.32"), VatPriceOption.EXCLUDING_VAT),
                new BigDecimal("99.98")),
            Arguments.of(Vat.valueOf(new BigDecimal("5.94"), VatPriceOption.INCLUDING_VAT),
                new BigDecimal("5.94")),
            Arguments.of(Vat.valueOf(new BigDecimal("0"), VatPriceOption.INCLUDING_VAT),
                new BigDecimal("0.00"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideGetVatForCheck")
    void getVatForCheck(Vat vat, BigDecimal expected) {
        assertEquals(expected, vat.getVatForCheck());
    }

    private static Stream<Arguments> provideGetVatForCheck() {
        return Stream.of(
            Arguments.of(Vat.valueOf(new BigDecimal("99.99"), VatPriceOption.EXCLUDING_VAT),
                new BigDecimal("20.00")),
            Arguments.of(Vat.valueOf(new BigDecimal("92.40"), VatPriceOption.INCLUDING_VAT),
                new BigDecimal("15.40")),
            Arguments.of(Vat.valueOf(new BigDecimal("83.32"), VatPriceOption.EXCLUDING_VAT),
                new BigDecimal("16.66")),
            Arguments.of(Vat.valueOf(new BigDecimal("5.94"), VatPriceOption.INCLUDING_VAT),
                new BigDecimal("0.99")),
            Arguments.of(Vat.valueOf(new BigDecimal("0"), VatPriceOption.INCLUDING_VAT),
                new BigDecimal("0.00"))
        );
    }


    @ParameterizedTest
    @MethodSource("provideGetVatForTaxReturn")
    void getVatForTaxReturn(Vat vat, BigDecimal expected) {
        assertEquals(expected, vat.getVatForTaxReturn());
    }

    private static Stream<Arguments> provideGetVatForTaxReturn() {
        return Stream.of(
            Arguments.of(Vat.valueOf(new BigDecimal("99.99"), VatPriceOption.EXCLUDING_VAT),
                new BigDecimal("20")),
            Arguments.of(Vat.valueOf(new BigDecimal("92.40"), VatPriceOption.INCLUDING_VAT),
                new BigDecimal("15")),
            Arguments.of(Vat.valueOf(new BigDecimal("83.32"), VatPriceOption.EXCLUDING_VAT),
                new BigDecimal("17")),
            Arguments.of(Vat.valueOf(new BigDecimal("5.94"), VatPriceOption.INCLUDING_VAT),
                new BigDecimal("1")),
            Arguments.of(Vat.valueOf(new BigDecimal("0"), VatPriceOption.INCLUDING_VAT),
                new BigDecimal("0"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideValueOf")
    void valueOf(Vat vat, BigDecimal expected) {
        assertEquals(expected, vat.getAmountWithoutVat());
    }

    private static Stream<Arguments> provideValueOf() {
        return Stream.of(
            Arguments.of(Vat.valueOf(new BigDecimal("100"), VatPriceOption.EXCLUDING_VAT),
                new BigDecimal("100.00")),
            Arguments.of(Vat.valueOf(new BigDecimal("100"), VatPriceOption.INCLUDING_VAT),
                new BigDecimal("83.33")),
            Arguments.of(Vat.valueOf(new BigDecimal("0"), VatPriceOption.EXCLUDING_VAT),
                new BigDecimal("0.00")),
            Arguments.of(Vat.valueOf(new BigDecimal("0"), VatPriceOption.INCLUDING_VAT),
                new BigDecimal("0.00"))
        );
    }

    @Test
    void valueOf_whenAmountNegative_throwIllegalArgumentException() {
        BigDecimal amount = new BigDecimal("-1");
        assertThrows(IllegalArgumentException.class,
            () -> Vat.valueOf(amount, VatPriceOption.EXCLUDING_VAT));
    }
}