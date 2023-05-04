import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class Vintage implements Serializable{
    private GestorEncomendas gestorEncomendas;
    private GestorTransportadoras gestorTransportadoras;
    private GestorUtilizadores gestorUtilizadores;
    private GestorArtigos gestorArtigos;
    private LocalDate data_atual;

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
    private void estadoInicial_Vintage() throws DateTimeException, AddException{
        this.data_atual = LocalDate.now();

        Artigo sap1a = new Sapatilha("sap1a",true,"novo",1,"sapatilha vermelha Numero 45",
        "Nike",50.5,1,45,true,Color.RED,false,"10-05-2000");
        Artigo sap1b = new Sapatilha("sap1b",false,"pouco usado",3,"Sapatilha preta numero 39","reebok",50.7,0.6,39,
        false,Color.BLACK,true,"10-10-2020");
        Artigo tshirt_1a = new Tshirt("Tshirt_1a",true,"novo",1,"Tshirt branca tamamho L","Adidas",25,
        1,Tshirt.Tamanho.L,Tshirt.Padrao.LISO);
        Artigo tshirt_1b = new Tshirt("Tshirt_1b",false,"bastante usado",5,"Tshirt vermelha às riscas tamanho M",
        "Pull&Bear",33,0.7,Tshirt.Tamanho.M,Tshirt.Padrao.RISCAS); 
        Artigo mala1a = new Mala("mala1a",false,"pouco usada",2,"mala amarela grande","Zara",35,
        0.8,Mala.Dimensao.GRANDE,"tecido","10-08-2021",false);
        Artigo mala1b = new Mala("mala1b",true,"novo",1,"mala preta pequena","Prada",500,1,Mala.Dimensao.PEQUENO,"pele",
        "10-05-2000",true);

        this.gestorArtigos.addArtigo(sap1a); //ficar no historico do Vintage
        this.gestorArtigos.addArtigo(sap1b);
        this.gestorArtigos.addArtigo(tshirt_1a);
        this.gestorArtigos.addArtigo(tshirt_1b);
        this.gestorArtigos.addArtigo(mala1a);
        this.gestorArtigos.addArtigo(mala1b);

        Transportadora fedex = new Transportadora("Fedex",10,20,50);
        Transportadora DPD = new Transportadora("DPD",20,25,40);
        Transportadora DHL = new Transportadora("DHL",10,50,100);
        Transportadora Amazon_Logistics = new Transportadora("Amazon Logistics",30,70,120);

        this.gestorTransportadoras.addTransportadora(fedex);
        this.gestorTransportadoras.addTransportadora(DPD);
        this.gestorTransportadoras.addTransportadora(DHL);
        this.gestorTransportadoras.addTransportadora(Amazon_Logistics);

        GestorArtigos historicoCompras_u0001 = new GestorArtigos();
        historicoCompras_u0001.addArtigo(sap1a);
        GestorArtigos historicoVendas_u0001 = new GestorArtigos();
        historicoVendas_u0001.addArtigo(tshirt_1b);
        GestorArtigos aVenda_u0001 = new GestorArtigos();
        aVenda_u0001.addArtigo(mala1a);

        Utilizador u0001 = new Utilizador("u0001","u0001@gmail.com","Henrique Malheiro","Rua Braga Parque",
        967865421,historicoCompras_u0001,historicoVendas_u0001,aVenda_u0001,fedex);
        

        GestorArtigos historicoCompras_u0002 = new GestorArtigos();
        GestorArtigos historicoVendas_u0002 = new GestorArtigos();
        historicoVendas_u0002.addArtigo(sap1a);
        historicoVendas_u0002.addArtigo(tshirt_1a);
        GestorArtigos aVenda_u0002 = new GestorArtigos();
        aVenda_u0002.addArtigo(sap1b);

        Utilizador u0002 = new Utilizador("u0002","u0002@gmail.com","Carolina Melo","Rua de Baixo",
        973417390,historicoCompras_u0002,historicoVendas_u0002,aVenda_u0002,DPD);

        GestorArtigos historicoCompras_u0003 = new GestorArtigos();
        historicoCompras_u0003.addArtigo(tshirt_1a);
        historicoCompras_u0003.addArtigo(tshirt_1b);
        GestorArtigos historicoVendas_u0003 = new GestorArtigos();
        GestorArtigos aVenda_u0003 = new GestorArtigos();
        aVenda_u0003.addArtigo(mala1b);

        Utilizador u0003 = new Utilizador("u0003","u0003@gmail.com","Inês Rodrigues","Rua de Cima",
        628183821,historicoCompras_u0003,historicoVendas_u0003,aVenda_u0003,Amazon_Logistics);

        this.gestorUtilizadores.addUtilizador(u0001);
        this.gestorUtilizadores.addUtilizador(u0002);
        this.gestorUtilizadores.addUtilizador(u0003);

        GestorArtigos artigos_Encomenda1 = new GestorArtigos();
        artigos_Encomenda1.addArtigo(sap1a);
        GestorUtilizadores vendedores_Encomenda1 = new GestorUtilizadores();
        vendedores_Encomenda1.addUtilizador(u0002);

        Encomenda encomenda1 = new Encomenda(artigos_Encomenda1,Encomenda.DimensaoEmbalagem.pequeno,5,
        "05-07-2002","10-07-2002",Encomenda.Estado.entregue,vendedores_Encomenda1,u0001);

        GestorArtigos artigos_Encomenda2 = new GestorArtigos();
        artigos_Encomenda2.addArtigo(tshirt_1a);
        artigos_Encomenda2.addArtigo(tshirt_1b);
        GestorUtilizadores vendedores_Encomenda2 = new GestorUtilizadores();
        vendedores_Encomenda2.addUtilizador(u0001);
        vendedores_Encomenda2.addUtilizador(u0002);

        Encomenda encomenda2 = new Encomenda(artigos_Encomenda2,Encomenda.DimensaoEmbalagem.medio,20,
        "04-03-2023","10-10-2023",Encomenda.Estado.expedida,vendedores_Encomenda1,u0001);

        this.gestorEncomendas.addEncomenda(encomenda1);
        this.gestorEncomendas.addEncomenda(encomenda2);
    }

    public Vintage() throws DateTimeException, AddException{
        this.gestorEncomendas = new GestorEncomendas();
        this.gestorTransportadoras= new GestorTransportadoras();
        this.gestorUtilizadores= new GestorUtilizadores();
        this.gestorArtigos = new GestorArtigos();
        this.estadoInicial_Vintage();
    }

    public Vintage(String data_atual,GestorEncomendas gestorEncomendas,GestorTransportadoras gestorTransportadoras, 
    GestorUtilizadores gestorUtilizadores,GestorArtigos gestorArtigos) throws DateTimeException{
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
    public LocalDate get_DataAtual() {
        return LocalDate.of(data_atual.getYear(),
        data_atual.getMonth(),data_atual.getDayOfMonth());
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
    public void set_DataAtual(LocalDate data) {
        this.data_atual = LocalDate.of(data.getYear(),
        data.getMonth(),data.getDayOfMonth());
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
        sb.append("\nArtigos Registados: ").append(gestorArtigos.toString()+'\n');
        sb.append("Encomendas Vendidas: ").append(gestorEncomendas.toString()+'\n');
        sb.append("Transportadoras disponiveis: ").append(gestorTransportadoras.toString()+'\n');
        sb.append("utilizadores registrados: ").append(gestorUtilizadores.toString());
        sb.append("\nData atual: ").append(data_atual.toString());

        return sb.toString();
    }

    //Outros métodos
    private List<Encomenda> entregaEncomenda(){
        return gestorEncomendas.entregaEncomenda(this);
    }

    public List<Encomenda> avancarTempo(String date) throws DateTimeException{
        LocalDate newDate = Data.StringtoDate(date);
        set_DataAtual(newDate);
        return this.entregaEncomenda();
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

    //Q4
    public void topVendedoresCompradores(String datai, String dataf, int top) throws DateTimeException{
        gestorEncomendas.topVendedoresCompradores(datai, dataf, top);
    }

    //Q5
    public double ganhosVintage(){
        return gestorEncomendas.ganhosVintage();
    }
}