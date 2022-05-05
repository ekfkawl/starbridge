package kr.starbridge.web.global.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    public static String getBeforeDiffToString(LocalDateTime dt) {
        long m = ChronoUnit.MINUTES.between(dt, LocalDateTime.now());
        long h = ChronoUnit.HOURS.between(dt, LocalDateTime.now());
        long d = ChronoUnit.DAYS.between(dt, LocalDateTime.now());
        long mth = ChronoUnit.MONTHS.between(dt, LocalDateTime.now());
        long y = ChronoUnit.YEARS.between(dt, LocalDateTime.now());

        if (m == 0) {
            return "방금";
        }
        if (m < 60) {
            return String.format("%d분 전", m);
        }
        if (h > 0 && h < 24) {
            return String.format("%d시간 전", h);
        }
        if (d > 0 && mth == 0) {
            return String.format("%d일 전", d);
        }
        if (mth > 0 && y == 0) {
            return String.format("%d달 전", mth);
        }

        return String.format("%d년 전", y);
    }
}
