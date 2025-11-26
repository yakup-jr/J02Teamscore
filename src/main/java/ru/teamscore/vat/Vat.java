package ru.teamscore.vat;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The type Vat.
 */
public class Vat {
    private static final BigDecimal vatRate = new BigDecimal("0.2");
    private static final BigDecimal rateWithVat = new BigDecimal("1").add(vatRate);
    private final BigDecimal amountWithoutVat;

    private Vat(BigDecimal amountWithoutVat) {
        this.amountWithoutVat = amountWithoutVat;
    }

    /**
     * Gets amount with vat.
     *
     * @return the amount with vat
     */
    public BigDecimal getAmountWithVat() {
        return amountWithoutVat.multiply(rateWithVat).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Gets amount without vat.
     *
     * @return the amount without vat
     */
    public BigDecimal getAmountWithoutVat() {
        return amountWithoutVat.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Gets vat for check.
     *
     * @return the vat for check
     */
    public BigDecimal getVatForCheck() {
        return amountWithoutVat.multiply(vatRate).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Gets vat for tax return.
     *
     * @return the vat for tax return
     */
    public BigDecimal getVatForTaxReturn() {
        return amountWithoutVat.multiply(vatRate).setScale(0, RoundingMode.HALF_UP);
    }

    /**
     * Value of vat.
     *
     * @param amount the amount
     * @param option the option
     * @return the vat
     */
    public static Vat valueOf(BigDecimal amount, VatPriceOption option) {
        if (new BigDecimal("0").compareTo(amount) > 0) {
            throw new IllegalArgumentException("Amount cant be negative");
        }

        return switch (option) {
            case INCLUDING_VAT -> {
                BigDecimal round =
                    amount.divide(rateWithVat, 2, RoundingMode.HALF_UP);
                yield new Vat(round);
            }
            case EXCLUDING_VAT -> new Vat(amount);
        };
    }

    /**
     * The enum Vat price option.
     */
    enum VatPriceOption {
        /**
         * Excluding vat vat price option.
         */
        EXCLUDING_VAT,
        /**
         * Including vat vat price option.
         */
        INCLUDING_VAT
    }
}
