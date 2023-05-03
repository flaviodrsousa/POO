import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Encomenda implements Serializable{
    public enum DimensaoEmbalagem{
        pequeno,medio,grande
    }
    
    public enum Estado{
        pendente,paga,expedida,entregue
    }

    private int numeroEncomenda;
    private GestorArtigos gestorArtigos;
    private DimensaoEmbalagem dimensaoEmbalagem; 
    private double taxaGarantia; //preco pago no inicio, como uma garantia ao vendedor
    private double custoExpedicao;
    private double precoFinal;
    private LocalDate dataCriacao;
    private LocalDate dataEntrega;
    private Estado estado;
    private Utilizador vendedor;
    private Utilizador comprador;
    private Transportadora transportadora;

    private static int contador = 1;


    //Construtores
    public Encomenda() {
        this.numeroEncomenda=contador++;
        this.gestorArtigos = new GestorArtigos();
        this.dimensaoEmbalagem = DimensaoEmbalagem.pequeno;
        this.taxaGarantia = 0;
        this.custoExpedicao = 0;
        this.precoFinal = 0;
        this.dataCriacao = LocalDate.now();
        this.dataEntrega = null;
        this.estado=null;
        this.vendedor=null;
        this.comprador=null;
        this.transportadora=null;
    }

    private double calcularPrecoFinal() {
        return gestorArtigos.calcularPrecoFinalEncomenda(this);
    }


    public Encomenda(GestorArtigos gestorArtigos,DimensaoEmbalagem dimensaoEmbalagem,double taxaGarantia,
    double custoExpedicao,String dataCriacao,String dataEntrega,Estado estado,
    Utilizador vendedor,Utilizador comprador) throws DateTimeException{
        this.numeroEncomenda=contador++;
        this.gestorArtigos=gestorArtigos.clone();

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
        this.dataCriacao=Data.StringtoDate(dataCriacao);
        this.dataEntrega=Data.StringtoDate(dataEntrega);     
        this.estado=estado;
        this.vendedor=vendedor;
        this.comprador=comprador;
        this.transportadora=vendedor.getTransportadora(); //transportadora é definida por defeito pelo vendedor
    }

    public Encomenda(Encomenda umEncomenda){
        this.numeroEncomenda=umEncomenda.getNumeroEncomenda();
        this.gestorArtigos=umEncomenda.getGestorArtigos();
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

    public GestorArtigos getGestorArtigos() {
        return gestorArtigos.clone();
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

    public LocalDate get_DataCriacao() { 
        return LocalDate.of(dataCriacao.getYear(),
        dataCriacao.getMonth(),dataCriacao.getDayOfMonth());
    }

    public LocalDate get_DataEntrega() {
        return LocalDate.of(dataEntrega.getYear(),
        dataEntrega.getMonth(),dataEntrega.getDayOfMonth());
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

    public void setGestorArtigos(GestorArtigos gestorArtigos) {
        this.gestorArtigos = gestorArtigos.clone();
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

    public void set_DataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = LocalDate.of(dataCriacao.getYear(),
        dataCriacao.getMonth(),dataCriacao.getDayOfMonth());
    }

    public void set_DataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = LocalDate.of(dataEntrega.getYear(),
        dataEntrega.getMonth(),dataEntrega.getDayOfMonth());
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
        encomenda.getGestorArtigos().equals(this.gestorArtigos) &&
        encomenda.get_DimensaoEmbalagem()==this.dimensaoEmbalagem &&
        encomenda.getTaxaGarantia()==this.taxaGarantia &&
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
        sb.append(gestorArtigos.toString());
        if(this.get_DimensaoEmbalagem() == DimensaoEmbalagem.pequeno){
            sb.append("DimensãoEmbalagem: Pequena\n");
        }else if(this.get_DimensaoEmbalagem()==DimensaoEmbalagem.medio){
            sb.append("DimensãoEmbalagem: Média\n");
        }else{
            sb.append("DimensãoEmbalagem: Grande\n");
        }
        sb.append("Vendedor: \n"+this.getVendedor().toString()+"\n");
        sb.append("Comprador: \n"+this.getComprador().toString()+"\n");
        sb.append("Transportadora utilizada: \n"+this.getTransportadora().toString()+"\n");
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
    public String fatura(Encomenda encomenda){
        StringBuilder sb = new StringBuilder();
        sb.append(encomenda.toString());
        return sb.toString();
    }
    
    public void entregar() {
        this.dataEntrega = LocalDate.now();
    }

    public Estado atualizarEstado(LocalDate data) { //recebe a data atual do sistema e atualiza se
        if(data.compareTo(this.get_DataEntrega()) > 0) {
            return(this.estado = Estado.entregue);
        }else{
            return(this.estado);
        }              
    }
    
    public boolean isDentroDoPrazoDevolucao() {
        if (this.getEstado()==Estado.entregue) {
            long diferencaDias = ChronoUnit.DAYS.between(this.dataCriacao,this.dataEntrega);
            return diferencaDias <= 2;
        }else {
            return false;
        }
    }
}
