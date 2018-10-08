package Main;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelWriter {

    private List<Calculation> calculations;
    private XMLConfig xmlConfig;
    private Workbook workbook;
    private Map<String, Integer> indices;

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
                Cell currentCell = currentSheet.createRow(entry.getValue() + 1).createCell(0);
                currentCell.setCellValue(entry.getKey());
            }
        }

        for (Calculation calculation : calculations) {
            Sheet currentSheet = workbook.getSheetAt(xmlConfig.getTargetSheets().get(calculation.getType()));

            for (Map.Entry<String, Float> resultEntry : calculation.getResult().getResultMap().entrySet()) {
                Row row = currentSheet.getRow(indices.get(resultEntry.getKey()) + 1);
                row.createCell(calculation.getTimeIndicator()).setCellValue(resultEntry.getValue());
            }
        }

        try {
            //dynamische Dateinamen
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
            String fileCreationString = (xmlConfig.getResultFilepath() + dateFormat.format(date) + "_KPI_Results.xlsx");
//          String fileCreationString = (xmlConfig.getResultFilepath() + "KPI_Results.xlsx");
            FileOutputStream outputStream = new FileOutputStream(fileCreationString);
            workbook.write(outputStream);

        } catch (
                Exception e) {
        }
    }
}