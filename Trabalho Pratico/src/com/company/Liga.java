package com.company;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Liga implements Serializable {
    private Map<String,Equipa> equipas;
    private List<Jogo> jogos;
    private int n_equipas;


    //Contrutores da classe Liga

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

    /**
     *  Método de consulta de uma Equipa da liga
     * @param nome Equipa a procurar
     * @return Cópia da Equipa especificada no parametro
     * @throws EquipaNaoExisteException caso não exista
     **/
    public Equipa getEquipa(String nome) throws EquipaNaoExisteException{
        Equipa eq = this.equipas.get(nome);
        if (eq == null) throw new EquipaNaoExisteException("Equipa não existe");
        return eq;
    }


    /**
     *  Método de consulta do valor da variável de instância Equipas
     * @return valor da variável de instância Equipas da liga
     **/
    public Map<String,Equipa> getEquipas() {
        return this.equipas.values().stream().map(e -> e.clone()).collect(Collectors.toMap(e -> e.getNome(), e -> e.clone()));
    }

    /**
     *  Método de consulta do valor da variável de instância Equipas sobre a forma de lista
     * @return valor da variável de instância Equipas sobre a forma de lista
     **/
    public List<Equipa> getEquipasList(){
        return this.equipas.values().stream().map(e -> e.clone()).collect(Collectors.toList());
    }


    /**
     *  Método de consulta do valor da variável de instância N_equipas
     * @return valor da variável de instância N_equipas da liga
     **/
    public int getN_equipas() {
        return this.n_equipas;
    }

    /**
     *  Método de consulta do valor da variável de instância jogos
     * @return valor da variável de instância jogos da liga
     **/
    public List<Jogo> getJogos(){
        return this.jogos.stream().map(Jogo::clone).collect(Collectors.toList());
    }

    public Jogador getJogadorLiga(String equipa, Jogador procura) throws JogadorNaoExiste{
        Jogador result = this.equipas.get(equipa).getJogadorEquipa(procura);
        if ( result == null) throw new JogadorNaoExiste("Jogador não existe");
        else return result;
    }

    /**
     *  Método de alteração do variável de instância jogos
     * @param j lista com os jogos a adicionar na variável de instância jogos
     **/

    public void setJogos(List<Jogo> j){
        this.jogos = j.stream().map(Jogo::clone).collect(Collectors.toList());
    }

    /**
     *  Método de alteração do variável de instância equipas
     * @param equipas novo valor da variável de instância equipas
     **/

    public void setEquipas(Map<String,Equipa> equipas) {
        TreeMap<String,Equipa> novo = new TreeMap<>();
        Iterator<Map.Entry<String,Equipa>> it = equipas.entrySet().iterator();
        while(it.hasNext()){
            Equipa e = it.next().getValue();
            novo.put(e.getNome(),e.clone());
        }
        this.equipas = novo;
    }

    /**
     *  Método de alteração do variável de instância n_equipas
     * @param n_equipas novo valor da variável de instância n_equipas
     **/

    public void setN_equipas(int n_equipas) {
        this.n_equipas = n_equipas;
    }


    //toString

    /**
     *  Método de construção de uma String com a representação da classe Liga
     * @return String com a representação da classe Liga
     **/

    public String toString(){
        return "Liga: { " +
                "Equipas: " + this.equipas.toString() +
                "Número de equipas: " + this.n_equipas +
                " }";
    }

    /**
     *  Método cópia de uma liga
     * @return Cópia da liga
     **/
    
    public Liga clone(){
        return new Liga(this);
    }


    //Métodos relativos ao funcionamento da classe Liga

    /**
     * Método que insere um jogador numa equipa passada como parametro pelo utilizador e adiciona essa mesma equipa ao historial do jogador
     * @param j jogador a adicionar
     * @param teamName nome da equipa ao qual se pretende adicionar o jogador
     * @throws EquipaNaoExisteException caso a equipa nao exista
     * @throws NumeroNaoDisponivel caso o numero do jogador nao esteja disponivel
     */
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

    /**
     *  Método de inserção de uma nova equipa ainda não existente na liga a partir de um nome,
     *  sendo também responsável por criá-la, a partir do construtor da classe Equipa
     * @param equipa noma da equipa a criar
     **/

    public void criaEquipa(String equipa) {
        Equipa nova = new Equipa(equipa);
        this.n_equipas++;
        this.equipas.put(equipa, nova);
    }

    /**
     *  Método de inserção de uma nova equipa já existente na liga
     * @param equipa Equipa a adicionar
     **/

    public void adicionaEquipa(Equipa equipa){
        this.equipas.put(equipa.getNome(), equipa.clone());
    }


    /**
     * Método de um inserção de um jogo
     * @param jogo Jogo a adicionar
     */
    public void adicionaJogo(Jogo jogo){
        this.jogos.add(jogo.clone());
    }

    /**
     *  Método de representação da lista de todas as equipas na liga
     * @return String com a representação das Equipa da liga
     **/

    public String equipasToString(){
        Iterator<Map.Entry<String,Equipa>> it = equipas.entrySet().iterator();
        StringBuilder equipas = new StringBuilder("Equipas");
        while(it.hasNext()){
            Equipa e = it.next().getValue();
            equipas.append('a');
        }
        return equipas.toString();
    }

    /**
     *  Método de tranferência de um jogador
     * @param transf Um par com o nome do jogador e com um novo par. Este novo par, contém o nome da equipa atual do jogador e o nome da equipa para a qual será tranferido
     * @throws EquipaNaoExisteException caso alguma das equipas não exista
     * @throws JogadorNaoExiste caso o jogador não exista na equipa origem
     * @throws NumeroNaoDisponivel caso o número não esteja disponível e este tenha sido inserido com um novo número
     **/

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

    /**
     * Método de pesquisa para verificar se o jogador, dado o seu nome, existe na liga
     * @param nome do jogador
     * @return Jogador caso este exista
     * @throws JogadorNaoExiste caso não exista
     */
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

    public void guardaBin(String nomeFicheiro) throws FileNotFoundException, IOException {
        FileOutputStream bf = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(bf);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public static Liga readObj(String nomeFich) throws IOException, ClassNotFoundException{
        FileInputStream bf = new FileInputStream(nomeFich);
        ObjectInputStream ois = new ObjectInputStream(bf);
        Liga l = (Liga) ois.readObject();
        ois.close();
        return l;
    }


}