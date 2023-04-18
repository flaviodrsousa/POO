import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Encomenda{
    
    private List<Artigo> artigos;
    private int dimensaoEmbalagem; //1 para pequena,2 para media e outro para grande
    private double taxaGarantia;
    private double custoExpedicao;
    private double precoFinal;
    private Date dataCriacao;
    private Date dataEntrega;
    private boolean paga;
    private boolean expedida;
    
    //Construtores
    public Encomenda() {
        this.artigos = new ArrayList<>();
        this.dimensaoEmbalagem = 1;
        this.taxaGarantia = 0;
        this.custoExpedicao = 0;
        this.precoFinal = 0;
        this.dataCriacao = new Date();
        this.dataEntrega = null;
        this.paga = false;
        this.expedida = false;
    }

    public Encomenda(List<Artigo> artigos,int dimensaoEmbalagem,double taxaGarantia,double custoExpedicao,
    double precoFinal,Date dataCriacao,Date dataEntrega,boolean paga, boolean expedida){
        this.artigos= new ArrayList<>();
        for (Artigo artigo:artigos){
            this.artigos.add(artigo.clone());
        }

        this.dimensaoEmbalagem=dimensaoEmbalagem;
        this.taxaGarantia=taxaGarantia;
        this.custoExpedicao=custoExpedicao;
        this.precoFinal=precoFinal;
        this.dataCriacao=dataCriacao;
        this.dataEntrega=dataEntrega;
        this.paga=paga;
        this.expedida=expedida;
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
    }
    
    //gets
    public List<Artigo> getArtigos() {
        List<Artigo> new_Artigos = new ArrayList<>();
        for (Artigo artigo:this.artigos){
            new_Artigos.add(artigo.clone());
        }
        return new_Artigos;
    }

    public int get_DimensaoEmbalagem() {
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

    //sets
    public void setArtigos(List<Artigo> artigos) {
        this.artigos=new ArrayList<Artigo>();
        for(Artigo artigo:artigos){
            this.artigos.add(artigo.clone());
        }
    }

    public void set_DimensaoEmbalagem(int dimensaoEmbalagem) {
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
        encomenda.get_Expedida()==this.expedida);
    }

    //toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("........Encomenda.........\n");
        if(this.get_DimensaoEmbalagem() == 1){
            sb.append("DimensãoEmbalagem: Pequena\n");
        }else{
            if(this.get_DimensaoEmbalagem()==2){
                sb.append("DimensãoEmbalagem: Média\n");
            }else{
                sb.append("DimensãoEmbalagem: Grande\n");
            }
        }
        sb.append("PrecoTotal: "+this.get_PrecoFinal()+"\n");
        sb.append("DataCriaçao: "+this.get_DataCriacao()+"\n");
        sb.append("DataEntrega: "+this.get_DataEntrega()+"\n");
        if(this.get_Paga() == false && this.get_Expedida() == false){
            sb.append("Estado: Pendente\n");
        }else{
            if(this.get_Paga() == true && this.get_Expedida() == false){
                sb.append("Estado: Paga\n");
            }else{
                sb.append("Estado: Expedida\n");
            }
        }
        if(this.get_Expedida() == false) {
            sb.append("Prazo de Devolução: Encomenda ainda não foi enviada\n");
            }else{
                if(isDentroDoPrazoDevolucao()){
                    sb.append("Prazo de Devolução: No prazo\n");
                }else{
                    sb.append("Prazo de Devolução: Fora do Prazo\n");
                }
            }
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

    private void atualizarPrecoFinal() {
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
        this.precoFinal = (precototal + (quantidadeNovos * 0.5) + (quantidadeUsados * 0.25)) + this.taxaGarantia + this.custoExpedicao;
    }
}
