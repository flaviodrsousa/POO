public class Main{
    public static void main(String[] args){
        Artigo art1 = new Artigo();
        System.out.println(art1);

        Artigo art2 = new Artigo("sjgd4la",false,"Como novo",
        2,"Confortaveis e bonitos","Adidas",47.5);
        System.out.println(art2);

        Artigo art2 = new Artigo("sjgd4la",true,"Como novo",
        2,"Confortaveis e bonitos","Adidas",47.5);
        System.out.println(art2);

    }
    
}