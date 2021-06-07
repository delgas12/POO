package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Wagner Athletic
// Sporting Club Schubert

//Avançado,Jucca,13,100,100,100,100,100,100,100,100,100,
//Defesa,Toldy,13,100,100,100,100,100,100,100,100,100
//Lateral,Castiçu,13,100,100,100,100,100,100,100,100,100

// David Alexandre Ferreira Duarte
// Tiago Lucas Alves

public class FMApp {
    public static void main(String[] args){

        Liga l = new Liga();

        String[] s = {"**** FM_APP ****\n", "Gerir a Liga", "Menu de Consulta","Jogo","Carregar Estado De Um Ficheiro"};
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
                    //chamar o metodo do jogo
                    menuJogo(l);
                    break;
                case 4:
                    Parser p;
                    try {
                        System.out.println("correu");
                        p = new Parser();
                        p.parse();
                        //System.out.println(p.getEquipas().toString());
                        l.setEquipas(p.getEquipas());
                        l.setJogos(p.getJogos());
                        //System.out.println(l.toString());
                        //System.out.println(l.getEquipa("Mahler Athletic"));
                        //System.out.println(l.getJogos());
                    }
                    catch (LinhaIncorretaException | InsufficientPlayers e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    break;
            }
        }
    }

    private static void menuGestao(Liga l){
        String[] s = {"Adicionar Jogador", "Adicionar Equipa", "Mudar Jogador de Equipa"};
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
                        System.out.println(l.getJogadorLiga(equipa, j));
                    }
                    catch(EquipaNaoExisteException | JogadorNaoExiste | NumeroNaoDisponivel e){
                        System.out.println(e.getMessage());
                    }
                    //System.out.println(l.getEquipa(equipa));
                    break;
                case 2:
                    equipa = FMView.getEquipa();
                    l.criaEquipa(equipa);
                    //System.out.println(l.getEquipa(equipa));
                    break;
                case 3:
                    Map.Entry<String, Map.Entry<String, String>> transf = FMView.getTransferencia();
                    Equipa origem = null;
                    Equipa destino = null;
                    try{
                        origem = l.getEquipa(transf.getValue().getKey());
                        destino = l.getEquipa(transf.getValue().getValue());
                        //System.out.println(origem.toString());
                        //System.out.println(destino.toString());
                        l.transferencia(transf);
                    }
                    catch(NumeroNaoDisponivel | EquipaNaoExisteException | JogadorNaoExiste e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println(origem.toString());
                    System.out.println(destino.toString());
                    break;

            }
        }

    }

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
                        FMView.displayJogador(l.consultaJogador(jogadorConsultar));
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

    private static void menuJogo(Liga l) {
        String[] s = {"**** Menu Jogo ****\n", "Escolher Equipas", "Escolher Titulares Casa" , "Escolher Titulares Fora", "Jogar", "Desforra", "Novo Jogo"};
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
                            j.setJogadoresCasa(casa.getTitularesList().stream().map(jog -> jog.getNumeroJogador()).collect(Collectors.toList()));
                            //j.setJogadoresCasa(FMView.mostraJogadores(j.getEquipaCasa()));
                            //j.adicionaTitularesCasaJogo();
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
                            j.setJogadoresFora(fora.getTitularesList().stream().map(jog -> jog.getNumeroJogador()).collect(Collectors.toList()));
                            //j.setJogadoresFora(FMView.mostraJogadores(j.getEquipaFora()));
                            //j.adicionaTitularesForaJogo();
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

    public static void correJogo(Equipa casa, Equipa fora, Jogo j , Liga l) throws InterruptedException{
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
        else{// 68 65
            int periodos = 0;
            int habCasa = casa.calculaHabilidadeEquipa();
            int habFora = fora.calculaHabilidadeEquipa();
            int difHab = habCasa - habFora;
            System.out.println("A Habilidade de " + casa.getNome() + " é: " + habCasa);
            System.out.println("A Habilidade de " + fora.getNome() + " é: " + habFora);
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
            Equipa eq;
            int subsCasa = 0, subsFora = 0;
            while (periodos < 18){
                System.out.println(j.getBola());
                if (periodos % 2 == 0){
                    try{
                        equipa = FMView.pedeSubstituicao();
                        if (!equipa.equals("")){
                            if (equipa.equals("casa")){
                                eq = casa;
                                subsCasa++;
                            }
                            else{
                                eq = fora;
                                subsFora++;
                            }
                            if (subsCasa > 3) throw new SubstituicaoInvalida("Já gastou o numero de substituições!");
                            if (subsFora > 3) throw new SubstituicaoInvalida("Já gastou o numero de substituições!");
                            entry = FMView.pedeJogadoresSub(eq.listaSemTitulares(), eq.getTitularesList());
                            String in = entry.getKey();
                            String out = entry.getValue();
                            j.substituicao(eq, in, out);
                            l.adicionaEquipa(eq);
                        }
                    }
                    catch (JogadorNaoExiste | SubstituicaoInvalida e){
                        System.out.println(e.getMessage());
                    }
                }
                habCasa = casa.calculaHabilidadeEquipa();
                habFora = fora.calculaHabilidadeEquipa();
                Thread.sleep(2000);
                j.calculaJogo(habCasa, habFora);
                periodos++;
            }
            System.out.println(casa.getNome() + " " + j.getGolosCasa() + " - "  + j.getGolosFora() +  " " + fora.getNome() );
        }
    }
}
