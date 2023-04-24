import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
    private static DateFormat dataFormato = new SimpleDateFormat("dd-MM-yyyy");
    
    public static Date StringtoDate(String dateString){
        Date date = new Date();
        try {
            date= dataFormato.parse(dateString);
        }catch (ParseException e) {
            System.out.println("Data no formato errado");
        }

        return date;
    }
}
