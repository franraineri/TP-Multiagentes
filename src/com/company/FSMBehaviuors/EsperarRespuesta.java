package com.company.FSMBehaviuors;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class EsperarRespuesta extends Behaviour {

    private int respuesta = -1;
    private boolean recibido = false;

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();

        if (msg != null){
            recibido = true;
            System.out.println("Recibí la respuesta a mi propuesta: '" + msg.getContent() + "'");
            
            if (msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL)    //corregirlo en IntelliJ
                this.respuesta = 0;
                // this.reset();  //probar en intelli J
            else { 
                if (msg.getPerformative() == ACLMessage.REJECT_PROPOSAL){    //corregirlo en IntelliJ
                    this.respuesta = 1;
                } else {
                    this.respuesta = -1;
                    System.out.println("El mensaje recibido no acepta ni rechaza mi propuesta");
                }
            }
        } else { //espero por el mensaje
            block();
        }

    }

    @Override
    public int onEnd() {
        return this.respuesta;
    }

    @Override
    public void reset() {
        super.reset();
        recibido = false;
    }

    @Override
    public boolean done() { //tiene que ser ciclico ???
        return recibido;
    }
}
