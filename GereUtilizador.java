import java.util.HashMap;
import java.util.Map;

public class GereUtilizador{
    private Map<String,Utilizador> utilizadores;

    //Contrutores
    public GereUtilizador(){
        this.utilizadores= new HashMap<>();
    }
    public GereUtilizador(Map<String,Utilizador> novo) {
        this.utilizadores = new HashMap<>();
        for (Map.Entry<String,Utilizador> entry: novo.entrySet()){
            this.utilizadores.put(entry.getKey(), entry.getValue().clone());
        }
    }

    public GereUtilizador(GereUtilizador gereUtilizador) {
        this.utilizadores = gereUtilizador.getVendas();
    }

    //get
    public Map<String, Utilizador> getVendas() {
        Map<String,Utilizador> novo = new HashMap<>();
        for(Map.Entry<String,Utilizador> entry: this.utilizadores.entrySet()){
            novo.put(entry.getKey(),entry.getValue().clone());
        }
        return novo;
    }

    //set
    public void setVendas(Map<String,Utilizador> utilizadores) {
        this.utilizadores=new HashMap<>();
        for(Map.Entry<String,Utilizador> entry: utilizadores.entrySet()){
            this.utilizadores.put(entry.getKey(),entry.getValue().clone());
        }
    }

    //clone
    public GereUtilizador clone(){
        GereUtilizador novo = new GereUtilizador();
        novo.setVendas(this.utilizadores);
        return novo;
    }

    //equals
    public boolean equals(Object o){
        if (this==o) 
            return true;
        if ((o==null) || (this.getClass() != o.getClass())) 
            return false;
        GereUtilizador utilizador = (GereUtilizador) o;
        return (utilizador.getVendas().equals(this.utilizadores));
    }

    //toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizadores registados: ").append(utilizadores.size()).append("\n");
    
        for(Map.Entry<String,Utilizador> entry: utilizadores.entrySet()){
            sb.append(entry.getValue().toString()).append("\n");
        }
    
        return sb.toString();
    }

    //Outros métodos
    public void addUtilizador(Utilizador utilizador){
        this.utilizadores.put(utilizador.getCodUtilizador(),utilizador.clone());
    }

    public void removeVendas(Utilizador utilizador){
        this.utilizadores.remove(utilizador.getCodUtilizador());
    }
    

}