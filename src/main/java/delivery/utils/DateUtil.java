package delivery.utils;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static LocalDateTime convertToLocalDateTime(String date, String format) {
        if (StringUtils.hasText(format)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(date, formatter);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);
        return LocalDateTime.parse(date, formatter);
    }

    public static int getDiffDays(LocalDateTime startDate, LocalDateTime endDate) {
        Period diff = Period.between(startDate.toLocalDate(), endDate.toLocalDate());
        return diff.getDays();
    }
}
