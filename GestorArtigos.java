import java.util.HashMap;
import java.util.Map;

public class GestorArtigos{
    private Map<String,Artigo> artigos;

    //Contrutores
    public GestorArtigos(){
        this.artigos= new HashMap<>();
    }
    public GestorArtigos(Map<String,Artigo> novo) {
        this.artigos = new HashMap<>();
        for (Map.Entry<String,Artigo> entry: novo.entrySet()){
            this.artigos.put(entry.getKey(), entry.getValue().clone());
        }
    }

    public GestorArtigos(GestorArtigos gereArtigo) {
        this.artigos = gereArtigo.getArtigo();
    }

    //get
    public Map<String, Artigo> getArtigo() {
        Map<String,Artigo> novo = new HashMap<>();
        for(Map.Entry<String,Artigo> entry: this.artigos.entrySet()){
            novo.put(entry.getKey(),entry.getValue().clone());
        }
        return novo;
    }

    //set
    public void setArtigo(Map<String,Artigo> artigos) {
        this.artigos=new HashMap<>();
        for(Map.Entry<String,Artigo> entry: artigos.entrySet()){
            this.artigos.put(entry.getKey(),entry.getValue().clone());
        }
    }

    //clone
    public GestorArtigos clone(){
        return new GestorArtigos(this);
    }

    //equals
    public boolean equals(Object o){
        if (this==o) 
            return true;
        if ((o==null) || (this.getClass() != o.getClass())) 
            return false;
        GestorArtigos artigo = (GestorArtigos) o;
        return (artigo.getArtigo().equals(this.artigos));
    }

    //toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Artigos registados: ").append(artigos.size()).append("\n");
    
        for(Map.Entry<String,Artigo> entry: artigos.entrySet()){
            sb.append(entry.getValue().toString()).append("\n");
        }
    
        return sb.toString();
    }

    //Outros métodos
    public void addArtigo(Artigo Artigo){
        if(!this.artigos.containsKey(Artigo.getCod_barras())){
            this.artigos.put(Artigo.getCod_barras(),Artigo.clone());
        }
    }

    public void removeArtigo(Artigo Artigo){
        if(this.artigos.containsKey(Artigo.getCod_barras())){
            this.artigos.remove(Artigo.getCod_barras());
        }
    }


    public double calcularPrecoFinalEncomenda(Encomenda encomenda) {
        int quantidadeNovos = 0;
        int quantidadeUsados = 0;
        double precototal = 0;
        GestorArtigos gestorArtigos=encomenda.getGestorArtigos();
        for (Map.Entry<String, Artigo> entry:gestorArtigos.getArtigo().entrySet()) {
            if (entry.getValue().getArtigo_novo()) {
                quantidadeNovos++;
            }else {
                quantidadeUsados++;
            }
            precototal += entry.getValue().getPreco_base();
        }
        return ((precototal + (quantidadeNovos * 0.5) + (quantidadeUsados * 0.25)) + encomenda.getTaxaGarantia() + encomenda.getCustoExpedicao());
    }
}