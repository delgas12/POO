package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class FMApp {
    public static void main(String[] args){

        Liga l = new Liga();

        String[] s = {"**** FM_APP ****\n", "Gerir a Liga", "Menu de Consulta","Jogar","Carregar Estado De Um Ficheiro de texto", "Carregar Estado De Um Ficheiro de Objetos", "Guardar Estado Em Ficheiro de Objetos"};
        Menu start = new Menu(s);
        Jogador j;
        String jogadorConsultar = "";
        String equipa = "";
        int getOpt = -1;
        while(getOpt != 0){
            start.execute();
            getOpt = start.getOption();
            switch (getOpt){
                case 1:
                    menuGestao(l);
                    break;
                case 2:
                    menuConsulta(l);
                    break;
                case 3:
                    menuJogo(l);
                    break;
                case 4:
                    Parser p;
                    try {
                        System.out.println("correu");
                        p = new Parser();
                        p.parse();
                        l.setEquipas(p.getEquipas());
                        l.setJogos(p.getJogos());
                    }
                    catch (LinhaIncorretaException | InsufficientPlayers e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    String nomeFichRead = FMView.pedeNomeFicheiro();
                    try{
                        l = Liga.readObj(nomeFichRead);
                    }
                    catch (IOException | ClassNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    String nomeFichWrite = FMView.pedeNomeFicheiro();
                    try{
                        l.guardaBin(nomeFichWrite);
                    }
                    catch(IOException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    break;
            }
        }
    }
    /**
     *  Metodo responsável por construir e executar o Menu De Gestão
     * @param l Classe Liga que aramazena o estado do programa
     **/
    private static void menuGestao(Liga l){
        String[] s = {"*** MENU GESTAO ***", "Adicionar Jogador", "Adicionar Equipa", "Mudar Jogador de Equipa"};
        Menu gestao = new Menu(s);
        int getOpt = -1;
        String equipa = "";
        Jogador j;
        String jogadorConsultar = "";
        while(getOpt != 0) {
            gestao.execute();
            getOpt = gestao.getOption();
            switch (getOpt) {
                case 1:
                    String jogador;
                    String[] sjogador;
                    try{
                        Map.Entry<String, Jogador> entry = FMView.getPlayer();
                        j = entry.getValue();
                        equipa = entry.getKey();
                    }
                    catch(CamposInvalidos e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    try{
                        l.addJogador(j, equipa);
                        FMView.displayHabilidadeJogador(l.getJogadorLiga(equipa, j));
                    }
                    catch(EquipaNaoExisteException | JogadorNaoExiste | NumeroNaoDisponivel e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    equipa = FMView.getEquipa();
                    l.criaEquipa(equipa);
                    break;
                case 3:
                    Map.Entry<String, Map.Entry<String, String>> transf = FMView.getTransferencia();
                    Equipa origem = null;
                    Equipa destino = null;
                    try{
                        origem = l.getEquipa(transf.getValue().getKey());
                        destino = l.getEquipa(transf.getValue().getValue());
                        l.transferencia(transf);
                        FMView.displayEquipa(origem);
                        FMView.displayEquipa(destino);
                    }
                    catch(NumeroNaoDisponivel | EquipaNaoExisteException | JogadorNaoExiste e){
                        System.out.println(e.getMessage());
                    }

                    break;

            }
        }

    }

    /**
     *  Método responsável por contruir e executar o Menu de Consulta
     * @param l Classe liga que armazena o estado do programa
     **/
    private static void menuConsulta(Liga l){
        String[] s = {"**** Menu de Consulta ****", "Consultar Equipa", "Consultar Jogador", "Calcular Habilidade Jogador", "Calcular Habilidade Equipa"};
        Menu consulta = new Menu(s);
        int getOpt = -1;
        String equipa = "";
        String jogadorConsultar = "";
        while(getOpt != 0){
            consulta.execute();
            getOpt = consulta.getOption();
            switch (getOpt){
                case 1:
                    equipa = FMView.consultarEquipa();
                    try{
                        FMView.displayEquipa(l.getEquipa(equipa));
                    }
                    catch(EquipaNaoExisteException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    jogadorConsultar = FMView.consultarJogador();
                    try{
                        FMView.displayHabilidadeJogador(l.consultaJogador(jogadorConsultar));
                    }
                    catch(JogadorNaoExiste e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    String jogadorHabilidade = FMView.getPlayerForHability();
                    try{
                        FMView.mostraHabilidade(l.consultaJogador(jogadorHabilidade).getHabilidade());
                    }
                    catch(JogadorNaoExiste e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    equipa = FMView.calculaHabilidade();
                    try{
                        Equipa procura = l.getEquipa(equipa);
                        int habilidade = procura.calculaHabilidadeEquipa();
                        String tatica = "";
                        if (habilidade == 0){
                            tatica = FMView.pedeAlinhamento();
                            String[] posicoes = tatica.split("-");
                            procura.criaInicial(Integer.parseInt(posicoes[0]), Integer.parseInt(posicoes[1]), Integer.parseInt(posicoes[2]), Integer.parseInt(posicoes[3]));
                            procura.calculaHabilidadeEquipa();
                            l.adicionaEquipa(procura);
                        }
                        FMView.displayHabilidadeEquipa(procura.getHabilidade());
                    }
                    catch (EquipaNaoExisteException | InsufficientPlayers e){
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }

    /**
     *  Metodo responsável por construir e executar o Menu De Jogo
     * @param l Classe Liga que contém o estado atual do programa
     **/

    private static void menuJogo(Liga l) {
        String[] s = {"**** Menu Jogo ****\n", "Escolher Equipas", "Escolher Tática Casa" , "Escolher Tática Fora", "Jogar", "Desforra", "Novo Jogo"};
        Menu jogo = new Menu(s);
        Jogo j = new Jogo();
        Equipa casa = null;
        Equipa fora = null;
        int getOpt = -1;
        while(getOpt != 0) {
            jogo.execute();
            getOpt = jogo.getOption();
            switch (getOpt) {
                case 1:
                    Map.Entry<String, String> equipas = FMView.escolherEquipas();
                    try{
                        casa = l.getEquipa(equipas.getKey());
                        fora = l.getEquipa(equipas.getValue());
                        j.setEquipaCasa(casa);
                        j.setEquipaFora(fora);
                        j.setNomeEquipaCasa(casa.getNome());
                        j.setNomeEquipaFora(casa.getNome());
                    }
                    catch(EquipaNaoExisteException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    if (casa != null){
                        try {
                            String tatica = FMView.pedeAlinhamento();
                            String[] posicoes = tatica.split("-");
                            casa.criaInicial(Integer.parseInt(posicoes[0]), Integer.parseInt(posicoes[1]), Integer.parseInt(posicoes[2]), Integer.parseInt(posicoes[3]));
                            casa.calculaHabilidadeEquipa();
                            j.setJogadoresCasa(casa.getTitularesList().stream().map(Jogador::getNumeroJogador).collect(Collectors.toList()));
                            l.adicionaEquipa(casa);
                            FMView.displayEquipa(casa);
                        }
                        catch (InsufficientPlayers e){
                            System.out.println(e.getMessage());
                        }
                    }
                    else FMView.erros(1);
                    break;
                case 3:
                    if (fora != null){
                        try {
                            String tatica = FMView.pedeAlinhamento();
                            String[] posicoes = tatica.split("-");
                            fora.criaInicial(Integer.parseInt(posicoes[0]), Integer.parseInt(posicoes[1]), Integer.parseInt(posicoes[2]), Integer.parseInt(posicoes[3]));
                            fora.calculaHabilidadeEquipa();
                            j.setJogadoresFora(fora.getTitularesList().stream().map(Jogador::getNumeroJogador).collect(Collectors.toList()));
                            l.adicionaEquipa(fora);
                            FMView.displayEquipa(fora);
                        }
                        catch (InsufficientPlayers e){
                            System.out.println(e.getMessage());
                        }

                    }
                    else FMView.erros(2);
                    break;
                case 4:
                    try{
                        FMApp.correJogo(casa, fora, j, l);
                    }
                    catch (InterruptedException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try{
                        j.setGolosCasa(0);
                        j.setGolosFora(0);
                        FMApp.correJogo(casa, fora, j, l);
                    }
                    catch (InterruptedException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    l.adicionaJogo(j);
                    j = new Jogo();
                    break;
            }
        }


    }

    /**
     * Método responsável por executar a simulação de um jogo
     * @param casa Objeto da Classe Equipa que armazena o estado da equioa da casa
     * @param fora Objeto da Classe Equipa que armazena o estado da equioa de fora
     * @param j Objeto da Classe Jogo que armazena o estado do jogo a executar
     * @param l Objeto da Classe Liga que armazena o estado do programa
     * @throws InterruptedException que vem do método sleep
     **/
    public static void correJogo(Equipa casa, Equipa fora, Jogo j , Liga l) throws InterruptedException{
        int resultJogada = 0;
        String bolaAntes = "";
        String bolaDepois = "";
        Map.Entry<String, String> entry;
        String equipa = null;
        if (casa == null){
            FMView.erros(1);
        }
        else if (fora == null){
            FMView.erros(2);
        }
        else if (j.getJogadoresCasa().size() == 0){
            FMView.erros(3);
        }
        else if (j.getJogadoresFora().size() == 0){
            FMView.erros(4);
        }
        else{
            int periodos = 0;
            int habCasa = casa.calculaHabilidadeEquipa();
            int habFora = fora.calculaHabilidadeEquipa();
            int difHab = habCasa - habFora;
            FMView.infoInicioJogo(casa.getNome(),fora.getNome(),habCasa,habFora);
            if (difHab > 0){
                if (difHab > 5) habCasa = 5;
                else habCasa = difHab;
                habFora = 0;
            }
            else{
                if (difHab < -5) habFora = 5;
                else habFora = -(difHab);
                habCasa = 0;
            }
            Equipa eq = null;
            boolean subValida = false;
            int subsCasa = 0, subsFora = 0;
            while (periodos < 18){
                if (periodos % 2 == 0){
                    try{
                        equipa = FMView.pedeSubstituicao();
                        if (!equipa.equals("")){
                            if (equipa.equals("casa")) eq = casa;
                            else eq = fora;
                            if (equipa.equals("casa") && subsCasa > 2){
                                periodos--;
                                throw new SubstituicaoInvalida("Já gastou o numero de substituições!");
                            }
                            if (equipa.equals("fora") && subsFora > 2) {
                                periodos--;
                                throw new SubstituicaoInvalida("Já gastou o numero de substituições!");
                            }
                            entry = FMView.pedeJogadoresSub(eq.listaSemTitulares(), eq.getTitularesList());
                            String in = entry.getKey();
                            String out = entry.getValue();
                            subValida = false;
                            subValida = j.substituicao(eq, in, out);
                        }
                    }
                    catch (JogadorNaoExiste | SubstituicaoInvalida e){
                        System.out.println(e.getMessage());
                    }
                }
                if (subValida){
                    if (equipa.equals("casa")){
                        subsCasa++;
                    } else if (equipa.equals("fora")){
                        subsFora++;
                    }
                    l.adicionaEquipa(eq);
                    habCasa = casa.calculaHabilidadeEquipa();
                    habFora = fora.calculaHabilidadeEquipa();
                    bolaAntes = j.getBola();
                    resultJogada = j.calculaJogo(habCasa, habFora);
                    bolaDepois = j.getBola();
                    FMView.displayJogadas(bolaAntes,bolaDepois,resultJogada,casa.getNome(),fora.getNome());

                }
                else if (equipa.equals("")){
                    bolaAntes = j.getBola();
                    resultJogada = j.calculaJogo(habCasa,habFora);
                    bolaDepois = j.getBola();
                    FMView.displayJogadas(bolaAntes,bolaDepois,resultJogada,casa.getNome(),fora.getNome());
                }
                else periodos--;
                periodos++;
                Thread.sleep(2000);
                equipa = "";
                subValida = false;
            }
            FMView.displayResultado(casa.getNome(),fora.getNome(),j.getGolosCasa(),j.getGolosFora());
        }
    }
}
