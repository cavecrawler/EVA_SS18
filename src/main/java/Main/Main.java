package Main;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException {

        ConfigReader config = new ConfigReader();
        ExcelLoader excelLoader = new ExcelLoader(config.getFilePath());

        Workbook workbook = excelLoader.getWorkbook(); //workbook holen
        WorkbookReader workbookReader = new WorkbookReader(workbook, config.getWorkbook());  //workbookReader starten
        workbookReader.getValueNames();
        NumberSetList numberSetList = workbookReader.getNumberSetList();     // numberSetList aus workbook lesen

        //Prüfmethode um NumberSetList auf leere Objekte zu prüfen
        numberSetList.checkNumberSetListForEmptyNumberSets();

        // TODO: Mapper Starten
        // workbookReader.getValueNames();     // Testmethodenaufruf um 1. Zeile mit Indize-Namen zu erhalten

      //Taskmaster übernimmt das Starten der Threads nach Config Vorgaben
        TaskMaster taskMaster = new TaskMaster(numberSetList, config);
        taskMaster.startThreads();





    }
}