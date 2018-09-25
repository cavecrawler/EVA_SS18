package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException {


        XMLConfig xmlConfig = new XMLConfig();
        ExcelLoader excelLoader = new ExcelLoader(xmlConfig.getFilepath());    //Exceldatei laden
        Workbook workbook = excelLoader.getWorkbook(); //workbook holen
        WorkbookReader workbookReader = new WorkbookReader(workbook, xmlConfig.getSheets());  //workbookReader starten
        // TODO hardcode sheetnumber entfernen
        NumberSetList numberSetList = workbookReader.getNumberSetList(0);     // numberSetList aus workbook lesen

        // calculations List wird aus der config gelesen.
        ArrayList<Calculation> calculations = xmlConfig.getCalculations();

        //Taskmaster erhält numberSetList und calculations List
        TaskMaster taskMaster = new TaskMaster(numberSetList, calculations);
        try {
            taskMaster.startThreads();
        } catch (Exception e) {
        }
        System.out.println("Taskmaster beendet.");
        int i=1;
    }
}
