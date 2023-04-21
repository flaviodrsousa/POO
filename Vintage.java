import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Vintage{
    private Map<Integer,Encomenda> vendas; //map das diversas encomendas vendidas (chave é numeroEncomenda).
    private Date data_atual;

    public Vintage(){
        this.vendas = new HashMap<>();
        this.data_atual=new Date();
    }

    public Vintage(Date data_atual,Map<Integer,Encomenda> vendas) {
        this.data_atual=data_atual;
        this.vendas = new HashMap<>();
        for (Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            this.vendas.put(entry.getKey(), entry.getValue().clone());
        }
    }

    public Vintage(Vintage vintage) {
        this.data_atual=vintage.get_DataAtual();
        this.vendas = new HashMap<>();
        for (Map.Entry<Integer,Encomenda> entry: vintage.getVendas().entrySet()){
            this.vendas.put(entry.getKey(), entry.getValue().clone());
        }
    }

    //gets
    public Date get_DataAtual() {
        Date newDate = new Date(this.data_atual.getTime());
        return newDate;
    }

    public Map<Integer, Encomenda> getVendas() {
        Map<Integer,Encomenda> novo = new HashMap<>();
        for(Map.Entry<Integer,Encomenda> entry: this.vendas.entrySet()){
            novo.put(entry.getKey(),entry.getValue().clone());
        }
        return novo;
    }

    //sets
    public Date set_DataAtual(Date data) {
        return this.data_atual = new Date(data.getTime());
    }

    public void setVendas(Encomenda novo) {
        this.vendas=new HashMap<>();
        for(Map.Entry<Integer,Encomenda> entry: this.vendas.entrySet()){
            this.vendas.put(entry.getKey(),entry.getValue().clone());
        }
    }

    //Outros métodos
    private void addVendas(Encomenda encomenda){
        this.vendas.put(encomenda.getNumeroEncomenda(),encomenda.clone());
    }

    private void removeVendas(Encomenda encomenda){
        this.vendas.remove(encomenda.getNumeroEncomenda());
    }

    private String fatura(Encomenda encomenda){
        StringBuilder sb = new StringBuilder();
        sb.append(encomenda.toString());
        return sb.toString();
    }
}