package ru.erp.sfsb.utils;

import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class DurationRuCustomFormatter {

    public String getRusTimeFormat(Duration duration) {
        return String.format("%dч. %dм.", duration.toMinutes() / 60, duration.toMinutes() % 60);
    }

    public Duration getDurationFromString(String string) {
        var strings = string.split(" ");
        var hours = Duration.ofHours(Integer.parseInt(strings[0].split("ч.")[0]));
        var minutes = Duration.ofMinutes(Integer.parseInt(strings[1].split("м.")[0]));
        return hours.plus(minutes);
    }
}