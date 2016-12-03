package zeroone3010.mediawiki.spreadsheetredirector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class MediaWikiPageCreator {

    List<Page> convertDataToPages(final Redirections redirections) {
        final List<Page> results = new ArrayList<>();
        for (final Map.Entry<String, String> redirection : redirections.entrySet()) {
            final String title = redirection.getKey();
            final String content = createRedirect(redirection.getValue());
            results.add(new Page(title, content));
        }
        return results;
    }

    private String createRedirect(final String target) {
        return String.format("#REDIRECT [[%s]]", target);
    }
}
