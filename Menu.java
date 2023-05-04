import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Controlador_Menu_Vintage controlador_Menu_Vintage;
    private Scanner input;

    // Contrutores
    public Menu(Controlador_Menu_Vintage controlador_Menu_Vintage) {
        this.controlador_Menu_Vintage = controlador_Menu_Vintage; // Agregação
        this.input = new Scanner(System.in);
    }

    public void run() throws DateTimeException {
        int opcao;

        System.out.println("\n------------LOGIN-----------");
        System.out.println("Código de utilizador:");
        String codUtilizadorLogin = input.nextLine();
        if(!controlador_Menu_Vintage.exists_Utilizador(codUtilizadorLogin)){
            System.out.println("Utilizador não existe!!");
            this.run();
        }

        do {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Verificar Estado do sistema");
            System.out.println("2. Avançar no Tempo");
            System.out.println("3. Encomendas realizadas");
            System.out.println("4. Ganhos do Sistema");
            System.out.println("5. Vendedor que mais fatorou");
            System.out.println("6. Transportadora com mais volume de encomendas");
            System.out.println("7. Melhores compradores e Vendedores num determinado periodo de tempo");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opção: ");

            opcao = -1;

            try {
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
            }

            switch (opcao) { 
                case 1:
                    System.out.println(controlador_Menu_Vintage.toString_Vintage());
                    break;
                case 2:
                    System.out.println("Data (dd-MM-yyyy) para onde pertende avancar: ");
                    input.nextLine();
                    String data = input.nextLine();
                    try {
                        List<Encomenda> encomendas = controlador_Menu_Vintage.avancarTempo(data);
                        for (Encomenda encomenda:encomendas){
                            encomenda.fatura();
                        }
                    } catch (DateTimeException e) {
                        System.out.println("A data é no formato (dd-MM-yyyy)!");
                        break;
                    }
                    break;
                case 3:
                    controlador_Menu_Vintage.encomendasVendedor(codUtilizadorLogin);
                    break;
                case 4:
                    System.out.println("Ganhos do sistema: " + controlador_Menu_Vintage.ganhosVintage());
                    break;
                case 5:
                    System.out.println("Vendedor que mais fatorou: " + controlador_Menu_Vintage.vendedorMaisFatorou());
                    break;
                case 6:
                    System.out.println("Transportadora mais usada: "+ controlador_Menu_Vintage.TransportadoraMaiorVolumeFatoracao());
                    break;
                case 7:
                    System.out.println("Data Inicial do Periodo (dd-MM-yyyy):  ");
                    input.nextLine();
                    String datai = input.nextLine();
                    System.out.println("Data Final do Periodo (dd-MM-yyyy):  ");
                    String dataf = input.nextLine();
                    System.out.println("Quantos vendedores/compradores? ");
                    int top=0;
                    try{
                        top = input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("A quantidade de utilizadores é um numero!!");
                        input.nextLine();
                        break;
                    }
                    try{
                        controlador_Menu_Vintage.topVendedoresCompradores(datai, dataf, top);
                    }catch(DateTimeException e){
                        System.out.println("Datas sao no formato (dd-MM-yyyy)!!");
                        break;
                    }
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
