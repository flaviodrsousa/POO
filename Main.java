import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Vintage vintage = new Vintage();
        Date date= vintage.get_DataAtual();
        date.setTime(1000);
        System.out.println(vintage.get_DataAtual());
        System.out.println(date.toString());
    }
}
