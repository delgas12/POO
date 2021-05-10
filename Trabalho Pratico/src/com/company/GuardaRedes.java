package com.company;

import java.util.ArrayList;

public class GuardaRedes extends Jogador{

        private int elasticidade;

        public GuardaRedes(){
            super();
            this.elasticidade = 0;
        }

        public GuardaRedes(String nome, int numero , int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe,  int elasticidade, String posicao, ArrayList<String> historial) {
            super(nome, numero , velocidade, resistencia, destreza, impulsao, jogoDeCabeca, remate, passe,  posicao, historial);
            this.elasticidade = elasticidade;
            this.setHabilidade(this.calculateHability());
        }

        public GuardaRedes(GuardaRedes j){
            super(j);
            this.elasticidade = j.getElasticidade();
        }

        public int getElasticidade() {
            return this.elasticidade;
        }

        public void setElasticidade(int elasticidade) {
            this.elasticidade = elasticidade;
        }

        public int calculateHability(){
            int result = 0;
            result = (int) ((double) this.getVelocidade() * 0.05 +
                            (double) this.getResistencia() * 0.05 +
                            (double) this.getDestreza() * 0.20 +
                            (double) this.getImpulsao() * 0.20 +
                            (double) this.getJogoDeCabeca() * 0.05 +
                            (double) this.getRemate() * 0.10 +
                            (double) this.getPasse() * 0.20 +
                            (double) this.getElasticidade() * 0.15);
            return result;
        }

        public GuardaRedes clone(){
            return  new GuardaRedes(this);
        }

    public static GuardaRedes parse(String input){
        String[] campos = input.split(",");
        return new GuardaRedes(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]),
                "GuardaRedes",
                new ArrayList<>());
    }

}



