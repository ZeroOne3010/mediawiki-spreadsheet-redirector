package zeroone3010.mediawiki.spreadsheetredirector;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExcelToRedirectionDataParserTest {
    @Test
    public void parseExcelFile_should_return_correct_result() {
        final ClassLoader classLoader = getClass().getClassLoader();
        final File file = new File(classLoader.getResource("redirections.xls").getFile());
        final Redirections result = new ExcelToRedirectionDataParser().parseExcelFile(file);

        assertThat(result.size(), is(3));

        assertThat(result.get("Daenerys"), is("Daenerys Targaryen"));
        assertThat(result.get("Drogo"), is("Khal Drogo"));
        assertThat(result.get("Tyrion"), is("Tyrion Lannister"));
    }
}
