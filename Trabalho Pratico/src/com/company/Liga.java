package com.company;
import java.util.*;
import java.util.stream.Collectors;

public class Liga {
    private Map<String,Equipa> equipas;
    private int n_equipas;


    public Liga(){
        ArrayList<Equipa> equipa = new ArrayList<>();
        this.n_equipas = 0;
    }
    public Liga(Map<String,Equipa> equipas, int n_equipas){
        this.equipas = new TreeMap<>();
        Iterator<Map.Entry<String,Equipa>> it = equipas.entrySet().iterator();

        while(it.hasNext()){
            Equipa e = it.next().getValue();
            this.equipas.put(e.getName(),e.clone());
        }
        this.n_equipas = n_equipas;
    }
    public Liga(Liga l){
        this.equipas = new TreeMap<>();
        for (Equipa e : l.getEquipasList()){
            this.equipas.put(e.getName(),e.clone());
        }
        this.n_equipas = l.getN_equipas();
    }

    //Getters
    public Map<String,Equipa> getEquipas() {
        return this.equipas.values().stream().map(e -> e.clone()).collect(Collectors.toMap(e -> e.getName(), e -> e.clone()));
    }

    public List<Equipa> getEquipasList(){
        return this.equipas.values().stream().map(e -> e.clone()).collect(Collectors.toList());
    }

    public int getN_equipas() {
        return this.n_equipas;
    }
    
    //Setters
    
    public void setEquipas(Map<String,Equipa> equipas) {
        TreeMap<String,Equipa> novo = new TreeMap<>();
        Iterator<Map.Entry<String,Equipa>> it = equipas.entrySet().iterator();
        while(it.hasNext()){
            Equipa e = it.next().getValue();
            novo.put(e.getName(),e.clone());
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
        Jogador transferido = null;
        Iterator<Map.Entry<String,Equipa>> it = equipas.entrySet().iterator();             //o iterador percorre a liga, equipa a equipa
        List<Jogador> novo = new ArrayList<>();
        Equipa a;
        boolean removeu = false;
        while(it.hasNext() && !removeu){                            //enquanto o iterador nao tiver percorrido a liga até ao fim ou ainda nao tiver sido removido,
            a = it.next().getValue();
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

    public String equipasToString(){
        Iterator<Map.Entry<String,Equipa>> it = equipas.entrySet().iterator();
        StringBuilder equipas = new StringBuilder("Equipas");
        while(it.hasNext()){
            Equipa e = it.next().getValue();
            equipas.append('a');
        }
        return equipas.toString();
    }

    public String toString(){
        return "Liga: { " +
                "Equipas: " + this.equipas.toString() +
                "Número de equipas: " + this.n_equipas +
                " }";
    }

}