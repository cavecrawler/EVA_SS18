package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException {

        ConfigReader config = new ConfigReader();
        ExcelLoader excelLoader = new ExcelLoader(config.getFilePath());

        Workbook workbook = excelLoader.getWorkbook(); //workbook holen
        WorkbookReader workbookReader = new WorkbookReader(workbook, config.getWorkbook());  //workbookReader starten
        NumberSetList numberSetList = workbookReader.getNumberSetList();     // numberSetList aus workbook lesen

        //Prüfmethode um NumberSetList auf leere Objekte zu prüfen
        numberSetList.checkNumberSetList();

        //Taskmaster übernimmt das Starten der Threads nach Config Vorgaben
        TaskMaster taskMaster = new TaskMaster(numberSetList, config);
        taskMaster.startThreads();



       // KPICalc Calc = new KPICalc(numberSetList);
       // System.out.println(Calc.calculate_MaxDD());

    }
}