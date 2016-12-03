package zeroone3010.mediawiki.spreadsheetredirector;

import java.io.File;
import java.util.List;

public class MediaWikiSpreadsheetRedirector {

    public static void main(final String[] args) {
        final MediaWikiSpreadsheetRedirector redirector = new MediaWikiSpreadsheetRedirector();

        if (propertyIsNull("username") || propertyIsNull("in") || propertyIsNull("out")) {
          System.out.println("\nUsage:\njava -Din=\"input_file.xls\" -Dout=\"output_file.xml\" -Dusername=\"Desired wiki username\" -jar name_of_the_jar_file.jar\n");
          System.exit(1);
        }
        redirector.generatePages(new File(System.getProperty("in")));
    }

    private static boolean propertyIsNull(final String username) {
        return System.getProperty(username) == null;
    }

    public void generatePages(final File file) {
        final ExcelToRedirectionDataParser parser = new ExcelToRedirectionDataParser();
        final Redirections parsedCollection = parser.parseExcelFile(file);
        final MediaWikiPageCreator creator = new MediaWikiPageCreator();
        final List<Page> pages = creator.convertDataToPages(parsedCollection);
        System.out.println("Creating " + pages.size() + " redirections...");
        final MediaWikiXmlDocument mediaWikiDocument = new MediaWikiXmlDocument(System.getProperty("username"));
        for (final Page page : pages) {
            mediaWikiDocument.addPage(page.getTitle(), page.getContent());
        }
        mediaWikiDocument.writeToFile(System.getProperty("out"));
        System.out.println("Done.");
    }

}
