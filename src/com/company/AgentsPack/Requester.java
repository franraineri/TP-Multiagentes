package com.company.AgentsPack;

import com.company.BehaivoursPack.FMS.MyFSM;
import jade.core.Agent;
import behaviours.NumberInitiatorBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.SubscriptionInitiator;


public class Requester extends Agent {
    
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
        getContentgetContentManager().registerOregisterOntology(MCP);

        try {
			DFAgentDescription[] result = DFService.search(this, template);
			if (result.length > 0)
                System.out.println("Found the following agents:" + result[0].getName());
                this.addBehaviour(new MyFSM(result[0].getName()));
			else {
				addBehaviour( new SubscriptionInitiator( this, 
						        DFService.createSubscriptionMessage( this, getDefaultDF(), 
								template, null)) 
                            {
                                protected void handleInform(ACLMessage inform) {
                                    try {
                                        DFAgentDescription[] result =         
                                            DFService.decodeNotification(inform.getContent());
                                        if (result[0].getAllServices().hasNext())
                                            addBehaviour(new NumberInitiatorBehaviour(result[0].getName()));
                                        
                                        
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
