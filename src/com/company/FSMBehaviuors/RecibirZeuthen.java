package com.company.FSMBehaviuors;

import com.company.Ontologias.EsMiZeuthen;
import jade.content.ContentElement;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class RecibirZeuthen extends Behaviour {

	private Boolean recibido= false;


	@Override
	public void action() {

		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM)

		// Si recibo un mensaje
		ACLMessage zeuthen_msg = getAgent().blockingReceive(mt);


		if (zeuthen_msg != null) { // Si recibi el mensaje, lo proceso
			ContentElement ce = myAgent.(zeuthen_msg); //extraigo el contenido del mensaje
			EsMiZeuthen zeuthen_adversario = (EsMiZeuthen) ce;

			System.out.println("El agente " + myAgent.getLocalName() + " recibio la propuesta de " + zeuthen_msg.getSender().getLocalName());
			
			//	zeuthen.getValor(); || getPropuesta
			recibido = true;
			//System.out.println("Mensaje Recibido: '" + zeuthen + "'");

			//comparamos el valor de zeuthen adversario con el de zeuthen propio
			//si mi zeuthen es mayor, espero por la proxima propuesta, 


			if (zeuthen_adversario.getZeuthen() > zeuthen.getValor()) {
				System.out.println("El agente " + myAgent.getLocalName() + " recibio la propuesta de " + zeuthen_msg.getSender().getLocalName() + " y le responde que no le gusta");
				ACLMessage msg = new ACLMessage(ACLMessage.REFUSE);
				msg.addReceiver(zeuthen_msg.getSender());
				myAgent.send(msg);
			} else {
				System.out.println("El agente " + myAgent.getLocalName() + " recibio la propuesta de " + zeuthen_msg.getSender().getLocalName() + " y le responde que le gusta");
				ACLMessage msg = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
				msg.addReceiver(zeuthen_msg.getSender());
				myAgent.send(msg);
			}

		}
		else
			block(); 	// Si no lo recibi, se bloquea el comportamiento
	}


	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
