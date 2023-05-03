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
        int opcao;
        
        do {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Avançar no Tempo");
            System.out.println("2. Encomendas de um Vendedor");
            System.out.println("3. Ganhos do Vintage");
            System.out.println("4. Vendedor que mais fatorou");
            System.out.println("5. Transportadora com mais volume de encomendas");
            System.out.println("6. Adicionar utilizador");
            System.out.println("7. Verificar Data do sistema");
            System.out.println("8. Adicionar Transportadora");
            System.out.println("9. Adicionar Mala");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opção: ");

            opcao=-1;
            
            try{
                opcao = input.nextInt();
            }catch(InputMismatchException e){
                input.nextLine();
            }
            
            switch (opcao) {
                case 1:
                    System.out.println("Data (dd-MM-yyyy) para onde pertende avancar: ");
                    input.nextLine();
                    String data = input.nextLine();
                    try{
                        controlador_Menu_Vintage.avancarTempo(data);
                        break;
                    }catch (ParseException e){
                        System.out.println("A data é no formato (dd-MM-yyyy)!");
                        break;
                    }
                case 2:
                    System.out.println("Código de utilizador do vendedor: ");
                    input.nextLine();
                    String codUtilizador = input.nextLine();
                    try{
                        Utilizador utilizador = controlador_Menu_Vintage.getUtilizador(codUtilizador); //forcar a verificar se existe um utilizador com esse codUtilizador
                        controlador_Menu_Vintage.encomendasVendedor(utilizador.getCodUtilizador());
                        break;
                    }catch (GetException e){
                        System.out.println(e.getMessage());
                        break;
                    }
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
                        System.out.println(controlador_Menu_Vintage.toString_Transportadoras());
                        input.nextLine();
                        System.out.println("Nome Transportadora:");
                        String nomeTransportadora = input.nextLine();
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
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("O numFiscal é um numero!!");
                        input.nextLine();
                        break;
                    }
                case 7:
                    System.out.println("Data do sistema: "+controlador_Menu_Vintage.getData_atual().toString());
                    break;
                case 8:
                    System.out.println("Nome: ");
                    input.nextLine();
                    nome = input.nextLine();
                    System.out.println("Preço Encomenda pequena: ");
                    try{
                        int precoExpPequena = input.nextInt();
                        System.out.println("Preço Encomenda media: ");
                        int precoExpMedia = input.nextInt();
                        System.out.println("preço Encomenda Grande: ");
                        int precoExpGrande = input.nextInt();
                        Transportadora transportadora = new Transportadora(nome, precoExpPequena, precoExpMedia, precoExpGrande);
                        controlador_Menu_Vintage.addTransportadora(transportadora);
                        break;
                    }catch(InputMismatchException e){
                        System.out.println("Os Preços são números!!");
                        input.nextLine();
                        break;
                    }catch(AddException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                case 9:
                    System.out.println("Codigo de barras: ");
                    input.nextLine();
                    String cod_barras=input.nextLine();
                    System.out.println("O artigo é novo (true/false)? ");
                    boolean artigo_novo;
                    try{
                        artigo_novo = input.nextBoolean();
                    }catch(InputMismatchException e){
                        System.out.println("Deve inserir true/false!!");
                        break;
                    }
                    input.nextLine();
                    System.out.println("Estado de utilizacao do artigo (novo/velho): ");
                    String estado = input.nextLine();
                    System.out.println("Número de donos do artigo: ");
                    int num_donos=0;
                    try{
                        num_donos=input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Numero de donos é um numero");
                    }
                    System.out.println("Descreva o produto: ");
                    input.nextLine();
                    String descricao = input.nextLine();
                    System.out.println("Marca: ");
                    String marca = input.nextLine();
                    System.out.println("Preco Base: ");
                    double preco_base;
                    try{
                        preco_base = input.nextDouble();
                    }catch(InputMismatchException e){
                        System.out.println("Precos sao numeros!!");
                        break;
                    }
                    System.out.println("Estado de utilizacao (0-1): ");
                    double estado_utilizacao;
                    try{
                        estado_utilizacao = input.nextDouble();
                    }catch(InputMismatchException e){
                        System.out.println("Estado de utilizacao varia entre 0 e 1 (sendo 0 muito usado e 1 novo)!!");
                        break;
                    }
                    System.out.println("Dimensao (pequeno/medio/grande): ");
                    Mala.Dimensao dimensao;
                    try{
                        dimensao = Mala.Dimensao.valueOf(input.next().toUpperCase());
                    }catch(IllegalArgumentException e){
                        System.out.println("Dimensao tem que ser pequeno/medio/grande!!");
                        break;
                    }
                    System.out.println("Material: ");
                    String material = input.nextLine();
                    System.out.println("Data colecao (dd-MM-yyyy): ");
                    String dataColecao=input.next();
                    System.out.println("Premium? (true/false): ");
                    boolean premium;
                    try{
                        premium = input.nextBoolean();
                    }catch(InputMismatchException e){
                        System.out.println("Premium é (true/false)!!");
                        break;
                    }
                    Artigo Mala=null;
                    try{
                        Mala = new Mala(cod_barras, artigo_novo, estado, num_donos, descricao, marca,
                        preco_base, estado_utilizacao, dimensao, material, dataColecao, premium);
                    }catch(ParseException e){
                        System.out.println("Data no fomato errado (dd-MM-yyyy)!!");
                    }
                    try {
                        controlador_Menu_Vintage.addArtigo(Mala);
                    }catch (AddException e){
                        System.out.println(e.getMessage());
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
