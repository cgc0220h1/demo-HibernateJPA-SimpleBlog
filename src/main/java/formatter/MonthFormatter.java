package formatter;

import org.apache.commons.text.WordUtils;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

@Component
public class MonthFormatter implements Formatter<Month> {
    @Override
    public Month parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(Month month, Locale locale) {
        Locale vnLocale = new Locale("vi", "VN");
        String monthDisplay = month.getDisplayName(TextStyle.FULL, vnLocale);
        return WordUtils.capitalizeFully(monthDisplay);
    }
}
