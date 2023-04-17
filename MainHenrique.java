public class MainHenrique {
    public static void main(String[] args) {
        Utilizador utilizador = new Utilizador();
        Utilizador utilizador2= utilizador.clone();

        System.out.println(utilizador==utilizador2);
        System.out.println(utilizador.equals(utilizador2));
    }
}
