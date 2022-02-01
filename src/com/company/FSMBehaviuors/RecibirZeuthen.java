package com.company.FSMBehaviuors;

import com.company.Ontologias.*;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.onto.OntologyException;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class RecibirZeuthen extends Behaviour {

	private Boolean recibido= false;
	private int on_end = -1;

	@Override
	public void action() {

		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

		// Si recibo un mensaje
		ACLMessage zeuthen_msg = getAgent().receive(mt);

		if (zeuthen_msg != null) { // Si recibi el mensaje, lo proceso
			ContentElement ce = null; //extraigo el contenido del mensaje
			try {
				ce = myAgent.getContentManager().extractContent(zeuthen_msg);
			} catch (Codec.CodecException | OntologyException e) {
				e.printStackTrace();
			}
			EsMiZeuthen zeuthen_adversario = (EsMiZeuthen) ce;

			System.out.println("El agente " + myAgent.getLocalName() + " recibio el Zeuthen de " + zeuthen_msg.getSender().getLocalName());
			
			//	zeuthen.getValor(); || getPropuesta
			recibido = true;

			//comparamos el valor de zeuthen adversario con el de zeuthen propio
			//si mi zeuthen es mayor, espero por la proxima propuesta, 

			assert zeuthen_adversario != null;
			if (zeuthen_adversario.getZeuthen() > (float) getDataStore().get("zeuthen_actual")) {
				System.out.println("El agente " + myAgent.getLocalName() + " recibio la propuesta de " + zeuthen_msg.getSender().getLocalName() + " y le responde que no le gusta");
				on_end = 1;
			} else {
				System.out.println("El agente " + myAgent.getLocalName() + " recibio la propuesta de " + zeuthen_msg.getSender().getLocalName() + " y le responde que le gusta");
				on_end = 0;
			}

		}
		else
			block(); 	// Si no lo recibi, se bloquea el comportamiento
	}

	@Override
	public int onEnd(){
		return on_end;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return recibido;
	}

	@Override
	public void reset() {
		recibido = false;
	}

}
