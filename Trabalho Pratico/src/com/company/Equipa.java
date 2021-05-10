package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class Equipa implements Comparable<Equipa>{
    private String nome;
    private List<Jogador> jogadores;


    public Equipa(){
        String name = "";
        this.jogadores = new ArrayList<>();
    }

    public Equipa(String name){
        this.nome = name;
        this.jogadores = new ArrayList<>();
    }

    public Equipa(Equipa e){
        this.nome = e.getNome();
        this.jogadores = new ArrayList<>();
        this.jogadores = e.getJogadores();


    }

    //Getters
    public String getNome(){
        return this.nome;
    }

    public List<Jogador> getJogadores() {
        return this.jogadores.stream().map(Jogador::clone).collect(Collectors.toList());
    }

    //Setters


    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores.stream().map(Jogador::clone).collect(Collectors.toList());
    }

    public String toString() {
        return "Equipa{" +
                "Nome: " + this.nome +
                "titulares=" + this.jogadores.toString() + "}";
    }

    //funçao que calcula a habilidade da equipa recorrendo ao calculo de uma media simples
    public int habilidadeEquipa(){
        int resultado = 0;
        for(Jogador j : this.jogadores){
            resultado += j.getHabilidade();
        }
        return resultado/this.jogadores.size();
    }
    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }

    public void insereJogador(Jogador j) {
        jogadores.add(j.clone());
    }
    /**
     *
     * @param nome  Nome do jogador a procurar
     * @return true caso o jogador tenha sido encontrado na equipa em questao, ou nos titulares ou nos suplentes
     */
    /*
    public boolean encontraJogador(int numero) {



        return result1;
    }
     */

/*
    /**
     *
     * @param name  nome do jogador que se pretende remover
     * @return      a funçao devolve o jogador que foi removido (para depois ser introduzido na equipa
     */

    //adicionar exceção caso o jogador nao exista
    /*
    public Jogador removeJogador(String nome){
        Jogador resultado = null;
        Iterator<Jogador> itTitulares = titulares.iterator();
        Iterator<Jogador> itSuplentes = suplentes.iterator();
        Jogador j = itTitulares.next();
        while(itTitulares.hasNext() && !(j.getNome().equals(nome))) j = itTitulares.next(); //o ciclo percorre o arraylist recorrendo ao iterador itTitulares
        if(j.getNome().equals(nome)){                                                       //no fim, caso o jogador esteja no arraylist titulares, clona o jogador em questao para o jogador resultado
            resultado = j.clone();                                                          //remove o jogador em questao
            itTitulares.remove();
        }
        if(itSuplentes.hasNext()){                                                          //caso exista suplentes, a funçao realiza um processo analogo ao descrtio acima, mas para o arrayList suplentes
            j = itSuplentes.next();
            while(itSuplentes.hasNext() && !(j.getNome().equals(nome))) j = itSuplentes.next();
            if(j.getNome().equals(nome)){
                resultado = j.clone();
                itSuplentes.remove();
            }
        }
        return resultado;
    }
     */

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
