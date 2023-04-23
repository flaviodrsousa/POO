public class Menu {
    private Controlador_Menu_Vintage controlador_Menu_Vintage;
    private String Menu;

    //Contrutores
    public Menu(){
        this.controlador_Menu_Vintage= new Controlador_Menu_Vintage();
        this.Menu=this.menu_String();
    }

    public Menu(Controlador_Menu_Vintage controlador_Menu_Vintage){
        this.controlador_Menu_Vintage=controlador_Menu_Vintage; //Agregação
        this.Menu=this.menu_String();
    }

    //gets
    public Controlador_Menu_Vintage getControlador_Menu_Vintage(){ //Agregação
        return this.controlador_Menu_Vintage;
    }

    public String getMenu() {
        return this.Menu;
    }

    //sets
    public void setControlador_Menu_Vintage(Controlador_Menu_Vintage controlador_Menu_Vintage) {
        this.controlador_Menu_Vintage = controlador_Menu_Vintage; //Agregação
    }

    public void setMenu(String menu) {
        this.Menu = menu;
    }

    //Menu
    private String menu_String(){
        StringBuilder sb = new StringBuilder();
        sb.append("--------Menu-------\n");
        sb.append("Opcao1: \n");
        sb.append("Opcao2: \n");
        sb.append("Opcao3: ");

        return sb.toString();
    }
}
