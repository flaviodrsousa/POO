public class Main{
    public static void main(String[] args){
        Artigo art1 = new Artigo();
        System.out.println(art1);

        Artigo art2 = new Artigo("sjgd4la",false,"Como novo",
        2,"Confortaveis e bonitos","Adidas",47.5,0.8);
        System.out.println(art2);

        Artigo art3 = new Artigo("sjgd4la",true,"Como novo",
        2,"Confortaveis e bonitos","Adidas",47.5,0.8);
        System.out.println(art3);

        Sapatilha art4 = new Sapatilha("12345", true, "novo",
        1, "Sapatilha de corrida", "Nike", 100.0,
        0.9, 42, true, "preta", false,
        "2022-01-01");
        System.out.println(art4); 

        Mala art5 = new Mala("12345", true, "novo",
        1, "Sapatilha de corrida", "Nike", 100.0,
        0.9,2,"Couro","2022-12-23",true);
        System.out.println(art5);

        Tshirt art6 = new Tshirt("12345", true, "novo",
        1, "Sapatilha de corrida", "Nike", 100.0,
        0.9,2,2);
        System.out.println(art6);


    }
    
}