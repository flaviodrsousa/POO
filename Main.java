import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Vintage vintage = new Vintage();

        Artigo artigo1 = new Sapatilha("sap1a",true,"novo",1,"sapatilha vermelha num 45",
        "Nike",50.5,1,45,true,Color.RED,false,"10-05-2000");
        
        List<Artigo> listArtigos = new ArrayList<>();
        listArtigos.add(artigo1);

        Transportadora transportadora = new Transportadora("Fedex",10,20,50);

        Utilizador utilizador1 = new Utilizador("u0001","u0001@gmail.com","Henrique Malheiro","Rua Braga Parque",
        1000,listArtigos,null,null,transportadora);
        Utilizador utilizador2 = new Utilizador("u0002","u0002@gmail.com","Carolina Melo","Rua de Baixo",
        1005,null,listArtigos,null,transportadora);
        
        vintage.encomendasVendedor(utilizador1);
        vintage.encomendasVendedor(utilizador2);

/*         Controlador_Menu_Vintage controlador_Menu_Vintage = new Controlador_Menu_Vintage(vintage);

        Menu menu = new Menu(controlador_Menu_Vintage);

        System.out.println(menu.getMenu()); */
        //Ciclo com scanners, passam isto para o vintage que vai chamar as respetivas queries com os argumentos do scanner.
    }
}
