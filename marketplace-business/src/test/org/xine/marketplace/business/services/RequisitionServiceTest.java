package org.xine.marketplace.business.services;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@SuppressWarnings("static-method")
public class RequisitionServiceTest {

    @Test
    public void test() {
        final LocalDateTime dt = LocalDateTime.now();

        final ZonedDateTime zdt = dt.atZone(ZoneId.systemDefault());
        final Date date = Date.from(zdt.toInstant());

        System.out.println(date);
    }

    @Test
    public void test2() {
        // final LocalDateTime dt = LocalDateTime.now();

        // final final ZonedDateTime zdt = dt.atZone(ZoneId.systemDefault());
        final Date date = Date.from(Instant.now());

        System.out.println(date);
    }
}
