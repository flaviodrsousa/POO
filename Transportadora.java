public class Transportadora {
    private int precoExpPequena; //1 artigo
    private int precoExpMedia; //2 a 5 artigos
    private int precoExpGrande; // >5 artigos

    //Construtores
    public Transportadora(){
        this.precoExpPequena=0;
        this.precoExpMedia=0;
        this.precoExpGrande=0;
    }

    public Transportadora(int precoExpPequena,int precoExpMedia,
    int precoExpGrande){
        this.precoExpPequena=precoExpPequena;
        this.precoExpMedia=precoExpMedia;
        this.precoExpGrande=precoExpGrande;
    }

    public Transportadora(Transportadora umTransportadora){
        this.precoExpPequena=umTransportadora.getPrecoExpPequena();
        this.precoExpMedia=umTransportadora.getPrecoExpMedia();
        this.precoExpGrande=umTransportadora.getPrecoExpGrande();
    }

    //gets
    public int getPrecoExpPequena() {
        return this.precoExpPequena;
    }

    public int getPrecoExpMedia() {
        return this.precoExpMedia;
    }

    public int getPrecoExpGrande() {
        return this.precoExpGrande;
    }

    //sets
    public void setPrecoExpPequena(int precoExpPequena) {
        this.precoExpPequena = precoExpPequena;
    }

    public void setPrecoExpMedia(int precoExpMedia) {
        this.precoExpMedia = precoExpMedia;
    }

    public void setPrecoExpGrande(int precoExpGrande) {
        this.precoExpGrande = precoExpGrande;
    }

    //clone
    public Transportadora clone(){
        return new Transportadora(this);
    }

    //equals
    public boolean equals(Object o){
        if (this==o) 
            return true;
        if ((o==null) || (this.getClass() != o.getClass())) 
            return false;
        Transportadora transportadora = (Transportadora) o;
        return (transportadora.getPrecoExpPequena()==this.precoExpPequena &&
        transportadora.getPrecoExpMedia()==this.precoExpMedia &&
        transportadora.getPrecoExpGrande()==this.precoExpGrande);
    }

    //toString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("........Transportadora......\n");
        sb.append("PreçoExp Encomenda pequena: "+this.getPrecoExpPequena()+"\n");
        sb.append("PreçoExp Encomenda media: "+this.getPrecoExpMedia()+"\n");
        sb.append("PreçoExp Encomenda grande: "+this.getPrecoExpGrande()+"\n");

        return sb.toString();
    }

}
