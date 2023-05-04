import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.List;

public class Controlador_Menu_Vintage {
    private Vintage vintage;

    //Carregar Estado
    public static Vintage carregaEstado(String nomeFicheiro) throws FileNotFoundException,IOException,
    ClassNotFoundException{
        return Vintage.carregaEstado(nomeFicheiro);
    }

    //Guardar estado
    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException,IOException{
        vintage.guardaEstado(nomeFicheiro);
    }

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
    public List<Encomenda> avancarTempo(String date) throws DateTimeException{ //retorna uma lista de encomendas entregues no periodo
        return vintage.avancarTempo(date);
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

    //Q4
    public void topVendedoresCompradores(String dataInicial, String dataFinal, int top) throws DateTimeException{
        vintage.topVendedoresCompradores(dataInicial, dataFinal, top);
    }

    //Q5
    public double ganhosVintage(){
        return vintage.ganhosVintage();
    }

    //ToString_Vintage
    public String toString_Vintage(){
        return vintage.toString();
    }

    //exists_Utilizador
    public boolean exists_Utilizador(String codUtilizador){
        return vintage.getGestorUtilizadores().getUtilizadores().containsKey(codUtilizador);
    }
}