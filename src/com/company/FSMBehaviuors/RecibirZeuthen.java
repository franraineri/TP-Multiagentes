package beh;

import jade.core.behaviours.Behaviour;

public class RecibirZeuthen extends Behaviour {

	@Override
	public void action() {

		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM)

		// Si recibo un mensaje
		ACLMessage zeuthen_msg = blockingReceive(mt);


		if (zeuthen_msg != null) { // Si recibi el mensaje, lo proceso
			ContentElement ce = getContentManager().extractContent(zeuthen_msg); //extraigo el contenido del mensaje
			EsMiZeuthen zeuthen_adversario = (EsMiZeuthen) ce;

			System.out.println("El agente " + getLocalName() + " recibio la propuesta de " + zeuthen_msg.getSender().getLocalName());
			
			//	zeuthen.getValor(); || getPropuesta
			recibido = true;
			System.out.println("Mensaje Recibido: '" + zeuthen + "'");

			//comparamos el valor de zeuthen adversario con el de zeuthen propio
			//si mi zeuthen es mayor, espero por la proxima propuesta, 
			if (zeuthen_adversario.getValor() > zeuthen.getValor()) {
				System.out.println("El agente " + getLocalName() + " espera por la proxima propuesta");
				block();
			} // sino envio la proxima propuesta
			else {
				

			if (zeuthen_adversario.getValor() > zeuthen.getValor()) {
				System.out.println("El agente " + getLocalName() + " recibio la propuesta de " + zeuthen_msg.getSender().getLocalName() + " y le responde que no le gusta");
				ACLMessage msg = new ACLMessage(ACLMessage.REFUSE);
				msg.addReceiver(zeuthen_msg.getSender());
				send(msg);
			} else {
				System.out.println("El agente " + getLocalName() + " recibio la propuesta de " + zeuthen_msg.getSender().getLocalName() + " y le responde que le gusta");
				ACLMessage msg = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
				msg.addReceiver(zeuthen_msg.getSender());
				send(msg);
			}

		}
		else
			block(); 	// Si no lo recibi, se bloquea el comportamiento
	}
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
