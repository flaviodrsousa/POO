public class Tshirt extends Artigo{
    private int tamanho; //0,1,2,3 = S/M/L/XL
    private int padrao;//0,1,2 = liso,riscas,palmeiras
    private double desconto;
    private double preco_final;

    public Tshirt(){
        super();
        this.tamanho=0;
        this.padrao=0;
        this.desconto=0;
        this.preco_final=0;
    }
    public Tshirt(String cod_barras, boolean artigo_novo, String estado, int num_donos,
    String descricao, String marca,double preco_base,double estado_utilizacao,
    int tamanho,int padrao){
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
    public int getTamanho(){
        return this.tamanho;
    }
    public int getPadrao(){
        return padrao;
    }
    public double getDesconto(){
        return this.desconto;
    }
    public double getPreco_final(){
        return this.preco_final;
    }

    //sets
    public void setTamanho(int tamanho){
        this.tamanho = tamanho;
    }
    public void setPadrao(int padrao){
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
        
        int tam=this.getTamanho();
        if(tam==0){
            sb.append("Tamanho: S"+"\n");
        }else if(tam==1){
            sb.append("Tamanho: M"+"\n");
        }else if(tam==2){
            sb.append("Tamanho: L"+"\n");
        }else{
            sb.append("Tamanho: XL"+"\n");
        }

        int padrao=this.getPadrao();
        if(padrao==0){
            sb.append("Padrao: Liso"+"\n");
        }else if(padrao==1){
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