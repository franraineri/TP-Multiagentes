package beh;

import jade.core.behaviours.Behaviour;

public class EnviarZeuthen extends Behaviour {

	@Override
	public void action() {
		// TODO Auto-generated method stub
		//crear mensaje y enviar zeuthen
		System.out.println("Enviando Zeuthen de el agente: " + myAgent.getLocalName());

		ACLMessage propuesta = new ACLMessage(ACLMessage.INFORM);
		propuesta.addReceiver((AID) getDataStore().get(FMSProtocolo.AID_OPONENTE));
		propuesta.setOntology(myAgent.getContentManager().getOntology().getName());

		try {
			EsMiZeuthen zeuthen = new EsMiZeuthen();
			zeuthen.setValor(((float)getDataStore().get("ponderation_last_propose") - (float)getDataStore().get("menor_ponderacion") ) / (float)getDataStore().get("diferencia_ponderaciones"));
			myAgent.getContentManager().fillContent(propuesta, zeuthen); 	//joaquin
			myAgent.send(propuesta);
			
			////myAgent.getContentManager().fillContent(myAgent.getCurrentMessage(), zeuthen); //verificar esta linea
		}
		catch ( Codec.CodecException | OntologyException e ) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
