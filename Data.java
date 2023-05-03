import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Data implements Serializable{
    private static DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    public static LocalDate StringtoDate(String dateString) throws DateTimeException{
        LocalDate date = LocalDate.parse(dateString,dataFormato);

        return date;
    }
}
