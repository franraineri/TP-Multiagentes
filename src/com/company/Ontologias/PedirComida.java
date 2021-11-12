package com.company.Ontologias;

import jade.content.AgentAction;

public class PedirComida implements AgentAction {
    // class PedirComida with two string atributes: String LUGAR and COMIDA cominda
    private String LUGAR;
    private Comida COMIDA;

    public PedirComida(String LUGAR, Comida COMIDA) {
        this.LUGAR = LUGAR;
        this.COMIDA = COMIDA;
    }

    public PedirComida( Comida COMIDA) {
        this.COMIDA = COMIDA;
    }

    public String getLugar() {
        return LUGAR;
    }

    public void setLugar(String LUGAR) {
        this.LUGAR = LUGAR;
    }

    public Comida getComida() {
        return COMIDA;
    }

    public void setComida(Comida COMIDA) {
        this.COMIDA = COMIDA;
    } 

}
