import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Controlador_Menu_Vintage controlador_Menu_Vintage;
    private static Scanner input = new Scanner(System.in);

    // Contrutores
    public Menu(Controlador_Menu_Vintage controlador_Menu_Vintage) {
        this.controlador_Menu_Vintage = controlador_Menu_Vintage; // Agregação
    }

    //fecha scanner
    private void closeScanner(Scanner scanner){
        scanner.close();
    } 

    public static Vintage carregaDefault_Changed() throws FileNotFoundException, ClassNotFoundException, IOException, 
    DateTimeException, AddException, RemoveException, VintageException{

        System.out.println("\n------Carregar Sistema-----");
        System.out.println("Estado do Sistema (1-> Por Defeito)/(2-> Alterado): ");

        int default_changed=0;
        try{
            default_changed=input.nextInt();
        }catch (InputMismatchException e){
            input.nextLine();
        }

        if (default_changed==1) return Controlador_Menu_Vintage.carregaEstado("EstadoVintage_default.dat");
        else if (default_changed==2) return Controlador_Menu_Vintage.carregaEstado("EstadoVintage_changed.dat");
        else throw new VintageException("Selecione 1 ou 2!!!");
    }

    public void run_AdminOrUser(){
        System.out.println("\n------Vintage-----");
        System.out.println("Modo de navegação (1-> Admin)/(2->User): ");

        int admin_user=0;
        try{
            admin_user=input.nextInt();
            input.nextLine(); 
        }catch (InputMismatchException e){
            System.out.println("Escolha 1 ou 2!!!");
        }

        if(admin_user==1) this.runAdmin();
        else if (admin_user==2) this.runUtilizador();
        else {
            System.out.println("Escolha 1 ou 2!!!");
            this.run_AdminOrUser();
        }
    }

    private void runAdmin(){
        int opcao;

        do{
            System.out.println("\n------ MENU ------");
            System.out.println("1. Verificar Estado do sistema");
            System.out.println("2. Avançar no Tempo");
            System.out.println("3. Adicionar Utilizador");
            System.out.println("4. Adicionar Transportadora");
            System.out.println("5. Remover Utilizador");
            System.out.println("6. Remover Transportadora");
            System.out.println("7. Ganhos do Sistema");
            System.out.println("8. Vendedor que mais fatorou");
            System.out.println("9. Transportadora com mais volume de encomendas");
            System.out.println("10. Melhores compradores e vendedores num determinado periodo de tempo");
            System.out.println("0. Sair");

            opcao=-1;

            try {
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
            }

            switch(opcao){
                case 1:
                    System.out.println(controlador_Menu_Vintage.toString_Vintage());
                    break;
                case 2:
                    System.out.println("Data (dd-MM-yyyy) para onde pertende avancar: ");
                    input.nextLine();
                    String data = input.nextLine();
                    try {
                        controlador_Menu_Vintage.avancarTempo(data);
                    } catch (DateTimeException e) {
                        System.out.println("A data é no formato (dd-MM-yyyy)!");
                        break;
                    }
                    break;
                case 3:
                    input.nextLine();
                    System.out.println("Codigo de utilizador: ");
                    String codUtilizador = input.nextLine();
                    System.out.println("Email: ");
                    String email = input.nextLine();
                    System.out.println("Nome: ");
                    String nome = input.nextLine();
                    System.out.println("Morada: ");
                    String morada = input.nextLine();
                    System.out.println("NumFiscal: ");
                    int numFiscal =0;
                    try {
                        numFiscal = input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("O numFiscal é um numero!!");
                        input.nextLine();
                        break;
                    }
                    try {
                        System.out.println("\nEscolha uma das transportadoras (nome): \n"+"Nº de Transportadoras: "
                        +controlador_Menu_Vintage.toString_Transportadoras());
                        System.out.println();
                        input.nextLine();
                        System.out.println("Nome Transportadora:");
                        String nomeTransportadora = input.nextLine();
                        Transportadora transportadora = controlador_Menu_Vintage.getTransportadora(nomeTransportadora);

                        Utilizador utilizador = new Utilizador(codUtilizador, email, nome, morada,
                        numFiscal, new GestorArtigos(), new GestorArtigos(), new GestorArtigos(), transportadora);

                        controlador_Menu_Vintage.addUtilizador(utilizador);
                        break;
                    }catch(GetException e){
                        System.out.println(e.getMessage());
                        break;
                    }catch(AddException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                case 7:
                    System.out.println("Ganhos do sistema: " + controlador_Menu_Vintage.ganhosVintage());
                    break;
                case 8:
                    System.out.println("Vendedor que mais fatorou: " + controlador_Menu_Vintage.vendedorMaisFatorou());
                    break;
                case 9:
                    System.out.println("Transportadora mais usada: "+ controlador_Menu_Vintage.TransportadoraMaiorVolumeFatoracao());
                    break;
                case 10:
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

        }while(opcao!=0);

        closeScanner(input);
    }

    private void runUtilizador() throws DateTimeException {
        int opcao;

        System.out.println("\n------------LOGIN-----------");
        System.out.println("Código de utilizador:");
        String codUtilizadorLogin = input.nextLine();
        if(!controlador_Menu_Vintage.exists_Utilizador(codUtilizadorLogin)){
            System.out.println("Utilizador não existe!!");
            this.runUtilizador();
        }

        do {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Meu registo de encomendas");
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
                    controlador_Menu_Vintage.encomendasVendedor(codUtilizadorLogin);
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (opcao != 0);

        closeScanner(input);
    }
}
