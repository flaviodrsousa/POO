public abstract class Artigo{
    private String cod_barras;
    private boolean artigo_novo; //true=novo;false=velho;

    //....Em caso de ser usado........
    private String estado; //Valor de 0 a 1 que corresponde a percentagem
    private int num_donos;
    //................................

    private String descricao;
    private String marca;
    private double preco_base;
    private double estado_utilizacao; //valor de 0 a 1
    private double desconto;
    private double preco_final;

    //contrutores
    public Artigo(){
        this.cod_barras="";
        this.artigo_novo=true;
        this.estado="";
        this.num_donos=0;
        this.descricao="";
        this.marca="";
        this.preco_base=0;
        this.estado_utilizacao=0;
        this.desconto=0;
        this.preco_final=0;

    }

    public Artigo(String cod_barras, boolean artigo_novo, String estado, int num_donos,
    String descricao, String marca,double preco_base,double estado_utilizacao){
        this.cod_barras=cod_barras;
        this.artigo_novo=artigo_novo;
        this.estado=estado;
        this.num_donos=num_donos;
        this.descricao=descricao;
        this.marca=marca;
        this.preco_base=preco_base;
        this.estado_utilizacao=estado_utilizacao;
        this.desconto=this.desconto();
        this.preco_final=this.preco_final();
    }

    public Artigo(Artigo novo){
        this.cod_barras=novo.getCod_barras();
        this.artigo_novo=novo.getArtigo_novo();
        this.estado=novo.getEstado();
        this.num_donos=novo.getNum_donos();
        this.descricao=novo.getDescricao();
        this.marca=novo.getMarca();
        this.preco_base=novo.getPreco_base();
        this.estado_utilizacao=novo.getEstado_utilizacao();
        this.desconto=this.desconto();
        this.preco_final=this.preco_final();
    }


    //gets
    public String getCod_barras() {
        return this.cod_barras;
    }
    public boolean getArtigo_novo(){
        return this.artigo_novo;
    }
    public String getEstado(){
        return this.estado;
    }
    public int getNum_donos(){
        return this.num_donos;
    }
    public String getDescricao(){
        return this.descricao;
    }
    public String getMarca(){
        return this.marca;
    }
    public double getPreco_base(){
        return this.preco_base;
    }
    public double getEstado_utilizacao(){
        return estado_utilizacao;
    }
    public double getDesconto(){
        return this.desconto;
    }
    public double getPreco_final(){
        return preco_final;
    }

    //sets
    public void setCod_barras(String cod_barras){
        this.cod_barras = cod_barras;
    }
    public void setArtigo_novo(boolean artigo_novo){
        this.artigo_novo = artigo_novo;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
    public void setNum_donos(int num_donos){
        this.num_donos = num_donos;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public void setPreco_base(double preco_base){
        this.preco_base = preco_base;
    }
    public void setEstado_utilizacao(double estado_utilizacao){
        this.estado_utilizacao = estado_utilizacao;
    }
    public void setDesconto(double desconto){
        this.desconto = desconto;
    }
    public void setPreco_final(double preco_final){
        this.preco_final=preco_final;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this) 
           return true;
        if(obj==null || obj.getClass() != this.getClass()) 
           return false;
        Artigo art = (Artigo) obj;
        return (this.cod_barras==art.getCod_barras() && this.artigo_novo==art.getArtigo_novo() && 
        this.estado==art.getEstado() && this.num_donos==art.getNum_donos() && 
        this.descricao==art.getDescricao() && this.marca==art.getMarca() &&
        this.preco_base==art.getPreco_base() && this.estado_utilizacao==art.getEstado_utilizacao());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo de barras: "+this.getCod_barras()+"\n");
        if(this.getArtigo_novo()==false){
            sb.append("Usado/Novo: Usado\n");
            sb.append("Estado: "+this.getEstado()+"\n");
            sb.append("Numero de donos: "+this.getNum_donos()+"\n");
        }else{
            sb.append("Usado/Novo: Novo\n");
        }
        sb.append("Descricao: "+this.getDescricao()+"\n");
        sb.append("Marca: "+this.getMarca()+"\n");
        sb.append("Estado de utilizacao (0-1): "+this.getEstado_utilizacao()+"\n");
        sb.append("Preco base: "+this.getPreco_base()+"\n");
        sb.append("Desconto: "+this.getDesconto()+"\n");
        sb.append("Preco final: " + this.getPreco_final()+"\n");

        return sb.toString();
    }  

    public abstract Artigo clone();
    public abstract double desconto();
    public abstract double preco_final();
}