package ru.erp.sfsb.utils;

import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class DurationRuCustomFormatter {

    public String getRusTimeFormat(Duration duration) {
        return String.format("%dч. %dм.", duration.toMinutes() / 60, duration.toMinutes() % 60);
    }
}