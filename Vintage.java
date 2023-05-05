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
        Encomenda.setContador((int) ois.readObject()); 
        Vintage v = (Vintage) ois.readObject();
        ois.close();
        return v;
    }

    //Guardar estado
    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException,IOException{
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Encomenda.getContador());
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    //Construtores
    private void estadoInicial_Vintage() throws DateTimeException, AddException, RemoveException{
        this.data_atual = LocalDate.now();

        //Sapatilhas

        //u0002
        Artigo sap1a = new Sapatilha("sap1a",true,"novo",1,"sapatilha vermelha Numero 45",
        "Nike",50.5,1,45,true,Color.RED,false,"10-05-2000");

        //0003
        Artigo sap1b = new Sapatilha("sap1b",false,"pouco usado",3,"Sapatilha preta numero 39",
        "reebok",50.7,0.6,39,false,Color.BLACK,true,"10-10-2020");
        
        //u0001
        Artigo sap1c = new Sapatilha("sap1c", true, "Excelente estado", 1,"Sapatilha de corrida",
        "Nike", 79.99, 1, 43, true, Color.BLACK,true, "01-01-2023");

        //u0008
        Artigo sap1d = new Sapatilha("sap1d", false, "Bom estado", 2,"Sapatilha de treino",
        "Adidas", 89.99, 0.5, 42, true, Color.BLUE,false, "07-07-2022");

        //u0005
        Artigo sap1e = new Sapatilha("sap1e", true, "Excelente estado", 1,"Sapatilha de basquete", 
        "Jordan", 129.99, 1, 44, true, Color.RED,true, "08-08-2023");

        //u0005
        Artigo sap1f = new Sapatilha("sap1f", false, "Bom estado", 2,"Sapatilha de futsal", 
        "Puma", 69.99, 0.6, 41, true, Color.YELLOW,false, "09-09-2022");
        
        //u0002
        Artigo sap1g = new Sapatilha("sap1g", true, "Novo", 0,"Sapatilha casual", 
        "Converse", 49.99, 1.0, 39, false, Color.WHITE,false, "10-10-2023");

        //Tshirts

        //u0002
        Artigo tshirt_1a = new Tshirt("Tshirt_1a",true,"novo",1,"Tshirt branca tamamho L",
        "Adidas",25, 1,Tshirt.Tamanho.L,Tshirt.Padrao.LISO);
        
        //u0001
        Artigo tshirt_1b = new Tshirt("Tshirt_1b",false,"bastante usado",5,"Tshirt vermelha às riscas tamanho M",
        "Pull&Bear",33,0.7,Tshirt.Tamanho.M,Tshirt.Padrao.RISCAS); 

        //0001
        Artigo tshirt_1c = new Tshirt("tshirt_1c", true, "Novo", 1, "Camiseta branca", 
        "Nike", 20.0, 1, Tshirt.Tamanho.M, Tshirt.Padrao.PALMEIRAS);

        //u0008
        Artigo tshirt_1d = new Tshirt("tshirt_1d", true, "Novo", 1, "Camiseta preta", 
        "Adidas", 25.0, 1.0, Tshirt.Tamanho.L, Tshirt.Padrao.LISO);
        
        //u0007
        Artigo tshirt_1e = new Tshirt("tshirt_1e", false, "Usado", 2, "Camiseta cinza", 
        "Puma", 18.0, 0.3, Tshirt.Tamanho.S, Tshirt.Padrao.RISCAS);

        //u0003
        Artigo tshirt_1f = new Tshirt("tshirt_1f", true, "Novo", 1, "Camiseta rosa", 
        "Reebok", 22.0, 1.0, Tshirt.Tamanho.M, Tshirt.Padrao.PALMEIRAS);

        //u0007
        Artigo tshirt_1g = new Tshirt("tshirt_1g", false, "Usado", 2, "Camiseta verde", 
        "Under Armour", 30.0, 0.5, Tshirt.Tamanho.L, Tshirt.Padrao.RISCAS);

        //Malas

        //u0006
        Artigo mala1a = new Mala("mala1a",false,"pouco usada",2,"mala amarela grande",
        "Zara",35, 0.8,Mala.Dimensao.GRANDE,"tecido","10-08-2021",false);

        //u0002
        Artigo mala1b = new Mala("mala1b",true,"novo",1,"mala preta pequena",
        "Prada",500,1,Mala.Dimensao.PEQUENO,"pele","10-05-2000",true);

        //u0004
        Artigo mala1c = new Mala("mala1c", true, "Novo", 1, "Mala de Viagem",
        "Samsonite", 200.0, 0.5, Mala.Dimensao.GRANDE, "Poliéster", "01-01-2023", true);

        //u0007
        Artigo mala1d = new Mala("mala1d", true, "Novo", 2, "Mala de Mão", 
        "Rimowa", 500.0, 1, Mala.Dimensao.PEQUENO, "Policarbonato", "01-04-2022", true);
        
        //u0006
        Artigo mala1e = new Mala("mala1e", false, "Usado", 3, "Mala de Viagem",
        "Louis Vuitton", 1000.0, 0.3, Mala.Dimensao.MEDIO, "Couro", "01-09-2022", false);

        //u0004
        Artigo mala1f = new Mala("mala1f", false, "Usado", 1, "Mala de Mão", 
        "Gucci", 700.0, 0.6, Mala.Dimensao.GRANDE, "Couro", "01-06-2021", true);
        
        //u0007
        Artigo mala1g = new Mala("mala1g", true, "Novo", 2, "Mala de Mão",
        "Prada", 800.0, 1, Mala.Dimensao.MEDIO, "Couro", "01-03-2023", true);

        this.gestorArtigos.addArtigo(sap1a); //ficar no historico do Vintage
        this.gestorArtigos.addArtigo(sap1b);
        this.gestorArtigos.addArtigo(sap1c);
        this.gestorArtigos.addArtigo(sap1d);
        this.gestorArtigos.addArtigo(sap1e);
        this.gestorArtigos.addArtigo(sap1f);
        this.gestorArtigos.addArtigo(sap1g);
        this.gestorArtigos.addArtigo(tshirt_1a);
        this.gestorArtigos.addArtigo(tshirt_1b);
        this.gestorArtigos.addArtigo(tshirt_1c);
        this.gestorArtigos.addArtigo(tshirt_1d);
        this.gestorArtigos.addArtigo(tshirt_1e);
        this.gestorArtigos.addArtigo(tshirt_1f);
        this.gestorArtigos.addArtigo(tshirt_1g);
        this.gestorArtigos.addArtigo(mala1a);
        this.gestorArtigos.addArtigo(mala1b);
        this.gestorArtigos.addArtigo(mala1c);
        this.gestorArtigos.addArtigo(mala1d);
        this.gestorArtigos.addArtigo(mala1e);
        this.gestorArtigos.addArtigo(mala1f);
        this.gestorArtigos.addArtigo(mala1g);

        //Transportadoras
        Transportadora fedex = new Transportadora("Fedex",1,2,5);
        Transportadora DPD = new Transportadora("DPD",2,3,4);
        Transportadora DHL = new Transportadora("DHL",1,5,10);
        Transportadora Amazon_Logistics = new Transportadora("Amazon Logistics",3,7,12);
        Transportadora CTT_Expresso = new Transportadora("CTT Expresso", 5, 7, 10);

        this.gestorTransportadoras.addTransportadora(fedex);
        this.gestorTransportadoras.addTransportadora(DPD);
        this.gestorTransportadoras.addTransportadora(DHL);
        this.gestorTransportadoras.addTransportadora(CTT_Expresso);
        this.gestorTransportadoras.addTransportadora(Amazon_Logistics);

        //Utilizadores
        Utilizador u0001 = new Utilizador("u0001","u0001@gmail.com","Henrique Malheiro","Rua Braga Parque",
        967865421,new GestorArtigos(),new GestorArtigos(),new GestorArtigos(),fedex);
        u0001.artigo_aVenda(tshirt_1b);
        u0001.artigo_aVenda(sap1c);
        u0001.artigo_aVenda(tshirt_1c);

        Utilizador u0002 = new Utilizador("u0002","u0002@gmail.com","Carolina Melo","Rua de Baixo",
        973417390,new GestorArtigos(),new GestorArtigos(),new GestorArtigos(),DPD);
        u0002.artigo_aVenda(sap1a);
        u0002.artigo_aVenda(tshirt_1a);
        u0002.artigo_aVenda(sap1g);
        u0002.artigo_aVenda(mala1b);

        Utilizador u0003 = new Utilizador("u0003","u0003@gmail.com","Inês Rodrigues","Rua de Cima",
        628183821,new GestorArtigos(),new GestorArtigos(),new GestorArtigos(),Amazon_Logistics);
        u0003.artigo_aVenda(sap1b);
        u0003.artigo_aVenda(tshirt_1f);

        Utilizador u0004 = new Utilizador("u0004", "u0004@gmail.com", "John Smith", "123 Main St", 
        123456789, new GestorArtigos(), new GestorArtigos(), new GestorArtigos(), DHL);
        u0004.artigo_aVenda(mala1c);
        u0004.artigo_aVenda(mala1f);

        Utilizador u0005 = new Utilizador("u0005", "u0005@gmail.com", "Jane Doe", "456 Elm St",
        987654321, new GestorArtigos(), new GestorArtigos(), new GestorArtigos(), CTT_Expresso);
        u0005.artigo_aVenda(sap1e);
        u0005.artigo_aVenda(sap1f);

        Utilizador u0006 = new Utilizador("u0006", "u0006@gmail.com", "Mike Johnson", "789 Oak St", 
        246813579, new GestorArtigos(), new GestorArtigos(), new GestorArtigos(), Amazon_Logistics);
        u0006.artigo_aVenda(mala1e);
        u0006.artigo_aVenda(mala1a);

        Utilizador u0007 = new Utilizador("u0007", "u0007@gmail.com", "Sarah Lee", "101 Maple St",
        135792468, new GestorArtigos(), new GestorArtigos(), new GestorArtigos(), DHL);
        u0007.artigo_aVenda(mala1g);
        u0007.artigo_aVenda(tshirt_1e);
        u0007.artigo_aVenda(tshirt_1g);
        u0007.artigo_aVenda(mala1d);

        Utilizador u0008 = new Utilizador("u0008", "u0008@gmail.com", "Tom Brown", "246 Pine St", 
        369258147, new GestorArtigos(), new GestorArtigos(), new GestorArtigos(), fedex);
        u0008.artigo_aVenda(sap1d);
        u0008.artigo_aVenda(tshirt_1d);

        this.gestorUtilizadores.addUtilizador(u0001);
        this.gestorUtilizadores.addUtilizador(u0002);
        this.gestorUtilizadores.addUtilizador(u0003);
        this.gestorUtilizadores.addUtilizador(u0004);
        this.gestorUtilizadores.addUtilizador(u0005);
        this.gestorUtilizadores.addUtilizador(u0006);
        this.gestorUtilizadores.addUtilizador(u0007);
        this.gestorUtilizadores.addUtilizador(u0008);

        //Encomendas
        Encomenda encomenda1 = new Encomenda(new GestorArtigos(),Encomenda.DimensaoEmbalagem.pequeno,5,
        "05-07-2002","10-07-2002",Encomenda.Estado.entregue,new GestorUtilizadores(),u0001);
        encomenda1.addArtigo(sap1a,u0002,u0001); //u0002 vendeu a sap1a ao u0001

        Encomenda encomenda2 = new Encomenda(new GestorArtigos(),Encomenda.DimensaoEmbalagem.medio,20,
        "04-03-2023","10-10-2023",Encomenda.Estado.pendente,new GestorUtilizadores(),u0003);
        encomenda2.addArtigo(tshirt_1a,u0002,u0003);
        encomenda2.addArtigo(tshirt_1b,u0001,u0003);

        Encomenda encomenda3 = new Encomenda(new GestorArtigos(), Encomenda.DimensaoEmbalagem.pequeno, 5.0,
        "10-05-2023", "15-10-2023", Encomenda.Estado.pendente, new GestorUtilizadores(), u0002);
        encomenda3.addArtigo(mala1g, u0007, u0002);
        encomenda3.addArtigo(mala1c, u0004, u0002);

        Encomenda encomenda4 = new Encomenda(new GestorArtigos(), Encomenda.DimensaoEmbalagem.medio, 7.5, 
        "12-05-2023", "07-04-2023", Encomenda.Estado.entregue, new GestorUtilizadores(), u0001);
        encomenda4.addArtigo(sap1e, u0005, u0001);

        Encomenda encomenda5 = new Encomenda(new GestorArtigos(), Encomenda.DimensaoEmbalagem.grande, 10.0, 
        "15-05-2023", "20-05-2024", Encomenda.Estado.pendente, new GestorUtilizadores(), u0007);
        encomenda5.addArtigo(sap1g, u0002, u0007);

        Encomenda encomenda6 = new Encomenda(new GestorArtigos(), Encomenda.DimensaoEmbalagem.pequeno, 4.0, 
        "01-03-2023", "22-03-2023", Encomenda.Estado.entregue, new GestorUtilizadores(), u0008);
        encomenda6.addArtigo(sap1c, u0001, u0008);

        Encomenda encomenda7 = new Encomenda(new GestorArtigos(), Encomenda.DimensaoEmbalagem.medio, 6.0, 
        "01-05-2023", "25-09-2023", Encomenda.Estado.pendente, new GestorUtilizadores(), u0004);
        encomenda7.addArtigo(tshirt_1e, u0007, u0004);
        encomenda7.addArtigo(mala1e, u0006, u0004);

        this.gestorEncomendas.addEncomenda(encomenda1);
        this.gestorEncomendas.addEncomenda(encomenda2);
        this.gestorEncomendas.addEncomenda(encomenda3);
        this.gestorEncomendas.addEncomenda(encomenda4);
        this.gestorEncomendas.addEncomenda(encomenda5);
        this.gestorEncomendas.addEncomenda(encomenda6);
        this.gestorEncomendas.addEncomenda(encomenda7);
    }

    public Vintage() throws DateTimeException, AddException, RemoveException{
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
        sb.append("Transportadoras registadas: ").append(gestorTransportadoras.toString()+'\n');
        sb.append("Utilizadores registrados: ").append(gestorUtilizadores.toString());
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

    //addUtilizador
    public void addUtilizador(Utilizador utilizador) throws AddException{
        gestorUtilizadores.addUtilizador(utilizador);
    }
}