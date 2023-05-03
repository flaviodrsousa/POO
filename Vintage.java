import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class Vintage implements Serializable{
    private GestorEncomendas gestorEncomendas;
    private GestorTransportadoras gestorTransportadoras;
    private GestorUtilizadores gestorUtilizadores;
    private GestorArtigos gestorArtigos;
    private Date data_atual;

    //Carrega estado
    public static Vintage carregaEstado(String nomeFicheiro) throws FileNotFoundException,IOException,
    ClassNotFoundException{
        FileInputStream fis = new FileInputStream(nomeFicheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Vintage v = (Vintage) ois.readObject();
        ois.close();
        return v;
    }

    //Guardar estado
    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException,IOException{
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    //Construtores
    private void estadoInicial_Vintage() throws ParseException, AddException{
        this.data_atual = new Date();

        Artigo artigo1 = new Sapatilha("sap1a",true,"novo",1,"sapatilha vermelha num 45",
        "Nike",50.5,1,45,true,Color.RED,false,"10-05-2000");

        GestorArtigos gestorArtigos_1= new GestorArtigos();
        gestorArtigos_1.addArtigo(artigo1);

        Transportadora transportadora = new Transportadora("Fedex",10,20,50);

        this.gestorTransportadoras.addTransportadora(transportadora);

        Utilizador utilizador1 = new Utilizador("u0001","u0001@gmail.com","Henrique Malheiro","Rua Braga Parque",
        1000,gestorArtigos_1,null,null,transportadora);
        Utilizador utilizador2 = new Utilizador("u0002","u0002@gmail.com","Carolina Melo","Rua de Baixo",
        1005,null,gestorArtigos_1,null,transportadora);

        this.gestorUtilizadores.addUtilizador(utilizador1);
        this.gestorUtilizadores.addUtilizador(utilizador2);

        Encomenda encomenda = new Encomenda(gestorArtigos_1,Encomenda.DimensaoEmbalagem.pequeno,5,10,
        "05-07-2002","10-07-2002",Encomenda.Estado.entregue,utilizador1,utilizador2);

        this.gestorEncomendas.addEncomenda(encomenda);
    }

    public Vintage() throws ParseException, AddException{
        this.gestorEncomendas = new GestorEncomendas();
        this.gestorTransportadoras= new GestorTransportadoras();
        this.gestorUtilizadores= new GestorUtilizadores();
        this.gestorArtigos = new GestorArtigos();
        this.estadoInicial_Vintage();
    }

    public Vintage(String data_atual,GestorEncomendas gestorEncomendas,GestorTransportadoras gestorTransportadoras, 
    GestorUtilizadores gestorUtilizadores,GestorArtigos gestorArtigos) throws ParseException{
        this.data_atual=Data.StringtoDate(data_atual);
        this.gestorEncomendas=gestorEncomendas.clone();
        this.gestorTransportadoras=gestorTransportadoras.clone();
        this.gestorUtilizadores=gestorUtilizadores.clone();
        this.gestorArtigos=gestorArtigos.clone();
    }

    public Vintage(Vintage vintage) {
        this.gestorEncomendas = vintage.getGestorEncomendas();
        this.gestorTransportadoras=vintage.getGestorTransportadoras();
        this.gestorUtilizadores=vintage.getGestorUtilizadores();
        this.gestorArtigos=vintage.getGestorArtigos();
        this.data_atual=vintage.get_DataAtual();
    }

    //gets
    public Date get_DataAtual() {
        Date newDate = new Date(this.data_atual.getTime());
        return newDate;
    }

    public GestorEncomendas getGestorEncomendas() {
        return gestorEncomendas.clone();
    }

    public GestorTransportadoras getGestorTransportadoras() {
        return gestorTransportadoras.clone();
    }

    public GestorUtilizadores getGestorUtilizadores() {
        return gestorUtilizadores.clone();
    }

    public GestorArtigos getGestorArtigos() {
        return gestorArtigos.clone();
    }

    //sets
    public void set_DataAtual(Date data) {
        this.data_atual = new Date(data.getTime());
    }

    public void setGestorEncomendas(GestorEncomendas gestorEncomendas) {
        this.gestorEncomendas = gestorEncomendas.clone();
    }

    public void setGestorTransportadoras(GestorTransportadoras gestorTransportadoras) {
        this.gestorTransportadoras = gestorTransportadoras.clone();
    }

    public void setGestorUtilizadores(GestorUtilizadores gestorUtilizadores) {
        this.gestorUtilizadores = gestorUtilizadores.clone();
    }
    
    public void setGestorArtigos(GestorArtigos gestorArtigos) {
        this.gestorArtigos = gestorArtigos.clone();
    }
    
    //clone
    public Vintage clone(){
        return new Vintage(this);
    }

    //equals
    public boolean equals(Object o){
        if (this==o) 
            return true;
        if ((o==null) || (this.getClass() != o.getClass())) 
            return false;
        Vintage vintage = (Vintage) o;
        return (vintage.gestorEncomendas.equals(this.gestorEncomendas) &&
        vintage.gestorTransportadoras.equals(this.gestorTransportadoras) &&
        vintage.gestorUtilizadores.equals(this.gestorUtilizadores) &&
        vintage.get_DataAtual().equals(this.data_atual));
    }

    //toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Encomendas Vendidas: ").append(gestorEncomendas.toString()+'\n');
        sb.append("Transportadoras disponiveis: ").append(gestorTransportadoras.toString()+'\n');
        sb.append("utilizadores registrados: ").append(gestorUtilizadores.toString());
        sb.append("Data atual: ").append(data_atual.toString());

        return sb.toString();
    }

    //Outros métodos
    private void entregaEncomenda(){
        gestorEncomendas.entregaEncomenda(this);
    }

    public void avancarTempo(String date) throws ParseException{
        Date newDate = Data.StringtoDate(date);
        set_DataAtual(newDate);
        this.entregaEncomenda();
    }

    //Q1
    public String vendedorMaisFatorou(){
        return gestorEncomendas.vendedorMaisFatorou();
    }

    //Q2
    public String TransportadoraMaiorVolumeFatoracao(){
        return gestorEncomendas.TransportadoraMaiorVolumeFatoracao();
    }

    //Q3
    public void encomendasVendedor(String CodUtilizador){
        gestorEncomendas.encomendasVendedor(CodUtilizador);
    }

    //Q5
    public double ganhosVintage(){
        return gestorEncomendas.ganhosVintage();
    }
}