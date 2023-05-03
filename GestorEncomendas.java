import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;

public class GestorEncomendas implements Serializable{
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
    public void addEncomenda(Encomenda encomenda) throws AddException{
        Encomenda previousValue = vendas.putIfAbsent(encomenda.getNumeroEncomenda(),encomenda.clone());
        if (previousValue != null) { //se a chave já existir no map
            throw new AddException("Já existe no sistema uma Encomenda com esse Numero de Encomenda");
        }
    }

    public void removeEncomenda(Encomenda encomenda) throws RemoveException{
        Encomenda encomandaRemovida = this.vendas.remove(encomenda.getNumeroEncomenda());
        if (encomandaRemovida == null){
            throw new RemoveException("Não existe uma Encomenda com esse Numero de Encomenda!");
        }
    }

    public Encomenda getEncomenda(Integer numEncomenda) throws GetException{
        Encomenda encomenda = this.vendas.get(numEncomenda);
        if (encomenda == null){
            throw new GetException("Não existe uma Encomenda com esse Numero de Encomenda!");
        }
        return encomenda;
    }

    public void entregaEncomenda(Vintage vintage){
        for (Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            if (vintage.get_DataAtual().compareTo(entry.getValue().get_DataEntrega())>0){
                entry.getValue().setEstado(Encomenda.Estado.entregue);
            }
        }
    }

    //Q1
    public String vendedorMaisFatorou(){
        Map<String, Double> vendedores = new HashMap<>();
        for(Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            if(!(entry.getValue().getEstado().equals(Encomenda.Estado.pendente))){
                String codUtilizadorVendedor = entry.getValue().getVendedor().getCodUtilizador();
                GestorArtigos gestorArtigos = entry.getValue().getGestorArtigos();
                double valorEncomenda=gestorArtigos.ValorFaturado_Vendedor_Encomenda();
                if(!(vendedores.containsKey(codUtilizadorVendedor))){
                    vendedores.put(codUtilizadorVendedor,valorEncomenda);
                }else{
                    double valorExistente = vendedores.get(codUtilizadorVendedor);
                    vendedores.replace(codUtilizadorVendedor, valorEncomenda+valorExistente);
                }
            }
        }

        double maior=0;
        String maiorVendedor="Não se realizaram vendas";
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
        String transportadoraNome="Não foram realizadas vendas";
        int volume=0;
        for(Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            transportadoraNome=entry.getValue().getTransportadora().getNome();
            if(transportadoras.containsKey(transportadoraNome)){
                volume=transportadoras.get(transportadoraNome)+1;
                transportadoras.put(transportadoraNome,volume);
            }else{
                transportadoras.put(transportadoraNome,1);
            }
        }
        for(Map.Entry<String,Integer> entry: transportadoras.entrySet()){
            if(entry.getValue()>volume){
                volume=entry.getValue();
                transportadoraNome=entry.getKey();
            }
        }
        return transportadoraNome;
    }

    //Q3
    public void encomendasVendedor(String codUtilizador){
        for(Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            if (entry.getValue().getVendedor().getCodUtilizador().equals(codUtilizador)){
                System.out.println(entry.getValue().toString());
            }
        }
    }

    //Q4
    public void topVendedoresCompradores(String dataInicial, String dataFinal, String top) {

    int topi = Integer.parseInt(top);

    ArrayList<Encomenda> encomendas = getEncomendasForPeriod(dataInicial, dataFinal);

    Map.Entry<Utilizador, Double> vendedoresTotais = new HashMap<>();
    for (Map.Entry<Int, Encomenda> entry : vendas.entrySet()) {
        Utilizador vendedor = entry.getValue().getVendedor();
        Double preco_final = entry.getValue().get_PrecoFinal();
        if (vendedoresTotais.containsKey(vendedor)) {
            preco_final += vendedoresTotais.get(vendedor);
        }
        vendedoresTotais.put(vendedor, preco_final);
    }

    List<Map.Entry<Utilizador, Double>> organizarVendedores = new ArrayList<>(vendedoresTotais.entrySet());
    organizarVendedores.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

    System.out.println("Maiores Vendedores:");
    int count = 1;
    for (Map.Entry<Utilizador, Double> entry : organizarVendedores) {
        Utilizador vendedor_ = entry.getKey();
        System.out.println(count + ". " + vendedor_.getNome());
        count++;
        if (count > topi) {
            break;
        }
    }

    Map<Utilizador, Double> compradoresTotais = new HashMap<>();
    for (Map.Entry<Int, Encomenda> entry : vendas.entrySet()) {
        Utilizador comprador = entry.getValue().getComprador();
        Double precofinal = entry.getValue().get_PrecoFinal();
        if (compradoresTotais.containsKey(comprador)) {
            precofinal += compradoresTotais.get(comprador);
        }
        compradoresTotais.put(comprador, precofinal);
    }

    List<Map.Entry<Utilizador, Double>> organizarCompradores = new ArrayList<>(compradoresTotais.entrySet());
    organizarCompradores.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

    System.out.println("\nMaiores Compradores:");
    count = 1;
    for (Map.Entry<Utilizador, Double> entry : organizarCompradores) {
        Utilizador comprador_ = entry.getKey();
        System.out.println(count + ". " + comprador_.getNome());
        count++;
        if (count > top) {
            break;
            }
        }
    }

    private ArrayList<Encomenda> getEncomendasForPeriod(String dataInicio, String dataFim) {
        ArrayList<Encomenda> encomendasPeriodo = new ArrayList<>();
        Date dataInicioF = Data.StringtoDate(dataInicio);
        Date dataFimF = Data.StringtoDate(dataFim);
        for (Map.Entry<Integer, Encomenda> entry: vendas.entrySet()) {
            Date dataCriacao = entry.getValue().get_DataCriacao();
            if (dataCriacao.compareTo(dataInicioF) >= 0 && dataCriacao.compareTo(dataFimF) <= 0) {
                encomendasPeriodo.add(entry.getValue());
            }
        }

    return encomendasPeriodo;
    }


    //Q5
    public double ganhosVintage(){
        int ganhosTotais=0;
        for(Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            ganhosTotais+=entry.getValue().get_PrecoFinal();
        }
        return ganhosTotais;
    }
}

