package util;

import model.Post;
import org.springframework.data.domain.Page;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostUtil {
    public static Page<Post> summaryPost(Page<Post> postList, int numberOfWords) {
        for (Post post : postList) {
            post.setContent(truncateAfterWords(numberOfWords, post.getContent()));
        }
        return postList;
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
}
