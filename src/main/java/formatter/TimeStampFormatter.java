package formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.*;
import java.util.Arrays;
import java.util.Locale;

@Component
public class TimeStampFormatter implements Formatter<Timestamp[]> {

    @Override
    public Timestamp[] parse(String text, Locale locale) throws ParseException {
        String[] data = text.split("-");

        YearMonth yearMonth = YearMonth.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        LocalDateTime startDate = LocalDateTime.of(
                LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1),
                LocalTime.MIDNIGHT);
        LocalDateTime endDate = LocalDateTime.of(
                LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), yearMonth.lengthOfMonth()),
                LocalTime.MIDNIGHT);

        return new Timestamp[]{Timestamp.valueOf(startDate), Timestamp.valueOf(endDate)};
    }

    @Override
    public String print(Timestamp[] object, Locale locale) {
        return null;
    }
}
