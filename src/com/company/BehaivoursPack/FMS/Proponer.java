package com.company.BehaivoursPack.FMS;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class Proponer extends Behaviour {

    private static String receiver;
    public Proponer( ) {
        ;
    }

    @Override
    public void action() {
        // Armo el mensaje
        ACLMessage req = new ACLMessage(ACLMessage.REQUEST);
        req.addReceiver(new AID("Receiver",AID.ISLOCALNAME));
        ArrayList <String> comidas = (ArrayList<String>) this.getDataStore().get("comidas");
        req.setContent("Te prepongo pedir para comer " + comidas.get((int) (Math.random() * comidas.size())) );

        //Envio de REQUEST//
        myAgent.send(req);
    }

    @Override
    public boolean done() {
        return true;
    }
}

