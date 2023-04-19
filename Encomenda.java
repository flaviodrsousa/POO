import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum DimensaoEmbalagem{
    pequeno,medio,grande
}

enum Estado{
    pendente,paga,expedida,entregue
}

public class Encomenda{
    
    private List<Artigo> artigos;
    private DimensaoEmbalagem dimensaoEmbalagem; 
    private double taxaGarantia;
    private double custoExpedicao;
    private double precoFinal;
    private Date dataCriacao;
    private Date dataEntrega;
    private boolean paga;
    private boolean expedida;
    private Estado estado;
    private Utilizador vendedor;
    private Utilizador comprador;
    private Transportadora transportadora;
    
    //Construtores
    public Encomenda() {
        this.artigos = new ArrayList<>();
        this.dimensaoEmbalagem = DimensaoEmbalagem.pequeno;
        this.taxaGarantia = 0;
        this.custoExpedicao = 0;
        this.precoFinal = 0;
        this.dataCriacao = new Date();
        this.dataEntrega = null;
        this.paga = false;
        this.expedida = false;
        this.vendedor=null;
        this.comprador=null;
        this.transportadora=null;
    }

    private double calcularPrecoFinal() {
        int quantidadeNovos = 0;
        int quantidadeUsados = 0;
        double precototal = 0;
        for (Artigo artigo : this.artigos) {
            if (artigo.getArtigo_novo()) {
                quantidadeNovos++;
            } else {
                quantidadeUsados++;
            }
            precototal += artigo.getPreco_base();
        }
        return ((precototal + (quantidadeNovos * 0.5) + (quantidadeUsados * 0.25)) + this.taxaGarantia + this.custoExpedicao);
    }
    private Estado definirEstado(Vintage data) {
          if(this.get_Paga() == false && this.get_Expedida() == false){
            return(this.estado = estado.pendente);
        }else{
            if(this.get_Paga() == true && this.get_Expedida() == false){
                return(this.estado = estado.paga);
            }else{
                if(this.get_DataEntrega() == null || (data.get_DataAtual().compareTo(this.get_DataEntrega()) < 0)) {
                    return(this.estado = estado.expedida);
                } else {
                    return(this.estado = estado.entregue);
                }          
            }      
        }
    }

    public Encomenda(List<Artigo> artigos,DimensaoEmbalagem dimensaoEmbalagem,double taxaGarantia,double custoExpedicao,
    double precoFinal,Date dataCriacao,Date dataEntrega,boolean paga, boolean expedida,Utilizador vendedor,
    Utilizador comprador){
        this.artigos= new ArrayList<>();
        for (Artigo artigo:artigos){
            this.artigos.add(artigo.clone());
        }

        this.dimensaoEmbalagem=dimensaoEmbalagem;
        this.taxaGarantia=taxaGarantia;

        if(dimensaoEmbalagem==DimensaoEmbalagem.pequeno){
            this.custoExpedicao=vendedor.getTransportadora().getPrecoExpPequena();
        }else if (dimensaoEmbalagem==DimensaoEmbalagem.medio){
            this.custoExpedicao=vendedor.getTransportadora().getPrecoExpMedia();
        }else{
            this.custoExpedicao=vendedor.getTransportadora().getPrecoExpGrande();
        }

        this.precoFinal=this.calcularPrecoFinal();
        this.dataCriacao=dataCriacao;
        this.dataEntrega=dataEntrega;
        this.paga=paga;
        this.expedida=expedida;
        this.vendedor=vendedor;
        this.comprador=comprador;
        this.transportadora=vendedor.getTransportadora(); //transportadora é definida por defeito pelo vendedor
    }

    public Encomenda(Encomenda umEncomenda){
        this.artigos=umEncomenda.getArtigos();
        this.dimensaoEmbalagem=get_DimensaoEmbalagem();
        this.taxaGarantia=umEncomenda.getTaxaGarantia();
        this.custoExpedicao=umEncomenda.getCustoExpedicao();
        this.precoFinal=umEncomenda.get_PrecoFinal();
        this.dataCriacao=umEncomenda.get_DataCriacao();
        this.dataEntrega=umEncomenda.get_DataEntrega();
        this.paga=umEncomenda.get_Paga();
        this.expedida=umEncomenda.get_Expedida();
        this.vendedor=umEncomenda.getVendedor();
        this.comprador=umEncomenda.getComprador();
        this.transportadora=umEncomenda.getTransportadora();
    }
    
    //gets
    public List<Artigo> getArtigos() {
        List<Artigo> new_Artigos = new ArrayList<>();
        for (Artigo artigo:this.artigos){
            new_Artigos.add(artigo.clone());
        }
        return new_Artigos;
    }

    public DimensaoEmbalagem get_DimensaoEmbalagem() {
        return this.dimensaoEmbalagem;
    }

    public double getTaxaGarantia() {
        return taxaGarantia;
    }

    public double getCustoExpedicao() {
        return custoExpedicao;
    }

    public double get_PrecoFinal() {
        return this.precoFinal;
    }

