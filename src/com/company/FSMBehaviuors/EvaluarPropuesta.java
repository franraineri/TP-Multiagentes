package beh;

import jade.core.behaviours.Behaviour;

public class EvaluarPropuesta extends Behaviour {

	private boolean flag = false; //marca que mi ponderacion es menor que la de la propuesta
	private int event;
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

		//LA PROPUESTA DEBE TOMARSE DESDE EL DATASTORE

		ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE));

		if(msg!=null){

			ContentElement ce;
			
			if(msg.getPerformative() == ACLMessage.PROPOSE){
			
				try{
					ce = myAgent.getContentManager().extractContent(msg);
					PedirComida comidaObj =  ce;
					double otraPonderacion = comidaObj.getComida().ponderacionComida();
					ACLMessage resp = msg.createRe ply();
					
					if ( (double)getDataStore().get("ponderation_last_propose") > otraPonderacion ){
						
						this.flag = false;
						
						resp. setPerformative(ACLMessage.REJECT_PROPOSAL);
						resp.setContent("El agente "+ myAgent.getAID()+ "no acepto la comida. ");
						this.event = 0;

					} else {

						this.flag = true;	//marca que mi ponderacion es menor que la de la propuesta
						resp.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
						resp.setContent("El agente "+ myAgent.getAID()+ " acepto la comida. ");
						this.event = 1;

						}				
					myAgent().send(resp);
					}}
					catch (CodecException | OntolontologyException){
						e.printStackTrace();
				}
			}
		}else{
			
		}



	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
