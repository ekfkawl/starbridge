package kr.starbridge.web.global.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    public static String getBeforeDiffToString(LocalDateTime dt) {
        long m = ChronoUnit.MINUTES.between(dt, LocalDateTime.now());
        long h = ChronoUnit.HOURS.between(dt, LocalDateTime.now());
        long d = ChronoUnit.DAYS.between(dt, LocalDateTime.now());

        if (m == 0) {
            return "방금";
        }
        if (m < 60) {
            return String.format("%d분 전", m);
        }
        if (m < 1440) {
            return String.format("%d시간 전", h);
        }
        return String.format("%d일 전", d);
    }
}
