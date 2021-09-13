package com.company.BehaivoursPack.FMS;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class EsperarRespuesta extends Behaviour {

    private int respuesta = -1;
    private boolean recibido = false;

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null){
            recibido = true;
            if (msg.getContent().equals("No me gusta")){
                respuesta = 0;
            } else {
                respuesta = 1;
            }
        }else { //espero por el mensaje
            block();
        }
    }

    @Override
    public int onEnd() {
        return respuesta;
    }

    @Override
    public void reset() {
        recibido = false;
        super.reset();
    }

    @Override
    public boolean done() { //tiene que ser ciclico ???
        return recibido;
    }
}
