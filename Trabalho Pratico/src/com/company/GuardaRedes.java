package com.company;

import java.util.ArrayList;

public class GuardaRedes extends Jogador{

        private int elasticidade;

        public GuardaRedes(){
            super();
            this.elasticidade = 0;
        }

        public GuardaRedes(String nome, int velocidade, int resistencia, int destreza, int impulsao, int jogoDeCabeca, int remate, int passe, String posicao, ArrayList<String> historial, int elasticidade) {
            super(nome, velocidade, resistencia, destreza, impulsao, jogoDeCabeca, remate, passe,  posicao, historial);
            this.elasticidade = elasticidade;
        }

        public GuardaRedes(GuardaRedes j){
            super(j);
            this.elasticidade = j.getElasticidade();
        }

        public int getElasticidade() {
            return elasticidade;
        }

        public void setElasticidade(int elasticidade) {
            this.elasticidade = elasticidade;
        }
}