    public Date get_DataCriacao() {
        return this.dataCriacao;
    }

    public Date get_DataEntrega() {
        return this.dataEntrega;
    }

    public boolean get_Paga() {
        return this.paga;
    }

    public boolean get_Expedida() {
        return this.expedida;
    }

    public Utilizador getVendedor() {
        return this.vendedor.clone();
    }

    public Utilizador getComprador() {
        return this.comprador.clone();
    }

    public Transportadora getTransportadora() {
        return this.transportadora.clone();
    }

    //sets
    public void setArtigos(List<Artigo> artigos) {
        this.artigos=new ArrayList<Artigo>();
        for(Artigo artigo:artigos){
            this.artigos.add(artigo.clone());
        }
    }

    public void set_DimensaoEmbalagem(DimensaoEmbalagem dimensaoEmbalagem) {
        this.dimensaoEmbalagem = dimensaoEmbalagem;
    }

    public void set_TaxaGarantia(double taxaGarantia) {
        this.taxaGarantia = taxaGarantia;
    }

    public void set_CustoExpedicao(double custoExpedicao) {
        this.custoExpedicao = custoExpedicao;
    }

    public void set_PrecoFinal(int precoFinal) {
        this.precoFinal = precoFinal;
    }

    public void set_DataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void set_DataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    
    public void set_paga(boolean paga) {
        this.paga = paga;
    }

    public void set_expedida(boolean expedida) {
        this.expedida = expedida;
    }

    public void setVendedor(Utilizador vendedor) {
        this.vendedor = vendedor;
    }

    public void setComprador(Utilizador comprador) {
        this.comprador = comprador;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    //clone
    public Encomenda clone(){
        return new Encomenda(this);
    }

    //equals
    public boolean equals(Object o){
        if (this==o) 
            return true;
        if ((o==null) || (this.getClass() != o.getClass())) 
            return false;
        Encomenda encomenda = (Encomenda) o;
        return (encomenda.getArtigos().equals(this.artigos) &&
        encomenda.get_DimensaoEmbalagem()==this.dimensaoEmbalagem &&
        encomenda.getTaxaGarantia()==this.getTaxaGarantia() &&
        encomenda.getCustoExpedicao()==this.custoExpedicao &&
        encomenda.get_PrecoFinal()==this.precoFinal &&
        encomenda.get_DataCriacao().equals(this.dataCriacao) &&
        encomenda.get_DataEntrega().equals(this.dataEntrega) &&
        encomenda.get_Paga()==this.paga &&
        encomenda.get_Expedida()==this.expedida &&
        encomenda.getVendedor().equals(this.vendedor) &&
        encomenda.getComprador().equals(this.comprador) &&
        encomenda.getTransportadora().equals(this.transportadora));
    }

    //toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("........Encomenda.........\n");
        for (Artigo artigo:artigos){
            sb.append(artigo.toString());
        }
        if(this.get_DimensaoEmbalagem() == DimensaoEmbalagem.pequeno){
            sb.append("DimensãoEmbalagem: Pequena\n");
        }else if(this.get_DimensaoEmbalagem()==DimensaoEmbalagem.medio){
            sb.append("DimensãoEmbalagem: Média\n");
        }else{
            sb.append("DimensãoEmbalagem: Grande\n");
        }
        sb.append("Vendedor: "+this.getVendedor()+"\n");
        sb.append("Comprador: "+this.getComprador()+"\n");
        sb.append("Transportadora: "+this.getTransportadora()+"\n");
        if(this.get_Paga() == false && this.get_Expedida() == false){
            sb.append("Estado: Pendente\n");
        }else{
            if(this.get_Paga() == true && this.get_Expedida() == false){
                sb.append("Estado: Paga\n");
            }else{
                sb.append("Estado: Expedida\n");
            }
        }
        sb.append("DataCriação: "+this.get_DataCriacao()+"\n");
        sb.append("DataEntrega: "+this.get_DataEntrega()+"\n");
        if(this.get_Expedida() == false) {
            sb.append("Prazo de Devolução: Encomenda ainda não foi enviada\n");
            }else{
                if(isDentroDoPrazoDevolucao()){
                    sb.append("Prazo de Devolução: No prazo\n");
                }else{
                    sb.append("Prazo de Devolução: Fora do Prazo\n");
                }
            }
        sb.append("PrecoTotal: "+this.get_PrecoFinal()+"\n");
        return sb.toString();
    }

    //métodos adicionais
    public void pagar() {
        this.paga = true;
    }
    
    public void expedir() {
        this.expedida = true;
    }
    
    public void entregar() {
        this.dataEntrega = new Date();
    }
    
    public boolean foi_Entregue() {
        return this.dataEntrega != null;
    }
    
    public boolean isDentroDoPrazoDevolucao() {
        if (this.dataEntrega != null) {
            long diferencaMilissegundos = this.dataEntrega.getTime() - this.dataCriacao.getTime();
            long diferencaHoras = diferencaMilissegundos / (60 * 60 * 1000);
            return diferencaHoras < 48;
        } else {
            return false;
        }
    }
}
