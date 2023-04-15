import java.time.LocalDate;
import java.time.Period;

public class Sapatilha extends Artigo{
    private int tamanho;
    private boolean atacadores;
    private String cor;
    private LocalDate data_lancamento; //Passada como argumento em String "XXXX-XX-XX"
    private boolean premium;
    private double desconto;
    private double preco_final;

    public Sapatilha(){
        super();
        this.tamanho=0;
        this.atacadores=false;
        this.cor="";
        this.data_lancamento= LocalDate.now();
        this.premium=false;
        this.desconto=0;
        this.preco_final=this.total_pagar();
    }
    public Sapatilha(String cod_barras, boolean artigo_novo, String estado, int num_donos,
    String descricao, String marca,double preco_base,double estado_utilizacao,int tamanho,boolean atacadores,String cor,
    boolean premium,String data_colecao){
        super(cod_barras,artigo_novo,estado,num_donos,descricao,marca,preco_base,estado_utilizacao);
        this.tamanho=tamanho;
        this.atacadores=atacadores;
        this.cor=cor;
        this.data_lancamento=LocalDate.parse(data_colecao);
        this.premium=premium;
        this.desconto=this.desconto();
        this.preco_final=this.total_pagar();
    }

    public Sapatilha(Sapatilha novo){
        super(novo);
        this.tamanho=novo.getTamanho();
        this.atacadores=novo.getAtacadores();
        this.cor=novo.getCor();
        this.data_lancamento=novo.getData_lancamento();
        this.premium=novo.getPremium();
        this.desconto=novo.getDesconto();
        this.preco_final=novo.getPreco_final();
    }

    //get
    public int getTamanho(){
        return this.tamanho;
    }
    public boolean getAtacadores(){
        return this.atacadores;
    }
    public String getCor(){
        return this.cor;
    }
    public LocalDate getData_lancamento(){
        return this.data_lancamento;
    }
    public boolean getPremium(){
        return this.premium;
    }
    public double getDesconto(){
        return this.desconto;
    }
    public double getPreco_final(){
        return preco_final;
    }

    //set
    public void setTamanho(int tamanho){
        this.tamanho = tamanho;
    }
    public void setAtacadores(boolean atacadores){
        this.atacadores=atacadores;
    }
    public void setCor(String cor){
        this.cor = cor;
    }
    public void setData_lancamento(LocalDate data_lancamento) {
        this.data_lancamento = data_lancamento;
    }
    public void serPremium(boolean premium){
        this.premium=premium;
    }
    public void setDesconto(double desconto){
        this.desconto = desconto;
    }
    public void setPreco_final(double preco_final){
        this.preco_final=preco_final;
    }

    @Override
    public Sapatilha clone(){
        return new Sapatilha(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this) 
           return true;
        if(obj==null || obj.getClass() != this.getClass()) 
           return false;
        Sapatilha sap = (Sapatilha) obj;
        return (super.equals(sap) && this.tamanho==sap.getTamanho() && 
        this.atacadores==sap.getAtacadores() && this.cor==sap.getCor() && 
        this.data_lancamento==sap.getData_lancamento() && this.desconto==sap.getDesconto()
        && this.premium==sap.getPremium() && this.preco_final==sap.getPreco_final()); 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("........SAPATILHA.........\n");
        sb.append("Tamanho: "+this.getTamanho()+"\n");
        sb.append("Atacadores: " + (this.getAtacadores() ? "Sim" : "Não") + "\n");
        sb.append("Cor: "+this.getCor()+"\n");
        sb.append("Data de lancamento: "+this.getData_lancamento()+"\n");
        sb.append("Desconto: "+this.getDesconto()+"\n");
        sb.append("Premium: " + (this.getPremium() ? "Sim" : "Não") + "\n");
        sb.append("Preco final: " + this.getPreco_final()+"\n");
        
        return sb.toString();
    }  

    private double desconto(){
        if(this.getPremium()){
            Period periodo = Period.between(LocalDate.now(),this.getData_lancamento());
            int diferencaAnos = periodo.getYears();
            return this.getPreco_base()*0.1*diferencaAnos;
        }else{
            if(this.getArtigo_novo()){
                if(this.getTamanho()>45){
                    return this.getPreco_base() * 0.3;
                } else {
                    return 0;
                }
            } else {
                return (this.getPreco_base() / this.getNum_donos()) * this.getEstado_utilizacao();
            }
        }
    }

    private double total_pagar(){
        return (this.getAtacadores() ? this.getPreco_base()+this.getDesconto() : this.getPreco_base()-this.getDesconto());
    }
    
    
}
