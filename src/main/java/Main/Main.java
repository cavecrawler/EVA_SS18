package Main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException {

        String XLS_Filepath = "C:\\Users\\chrisso\\Documents\\JavaProjects\\src\\main\\resources\\Rohdaten.xlsx";

        ExcelLoader excelLoader = new ExcelLoader(XLS_Filepath);
        DataFormatter dataFormatter = new DataFormatter();
        NumberSet NS = new NumberSet();
        DateConverter dateConverter = new DateConverter();
        ArrayList<NumberSet> NumberSets = new ArrayList<>();

        //workbook holen. sheet extrahieren
        Workbook workbook = excelLoader.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);

        //zelle auswählen, inhalt formatieren zu String, umwandlung zu Datum
        /*
        System.out.println("Extracting cell value.");
        Cell cell = sheet.getRow(1).getCell(0);
        String cellValue = dataFormatter.formatCellValue(cell);

        System.out.println("Extraced CellValue: " + cellValue);
        LocalDate localDate = dateConverter.formatDate(cellValue);
        NS.setDate(localDate);

        System.out.println("Fetch associated values.");
        cell = sheet.getRow(1).getCell(2);
        String floatString = dataFormatter.formatCellValue(cell);
        floatString = floatString.replace(',','.');
        float input = Float.valueOf(floatString);
        NS.setValues(input);
        System.out.println("Date return from NumberSet: " + NS.getDate());
        System.out.println("Value return from NumberSet: " + NS.getValues(0));
        */

        Iterator<Row> rowIterator = sheet.rowIterator();

        int rowCounter = 0;
        while (rowIterator.hasNext()) {
            Row currentRow = rowIterator.next();
            if (rowCounter != 0) {
                NumberSet currentNumberSet = new NumberSet();
                Iterator<Cell> cellIterator = currentRow.cellIterator();
                int counter = 0;
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(currentCell);
                    if (cellValue != "" && counter == 0) {
                        LocalDate localDate = dateConverter.formatDate(cellValue);
                        currentNumberSet.setDate(localDate);
                        System.out.println(currentNumberSet.getDate());
                        counter++;
                    } else if (cellValue != "") {
                        String floatString = dataFormatter.formatCellValue(currentCell);
                        floatString = floatString.replace(',', '.');
                        float input = Float.valueOf(floatString);
                        currentNumberSet.setValues(input);
                        System.out.println("Value of " + currentNumberSet.getValues(counter - 1));
                        counter++;
                    }
                }
            }
            rowCounter++;
        }
    }
}