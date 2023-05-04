import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;

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
            sb.append(entry.getKey().toString());
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

    public void removeEncomenda(Integer numEncomenda) throws RemoveException{
        Encomenda encomandaRemovida = this.vendas.remove(numEncomenda);
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

    public List<Encomenda> entregaEncomenda(Vintage vintage){
        List<Encomenda> encomendasEntregues = new ArrayList<>();
        for (Map.Entry<Integer,Encomenda> entry: vendas.entrySet()){
            if (vintage.get_DataAtual().compareTo(entry.getValue().get_DataEntrega())>0){
                entry.getValue().setEstado(Encomenda.Estado.entregue);
                encomendasEntregues.add(entry.getValue());
            }
        }
        return encomendasEntregues;
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
    public void topVendedoresCompradores(String dataInicial, String dataFinal, int top) throws DateTimeException {
        ArrayList<Encomenda> encomendas = getEncomendasForPeriod(dataInicial, dataFinal); //lista so com as encomendas dentro desse periodo de tempo

        Map<Utilizador, Double> vendedoresTotais = new HashMap<>();
        Map<Utilizador, Double> compradoresTotais = new HashMap<>();
        for (Encomenda encomenda : encomendas) {
            Utilizador vendedor = encomenda.getVendedor();
            Utilizador comprador = encomenda.getComprador();
            Double preco_final = encomenda.get_PrecoFinal();

            if (vendedoresTotais.containsKey(vendedor)) {
                preco_final += vendedoresTotais.get(vendedor);
            }

            if (compradoresTotais.containsKey(comprador)) {
                preco_final += compradoresTotais.get(comprador);
            }
            compradoresTotais.put(comprador, preco_final);
            vendedoresTotais.put(vendedor, preco_final);
        }

        List<Map.Entry<Utilizador,Double>> organizarVendedores = new ArrayList<>(vendedoresTotais.entrySet());
        organizarVendedores.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        System.out.println("Maiores Vendedores:");
        int count = 1;
        for (Map.Entry<Utilizador, Double> entry : organizarVendedores) {
            Utilizador vendedor =  entry.getKey();
            System.out.println(count + ". " + vendedor.getNome());
            count++;
            if (count > top) {
                break;
            }
        }

        List<Map.Entry<Utilizador, Double>> organizarCompradores = new ArrayList<>(compradoresTotais.entrySet());
        organizarCompradores.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        System.out.println("\nMaiores Compradores:");
        count = 1;
        for (Map.Entry<Utilizador, Double> entry : organizarCompradores) {
            Utilizador comprador = entry.getKey();
            System.out.println(count + ". " + comprador.getNome());
            count++;
            if (count > top) {
                break;
            }
        }
    }

    private ArrayList<Encomenda> getEncomendasForPeriod(String dataInicio, String dataFim) throws DateTimeException {
        ArrayList<Encomenda> encomendasPeriodo = new ArrayList<>();
        LocalDate dataInicioF = Data.StringtoDate(dataInicio);
        LocalDate dataFimF = Data.StringtoDate(dataFim);
        for (Map.Entry<Integer, Encomenda> entry: vendas.entrySet()) {
            LocalDate dataCriacao = entry.getValue().get_DataCriacao();
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

