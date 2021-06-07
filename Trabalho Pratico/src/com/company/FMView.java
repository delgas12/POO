package com.company;

import java.security.Guard;
import java.util.*;

public class FMView {


    public static Map.Entry<String,Jogador> getPlayer() throws CamposInvalidos{
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
                if(pos.length != 12) throw new CamposInvalidos("Para o Medio sao necessarios 11 campos separados por virgulas! Sendo os atributos [ReceberBola]");

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
                if(pos.length != 12) throw new CamposInvalidos("Para o Medio sao necessarios 11 campos separados por virgulas! Sendo os atributos [Elasticidade]");
                j = new GuardaRedes(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"GuardaRedes",new ArrayList<>());
                equipa = pos[11];
                break;
            case "defesa":
                if(pos.length != 12) throw new CamposInvalidos("Para o Medio sao necessarios 11 campos separados por virgulas! Sendo os atributos [Força]");
                j = new Defesa(pos[1],Integer.parseInt(pos[2]),Integer.parseInt(pos[3]),Integer.parseInt(pos[4]),Integer.parseInt(pos[5]),Integer.parseInt(pos[6]),
                        Integer.parseInt(pos[7]),Integer.parseInt(pos[8]),Integer.parseInt(pos[9]),Integer.parseInt(pos[10]),"Defesa",new ArrayList<>());
                equipa = pos[11];
                break;
        }
       return new AbstractMap.SimpleEntry(equipa, j);
    }

    private static boolean checkPosition(String pos){
        return pos.equals("defesa") || pos.equals("avançado") || pos.equals("lateral") || pos.equals("medio") || pos.equals("guardaredes");
    }

    private static String getDefesaAtributes(String pos){
        Scanner sp = new Scanner(System.in);

        System.out.println("Introduzir atributos do jogador");
        return sp.nextLine();
    }

    public static String getEquipa(){
        System.out.print("Insira o nome da equipa que pretende adicionar:");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

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

    public static String consultarEquipa(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o nome da equipa a consultar: ");
        return sc.nextLine();
    }

    public static void displayEquipa(Equipa e){
        System.out.println(e.toString());
    }

    public static String consultarJogador(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o nome do jogador a consultar: ");
        return sc.nextLine();
    }

    public static void displayJogador(Jogador j){
        System.out.println(j.toString());
    }

    public static String getPlayerForHability(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o nome do jogador para o qual pretende calcular a habilidade : ");
        return sc.nextLine();
    }

    public static void mostraHabilidade(int habilidade){
        System.out.println("O Jogador tem habilidade: " + habilidade);
    }

    public static String calculaHabilidade(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o nome da Equipa cuja Habilidade quer consultar");
        return sc.nextLine();
    }

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

    public static void displayHabilidadeEquipa(int habilidade){
        System.out.println("A habilidade da equipa é: " + habilidade);
    }

    public static Map.Entry<String, String> escolherEquipas(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual a equipa da casa?");
        String casa = sc.nextLine();
        System.out.println("Qual a equipa de fora?");
        String fora = sc.nextLine();
        return new AbstractMap.SimpleEntry<>(casa, fora);
    }

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

    public static void erros(int err){
        switch (err) {
            case 1 -> System.out.println("A Equipa da casa ainda não foi escolhida");
            case 2 -> System.out.println("A Equipa de fora ainda não foi escolhida");
            case 3 -> System.out.println("Ainda não escolheu os jogadores da quipa da Casa");
            case 4 -> System.out.println("Ainda não escolheu os jogadores da quipa de Fora");
        }
    }

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

    public static void displayListJog(List<Jogador> listJog){
        System.out.println(listJog.toString());
    }

    public static void displayJogadas(String bolaAntes, String bolaDepois, int result){
        Random rand = new Random();
        int rand_int = rand.nextInt(3);
        String[] pBFParaMeioCampo = {"Pontapé de baliza para a equipa da casa vai para o meio campo", "O Guarda Redes lança a jogada para o meio campo!", "Guarda Redes joga longo para a zona média do campo"};
        String[] pBFParaDefFora = {"Guarda Redes decide jogar curto nos defesas", "Bola para os defesas", "Jogada curta pelo Guarda Redes"};
        String[] pBFParaDefCasa = {"Guarda Redes bate longo!", "Guarda Redes joga a bola diretamente para o ataque!", "Bola na frente!"};
        String[] pBCParaMeioCampo = {"Guarda Redes joga no meio campo!", "Guarda Redes mete a bola no meio campo!", "Guarda Redes mete a bola em jogo!"};
        String[] pBCParaDefFora = {"Guarda Redes joga longo!", "Atira para longe ! Grande Guardião!!", "Bola metida no ataque!"};
        String[] pBCParaDefCasa = {"Guarda Redes decide jogar curto nos defesas", "Bola para os defesas", "Jogada curta pelo Guarda Redes"};
        String[] cantoForaParaMeioCampoGolo= {"Golo de cabeça do central!!", "Mas que golaço de pontapé de bicicleta!", "Autogolo! Como é possível!"};
        String[] cantoForaParaMeioCampo = {"Bola aliviada pelos defesas!", "Canto da equipa da casa não dá em nada!", "Guarda Redes soqueia a bola para longe!"};
        String[] cantoForaParaCantoFora = {"A bola acaba na bancada , desviada por um defesa!","A defesa atira para fora!", "UI! UI! Partiram a camera do fotógrafo, e é novo canto para a equipa da casa!"};
        String[] cantoForaParaPBF = {"Canto não dá em nada! Pontapé de baliza para a equipa da casa!", "Mas que desastre! O central não dá uma para a caixa!"};
        String[] cantoForaParaDefFora = {"Alívio do central!", "O Guarda Redes alivia com os punhos!", "Equipa visitante recupera a posse e começa a construir a jogada!"};
        String[] cantoCasaParaMeioCampoGolo = {"E já está! A equipa visitante marcou!" , "Está lá dentroooo! O guarda redes nem teve tempo de pestanejar!","GOLOOO! O guarda redes nao apanha uma!"};
        String[] cantoCasaParaMeioCampo = {"Alivia Freitas!", "Mas que força tem o Guarda Redes, alivia para o meio campo!!", "Mas que grande cabeçada do central!"};
        String[] cantoCasaParaCantoCasa = {"E o centralão resolve!", "Que grande defesa do Guarda Redes! Está a brilhar este jovem!", "Novo canto para a equipa visitante"};
        String[] cantoCasaParaPBC = {"E vai remateeee.... Foi para foraaa...","O homem chutaaaa...e falhaaa","A bola vai entrar.. vai entrar... mas nao entrou! Ponta pé de baliza par aa equipa da casa!"};
        String[] cantoCasaParaDefCasa = {"Alívio da defesa!", "Defesa recomeça a jogada!", "Mas que perigo! O Guarda Redes defende mas para a frente! A recaaaaarga.... Grade defesa! Passa o perigo!"};
        String[] areaForaParaMeioCampoGolo = {"E ele vai , e ele remata e é GOLOOOOOOOOO!","Ta a olhar , ta pensar , disparaaa! GOLOOOOOO","Está mesmo em frente à baliza , tira a as medidas, remataa.... e marcaaaa!"};
        String[] areaForaParaMeioCampo = {"Alivia Freitas! Passa o perigo da equipa da visitante!", "Passe longo da equipa visitante! Lança a jogada no ataque!", "Bola para o meio campo ofensivo da equipa visitante!"};
        String[] areaForaParaCantoFora = {"Remate do jogadoooooor.... e bate num defesa! Para canto!", "Temos mais um canto da equipa da casa!", "Grande remate! Mas que defesa do Guarda Redes! Parece que tem asas este gigante!"};
        String[] areaForaParaPBF = {"E vai um remateee ... acerta num miudo da bancada! É pontapé de baliza para a equipa visitante!","Remate completamente ao lado! O homem deve tar com os olhos tapados! Pontapé de baliza para a equipa visitante", "Remata! E Falha! Ponta pé de baliza para a equipa visitante"};
        String[] areaForaParaDefesaCasa = {"Despacha o perigo da area, o central!", "Mas que potente o corte do Guarda Redes", "E a bola é despachada para a defesa da equipa adversária"};
        String[] areaForaParaDefesaFora = {"E recomeça a jogada pelos centrais...", "Desenvolve a jogada pela direita a equipa visitante!", "Grande corte! Alivia a defesa a equipa visitante!"};
        String[] areaCasaParaMeioCampoGolo = {"Está muito perigoso! É Golooo , a equipa visitante arruina a equipa da casa!","É GOLOOOO! Equipa visitante marca !","Já está lá dentro ! Rasgou a rede ! Equipa visitante aumenta o seu reultado!"};
        String[] areaCasaParaMeioCampo = {"Alivia o perigo o central da equipa da casa! Mas que jogador!", "Tem estado muito bem a equipa da casa! Defesa implacável!!!", "Remate da equipa visitante e bate no defesa! É uma autentica muralha humana!"};
        String[] areaCasaParaCantoCasa = {"Grande Remate...... E que defesaaaaa!! Ganhou asas o Guarda Redes!!!", "Mas que asneira! Foi para as nuvens o remate!!!", "Para fora do estádio! Parece que nem se está a esforçar este avançado...."};
        String[] areaCasaParaPBC ={"E vai um remateee ... saiu fora do estádio! É pontapé de baliza para a equipa da casa!","Remateeee...Muito torto ! Nem com uma baliza maior acertava! Pontapé de baliza para a equipa da casa!","Remata! E Falha! Ponta pé de baliza para a equipa da casa"};
        String[] areaCasaParaDefCasa = {"A equipa da casa consegur evitar o desastre! A equipa da casa pode respirar de alívio!","Passa o perigo da equipa da casa!","Está perigoso! Mas a equipa da casa resolve e atira a bola para fora da area!"};
        String[] areaCasaParaDefFora = {"Que alívio do guarda redes! Está com força o guardião!", "Passa o perigo da equipa da casa!", "Resolve o central da maneira mais simples!"};
        String[] defesaCasaParaMeioCampoGolo = {"Que perigo! O Guarda Redes defende mas para a frente... Olha a recarga, olha o golo...É GOLOOOOOOOOO", "Vai tirar... Vai tirar... GOLOOOO! Mas que protento!", "Golasso do Médio!!"};
        String[] defesaCasaParaMeioCampo = {"A equipa da casa avança para o meio campo!","Os da casa assumem controlo e já estão no meio campo!","vai o ataque dos da casa , estão no meio campo!"};
        String[] defesaCasaParaCantoCasa = {"A equipa visitante avança ! E conseguem canto!","Sai um remate da equipa visitante! Mas o guarda redes atira para fora","Vao em diração à baliza.. mas a bola é desvidada por um defesa e sai na linha de fundo!"};
        String[] defesaCasaParaPBC = {"Mas que grande remate....!!! Ao lado! Passa o Perogo!", "É pontapé de baliza para a equipa da casa", "Mas que disparate! Sai longe da baliza o remate!"};
        String[] defesaCasaParaAreaCasa = {"E a equipa visitante avança !","Vai a equipa visitante no ataque","Está perigoso para a equipa da casa! A equipa visitante já estaá na sua area"};
        String[] defesaCasaParaDefFora = {"E os defesas da equipa da casa aliviam para muito longe! ","Está seguro ! A bolo é desviada para o outro lado do campo! Grande trabalho da qeuipa da casa! ","E a equipa da casa tira a bola do perigo!"};
        String[] defesaForaMeioCampoGolo ={"Está perigosoo! E é GOLOOO da equipa da casa!","Vai tirar... Vai tirar... GOLOOOO! Equipa da casa marca!","Golasso do Médio!! Equipa da casa aumenta o resultado!"};
        String[] defForaParaMeioCampo = {"A formação defensiva começa a jogada", "A defesa constroi a jogada pela direita para os médios", "Equipa da Casa joga para trás para reconstruir a jogada"};
        String[] defForaParaPBF  = {"Remate da equipa da casa passa a rasar o poste!", "Remate da equipa da casa... passa o perigo!", "Remate de meia distância... Mas que asneira! Foi para as nuvens!"};
        String[] defForaParaCantoFora = {"Canto para a equipa da casa!", "Olha o remaaaate.... Defesa do guardião! Espetacular!", "Mas que asneira da equipa visitante... Canto para a equipa da casa"};
        String[] defForaParaAreaFora  = {"E a equipa da casa avança !","Vai a equipa da casa no ataque","Está perigoso para a equipa visitante ! A equipa da casa já está na sua àrea"};
        String[] defForaParaDefCasa = {"E os defesas da equipa visitante aliviam para muito longe! ","Está seguro ! A bolo é desviada para o outro lado do campo! Grande trabalho da visitante! ","E a equipa visitante tira a bola do perigo!"};
        String[] meioCampoGoloCasa = {"Sai um remateee.... maluco do meio campo ...e MARCAAA ! É GOLO PARA A EQUIPA DA CASA!","VAI DO MEIO CAMPO ! E É GOLOOO! INCRIVEL A EQUIPA DA CASA","UM REMATE DO MEIO CAMPO ATERRA NAS REDES DA EQUIPA VISITANTE"};
        String[] meioCampoGoloFora = {"Sai um remateee.... maluco do meio campo ...e MARCAAA ! É GOLO PARA A EQUIPA VISITANTE!","VAI DO MEIO CAMPO ! E É GOLOOO! INCRIVEL A EQUIPA VISITANTE","UM REMATE DO MEIO CAMPO ATERRA NAS REDES DA EQUIPA DA CASA"};
        String[] meioCampoParaAreaCasa = {"Sai a equipa visitante a jogar e avança para a area da equipa da casa", "A equipa visitante já vai na area da equipa da casa", "A equipa da casa a jogar para trás e analisar o jogo"};
        String[] meioCampoParaAreaFora = {"Sai a equipa da casa a jogar e avança para a area da equipa visitante", "A equipa da casa já vai na area da equipa visitante", "A equipa visitante a jogar para trás e analisar o jogo"};
        String[] meioCampoParaDefFora = {"Vai a equipa da casa a construir a jogada e a avançar no meio campo da equipa visitante","Começa a equipa da casa a pressionar","Equipa da casa já vai no ataque"};
        String[] meioCampoParaDefCasa = {"Vai a equipa visitante a construir a jogada e a avançar no meio campo da equipa da casa","Começa a equipa visitante a pressionar","Equipa visitante já vai no ataque"};

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
}
