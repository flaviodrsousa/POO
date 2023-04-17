import java.time.LocalDate;
import java.time.Period;
import java.awt.Color;

public class Sapatilha extends Artigo{
    private int tamanho;
    private boolean atacadores;
    private Color cor;//Usar tuplo para RGB
    private LocalDate data_lancamento; //Passada como argumento em String "XXXX-XX-XX"
    private boolean premium;

    public Sapatilha(){
        super();
        this.tamanho=0;
        this.atacadores=false;
        this.cor=new Color(0,0,0);
        this.data_lancamento= LocalDate.now();
        this.premium=false;
    }
    public Sapatilha(String cod_barras, boolean artigo_novo, String estado, int num_donos,
    String descricao, String marca,double preco_base,double estado_utilizacao,int tamanho,boolean atacadores,Color cor,
    boolean premium,String data_colecao){
        super(cod_barras,artigo_novo,estado,num_donos,descricao,marca,preco_base,estado_utilizacao);
        this.tamanho=tamanho;
        this.atacadores=atacadores;
        this.cor=cor;
        this.data_lancamento=LocalDate.parse(data_colecao);
        this.premium=premium;
    }

    public Sapatilha(Sapatilha novo){
        super(novo);
        this.tamanho=novo.getTamanho();
        this.atacadores=novo.getAtacadores();
        this.cor=novo.getCor();
        this.data_lancamento=novo.getData_lancamento();
        this.premium=novo.getPremium();
    }

    //get
    public int getTamanho(){
        return this.tamanho;
    }
    public boolean getAtacadores(){
        return this.atacadores;
    }
    public Color getCor(){
        return this.cor;
    }
    public LocalDate getData_lancamento(){
        return this.data_lancamento;
    }
    public boolean getPremium(){
        return this.premium;
    }

    //set
    public void setTamanho(int tamanho){
        this.tamanho = tamanho;
    }
    public void setAtacadores(boolean atacadores){
        this.atacadores=atacadores;
    }
    public void setCor(Color cor){
        this.cor = cor;
    }
    public void setData_lancamento(LocalDate data_lancamento) {
        this.data_lancamento = data_lancamento;
    }
    public void serPremium(boolean premium){
        this.premium=premium;
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
        this.data_lancamento==sap.getData_lancamento() && this.premium==sap.getPremium() ); 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("........SAPATILHA.........\n");
        sb.append("Tamanho: "+this.getTamanho()+"\n");
        sb.append("Atacadores: " + (this.getAtacadores() ? "Sim" : "Não") + "\n");
        sb.append("Cor: ("+cor.getRed() + "," + cor.getGreen() + "," + cor.getBlue()+")\n");
        sb.append("Data de lancamento: "+this.getData_lancamento()+"\n");
        sb.append("Premium: " + (this.getPremium() ? "Sim" : "Não") + "\n");
       
        return sb.toString();
    }  

    public double desconto(){
        if(this.getPremium()){
            Period periodo = Period.between(LocalDate.now(),this.getData_lancamento());
            int diferencaAnos = periodo.getYears();
            return this.getPreco_base()*0.1*diferencaAnos;
        }else{
            if(super.getArtigo_novo()){
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

    public double preco_final(){
        return (this.getAtacadores() ? this.getPreco_base()+this.getDesconto() : this.getPreco_base()-this.getDesconto());
    }
    
    
}
