import java.util.Date;

public class Vintage{
    private Date data_atual;

    public Vintage(){
        this.data_atual=new Date();
    }

    public Vintage(Date data) {
        this.data_atual=data;
    }

    public Vintage(Vintage novo) {
        this.data_atual=get_DataAtual();
    }

//gets
    public Date get_DataAtual() {
        return this.data_atual;
    }

//sets
    public Date set_DataAtual(Date data) {
        return this.data_atual = data;
    }
}