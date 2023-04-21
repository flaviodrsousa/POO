import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Encomenda{
    public enum DimensaoEmbalagem{
        pequeno,medio,grande
    }
    
    public enum Estado{
        pendente,paga,expedida,entregue
    }

    private int numeroEncomenda;
    private List<Artigo> artigos;
    private DimensaoEmbalagem dimensaoEmbalagem; 
    private double taxaGarantia;
    private double custoExpedicao;
    private double precoFinal;
    private Date dataCriacao;
    private Date dataEntrega;
    private Estado estado;
    private Utilizador vendedor;
    private Utilizador comprador;
    private Transportadora transportadora;

    private static int contador = 1;

    //Construtores
    public Encomenda() {
        this.numeroEncomenda=contador++;
        this.artigos = new ArrayList<>();
        this.dimensaoEmbalagem = DimensaoEmbalagem.pequeno;
        this.taxaGarantia = 0;
        this.custoExpedicao = 0;
        this.precoFinal = 0;
        this.dataCriacao = new Date();
        this.dataEntrega = null;
        this.estado=null;
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
            }else {
                quantidadeUsados++;
            }
            precototal += artigo.getPreco_base();
        }
        return ((precototal + (quantidadeNovos * 0.5) + (quantidadeUsados * 0.25)) + this.taxaGarantia + this.custoExpedicao);
    }

    public Encomenda(List<Artigo> artigos,DimensaoEmbalagem dimensaoEmbalagem,double taxaGarantia,double custoExpedicao,
    double precoFinal,Date dataCriacao,Date dataEntrega,Date data_atual,Estado estado,
    Utilizador vendedor,Utilizador comprador){
        this.numeroEncomenda=contador++;

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
        this.estado=estado;
        this.vendedor=vendedor;
        this.comprador=comprador;
        this.transportadora=vendedor.getTransportadora(); //transportadora é definida por defeito pelo vendedor
    }

    public Encomenda(Encomenda umEncomenda){
        this.numeroEncomenda=umEncomenda.getNumeroEncomenda();
        this.artigos=umEncomenda.getArtigos();
        this.dimensaoEmbalagem=get_DimensaoEmbalagem();
        this.taxaGarantia=umEncomenda.getTaxaGarantia();
        this.custoExpedicao=umEncomenda.getCustoExpedicao();
        this.precoFinal=umEncomenda.get_PrecoFinal();
        this.dataCriacao=umEncomenda.get_DataCriacao();
        this.dataEntrega=umEncomenda.get_DataEntrega();
        this.estado=umEncomenda.getEstado();
        this.vendedor=umEncomenda.getVendedor();
        this.comprador=umEncomenda.getComprador();
        this.transportadora=umEncomenda.getTransportadora();
    }
    
    //gets
    public int getNumeroEncomenda() {
        return this.numeroEncomenda;
    }

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
        return this.taxaGarantia;
    }

    public double getCustoExpedicao() {
        return this.custoExpedicao;
    }

    public double get_PrecoFinal() {
        return this.precoFinal;
    }

    public Date get_DataCriacao() {
        Date newDate = new Date(this.dataCriacao.getTime());
        return newDate;
    }

    public Date get_DataEntrega() {
        Date newDate = new Date(this.dataEntrega.getTime());
        return newDate;
    }

    public Estado getEstado() {
        return this.estado;
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
    public void setNumeroEncomenda(int numeroEncomenda) {
        this.numeroEncomenda = numeroEncomenda;
    }

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

    public void setEstado(Estado estado) {
        this.estado = estado;
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
        return (encomenda.getNumeroEncomenda()==this.numeroEncomenda &&
        encomenda.getArtigos().equals(this.artigos) &&
        encomenda.get_DimensaoEmbalagem()==this.dimensaoEmbalagem &&
        encomenda.getTaxaGarantia()==this.getTaxaGarantia() &&
        encomenda.getCustoExpedicao()==this.custoExpedicao &&
        encomenda.get_PrecoFinal()==this.precoFinal &&
        encomenda.get_DataCriacao().equals(this.dataCriacao) &&
        encomenda.get_DataEntrega().equals(this.dataEntrega) &&
        encomenda.getEstado()==this.estado &&
        encomenda.getVendedor().equals(this.vendedor) &&
        encomenda.getComprador().equals(this.comprador) &&
        encomenda.getTransportadora().equals(this.transportadora));
    }

    //toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("........Encomenda.........\n");
        sb.append("Numero de Encomenda: "+this.getNumeroEncomenda()+'\n');
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
        sb.append("DataCriação: "+this.get_DataCriacao()+"\n");
        sb.append("DataEntrega: "+this.get_DataEntrega()+"\n");
        sb.append("Estado: "+this.getEstado()+'\n');
        if(this.getEstado() == Estado.entregue) {
            if(isDentroDoPrazoDevolucao()){
                sb.append("Prazo de Devolução: No prazo\n");
            }else{
                sb.append("Prazo de Devolução: Prazo de devolução já expirou\n");
            }
        }
        sb.append("PrecoTotal: "+this.get_PrecoFinal()+"\n");
        return sb.toString();
    }

    //métodos adicionais    
    public void entregar() {
        this.dataEntrega = new Date();
    }

    public Estado atualizarEstado(Date data) { //recebe a data atual do sistema e atualiza se
        if(data.compareTo(this.get_DataEntrega()) > 0) {
            return(this.estado = Estado.entregue);
        }else{
            return(this.estado);
        }              
    }
    
    public boolean isDentroDoPrazoDevolucao() {
        if (this.getEstado()==Estado.entregue) {
            long diferencaMilissegundos = this.dataEntrega.getTime() - this.dataCriacao.getTime();
            long diferencaHoras = diferencaMilissegundos / (60 * 60 * 1000);
            return diferencaHoras < 48;
        }else {
            return false;
        }
    }
}
