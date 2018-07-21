package Main;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    public LocalDate formatDate(String cellValue) throws DateTimeException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("M/d/yy");
        try {
            LocalDate FormattedDateOut = LocalDate.parse(cellValue,df);
            return FormattedDateOut;
        }
        catch (DateTimeException i){

            System.out.print("DateTimeException: " + cellValue);
            return null;
        }
    }
}
