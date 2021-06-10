package com.company;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class Equipa implements Comparable<Equipa>, Serializable {
    private String nome;
    private Map<Integer, Jogador> jogadores;
    private Map<String,List<Jogador>> titulares;
    private int habilidade;

    //Construtores da classe Equipa

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

    /**
     *  Método de consulta do valor da variável de instância Nome
     * @return variável de instancia Nome
     **/
    public String getNome(){
        return this.nome;
    }



    /**
     *  Método de consulta do valor da variável de instância Jogadores
     * @return variável de instância Jogadores
     **/
    public Map<Integer,Jogador> getJogadores() {
        return this.jogadores.values().stream().collect(Collectors.toMap(Jogador::getNumeroJogador, Jogador::clone));
    }

    /**
     *  Método de consulta de um Jogador da Equipa
     * @param procura Jogador a procurar
     * @return Cópia do Jogador especificado no parametro
     * @throws JogadorNaoExiste caso não exista
     **/

    public Jogador getJogadorEquipa(Jogador procura) throws JogadorNaoExiste {
        if (!this.jogadores.containsKey(procura.getNumeroJogador())) throw new JogadorNaoExiste("Jogador não existe nesta equipa");
        return this.jogadores.get(procura.getNumeroJogador());
    }

    /**
     *  Método de consulta do valor da variável de instância Titulares
     * @return variável de instância Titulares
     **/
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

    /**
     *  Método de consulta da lista de titulares
     * @return lista dos titulares da equipa
     **/

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

    /**
     *  Método de consulta do valor da variável de instância Habilidade
     * @return variável de instância Habilidade
     **/
    public int getHabilidade(){
        return this.habilidade;
    }



    /**
     *  Método de alteração da variável de instância jogadores
     * @param jogadores novo valor da variável de instância jogadores
     **/

    public void setJogadores(Map<Integer,Jogador> jogadores) {
        this.jogadores = jogadores.values().stream().collect(Collectors.toMap(Jogador::getNumeroJogador, Jogador::clone));
    }




    /**
     *  Método toString da classe Equipa
     * @return String com a representação em texto da classe Equipa
     **/

    public String toString() {
        return "Equipa{" +
                "Nome: " + this.nome +
                //"\n\nJogadores =\n\n" + this.jogadores.toString() +
                "\n\n\n\ntitulares =" + this.titulares.toString() + "}";
    }


    /**
     *  Método copia de uma Equipa
     * @return Copia de uma Equipa
     **/
    public Equipa clone(){
        return new Equipa(this);
    }

    /**
     *  Método compareTo que define a ordem natural como a ordem alfabética
     * @param b Equipa a comparar
     * @return valor da comparação (iguais -> (0); 1º > 2º -> positivo; 1º < 2º -> negativo)
     **/

    public int compareTo(Equipa b){
        //iguais -> 0
        //primeiro maior que o segundo -> > 0
        //segundo maior que o primeiro -> < 0
        return this.getNome().compareTo(b.getNome());
    }


    /**
     *  Método de parse de uma Equipa
     * @param input lido do ficheiro, separando os atributos por virgulas
     * @return Equipa
     **/

    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }

    /**
     *  Método de inserção de uma cópia de um jogador na equipa
     * @param j jogador a adicionar
     **/

    public void insereJogador(Jogador j){
        this.jogadores.put(j.getNumeroJogador(),j.clone());
    }


    /**
     *  Método de inserção de um jogador como resultado de transferência
     * @param j jogador a ser inserido
     * @return Avancado
     * @throws NumeroNaoDisponivel caso o número do jogador transferido nao esteja disponivel na equipa Destino
     **/
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

    /**
     *  Método de remoção de um jogador na equipa
     * @param j jogador a remover
     * @throws JogadorNaoExiste caso não exista
     **/

    public void removeJogador(Jogador j) throws JogadorNaoExiste{
        if (this.jogadores.remove(j.getNumeroJogador()) == null) throw new JogadorNaoExiste("Jogador não existe na equipa destino");
    }

    /**
     *  Método de procura de um jogador na equipa
     * @param nome jogador a adicionar
     * @return jogador encontrado ou null caso não conste na equipa
     **/

    public Jogador procuraJogador(String nome){
        return this.jogadores.values().stream().filter(j -> j.getNome().equals(nome)).findFirst().orElse(null);
    }


    /**
     * Método de cálculo de Habilidade dos titulares de uma Equipa
     * @return  valor calculado
     */
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

    /**
     *  Método de inserção de n jogadores de uma lista à lista de titulares da Equipa
     * @param titulares lista de jogadores a serem adicionados
     **/

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

    /**
     *  Método que cria a lista de titulares consoante a tática especificada pelo utilizador. Esta inserção tem em conta listas ordenadas (com base na habilidade)
     *  de cada posição, escolhendo o melhor jogador a cada inserção
     * @param nDefesas número de defesas
     * @param nLaterais número de laterais
     * @param nMedios número de médios
     * @param nAvancados número de avançados
     * @throws InsufficientPlayers caso o número cumulativo de jogadores na tática não seja 10 (o guarda redes não conta)
     **/
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
        result.put("Defesa", aux);
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

    /**
     *  Método que realiza uma substituição. Percorrendo a lista de titulares, é feita a verificação da presença do jogador especificado para sair e do jogador
     *  que vai entrar. Assim, caso o jogador a sair conste nos titulares, é validada a sua saída e removido da lista de titulares. Para o jogador a entrar, caso
     *  não conste na lista de titulares, é inserido na mesma e é feita a verificação da igualdade da posição atual com a sua posição de raíz. Caso sejam diferentes,
     *  é feito um decremento da sua habilidade de 30% e a sua posição atual é alterada para a posição para a qual foi substituído.
     * @param in jogador a entrar
     * @param out jogador a sair
     * @throws SubstituicaoInvalida caso o jogador a entrar já conste nos titulares, ou o jogador a sair não conste nos titulares, ou caso algum destes não exista na equipa
     **/

    // sai -> entra

    public Map.Entry<Boolean, Map<Integer, Integer>> substituiEquipa(Jogador in, Jogador out, Map<Integer, Integer> subs) throws SubstituicaoInvalida{
        boolean success = false;
        Iterator<Map.Entry<String, List<Jogador>>> it = this.titulares.entrySet().iterator();
        List<Jogador> aux;
        Map.Entry<String, List<Jogador>> entry;
        String posicao = "";
        boolean encontrouOut = false, encontrouIn = false;
        List<Jogador> removerOut = null;
        while (it.hasNext()){
            entry = it.next();
            aux = entry.getValue();
            if (aux.contains(out)){
                encontrouOut = true;
                removerOut = aux;
                posicao = entry.getKey();
            }
            if (aux.contains(in)){
                encontrouIn = true;
                throw new SubstituicaoInvalida("Jogador" + in.getNome() + "já consta nos titulares");
            }
        }
        if (!encontrouOut) throw new SubstituicaoInvalida("Jogador " + out.getNome() + "não consta da lista dos titulares");

        if (subs.containsKey(in.getNumeroJogador())) throw new SubstituicaoInvalida("Jogador " + in.getNome() + "já foi substituído antes e não pode voltar a entrar");

        removerOut.remove(out);
        if (!in.getPosicao().equals(posicao)){
            in.setHabilidade((int) (in.getHabilidade()*0.7));
            in.setPosicao(posicao);
        }
        removerOut.add(in.clone());
        subs.put(out.getNumeroJogador(), in.getNumeroJogador());
        success = true;
        return new AbstractMap.SimpleEntry<>(success, subs);
    }
