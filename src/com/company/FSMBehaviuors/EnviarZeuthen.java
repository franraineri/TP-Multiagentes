package com.company.FSMBehaviuors;

import com.company.Ontologias.*;
import jade.content.lang.Codec;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class EnviarZeuthen extends Behaviour {

	@Override
	public void action() {
		// TODO Auto-generated method stub
		//crear mensaje y enviar zeuthen
		System.out.println("Enviando Zeuthen de el agente: " + myAgent.getLocalName());

		ACLMessage propuesta = new ACLMessage(ACLMessage.INFORM);
		propuesta.addReceiver((AID) getDataStore().get(FSM.AID_OPONENTE));
		propuesta.setOntology(MCPOntology.getInstance().getName());

		try {
			EsMiZeuthen zeuthen = new EsMiZeuthen();
			zeuthen.setZeuthen(((float)getDataStore().get("ponderation_last_propose") - (float)getDataStore().get("") ) / (float)getDataStore().get("diferencia_ponderaciones"));
			getDataStore().put("zeuthen_actual", zeuthen.getZeuthen());
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
