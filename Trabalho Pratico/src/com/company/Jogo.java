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
    Map<Integer, Integer> substituicoesCasa; // sai -> entra
    Map<Integer, Integer> substituicoesFora;
    private String bola;

    //Construtores da classe Jogo

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

    /**
     *  Método de consulta do valor da variável de instância NomeEquipaCasa
     * @return valor da variável de instância NomeEquipaCasa do jogo
     **/
    public String getNomeEquipaCasa() {
        return this.nomeEquipaCasa;
    }


    /**
     *  Método de consulta do valor da variável de instância NomeEquipaFora
     * @return valor da variável de instância NomeEquipaFora do jogo
     **/
    public String getNomeEquipaFora() {
        return this.nomeEquipaFora;
    }

    /**
     *  Método de consulta do valor da variável de instância GolosCasa
     * @return valor da variável de instância golosCasa do jogo
     **/
    public int getGolosCasa() {
        return golosCasa;
    }


    /**
     *  Método de consulta do valor da variável de instância GolosFora
     * @return valor da variável de instância golosFora do jogo
     **/
    public int getGolosFora() {
        return golosFora;
    }

    /**
     *  Método de consulta do valor da variável de instância Date
     * @return valor da variável de instância Date do jogo
     **/
    public LocalDate getDate() {
        return date;
    }

    /**
     *  Método de consulta do valor da variável de instância JogadoresCasa
     * @return valor da variável de instância JogadoresCasa do jogo
     **/
    public List<Integer> getJogadoresCasa() {
        return new ArrayList<>(this.jogadoresCasa);
    }

    /**
     *  Método de consulta do valor da variável de instância JogadoresFora
     * @return valor da variável de instância JogadoresFora do jogo
     **/
    public List<Integer> getJogadoresFora() {
        return new ArrayList<>(this.jogadoresFora);
    }

    /**
     *  Método de consulta do valor da variável de instância EquipaCasa
     * @return valor da variável de instância EquipaCasa do jogo
     **/
    public Equipa getEquipaCasa(){
        return this.equipaCasa.clone();
    }

    /**
     *  Método de consulta do valor da variável de instância EquipaFora
     * @return valor da variável de instância EquipaFora do jogo
     **/
    public Equipa getEquipaFora(){
        return this.equipaFora.clone();
    }

    /**
     *  Método de consulta do valor da variável de instância SubstituicoesCasa
     * @return valor da variável de SubstituicoesCasa Date do jogo
     **/
    public Map<Integer, Integer> getSubstituicoesCasa() {
        return new HashMap<>(this.substituicoesCasa);
    }

    /**
     *  Método de consulta do valor da variável de instância SubstituicoesFora
     * @return valor da variável de instância SubstituicoesFora do jogo
     **/
    public Map<Integer, Integer> getSubstituicoesFora() {
        return new HashMap<>(this.substituicoesFora);
    }

    /**
     *  Método de consulta do valor da variável de instância Bola
     * @return valor da variável de instância Bola do jogo
     **/
    public String getBola(){
        return this.bola;
    }


    /**
     *  Método de alteração da variável de instância equipaCasa
     * @param casa novo valor da variável de instância equipaFora
     **/

    public void setEquipaCasa(Equipa casa){
        this.equipaCasa = new Equipa(casa);
    }

    /**
     *  Método de alteração da variável de instância equipaFora
     * @param fora novo valor da variável de instância equipaFora
     **/

    public void setEquipaFora(Equipa fora){
        this.equipaFora = new Equipa(fora);
    }

    /**
     *  Método de alteração da variável de instância jogadoresCasa
     * @param jogCasa novo valor da variável de instância jogadoresCasa
     **/

    public void setJogadoresCasa(List<Integer> jogCasa){
        this.jogadoresCasa = new ArrayList<>(jogCasa);
    }

    /**
     *  Método de alteração da variável de instância jogadoresFora
     * @param jogFora novo valor da variável de instância jogadoresFora
     **/

    public void setJogadoresFora(List<Integer> jogFora){
        this.jogadoresFora = new ArrayList<>(jogFora);
    }

    public void setSubstituicoesCasa(Map<Integer, Integer> subs){
        this.substituicoesCasa = new HashMap<>(subs);
    }

    public void setSubstituicoesFora(Map<Integer, Integer> subs){
        this.substituicoesFora = new HashMap<>(subs);
    }

    /**
     *  Método de alteração da variável de instância golosCasa
     * @param gCasa novo valor da variável de instância golosCasa
     **/

    public void setGolosCasa(int gCasa){
        this.golosCasa = gCasa;
    }

    /**
     *  Método de alteração da variável de instância golosFora
     * @param gFora novo valor da variável de instância golosFora
     **/

    public void setGolosFora(int gFora){
        this.golosFora = gFora;
    }

    /**
     *  Método de alteração da variável de instância nomeEquipaCasa
     * @param nome novo valor da variável de instância nomeEquipaCasa
     **/

    public void setNomeEquipaCasa(String nome){
        this.nomeEquipaCasa = nome;
    }

    /**
     *  Método de alteração da variável de instância nomeEquipaFora
     * @param nome novo valor da variável de instância nomeEquipaFora
     **/

    public void setNomeEquipaFora(String nome){
        this.nomeEquipaFora = nome;
    }



    /**
     *  Método de representação da classe Jogo sob a forma de uma String
     * @return String com a representação da classe Jogo
     **/

    public String toString() {
        return  "Jogo:" + this.nomeEquipaCasa + " - " + this.nomeEquipaFora
                + " ; " + subsToString(substituicoesCasa) + " ; " + subsToString(substituicoesFora) + "\n";
    }

    //clone

    /**
     *  Método de cópia de um objeto da classe Jogo
     * @return Jogo cópia do jogo sob o qual foi invocado o método clone
     **/

    public Jogo clone(){
        return new Jogo(this);
    }


    //Métodos relativos ao funcionamento da classe Jogo


    /**
     *  Método de pase de uma String para um objeto da classe Jogo
     * @param input String lida no ficheiro, com os atributos de Jogo separados por vírgulas
     * @return Objeto da classe Jogo com os atributos especificados
     **/

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

    /**
     *  Método de representação dos substitutos de uma equipa
     * @param subs Map dos jogadores substitutos
     * @return String de representação dos substitutos de uma equipa
     **/

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

    /**
     *  Método de inserção dos titulares da equipaCasa que chama o método adicionatitularesEquipa da classe equipa
     **/

    public void adicionaTitularesCasaJogo(){
        this.equipaCasa.adicionaTitularesEquipa(this.jogadoresCasa);
    }

    /**
     *  Método de inserção dos titulares da equipaFora que chama o método adicionatitularesEquipa da classe equipa
     **/

    public void adicionaTitularesForaJogo(){
        this.equipaFora.adicionaTitularesEquipa(this.jogadoresFora);
    }


    /**
     *  Método de cálculo do Jogo. Este método usa uma noção de favorecimento da equipa com maior habilidade. Este favorecimento tem por base o valor da diferença entre as habilidades
     *  das duas equipas, sendo que, este valor é limitado com o máximo de 5 (noutro método), sendo que quando uma equipa é favorecida, o favorecimento da outra será nulo.
     *  Introduzimos a noção de Bola, uma string que pode ter o valor de uma de várias ações/áreas. Essas são Canto, Pontapé de Baliza, Área, Defesa e Meio Campo. Quando o método é chamado,
     *  é verificado o valor de bola e, consoante esse valor, é executado um método que irá calcular o posicionamento seguinte da bola.
     *  O reposicionamento da bola tem em conta um rand_int, as probabilidades definidas para cada resultado possível e um valor de favorecimento (que aumenta as probabilidades de algo acontecer que favorece a equipa com maior Habilidade)
     *  Pontapé de Baliza Fora -> Pontapé de baliza para a equipa de fora
     *  Canto Fora -> Canto a favor da equipa da casa, na área da equipa de fora
     *  Área Fora -> A bola está na área da fora, portanto, à partida será um ataque da equipa da casa
     *  Defesa Fora -> A bola está na área entre o meio campo e a área da equipa de fora
     *  Meio Campo -> A bola está no meio campo
     *  Defesa Casa -> A bola está na área entre o meio campo e a área da equipa da casa
     *  Área Casa -> A bola está na área da casa, portanto, à partida será um ataque da equipa de fora
     *  Canto Casa -> Canto a favor da equipa de fora, na área da equipa da casa
     *  Pontapé de Baliza Casa -> Pontapé de baliza para a equipa da casa
     * @param casa valor de favorecimento da equipa da casa
     * @param fora valor de favorecimento da equipa de fora
     **/


    //RESULT GOLO CASA -> 1
    //RESULT GOLO FORA -> 2
    public int calculaJogo(int casa, int fora) {
        Random rand = new Random();
        int rand_int = rand.nextInt(100);
        int result = 0;
        int favoreceCasa = (casa-fora);
        int favoreceFora = (fora-casa);
        switch (this.bola) {
            case "Canto Fora":
                result = this.cantoFora(rand_int, 10 + favoreceCasa,25 + favoreceCasa,25 + favoreceFora,25 + favoreceFora,15);
                break;
            case "Pontape de Baliza Fora":
                this.pbFora(rand_int,50,35,15);
                break;
            case "Area Fora":
                result = this.areaFora(rand_int, 10 + favoreceCasa, 20 + favoreceCasa, 20 + favoreceFora, 30 + favoreceFora, 5, 15);
                break;
            case "Defesa Fora":
                result = this.defesaFora(rand_int, 5 + favoreceCasa,40 + favoreceCasa,10 + favoreceCasa,10 + favoreceFora,25 + favoreceFora,10 + favoreceFora);
                break;
            case "Meio Campo":
                result = this.meioCampo(rand_int,2,2,18 + favoreceFora,18 + favoreceCasa,30 + favoreceCasa,30 + favoreceFora);
                break;
            case "Defesa Casa":
                result = this.defesaCasa(rand_int, 5 + favoreceFora, 40 + favoreceCasa, 10 + favoreceCasa, 10 + favoreceFora, 25 + favoreceFora, 10 + favoreceCasa);
                break;
            case "Area Casa":
                result = this.areaCasa(rand_int, 10 + favoreceFora, 20 + favoreceFora, 20 + favoreceCasa, 30, 15, 5);
                break;
            case "Canto Casa":
                result = this.cantoCasa(rand_int, 10 + favoreceFora, 25 + favoreceFora, 25 + favoreceCasa , 25 + favoreceCasa, 15);
            case "Pontape de Baliza Casa":
                this.pbCasa(rand_int, 50, 35, 15);
                break;
        }
        return result;
    }

    /**
     *  Método de decisão da próxima posição da bola a partir da posição canto a favor da equipa da casa
     * @param rand_int valor aleatório de probabilidade
     * @param goloCasa valor da probabilidade de, a partir do canto na área da equipa de fora, a equipa da casa marcar
     * @param cantoFora valor da probabilidade de, a partir do canto na área da equipa de fora, a equipa da casa beneficiar de um novo canto
     * @param pbFora valor da probabilidade de, a partir do canto na área da equipa de fora, ser pontapé de baliza para a equipa de fora
     * @param defesaFora valor da probabilidade de, a partir do canto na área da equipa de fora, a bola passar para a zona da defesa da equipa de fora
     * @param meioCampo valor da probabilidade de, a partir do canto na área da equipa de fora, a equipa de fora aliviar a bola para o meio campo
     **/

    public int cantoFora(int rand_int, int goloCasa, int cantoFora, int pbFora, int defesaFora, int meioCampo){
        int result = 0;
        if (rand_int <= goloCasa) {
            this.golosCasa++;
            result = 1;
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
        return result;
    }

    /**
     * Metodo de cálculo de decisão da proxima posição da bola em situa~ão de pontapé de baliza da equipa de fora
     * @param rand_int valor aleatório de probabilidade
     * @param meioCampo probabilidade de a bola ir para o meio campo
     * @param defesaFora probabilidade de a bola ir para a zona de defesa da equipa de fora
     * @param defesaCasa probabilidade de a bola ir para a zona de defesa da equipa da casa
     */
    public void pbFora(int rand_int, int meioCampo, int defesaFora, int defesaCasa){
        if (rand_int <= meioCampo) {
            this.bola = "Meio Campo";
        } else if (rand_int <= meioCampo + defesaFora) {
            this.bola = "Defesa Fora";
        } else {
            this.bola = "Defesa Casa";
        }
    }



    /**
     * Metodo de cálculo de decisão da proxima posição da bola em situação de pontapé de baliza da equipa de fora
     * @param rand_int valor aleatório de probabilidade
     * @param goloCasa valor da probabilidade de ser marcado um golo pela equipa da Casa
     * @param cantoFora valor da probabilidade de, a partir do canto na área da equipa de fora, a equipa da casa beneficiar de um novo canto
     * @param pbFora valor de probabilidade da ação seguinte ser um pontapé de baliza para a equipa de fora
     * @param defesaFora  valor de probabilidade da bola ficar na área de defesa da equipa de fora
     * @param defesaCasa valor de probabilidade da bola ser aliviada para a área de defesa da equipa de fora
     * @param meioCampo valor de probabilidade da bola ser aliviada para o meio campo
     */
    public int areaFora(int rand_int, int goloCasa, int cantoFora, int pbFora, int defesaFora, int defesaCasa, int meioCampo){
        int result = 0;
        if (rand_int <= goloCasa) {
            this.golosCasa++;
            result = 1;
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
        return result;
    }

    /**
     *  Método de decisão da próxima posição da bola a partir da área da defesa da equipa de fora
     * @param rand_int valor aleatório de probabilidade
     * @param goloCasa valor da probabilidade de, a partir da área de defesa da equipa de fora, a equipa da casa marcar
     * @param areaFora valor da probabilidade de, a partir da área de defesa da equipa de fora, a bola passar para a área da equipa de fora
     * @param cantoFora valor da probabilidade de, a partir da área de defesa da equipa de fora, a equipa da casa beneficiar de um canto
     * @param pbFora valor da probabilidade de, a partir da área de defesa da equipa de fora, ser pontapé de baliza para a equipa de fora
     * @param defesaCasa valor da probabilidade de, a partir da área de defesa da equipa de fora, a equipa de fora aliviar a bola para a área da defesa da equipa da casa
     * @param meioCampo valor da probabilidade de, a partir da área de defesa da equipa de fora, a equipa de fora aliviar a bola para o meio campo
     **/

    public int defesaFora(int rand_int, int goloCasa, int areaFora, int cantoFora, int pbFora, int defesaCasa, int meioCampo){
        int result = 0;
        if (rand_int <= goloCasa) {
            this.golosCasa++;
            result = 1;
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
        return result;
    }

    /**
     *  Método de decisão da próxima posição da bola a partir da área da defesa da equipa de fora
     * @param rand_int valor aleatório de probabilidade
     * @param goloCasa valor da probabilidade da equipa da casa marcar do meio campo
     * @param goloFora valor da probabilidade da equipa visitante marcar do meio campo
     * @param areaCasa valor da probabilidade da bola passar para a área da equipa da casa
     * @param areaFora valor da probabilidade da bola passar para a área da equipa de fora
     * @param defesaFora valor da probabilidade da bola passar para a área de defesa da equipa de fora
     * @param defesaCasa valor da probabilidade da bola passar para a área de defesa da equipa da casa
     **/

    public int meioCampo(int rand_int, int goloCasa, int goloFora, int areaCasa, int areaFora, int defesaFora, int defesaCasa){
        int result = 0;
        if (rand_int <= goloCasa) {
            this.golosCasa++;
            result = 1;
            System.out.println("GOLO CARALHO");
            this.bola = "Meio Campo";
        } else if (rand_int <= goloCasa + goloFora) {
            this.golosFora++;
            result = 2;
            System.out.println("GOLO CARALHO");
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
        return result;
    }

    /**
     *  Método de decisão da próxima posição da bola a partir da área da defesa da equipa de fora
     * @param rand_int valor aleatório de probabilidade
     * @param goloFora valor da probabilidade da equipa de fora marcar
     * @param meioCampo valor da probabilidade da bola passar para o meio campo
     * @param pbCasa valor da probabilidade da equipa da casa beneficiar de um pontapé de baliza
     * @param cantoCasa valor da probabilidade da equipa de Fora beneficiar de um canto
     * @param areaCasa valor da probabilidade da bola passar para a área da equipa da casa
     * @param defesaFora valor da probabilidade da bola ser aliviada para a zona da defesa da equipa de fora
     **/

    public int defesaCasa (int rand_int, int goloFora, int meioCampo, int pbCasa, int cantoCasa, int areaCasa, int defesaFora){
        int result = 0;
        if (rand_int <= goloFora) {
            this.golosFora++;
            result = 2;
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
        return result;
    }

    /**
     * Metodo de cálculo de decisão da proxima posição da bola em situação de pontapé de baliza da equipa de fora
     * @param rand_int valor aleatório de probabilidade
     * @param goloFora valor da probabilidade de ser marcado um golo pela equipa de Fora
     * @param cantoCasa valor da probabilidade da equipa de fora beneficiar de um canto
     * @param pbCasa valor de probabilidade da ação seguinte ser um pontapé de baliza para a equipa de casa
     * @param defesaCasa valor de probabilidade da bola passar para a área de defesa da casa
     * @param meioCampo valor de probabilidade da bola ser aliviada para o meio campo
     * @param defesaFora valor de probabilidade da bola ser aliviada para a zona de defesa da equipa de fora
     */

    public int areaCasa(int rand_int, int goloFora, int cantoCasa, int pbCasa, int defesaCasa, int meioCampo, int defesaFora){
        int result = 0;
        if (rand_int <= cantoCasa) {
            result = 2;
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
        return result;
    }


    /**
     * Metodo de cálculo de decisão da proxima posição da bola em situa~ão de pontapé de baliza da equipa de fora
     * @param rand_int valor aleatório de probabilidade
     * @param meioCampo probabilidade de a bola ir para o meio campo
     * @param defesaCasa probabilidade de a bola ir para a zona de defesa da equipa da casa
     * @param defesaFora probabilidade de a bola ir para a zona de defesa da equipa de fora
     */
    public void pbCasa (int rand_int, int meioCampo, int defesaCasa, int defesaFora){
        if (rand_int <= meioCampo) {
            this.bola = "Meio Campo";
        } else if (rand_int <= meioCampo + defesaCasa) {
            this.bola = "Defesa Casa";
        } else {
            this.bola = "Defesa Fora";
        }
    }

    /**
     * Metodo de cálculo de decisão da proxima posição da bola em situação de pontapé de baliza da equipa de fora
     * @param rand_int valor aleatório de probabilidade
     * @param goloFora valor da probabilidade de ser marcado um golo pela equipa de Fora
     * @param cantoCasa valor da probabilidade da equipa de fora beneficiar de um novo canto
     * @param pbCasa valor de probabilidade da ação seguinte ser um pontapé de baliza para a equipa de casa
     * @param defesaCasa valor de probabilidade da bola passar para a área de defesa da casa
     * @param meioCampo valor de probabilidade da bola ser aliviada para o meio campo
     */

    public int cantoCasa(int rand_int, int goloFora, int cantoCasa, int pbCasa, int defesaCasa, int meioCampo){
        int result = 0;
        if (rand_int <= goloFora){
            this.golosFora++;
            System.out.println("GOLO CARALHO");
            result = 2;
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
        return result;
    }

    /**
     * Metodo de susbtituição que identifica os objetos Jogador a partir dos seus nomes e chama o método de substituição da classe equipa
     * @param equipa Equipa sobre a qual será efetuada a substituição
     * @param jIn nome do jogador a entrar
     * @param jOut nome do jogador a sair
     * @throws JogadorNaoExiste caso algum dos jogadores não conste na equipa em questão
     * @throws SubstituicaoInvalida caso a substituição seja inválida
     */

    public boolean substituicao (Equipa equipa , String jIn , String jOut) throws JogadorNaoExiste , SubstituicaoInvalida {

        Jogador jogIn = equipa.procuraJogador(jIn);
        Jogador jogOut = equipa.procuraJogador(jOut);
        Map<Integer, Integer> subs;
        Map.Entry<Boolean, Map<Integer, Integer>> resSub;

        if(jogIn == null) throw new JogadorNaoExiste ("" + jIn + " não existe na equipa");
        if(jogOut == null) throw new JogadorNaoExiste ("" + jOut + " não existe na equipa");

        if (equipa.equals(this.equipaCasa)){
            if (this.substituicoesCasa.containsKey(jogIn.getNumeroJogador())) throw new SubstituicaoInvalida("Jogador a entrar já foi substituido e não pode voltar a entrar no jogo");
            else {
                resSub = equipa.substituiEquipa(jogIn, jogOut, this.getSubstituicoesCasa());
                this.setSubstituicoesCasa(resSub.getValue());
            }
        }else{
            if (this.substituicoesFora.containsKey(jogIn.getNumeroJogador())) throw new SubstituicaoInvalida("Jogador a entrar já foi substituido e não pode voltar a entrar no jogo");
            else {
                resSub = equipa.substituiEquipa(jogIn, jogOut, this.getSubstituicoesFora());
                this.setSubstituicoesFora(resSub.getValue());
            }
        }
        return resSub.getKey();
    }
}
