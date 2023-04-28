import java.sql.Date;
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
        return new GestorEncomendas(this);
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

    public void entregaEncomenda(Vintage vintage){
        for (Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            if (vintage.get_DataAtual().compareTo(entry.getValue().get_DataEntrega())>0){
                entry.getValue().setEstado(Encomenda.Estado.entregue);
            }
        }
    }

    //Q3
    public void encomendasVendedor(Utilizador utilizador){
        for(Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            if (entry.getValue().getVendedor().equals(utilizador)){
                System.out.println(entry.getValue().toString());
            }
        }
    }

    //Q5
    public double ganhosVintage(){
        int ganhosTotais=0;
        for(Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            ganhosTotais+=entry.getValue().get_PrecoFinal();
        }
        return ganhosTotais;
    }

    //Q1
    public String vendedorMaisFatorou(Date data){
        Map<String, Double> vendedores = new HashMap<>();
        for(Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            if((entry.getValue().get_DataCriacao()).compareTo(data)>0){
                GestorArtigos gestorArtigos = entry.getValue().getGestorArtigos();
                double soma=gestorArtigos.vendedorMaisFatorouContinuacao();
                vendedores.put(entry.getValue().getVendedor().getCodUtilizador(),soma);
            }
        }
        double maior=0;
        String maiorVendedor="";
        for(Map.Entry<String,Double> entry: vendedores.entrySet()){
            if(entry.getValue()>maior){
                maior=entry.getValue();
                maiorVendedor=entry.getKey();
            }
        }
        return maiorVendedor;   
    }

    //Q2
    public String TransportadoraMaiorVolumeFatoracao(){
        Map<String, Integer> transportadoras = new HashMap<>();
        String transportadora="";
        int volume=0;
        for(Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            transportadora=entry.getValue().getTransportadora().getNome();
            if(transportadora.contains(transportadora)){
                volume=transportadoras.get(transportadora)+1;
                transportadoras.put(transportadora,volume);
            }else{
                transportadoras.put(transportadora,1);
            }
        }
        for(Map.Entry<String,Integer> entry: transportadoras.entrySet()){
            if(entry.getValue()>volume){
                volume=entry.getValue();
                transportadora=entry.getKey();
            }
        }
        return transportadora;
    }
}

