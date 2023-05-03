import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

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
    public void avancarTempo(String date) throws DateTimeException{
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

    //Q4
    public void topVendedoresCompradores(String dataInicial, String dataFinal, int top) throws DateTimeException{
        vintage.topVendedoresCompradores(dataInicial, dataFinal, top);
    }

    //Q5
    public double ganhosVintage(){
        return vintage.ganhosVintage();
    }

    //addArtigo
    public void addArtigo(Artigo artigo) throws AddException{
        vintage.getGestorArtigos().addArtigo(artigo);
    }

    //addUtilizador
    public void addUtilizador(Utilizador utilizador) throws AddException{
        vintage.getGestorUtilizadores().addUtilizador(utilizador);
    }

    //addTransportadora
    public void addTransportadora(Transportadora transportadora) throws AddException{
        vintage.getGestorTransportadoras().addTransportadora(transportadora);
    }

    //addEncomenda
    public void addEncomenda(Encomenda encomenda) throws AddException{
        vintage.getGestorEncomendas().addEncomenda(encomenda);
    }

    //removeArtigo
    public void removeArtigo(Artigo artigo) throws RemoveException{
        vintage.getGestorArtigos().removeArtigo(artigo);
    }

    //removeUtilizador
    public void removeUtilizador(Utilizador utilizador) throws RemoveException{
        vintage.getGestorUtilizadores().removeUtilizador(utilizador);
    }

    //removeTransportadora
    public void removeTransportadora(Transportadora transportadora) throws RemoveException{
        vintage.getGestorTransportadoras().removeTransportadora(transportadora);
    }

    //removeEncomenda
    public void removeEncomenda(Encomenda encomenda) throws RemoveException{
        vintage.getGestorEncomendas().removeEncomenda(encomenda);
    }

    //getArtigo
    public Artigo getArtigo(String codBarras) throws GetException{
        return vintage.getGestorArtigos().getArtigo(codBarras);
    }    

    //getUtilizador
    public Utilizador getUtilizador(String codUtilizador) throws GetException{
        return vintage.getGestorUtilizadores().getUtilizador(codUtilizador);
    }

    //getTransportadora
    public Transportadora getTransportadora(String nomeTransportadora) throws GetException{
        return vintage.getGestorTransportadoras().getTransportadora(nomeTransportadora);
    }

    //getEncomenda
    public Encomenda getEncomenda(Integer numEncomenda) throws GetException{
        return vintage.getGestorEncomendas().getEncomenda(numEncomenda);
    }

    //getData_atual
    public LocalDate getData_atual(){
        return vintage.get_DataAtual();
    }

    //toString Transportadoras
    public String toString_Transportadoras(){
        return vintage.getGestorTransportadoras().getTransportadoras().toString();
    }
}