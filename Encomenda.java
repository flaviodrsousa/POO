import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Encomenda{
    
    private List<Artigo> artigos;
    private int dimensaoEmbalagem;
    private double taxaGarantia;
    private double custoExpedicao;
    private double precoFinal;
    private Date dataCriacao;
    private Date dataEntrega;
    private boolean paga;
    private boolean expedida;
    
    //Falar sobre os artigos
    public Encomenda(int dimensaoEmbalagem, double taxaGarantia, double custosExpedicao) {
        this.artigos = new ArrayList<>();
        this.dimensaoEmbalagem = dimensaoEmbalagem;
        this.taxaGarantia = taxaGarantia;
        this.custoExpedicao = custosExpedicao;
        this.precoFinal = 0;
        this.dataCriacao = new Date();
        this.dataEntrega = null;
        this.paga = false;
        this.expedida = false;
    }
    
//gets
    public int get_DimensaoEmbalagem() {
        return this.dimensaoEmbalagem;
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
    
    public void pagar() {
        this.paga = true;
    }
    
    public void expedir() {
        this.expedida = true;
    }
    
    public void entregar() {
        this.dataEntrega = new Date();
    }
    
    public boolean get_Entregue() {
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

//sets
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
            sb.append("Devolução: Ainda não enviada\n");
            }else{
                if(isDentroDoPrazoDevolucao()){
                    sb.append("Devolução: No prazo\n");
                }else{
                    sb.append("Devolução: Fora do Prazo\n");
                }
            }
        

        return sb.toString();

    }
}
