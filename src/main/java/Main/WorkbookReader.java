package Main;

import org.apache.poi.ss.usermodel.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class WorkbookReader {

    private Workbook currentWorkbook;
    private Sheet currentSheet;
    private int[] sheetNumbers;

    public WorkbookReader(Workbook workbook, int[] sheetNumbers) {

        currentWorkbook = workbook;
        this.sheetNumbers = sheetNumbers;


        ;
    }

    public ArrayList<String> getValueNames(int sheetNumber) {

        ArrayList<String> valueNames = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();
        currentSheet = currentWorkbook.getSheetAt(sheetNumber); // sheetNumber Parameter legt sheet des workbooks fest
        System.out.println("WorkbookReader: Initialized. \nWorkbookReader: Reading Sheet No.: " +
                currentWorkbook.getSheetName(sheetNumber));
        Iterator<Row> rowIterator = currentSheet.rowIterator();
        Row currentRow = rowIterator.next();
        Iterator<Cell> cellIterator = currentRow.cellIterator();
        int cellCounter = 0;

        while (cellIterator.hasNext()) {

            Cell currentCell = cellIterator.next();
            String valueName = dataFormatter.formatCellValue(currentCell);

            if (cellCounter != 0 && valueName != "") {

                valueNames.add(valueName);
                System.out.println("WorkbookReader: " + valueName);
            }

            cellCounter++;
        }
        return valueNames;
    }

    public NumberSetList getNumberSetList(int sheetNumber) {

        DataFormatter dataFormatter = new DataFormatter();
        DateConverter dateConverter = new DateConverter();
        NumberSetList numberSetList = new NumberSetList();
        currentSheet = currentWorkbook.getSheetAt(sheetNumber);
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

                    //Die erste Zelle mit Datum wird in das NumberSet geschrieben.
                    if (cellValue != "" && counter == 0) {
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
                //Nur NumberSets mit Datum werden in die Liste geschrieben.
                if (currentNumberSet.getDate() != null) {

                    numberSetList.addNumberSet(currentNumberSet);
                }
            }
            rowCounter++;
        }
        System.out.println("WorkbookReader: numberSetList has been created.");
        return numberSetList;
    }
}
