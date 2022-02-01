package com.company.AgentsPack;
import com.company.FSMBehaviuors.*;
import com.company.Ontologias.*;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.HashMap;
import java.util.Map;


public class Responder extends AgenteNegociador {

	protected Codec codec = new SLCodec();
	protected Ontology mcp = MCPOntology.getInstance();


	@Override
	protected void setup() {

		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(mcp);

		// Crea una descripcion del agente para el DF
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		
		// Crea una descripcion del servicio
		ServiceDescription sd = new ServiceDescription();
		sd.setType("comidas");
		sd.setName("proponer-comidas");
		dfd.addServices(sd);
		
		try {	// Registra el agente en el DF
			DFService.register(this, dfd);
			System.out.println("Se registro el agente responder");
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
		this.addBehaviour(new EsperarPropuestaInicial());
	}

	@Override
	protected void takeDown() { 
		// Unregister from the yellow pages
		try {
			DFService.deregister(this);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
		System.out.println("apagando agente Responder");
		super.takeDown();
	}

}