package formatter;

import org.springframework.format.Formatter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeStampFormatter implements Formatter<Timestamp> {
    @Override
    public Timestamp parse(String text, Locale locale) {
        return null;
    }

    @Override
    public String print(Timestamp object, Locale locale) {
        return object.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss", locale));
    }
}
