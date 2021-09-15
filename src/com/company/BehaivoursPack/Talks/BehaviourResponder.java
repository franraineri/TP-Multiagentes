package com.company.BehaivoursPack.Talks;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;

public class BehaviourResponder extends Behaviour {

	private boolean recibido = false;
	private boolean loRecibi = false;
	private ArrayList<String> Respuestas;

	public BehaviourResponder (){
		ArrayList<String> Respuestas = new ArrayList<String>(){{
			add("No me gusta"); add("Me gusta");
		}};
	}

	@Override
	public void action() {
		// Recibo un mensaje
		ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));

		if (msg != null) { // Si recibi el mensaje, lo proceso
			System.out.println("Mensaje Recibido: " + msg.getContent());

			loRecibi = true;

			String Respuesta = Respuestas.get((int) (Math.random() * Respuestas.size()));

			ACLMessage resp = msg.createReply();
			resp.setPerformative(ACLMessage.INFORM);
			resp.setContent(Respuesta);

			System.out.println(Respuesta + " la comida " + msg.getContent());

			// Envio la respuesta
			myAgent.send(resp);
		}
		else
			block(); // Si no lo recibi, se bloquea el comportamiento
	}

	//PARA NOSOTROS Verificar en caso de que no funcione//
	@Override
	public void reset() {
		recibido = false;
		super.reset();
	}

	@Override
	public boolean done() {
		return loRecibi;
	}



}