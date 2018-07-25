package Main;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException {


        String XLS_Filepath = "C:\\Users\\chris\\IdeaProjects\\EVA_ExcelImporter\\src\\main\\resources\\Rohdaten.xlsx";
        ExcelLoader excelLoader = new ExcelLoader(XLS_Filepath);
        ArrayList<NumberSet> numberSetList = new ArrayList<>();
        //workbook holen. sheet extrahieren
        Workbook workbook = excelLoader.getWorkbook();
        WorkbookReader workbookReader = new WorkbookReader(workbook, 0);
        numberSetList = workbookReader.getNumberSetList();

        System.out.println("Main: Job finished.");

        CustomThread customThread1 = new CustomThread(1,numberSetList);
        CustomThread customThread2 = new CustomThread(3,numberSetList);

        customThread1.run();
        customThread2.run();

        //testabfragen von Listenelementen
//        KPICalc kpiCalc = new KPICalc(numberSetList);
//        NumberSet targetNumberSet = kpiCalc.getTargetDate("6/22/17");
//        System.out.print("Ausgabe über Methodenaufruf printDate(): ");
//       targetNumberSet.printDate();

        //YoY Profit
        //float profit = kpiCalc.calculateYoYProfit("6/24/18", 0);

    }
}