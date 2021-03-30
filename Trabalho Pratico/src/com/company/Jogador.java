package com.company;

import java.util.ArrayList;

public class Jogador {
    private String nome;
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


    public int calculateHability(){
        int result = 0;
        switch(getPosicao()){
            case "Defesa" :
                  result = (int)((double) this.getVelocidade() * 0.10 +
                                 (double) this.getResistencia() * 0.10 +
                                 (double) this.getDestreza() * 0.10 +
                                 (double) this.getImpulsao() * 0.20 +
                                 (double) this.getJogoDeCabeca() * 0.3 +
                                 (double) this.getRemate() * 0.05 +
                                 (double) this.getPasse() * 0.15);
                break;
            case "Médio" :
                result = (int)((double) this.getVelocidade() * 0.10 +
                        (double) this.getResistencia() * 0.15 +
                        (double) this.getDestreza() * 0.20 +
                        (double) this.getImpulsao() * 0.05 +
                        (double) this.getJogoDeCabeca() * 0.05 +
                        (double) this.getRemate() * 0.15 +
                        (double) this.getPasse() * 0.20);
                break;
            case "Avançado" :
                result = (int)((double) getVelocidade() * 0.15 +
                        (double) this.getResistencia() * 0.15 +
                        (double) this.getDestreza() * 0.10 +
                        (double) this.getImpulsao() * 0.15 +
                        (double) this.getJogoDeCabeca() * 0.15 +
                        (double) this.getRemate() * 0.20 +
                        (double) this.getPasse() * 0.10);
                break;

        }
        return result;
    }

    //Construtores

    public Jogador() {
        this.nome = "";
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

    public Jogador(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, String posicao, ArrayList<String> historial) {
        this.nome = nome;
        this.velocidade = velocidade;
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

    //Getters

    public String getNome() {
        return this.nome;
    }

    public int getVelocidade() {
        return this.velocidade;
    }

    public int getResistencia() {
        return this.resistencia;
    }

    public int getDestreza() {
        return this.destreza;
    }

    public int getImpulsao() {
        return this.impulsao;
    }

    public int getJogoDeCabeca() {
        return this.jogoDeCabeca;
    }

    public int getRemate() {
        return this.remate;
    }

    public int getPasse() {
        return this.passe;
    }

    public String getPosicao() {
        return this.posicao;
    }

    public ArrayList<String> getHistorial() {
        return this.historial;
    }

    public int getHabilidade() {
        return this.habilidade;
    }

    //Setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    public void setJogoDeCabeca(int jogoDeCabeca) {
        this.jogoDeCabeca = jogoDeCabeca;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }

    public void setPasse(int passe) {
        this.passe = passe;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public void setHistorial(ArrayList<String> historial) {
        this.historial = historial;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }


    public String toString(){
        return "\nEstatísticas do Jogador: \nNome: " + this.nome /*+
                "\nVelocidade: " + this.velocidade + "\nResistencia: " + this.resistencia +
                "\nDestreza: " + this.destreza + "\nImpulsão: " + this.impulsao + "\nJogo De Cabeça: " + this.jogoDeCabeca +
                "\nRemate: " + this.remate + "\nPasse: " + this.passe + "\nPosição: " + this.posicao + "\nHabilidade: " + this.habilidade +
                "\nHistorial: " + this.historial.toString()
                ;
                 */
                ;
    }

    //clone

    public Jogador clone(){
        return new Jogador(this);
    }

    //equals

    public boolean equals(Object o){
        if (o == this) return true;
        if ((o == null) || (o.getClass() != this.getClass())) return false;

        Jogador j = (Jogador) o;

        return (this.nome.equals(j.getNome()) && this.velocidade == j.getVelocidade() && this.resistencia == j.getResistencia() && this.destreza == j.getDestreza() &&
                this.impulsao == j.getImpulsao() && this.jogoDeCabeca == j.getJogoDeCabeca() && this.remate == j.getRemate() && this.passe == j.getPasse() &&
                this.habilidade == j.getHabilidade() && this.posicao.equals(j.getPosicao()) && this.historial.equals(j.getHistorial()));
    }

}
