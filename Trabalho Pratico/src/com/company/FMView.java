package com.company;

import java.security.Guard;
import java.sql.SQLOutput;
import java.util.*;

public class FMView {

    /**
     *  Metodo responsÁvel por construir e executar o Menu De Gestão
     * @return um par com a equipa do jogador e o objeto Jogador respetivo
     **/

    public static Map.Entry<String,Jogador> getPlayer() throws CamposInvalidos, NumberFormatException{
        Jogador j = null ;
        System.out.println("**** Introduzir Jogador ****");
        System.out.println("Posição,Nome,Numero,Velocidade,Resistencia,Destreza,Impulsao,JogoDeCabeça,Remate,Passe,Atributos Da posição,Equipa");
        Scanner sc = new Scanner(System.in);
        String[] pos =  sc.nextLine().split(",");
        String equipa = "";
        if(!checkPosition(pos[0].toLowerCase(Locale.ROOT))) throw new CamposInvalidos("Posição Introduzida INVALIDA!");
        switch (pos[0].toLowerCase(Locale.ROOT)){
            case "lateral":
                if(pos.length != 12) throw new CamposInvalidos("Para o Lateral sao necessarios 11 campos separados por virgulas! Sendo os seus atributos [Cruzamento]");
                j = new Lateral(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                                       Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"Lateral",new ArrayList<>());
                equipa = pos[11];
                break;
            case "medio":
                if(pos.length != 12) throw new CamposInvalidos("Para o Medio sao necessarios 11 campos separados por virgulas! Sendo os atributos [RecuperacaoBola]");

                j = new Medio(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"Medio",new ArrayList<>());
                equipa = pos[11];
                break;
            case "avançado":
                if(pos.length != 13) throw new CamposInvalidos("Para o Avançado sao necessarios 12 campos separados por virgulas! Sendo os atributos [Agilidade,Finalização]");
                j = new Avancado(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),Integer.parseInt(pos[11]),"Avançado",new ArrayList<>());
                equipa = pos[12];
                break;
            case "guardaredes":
                if(pos.length != 12) throw new CamposInvalidos("Para o GuardaRedes sao necessarios 11 campos separados por virgulas! Sendo os atributos [Elasticidade]");
                j = new GuardaRedes(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"GuardaRedes",new ArrayList<>());
                equipa = pos[11];
                break;
            case "defesa":
                if(pos.length != 12) throw new CamposInvalidos("Para o Defesa sao necessarios 11 campos separados por virgulas! Sendo os atributos [Força]");
                j = new Defesa(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"Defesa",new ArrayList<>());
                equipa = pos[11];
                break;
        }
       return new AbstractMap.SimpleEntry(equipa, j);
    }

    /**
     * Método para verificar se a posição especificada é válida, ou seja, se existe
     * @param pos String com a posição especificada
     * @return um par com a equipa do jogador e o objeto Jogador respetivo
     **/

    private static boolean checkPosition(String pos){
        return pos.equals("defesa") || pos.equals("avançado") || pos.equals("lateral") || pos.equals("medio") || pos.equals("guardaredes");
    }

    /**
    * Método para pedir ao utilizador para introduzir o nome de uma equipa
    * @return String com o nome da equipa especificada pelo utilizador
    **/


    public static String getEquipa(){
        System.out.print("Insira o nome da equipa a adicionar:");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Método responsável por pedir ao utilizador para introduzir os dados necessários para realizar a tranferência
     * @return um par com o nome do jogador e com um outro par que contém a equipa de origem e a equipa destino
     **/

    public static Map.Entry<String, Map.Entry<String, String>> getTransferencia(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o nome da equipa origem: ");
        String equipaOrigem = sc.nextLine();
        System.out.print("Insira o nome do jogador: ");
        String jogador = sc.nextLine();
        System.out.print("Insira o nome da equipa destino: ");
        String equipaDestino = sc.nextLine();
        return new AbstractMap.SimpleEntry(jogador, new AbstractMap.SimpleEntry(equipaOrigem, equipaDestino));
    }

    /**
     *  Metodo responsável pela operação de IO que pede ao utlilizador para escrever o nome de uma equipa
     *  @return retorna o nome da equipa introduzido pelo utilizador no srdin
     **/

    public static String consultarEquipa(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o nome da equipa a consultar: ");
        return sc.nextLine();
    }

    /**
     * Método responsável por imprimir uma equipa passada por argumento
     **/

    public static void displayEquipa(Equipa e){
        System.out.println(e.toString());
    }

    /**
     * Método responsável por pedir ao utilizador o nome do jogador a consultar
     * @return a String com o nome do Jogador
     **/

    public static String consultarJogador(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o nome do jogador a consultar: ");
        return sc.nextLine();
    }

    /**
     * Método responável por imprimir um jogador
     **/
    public static void displayJogador(Jogador j){
        System.out.println(j.toString());
    }

    /**
     * Método responsável por imprimir as características do Jogador, incluindo todas as suas habilidades
     **/

    public static void displayHabilidadeJogador(Jogador j){
        System.out.println(j.toStringHabilidades());
    }


    /**
     * Método responsável por pedir ao utilizador o nome do jogador para o qual se pretende calcular a habilidade
     * @return String com o nome do jogador para o qual se pretende calcular a habilidade
     **/
    public static String getPlayerForHability(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o nome do jogador para o qual pretende calcular a habilidade : ");
        return sc.nextLine();
    }

    /**
     * Método responsável por mostrar o valor da habilidadede um jogador
     * @param habilidade valor da habilidade do jogador
     **/

    public static void mostraHabilidade(int habilidade){
        System.out.println("O Jogador tem habilidade: " + habilidade);
    }

    /**
     * Método responsável por pedir ao utilizador o nome do equipa para o qual se pretende calcular a habilidade
     * @return String com o nome do jogador especificado
     **/

    public static String calculaHabilidade(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o nome da Equipa cuja Habilidade quer consultar");
        return sc.nextLine();
    }

    /**
     * Método responsável por pedir ao utilizador para introduzir os dados necessários a compor a tática da equipa
     * @return String com o número de jogadores de cada posição
     **/

    public static String pedeAlinhamento(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Quantos Defesas?");
        String defesas = sc.nextLine();
        System.out.println("Quantos Laterais?");
        String laterais = sc.nextLine();
        System.out.println("Quantos Médios?");
        String medios = sc.nextLine();
        System.out.println("Quantos Avançados?");
        String avancados = sc.nextLine();
        return defesas + "-" + laterais + "-" + medios + "-" + avancados;
    }

    /**
     * Método responsável por imprimir a habilidade da equipa
     * @param habilidade valor da habilidade da equipa
     **/

    public static void displayHabilidadeEquipa(int habilidade){
        System.out.println("A habilidade da equipa é: " + habilidade);
    }

    /**
     * Método responsável pela operacao de IO que permite ao utilizador inicializar um jogo , itroduzindo qual a equipa de casa e qual a de fora
     * @return retorna um par com os nomes das duas equipas definido como um map entry
     **/
    public static Map.Entry<String, String> escolherEquipas(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual a equipa da casa?");
        String casa = sc.nextLine();
        System.out.println("Qual a equipa de fora?");
        String fora = sc.nextLine();
        return new AbstractMap.SimpleEntry<>(casa, fora);
    }

/*
    public static List<Integer> mostraJogadores(Equipa e){
        Scanner sc = new Scanner(System.in);
        List<Integer> result = new ArrayList<>();
        for (Jogador j : e.getJogadores().values()){
            System.out.println(j.toString());
        }
        System.out.println("Escolha os Titulares: ");
        for (int i = 0; i < 11; i++){
            result.add(sc.nextInt());
        }
        return result;
    }
*/

    /**
     * Método usado para imprimir mensagens de erro
     * @param err int que corresponde a uma das possíveis mensagens de erro
     **/
    public static void erros(int err){
        switch (err) {
            case 1 -> System.out.println("A Equipa da casa ainda não foi escolhida");
            case 2 -> System.out.println("A Equipa de fora ainda não foi escolhida");
            case 3 -> System.out.println("Ainda não escolheu os jogadores da quipa da Casa");
            case 4 -> System.out.println("Ainda não escolheu os jogadores da quipa de Fora");
        }
    }

    /**
     * Método usado para realizar IO quando for necessario fazer uma substituição
     * @return retorna a equipa na qual o utilizador pretende fazer substituições , casa ou fora
     **/
    public static String pedeSubstituicao(){
        Scanner sc = new Scanner(System.in);
        String equipa = "";
        Map.Entry<String, Map.Entry<String, String>> res = null;
        System.out.print("Deseja fazer substituicao? (Sim/Nao) ");
        String sub = sc.nextLine();
        sub = sub.toLowerCase(Locale.ROOT);
        if (sub.equals("sim")){
            System.out.print("Para que equipa? (Casa/Fora) ");
            equipa = sc.nextLine();
        }
        return equipa;
    }

    /**
     * Método usado para realizar IO quando for necessario fazer uma substituição
     * @param listJog corresponde à lista dos jogadores da equipa que nao fazem parte dos jogadores titulares
     * @param titulares corresponde à lista dos jogadores titulares da equipa
     * @return retorna um par com os jogadores introduzidos pelo utilizador , o que vai entrar para jogo e o que vai sair
     **/
    public static Map.Entry<String, String> pedeJogadoresSub(List<Jogador> listJog, List<Jogador> titulares){
        Scanner sc = new Scanner(System.in);
        System.out.print("Lista do Banco:");
        displayListJog(listJog);
        System.out.print("Qual o Jogador que vai entrar?");
        String jIn = sc.nextLine();
        System.out.print("Lista os Titulares:");
        displayListJog(titulares);
        System.out.print("Qual o Jogador que vai sair?");
        String jOut = sc.nextLine();
        return new AbstractMap.SimpleEntry<>(jIn, jOut);
    }

    /**
     * Método usado para imprimir uma lista de jogadores
     * @param listJog corresponde à lista dos jogadores
     **/
    public static void displayListJog(List<Jogador> listJog){
        System.out.println(listJog.toString());
    }

    /**
     * Método usado para imprimir o nome das equipas e a sua habilidade
     * @param casa String que correponde ao nome da equipa da casa
     * @param fora String que corresponde ao nome da equipa de fora
     * @param habCasa que corresponde à habilidade da equipa da casa
     * @param habFora que corresponde à habilidade da equioa de fora
     **/
    public static void infoInicioJogo(String casa, String fora, int habCasa, int habFora){
        System.out.println(casa + "(" + habCasa + ") - " + fora + " (" + habFora + ")");
    }


    /**
     * Método usado para imprimir eventos que podem estar a ocorrer durante o jogo
     * @param bolaAntes string que indica a posição da bola no campo num instante anterior
     * @param bolaDepois string que indica a posição da bola num instante seguinte
     * @param casa string que armazena o nome da equipa da casa
     * @param fora string que armazena o nome da equipa de fora
     **/
    public static void displayJogadas(String bolaAntes, String bolaDepois, int result, String casa, String fora){
        Random rand = new Random();
        int rand_int = rand.nextInt(3);
        String[] pBFParaMeioCampo = {"Pontapé de baliza para " + casa +"vai para o meio campo", "O Guarda Redes lança a jogada para o meio campo!", "Guarda Redes joga longo para a zona média do campo"};
        String[] pBFParaDefFora = {"Guarda Redes decide jogar curto nos defesas", "Bola para os defesas", "Jogada curta pelo Guarda Redes"};
        String[] pBFParaDefCasa = {"Guarda Redes bate longo!", "Guarda Redes joga a bola diretamente para o ataque!", "Bola na frente!"};
        String[] pBCParaMeioCampo = {"Guarda Redes joga no meio campo!", "Guarda Redes mete a bola no meio campo!", "Guarda Redes mete a bola em jogo!"};
        String[] pBCParaDefFora = {"Guarda Redes joga longo!", "Atira para longe ! Grande Guardião!!", "Bola metida no ataque!"};
        String[] pBCParaDefCasa = {"Guarda Redes decide jogar curto nos defesas", "Bola para os defesas", "Jogada curta pelo Guarda Redes"};
        String[] cantoForaParaMeioCampoGolo= {"Golo de cabeça do central!!", "Mas que golaço de pontapé de bicicleta!", "Autogolo! Como é possível!"};
        String[] cantoForaParaMeioCampo = {"Bola aliviada pelos defesas!", "Canto para " + casa + "não dá em nada!", "Guarda Redes soqueia a bola para longe!"};
        String[] cantoForaParaCantoFora = {"A bola acaba na bancada , desviada por um defesa!, Novo Canto Para " + casa,"A defesa atira para fora!", "UI! UI! Partiram a camera do fotógrafo, e é novo canto para " + casa + "!"};
        String[] cantoForaParaPBF = {"Canto não dá em nada! Pontapé de baliza para "  + casa, "Mas que desastre! O central não dá uma para a caixa!", "Que asneira! O cabeceamento pareceu um corte de um defesa! Mas que asneira! Passou completamnete ao lado!"};
        String[] cantoForaParaDefFora = {"Alívio do central!", "O Guarda Redes alivia com os punhos!", "" + fora + " recupera a posse e começa a construir a jogada!"};
        String[] cantoCasaParaMeioCampoGolo = {"E já está! " + fora + " marcou!" , "Está lá dentroooo! O guarda redes nem teve tempo de pestanejar!","GOLOOO! O guarda redes nao apanha uma!"};
        String[] cantoCasaParaMeioCampo = {"Alivia Freitas!", "Mas que força tem o Guarda Redes, alivia para o meio campo!!", "Mas que grande cabeçada do central!"};
        String[] cantoCasaParaCantoCasa = {"E o centralão resolve!", "Que grande defesa do Guarda Redes! Está a brilhar este jovem!", "Novo canto para " + fora};
        String[] cantoCasaParaPBC = {"E vai remateeee.... Foi para foraaa...","O homem chutaaaa...e falhaaa","A bola vai entrar.. vai entrar... mas nao entrou! Ponta pé de baliza para "+ casa + "!"};
        String[] cantoCasaParaDefCasa = {"Alívio da defesa!", "Defesa recomeça a jogada!", "Mas que perigo! O Guarda Redes defende mas para a frente! A recaaaaarga.... Grade defesa! Passa o perigo!"};
        String[] areaForaParaMeioCampoGolo = {"E ele vai , e ele remata e é GOLOOOOOOOOO!","Ta a olhar , ta pensar , disparaaa! GOLOOOOOO","Está mesmo em frente à baliza , tira a as medidas, remataa.... e marcaaaa!"};
        String[] areaForaParaMeioCampo = {"Alivia Freitas! Passa o perigo para " + fora + "!", "Passe longo de " + fora + "! Lança a jogada no ataque!", "Bola para o meio campo ofensivo de " +fora + "!"};
        String[] areaForaParaCantoFora = {"Remate do jogadoooooor.... e bate num defesa! Para canto!", "Temos mais um canto para " +casa+"!", "Grande remate! Mas que defesa do Guarda Redes! Parece que tem asas este gigante!"};
        String[] areaForaParaPBF = {"E vai um remateee ... acerta num miúdo da bancada! É pontapé de baliza para " + fora + "!","Remate completamente ao lado! O homem deve tar com os olhos tapados! Pontapé de baliza para " + fora, "Remata! E Falha! Ponta pé de baliza para " + fora};
        String[] areaForaParaDefesaCasa = {"Despacha o perigo da area, o central!", "Mas que potente o corte do Guarda Redes", "E a bola é despachada para a defesa da equipa adversária"};
        String[] areaForaParaDefesaFora = {"E recomeça a jogada pelos centrais...", "Desenvolve a jogada pela direita " + fora + "!", "Grande corte! Alivia a defesa " + fora + "!"};
        String[] areaCasaParaMeioCampoGolo = {"Está muito perigoso! É Golooo, " + fora + " arruina " + casa + "!","É GOLOOOO! Equipa " + fora + " marca !","Já está lá dentro ! Rasgou a rede ! Equipa " + fora + " aumenta o seu reultado!"};
        String[] areaCasaParaMeioCampo = {"Alivia o perigo o central de " + casa + "! Mas que jogador!", "Tem estado muito bem " + casa + "! Defesa implacável!!!", "Remate de " + fora + " e bate no defesa! É uma autentica muralha humana!"};
        String[] areaCasaParaCantoCasa = {"Grande Remate...... E que defesaaaaa!! Ganhou asas o Guarda Redes!!!", "Mas que asneira! Foi para as nuvens o remate!!!", "Para fora do estádio! Parece que nem se está a esforçar este avançado...."};
        String[] areaCasaParaPBC ={"E vai um remateee ... saiu fora do estádio! É pontapé de baliza para " + casa + "!","Remateeee...Muito torto ! Nem com uma baliza maior acertava! Pontapé de baliza para "+casa+"!","Remata! E Falha! Ponta pé de baliza para "+casa};
        String[] areaCasaParaDefCasa = {"" + casa + " consegue evitar o desastre! "+casa+" pode respirar de alívio!","Passa o perigo "+casa+"!","Está perigoso! Mas "+casa+" resolve e atira a bola para fora da area!"};
        String[] areaCasaParaDefFora = {"Que alívio do guarda redes! Está com força o guardião!", "Passa o perigo de " + casa + "!", "Resolve o central da maneira mais simples!"};
        String[] defesaCasaParaMeioCampoGolo = {"Que perigo! O Guarda Redes defende mas para a frente... Olha a recarga, olha o golo...É GOLOOOOOOOOO", "Vai tirar... Vai tirar... GOLOOOO! Mas que protento!", "Golaço do Médio!!"};
        String[] defesaCasaParaMeioCampo = {casa + " avança para o meio campo!",casa + " assumem controlo e já estão no meio campo!","vai o ataque dos da "+casa+" , estão no meio campo!"};
        String[] defesaCasaParaCantoCasa = {"" + fora + " avança! E conseguem canto!","Sai um remate de " + fora + "! Mas o guarda redes atira para fora","Vão em direção à baliza.. mas a bola é desviada por um defesa e sai na linha de fundo!"};
        String[] defesaCasaParaPBC = {"Mas que grande remate....!!! Ao lado! Passa o Perigo!", "É pontapé de baliza para"+casa, "Mas que disparate! Sai longe da baliza o remate!"};
        String[] defesaCasaParaAreaCasa = {"E " + fora + " avança !","Vai " + fora + " no ataque","Está perigoso para "+casa+"! " + fora + " já está na sua area"};
        String[] defesaCasaParaDefFora = {"E os defesas de "+casa +"aliviam para muito longe! ","Está seguro ! A bola é desviada para o outro lado do campo! Grande trabalho da" + casa,casa+" tira a bola do perigo!"};
        String[] defesaForaMeioCampoGolo ={"Está perigosoo! E é GOLOOO da equipa de " + casa ,"Vai tirar... Vai tirar... GOLOOOO! " + casa +" marca!","Golaço do Médio!! "+casa+" aumenta o resultado!"};
        String[] defForaParaMeioCampo = {"A formação defensiva começa a jogada", "A defesa constroi a jogada pela direita para os médios", casa + "joga para trás para reconstruir a jogada"};
        String[] defForaParaPBF  = {"Remate da "+ casa +" passa a rasar o poste!", "Remate de "+ casa+ "... passa o perigo!", "Remate de meia distância... Mas que asneira! Foi para as nuvens!"};
        String[] defForaParaCantoFora = {"Canto para "+casa+"!", "Olha o remaaaate.... Defesa do guardião! Espetacular!", "Mas que asneira de " + fora + "... Canto para "+casa};
        String[] defForaParaAreaFora  = {casa +" avança !","Vai "+casa+" no ataque","Está perigoso para " + fora + "! " + casa + " já está na sua àrea"};
        String[] defForaParaDefCasa = {"E os defesas de " + fora + " aliviam para muito longe! ","Está seguro ! A bola é desviada para o outro lado do campo! Grande trabalho de " + fora + "! ","E " + fora + " tira a bola do perigo!"};
        String[] meioCampoGoloCasa = {"Sai um remateee.... maluco do meio campo ...e MARCAAA ! É GOLO PARA "+casa+"!","VAI DO MEIO CAMPO ! E É GOLOOO! INCRÍVEL "+casa,"UM REMATE DO MEIO CAMPO ATERRA NAS REDES DE " + fora};
        String[] meioCampoGoloFora = {"Sai um remateee.... maluco do meio campo ...e MARCAAA ! É GOLO PARA A " +fora+ "!","VAI DO MEIO CAMPO ! E É GOLOOO! INCRÍVEL " + fora,"UM REMATE DO MEIO CAMPO ATERRA NAS REDES DA EQUIPA DA "+casa};
        String[] meioCampoParaAreaCasa = {"Sai "+fora +" a jogar e avança para "+casa, "" + fora + " já vai na area da equipa"+casa, casa+" vai jogar para trás e analisar o jogo"};
        String[] meioCampoParaAreaFora = {"Sai " +casa+" a jogar e avança para a area de " + fora , casa + "já vai na area de " + fora , fora + " a jogar para trás e analisar o jogo"};
        String[] meioCampoParaDefFora = {"Vai "+casa+ " a construir a jogada e a avançar no meio campo de " + fora,"Começa " +casa + " a pressionar",casa + " já vai no ataque"};
        String[] meioCampoParaDefCasa = {"Vai "+fora+" a construir a jogada e a avançar no meio campo de " + casa,"Começa " + fora+ " a pressionar",fora+ " já vai no ataque"};

        if(bolaDepois.equals("Meio Campo") && bolaAntes.equals("Pontape de Baliza Fora")){
            System.out.println(pBFParaMeioCampo[rand_int]);
        }
        else if(bolaDepois.equals("Defesa Fora") && bolaAntes.equals("Pontape de Baliza Fora")){
            System.out.println(pBFParaDefFora[rand_int]);
        }
        else if(bolaDepois.equals("Defesa Casa") && bolaAntes.equals("Pontape de Baliza Fora")){
            System.out.println(pBFParaDefCasa[rand_int]);
        }


        if(bolaDepois.equals("Meio Campo") && bolaAntes.equals("Pontape de Baliza Casa")){
            System.out.println(pBCParaMeioCampo[rand_int]);
        }
        else if(bolaDepois.equals("Defesa Fora") && bolaAntes.equals("Pontape de Baliza Casa")){
            System.out.println(pBCParaDefFora[rand_int]);
        }
        else if(bolaDepois.equals("Defesa Casa") && bolaAntes.equals("Pontape de Baliza Casa")){
            System.out.println(pBCParaDefCasa[rand_int]);
        }


        if(bolaDepois.equals("Meio Campo") && (result == 1) && bolaAntes.equals("Canto Fora")){
            System.out.println(cantoForaParaMeioCampoGolo[rand_int]);
        }
        else if(bolaDepois.equals("Meio Campo") && bolaAntes.equals("Canto Fora")){
             System.out.println(cantoForaParaMeioCampo[rand_int]);
        }
        else if(bolaDepois.equals("Canto Fora") && bolaAntes.equals("Canto Fora")){
            System.out.println(cantoForaParaCantoFora[rand_int]);
        }
        else if(bolaDepois.equals("Pontape de Baliza Fora") && bolaAntes.equals("Canto Fora")){
            System.out.println(cantoForaParaPBF[rand_int]);
        }
        else if(bolaDepois.equals("Defesa Fora") && bolaAntes.equals("Canto Fora")){
            System.out.println(cantoForaParaDefFora[rand_int]);
        }


        if(bolaDepois.equals("Meio Campo") && (result==2) && bolaAntes.equals("Canto Casa")){
            System.out.println(cantoCasaParaMeioCampoGolo[rand_int]);
        }
        else if(bolaDepois.equals("Meio Campo") && bolaAntes.equals("Canto Casa")){
            System.out.println(cantoCasaParaMeioCampo[rand_int]);
        }
        else if(bolaDepois.equals("Canto Casa") && bolaAntes.equals("Canto Casa")){
            System.out.println(cantoCasaParaCantoCasa[rand_int]);
        }
        else if(bolaDepois.equals("Pontape de Baliza Casa") && bolaAntes.equals("Canto Casa")){
            System.out.println(cantoCasaParaPBC[rand_int]);

        }
        else if(bolaDepois.equals("Defesa Casa") && bolaAntes.equals("Canto Casa")){
            System.out.println(cantoCasaParaDefCasa[rand_int]);

        }


        if(bolaDepois.equals("Meio Campo") && (result == 1) && bolaAntes.equals("Area Fora")){
            System.out.println(areaForaParaMeioCampoGolo[rand_int]);
        }
        else if(bolaDepois.equals("Meio Campo") && bolaAntes.equals("Area Fora")){
            System.out.println(areaForaParaMeioCampo[rand_int]);
        }
        else if(bolaDepois.equals("Canto Fora") && bolaAntes.equals("Area Fora")){
            System.out.println(areaForaParaCantoFora[rand_int]);
        }
        else if(bolaDepois.equals("Pontape de Baliza Fora") && bolaAntes.equals("Area Fora")){
            System.out.println(areaForaParaPBF[rand_int]);
        }
        else if(bolaDepois.equals("Defesa Casa") && bolaAntes.equals("Area Fora")){
            System.out.println(areaForaParaDefesaCasa[rand_int]);
        }
        else if(bolaDepois.equals("Defesa Fora") && bolaAntes.equals("Area Fora")){
            System.out.println(areaForaParaDefesaFora[rand_int]);
        }


        if(bolaDepois.equals("Meio Campo") && (result == 2) && bolaAntes.equals("Area Casa")){
            System.out.println(areaCasaParaMeioCampoGolo[rand_int]);
        }
        else if(bolaDepois.equals("Meio Campo") && bolaAntes.equals("Area Casa")){
            System.out.println(areaCasaParaMeioCampo[rand_int]);
        }
        else if(bolaDepois.equals("Canto Casa") && bolaAntes.equals("Area Casa")){
            System.out.println(areaCasaParaCantoCasa[rand_int]);
        }
        else if(bolaDepois.equals("Pontape de Baliza Casa") && bolaAntes.equals("Area Casa")){
            System.out.println(areaCasaParaPBC[rand_int]);
        }
        else if(bolaDepois.equals("Defesa Casa") && bolaAntes.equals("Area Casa")){
            System.out.println(areaCasaParaDefCasa[rand_int]);
        }
        else if(bolaDepois.equals("Defesa Fora") && bolaAntes.equals("Area Casa")){
            System.out.println(areaCasaParaDefFora[rand_int]);
        }



        if(bolaDepois.equals("Meio Campo") && (result==2) && bolaAntes.equals("Defesa Casa")){
            System.out.println(defesaCasaParaMeioCampoGolo[rand_int]);
        }
        else if(bolaDepois.equals("Meio Campo") && bolaAntes.equals("Defesa Casa")){
            System.out.println(defesaCasaParaMeioCampo[rand_int]);
        }
        else if(bolaDepois.equals("Canto Casa") && bolaAntes.equals("Defesa Casa")){
            System.out.println(defesaCasaParaCantoCasa[rand_int]);
        }
        else if(bolaDepois.equals("Pontape de Baliza Casa") && bolaAntes.equals("Defesa Casa")){
            System.out.println(defesaCasaParaPBC[rand_int]);
        }
        else if(bolaDepois.equals("Area Casa") && bolaAntes.equals("Defesa Casa")){
            System.out.println(defesaCasaParaAreaCasa[rand_int]);
        }
        else if(bolaDepois.equals("Defesa Fora") && bolaAntes.equals("Defesa Casa")){
            System.out.println(defesaCasaParaDefFora[rand_int]);
        }


        if(bolaDepois.equals("Meio Campo") && (result==1) && bolaAntes.equals("Defesa Fora")){
            System.out.println(defesaForaMeioCampoGolo[rand_int]);
        }
        else if(bolaDepois.equals("Meio Campo") && bolaAntes.equals("Defesa Fora")){
            System.out.println(defForaParaMeioCampo[rand_int]);
        }
        else if(bolaDepois.equals("Canto Fora") && bolaAntes.equals("Defesa Fora")){
            System.out.println(defForaParaCantoFora[rand_int]);
        }
        else if(bolaDepois.equals("Pontape de Baliza Fora") && bolaAntes.equals("Defesa Fora")){
            System.out.println(defForaParaPBF[rand_int]);
        }
        else if(bolaDepois.equals("Area Fora") && bolaAntes.equals("Defesa Fora")){
            System.out.println(defForaParaAreaFora[rand_int]);
        }
        else if(bolaDepois.equals("Defesa Casa") && bolaAntes.equals("Defesa Fora")){
            System.out.println(defForaParaDefCasa[rand_int]);
        }


        if(bolaDepois.equals("Meio Campo") && (result==1) && bolaAntes.equals("Meio Campo")){
            System.out.println(meioCampoGoloCasa[rand_int]);
        }
        else if(bolaDepois.equals("Meio Campo")&&  (result==2) && bolaAntes.equals("Meio Campo")){
            System.out.println(meioCampoGoloFora[rand_int]);
        }
        else if(bolaDepois.equals("Area Casa") && bolaAntes.equals("Meio Campo")){
            System.out.println(meioCampoParaAreaCasa[rand_int]);
        }
        else if(bolaDepois.equals("Area Fora") && bolaAntes.equals("Meio Campoa")){
            System.out.println(meioCampoParaAreaFora[rand_int]);
        }
        else if(bolaDepois.equals("Defesa Fora") && bolaAntes.equals("Meio Campo")){
            System.out.println(meioCampoParaDefFora[rand_int]);
        }
        else if(bolaDepois.equals("Defesa Casa") && bolaAntes.equals("Meio Campo")){
            System.out.println(meioCampoParaDefCasa[rand_int]);
        }

    }

    /**
     * Método usado para imprimir o resultado do jogo
     * @param nomeCasa string que indica o nome da equipa da casa
     * @param nomeFora string que indica o nome da equipa de fora
     * @param golosCasa numero de golos da equipa da casa
     * @param golosFora numero de golos da equipa de fora
     **/
    public static void displayResultado(String nomeCasa, String nomeFora, int golosCasa, int golosFora){
        if (golosCasa > golosFora){
            System.out.println("E o árbitro apita, chegamos ao fim do jogo! " + nomeCasa + " sai vitoriosa!");
            System.out.println("" + nomeCasa + " " + golosCasa + " - " + golosFora + " " + nomeFora);
        }
        else if (golosCasa < golosFora){
            System.out.println("E o árbitro apita, chegamos ao fim do jogo! " + nomeFora + " sai vitoriosa!");
            System.out.println("" + nomeCasa + " " + golosCasa + " - " + golosFora + " " + nomeFora);
        }
        else if (golosCasa != 0 && golosFora != 0){
            System.out.println("Saímos desta noite com o marcador empatado! Mas foi um jogo intenso e com grande qualidade! Ambas as equipas estão de parabéns!");
            System.out.println("" + nomeCasa + " " + golosCasa + " - " + golosFora + " " + nomeFora);
        }
        else{
            System.out.println("Saímos desta noite de jogo sem golos... Um jogo morto sem grande emoção, no entanto, a defesa de ambas a equipa esteve muito bem!");
            System.out.println("" + nomeCasa + " " + golosCasa + " - " + golosFora + " " + nomeFora);
        }
    }

    /**
     * Método usado para realizar IO
     * @return retorna uma string com o nome do ficheiro que o utilizador pretende abrir
     **/
    public static String pedeNomeFicheiro(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Qual é o nome do Ficheiro do qual quer carregar o estado? ");
        return sc.nextLine();
    }
    /**
     * Método usado para realizar IO
     **/
    public static void parseCompleto(){
        System.out.println("**** FICHEIRO LIDO ****");
    }

}
