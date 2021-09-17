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
        ACLMessage propuesta = new ACLMessage(ACLMessage.REQUEST);
        ArrayList <String> comidas = (ArrayList<String>) this.getDataStore().get("comidas");
        
        propuesta.addReceiver(new AID("Receiver",AID.ISLOCALNAME));
        
        if (!comidas.isEmpty()){
            System.out.println( "Cantidad de comidas en la db: "+comidas.size());
            propuesta.setContent( "Te prepongo pedir para comer " + comidas.remove((int) (Math.random() * comidas.size()))); 
        }
        else {
            propuesta.setPerformative(ACLMessage.FAILURE);
            propuesta.setContent(" No tengo nada mas para ofrecerte ");
        }
        //Envio de REQUEST//
        myAgent.send(req);
    }

    @Override
    public boolean done() {
        return true;
    }
}

