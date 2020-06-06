package util;

import model.Post;
import org.springframework.data.domain.Page;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostUtil {
    public static Page<Post> summaryPost(Page<Post> postList) {
        for (Post post : postList) {
            post.setContent(truncateAfterWords(36, post.getContent()));
        }
        return postList;
    }

    private static String truncateAfterWords(int numberOfWords, String string) {
        final Pattern WB_PATTERN = Pattern.compile("(?<=\\w)\\b");
        if (string == null) return null;
        if (numberOfWords <= 0) return "";
        Matcher m = WB_PATTERN.matcher(string);
        for (int i = 0; i < numberOfWords && m.find(); i++) ;
        if (m.hitEnd())
            return string + " ... ";
        else
            return string.substring(0, m.end()) + " ... ";
    }
}
