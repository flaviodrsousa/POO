import java.awt.Color;
import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Controlador_Menu_Vintage controlador_Menu_Vintage;
    private Scanner input;

    // Contrutores
    public Menu(Controlador_Menu_Vintage controlador_Menu_Vintage) {
        this.controlador_Menu_Vintage = controlador_Menu_Vintage; // Agregação
        this.input = new Scanner(System.in);
    }

    // gets
    public Controlador_Menu_Vintage getControlador_Menu_Vintage() { // Agregação
        return this.controlador_Menu_Vintage;
    }

    // sets
    public void setControlador_Menu_Vintage(Controlador_Menu_Vintage controlador_Menu_Vintage) {
        this.controlador_Menu_Vintage = controlador_Menu_Vintage; // Agregação
    }

    public void run() throws DateTimeException {
        int opcao;

        do {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Avançar no Tempo");
            System.out.println("2. Encomendas de um Vendedor");
            System.out.println("3. Ganhos do Vintage");
            System.out.println("4. Vendedor que mais fatorou");
            System.out.println("5. Transportadora com mais volume de encomendas");
            System.out.println("6. Adicionar Utilizador");
            System.out.println("7. Verificar Data do sistema");
            System.out.println("8. Adicionar Transportadora");
            System.out.println("9. Adicionar Artigo");
            System.out.println("10. Verificar melhores compradores e Vendedores num determinado periodo");
            System.out.println("11. Adicionar Encomenda");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opção: ");

            opcao = -1;

            try {
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
            }

            switchFlag : switch (opcao) { //switchFlag permite parar diretamente o switch com um break
                case 1:
                    System.out.println("Data (dd-MM-yyyy) para onde pertende avancar: ");
                    input.nextLine();
                    String data = input.nextLine();
                    try {
                        controlador_Menu_Vintage.avancarTempo(data);
                        break;
                    } catch (DateTimeException e) {
                        System.out.println("A data é no formato (dd-MM-yyyy)!");
                        break;
                    }
                case 2:
                    System.out.println("Código de utilizador do vendedor: ");
                    input.nextLine();
                    String codUtilizador = input.nextLine();
                    try {
                        Utilizador utilizador = controlador_Menu_Vintage.getUtilizador(codUtilizador);  /*forcar a
                                                                                                        verificar se
                                                                                                        existe um
                                                                                                        utilizador com
                                                                                                        esse
                                                                                                        codUtilizador */
                        controlador_Menu_Vintage.encomendasVendedor(utilizador.getCodUtilizador());
                        break;
                    } catch (GetException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                case 3:
                    System.out.println("Ganhos do sistema: " + controlador_Menu_Vintage.ganhosVintage());
                    break;
                case 4:
                    System.out.println("Vendedor que mais fatorou: " + controlador_Menu_Vintage.vendedorMaisFatorou());
                    break;
                case 5:
                    System.out.println("Transportadora mais usada: "
                            + controlador_Menu_Vintage.TransportadoraMaiorVolumeFatoracao());
                    break;
                case 6:
                    System.out.println("Codigo de utilizador: ");
                    input.nextLine();
                    codUtilizador = input.nextLine();
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
                    }catch(GetException e){
                        System.out.println(e.getMessage());
                        break;
                    }catch(AddException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                case 7:
                    System.out.println("Data do sistema: " + controlador_Menu_Vintage.getData_atual().toString());
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
                        Transportadora transportadora = new Transportadora(nome, precoExpPequena, precoExpMedia,
                                precoExpGrande);
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
                    String cod_barras = input.nextLine();
                    System.out.println("O artigo é novo (true/false)? ");
                    boolean artigo_novo;
                    try{
                        artigo_novo = input.nextBoolean();
                    }catch(InputMismatchException e){
                        System.out.println("Deve inserir true/false!!");
                        input.nextLine();
                        break;
                    }
                    input.nextLine();
                    System.out.println("Estado de utilizacao do artigo (novo/velho): ");
                    String estado = input.nextLine();
                    System.out.println("Número de donos do artigo: ");
                    int num_donos = 0;
                    try{
                        num_donos = input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Numero de donos é um numero");
                        input.nextLine();
                        break;
                    }
                    System.out.println("Descreva o produto: ");
                    input.nextLine();
                    String descricao = input.nextLine();
                    System.out.println("Marca: ");
                    String marca = input.nextLine();
                    System.out.println("Preco Base: ");
                    double preco_base;
                    try {
                        preco_base = input.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Precos sao numeros!!");
                        input.nextLine();
                        break;
                    }
                    System.out.println("Estado de utilizacao (0-1): ");
                    double estado_utilizacao;
                    try {
                        estado_utilizacao = input.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Estado de utilizacao varia entre 0 e 1 (sendo 0 muito usado e 1 novo)!!");
                        input.nextLine();
                        break;
                    }
                    System.out.println("Escolha o tipo de artigo (1->mala/2->sapatilha/3->t-shirt): ");
                    try{
                        int opcaoArtigo = input.nextInt();
                        if (opcaoArtigo==1){
                            System.out.println("Dimensao (pequeno/medio/grande): ");
                            input.nextLine();
                            Mala.Dimensao dimensao;
                            try {
                                dimensao = Mala.Dimensao.valueOf(input.next().toUpperCase());
                                input.nextLine();
                            } catch (IllegalArgumentException e) {
                                System.out.println("Dimensao tem que ser pequeno/medio/grande!!");
                                break;
                            }
                            System.out.println("Material: ");
                            String material = input.nextLine();
                            System.out.println("Data colecao (dd-MM-yyyy): ");
                            String dataColecao = input.nextLine();
                            System.out.println("Premium? (true/false): ");
                            boolean premium;
                            try {
                                premium = input.nextBoolean();
                            } catch (InputMismatchException e) {
                                System.out.println("Premium é (true/false)!!");
                                input.nextLine();
                                break;
                            }
                            Mala mala = null;
                            try {
                                mala = new Mala(cod_barras, artigo_novo, estado, num_donos, descricao, marca,
                                preco_base, estado_utilizacao, dimensao, material, dataColecao, premium);
                                controlador_Menu_Vintage.addArtigo(mala);
                                break;
                            }catch(DateTimeException e){ // datacolecao pode ter erro de parse
                                System.out.println("Data no fomato errado (dd-MM-yyyy)!!");
                                break;
                            }catch(AddException e){
                                System.out.println(e.getMessage());
                                break;
                            }
                        }else if (opcaoArtigo ==2){
                            System.out.println("Tamanho: ");
                            int tamanho=0;
                            try{
                                tamanho=input.nextInt();
                            }catch(InputMismatchException e){
                                System.out.println("Tamanho é um número!!");
                                input.nextLine();
                                break;
                            }
                            System.out.println("Tem atacadores? (true/false) ");
                            boolean atacadores=false;
                            try{
                                atacadores=input.nextBoolean();
                            }catch(InputMismatchException e){
                                System.out.println("Tem que escolher true/false!!");
                                input.nextLine();
                                break;
                            }
                            System.out.println("Escolha uma cor (valor RGB): ");
                            Color cor=Color.BLACK;
                            try{
                                cor = new Color(input.nextInt());
                                input.nextLine();
                            }catch(InputMismatchException e){
                                System.out.println("A cor é o seu valor em RGB!!");
                                input.nextLine();
                                break;
                            }
                            System.out.println("Data de lançamento (dd-MM-yyyy): ");
                            String data_lancamento = input.nextLine();
                            System.out.println("Premium? (true/false): ");
                            boolean premium=false;
                            try{
                                premium = input.nextBoolean();
                            }catch(InputMismatchException e){
                                System.out.println("Premium é (true/false)!!");
                                input.nextLine();
                                break;
                            }
                            try{
                                Sapatilha sapatilha = new Sapatilha(cod_barras, artigo_novo, estado, num_donos, descricao, 
                                marca, preco_base, estado_utilizacao, tamanho, atacadores, cor, premium, data_lancamento);
                                controlador_Menu_Vintage.addArtigo(sapatilha);
                            }catch (DateTimeException e){
                                System.out.println("Data de lancamento tem que estar no formato (dd-MM-yyyy)!!");
                                break;
                            } catch (AddException e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                            break;
                        }else if (opcaoArtigo ==3){
                            System.out.println("Tamanho: (s/m/l/xl)");
                            input.nextLine();
                            Tshirt.Tamanho tamanho;
                            try{
                                tamanho = Tshirt.Tamanho.valueOf(input.next().toUpperCase());
                            }catch(IllegalArgumentException e){
                                System.out.println("Tamanho tem que ser (s/m/l/xl)!!");
                                break;
                            }
                            System.out.println("Padrao (liso/riscas/palmeiras): ");
                            Tshirt.Padrao padrao;
                            try{
                                padrao = Tshirt.Padrao.valueOf(input.next().toUpperCase());
                            }catch(IllegalArgumentException e){
                                System.out.println("Padrao tem que ser (liso,riscas,palmeiras)!!");
                                break;
                            }
                            Tshirt tshirt = new Tshirt(cod_barras, artigo_novo, estado, num_donos, descricao,
                            marca, preco_base, estado_utilizacao, tamanho, padrao);
                            System.out.println(tshirt.toString());
                            try{
                                controlador_Menu_Vintage.addArtigo(tshirt);
                            }catch (AddException e){
                                System.out.println(e.getMessage());
                                break;
                            }
                            break;
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Tem que escolher entre 1,2 ou 3!!!");
                        input.nextLine();
                    }
                case 10:
                    System.out.println("Data Inicial do Periodo (dd-MM-yyyy):  ");
                    input.nextLine();
                    String datai = input.nextLine();
                    System.out.println("Data Final do Periodo (dd-MM-yyyy):  ");
                    String dataf = input.nextLine();
                    System.out.println("Quantos utilizadores? ");
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
                case 11:
                    GestorArtigos gestorArtigos = new GestorArtigos();
                    System.out.println("Numero de artigos na encomenda");
                    input.nextLine();
                    int numArtigos=0;
                    try{
                        numArtigos = input.nextInt();
                        if (numArtigos>controlador_Menu_Vintage.numArtigos_Vintage()){
                            System.out.println("Nao existem tantos artigos no sistema");
                            break;
                        }
                        input.nextLine();
                    }catch(InputMismatchException e){
                        System.out.println("Numero de artigos é um numero");
                        input.nextLine();
                        break;
                    }
                    System.out.println(controlador_Menu_Vintage.toString_Artigos());
                    for(int i=0;i<numArtigos;i++){
                        System.out.println("Escolha um artigo (codigo de Barras):");
                        String cod_Barras = input.nextLine();
                        Artigo artigo=null;
                        try {
                            artigo= controlador_Menu_Vintage.getArtigo(cod_Barras);
                            gestorArtigos.addArtigo(artigo);
                        }catch(GetException e){
                            System.out.println(e.getMessage());
                            break switchFlag; //parar o switch diretamente
                        }catch(AddException e){
                            System.out.println(e.getMessage());
                            break switchFlag;
                        }
                    }
                    Encomenda.DimensaoEmbalagem dimensaoEmbalagem = Encomenda.DimensaoEmbalagem.pequeno;
                    if (numArtigos>3) dimensaoEmbalagem = Encomenda.DimensaoEmbalagem.medio;
                    else if (numArtigos >7) dimensaoEmbalagem = Encomenda.DimensaoEmbalagem.grande;
                    System.out.println("Taxa de garantia (numerico): ");
                    double taxaGarantia;
                    try{
                        taxaGarantia=input.nextDouble();
                        input.nextLine();
                    }catch(InputMismatchException e){
                        System.out.println("Taxa de garantia é um numero!!");
                        input.nextLine();
                        break;
                    }
                    System.out.println("Data de criação da Encomenda:");
                    String dataCriacao= input.nextLine();
                    System.out.println("Qual o estado da Encomenda? (pendente/paga/expedida/entregue):");
                    Encomenda.Estado estadoEncomenda;
                    String dataEntrega=null;
                    try {
                        estadoEncomenda = Encomenda.Estado.valueOf(input.next().toLowerCase());
                        if (estadoEncomenda.equals(Encomenda.Estado.entregue)){
                            System.out.println("Data de entrega da encomenda (dd-MM-yyyy):");
                            input.nextLine();
                            dataEntrega=input.nextLine();
                        }
                    }catch (IllegalArgumentException e){
                        System.out.println("Dimensao tem que ser pequeno/medio/grande!!");
                        input.nextLine();
                        break;
                    }
                    System.out.println("\n"+controlador_Menu_Vintage.toString_Utilizadores());
                    System.out.println("Escolha um vendedor (codigo de Utilizador):");
                    String codVendedor = input.nextLine();
                    System.out.println("Escolha um comprador (codigo de Utilizador):");
                    String codComprador = input.nextLine();
                    Utilizador vendedor,comprador=null;
                    try {
                        vendedor= controlador_Menu_Vintage.getUtilizador(codVendedor);
                        comprador=controlador_Menu_Vintage.getUtilizador(codComprador);
                        Encomenda encomenda = new Encomenda(gestorArtigos, dimensaoEmbalagem, taxaGarantia, dataCriacao, 
                        dataEntrega, estadoEncomenda, vendedor, comprador);
                        controlador_Menu_Vintage.addEncomenda(encomenda);
                    }catch(GetException e){
                        System.out.println(e.getMessage());
                        break;
                    } catch (AddException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    catch(DateTimeException e){
                        System.out.println("Data tem que estar no formato (dd-MM-yyyy)!!!");
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
