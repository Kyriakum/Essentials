package com.earth2me.essentials.utils;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

public class NumberUtilTest {

    @Test
    public void testStringParseBDecimal() throws ParseException {

        final BigDecimal decimal = NumberUtil.parseStringToBDecimal("10,000,000.5");
        assertEquals(decimal.toString(), "10000000.5");

        final BigDecimal decimal2 = NumberUtil.parseStringToBDecimal("10.000.000,5");
        assertNotEquals(decimal2.toString(), "10000000.5");

        final BigDecimal decimal3 = NumberUtil.parseStringToBDecimal("10000000,5");
        assertNotEquals(decimal3.toString(), "10000000.5");

        final BigDecimal decimal4 = NumberUtil.parseStringToBDecimal("10000000.5");
        assertEquals(decimal4.toString(), "10000000.5");

        final BigDecimal decimal5 = NumberUtil.parseStringToBDecimal("10000000.50000");
        assertEquals(decimal5.toString(), "10000000.5");

        final BigDecimal decimal6 = NumberUtil.parseStringToBDecimal(".50000");
        assertEquals(decimal6.toString(), "0.5");

        final BigDecimal decimal7 = NumberUtil.parseStringToBDecimal("00000.50000");
        assertEquals(decimal7.toString(), "0.5");

        final BigDecimal decimal8 = NumberUtil.parseStringToBDecimal(",50000");
        assertEquals(decimal8.toString(), "50000");

        assertThrows(ParseException.class, ()-> {
            NumberUtil.parseStringToBDecimal("abc");
        });

        assertThrows(ParseException.class, ()-> {
            NumberUtil.parseStringToBDecimal("");
        });

        assertThrows(ParseException.class, ()-> {
            NumberUtil.parseStringToBDecimal("M");
        });
    }

    @Test
    public void testStringParseBDecimalLocale() throws ParseException {

        final Locale locale = Locale.GERMANY;

        final BigDecimal decimal = NumberUtil.parseStringToBDecimal("10,000,000.5", locale);
        assertNotEquals(decimal.toString(), "10000000.5");

        final BigDecimal decimal2 = NumberUtil.parseStringToBDecimal("10.000.000,5", locale);
        assertEquals(decimal2.toString(), "10000000.5");

        final BigDecimal decimal3 = NumberUtil.parseStringToBDecimal("10000000,5", locale);
        assertEquals(decimal3.toString(), "10000000.5");

        final BigDecimal decimal4 = NumberUtil.parseStringToBDecimal("10000000.5", locale);
        assertNotEquals(decimal4.toString(), "10000000.5");

        final BigDecimal decimal5 = NumberUtil.parseStringToBDecimal(",5", locale);
        assertEquals(decimal5.toString(), "0.5");

        final BigDecimal decimal6 = NumberUtil.parseStringToBDecimal(".50000", locale);
        assertEquals(decimal6.toString(), "50000");
    }
}
