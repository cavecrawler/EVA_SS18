package Main;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelWriter {

    List<Calculation> calculations;
    XMLConfig xmlConfig;
    Workbook workbook;
    Map<String, Integer> indices;

    public ExcelWriter(List<Calculation> calculations, XMLConfig xmlConfig, Map<String, Integer> indices) throws IOException, InvalidFormatException {
        this.calculations = calculations;
        this.xmlConfig = xmlConfig;
        this.indices = indices;
        workbook = getWorkbook(xmlConfig.getTargetFilepath());
    }

    public Workbook getWorkbook(String XLS_Filepath) throws IOException, InvalidFormatException {
        workbook = WorkbookFactory.create(new File(XLS_Filepath));
        System.out.println("ExcelLoader: Workbook has been created.");

        return workbook;
    }

    public void writeResultsInTargetExcel() {

        List<String> calculationTypes = new ArrayList<>();


        for (Calculation calculation : calculations) {
            calculationTypes.add(calculation.getType());
        }

        for (String type : calculationTypes) {
            Sheet currentSheet = workbook.getSheetAt(xmlConfig.getTargetSheets().get(type));

            // Befüllen der ersten Spalte mit Indexnamen
            Iterator<Row> rowIterator = currentSheet.rowIterator();
            Row row = currentSheet.getRow(2);

            for (Map.Entry<String, Integer> entry : indices.entrySet()) {
                row.getCell(1).setCellValue(entry.getKey());
                row = rowIterator.next();
            }

            for (Calculation calculation : calculations) {
                if (calculation.getType() == type) {

                    for (Map.Entry<String, Float> resultEntry : calculation.getResult().getResultMap().entrySet()) {
                        row = currentSheet.getRow(indices.get(resultEntry.getKey()) + 2);
                        row.getCell(calculation.getTimeIndicator() + 1).setCellValue(resultEntry.getValue());
                    }
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(xmlConfig.getTargetFilepath());
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {
        }
    }
}
