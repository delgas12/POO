package com.company;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Jogo {
    private String nomeEquipaCasa;
    private String nomeEquipaFora;
    private int golosCasa;
    private int golosFora;
    private LocalDate date;
    private List<Integer> jogadoresCasa;
    private List<Integer> jogadoresFora;
    private Equipa equipaCasa;
    private Equipa equipaFora;
    Map<Integer, Integer> substituicoesCasa;
    Map<Integer, Integer> substituicoesFora;

    public Jogo(){
        nomeEquipaCasa = "";
        nomeEquipaFora = "";
        golosCasa = 0;
        golosFora = 0;
        jogadoresCasa = new ArrayList<>();
        jogadoresFora = new ArrayList<>();
        equipaCasa = new Equipa();
        equipaFora = new Equipa();
        substituicoesCasa = new HashMap<>();
        substituicoesFora = new HashMap<>();
    }

    public Jogo (String ec, String ef, int gc, int gf, LocalDate d,  List<Integer> jc, Map<Integer, Integer> sc,  List<Integer> jf, Map<Integer, Integer> sf){
        nomeEquipaCasa = ec;
        nomeEquipaFora = ef;
        golosCasa = gc;
        golosFora = gf;
        date = d;
        jogadoresCasa = new ArrayList<>(jc);
        jogadoresFora = new ArrayList<>(jf);
        substituicoesCasa = new HashMap<>(sc);
        substituicoesFora = new HashMap<>(sf);
    }

    public Jogo(Jogo j){
        this.nomeEquipaCasa = j.getNomeEquipaCasa();
        this.nomeEquipaFora = j.getNomeEquipaFora();
        this.golosCasa = j.getGolosCasa();
        this.golosFora = j.getGolosFora();
        this.jogadoresCasa = j.getJogadoresCasa();
        this.jogadoresFora = j.getJogadoresFora();
        this.substituicoesCasa = j.getSubstituicoesCasa();
        this.substituicoesFora = j.getSubstituicoesFora();
    }


    public String getNomeEquipaCasa() {
        return this.nomeEquipaCasa;
    }

    public String getNomeEquipaFora() {
        return this.nomeEquipaFora;
    }

    public int getGolosCasa() {
        return golosCasa;
    }

    public int getGolosFora() {
        return golosFora;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Integer> getJogadoresCasa() {
        return new ArrayList<>(this.jogadoresCasa);
    }

    public List<Integer> getJogadoresFora() {
        return new ArrayList<>(this.jogadoresFora);
    }
    public Equipa getEquipaCasa(){
        return this.equipaCasa.clone();
    }

    public Equipa getEquipaFora(){
        return this.equipaCasa.clone();
    }
    public Map<Integer, Integer> getSubstituicoesCasa() {
        return new HashMap<>(this.substituicoesCasa);
    }

    public Map<Integer, Integer> getSubstituicoesFora() {
        return new HashMap<>(this.substituicoesFora);
    }

    //setters
    public void setEquipaCasa(Equipa casa){
        this.equipaCasa = new Equipa(casa);
    }

    public void setEquipaFora(Equipa fora){
        this.equipaCasa = new Equipa(fora);
    }

    public void setJogadoresCasa(List<Integer> jogCasa){
        this.jogadoresCasa = new ArrayList<>(jogCasa);
    }

    public void setJogadoresFora(List<Integer> jogFora){
        this.jogadoresFora = new ArrayList<>(jogFora);
    }

    public static Jogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
        for (int i = 5; i < 16; i++){
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        for (int i = 19; i < 30; i++){
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        return new Jogo(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                jc, subsC, jf, subsF);
    }

    public String toString() {
        return  "Jogo:" + this.nomeEquipaCasa + " - " + this.nomeEquipaFora
        + " ; " + subsToString(substituicoesCasa) + " ; " + subsToString(substituicoesFora) + "\n";
    }

    public String subsToString (Map<Integer,Integer> subs){
        StringBuilder result = new StringBuilder();
        Iterator<Map.Entry<Integer,Integer>> it = subs.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry<Integer,Integer>  e = it.next();
            Integer x = e.getKey();
            Integer y = e.getValue();
            result.append(x + " -> " + y + ",");
        }
        return result.toString();
    }


    public Jogo clone(){
        return new Jogo(this);
    }

    public void adicionaTitularesCasaJogo(){
        this.equipaCasa.adicionaTitularesEquipa(this.jogadoresCasa);
    }

    public void adicionaTitularesForaJogo(){
        this.equipaFora.adicionaTitularesEquipa(this.jogadoresFora);
    }
    

}
