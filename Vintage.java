import java.awt.Color;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vintage{
    private Map<Integer,Encomenda> vendas; //map das diversas encomendas vendidas (chave é numeroEncomenda).
    private Date data_atual;

    //Construtores
    private void estadoInicial_Vintage(){
        this.data_atual = new Date();

        Artigo artigo1 = new Sapatilha("sap1a",true,"novo",1,"sapatilha vermelha num 45",
        "Nike",50.5,1,45,true,Color.RED,false,"10-05-2000");
        
        List<Artigo> listArtigos = new ArrayList<>();
        listArtigos.add(artigo1);

        Transportadora transportadora = new Transportadora("Fedex",10,20,50);

        Utilizador utilizador1 = new Utilizador("u0001","u0001@gmail.com","Henrique Malheiro","Rua Braga Parque",
        1000,listArtigos,null,null,transportadora);
        Utilizador utilizador2 = new Utilizador("u0002","u0002@gmail.com","Carolina Melo","Rua de Baixo",
        1005,null,listArtigos,null,transportadora);

        Encomenda encomenda = new Encomenda(listArtigos,Encomenda.DimensaoEmbalagem.pequeno,5,10,
        "05-07-2002","10-07-2002",Encomenda.Estado.entregue,utilizador1,utilizador2);

        this.vendas = new HashMap<>();
        this.addVendas(encomenda);
    }

    public Vintage(){
        this.estadoInicial_Vintage();
    }

    public Vintage(String data_atual,Map<Integer,Encomenda> vendas) {
        try{
            this.data_atual=Data.StringtoDate(data_atual);
        }catch (ParseException e){
            System.out.println(e.getMessage());
        }

        this.vendas = new HashMap<>();
        for (Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            this.vendas.put(entry.getKey(), entry.getValue().clone());
        }
    }

    public Vintage(Vintage vintage) {
        this.vendas = vintage.getVendas();
        this.data_atual=vintage.get_DataAtual();
    }

    //gets
    public Date get_DataAtual() {
        Date newDate = new Date(this.data_atual.getTime());
        return newDate;
    }

    public Map<Integer, Encomenda> getVendas() {
        Map<Integer,Encomenda> novo = new HashMap<>();
        for(Map.Entry<Integer,Encomenda> entry: this.vendas.entrySet()){
            novo.put(entry.getKey(),entry.getValue().clone());
        }
        return novo;
    }

    //sets
    public void set_DataAtual(Date data) {
        this.data_atual = new Date(data.getTime());
        this.entregaEncomenda(); //posso fazer isto?
    }

    private void entregaEncomenda(){
        for (Map.Entry<Integer,Encomenda> entry: this.vendas.entrySet()){
            if (this.data_atual.compareTo(entry.getValue().get_DataEntrega())>0){
                entry.getValue().setEstado(Encomenda.Estado.entregue);
            }
        }
    }

    public void setVendas(Map<Integer,Encomenda> vendas) {
        this.vendas=new HashMap<>();
        for(Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            this.vendas.put(entry.getKey(),entry.getValue().clone());
        }
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
        return (vintage.getVendas().equals(this.vendas) &&
        vintage.get_DataAtual().equals(this.data_atual));
    }

    //toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Encomendas Vendidas: ").append(vendas.toString());
        sb.append("Data atual: ").append(data_atual.toString());

        return sb.toString();
    }

    //Outros métodos
    public void addVendas(Encomenda encomenda){
        this.vendas.put(encomenda.getNumeroEncomenda(),encomenda.clone());
    }

    public void removeVendas(Encomenda encomenda){
        this.vendas.remove(encomenda.getNumeroEncomenda());
    }

    public String fatura(Encomenda encomenda){
        StringBuilder sb = new StringBuilder();
        sb.append(encomenda.toString());
        return sb.toString();
    }

    //Q3
    public void encomendasVendedor(Utilizador utilizador){
        for(Map.Entry<Integer,Encomenda> entry: this.vendas.entrySet()){
            if (entry.getValue().getVendedor().equals(utilizador)){
                System.out.println(entry.getValue().toString());
            }
        }
    }

    //Q5
    public double ganhosVintage(){
        int ganhosTotais=0;
        for(Map.Entry<Integer,Encomenda> entry: this.vendas.entrySet()){
            ganhosTotais+=entry.getValue().get_PrecoFinal();
        }

        return ganhosTotais;
    }
}