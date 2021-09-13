package com.company.BehaivoursPack.FMS;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class EstadoFinal extends Behaviour {
    @Override
    public void action() {
        System.out.println(" Pudimos elegir una comida :D");
    }

    @Override
    public boolean done() {
        return true;
    }
}
