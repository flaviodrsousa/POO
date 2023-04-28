import java.text.ParseException;

public class Controlador_Menu_Vintage {
    private Vintage vintage;

    //Construtores
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

    //Outros métodos
    public void avancarTempo(String date) throws ParseException{
        vintage.avancarTempo(date);
    }

    //Q1
    public String vendedorMaisFatorou(){
        return vintage.vendedorMaisFatorou();
    }

    //Q2
    public String TransportadoraMaiorVolumeFatoracao(){
        return vintage.TransportadoraMaiorVolumeFatoracao();
    }

    //Q3
    public void encomendasVendedor(String codUtilizador){
        vintage.encomendasVendedor(codUtilizador);
    }

    //Q5
    public double ganhosVintage(){
        return vintage.ganhosVintage();
    }
}
