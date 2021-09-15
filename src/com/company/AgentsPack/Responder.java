package com.company.AgentsPack;

import com.company.BehaivoursPack.Talks.BehaviourResponder;
import jade.core.Agent;

public class Responder extends Agent {

	@Override
	protected void setup() {
		this.addBehaviour(new BehaviourResponder());
	}

	@Override
	protected void takeDown() {
		System.out.println("apagando agente Responder");
		super.takeDown();
	}


}