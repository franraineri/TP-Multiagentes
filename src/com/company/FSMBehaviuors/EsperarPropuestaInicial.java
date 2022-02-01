package com.company.FSMBehaviuors;


import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

// Comportamiento ciclico que recibe propuesta iniciales y inicia la FSM 
public class EsperarPropuestaInicial extends CyclicBehaviour {

	@Override
	public void action() {

		System.out.println("Esperando propuesta inicial");

		// Solo recibimos mensajes PROPOSE iniciales
		ACLMessage prop_ini = myAgent.receive(MessageTemplate.and(
						MessageTemplate.MatchPerformative(ACLMessage.PROPOSE), 
						MessageTemplate.MatchInReplyTo("")));
		
		if (prop_ini != null) {
			myAgent.addBehaviour(new FSM(prop_ini));
		}
		else
			block();

	}

}
