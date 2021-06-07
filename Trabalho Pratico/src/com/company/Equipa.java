package com.company;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class Equipa implements Comparable<Equipa>{
    private String nome;
    private Map<Integer, Jogador> jogadores;
    private Map<String,List<Jogador>> titulares;
    private int habilidade;


    public Equipa(){
        this.nome = "";
        this.jogadores = new HashMap<>();
        this.titulares = new HashMap<>();
        this.habilidade = 0;
    }

    public Equipa(String name){
        this.nome = name;
        this.jogadores = new HashMap<>();
        this.titulares = new HashMap<>();
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

    public Map<String, List<Jogador>> getTitulares(){
        List<Jogador> aux;
        Map<String, List<Jogador>> result = new HashMap<>();
        Iterator<Map.Entry<String,List<Jogador>>> it = this.titulares.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,List<Jogador>> entry = it.next();
            aux = entry.getValue().stream().map(Jogador::clone).collect(Collectors.toList());
            result.put(entry.getKey(), aux);
        }
        return result;
    }

    public List<Jogador> getTitularesList(){
        List<Jogador> aux = new ArrayList<>();
        Iterator<Map.Entry<String,List<Jogador>>> it = this.titulares.entrySet().iterator();
        while (it.hasNext()){
            for (Jogador j : it.next().getValue()){
                aux.add(j.clone());
            }
        }
        return aux;
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
                //"\n\nJogadores =\n\n" + this.jogadores.toString() +
                "\n\n\n\ntitulares =" + this.titulares.toString() + "}";
    }

    public int compareTo(Equipa b){
        //iguais -> 0
        //primeiro maior que o segundo -> > 0
        //segundo maior que o primeiro -> < 0
        return this.getNome().compareTo(b.getNome());
    }

    public int calculaHabilidadeEquipa (){
        int habilidadeCumulativa = 0;
        Iterator<Map.Entry<String,List<Jogador>>> it = this.titulares.entrySet().iterator();
        while (it.hasNext()){
            for (Jogador j : it.next().getValue()){
                habilidadeCumulativa += j.getHabilidade();
            }
        }
        this.habilidade = habilidadeCumulativa/11;
        return (habilidadeCumulativa/11);
    }

    public void adicionaTitularesEquipa(List<Integer> titulares){
        List<Jogador> aux;
        Jogador jog;
        for (Integer i : titulares){
            jog = this.jogadores.get(i);
            String pos = jog.getPosicao();
            aux = this.titulares.get(pos);
            aux.add(jog.clone());
        }
    }



    public void criaInicial (int nDefesas , int nLaterais,int nMedios, int nAvancados) throws InsufficientPlayers{
        int total = nDefesas + nLaterais + nMedios + nAvancados;
        if (total != 10) throw new InsufficientPlayers("Número de jogadores diferente de 11");
        Map<Integer, Jogador> listaNaoAdicionados = this.jogadores.values().stream().collect(Collectors.toMap(Jogador::getNumeroJogador, Jogador::clone));
        List<Jogador> aux = null;
        Jogador auxJogador;
        Map<String, List<Jogador>> result = new HashMap<>();
        int counter_defesas = 0, counter_laterais = 0, counter_medios = 0, counter_avancados = 0;
        Jogador grTeam = this.jogadores.values().stream().filter(j -> j instanceof GuardaRedes).findFirst().orElse(null);
        if(grTeam != null) {
            aux = new ArrayList<>();
            aux.add(grTeam.clone());
            result.put(grTeam.getPosicao(),aux);
        }
        List <Jogador> defesas = this.jogadores.values().stream().filter(j -> j instanceof Defesa).map(Jogador::clone).sorted().collect(Collectors.toList());
        List <Jogador> laterais = this.jogadores.values().stream().filter(j -> j instanceof Lateral).map(Jogador::clone).sorted().collect(Collectors.toList());
        List <Jogador> medios = this.jogadores.values().stream().filter(j -> j instanceof Medio).map(Jogador::clone).sorted().collect(Collectors.toList());
        List <Jogador> avancados = this.jogadores.values().stream().filter(j -> j instanceof Avancado).map(Jogador::clone).sorted().collect(Collectors.toList());

        aux = result.get("Defesa");
        for(counter_defesas = 0 ; counter_defesas < nDefesas &&  counter_defesas < defesas.size(); counter_defesas++) {
            if (aux == null) aux = new ArrayList<>();
            auxJogador = defesas.get(counter_defesas);
            aux.add(auxJogador);
            listaNaoAdicionados.remove(auxJogador.getNumeroJogador());
        }
        result.put("Defesa",aux);
        aux = result.get("Lateral");
        for (counter_laterais = 0;counter_laterais < laterais.size() && counter_laterais < nLaterais ; counter_laterais++){
            if (aux == null) aux = new ArrayList<>();
            auxJogador = laterais.get(counter_laterais);
            aux.add(auxJogador);
            listaNaoAdicionados.remove(auxJogador.getNumeroJogador());
        }
        result.put("Lateral", aux);
        aux = result.get("Medio");
        for (counter_medios = 0; counter_medios < medios.size() && counter_medios < nMedios; counter_medios++){
            if (aux == null) aux = new ArrayList<>();
            auxJogador = medios.get(counter_medios);
            aux.add(auxJogador);
            listaNaoAdicionados.remove(auxJogador.getNumeroJogador());
        }
        result.put("Medio", aux);
        aux = result.get("Avancado");
        for (counter_avancados = 0; counter_avancados < nAvancados && counter_avancados < avancados.size(); counter_avancados++){
            if (aux == null) aux = new ArrayList<>();
            auxJogador = avancados.get(counter_avancados);
            aux.add(auxJogador);
            listaNaoAdicionados.remove(auxJogador.getNumeroJogador());
        }
        result.put("Avancado", aux);
        if (grTeam == null) {
            aux = new ArrayList<>();
            auxJogador = new ArrayList<>(listaNaoAdicionados.values()).get(0);
            aux.add(auxJogador);
            result.put("Guarda-Redes", aux);
        }
        aux = result.get("Defesa");
        while (counter_defesas < nDefesas){
            auxJogador = new ArrayList<>(listaNaoAdicionados.values()).get(0);
            auxJogador.setHabilidade((int) (auxJogador.getHabilidade()*0.7));
            auxJogador.setPosicao("Defesa");
            aux.add(auxJogador);
            listaNaoAdicionados.remove(auxJogador.getNumeroJogador());
            counter_defesas++;
        }
        result.put("Defesas", aux);
        aux = result.get("Lateral");
        while (counter_laterais < nLaterais){
            auxJogador = new ArrayList<>(listaNaoAdicionados.values()).get(0);
            auxJogador.setHabilidade((int) (auxJogador.getHabilidade()*0.7));
            auxJogador.setPosicao("Lateral");
            aux.add(auxJogador);
            listaNaoAdicionados.remove(auxJogador.getNumeroJogador());
            counter_laterais++;
        }
        result.put("Lateral", aux);
        aux = result.get("Medio");
        while (counter_medios < nMedios){
            auxJogador = new ArrayList<>(listaNaoAdicionados.values()).get(0);
            auxJogador.setHabilidade((int) (auxJogador.getHabilidade()*0.7));
            auxJogador.setPosicao("Medio");
            aux.add(auxJogador);
            listaNaoAdicionados.remove(auxJogador.getNumeroJogador());
            counter_medios++;
        }
        result.put("Medio", aux);
        aux = result.get("Avancado");
        while (counter_avancados < nAvancados){
            auxJogador = new ArrayList<>(listaNaoAdicionados.values()).get(0);
            auxJogador.setHabilidade((int) (auxJogador.getHabilidade()*0.7));
            auxJogador.setPosicao("Avancado");
            aux.add(auxJogador);
            listaNaoAdicionados.remove(auxJogador.getNumeroJogador());
            counter_avancados++;
        }
        this.titulares = result;
        System.out.println(result.toString());
    }

    public Equipa clone(){
        return new Equipa(this);
    }
}
