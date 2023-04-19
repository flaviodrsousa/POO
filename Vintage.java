import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Vintage{
    private Map<Integer,Encomenda> vendas;
    private Date data_atual;

    public Vintage(){
        this.data_atual=new Date();
        this.vendas = new HashMap<>();
    }

    public Vintage(Date data) {
        this.data_atual=data;
        this.vendas = new HashMap<>();
    }

    public Vintage(Vintage novo) {
        this.data_atual=get_DataAtual();
        this.vendas = new HashMap<>();
    }

//gets
    public Date get_DataAtual() {
        return this.data_atual;
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
        return this.data_atual = data;
    }
    public void setVendas(Encomenda novo) {
        this.vendas=new HashMap<>();
        for(Map.Entry<Integer,Encomenda> entry: this.vendas.entrySet()){
            this.vendas.put(entry.getKey(),entry.getValue().clone());
        }
    }

//Outras funcoes
    private Map<Integer, Encomenda> addVendas(Encomenda encomenda){
        Map<Integer,Encomenda> novo = getVendas();
        novo.put(encomenda.getNumeroEncomenda(),encomenda.clone());
        return novo;
    }
    private Map<Integer, Encomenda> removeVendas(Encomenda encomenda){
        Map<Integer,Encomenda> novo = getVendas();
        novo.remove(encomenda.getNumeroEncomenda());
        return novo;  
    }
    private String fatura(Encomenda encomenda){
        StringBuilder sb = new StringBuilder();
        sb.append(encomenda.toString());
        return sb.toString();
    }



}