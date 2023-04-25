public class Main {
    public static void main(String[] args) {
        Vintage vintage = new Vintage();

        Controlador_Menu_Vintage controlador_Menu_Vintage = new Controlador_Menu_Vintage(vintage);

        Menu menu = new Menu(controlador_Menu_Vintage);

        System.out.println(menu.getMenu());
        //Ciclo com scanners, passam isto para o vintage que vai chamar as respetivas queries com os argumentos do scanner.
    }
}
