package beh;

import jade.core.behaviours.Behaviour;

public class EvaluarPropuesta extends Behaviour {

	@Override
	public void action() {
		// ACLMessage resp = msg.createReply();
		// 	if(Math.random() > prob){
		// 		Respuesta = Respuestas.get(0);
		// 		resp.setPerformative(ACLMessage.REJECT_PROPOSAL);	
		// 		this.reset();
		// 	} else{
		// 		Respuesta = Respuestas.get(1);
		// 		resp.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
		// 	}

		// 	resp.setContent(Respuesta);

		// 	System.out.println(Respuesta + " esa");

		// 	myAgent.send(resp); // Envio la respuesta a la propuesta

	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
