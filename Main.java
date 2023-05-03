import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        try{
/*             Vintage vintage = Controlador_Menu_Vintage.carregaEstado("EstadoVintage.dat");
            if(vintage ==null) {
                System.out.println("cenas");
                vintage = new Vintage();
            } */
            Vintage vintage = new Vintage();
            Controlador_Menu_Vintage controlador_Menu_Vintage = new Controlador_Menu_Vintage(vintage);
            Menu menu = new Menu(controlador_Menu_Vintage);
            menu.run();
            vintage.guardaEstado("EstadoVintage.dat");
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
/*         }catch (ClassNotFoundException e){
            System.out.println(e.getMessage()); */
        }catch (ParseException e){
            System.out.println(e.getMessage());
        } catch (AddException e) {
            System.out.println(e.getMessage());
        }
    }
}