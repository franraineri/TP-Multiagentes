package com.company.AgentsPack;

import com.company.FSMBehaviuors.FSM;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.SubscriptionInitiator;
import com.company.Ontologias.*;

import java.util.HashMap;
import java.util.Map;

public class Requester extends AgenteNegociador {
    
    private Codec codec = new SLCodec();
    private Ontology MCP = MCPOntology.getInstance();

    @Override
    protected void setup(){
        System.out.println("Requester agent "+getAID().getName()+" is ready");

        DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("comidas");
		sd.setName("proponer-comidas");
		template.addServices(sd);
		    
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(MCP);

        try {
			DFAgentDescription[] result = DFService.search(this, template);
			if (result.length > 0) {
                System.out.println("Found the following agents:" + result[0].getName());
                this.addBehaviour(new FSM(result[0].getName(), true));

            } else {
				addBehaviour( new SubscriptionInitiator( this,
						        DFService.createSubscriptionMessage( this, getDefaultDF(), 
								template, null))
                                {
                                protected void handleInform(ACLMessage inform) {
                                    try {
                                        DFAgentDescription[] result =
                                            DFService.decodeNotification(inform.getContent());
                                        if (result[0].getAllServices().hasNext())
                                            addBehaviour(new FSM(result[0].getName(), true));
                                    }
                                    catch (FIPAException fe) {fe.printStackTrace(); }
                                }
				});}
            }
			catch (FIPAException fe) { fe.printStackTrace(); }
		}


    @Override
    protected void takeDown() {
        //el agente de des subscribe del DF
		try {
			DFService.deregister(this);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
        System.out.println("Apagando agente Requester");
        super.takeDown();
    }
}
