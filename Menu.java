import java.text.ParseException;
import java.util.Scanner;

public class Menu {
    private Controlador_Menu_Vintage controlador_Menu_Vintage;
    private Scanner input;

    //Contrutores
    public Menu(Controlador_Menu_Vintage controlador_Menu_Vintage){
        this.controlador_Menu_Vintage=controlador_Menu_Vintage; //Agregação
        this.input = new Scanner(System.in);
    }

    //gets
    public Controlador_Menu_Vintage getControlador_Menu_Vintage(){ //Agregação
        return this.controlador_Menu_Vintage;
    }

    //sets
    public void setControlador_Menu_Vintage(Controlador_Menu_Vintage controlador_Menu_Vintage) {
        this.controlador_Menu_Vintage = controlador_Menu_Vintage; //Agregação
    }

    public void run() throws ParseException {
        int opcao;
        
        do {
            System.out.println("------ MENU ------");
            System.out.println("1. Avançar no Tempo");
            System.out.println("2. Encomendas de um Vendedor");
            System.out.println("3. Ganhos do Vintage");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opção: ");
            
            opcao = input.nextInt();
            
            switch (opcao) {
                case 1:
                    System.out.println("Data (yyyy-mm-dd) para onde pertende avancar: ");
                    input.nextLine();
                    String data = input.nextLine();
                    controlador_Menu_Vintage.avancarTempo(data);
                    break;
                case 2:
                    System.out.println("Código de utilizador do vendedor: ");
                    input.nextLine();
                    String codUtilizador = input.nextLine();
                    controlador_Menu_Vintage.encomendasVendedor(codUtilizador);
                    break;
                case 3:
                    controlador_Menu_Vintage.ganhosVintage();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
            
        } while (opcao != 0);
        
        input.close();
    }
}
