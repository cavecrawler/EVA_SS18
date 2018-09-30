package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException {

        XMLConfig xmlConfig = new XMLConfig();
        ExcelLoader excelLoader = new ExcelLoader(xmlConfig.getSourceFilepath());    //Exceldatei laden
        Workbook workbook = excelLoader.getWorkbook(); //workbook holen
        WorkbookReader workbookReader = new WorkbookReader(workbook, xmlConfig.getSourceSheets());  //workbookReader starten

        NumberSetList numberSetList = workbookReader.getNumberSetList(xmlConfig.getSourceSheets()[0]);     // numberSetList aus workbook lesen

        // calculations List wird aus der xmlConfig gelesen.
        ArrayList<Calculation> calculations = xmlConfig.getCalculations();

        //Taskmaster erhält numberSetList und calculations List
        TaskMaster taskMaster = new TaskMaster(numberSetList, calculations);
        try {
            taskMaster.startThreads();
        } catch (Exception e) {
        }
        System.out.println("Taskmaster beendet.");


        // ResultObjects werden aus Calculationlist gelesen und in das TargetSheet (yoy, maxdd, etc.) geschrieben
        Map<String, Integer> indices = numberSetList.getFirstNumberSet().getIndices();
        ExcelWriter excelWriter = new ExcelWriter(calculations, xmlConfig, indices);
        excelWriter.writeResultsInTargetExcel();
    }
}
