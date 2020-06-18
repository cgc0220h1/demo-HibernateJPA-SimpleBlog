package app.blog.util;

import app.blog.model.Post;
import org.jsoup.Jsoup;
import org.springframework.data.domain.Page;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostUtil {

    public static void summaryPost(Page<Post> postList, int numberOfWords) {
        for (Post post : postList) {
            String plaintText = toPlainText(post.getContent());
            post.setContent(truncateAfterWords(numberOfWords, plaintText));
        }
    }

    private static String truncateAfterWords(int numberOfWords, String string) {
        final Pattern WB_PATTERN = Pattern.compile("(?<=\\w)\\b");
        final String END_STRING = " ... ";
        if (numberOfWords <= 0 || string == null) {
            return "";
        }
        Matcher m = WB_PATTERN.matcher(string);
        for (int i = 0; i < numberOfWords && m.find(); i++) {
            if (m.hitEnd()) {
                return string + END_STRING;
            }
        }
        return string.substring(0, m.end()) + END_STRING;
    }

    public static String toPlainText(String html) {
        return Jsoup.parse(html).text();
    }
}
