package Main;

import org.apache.poi.ss.usermodel.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class WorkbookReader {

    private Workbook currentWorkbook;
    private Sheet currentSheet;

    public WorkbookReader(Workbook workbook, int i) {

        currentWorkbook = workbook;
        currentSheet = workbook.getSheetAt(i);
    }


    public ArrayList<NumberSet> getNumberSetList() {

        DataFormatter dataFormatter = new DataFormatter();
        DateConverter dateConverter = new DateConverter();
        ArrayList<NumberSet> numberSetList = new ArrayList<>();
        Iterator<Row> rowIterator = currentSheet.rowIterator();

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
                        //Die erste Zelle mit Datum wird in das NumberSet geschrieben.
                        LocalDate localDate = dateConverter.formatDate(cellValue);
                        currentNumberSet.setDate(localDate);

                        //System.out.println(currentNumberSet.getDate());


                        counter++;

                    } else if (cellValue != "") {
                        //Zellen mit Values werden in das NumberSet geschrieben.
                        String floatString = dataFormatter.formatCellValue(currentCell);
                        floatString = floatString.replace(',', '.');
                        float input;

                        try {
                            input = Float.parseFloat(floatString);
                        } catch (NumberFormatException e) {
                            input = 0.0f;
                        }

                        currentNumberSet.setValues(input);

                        //System.out.println("Value of " + currentNumberSet.getValues(counter - 1));

                        counter++;
                    }
                }
                numberSetList.add(currentNumberSet);
            }
            rowCounter++;
        }
        System.out.println("WorkbookReader: numberSetList has been created.");
        return numberSetList;
    }
}
