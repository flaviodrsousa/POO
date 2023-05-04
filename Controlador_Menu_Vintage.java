import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
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
    public void removeArtigo(String codBarras) throws RemoveException{
        vintage.getGestorArtigos().removeArtigo(codBarras);
    }

    //removeUtilizador
    public void removeUtilizador(String codUtilizador) throws RemoveException{
        vintage.getGestorUtilizadores().removeUtilizador(codUtilizador);
    }

    //removeTransportadora
    public void removeTransportadora(String nome) throws RemoveException{
        vintage.getGestorTransportadoras().removeTransportadora(nome);
    }

    //removeEncomenda
    public void removeEncomenda(Integer numEncomenda) throws RemoveException{
        vintage.getGestorEncomendas().removeEncomenda(numEncomenda);
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
        return vintage.getGestorTransportadoras().toString();
    }

    //toString Artigos
    public String toString_Artigos(){
        return vintage.getGestorArtigos().toString();
    }

    //toString Utilizadores
    public String toString_Utilizadores(){
        return vintage.getGestorUtilizadores().toString();
    }

    //numArtigosVintage
    public int numArtigos_Vintage(){
        return vintage.getGestorArtigos().getArtigos().size();
    }
}