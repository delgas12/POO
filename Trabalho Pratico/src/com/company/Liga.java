package com.company;
import java.util.*;
import java.util.stream.Collectors;

public class Liga {
    private Map<String,Equipa> equipas;
    private List<Jogo> jogos;
    private int n_equipas;


    public Liga(){
        this.equipas = new TreeMap<>();
        this.n_equipas = 0;
    }

    public Liga(Map<String,Equipa> equipas, int n_equipas){
        this.equipas = new TreeMap<>();
        Iterator<Map.Entry<String,Equipa>> it = equipas.entrySet().iterator();

        while(it.hasNext()){
            Equipa e = it.next().getValue();
            this.equipas.put(e.getNome(),e.clone());
        }
        this.n_equipas = n_equipas;
    }

    public Liga(Liga l){
        this.equipas = new TreeMap<>();
        for (Equipa e : l.getEquipasList()){
            this.equipas.put(e.getNome(),e.clone());
        }
        this.n_equipas = l.getN_equipas();
    }

    public Equipa getEquipa(String nome) throws EquipaNaoExisteException{
        Equipa eq = this.equipas.get(nome);
        if (eq == null) throw new EquipaNaoExisteException("Equipa não existe");
        return eq;
    }


    //Getters
    public Map<String,Equipa> getEquipas() {
        return this.equipas.values().stream().map(e -> e.clone()).collect(Collectors.toMap(e -> e.getNome(), e -> e.clone()));
    }

    public List<Equipa> getEquipasList(){
        return this.equipas.values().stream().map(e -> e.clone()).collect(Collectors.toList());
    }

    public int getN_equipas() {
        return this.n_equipas;
    }

    public List<Jogo> getJogos(){
        return this.jogos.stream().map(Jogo::clone).collect(Collectors.toList());
    }

    public Jogador getJogadorLiga(String equipa, Jogador procura) throws JogadorNaoExiste{
        Jogador result = this.equipas.get(equipa).getJogadorEquipa(procura);
        if ( result == null) throw new JogadorNaoExiste("Jogador não existe");
        else return result;
    }


    //Setters
    public void setJogos(List<Jogo> j){
        this.jogos = j.stream().map(jogo -> jogo.clone()).collect(Collectors.toList());
    }
    public void setEquipas(Map<String,Equipa> equipas) {
        TreeMap<String,Equipa> novo = new TreeMap<>();
        Iterator<Map.Entry<String,Equipa>> it = equipas.entrySet().iterator();
        while(it.hasNext()){
            Equipa e = it.next().getValue();
            novo.put(e.getNome(),e.clone());
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

    public void addJogador(Jogador j, String teamName) throws EquipaNaoExisteException, NumeroNaoDisponivel{
        //qual a posição
        //habilidades
        //nome
        //numero
        if(this.equipas.containsKey(teamName)){
            Equipa equipa = this.equipas.get(teamName);
            j.addEquipaToHistorial(teamName);
            equipa.insereJogador(j);
        }
        else{
            throw new EquipaNaoExisteException("Equipa não existe.");
        }
    }

    public void criaEquipa(String equipa) {
        Equipa nova = new Equipa(equipa);
        this.n_equipas++;
        this.equipas.put(equipa, nova);
    }

    public void adicionaEquipa(Equipa equipa){
        this.equipas.put(equipa.getNome(), equipa.clone());
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
                                    //<Jogador, <equipaOrigem, equipaDestino>>
    public void transferencia(Map.Entry<String,Map.Entry<String, String>> transf) throws EquipaNaoExisteException , JogadorNaoExiste, NumeroNaoDisponivel {
        String jogador = transf.getKey();
        String equipaOrigem = transf.getValue().getKey();
        String equipaDestino = transf.getValue().getValue();
        Equipa equipaO, equipaD;
        Jogador jog;
        if ((equipaO = this.equipas.get(equipaOrigem)) == null) throw new EquipaNaoExisteException("Equipa Origem não existe");
        if ((jog = equipaO.procuraJogador(jogador)) == null) throw new JogadorNaoExiste ("Jogador não existe na equipa origem");
        if ((equipaD = this.equipas.get(equipaDestino)) == null) throw new EquipaNaoExisteException("Equipa Destino não existe");
        equipaO.removeJogador(jog);
        equipaD.insereJogadorTransferencia(jog);
    }

    public Jogador consultaJogador(String nome) throws JogadorNaoExiste{
        Iterator<Equipa> it = this.equipas.values().iterator();
        boolean encontrou = false;
        Jogador result = null;
        while(!encontrou && it.hasNext()){
            result = it.next().procuraJogador(nome);
            if (result != null ) encontrou = true;
        }
        if (result == null) throw new JogadorNaoExiste("Jogador não existe");
        return result;
    }

    public String toString(){
        return "Liga: { " +
                "Equipas: " + this.equipas.toString() +
                "Número de equipas: " + this.n_equipas +
                " }";
    }

}