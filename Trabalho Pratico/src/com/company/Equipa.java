package com.company;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class Equipa implements Comparable<Equipa>{
    private String nome;
    private Map<Integer, Jogador> jogadores;



    public Equipa(){
        String name = "";
        this.jogadores = new HashMap<>();
    }

    public Equipa(String name){
        this.nome = name;
        this.jogadores = new HashMap<>();
    }

    public Equipa(Equipa e){
        this.nome = e.getNome();
        this.jogadores = new HashMap<>();
        this.jogadores = e.getJogadores();


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
                "titulares=" + this.jogadores.toString() + "}";
    }

    public int compareTo(Equipa b){
        //iguais -> 0
        //primeiro maior que o segundo -> > 0
        //segundo maior que o primeiro -> < 0
        return this.getNome().compareTo(b.getNome());
    }


    public Equipa clone(){
        return new Equipa(this);
    }
}
