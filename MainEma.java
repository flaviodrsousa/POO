import java.awt.Color;
import java.text.ParseException;

public class MainEma{
    public static void main(String[] args){
        try{
            Sapatilha art4 = new Sapatilha("12345", true, "novo",
            1, "Sapatilha de corrida", "Nike", 100.0,
            0.9, 42, true,new Color(0,0,0), false,
            "2022-01-01");
            System.out.println(art4); 

            Mala art5 = new Mala("12345", true, "novo",
            1, "Sapatilha de corrida", "Nike", 100.0,
            0.9,Mala.Dimensao.PEQUENO,"Couro","2022-12-23",true);
            System.out.println(art5);
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }

        Tshirt art6 = new Tshirt("12345", true, "novo",
        1, "Sapatilha de corrida", "Nike", 100.0,
        0.9,Tshirt.Tamanho.L,Tshirt.Padrao.LISO);
        System.out.println(art6);
    }
}