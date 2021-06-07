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
    private String bola;

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
        this.bola = "Meio Campo";
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
        this.bola = "Meio Campo";
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
        return this.equipaFora.clone();
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

    public void setGolosCasa(int gCasa){this.golosCasa = gCasa;}
    public void setGolosFora(int gFora){this.golosFora = gFora; }

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

    public String getBola(){
        return this.bola;
    }



    //Casa X
    //Fora Y

    public void calculaJogo(int casa, int fora) {
        Random rand = new Random();
        int rand_int = rand.nextInt(100);
        int favoreceCasa = (casa-fora);
        int favoreceFora = (fora-casa);
        switch (this.bola) {
            case "Canto Fora":
                this.cantoFora(rand_int, 10 + favoreceCasa,25 + favoreceCasa,25 + favoreceFora,25 + favoreceFora,15);
                break;
            case "Pontape de Baliza Fora":
                this.pbFora(rand_int,50,35,15);
                break;
            case "Area Fora":
                this.areaFora(rand_int, 10 + favoreceCasa, 20 + favoreceCasa, 20 + favoreceFora, 30 + favoreceFora, 5, 15);
                break;
            case "Defesa Fora":
                this.defesaFora(rand_int, 5 + favoreceCasa,40 + favoreceCasa,10 + favoreceCasa,10 + favoreceFora,25 + favoreceFora,10 + favoreceFora);
                break;
            case "Meio Campo":
                this.meioCampo(rand_int,2,2,18 + favoreceFora,18 + favoreceCasa,30 + favoreceCasa,30 + favoreceFora);
                break;
            case "Defesa Casa":
                this.defesaCasa(rand_int, 5 + favoreceFora, 40 + favoreceCasa, 10 + favoreceCasa, 10 + favoreceFora, 25 + favoreceFora, 10 + favoreceCasa);
                break;
            case "Area Casa":
                this.areaCasa(rand_int, 10 + favoreceFora, 20 + favoreceFora, 20 + favoreceCasa, 30, 15, 5);
                break;
            case "Canto Casa":
                this.cantoCasa(rand_int, 10 + favoreceFora, 25 + favoreceFora, 25 + favoreceCasa , 25 + favoreceCasa, 15);
            case "Pontape de Baliza Casa":
                this.pbCasa(rand_int, 50, 35, 15);
                break;
        }
    }

    public void cantoFora(int rand_int, int goloCasa, int cantoFora, int pbFora, int defesaFora, int meioCampo){
        if (rand_int <= goloCasa) {
            this.golosCasa++;
            System.out.println("GOLO CARALHO");
            this.bola = "Meio Campo";
        } else if (rand_int <= goloCasa + cantoFora){
            this.bola = "Canto Fora";
        } else if (rand_int <= goloCasa + cantoFora + pbFora) {
            this.bola = "Pontape de Baliza Fora";
        } else if (rand_int <= goloCasa + cantoFora + pbFora + defesaFora) {
            this.bola = "Defesa Fora";
        } else { // 15 15
            this.bola = "Meio Campo";
        }
    }

    public void pbFora(int rand_int, int meioCampo, int defesaFora, int defesaCasa){
        if (rand_int <= meioCampo) {
            this.bola = "Meio Campo";
        } else if (rand_int <= meioCampo + defesaFora) {
            this.bola = "Defesa Fora";
        } else {
            this.bola = "Defesa Casa";
        }
    }

    public void areaFora(int rand_int, int goloCasa, int cantoFora, int pbFora, int defesaFora, int defesaCasa, int meioCampo){
        if (rand_int <= goloCasa) {
            this.golosCasa++;
            System.out.println("GOLO CARALHO");

            this.bola = "Meio Campo";
        } else if (rand_int <= goloCasa + cantoFora) {
            this.bola = "Canto Fora";
        } else if (rand_int <= goloCasa + cantoFora + pbFora) {
            this.bola = "Pontape de Baliza Fora";
        } else if (rand_int <= goloCasa + cantoFora + pbFora + defesaFora) {
            this.bola = "Defesa Fora";
        } else if (rand_int <= goloCasa + cantoFora + pbFora + defesaFora + defesaCasa) {
            this.bola = "Defesa Casa";
        } else {
            this.bola = "Meio Campo";
        }
    }

    public void defesaFora(int rand_int, int goloCasa, int areaFora, int cantoFora, int pbFora, int defesaCasa, int meioCampo){
        if (rand_int <= goloCasa) {
            this.golosCasa++;
            System.out.println("GOLO CARALHO");

            this.bola = "Meio Campo";
        } else if (rand_int <= goloCasa + areaFora) {
            this.bola = "Area Fora";
        } else if (rand_int <= goloCasa + areaFora + cantoFora) {
            this.bola = "Canto Fora";
        } else if (rand_int <= goloCasa + areaFora + cantoFora + pbFora) {
            this.bola = "Pontape de Baliza Fora";
        } else if (rand_int <= goloCasa + areaFora + cantoFora + pbFora + defesaCasa) {
            this.bola = "Defesa Casa";
        } else {
            this.bola = "Meio Campo";
        }
    }

    public void meioCampo(int rand_int, int goloCasa, int goloFora, int areaCasa, int areaFora, int defesaFora, int defesaCasa){
        if (rand_int <= goloCasa) {
            this.golosCasa++;
            System.out.println("GOLO CARALHO");

            this.bola = "Meio Campo";
        } else if (rand_int <= goloCasa + goloFora) {
            this.golosFora++;
            this.bola = "Meio Campo";
        } else if (rand_int <= goloCasa + goloFora + areaCasa) {
            this.bola = "Area Casa";
        } else if (rand_int <= goloCasa + goloFora + areaCasa + areaFora) {
            this.bola = "Area Fora";
        } else if (rand_int <= goloCasa + goloFora + areaCasa + areaFora + defesaFora) {
            this.bola = "Defesa Fora";
        } else {
            this.bola = "Defesa Casa";
        }
    }

    public void defesaCasa (int rand_int, int goloFora, int meioCampo, int pbCasa, int cantoCasa, int areaCasa, int defesaFora){
        if (rand_int <= goloFora) {
            this.golosCasa++;
            System.out.println("GOLO CARALHO");

            this.bola = "Meio Campo";
        } else if (rand_int <= goloFora + meioCampo) {
            this.bola = "Meio Campo";
        } else if (rand_int <= goloFora + meioCampo + pbCasa) {
            this.bola = "Pontape de Baliza Casa";
        } else if (rand_int <= goloFora + meioCampo + pbCasa + cantoCasa) {
            this.bola = "Canto Casa";
        } else if (rand_int <= goloFora + meioCampo + pbCasa + cantoCasa + areaCasa) {
            this.bola = "Area Casa";
        } else {
            this.bola = "Defesa Fora";
        }
    }

    public void areaCasa(int rand_int, int goloFora, int cantoCasa, int pbCasa, int defesaCasa, int meioCampo, int defesaFora){
        if (rand_int <= cantoCasa) {
            this.golosFora++;
            System.out.println("GOLO CARALHO");

            this.bola = "Meio Campo";
        } else if (rand_int <= goloFora) {
            this.bola = "Canto Casa";
        } else if (rand_int <= cantoCasa + pbCasa ) {
            this.bola = "Pontape de Baliza Casa";
        } else if (rand_int <= cantoCasa + pbCasa + defesaCasa) {
            this.bola = "Defesa Casa";
        } else if (rand_int <= cantoCasa + pbCasa + defesaCasa + meioCampo) {
            this.bola = "Meio Campo";
        } else {
            this.bola = "Defesa Fora";
        }
    }

    public void pbCasa (int rand_int, int meioCampo, int defesaCasa, int defesaFora){
        if (rand_int <= meioCampo) {
            this.bola = "Meio Campo";
        } else if (rand_int <= meioCampo + defesaCasa) {
            this.bola = "Defesa Casa";
        } else {
            this.bola = "Defesa Fora";
        }
    }

    public void cantoCasa(int rand_int, int goloFora, int cantoCasa, int pbCasa, int defesaCasa, int meioCampo){
        if (rand_int <= goloFora){
            this.golosFora++;
            System.out.println("GOLO CARALHO");

            this.bola = "Meio Campo";
        }
        else if (rand_int <= goloFora + cantoCasa){
            this.bola = "Canto Casa";
        }
        else if (rand_int <= goloFora + cantoCasa + pbCasa){
            this.bola = "Pontape de Baliza Casa";
        }
        else if (rand_int <= goloFora + cantoCasa + pbCasa + defesaCasa){
            this.bola = "Defesa Casa";
        }
        else{
            this.bola = "Meio Campo";
        }
    }

    public void substituicao(int jOut, int jIn){

    }

}
