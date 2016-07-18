/*
 * Copyright (c) 2009-2016 Matthew R. Harrah
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package org.gedcom4j.parser;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.gedcom4j.parser.DateParser.ImpreciseDatePreference;
import org.junit.Test;

/**
 * Test case for DateParser. All test cases are for Gregorian dates.
 * 
 * @author frizbog
 */
public class DateParserTest {

    /**
     * Class under test
     */
    private final DateParser dp = new DateParser();

    /**
     * Test BC Dates
     */
    @Test
    public void testBC() {
        SimpleDateFormat sdf = new SimpleDateFormat("y G");
        Date d;

        d = dp.parse("1905 BC");
        assertNotNull(d);
        assertEquals("1905 BC", sdf.format(d));

        d = dp.parse("1905");
        assertNotNull(d);
        assertEquals("1905 AD", sdf.format(d));

        d = dp.parse("500 BC");
        assertNotNull(d);
        assertEquals("500 BC", sdf.format(d));

        d = dp.parse("500");
        assertNotNull(d);
        assertEquals("500 AD", sdf.format(d));

        d = dp.parse("50 BC");
        assertNotNull(d);
        assertEquals("50 BC", sdf.format(d));

        d = dp.parse("50");
        assertNotNull(d);
        assertEquals("50 AD", sdf.format(d));

        d = dp.parse("5 BC");
        assertNotNull(d);
        assertEquals("5 BC", sdf.format(d));

        d = dp.parse("5");
        assertNotNull(d);
        assertEquals("5 AD", sdf.format(d));

        d = dp.parse("1");
        assertNotNull(d);
        assertEquals("1 AD", sdf.format(d));

        d = dp.parse("1 BC");
        assertNotNull(d);
        assertEquals("1 BC", sdf.format(d));

    }

    // /**
    // * Test dates around the English calendar change of 1752
    // */
    // @Test
    // public void testEnglishCalendarChange() {
    // fail("Not implemented yet");
    // }

    /**
     * Test for {@link DateParser#formatBC(String)}
     */
    @Test
    public void testFormatBC() {
        assertEquals("14 DEC -2049", dp.formatBC("14 DEC 2050BC"));
        assertEquals("DEC -2049", dp.formatBC("DEC 2050BC"));
        assertEquals("-2049", dp.formatBC("2050BC"));
        assertEquals("14 DEC -649", dp.formatBC("14 DEC 650BC"));
        assertEquals("DEC -649", dp.formatBC("DEC 650BC"));
        assertEquals("-649", dp.formatBC("650BC"));
        assertEquals("14 DEC -49", dp.formatBC("14 DEC 50BC"));
        assertEquals("DEC -49", dp.formatBC("DEC 50BC"));
        assertEquals("-49", dp.formatBC("50BC"));

        assertEquals("14 DEC -2049", dp.formatBC("14 DEC 2050 BC"));
        assertEquals("DEC -2049", dp.formatBC("DEC 2050 BC"));
        assertEquals("-2049", dp.formatBC("2050 BC"));
        assertEquals("14 DEC -649", dp.formatBC("14 DEC 650 BC"));
        assertEquals("DEC -649", dp.formatBC("DEC 650 BC"));
        assertEquals("-649", dp.formatBC("650 BC"));
        assertEquals("14 DEC -49", dp.formatBC("14 DEC 50 BC"));
        assertEquals("DEC -49", dp.formatBC("DEC 50 BC"));
        assertEquals("-49", dp.formatBC("50 BC"));

        assertEquals("14 DEC -2049", dp.formatBC("14 DEC 2050 B.C."));
        assertEquals("DEC -2049", dp.formatBC("DEC 2050 B.C."));
        assertEquals("-2049", dp.formatBC("2050 B.C."));
        assertEquals("14 DEC -649", dp.formatBC("14 DEC 650 B.C."));
        assertEquals("DEC -649", dp.formatBC("DEC 650 B.C."));
        assertEquals("-649", dp.formatBC("650 B.C."));
        assertEquals("14 DEC -49", dp.formatBC("14 DEC 50 B.C."));
        assertEquals("DEC -49", dp.formatBC("DEC 50 B.C."));
        assertEquals("-49", dp.formatBC("50 B.C."));

        assertEquals("14 DEC -2049", dp.formatBC("14 DEC 2050 BCE"));
        assertEquals("DEC -2049", dp.formatBC("DEC 2050 BCE"));
        assertEquals("-2049", dp.formatBC("2050 BCE"));
        assertEquals("14 DEC -649", dp.formatBC("14 DEC 650 BCE"));
        assertEquals("DEC -649", dp.formatBC("DEC 650 BCE"));
        assertEquals("-649", dp.formatBC("650 BCE"));
        assertEquals("14 DEC -49", dp.formatBC("14 DEC 50 BCE"));
        assertEquals("DEC -49", dp.formatBC("DEC 50 BCE"));
        assertEquals("-49", dp.formatBC("50 BCE"));

        assertEquals("14 DEC -2049", dp.formatBC("14 DEC 2050 B.C.E."));
        assertEquals("DEC -2049", dp.formatBC("DEC 2050 B.C.E."));
        assertEquals("-2049", dp.formatBC("2050 B.C.E."));
        assertEquals("14 DEC -649", dp.formatBC("14 DEC 650 B.C.E."));
        assertEquals("DEC -649", dp.formatBC("DEC 650 B.C.E."));
        assertEquals("-649", dp.formatBC("650 B.C.E."));
        assertEquals("14 DEC -49", dp.formatBC("14 DEC 50 B.C.E."));
        assertEquals("DEC -49", dp.formatBC("DEC 50 B.C.E."));
        assertEquals("-49", dp.formatBC("50 B.C.E."));

        assertEquals("50", dp.formatBC("50"));
        assertEquals("650", dp.formatBC("650"));
        assertEquals("1950", dp.formatBC("1950"));
        assertEquals("JUN 1950", dp.formatBC("JUN 1950"));
        assertEquals("14 JUN 1950", dp.formatBC("14 JUN 1950"));
    }

