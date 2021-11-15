package com.company.FSMBehaviuors;

import com.company.Ontologias.EsMiZeuthen;
import com.company.Ontologias.MCPOntology;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class EnviarPropuesta extends Behaviour {

    private Codec codec = new SLCodec();;
    private MCPOntology ontology = new MCPOntology();

    public void Proponer( ) {

    }

    @Override
    public void action() {
        // Armo el mensaje
        ACLMessage propuesta = new ACLMessage(ACLMessage.REQUEST);
        ArrayList <String> comidas = (ArrayList<String>) this.getDataStore().get("comidas");

        propuesta.addReceiver(new AID("DES HARDcordear",AID.ISLOCALNAME));  //utilizar el df
        propuesta.setLanguage(codec.getName()); //seteamos el codec y la ontologia que usaremos
        propuesta.setOntology(ontology.getName());
        
        if (!comidas.isEmpty()){

            System.out.println( "Cantidad de comidas en la db: "+ comidas.size());
            
            //propuesta.setContent( "Te prepongo pedir para comer " + comidas.remove((int) (Math.random() * comidas.size())));

            //seteamos el contenido del mensaje con la comida que queremos pedir
            try {
                myAgent.getContentManager().fillContent(propuesta, new EsMiZeuthen());
            } catch (Codec.CodecException e) {
                e.printStackTrace();
            } catch (OntologyException e) {
                e.printStackTrace();
            }
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

