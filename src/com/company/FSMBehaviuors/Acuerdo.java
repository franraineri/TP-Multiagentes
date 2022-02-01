package com.company.FSMBehaviuors;

import jade.core.behaviours.Behaviour;

public class Acuerdo extends Behaviour {

	@Override
	public void action() {
		System.out.println("Los agente llegamos a un acuerdo, finaliza la negociación");
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
