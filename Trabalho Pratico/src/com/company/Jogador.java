package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Jogador implements Comparable<Jogador>, Serializable {
    private String nome;
    private int numero;
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogoDeCabeca;
    private int remate;
    private int passe;
    private int habilidade;
    private String posicao; // (GR, Defesa, Medios, Avançados)
    private ArrayList<String> historial;


    public abstract  int calculateHability();

    public Jogador() {
        this.nome = "";
        this.numero = 0;
        this.velocidade = 0;
        this.resistencia = 0;
        this.destreza = 0;
        this.impulsao = 0;
        this.jogoDeCabeca = 0;
        this.remate = 0;
        this.passe = 0;
        this.posicao = "";
        this.habilidade = 0;
        this.historial = new ArrayList<>();
    }

    public Jogador(String nome, int numero, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, String posicao, ArrayList<String> historial) {
        this.nome = nome;
        this.velocidade = velocidade;
        this.numero = numero;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogoDeCabeca = jogoDeCabeca;
        this.remate = remate;
        this.passe = passe;
        this.posicao = posicao;
        this.historial = new ArrayList<>();
        this.historial.addAll(historial);
        this.habilidade = this.calculateHability();
    }

    public Jogador(Jogador j){
        this.nome = j.getNome();
        this.numero = j.getNumeroJogador();
        this.velocidade = j.getVelocidade();
        this.resistencia = j.getResistencia();
        this.destreza = j.getDestreza();
        this.impulsao = j.getImpulsao();
        this.jogoDeCabeca = j.getJogoDeCabeca();
        this.remate = j.getRemate();
        this.passe = j.getPasse();
        this.posicao = j.getPosicao();
        this.habilidade = j.getHabilidade();
        this.historial = new ArrayList<>();
        this.historial.addAll(j.getHistorial());
    }


    /**
     *  Método de consulta do valor da variável de instância Nome
     * @return valor da variável de instância Nome do jogador
     **/

    public String getNome() {
        return this.nome;
    }

    /**
     *  Método de consulta do valor da variável de instância NumeroJogador
     * @return valor da variável de instância NumeroJogador
     **/
    public int getNumeroJogador() {
        return this.numero;
    }


    /**
     *  Método de consulta do valor da variável de instância Velocidade
     * @return valor da variável de instância Velocidade do jogador
     **/
    public int getVelocidade() {
        return this.velocidade;
    }

    /**
     *  Método de consulta do valor da variável de instância Resistência
     * @return valor da variável de Resistência do jogador
     **/
    public int getResistencia() {
        return this.resistencia;
    }

    /**
     *  Método de consulta do valor da variável de instância Destreza
     * @return valor da variável de instância Destre do jogador
     **/
    public int getDestreza() {
        return this.destreza;
    }

    /**
     *  Método de consulta do valor da variável de instância Impulsão
     * @return valor da variável de instância Impulsão do jogador
     **/
    public int getImpulsao() {
        return this.impulsao;
    }

    /**
     *  Método de consulta do valor da variável de instância JogoDeCabeca
     * @return valor da variável de instância JogoDeCabeca do jogador
     **/
    public int getJogoDeCabeca() {
        return this.jogoDeCabeca;
    }

    /**
     *  Método de consulta do valor da variável de instância Remate
     * @return valor da variável de instância Remate do jogador
     **/
    public int getRemate() {
        return this.remate;
    }

    /**
     *  Método de consulta do valor da variável de instância Passe
     * @return valor da variável de instância Passe do jogador
     **/
    public int getPasse() {
        return this.passe;
    }

    /**
     *  Método de consulta do valor da variável de instância Posicao
     * @return valor da variável de instância Posicao do jogador
     **/
    public String getPosicao() {
        return this.posicao;
    }

    /**
     *  Método de consulta do valor da variável de instância Historial
     * @return valor da variável de instância Historial do jogador
     **/
    public ArrayList<String> getHistorial() {
        return this.historial;
    }

    /**
     *  Método de consulta do valor da variável de instância Habilidade
     * @return valor da variável de Habilidade Remate do jogador
     **/
    public int getHabilidade() {
        return this.habilidade;
    }

    /**
     *  Método de alteração da variável de instância Nome
     * @param nome novo valor da variável de instância Nome
     **/

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *  Método de alteração da variável de instância NumeroJogador
     * @param numero novo valor da variável de instância NumeroJogador
     **/

    public void setNumeroJogador(int numero){
        this.numero = numero;
    }

    /**
     *  Método de alteração da variável de instância velocidade
     * @param velocidade novo valor da variável de instância velocidade
     **/

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    /**
     *  Método de alteração da variável de instância resistencia
     * @param resistencia novo valor da variável de instância resistencia
     **/

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    /**
     *  Método de alteração da variável de instância destreza
     * @param destreza novo valor da variável de instância destreza
     **/

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    /**
     *  Método de alteração da variável de instância impulsao
     * @param impulsao novo valor da variável de instância impulsao
     **/

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    /**
     *  Método de alteração da variável de instância jogoDeCabeca
     * @param jogoDeCabeca novo valor da variável de instância jogoDeCabeca
     **/

    public void setJogoDeCabeca(int jogoDeCabeca) {
        this.jogoDeCabeca = jogoDeCabeca;
    }

    /**
     *  Método de alteração da variável de instância remate
     * @param remate novo valor da variável de instância remate
     **/

    public void setRemate(int remate) {
        this.remate = remate;
    }

    /**
     *  Método de alteração da variável de instância passe
     * @param passe novo valor da variável de instância passe
     **/

    public void setPasse(int passe) {
        this.passe = passe;
    }

    /**
     *  Método de alteração da variável de instância posicao
     * @param posicao novo valor da variável de instância posicao
     **/

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    /**
     *  Método de alteração da variável de instância historial
     * @param historial novo valor da variável de instância historial
     **/

    public void setHistorial(ArrayList<String> historial) {
        this.historial = historial;
    }

    /**
     *  Método de alteração da variável de instância habilidade
     * @param habilidade novo valor da variável de instância habilidade
     **/

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }

    /**
     * Método de comparação de 2 jogadores para ordenação que compara os valores de Habilidade
     * @return subtração do valor de habilidade de um jogador A e um jogador B
     **/
    public int compareTo(Jogador j){
        return (j.getHabilidade() - this.getHabilidade());
    }



    /**
     * Método de representação da classe Jogador sob a forma de uma String
     * @return String com a representação da classe Jogador
     **/

    public String toString(){
        return /*"\nEstatísticas do Jogador: Numero: "+ */"\nPosição: " + this.posicao + ", Numero: " + this.numero + ", Nome: " + this.nome + ", Habilidade: " + this.habilidade + "\nHistorial: " + this.historial.toString();/* +
                "\nVelocidade: " + this.velocidade + "\nResistencia: " + this.resistencia +
                "\nDestreza: " + this.destreza + "\nImpulsão: " + this.impulsao + "\nJogo De Cabeça: " + this.jogoDeCabeca +
                "\nRemate: " + this.remate + "\nPasse: " + this.passe + "\nPosição: " + this.posicao; + "\nHabilidade: " + this.habilidade +
                "\nHistorial: " + this.historial.toString()*/
    }

    /**
     * Método de representação das habilidades do Jogador sob a forma de uma String
     * @return String com a representação das habilidades do Jogador
     **/

    public String toStringHabilidades(){
        return "Estatísticas do Jogador: " +
                "\nNumero: "         + this.numero               +
                "\nVelocidade: "     + this.velocidade           +
                "\nResistencia: "    + this.resistencia          +
                "\nDestreza: "       + this.destreza             +
                "\nImpulsão: "       + this.impulsao             +
                "\nJogo De Cabeça: " + this.jogoDeCabeca         +
                "\nRemate: "         + this.remate               +
                "\nPasse: "          + this.passe                +
                "\nPosição: "        + this.posicao              +
                "\nHistorial: "      + this.historial.toString() ;
    }

    /**
     *  Método cópia de um Jogador
     * @return Cópia do Jogador
     * **/
    public abstract Jogador clone();


    /**
     * Método de comparação de um objeto com um Jogador
     * @param o objeto a comparar
     * @return valor da igualdade
     **/

    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass() != this.getClass())) return false;

        Jogador j = (Jogador) o;

        return (this.nome.equals(j.getNome()) && this.numero == j.getNumeroJogador() && this.velocidade == j.getVelocidade() && this.resistencia == j.getResistencia() && this.destreza == j.getDestreza() &&
                this.impulsao == j.getImpulsao() && this.jogoDeCabeca == j.getJogoDeCabeca() && this.remate == j.getRemate() && this.passe == j.getPasse() &&
                this.habilidade == j.getHabilidade() && this.posicao.equals(j.getPosicao()) && this.historial.equals(j.getHistorial()));
    }


    /**
     *  Método de inserção de uma equipa ao historial de equipas de um jogador
     * @param nome nome da equipa a adicionar
     **/

    public void addEquipaToHistorial(String nome){
        this.historial.add(nome);
    }

}
