package ru.erp.sfsb.deserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DurationSerializer extends JsonSerializer<Duration> {
    @Override
    public void serialize(Duration duration, JsonGenerator generator, SerializerProvider provider) throws IOException {
        long hours = duration.toHours();
        long minutes = duration.minus(hours, ChronoUnit.HOURS).toMinutes();
        String formattedDuration = String.format("%02d:%02d", hours, minutes);
        generator.writeString(formattedDuration);
    }
}