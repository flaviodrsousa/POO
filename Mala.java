import java.time.LocalDate;
import java.time.Period;

enum Dimensao{
    PEQUENO,MEDIO,GRANDE
}

public class Mala extends Artigo{
    private Dimensao dimensao;
    private String material;
    private LocalDate data_colecao;
    private boolean premium;

    public Mala(){
        super();
        this.dimensao=Dimensao.PEQUENO;
        this.material="";
        this.data_colecao=LocalDate.now();
        this.premium=false;
    }

    public Mala(String cod_barras, boolean artigo_novo, String estado, int num_donos,
    String descricao, String marca,double preco_base,double estado_utilizacao,
    Dimensao dimensao,String material,String data_colecao,boolean premium){
        super(cod_barras,artigo_novo,estado,num_donos,descricao,marca,preco_base,estado_utilizacao);
        this.dimensao=dimensao;
        this.material=material;
        this.data_colecao=LocalDate.parse(data_colecao);
        this.premium=premium;
    }

    public Mala(Mala novo){
        super(novo);
        this.dimensao=this.getDimensao();
        this.material=this.getMaterial();
        this.data_colecao=this.getData_colecao();
        this.premium=this.getPremium();
    }

    //gets
    public Dimensao getDimensao(){
        return this.dimensao;
    }
    public String getMaterial(){
        return this.material;
    }
    public LocalDate getData_colecao(){
        return this.data_colecao;
    }
    public boolean getPremium(){
        return this.premium;
    }

    //set
    public void setDimensao(Dimensao dimensao){
        this.dimensao = dimensao;
    }
    public void setMaterial(String material){
        this.material = material;
    }
    public void setData_colecao(LocalDate data_colecao){
        this.data_colecao = data_colecao;
    }
    public void setPremium(boolean premium){
        this.premium=premium;
    }

    @Override
    public Mala clone(){
        return new Mala(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this) 
           return true;
        if(obj==null || obj.getClass() != this.getClass()) 
           return false;
        Mala mal = (Mala) obj;
        return (super.equals(mal) && this.dimensao==mal.getDimensao() && 
        this.material.equals(mal.getMaterial()) && this.data_colecao==mal.getData_colecao() &&
        this.premium==mal.getPremium()); 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("........MALA.........\n");
        Dimensao dim=this.getDimensao();
        if(dim==Dimensao.PEQUENO){
            sb.append("Tamanho: Pequeno"+"\n");
        }else if(dim==Dimensao.MEDIO){
            sb.append("Tamanho: Medio"+"\n");
        }else{
            sb.append("Tamanho: Grande"+"\n");
        }
        sb.append("Material: "+this.getMaterial()+"\n");
        sb.append("Data da colecao: "+this.getData_colecao()+"\n");
        sb.append("Premium: " + (this.getPremium() ? "Sim" : "Não") + "\n");

        return sb.toString();
    }  


    public double desconto(){
        if(this.getPremium()){
            Period periodo = Period.between(LocalDate.now(),this.getData_colecao());
            int diferencaAnos = periodo.getYears();
            return this.getPreco_base()*0.1*diferencaAnos;
        }else{
            Dimensao dim=this.getDimensao();
            if(dim==Dimensao.PEQUENO){
                return (1/0.1)*this.getPreco_base();
            }else if(dim==Dimensao.MEDIO){
                return (1/0.25)*this.getPreco_base();
            }else{
                return (1/0.5)*this.getPreco_base();
            }
        }
    }

    public double preco_final(){
        if(this.getPremium()){
            return this.getPreco_base()+this.getDesconto();
        }else{
            return this.getPreco_base()-this.getDesconto();
        }
    }

}