    // /**
    // * Test French Republican calendar
    // */
    // @Test
    // public void testFrenchRepublican() {
    // fail("Not implemented yet");
    // }
    //
    // /**
    // * Test Hebrew calendar
    // */
    // @Test
    // public void testHebrew() {
    // fail("Not implemented yet");
    // }
    //
    /**
     * Test parsing a single date with month, day, and year
     */
    @Test
    public void testParseApproximateSingleDateFull() {
        assertDate(dp.parse("ABT 17 JUL 2016"), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("ABT 31 JUL 2016"), 2016, Calendar.JULY, 31);
        assertDate(dp.parse("ABT 1 JUL 932"), 932, Calendar.JULY, 1);
        // 2016 was a leap year
        assertDate(dp.parse("ABT 29 FEB 2016"), 2016, Calendar.FEBRUARY, 29);
        // 1900 was NOT a leap year, so Feb 29 of that year is parsed as Mar 1.
        assertDate(dp.parse("ABT 29 FEB 1900"), 1900, Calendar.MARCH, 1);
        assertDate(dp.parse("APPX 29 FEB 1900"), 1900, Calendar.MARCH, 1);
        assertDate(dp.parse("APPROX 29 FEB 1900"), 1900, Calendar.MARCH, 1);
        assertDate(dp.parse("APPX. 29 FEB 1900"), 1900, Calendar.MARCH, 1);
        assertDate(dp.parse("APPROX. 29 FEB 1900"), 1900, Calendar.MARCH, 1);

        assertDate(dp.parse("ABT. 17 JUL 2016"), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("ABT. 1 JUL 932"), 932, Calendar.JULY, 1);
        // 2016 was a leap year
        assertDate(dp.parse("ABT. 29 FEB 2016"), 2016, Calendar.FEBRUARY, 29);
        // 1900 was NOT a leap year, so Feb 29 of that year is parsed as Mar 1.
        assertDate(dp.parse("ABT. 29 FEB 1900"), 1900, Calendar.MARCH, 1);

        assertDate(dp.parse("CAL 17 JUL 2016"), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("CAL 31 JUL 2016"), 2016, Calendar.JULY, 31);
        assertDate(dp.parse("CAL 1 JUL 932"), 932, Calendar.JULY, 1);
        // 2016 was a leap year
        assertDate(dp.parse("CAL 29 FEB 2016"), 2016, Calendar.FEBRUARY, 29);
        // 1900 was NOT a leap year, so Feb 29 of that year is parsed as Mar 1.
        assertDate(dp.parse("CAL 29 FEB 1900"), 1900, Calendar.MARCH, 1);

        assertDate(dp.parse("CAL. 17 JUL 2016"), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("CAL. 31 JUL 2016"), 2016, Calendar.JULY, 31);
        assertDate(dp.parse("CAL. 1 JUL 932"), 932, Calendar.JULY, 1);
        // 2016 was a leap year
        assertDate(dp.parse("CAL. 29 FEB 2016"), 2016, Calendar.FEBRUARY, 29);
        // 1900 was NOT a leap year, so Feb 29 of that year is parsed as Mar 1.
        assertDate(dp.parse("CAL. 29 FEB 1900"), 1900, Calendar.MARCH, 1);

        assertDate(dp.parse("EST 17 JUL 2016"), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("EST 1 JUL 932"), 932, Calendar.JULY, 1);
        // 2016 was a leap year
        assertDate(dp.parse("EST 29 FEB 2016"), 2016, Calendar.FEBRUARY, 29);
        // 1900 was NOT a leap year, so Feb 29 of that year is parsed as Mar 1.
        assertDate(dp.parse("EST 29 FEB 1900"), 1900, Calendar.MARCH, 1);

        assertDate(dp.parse("est. 17 JUL 2016"), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("est. 1 JUL 932"), 932, Calendar.JULY, 1);
        // 2016 was a leap year
        assertDate(dp.parse("est. 29 FEB 2016"), 2016, Calendar.FEBRUARY, 29);
        // 1900 was NOT a leap year, so Feb 29 of that year is parsed as Mar 1.
        assertDate(dp.parse("est. 29 FEB 1900"), 1900, Calendar.MARCH, 1);

    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseApproximateSingleDateNoDayPreferEarliest() {
        assertDate(dp.parse("ABT JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("Abt. JUL 932", ImpreciseDatePreference.FAVOR_EARLIEST), 932, Calendar.JULY, 1);
        assertDate(dp.parse("Appx. JUL 932", ImpreciseDatePreference.FAVOR_EARLIEST), 932, Calendar.JULY, 1);
        assertDate(dp.parse("Approx. JUL 932", ImpreciseDatePreference.FAVOR_EARLIEST), 932, Calendar.JULY, 1);
        assertDate(dp.parse("About JUL 932", ImpreciseDatePreference.FAVOR_EARLIEST), 932, Calendar.JULY, 1);
        // 2016 was a leap year
        assertDate(dp.parse("Est. FEB 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.FEBRUARY, 1);
        // 1900 was NOT a leap year, despite being divisible by 4 - only ever four centuries is a leap year. So Feb 28 would be last
        // day in Feb.
        assertDate(dp.parse("cal feb 1900", ImpreciseDatePreference.FAVOR_EARLIEST), 1900, Calendar.FEBRUARY, 1);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseApproximateSingleDateNoDayPreferLatest() {
        assertDate(dp.parse("About JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 31);
        assertDate(dp.parse("Appx jul 932", ImpreciseDatePreference.FAVOR_LATEST), 932, Calendar.JULY, 31);
        // 2016 was a leap year
        assertDate(dp.parse("Cal Feb 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.FEBRUARY, 29);
        // 1900 was NOT a leap year, despite being divisible by 4 - only ever four centuries is a leap year. So Feb 28 would be last
        // day in Feb.
        assertDate(dp.parse("Est. feb 1900", ImpreciseDatePreference.FAVOR_LATEST), 1900, Calendar.FEBRUARY, 28);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseApproximateSingleDateNoDayPreferMidpoint() {
        assertDate(dp.parse("Appx JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 15);
        assertDate(dp.parse("APPROX JUL 932", ImpreciseDatePreference.FAVOR_MIDPOINT), 932, Calendar.JULY, 15);
        // 2016 was a leap year
        assertDate(dp.parse("ABT FEB 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.FEBRUARY, 14);
        // 1900 was NOT a leap year, despite being divisible by 4 - only ever four centuries is a leap year. So Feb 28 would be last
        // day in Feb.
        assertDate(dp.parse("CALC FEB 1900", ImpreciseDatePreference.FAVOR_MIDPOINT), 1900, Calendar.FEBRUARY, 14);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseApproximateSingleDateNoDayPreferPRECISE() {
        assertDate(dp.parse("ABT JUL 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("Abt. JUL 932", ImpreciseDatePreference.PRECISE), 932, Calendar.JULY, 1);
        assertDate(dp.parse("Appx. JUL 932", ImpreciseDatePreference.PRECISE), 932, Calendar.JULY, 1);
        assertDate(dp.parse("Approx. JUL 932", ImpreciseDatePreference.PRECISE), 932, Calendar.JULY, 1);
        assertDate(dp.parse("About JUL 932", ImpreciseDatePreference.PRECISE), 932, Calendar.JULY, 1);
        // 2016 was a leap year
        assertDate(dp.parse("Est. FEB 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.FEBRUARY, 1);
        // 1900 was NOT a leap year, despite being divisible by 4 - only ever four centuries is a leap year. So Feb 28 would be last
        // day in Feb.
        assertDate(dp.parse("cal feb 1900", ImpreciseDatePreference.PRECISE), 1900, Calendar.FEBRUARY, 1);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseApproximateSingleDateYearOnlyPrecise() {
        assertDate(dp.parse("abt 2016"), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("Appx 932"), 932, Calendar.JANUARY, 1);
        assertDate(dp.parse("Est 2016"), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("Calc 1900"), 1900, Calendar.JANUARY, 1);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseApproximateSingleDateYearOnlyPreferEarliest() {
        assertDate(dp.parse("Calc 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("Est 932", ImpreciseDatePreference.FAVOR_EARLIEST), 932, Calendar.JANUARY, 1);
        assertDate(dp.parse("appx 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("Approx 1900", ImpreciseDatePreference.FAVOR_EARLIEST), 1900, Calendar.JANUARY, 1);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseApproximateSingleDateYearOnlyPreferLatest() {
        assertDate(dp.parse("About 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.DECEMBER, 31);
        assertDate(dp.parse("Calc. 932", ImpreciseDatePreference.FAVOR_LATEST), 932, Calendar.DECEMBER, 31);
        assertDate(dp.parse("appx 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.DECEMBER, 31);
        assertDate(dp.parse("Approx. 1900", ImpreciseDatePreference.FAVOR_LATEST), 1900, Calendar.DECEMBER, 31);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseApproximateSingleDateYearOnlyPreferMidpoint() {
        assertDate(dp.parse("Est. 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("Est 932", ImpreciseDatePreference.FAVOR_MIDPOINT), 932, Calendar.JULY, 1);
        assertDate(dp.parse("est 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("EST 1900", ImpreciseDatePreference.FAVOR_MIDPOINT), 1900, Calendar.JULY, 1);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseInterpretedDates() {
        assertDate(dp.parse("INT 17 JUL 2016 (\"today\")"), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("INT 31 JUL 2016 (\"today\")"), 2016, Calendar.JULY, 31);
        assertDate(dp.parse("INT 17 JUL 932 (\"today\")"), 932, Calendar.JULY, 17);
        assertDate(dp.parse("INT FEB 2016 (\"today\")"), 2016, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("INT 1900 (\"today\")"), 1900, Calendar.JANUARY, 1);

        assertDate(dp.parse("Int. JUL 2016 (\"today\")"), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("Int. JUL 932 (\"today\")"), 932, Calendar.JULY, 1);
        assertDate(dp.parse("Int. FEB 2016 (\"today\")"), 2016, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("Int. 1900 (\"today\")"), 1900, Calendar.JANUARY, 1);

        assertDate(dp.parse("Int. JUL 2016 (\"today\")", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("Int. JUL 932 (\"today\")", ImpreciseDatePreference.FAVOR_EARLIEST), 932, Calendar.JULY, 1);
        assertDate(dp.parse("Int. FEB 2016 (\"today\")", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("Int. 1900 (\"today\")", ImpreciseDatePreference.FAVOR_EARLIEST), 1900, Calendar.JANUARY, 1);

        assertDate(dp.parse("Int. JUL 2016 (\"today\")", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 31);
        assertDate(dp.parse("Int. JUL 932 (\"today\")", ImpreciseDatePreference.FAVOR_LATEST), 932, Calendar.JULY, 31);
        // 2016 was a leap year
        assertDate(dp.parse("Int. FEB 2016 (\"today\")", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.FEBRUARY, 29);
        assertDate(dp.parse("Int. 1900 (\"today\")", ImpreciseDatePreference.FAVOR_LATEST), 1900, Calendar.DECEMBER, 31);

        assertDate(dp.parse("Int. JUL 2016 (\"today\")", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 15);
        assertDate(dp.parse("Int. JUL 932 (\"today\")", ImpreciseDatePreference.FAVOR_MIDPOINT), 932, Calendar.JULY, 15);
        assertDate(dp.parse("Int. FEB 2016 (\"today\")", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.FEBRUARY, 14);
        assertDate(dp.parse("Int. 1900 (\"today\")", ImpreciseDatePreference.FAVOR_MIDPOINT), 1900, Calendar.JULY, 1);
    }

    /**
     * Test parsing a single date with month, day, and year
     */
    @Test
    public void testParseSingleDateFull() {
        assertDate(dp.parse("17 JUL 2016"), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("31 DEC 2016"), 2016, Calendar.DECEMBER, 31);
        assertDate(dp.parse("1 JUL 932"), 932, Calendar.JULY, 1);
        // 2016 was a leap year
        assertDate(dp.parse("29 FEB 2016"), 2016, Calendar.FEBRUARY, 29);
        // 1900 was NOT a leap year, so Feb 29 of that year is parsed as Mar 1.
        assertDate(dp.parse("29 FEB 1900"), 1900, Calendar.MARCH, 1);
        assertDate(dp.parse("29 feb 1900"), 1900, Calendar.MARCH, 1);
        assertDate(dp.parse("29 Feb 1900"), 1900, Calendar.MARCH, 1);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseSingleDateNoDayPrecise() {
        assertDate(dp.parse("JUL 2016"), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("JUL 932"), 932, Calendar.JULY, 1);
        // 2016 was a leap year
        assertDate(dp.parse("FEB 2016"), 2016, Calendar.FEBRUARY, 1);
        // 1900 was NOT a leap year, so Feb 29 of that year is parsed as Mar 1.
        assertDate(dp.parse("FEB 1900"), 1900, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("feb 1900"), 1900, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("Feb 1900"), 1900, Calendar.FEBRUARY, 1);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseSingleDateNoDayPreferEarliest() {
        assertDate(dp.parse("JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("JUL 932", ImpreciseDatePreference.FAVOR_EARLIEST), 932, Calendar.JULY, 1);
        // 2016 was a leap year
        assertDate(dp.parse("FEB 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.FEBRUARY, 1);
        // 1900 was NOT a leap year, despite being divisible by 4 - only ever four centuries is a leap year. So Feb 28 would be last
        // day in Feb.
        assertDate(dp.parse("FEB 1900", ImpreciseDatePreference.FAVOR_EARLIEST), 1900, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("feb 1900", ImpreciseDatePreference.FAVOR_EARLIEST), 1900, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("Feb 1900", ImpreciseDatePreference.FAVOR_EARLIEST), 1900, Calendar.FEBRUARY, 1);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseSingleDateNoDayPreferLatest() {
        assertDate(dp.parse("JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 31);
        assertDate(dp.parse("JUL 932", ImpreciseDatePreference.FAVOR_LATEST), 932, Calendar.JULY, 31);
        // 2016 was a leap year
        assertDate(dp.parse("FEB 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.FEBRUARY, 29);
        // 1900 was NOT a leap year, despite being divisible by 4 - only ever four centuries is a leap year. So Feb 28 would be last
        // day in Feb.
        assertDate(dp.parse("FEB 1900", ImpreciseDatePreference.FAVOR_LATEST), 1900, Calendar.FEBRUARY, 28);
        assertDate(dp.parse("feb 1900", ImpreciseDatePreference.FAVOR_LATEST), 1900, Calendar.FEBRUARY, 28);
        assertDate(dp.parse("Feb 1900", ImpreciseDatePreference.FAVOR_LATEST), 1900, Calendar.FEBRUARY, 28);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseSingleDateNoDayPreferMidpoint() {
        assertDate(dp.parse("JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 15);
        assertDate(dp.parse("JUL 932", ImpreciseDatePreference.FAVOR_MIDPOINT), 932, Calendar.JULY, 15);
        // 2016 was a leap year
        assertDate(dp.parse("FEB 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.FEBRUARY, 14);
        // 1900 was NOT a leap year, despite being divisible by 4 - only ever four centuries is a leap year. So Feb 28 would be last
        // day in Feb.
        assertDate(dp.parse("FEB 1900", ImpreciseDatePreference.FAVOR_MIDPOINT), 1900, Calendar.FEBRUARY, 14);
        assertDate(dp.parse("feb 1900", ImpreciseDatePreference.FAVOR_MIDPOINT), 1900, Calendar.FEBRUARY, 14);
        assertDate(dp.parse("Feb 1900", ImpreciseDatePreference.FAVOR_MIDPOINT), 1900, Calendar.FEBRUARY, 14);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseSingleDateYearOnlyPrecise() {
        assertDate(dp.parse("2016"), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("932"), 932, Calendar.JANUARY, 1);
        assertDate(dp.parse("2016"), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("1900"), 1900, Calendar.JANUARY, 1);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseSingleDateYearOnlyPreferEarliest() {
        assertDate(dp.parse("2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("932", ImpreciseDatePreference.FAVOR_EARLIEST), 932, Calendar.JANUARY, 1);
        assertDate(dp.parse("2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("1900", ImpreciseDatePreference.FAVOR_EARLIEST), 1900, Calendar.JANUARY, 1);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseSingleDateYearOnlyPreferLatest() {
        assertDate(dp.parse("2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.DECEMBER, 31);
        assertDate(dp.parse("932", ImpreciseDatePreference.FAVOR_LATEST), 932, Calendar.DECEMBER, 31);
        assertDate(dp.parse("2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.DECEMBER, 31);
        assertDate(dp.parse("1900", ImpreciseDatePreference.FAVOR_LATEST), 1900, Calendar.DECEMBER, 31);
    }

    /**
     * Test parsing a single date with month and year but no day, using precise preference
     */
    @Test
    public void testParseSingleDateYearOnlyPreferMidpoint() {
        assertDate(dp.parse("2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("932", ImpreciseDatePreference.FAVOR_MIDPOINT), 932, Calendar.JULY, 1);
        assertDate(dp.parse("2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("1900", ImpreciseDatePreference.FAVOR_MIDPOINT), 1900, Calendar.JULY, 1);
    }

    /**
     * Test handling for dates that are not compliant with the GEDCOM spec in their format
     */
    @Test
    public void testParseUnsupportedFormats() {
        assertNull("Slashes not supported", dp.parse("07/17/2016"));
        assertNull("Hyphens not supported", dp.parse("2016-07-17"));
        assertNull("Field order not correct", dp.parse("2016 JUL 17"));
        assertNull("No month has 41 days", dp.parse("41 JUL 2016"));
    }

    /**
     * Test pattern for two dates
     */
    @Test
    public void testPatternTwoDates() {
        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("FROM 17 JUL 1900 TO 31 DEC 2000").matches());
        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("FROM JUL 1900 TO DEC 2000").matches());
        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("FROM 1900 TO 2000").matches());

        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("BET 17 JUL 1900 AND 31 DEC 2000").matches());
        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("BET JUL 1900 AND DEC 2000").matches());
        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("BET 1900 AND 2000").matches());

        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("BTW 17 JUL 1900 AND 31 DEC 2000").matches());
        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("BTW JUL 1900 AND DEC 2000").matches());
        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("BTW 1900 AND 2000").matches());

        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("BETWEEN 17 JUL 1900 AND 31 DEC 2000").matches());
        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("BETWEEN JUL 1900 AND DEC 2000").matches());
        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("BETWEEN 1900 AND 2000").matches());

        /*
         * The next three are strictly speaking not ok - BET should not have a period, and should not be paired with AND - but we'll
         * allow it.
         */
        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("BET. 17 JUL 1900 TO 31 DEC 2000").matches());
        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("BET. JUL 1900 TO DEC 2000").matches());
        assertTrue(DateParser.PATTERN_TWO_DATES.matcher("BET. 1900 TO 2000").matches());

    }

    /**
     * Test ranges
     */
    @Test
    public void testPeriodFullDates() {
        assertDate(dp.parse("FROM 1 DEC 2017 TO 31 DEC 2017"), 2017, Calendar.DECEMBER, 1);
        assertDate(dp.parse("FROM 1 DEC 2017 TO 31 DEC 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2017, Calendar.DECEMBER, 1);
        assertDate(dp.parse("FROM 1 DEC 2017 TO 31 DEC 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.DECEMBER, 31);
        assertDate(dp.parse("FROM 1 DEC 2017 TO 31 DEC 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2017, Calendar.DECEMBER, 16);

        assertDate(dp.parse("FROM 28 DEC 2017 TO 31 DEC 2017"), 2017, Calendar.DECEMBER, 28);
        assertDate(dp.parse("FROM 28 DEC 2017 TO 31 DEC 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2017, Calendar.DECEMBER,
                28);
        assertDate(dp.parse("FROM 28 DEC 2017 TO 31 DEC 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.DECEMBER, 31);
        assertDate(dp.parse("FROM 28 DEC 2017 TO 31 DEC 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2017, Calendar.DECEMBER,
                29);

    }

    /**
     * Test ranges of dates with a variety of mixed specificity (some dates with year/month/day, some with just year/month, some
     * with just year)
     */
    @Test
    public void testPeriodMixed() {
        assertDate(dp.parse("FROM 2014 TO 1 DEC 2017"), 2014, Calendar.JANUARY, 1);
        assertDate(dp.parse("FROM 2014 TO 1 DEC 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2014, Calendar.JANUARY, 1);
        assertDate(dp.parse("FROM 2014 TO 1 DEC 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.DECEMBER, 1);
        assertDate(dp.parse("FROM 2014 TO 1 DEC 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2015, Calendar.DECEMBER, 17);

        assertDate(dp.parse("FROM 1 FEB 2014 TO 2017"), 2014, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("FROM 1 FEB 2014 TO 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2014, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("FROM 1 FEB 2014 TO 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.DECEMBER, 31);
        assertDate(dp.parse("FROM 1 FEB 2014 TO 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JANUARY, 16);

        assertDate(dp.parse("FROM 1 FEB 2014 TO OCT 2017"), 2014, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("FROM 1 FEB 2014 TO OCT 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2014, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("FROM 1 FEB 2014 TO OCT 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.OCTOBER, 31);
        assertDate(dp.parse("FROM 1 FEB 2014 TO OCT 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2015, Calendar.DECEMBER, 16);

        assertDate(dp.parse("FROM 28 FEB 2014 TO OCT 2017"), 2014, Calendar.FEBRUARY, 28);
        assertDate(dp.parse("FROM 28 FEB 2014 TO OCT 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2014, Calendar.FEBRUARY, 28);
        assertDate(dp.parse("FROM 28 FEB 2014 TO OCT 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.OCTOBER, 31);
        assertDate(dp.parse("FROM 28 FEB 2014 TO OCT 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2015, Calendar.DECEMBER, 30);
    }

    /**
     * Test ranges of month and year dates without days
     */
    @Test
    public void testPeriodMonthYear() {
        assertDate(dp.parse("FROM JAN 2017 TO DEC 2017"), 2017, Calendar.JANUARY, 1);
        assertDate(dp.parse("FROM JAN 2017 TO DEC 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2017, Calendar.JANUARY, 1);
        assertDate(dp.parse("FROM JAN 2017 TO DEC 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.DECEMBER, 31);
        assertDate(dp.parse("FROM JAN 2017 TO DEC 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2017, Calendar.JULY, 2);

        assertDate(dp.parse("FROM FEB 2014 TO OCT 2017"), 2014, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("FROM FEB 2014 TO OCT 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2014, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("FROM FEB 2014 TO OCT 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.OCTOBER, 31);
        assertDate(dp.parse("FROM FEB 2014 TO OCT 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2015, Calendar.DECEMBER, 16);
    }

    /**
     * Test periods with only one date
     */
    @Test
    public void testPeriodOpenEnded() {
        assertDate(dp.parse("FROM 17 JUL 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("FROM. 17 JUL 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("FROM JUL 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("FROM 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("TO 17 JUL 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("TO. 17 JUL 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("TO JUL 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("TO 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JANUARY, 1);

        assertDate(dp.parse("FROM 17 JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("FROM. 17 JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("FROM JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("FROM 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("TO 17 JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("TO. 17 JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("TO JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("TO 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JANUARY, 1);

        assertDate(dp.parse("FROM 17 JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("FROM. 17 JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("FROM JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 31);
        assertDate(dp.parse("FROM 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.DECEMBER, 31);
        assertDate(dp.parse("TO 17 JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("TO. 17 JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("TO JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 31);
        assertDate(dp.parse("TO 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.DECEMBER, 31);

        assertDate(dp.parse("FROM 17 JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("FROM. 17 JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("FROM JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 15);
        assertDate(dp.parse("FROM 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("TO 17 JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("TO. 17 JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("TO JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 15);
        assertDate(dp.parse("TO 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 1);
    }

    /**
     * Test ranges of dates with only years, no months or days
     */
    @Test
    public void testPeriodYearOnly() {
        assertDate(dp.parse("FROM 2014 TO 2017"), 2014, Calendar.JANUARY, 1);
        assertDate(dp.parse("FROM 2014 TO 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2014, Calendar.JANUARY, 1);
        assertDate(dp.parse("FROM 2014 TO 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.DECEMBER, 31);
        assertDate(dp.parse("FROM 2014 TO 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("FROM 2013 TO 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2015, Calendar.JULY, 2);
    }

    /**
     * Test ranges
     */
    @Test
    public void testRangeFullDates() {
        assertDate(dp.parse("BET 1 DEC 2017 AND 31 DEC 2017"), 2017, Calendar.DECEMBER, 1);
        assertDate(dp.parse("BET 1 DEC 2017 AND 31 DEC 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2017, Calendar.DECEMBER, 1);
        assertDate(dp.parse("BET 1 DEC 2017 AND 31 DEC 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.DECEMBER, 31);
        assertDate(dp.parse("BET 1 DEC 2017 AND 31 DEC 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2017, Calendar.DECEMBER, 16);

        assertDate(dp.parse("BET 28 DEC 2017 AND 31 DEC 2017"), 2017, Calendar.DECEMBER, 28);
        assertDate(dp.parse("BET 28 DEC 2017 AND 31 DEC 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2017, Calendar.DECEMBER,
                28);
        assertDate(dp.parse("BET 28 DEC 2017 AND 31 DEC 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.DECEMBER, 31);
        assertDate(dp.parse("BET 28 DEC 2017 AND 31 DEC 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2017, Calendar.DECEMBER,
                29);

    }

    /**
     * Test ranges of dates with a variety of mixed specificity (some dates with year/month/day, some with just year/month, some
     * with just year)
     */
    @Test
    public void testRangeMixed() {
        assertDate(dp.parse("BET 2014 AND 1 DEC 2017"), 2014, Calendar.JANUARY, 1);
        assertDate(dp.parse("BET 2014 AND 1 DEC 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2014, Calendar.JANUARY, 1);
        assertDate(dp.parse("BET 2014 AND 1 DEC 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.DECEMBER, 1);
        assertDate(dp.parse("BET 2014 AND 1 DEC 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2015, Calendar.DECEMBER, 17);

        assertDate(dp.parse("BET 1 FEB 2014 AND 2017"), 2014, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("BET 1 FEB 2014 AND 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2014, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("BET 1 FEB 2014 AND 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.DECEMBER, 31);
        assertDate(dp.parse("BET 1 FEB 2014 AND 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JANUARY, 16);

        assertDate(dp.parse("BET 1 FEB 2014 AND OCT 2017"), 2014, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("BET 1 FEB 2014 AND OCT 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2014, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("BET 1 FEB 2014 AND OCT 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.OCTOBER, 31);
        assertDate(dp.parse("BET 1 FEB 2014 AND OCT 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2015, Calendar.DECEMBER, 16);

        assertDate(dp.parse("BET 28 FEB 2014 AND OCT 2017"), 2014, Calendar.FEBRUARY, 28);
        assertDate(dp.parse("BET 28 FEB 2014 AND OCT 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2014, Calendar.FEBRUARY, 28);
        assertDate(dp.parse("BET 28 FEB 2014 AND OCT 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.OCTOBER, 31);
        assertDate(dp.parse("BET 28 FEB 2014 AND OCT 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2015, Calendar.DECEMBER, 30);
    }

    /**
     * Test ranges of month and year dates without days
     */
    @Test
    public void testRangeMonthYear() {
        assertDate(dp.parse("BTW JAN 2017 AND DEC 2017"), 2017, Calendar.JANUARY, 1);
        assertDate(dp.parse("BTW JAN 2017 AND DEC 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2017, Calendar.JANUARY, 1);
        assertDate(dp.parse("BTW JAN 2017 AND DEC 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.DECEMBER, 31);
        assertDate(dp.parse("BTW JAN 2017 AND DEC 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2017, Calendar.JULY, 2);

        assertDate(dp.parse("BTW FEB 2014 AND OCT 2017"), 2014, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("BTW FEB 2014 AND OCT 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2014, Calendar.FEBRUARY, 1);
        assertDate(dp.parse("BTW FEB 2014 AND OCT 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.OCTOBER, 31);
        assertDate(dp.parse("BTW FEB 2014 AND OCT 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2015, Calendar.DECEMBER, 16);
    }

    /**
     * Test ranges with only one date
     */
    @Test
    public void testRangeOpenEnded() {
        assertDate(dp.parse("BEF 17 JUL 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("BEF. 17 JUL 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("BEFORE JUL 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("BEFORE 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("AFT 17 JUL 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("AFT. 17 JUL 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("AFTER JUL 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("AFTER 2016", ImpreciseDatePreference.PRECISE), 2016, Calendar.JANUARY, 1);

        assertDate(dp.parse("BEF 17 JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("BEF. 17 JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("BEFORE JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("BEFORE 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("AFT 17 JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("AFT. 17 JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("AFTER JUL 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("AFTER 2016", ImpreciseDatePreference.FAVOR_EARLIEST), 2016, Calendar.JANUARY, 1);

        assertDate(dp.parse("BEF 17 JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("BEF. 17 JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("BEFORE JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 31);
        assertDate(dp.parse("BEFORE 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.DECEMBER, 31);
        assertDate(dp.parse("AFT 17 JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("AFT. 17 JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("AFTER JUL 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.JULY, 31);
        assertDate(dp.parse("AFTER 2016", ImpreciseDatePreference.FAVOR_LATEST), 2016, Calendar.DECEMBER, 31);

        assertDate(dp.parse("BEF 17 JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("BEF. 17 JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("BEFORE JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 15);
        assertDate(dp.parse("BEFORE 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 1);
        assertDate(dp.parse("AFT 17 JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("AFT. 17 JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 17);
        assertDate(dp.parse("AFTER JUL 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 15);
        assertDate(dp.parse("AFTER 2016", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JULY, 1);
    }

    /**
     * Test ranges of dates with only years, no months or days
     */
    @Test
    public void testRangeYearOnly() {
        assertDate(dp.parse("BET 2014 AND 2017"), 2014, Calendar.JANUARY, 1);
        assertDate(dp.parse("BET 2014 AND 2017", ImpreciseDatePreference.FAVOR_EARLIEST), 2014, Calendar.JANUARY, 1);
        assertDate(dp.parse("BET 2014 AND 2017", ImpreciseDatePreference.FAVOR_LATEST), 2017, Calendar.DECEMBER, 31);
        assertDate(dp.parse("BET 2014 AND 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2016, Calendar.JANUARY, 1);
        assertDate(dp.parse("BET 2013 AND 2017", ImpreciseDatePreference.FAVOR_MIDPOINT), 2015, Calendar.JULY, 2);
    }

    /**
     * Test {@link DateParser#removeApproximations(String)}
     */
    @Test
    public void testRemoveAppoximations() {
        assertEquals("31 DEC 1900", dp.removeApproximations("31 DEC 1900"));
        assertEquals("31 DEC 1900", dp.removeApproximations("APPX 31 DEC 1900"));
        assertEquals("31 DEC 1900", dp.removeApproximations("APPROX 31 DEC 1900"));
        assertEquals("31 DEC 1900", dp.removeApproximations("APPX. 31 DEC 1900"));
        assertEquals("31 DEC 1900", dp.removeApproximations("APPROX. 31 DEC 1900"));
        assertEquals("31 DEC 1900", dp.removeApproximations("ABT 31 DEC 1900"));
        assertEquals("31 DEC 1900", dp.removeApproximations("ABT. 31 DEC 1900"));
        assertEquals("31 DEC 1900", dp.removeApproximations("ABOUT 31 DEC 1900"));
        assertEquals("31 DEC 1900", dp.removeApproximations("EST 31 DEC 1900"));
        assertEquals("31 DEC 1900", dp.removeApproximations("EST. 31 DEC 1900"));
    }

    /**
     * Test {@link DateParser#removePrefixes(String, String[])}
     */
    @Test
    public void testRemovePrefixes() {
        assertEquals("FOO", dp.removePrefixes("FOO", new String[] {}));
        assertEquals("FOO BAR BAZ BAT", dp.removePrefixes("FOO BAR BAZ BAT", new String[] {}));
        assertEquals("BAR BAZ BAT", dp.removePrefixes("FOO BAR BAZ BAT", new String[] { "FOO" }));
        assertEquals("BAR BAZ BAT", dp.removePrefixes("FOO. BAR BAZ BAT", new String[] { "FOO" }));
        assertEquals("BAR BAZ BAT", dp.removePrefixes("FOO BAR BAZ BAT", new String[] { "BAR", "BAZ", "BAT", "FOO" }));
        assertEquals("BAR BAZ BAT", dp.removePrefixes("FOO. BAR BAZ BAT", new String[] { "BAR", "BAZ", "BAT", "FOO" }));
    }

    /**
     * Test for {@link DateParser#splitTwoDateString(String, String)}
     */
    @Test
    public void testSplitTwoDateString() {
        assertArrayEquals(new String[] { "FOO", "BAR" }, dp.splitTwoDateString("FROM FOO TO BAR", " TO "));
        assertArrayEquals(new String[] { "1 JAN 2016", "31 DEC 2016" }, dp.splitTwoDateString("FROM 1 JAN 2016 TO 31 DEC 2016",
                " TO "));
        assertArrayEquals(new String[] { "1 JAN 2016", "31 DEC 2016" }, dp.splitTwoDateString("BTW 1 JAN 2016 AND 31 DEC 2016",
                " AND "));
        assertArrayEquals(new String[] { "JAN 2016", "31 DEC 2016" }, dp.splitTwoDateString("BTW JAN 2016 AND 31 DEC 2016",
                " AND "));
        assertArrayEquals(new String[] { "2016", "DEC 2016" }, dp.splitTwoDateString("BTW 2016 AND DEC 2016", " AND "));
        assertArrayEquals(new String[] { "1 JAN 2016", "31 DEC 2016" }, dp.splitTwoDateString("BTW. 1 JAN 2016 AND 31 DEC 2016",
                " AND "));
        assertArrayEquals(new String[] { "JAN 2016", "31 DEC 2016" }, dp.splitTwoDateString("BTW. JAN 2016 AND 31 DEC 2016",
                " AND "));
        assertArrayEquals(new String[] { "2016", "DEC 2016" }, dp.splitTwoDateString("BTW. 2016 AND DEC 2016", " AND "));
        assertArrayEquals(new String[] { "1 JAN 2016", "31 DEC 2016" }, dp.splitTwoDateString("BET 1 JAN 2016 AND 31 DEC 2016",
                " AND "));
        assertArrayEquals(new String[] { "JAN 2016", "31 DEC 2016" }, dp.splitTwoDateString("BET JAN 2016 AND 31 DEC 2016",
                " AND "));
        assertArrayEquals(new String[] { "2016", "DEC 2016" }, dp.splitTwoDateString("BET 2016 AND DEC 2016", " AND "));
        assertArrayEquals(new String[] { "1 JAN 2016", "31 DEC 2016" }, dp.splitTwoDateString("BET. 1 JAN 2016 AND 31 DEC 2016",
                " AND "));
        assertArrayEquals(new String[] { "JAN 2016", "31 DEC 2016" }, dp.splitTwoDateString("BET. JAN 2016 AND 31 DEC 2016",
                " AND "));
        assertArrayEquals(new String[] { "2016", "DEC 2016" }, dp.splitTwoDateString("BET. 2016 AND DEC 2016", " AND "));
    }

    /**
     * Assert that a given date has the expected year, month, and day values
     * 
     * @param d
     *            the date to test
     * @param year
     *            the expected year
     * @param month
     *            the expected month
     * @param day
     *            the expected day
     */
    private void assertDate(Date d, int year, int month, int day) {
        assertNotNull("Expected date of " + (month + 1) + "/" + day + "/" + year + " but got null", d);
        Calendar c = Calendar.getInstance(Locale.US);
        c.setTime(d);
        int y = c.get(Calendar.YEAR);
        assertEquals("Expected year of " + year + " but found " + y + " in date " + d, year, y);
        int m = c.get(Calendar.MONTH);
        assertEquals("Expected month of " + month + " but found " + m + " in date " + d, month, m);
        int dy = c.get(Calendar.DAY_OF_MONTH);
        assertEquals("Expected day of " + day + " but found " + dy + " in date " + d, day, dy);
        assertEquals("Expected no hour in date " + d, 0, c.get(Calendar.HOUR_OF_DAY));
        assertEquals("Expected no minutes in date " + d, 0, c.get(Calendar.MINUTE));
        assertEquals("Expected no seconds in date " + d, 0, c.get(Calendar.SECOND));
        assertEquals("Expected no milliseconds in date " + d, 0, c.get(Calendar.MILLISECOND));
    }

}
