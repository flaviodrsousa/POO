import java.util.ArrayList;
import java.util.List;

public class Utilizador{
    private String codUtilizador;
    private String email;
    private String nome;
    private String morada;
    private int numFiscal;
    private Transportadora transportadora;

    private List<Artigo> historicoCompras; //Historico de artigos comprados
    private List<Artigo> historicoVendas; //Historico de artigos vendidos
    private List<Artigo> aVenda; //Artigos ainda à venda.
    
    //Construtores
    public Utilizador(){
        this.codUtilizador="";
        this.email="";
        this.nome="";
        this.morada="";
        this.numFiscal=0;
        this.transportadora=new Transportadora();
        this.historicoCompras= new ArrayList<>();
        this.historicoVendas= new ArrayList<>();
        this.aVenda= new ArrayList<>();
    }

    public Utilizador(String codUtilizador,String email,String nome,String morada,
    int numFiscal,List<Artigo> historicoCompras,List<Artigo> historicoVendas,
    List<Artigo> aVenda,Transportadora transportadora){
        this.codUtilizador=codUtilizador;
        this.email=email;
        this.nome=nome;
        this.morada=morada;
        this.numFiscal=numFiscal;
        this.transportadora=transportadora.clone();

        if (historicoCompras!=null){
            this.historicoCompras= new ArrayList<>();
            for (Artigo artigo:historicoCompras){
                this.historicoCompras.add(artigo.clone());
            }    
        }else this.historicoCompras=new ArrayList<>();

        if (historicoVendas!=null){
            this.historicoVendas= new ArrayList<>();
            for (Artigo artigo:historicoVendas){
                this.historicoVendas.add(artigo.clone());
            }
        }else this.historicoVendas=new ArrayList<>();

        if (aVenda!=null){
            this.aVenda= new ArrayList<>();
            for (Artigo artigo:aVenda){
                this.aVenda.add(artigo.clone());
            }
        }else this.aVenda=new ArrayList<>();
    }

    public Utilizador(Utilizador umUtilizador){
        this.codUtilizador=umUtilizador.getCodUtilizador();
        this.email=umUtilizador.getEmail();
        this.nome=umUtilizador.getNome();
        this.morada=umUtilizador.getMorada();
        this.numFiscal=umUtilizador.getNumFiscal();
        this.historicoCompras=umUtilizador.getHistoricoCompras();
        this.historicoVendas=umUtilizador.getHistoricoVendas();
        this.aVenda=umUtilizador.getaVenda();
        this.transportadora=umUtilizador.getTransportadora();
    }

    //gets
    public String getCodUtilizador() {
        return this.codUtilizador;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome() {
        return this.nome;
    }

    public String getMorada() {
        return this.morada;
    }

    public int getNumFiscal() {
        return this.numFiscal;
    }

    public List<Artigo> getHistoricoCompras() {
        List<Artigo> new_Historico = new ArrayList<>();
        for (Artigo artigo:this.historicoCompras){
            new_Historico.add(artigo.clone());
        }
        return new_Historico;
    }

    public List<Artigo> getHistoricoVendas() {
        List<Artigo> new_Historico = new ArrayList<>();
        for (Artigo artigo:this.historicoVendas){
            new_Historico.add(artigo.clone());
        }
        return new_Historico;
    }

    public List<Artigo> getaVenda() {
        List<Artigo> new_aVenda = new ArrayList<>();
        for (Artigo artigo:this.aVenda){
            new_aVenda.add(artigo.clone());
        }
        return new_aVenda;
    }

    public Transportadora getTransportadora() {
        return this.transportadora.clone();
    }

    //sets
    public void setCodUtilizador(String codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setNumFiscal(int numFiscal) {
        this.numFiscal = numFiscal;
    }

    public void setHistoricoCompras(List<Artigo> historicoCompras) {
        this.historicoCompras=new ArrayList<Artigo>();
        for(Artigo artigo:historicoCompras){
            this.historicoCompras.add(artigo.clone());
        }
    }

    public void setHistoricoVendas(List<Artigo> hissetHistoricoV) {
        this.historicoVendas=new ArrayList<Artigo>();
        for(Artigo artigo:historicoVendas){
            this.historicoVendas.add(artigo.clone());
        }
    }

    public void setaVenda(List<Artigo> aVenda) {
        this.aVenda=new ArrayList<Artigo>();
        for(Artigo artigo:aVenda){
            this.aVenda.add(artigo.clone());
        }
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora.clone();
    }

    //clone
    public Utilizador clone(){
        return new Utilizador(this);
    } 

    //equals
    public boolean equals(Object o){
        if (this==o) 
            return true;
        if ((o==null) || (this.getClass() != o.getClass())) 
            return false;
        Utilizador utilizador = (Utilizador) o;
        return (utilizador.getCodUtilizador().equals(this.codUtilizador) && 
        utilizador.getEmail().equals(this.email) && 
        utilizador.getNome().equals(this.nome) &&
        utilizador.getMorada().equals(this.morada) &&
        utilizador.getNumFiscal()==this.numFiscal &&
        utilizador.getHistoricoCompras().equals(this.historicoCompras) &&
        utilizador.getHistoricoVendas().equals(this.historicoVendas) &&
        utilizador.getaVenda().equals(this.aVenda) &&
        utilizador.getTransportadora().equals(this.transportadora));
    }

    //toString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("........Utilizador......\n");
        sb.append("Código de Utilizador: "+this.getCodUtilizador()+"\n");
        sb.append("Email: "+this.getEmail()+"\n");
        sb.append("Nome: "+this.getNome()+"\n");
        sb.append("Morada: "+this.getMorada()+"\n");
        sb.append("Número Fiscal "+this.getNumFiscal()+"\n");
        sb.append("Histórico Compras: "+this.getHistoricoCompras().toString()+"\n");
        sb.append("Histórico Vendas: "+this.getHistoricoVendas().toString()+"\n");
        sb.append("À Venda: "+this.getaVenda().toString()+"\n");
        sb.append(this.getTransportadora()+"\n");

        return sb.toString();
    }

    //Publicar artigos à venda
    public void artigo_aVenda(Artigo artigo){
        this.aVenda.add(artigo.clone());
    }

    //Artigo vendido por um Vendedor
    public void artigo_Vendido(Artigo artigo){
        this.aVenda.remove(artigo);
        this.historicoVendas.add(artigo.clone());
    }

    //Artigo comprado por um Comprador
    public void artigo_Comprado(Artigo artigo){
        this.historicoCompras.add(artigo.clone());
    }
}