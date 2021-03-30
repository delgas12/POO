package com.company;
import java.util.ArrayList;
import java.util.Iterator;

public class Liga {
    private ArrayList<Equipa> equipas;
    private int n_equipas;


    public Liga(){
        ArrayList<Equipa> equipa = new ArrayList<>();
        this.n_equipas = 0;
    }
    public Liga(ArrayList<Equipa> equipas, int n_equipas){
        this.equipas = new ArrayList<>();
        this.equipas.addAll(equipas);
        this.n_equipas = n_equipas;
    }
    public Liga(Liga l){
        this.equipas = new ArrayList<>();
        for (Equipa e : l.equipas){
            this.equipas.add(e.clone());
        }
        this.n_equipas = l.n_equipas;
    }

    //Getters
    public ArrayList<Equipa> getEquipas() {
        ArrayList<Equipa> novo = new ArrayList<>();
        for (Equipa e : this.equipas){
            novo.add(e.clone());
        }
        return novo;
    }

    public int getN_equipas() {
        return this.n_equipas;
    }
    
    //Setters
    
    public void setEquipas(ArrayList<Equipa> equipas) {
        ArrayList<Equipa> novo = new ArrayList<>();
        for (Equipa e : equipas){
            novo.add(e.clone());
        }
        this.equipas = novo;
    }

    public void setN_equipas(int n_equipas) {
        this.n_equipas = n_equipas;
    }

    //clone
    
    public Liga clone(){
        return new Liga(this);
    }


    /**
     *
     * @param destino equipa para o qual o jogador selecionado será transferido
     * @param nome  Nome do jogador a transferir no formato String
     * @return
     */
    public boolean transferencia (Equipa destino, String nome){
        Jogador transferido = new Jogador();
        Iterator<Equipa> it = this.equipas.iterator();              //o iterador percorre a liga, equipa a equipa
        ArrayList<Jogador> novo = new ArrayList<>();
        Equipa a;
        boolean removeu = false;
        while(it.hasNext() && !removeu){                            //enquanto o iterador nao tiver percorrido a liga até ao fim ou ainda nao tiver sido removido,
            a = it.next();
            if (a.encontraJogador(nome)){
                transferido = a.removeJogador(nome);
                removeu = true;
            }
        }
        if (removeu){
            if (destino.getTitulares().size() == 11) {
                novo = destino.getSuplentes();
                novo.add(transferido);
                destino.setSuplentes(novo);
            }
            else {
                novo = destino.getTitulares();
                novo.add(transferido);
                destino.setTitulares(novo);
            }
        }
        return removeu;
    }

    public String toString(){
        return "Liga: { " +
                "Equipas: " + this.equipas.toString() +
                "Número de equipas: " + this.n_equipas +
                " }";
    }

}