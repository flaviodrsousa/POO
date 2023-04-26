import java.util.HashMap;
import java.util.Map;

public class GereTransportadoras {
    private Map<String, Transportadora> transportadoras;

    // Construtores
    public GereTransportadoras() {
        this.transportadoras = new HashMap<>();
    }

    public GereTransportadoras(Map<String, Transportadora> novo) {
        this.transportadoras = new HashMap<>();
        for (Map.Entry<String, Transportadora> entry : novo.entrySet()) {
            this.transportadoras.put(entry.getKey(), entry.getValue().clone());
        }
    }

    public GereTransportadoras(GereTransportadoras gereGereTransportadoras) {
        this.transportadoras = gereGereTransportadoras.getTransportadoras();
    }

    // get
    public Map<String, Transportadora> getTransportadoras() {
        Map<String, Transportadora> novo = new HashMap<>();
        for (Map.Entry<String, Transportadora> entry : this.transportadoras.entrySet()) {
            novo.put(entry.getKey(), entry.getValue().clone());
        }
        return novo;
    }

    // set
    public void setTransportadoras(Map<String, Transportadora> transportadoras) {
        this.transportadoras = new HashMap<>();
        for (Map.Entry<String, Transportadora> entry : transportadoras.entrySet()) {
            this.transportadoras.put(entry.getKey(), entry.getValue().clone());
        }
    }

    // clone
    public GereTransportadoras clone() {
        return new GereTransportadoras(this);
    }

    // equals
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        GereTransportadoras gt = (GereTransportadoras) o;
        return (gt.getTransportadoras().equals(this.transportadoras));
    }

    // toString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transportadoras registadas: ").append(transportadoras.size()).append("\n");

        for (Map.Entry<String, Transportadora> entry : transportadoras.entrySet()) {
            sb.append(entry.getValue().toString()).append("\n");
        }

        return sb.toString();
    }

    // Outros métodos
    public void addTransportadora(Transportadora transportadora) {
        this.transportadoras.put(transportadora.getNome(), transportadora.clone()); 
    }

    public void removeTransportadora(Transportadora transportadora) {
        this.transportadoras.remove(transportadora.getNome());
    }
}
