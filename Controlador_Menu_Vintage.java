public class Controlador_Menu_Vintage {
    private Vintage vintage;

    //Construtores
    public Controlador_Menu_Vintage(){
        this.vintage=new Vintage();
    }

    public Controlador_Menu_Vintage(Vintage vintage){
        this.vintage=vintage;  //Agregação
    }

    //gets
    public Vintage getVintage(){ //Agregação
        return this.vintage;
    }

    //sets
    public void setVintage(Vintage vintage) { //Agregação
        this.vintage = vintage;
    }
}
