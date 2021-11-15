package com.company.AgentsPack;

import com.company.BehaivoursPack.ResponderBehaviour;
import jade.core.Agent;

public class Responder extends Agent {

	@Override
	protected void setup() {
		this.addBehaviour(new ResponderBehaviour());
	}

	@Override
	protected void takeDown() {
		System.out.println("apagando agente Responder");
		super.takeDown();
	}


}