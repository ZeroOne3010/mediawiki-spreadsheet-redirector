package zeroone3010.mediawiki.spreadsheetredirector;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MediaWikiPageCreatorTest {
    @Test
    public void convertDataToPages_should_return_correct_result() {
        final Redirections redirections = new Redirections();
        redirections.put("Sansa", "Sansa Stark");
        redirections.put("Jaqen", "Jaqen H'ghar");
        final List<Page> pages = new MediaWikiPageCreator().convertDataToPages(redirections);

        assertThat(pages.size(), is(2));
        assertThat(pages.get(0).getTitle(), is("Sansa"));
        assertThat(pages.get(0).getContent(), is("#REDIRECT [[Sansa Stark]]"));

        assertThat(pages.get(1).getTitle(), is("Jaqen"));
        assertThat(pages.get(1).getContent(), is("#REDIRECT [[Jaqen H'ghar]]"));
    }
}