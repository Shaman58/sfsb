package ru.erp.sfsb.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.Duration;

public class DurationDeserializer extends JsonDeserializer<Duration> {
    @Override
    public Duration deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        String durationString = node.textValue();

        String[] parts = durationString.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid duration format: " + durationString);
        }

        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        long totalSeconds = hours * 3600L + minutes * 60L;
        return Duration.ofSeconds(totalSeconds);
    }
}