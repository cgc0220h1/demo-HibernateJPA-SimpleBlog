package util;

import model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostUtil {

    public static void summaryPost(Page<Post> postList, int numberOfWords) {
        for (Post post : postList) {
            post.setContent(truncateAfterWords(numberOfWords, post.getContent()));
        }
    }

    public static String truncateAfterWords(int size, String input) {
        if (input.length() < size) return input;

        int lastTagStart = 0;
        boolean inString = false;
        boolean inTag = false;

        for (int pos = 0; pos < size; pos++) {
            switch (input.charAt(pos)) {
                case '<':
                    if (!inString && !inTag) {
                        lastTagStart = pos;
                        inTag = true;
                    }
                    break;
                case '>':
                    if (!inString) inTag = false;
                    break;
                case '\"':
                    if (inTag) inString = !inString;
                    break;
            }
        }
        if (!inTag) lastTagStart = size;
        return input.substring(0, lastTagStart);
    }
}
