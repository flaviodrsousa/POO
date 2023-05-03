import java.text.ParseException;
import java.util.InputMismatchException;
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
        int opcao=-1;
        
        do {
            System.out.println("------ MENU ------");
            System.out.println("1. Avançar no Tempo");
            System.out.println("2. Encomendas de um Vendedor");
            System.out.println("3. Ganhos do Vintage");
            System.out.println("4. Vendedor que mais fatorou");
            System.out.println("5. Transportadora com mais volume de encomendas");
            System.out.println("6. Adicionar utilizador");
            System.out.println("7. Verificar melhores compradores e Vendedores num determinado periodo");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opção: ");
            
            try{
                opcao = input.nextInt();
            }catch(InputMismatchException e){
                input.nextLine();
            }
            
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
                    System.out.println("Ganhos do sistema: "+controlador_Menu_Vintage.ganhosVintage());
                    break;
                case 4:
                    System.out.println("Vendedor que mais fatorou: "+controlador_Menu_Vintage.vendedorMaisFatorou());
                    break;
                case 5:
                    System.out.println("Transportadora mais usada: "+controlador_Menu_Vintage.TransportadoraMaiorVolumeFatoracao());
                    break;
                case 6:
                    System.out.println("Codigo de utilizador: ");
                    input.nextLine();
                    codUtilizador=input.nextLine();
                    System.out.println("Email: ");
                    String email= input.nextLine();
                    System.out.println("Nome: ");
                    String nome = input.nextLine();
                    System.out.println("Morada: ");
                    String morada = input.nextLine();
                    System.out.println("NumFiscal: ");
                    try{
                        int numFiscal = input.nextInt();
                        System.out.println("Escolha uma das transportadoras (nome): ");
                        System.out.println(controlador_Menu_Vintage.getVintage().getGestorTransportadoras().getTransportadoras().toString());
                        input.nextLine();
                        System.out.println("Nome Transportadora:");
                        String nomeTransportadora = input.nextLine();
                        try{
                            Transportadora transportadora = controlador_Menu_Vintage.getTransportadora(nomeTransportadora);
                            Utilizador utilizador = new Utilizador(codUtilizador, email, nome, morada, 
                            numFiscal, null, null, null, transportadora);
                            controlador_Menu_Vintage.addUtilizador(utilizador);
                            break;
                        }catch (GetException e){
                            System.out.println(e.getMessage());
                            break;
                        }catch (AddException e){
                            System.out.println(e.getMessage());
                        }
                    }catch(InputMismatchException e){
                        System.out.println("O numFiscal é um numero!!");
                        input.nextLine();
                        break;
                    }
                case 7:
                    System.out.println("Data Inicial do Periodo (yyyy-mm-dd):  ");
                    input.nextLine();
                    String datai = input.nextLine();
                    System.out.println("Data Final do Periodo (yyyy-mm-dd):  ");
                    String dataf = input.nextLine();
                    System.out.println("Quantos pretende ver:  ");
                    String top = input.nextLine();
                    controlador_Menu_Vintage.topVendedoresCompradores(datai, dataf, top);
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