/*
    public void substituiEquipa(Jogador in, Jogador out) throws SubstituicaoInvalida{
        Iterator<Map.Entry<String, List<Jogador>>> it = this.titulares.entrySet().iterator();
        List<Jogador> aux;
        Map.Entry<String, List<Jogador>> entry;
        String posicao = "";
        boolean encontrouOut = false, encontrouIn = false;
        while (it.hasNext()){
            entry = it.next();
            aux = entry.getValue();
            if (aux.contains(out)){
                encontrouOut = true;
                aux.remove(out);
                posicao = entry.getKey();
            }
            if (aux.contains(in)){
                encontrouIn = true;
                throw new SubstituicaoInvalida("Jogador" + in.getNome() + "já consta nos titulares");
            }
        }
        if (!encontrouOut) throw new SubstituicaoInvalida("Jogador " + out.getNome() + "não consta da lista dos titulares");
        if (!in.getPosicao().equals(posicao)){
            in.setHabilidade((int) (in.getHabilidade()*0.7));
            in.setPosicao(posicao);
        }
        aux = this.titulares.get(posicao);
        aux.add(in);
        this.titulares.put(posicao, aux);
    }
*/
    /**
     * Método que calcula a lista dos Jogadores no banco
     * @return Jogadores no plantel exceto os que estão nos titulares
     */
    public List<Jogador> listaSemTitulares(){
        List<Jogador> aux = new ArrayList<>(this.jogadores.values());
        Iterator<Map.Entry<String, List<Jogador>>> it = this.titulares.entrySet().iterator();
        Map.Entry<String, List<Jogador>> entry;
        while (it.hasNext()){
            entry = it.next();
            for (Jogador j : entry.getValue()){
                aux.remove(j);
            }
        }
        return aux.stream().map(Jogador::clone).collect(Collectors.toList());
    }

}
