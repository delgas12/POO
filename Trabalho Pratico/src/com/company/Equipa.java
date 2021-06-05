package com.company;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class Equipa implements Comparable<Equipa>{
    private String nome;
    private Map<Integer, Jogador> jogadores;
    private List<Jogador> titulares;
    private int habilidade;


    public Equipa(){
        this.nome = "";
        this.jogadores = new HashMap<>();
        this.titulares = new ArrayList<>();
        this.habilidade = 0;
    }

    public Equipa(String name){
        this.nome = name;
        this.jogadores = new HashMap<>();
        this.titulares = new ArrayList<>();
        this.habilidade = 0;
    }

    public Equipa(Equipa e){
        this.nome = e.getNome();
        this.jogadores = new HashMap<>();
        this.jogadores = e.getJogadores();
        this.titulares = e.getTitulares();
        this.habilidade = e.getHabilidade();
    }

    //Getters
    public String getNome(){
        return this.nome;
    }

    public Map<Integer,Jogador> getJogadores() {
        return this.jogadores.values().stream().collect(Collectors.toMap(Jogador::getNumeroJogador, Jogador::clone));
    }

    public Jogador getJogadorEquipa(Jogador procura) throws JogadorNaoExiste {
        if (!this.jogadores.containsKey(procura.getNumeroJogador())) throw new JogadorNaoExiste("Jogador não existe nesta equipa");
        return this.jogadores.get(procura.getNumeroJogador());
    }

    public List<Jogador> getTitulares(){
        return this.titulares.stream().map(Jogador::clone).collect(Collectors.toList());
    }

    public int getHabilidade(){
        return this.habilidade;
    }

    //Setters


    public void setJogadores(Map<Integer,Jogador> jogadores) {
        this.jogadores = jogadores.values().stream().collect(Collectors.toMap(Jogador::getNumeroJogador, Jogador::clone));
    }

    //funçao que calcula a habilidade da equipa recorrendo ao calculo de uma media simples
    public int habilidadeEquipa(){
        int resultado = 0;
        for(Jogador j : this.jogadores.values()){
            resultado += j.getHabilidade();
        }
        return resultado/this.jogadores.size();
    }

    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }

    public void insereJogador(Jogador j){
        this.jogadores.put(j.getNumeroJogador(),j.clone());
    }

    public void insereJogadorTransferencia(Jogador j) throws NumeroNaoDisponivel{
        boolean result = true;
        int num = j.getNumeroJogador();;
        while (this.jogadores.containsKey(num)){
            if (num == 99) num = 1;
            else num++;
            result = false;
        }
        if(!result){
            j.setNumeroJogador(num);
            j.addEquipaToHistorial(this.getNome());
            this.jogadores.put(j.getNumeroJogador(),j.clone());
            throw new NumeroNaoDisponivel("Jogador inserido com novo numero : " + num);
        }
        this.jogadores.put(j.getNumeroJogador(),j.clone());
    }

    public void removeJogador(Jogador j) throws JogadorNaoExiste{
        if (this.jogadores.remove(j.getNumeroJogador()) == null) throw new JogadorNaoExiste("Jogador não existe na equipa destino");
    }

    public Jogador procuraJogador(String nome){
        return this.jogadores.values().stream().filter(j -> j.getNome().equals(nome)).findFirst().orElse(null);
    }


    public String toString() {
        return "Equipa{" +
                "Nome: " + this.nome +
                "\n\nJogadores =\n\n" + this.jogadores.toString() +
                "\n\n\n\ntitulares =" + this.titulares.toString() + "}";
    }

    public int compareTo(Equipa b){
        //iguais -> 0
        //primeiro maior que o segundo -> > 0
        //segundo maior que o primeiro -> < 0
        return this.getNome().compareTo(b.getNome());
    }

    public int calculaHabilidadeEquipa (){
        int habilidadeCumulativa = this.titulares.stream().mapToInt(j -> j.getHabilidade()).sum();
        this.habilidade = habilidadeCumulativa/11;
        return (habilidadeCumulativa/11);
    }



    public void criaInicial (int nDefesas , int nLaterais,int nMedios, int nAvancados) throws InsufficientPlayers{
        int total = nDefesas + nLaterais + nMedios + nAvancados;
        if (total != 10) throw new InsufficientPlayers("Número de jogadores diferente de 11");
        List<Jogador> result = new ArrayList<>();
        int counter = 0;
        Jogador grTeam = this.jogadores.values().stream().filter(j -> j instanceof GuardaRedes).findFirst().orElse(null);
        result.add(grTeam.clone());
        List <Jogador> defesas = this.jogadores.values().stream().filter(j -> j instanceof Defesa).map(Jogador::clone).sorted().collect(Collectors.toList());
        List <Jogador> laterais = this.jogadores.values().stream().filter(j -> j instanceof Lateral).map(Jogador::clone).sorted().collect(Collectors.toList());
        List <Jogador> medios = this.jogadores.values().stream().filter(j -> j instanceof Medio).map(Jogador::clone).sorted().collect(Collectors.toList());
        List <Jogador> avancados = this.jogadores.values().stream().filter(j -> j instanceof Avancado).map(Jogador::clone).sorted().collect(Collectors.toList());
        for(counter = 0 ; counter < nDefesas &&  counter < defesas.size(); counter++) {
            result.add(defesas.get(counter));
        }
        if(counter < 2) throw new InsufficientPlayers("Há Defesas em falta");
        counter = 0;
        for (;counter < laterais.size() && counter < nLaterais ; counter++){
            result.add(laterais.get(counter));
        }
        if(counter < 2) throw new InsufficientPlayers("Há Laterais em falta");
        counter = 0;
        for (  ;counter < medios.size() && counter < nMedios; counter++){
            result.add(medios.get(counter));
        }
        if(counter < 2) throw new InsufficientPlayers("Há Medios em falta");
        counter = 0;
        for (; counter < nAvancados && counter < avancados.size(); counter++){
            result.add(avancados.get(counter));
        }
        if(counter < 3) throw new InsufficientPlayers("Há Avançados em falta");
        this.titulares = result;
    }

    public Equipa clone(){
        return new Equipa(this);
    }
}
