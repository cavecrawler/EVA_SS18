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
        String XLS_Filepath = "C:\\Users\\Fabian\\Desktop\\Rohdaten_BI.xlsx";
        ExcelLoader excelLoader = new ExcelLoader(XLS_Filepath);

        Workbook workbook = excelLoader.getWorkbook(); //workbook holen
        WorkbookReader workbookReader = new WorkbookReader(workbook, 0); //workbookReader starten
        NumberSetList numberSetList = workbookReader.getNumberSetList(); // numberSetList aus workbook lesen
        System.out.println("Main: Job finished.");

        ExecutorService executor = Executors.newFixedThreadPool(2);
      
        for (int id = 1; id < 6; id++) {
            executor.submit(new Worker(id, 1, numberSetList));
        }

        executor.shutdown();



//        CustomThread customThread1 = new CustomThread(1, numberSetList);
//        CustomThread customThread2 = new CustomThread(3, numberSetList);
//        customThread1.run();
//        customThread2.run();


    }
}