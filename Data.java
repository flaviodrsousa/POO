import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data implements Serializable{
    private static DateFormat dataFormato = new SimpleDateFormat("dd-MM-yyyy");
    
    public static Date StringtoDate(String dateString) throws ParseException{
        Date date = new Date();
        date= dataFormato.parse(dateString);

        return date;
    }
}
