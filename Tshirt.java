enum Tamanho{
    S,M,L,XL
}
enum Padrao{
    LISO,RICAS,PALMEIRAS
}

public class Tshirt extends Artigo{
    private Tamanho tamanho;
    private Padrao padrao;
    private double desconto;
    private double preco_final;

    public Tshirt(){
        super();
        this.tamanho=Tamanho.S;
        this.padrao=Padrao.LISO;
        this.desconto=0;
        this.preco_final=0;
    }
    public Tshirt(String cod_barras, boolean artigo_novo, String estado, int num_donos,
    String descricao, String marca,double preco_base,double estado_utilizacao,
    Tamanho tamanho,Padrao padrao){
        super(cod_barras,artigo_novo,estado,num_donos,descricao,marca,preco_base,estado_utilizacao);
        this.tamanho=tamanho;
        this.padrao=padrao;
        this.desconto=this.desconto();
        this.preco_final=this.preco_final();

    }

    public Tshirt(Tshirt novo){
        super(novo);
        this.tamanho=novo.getTamanho();
        this.padrao=novo.getPadrao();
        this.desconto=novo.getDesconto();
        this.preco_final=novo.preco_final;
    }

    //gets
    public Tamanho getTamanho(){
        return this.tamanho;
    }
    public Padrao getPadrao(){
        return this.padrao;
    }
    public double getDesconto(){
        return this.desconto;
    }
    public double getPreco_final(){
        return this.preco_final;
    }

    //sets
    public void setTamanho(Tamanho tamanho){
        this.tamanho = tamanho;
    }
    public void setPadrao(Padrao padrao){
        this.padrao = padrao;
    }
    public void setDesconto(double desconto){
        this.desconto = desconto;
    }
    public void setPreco_final(double preco_final){
        this.preco_final = preco_final;
    }

    @Override
    public Tshirt clone(){
        return new Tshirt(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this) 
           return true;
        if(obj==null || obj.getClass() != this.getClass()) 
           return false;
        Tshirt tsh = (Tshirt) obj;
        return (super.equals(tsh) && this.tamanho==tsh.getTamanho() && 
        this.padrao==tsh.getPadrao() && this.desconto==tsh.getDesconto() &&
        this.preco_final==tsh.getPreco_final()); 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("........T-SHIRT.........\n");
        
        Tamanho tam=this.getTamanho();
        if(tam==Tamanho.S){
            sb.append("Tamanho: S"+"\n");
        }else if(tam==Tamanho.M){
            sb.append("Tamanho: M"+"\n");
        }else if(tam==Tamanho.L){
            sb.append("Tamanho: L"+"\n");
        }else{
            sb.append("Tamanho: XL"+"\n");
        }

        Padrao padrao=this.getPadrao();
        if(padrao==Padrao.LISO){
            sb.append("Padrao: Liso"+"\n");
        }else if(padrao==Padrao.RICAS){
            sb.append("Padrao: Riscas"+"\n");
        }else{
            sb.append("Tamanho: Palmeiras"+"\n");
        }

        sb.append("Desconto: "+this.getDesconto()+"\n");
        sb.append("Preco final: "+this.getPreco_final()+"\n");

        return sb.toString();
    }  

    private double desconto(){
        if(this.getArtigo_novo()) return 0;
        return this.getPreco_base()*0.5; 
    }
    private double preco_final(){
        return this.getPreco_base()-this.getDesconto();
    }


}