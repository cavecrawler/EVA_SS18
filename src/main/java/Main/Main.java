package Main;

import java.io.IOException;

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


        //Prüfmethode um NumberSetList auf leere Objekte zu prüfen
        numberSetList.checkNumberSetListForEmptyNumberSets();
        Mapper map = new Mapper(workbookReader.getValueNames(0));     // Testmethodenaufruf um 1. Zeile mit Indize-Namen zu erhalten


        //Taskmaster übernimmt das Starten der Threads nach Config Vorgaben
        TaskMaster taskMaster = new TaskMaster(numberSetList, xmlConfig);
        taskMaster.startThreads();


    }
}