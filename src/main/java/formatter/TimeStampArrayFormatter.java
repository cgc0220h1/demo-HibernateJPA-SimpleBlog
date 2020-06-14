package formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.*;
import java.util.Locale;

@Component
public class TimeStampArrayFormatter implements Formatter<Timestamp[]> {

    @Override
    public Timestamp[] parse(String text, Locale locale) {
        String[] data = text.split("-");
        LocalDateTime startDate;
        LocalDateTime endDate;
        if (data.length == 1) {
            Year year = Year.of(Integer.parseInt(text));
            startDate = LocalDateTime.of(
                    LocalDate.of(year.getValue(), 1, 1),
                    LocalTime.MIDNIGHT);
            endDate = LocalDateTime.of(
                    LocalDate.of(year.getValue(), 12, 31),
                    LocalTime.MIDNIGHT);
        } else {
            YearMonth yearMonth = YearMonth.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
            startDate = LocalDateTime.of(
                    LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1),
                    LocalTime.MIDNIGHT);
            endDate = LocalDateTime.of(
                    LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), yearMonth.lengthOfMonth()),
                    LocalTime.MIDNIGHT);
        }
        return new Timestamp[]{Timestamp.valueOf(startDate), Timestamp.valueOf(endDate)};
    }

    @Override
    public String print(Timestamp[] object, Locale locale) {
        return null;
    }
}
