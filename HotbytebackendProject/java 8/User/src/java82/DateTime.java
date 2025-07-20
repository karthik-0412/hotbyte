package java82;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    public static void main(String[] args) {
        // LocalDate
        LocalDate date = LocalDate.now();
        System.out.println("LocalDate: " + date);

        // LocalTime
        LocalTime time = LocalTime.now();
        System.out.println("LocalTime: " + time);

        // LocalDateTime
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("LocalDateTime: " + dateTime);

        // ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("ZonedDateTime: " + zonedDateTime);

        // Instant
        Instant instant = Instant.now();
        System.out.println("Instant: " + instant);

        // Duration
        Duration duration = Duration.ofMinutes(30);
        System.out.println("Duration: " + duration);

        // Period
        Period period = Period.ofYears(2).plusMonths(3).plusDays(4);
        System.out.println("Period: " + period);

        // DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        System.out.println("Formatted DateTime: " + formattedDateTime);
        
        // Time Zones
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime nyTime = ZonedDateTime.now(zone);
        System.out.println("New York Time: " + nyTime);
    }
}
