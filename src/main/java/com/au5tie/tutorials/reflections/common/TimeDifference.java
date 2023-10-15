package com.au5tie.tutorials.reflections.common;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
public class TimeDifference {

    private LocalDateTime start;
    private LocalDateTime end;

    private long years;
    private long months;
    private long days;
    private long hours;
    private long minutes;
    private long seconds;
    private long milliseconds;

    public TimeDifference(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
        calculateTimeDifference();
    }

    private void calculateTimeDifference() {
        LocalDateTime tempDateTime = LocalDateTime.from(start);

        years = tempDateTime.until(end, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        months = tempDateTime.until(end, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        days = tempDateTime.until(end, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);

        hours = tempDateTime.until(end, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        minutes = tempDateTime.until(end, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        seconds = tempDateTime.until(end, ChronoUnit.SECONDS);
        tempDateTime = tempDateTime.plusSeconds(seconds);

        milliseconds = tempDateTime.until(end, ChronoUnit.MILLIS);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (years > 0) {
            stringBuilder.append(years + " years ");
        }

        if (months > 0) {
            stringBuilder.append(months + " months ");
        }

        if (days > 0) {
            stringBuilder.append(days + " days ");
        }

        if (minutes > 0) {
            stringBuilder.append(minutes + " minutes ");
        }

        if (seconds > 0) {
            stringBuilder.append(seconds + " seconds ");
        }

        stringBuilder.append(milliseconds + " ms");

        return stringBuilder.toString();
    }
}