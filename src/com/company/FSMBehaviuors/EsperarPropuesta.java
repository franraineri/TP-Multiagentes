package beh;

import jade.core.behaviours.Behaviour;

public class EsperarPropuesta extends Behaviour {

	@Override
	public void action() {
		// Recibo un mensaje
		ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));

		if (msg != null) { // Si recibi el mensaje, lo proceso
			recibido = true;
			System.out.println("Mensaje Recibido: '" + msg.getContent() + "'");
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
