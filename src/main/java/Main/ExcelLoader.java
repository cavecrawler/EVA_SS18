package Main;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

public class ExcelLoader {

    Workbook workbook;
    Sheet sheet;
    String XLS_Filepath;

    public ExcelLoader(String XLS_Filepath) {
        this.XLS_Filepath = XLS_Filepath;
    }

    public Workbook getWorkbook() throws IOException, InvalidFormatException {
        workbook = WorkbookFactory.create(new File(XLS_Filepath));
        System.out.println("ExcelLoader: Workbook has been created.");

        return workbook;
    }
}
