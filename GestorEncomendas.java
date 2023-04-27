import java.util.HashMap;
import java.util.Map;

public class GestorEncomendas{
    private Map<Integer,Encomenda> vendas; //map das diversas encomendas vendidas (chave é numeroEncomenda).

    public GestorEncomendas(){
        this.vendas = new HashMap<>();
    }

    public GestorEncomendas(Map<Integer,Encomenda> vendas) {
        this.vendas = new HashMap<>();
        for (Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            this.vendas.put(entry.getKey(), entry.getValue().clone());
        }
    }

    public GestorEncomendas(GestorEncomendas gereEncomendas) {
        this.vendas = gereEncomendas.getVendas();
    }

    //get
    public Map<Integer, Encomenda> getVendas() {
        Map<Integer,Encomenda> novo = new HashMap<>();
        for(Map.Entry<Integer,Encomenda> entry: this.vendas.entrySet()){
            novo.put(entry.getKey(),entry.getValue().clone());
        }
        return novo;
    }

    //set
    public void setVendas(Map<Integer,Encomenda> vendas) {
        this.vendas=new HashMap<>();
        for(Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            this.vendas.put(entry.getKey(),entry.getValue().clone());
        }
    }

    //clone
    public GestorEncomendas clone(){
        GestorEncomendas novo = new GestorEncomendas();
        novo.setVendas(this.vendas);
        return novo;
    }
    

    //equals
    public boolean equals(Object o){
        if (this==o) 
            return true;
        if ((o==null) || (this.getClass() != o.getClass())) 
            return false;
        GestorEncomendas vendas = (GestorEncomendas) o;
        return (vendas.getVendas().equals(this.vendas));
    }

    //toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Encomendas Vendidas: ").append(vendas.toString());

        for(Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            sb.append(entry.getValue().clone().toString());
        }

        return sb.toString();
    }

    //Outros métodos
    public void addVendas(Encomenda encomenda){
        if(!this.vendas.containsKey(encomenda.getNumeroEncomenda())){
            this.vendas.put(encomenda.getNumeroEncomenda(),encomenda.clone());
        }
    }

    public void removeVendas(Encomenda encomenda){
        if(this.vendas.containsKey(encomenda.getNumeroEncomenda())){
            this.vendas.remove(encomenda.getNumeroEncomenda());
        } 
    }
}

