package com.company.BehaivoursPack.FMS;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class EnviarPropuesta extends Behaviour {

    private static String receiver;
    public Proponer( ) {

    }

    @Override
    public void action() {
        // Armo el mensaje
        ACLMessage propuesta = new ACLMessage(ACLMessage.REQUEST);
        ArrayList <String> comidas = (ArrayList<String>) this.getDataStore().get("comidas");
        
        propuesta.addReceiver(new AID("Res",AID.ISLOCALNAME));
        
        if (!comidas.isEmpty()){
            System.out.println( "Cantidad de comidas en la db: "+ comidas.size());
            propuesta.setContent( "Te prepongo pedir para comer " + comidas.remove((int) (Math.random() * comidas.size()))); 
        }
        else {
            propuesta.setPerformative(ACLMessage.FAILURE);
            propuesta.setContent("No tengo nada mas para ofrecerte ");
            System.out.println("Me quedé sin comidas para ofrecer :(");
        }
        //Envio de REQUEST o FAILURE//
        myAgent.send(propuesta);
    }

    @Override
    public boolean done() {
        return true;
    }
}

