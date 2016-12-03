package zeroone3010.mediawiki.spreadsheetredirector;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

class ExcelToRedirectionDataParser {
    public Redirections parseExcelFile(final File file) {
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(file);
        } catch (BiffException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        final Sheet sheet = workbook.getSheet(0);
        return readRows(sheet);
    }

    private Redirections readRows(final Sheet sheet) {
        final Redirections redirections = new Redirections();
        for (int row = 1; row < sheet.getRows(); row++) {
            redirections.put(sheet.getRow(row)[0].getContents(), sheet.getRow(row)[1].getContents());
        }
        return redirections;
    }
}
