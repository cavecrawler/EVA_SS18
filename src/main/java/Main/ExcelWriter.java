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

        Map<String, Integer> sheets = xmlConfig.getTargetSheets();
        // Befüllen der ersten Spalte mit Indexnamen
        for (Map.Entry<String, Integer> sheet : sheets.entrySet()) {
            Sheet currentSheet = workbook.getSheetAt(xmlConfig.getTargetSheets().get(sheet.getKey()));
            for (Map.Entry<String, Integer> entry : indices.entrySet()) {
                Cell currentCell = currentSheet.createRow(entry.getValue()+1).createCell(0);
                currentCell.setCellValue(entry.getKey());
            }
        }
        int i = 1;

        for (Calculation calculation : calculations) {
            Sheet currentSheet = workbook.getSheetAt(xmlConfig.getTargetSheets().get(calculation.getType()));

            for (Map.Entry<String, Float> resultEntry : calculation.getResult().getResultMap().entrySet()) {
                Row row = currentSheet.getRow(indices.get(resultEntry.getKey()) + 1);
                row.createCell(calculation.getTimeIndicator()).setCellValue(resultEntry.getValue());
            }
        }

        try {
            String resultFileName = xmlConfig.getResultFilepath();
            FileOutputStream outputStream = new FileOutputStream(resultFileName);
            workbook.write(outputStream);
//            workbook.close();
//            outputStream.close();
        } catch (
                Exception e) {
        }

    }
}