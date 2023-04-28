import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        try{
            Vintage vintage = new Vintage();
            Controlador_Menu_Vintage controlador_Menu_Vintage = new Controlador_Menu_Vintage(vintage);
            Menu menu = new Menu(controlador_Menu_Vintage);
            menu.run();
        }catch (ParseException e){
            System.out.println(e.getMessage());
        }
    }
}