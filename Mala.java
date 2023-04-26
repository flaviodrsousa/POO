import java.util.Date;

public class Mala extends Artigo{
    enum Dimensao{
        PEQUENO,MEDIO,GRANDE
    }

    private Dimensao dimensao;
    private String material;
    private Date data_colecao;
    private boolean premium;

    public Mala(){
        super();
        this.dimensao=Dimensao.PEQUENO;
        this.material="";
        this.data_colecao=new Date();
        this.premium=false;
    }

    public Mala(String cod_barras, boolean artigo_novo, String estado, int num_donos,
    String descricao, String marca,double preco_base,double estado_utilizacao,
    Dimensao dimensao,String material,String data_colecao,boolean premium){
        super(cod_barras,artigo_novo,estado,num_donos,descricao,marca,preco_base,estado_utilizacao);
        this.dimensao=dimensao;
        this.material=material;
        this.data_colecao=Data.StringtoDate(data_colecao);
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

    public Date getData_colecao(){
        Date new_Date = new Date(this.data_colecao.getTime());
        return new_Date;
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

    public void setData_colecao(Date data_colecao){
        this.data_colecao = new Date(data_colecao.getTime());
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
        this.material.equals(mal.getMaterial()) && this.data_colecao.equals(mal.getData_colecao()) &&
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
            Date dataAtual = new Date();
            long diffInMilliseconds = Math.abs(dataAtual.getTime() - this.getData_colecao().getTime());
            long diffInYears = diffInMilliseconds / (1000 * 60 * 60 * 24 * 365);
            return this.getPreco_base()*0.1*diffInYears;
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