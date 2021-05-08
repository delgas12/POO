package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Thread.sleep;

public class Equipa implements Comparable<Equipa>{
    String name;
    private ArrayList<Jogador> titulares;
    private ArrayList<Jogador> suplentes;



    public Equipa(){
        String name = "";
        this.titulares = new ArrayList<>();
        this.suplentes = new ArrayList<>();
    }

    public Equipa(Equipa e){
        this.name = e.getName();
        this.titulares = new ArrayList<>();
        this.titulares.addAll(e.getTitulares());//quebras de encapsulamento
        this.suplentes = new ArrayList<>();
        this.suplentes.addAll(e.getTitulares());//quebras de encapsulamento
    }

    public Equipa(String name, ArrayList<Jogador> titulares, ArrayList<Jogador> suplentes){
        this.name = name;
        this.titulares = copyList(titulares);
        this.suplentes = copyList(suplentes);
    }

    //Getters
    public String getName(){return this.name ; }

    public ArrayList<Jogador> getTitulares() {
        return copyList(this.titulares);
    }

    public ArrayList<Jogador> getSuplentes() {
        return copyList(suplentes);
    }

    //Setters

    public void setSuplentes(ArrayList<Jogador> suplentes) {

        this.suplentes = copyList(suplentes);
    }

    public void setTitulares(ArrayList<Jogador> titulares) {
        this.titulares = copyList(titulares);
    }

    public String toString() {
        return "Equipa{" +
                "Nome: " + this.name +
                "titulares=" + this.titulares.toString() +
                ", suplentes=" + this.suplentes.toString() +
                '}';
    }

    public ArrayList<Jogador> copyList(ArrayList <Jogador> jogadores){
        ArrayList<Jogador> newJogadores = new ArrayList<>(jogadores.size());
        for (Jogador j : jogadores) {
            newJogadores.add(j.clone());
        }
        return newJogadores;
    }

    //funçao que calcula a habilidade da equipa recorrendo ao calculo de uma media simples
    public int habilidadeEquipa(){
        int resultado = 0;
        for(Jogador j : this.titulares){
            resultado += j.getHabilidade();
        }
        return resultado/this.titulares.size();
    }


    /**
     *
     * @param nome  Nome do jogador a procurar
     * @return true caso o jogador tenha sido encontrado na equipa em questao, ou nos titulares ou nos suplentes
     */
    public boolean encontraJogador(String nome){
        boolean result1 = this.titulares.stream().anyMatch(jogador-> {      //recorrendo a uma stream, a funçao verifica a igualdade do nome de cada jogador com o nome fornecido
            return jogador.getNome().equals(nome);
        });
        boolean result2 = this.suplentes.stream().anyMatch(jogador-> {
            return jogador.getNome().equals(nome);
        });
        return result1 || result2;
    }


    /**
     *
     * @param nome  nome do jogador que se pretende remover
     * @return      a funçao devolve o jogador que foi removido (para depois ser introduzido na equipa
     */

    //adicionar exceção caso o jogador nao exista
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

    public int compareTo(Equipa b){
        //iguais -> 0
        //primeiro maior que o segundo -> > 0
        //segundo maior que o primeiro -> < 0
        return this.getName().compareTo(b.getName());
    }


    public Equipa clone(){
        return new Equipa(this);
    }
}
