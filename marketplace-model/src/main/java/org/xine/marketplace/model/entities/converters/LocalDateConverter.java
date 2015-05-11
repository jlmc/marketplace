package org.xine.marketplace.model.entities.converters;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * The Class LocalDateConverter.
 * the property must be marked with {@code  @Convert(converter=LocalDateConverter.class)}
 */
@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, java.util.Date> {

    @Override
    public Date convertToDatabaseColumn(final LocalDate attribute) {
        final Instant instant = attribute.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    @Override
    public LocalDate convertToEntityAttribute(final Date dbData) {
        final Instant instant = Instant.ofEpochMilli(dbData.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }

}
