package com.company.FSMBehaviuors;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class EsperarPropuesta extends Behaviour {
//debe esperar una propuesta de el adversario y guardarla en el datastore
	private Boolean recibido = false;
	private Float zeuthen;

	@Override
	public void action() {
		// Recibo un mensaje
		//ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
		
		//defino el template del mensaje

		System.out.println("El agente "+ myAgent.getLocalName() + " espera una propuesta...");

		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);

		// Si recibo un mensaje
		ACLMessage msg = myAgent.blockingReceive(mt);	

		if (msg != null) { // Si recibi el mensaje, lo proceso

			System.out.println("El agente " + myAgent.getLocalName() + " recibio la propuesta de " + msg.getSender().getLocalName());
			System.out.println("El mensaje recibido fue: " + msg + "\n")
			//save the message in the agent datastore
			getDataStore().put("last_message", msg);
			
			recibido = true;
		}
		else
			block(); 	// Si no lo recibi, se bloquea el comportamiento
	}

	@Override
	public void reset() {
		recibido = false;
		super.reset();
	}

	@Override
	public boolean done() {
		return false;	//no es nesesario retornar el reset pq no estamos en una FSM
	}

}
