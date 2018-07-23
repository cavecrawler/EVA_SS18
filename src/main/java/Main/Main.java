package Main;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException {

        String XLS_Filepath = "C:\\Users\\chrisso\\Documents\\JavaProjects\\src\\main\\resources\\Rohdaten.xlsx";
        ExcelLoader excelLoader = new ExcelLoader(XLS_Filepath);
        ArrayList<NumberSet> NumberSetList = new ArrayList<>();
        //workbook holen. sheet extrahieren
        Workbook workbook = excelLoader.getWorkbook();
        WorkbookReader workbookReader = new WorkbookReader(workbook, 0);
        NumberSetList = workbookReader.getNumberSetList();

        System.out.println("Main: Job finished.");

        //testabfragen von Listenelementen
        KPICalc kpiCalc = new KPICalc(NumberSetList);
        NumberSet targetNumberSet = kpiCalc.getTargetDate("6/22/17");
        System.out.print("Ausgabe über Methodenaufruf printDate(): ");
        targetNumberSet.printDate();
        float profit = kpiCalc.calculateYoYProfit("6/22/18", 1);
        System.out.println("Die Performance YoY ist: " + profit * 100 + "%.");

    }
}