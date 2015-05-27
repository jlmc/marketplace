package org.xine.marketplace.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * The Class DateUtils, contains a lot of common methods considering convert {@code java.util.Date} to
 * the {@code java.time.*} types from JDK 1.8.
 */
public final class DateUtils {

    /**
     * Instantiates a new date utils.
     */
    private DateUtils() {
    }

    /**
     * As date, convert a LocalDate to Date.
     * @param localDate
     *            the local date
     * @return the date
     */
    public static Date asDate(final LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * As local date, converte Date to LocalDate.
     * @param date
     *            the date
     * @return the local date
     */
    public static LocalDate asLocalDate(final Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * As date, Convert a LocalDateTime to Date.
     * @param localDateTime
     *            the local date time
     * @return the date
     */
    public static Date asDate(final LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * As local date time, convert a Date to LocalDateTime.
     * @param date
     *            the date
     * @return the local date time
     */
    public static LocalDateTime asLocalDateTime(final Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
