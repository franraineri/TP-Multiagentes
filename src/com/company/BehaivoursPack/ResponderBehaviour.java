package com.company.BehaivoursPack;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;

public class ResponderBehaviour extends Behaviour {

	private boolean recibido = false;
	public float prob = (float) 0.6;	//probabilidad de que salga "no me gusta" | "me gusta"
	private ArrayList<String> Respuestas = new ArrayList<String>(){
		{ add("No me gusta"); add("Me gusta"); }};


	@Override
	public void action() {
		// Recibo un mensaje
		ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));

		if (msg != null) { // Si recibi el mensaje, lo proceso
			recibido = true;
			System.out.println("Mensaje Recibido: '" + msg.getContent() + "'");
			String Respuesta;
			
			ACLMessage resp = msg.createReply();
			if(Math.random() > prob){
				Respuesta = Respuestas.get(0);
				resp.setPerformative(ACLMessage.REJECT_PROPOSAL);	
				this.reset();
			} else{
				Respuesta = Respuestas.get(1);
				resp.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
			}

			resp.setContent(Respuesta);

			System.out.println(Respuesta + " esa");

			myAgent.send(resp); // Envio la respuesta a la propuesta
		}
		else
			block(); 	// Si no lo recibi, se bloquea el comportamiento
	}

	@Override
	public void reset() {
		recibido = false;
		super.reset();
	}

	@Override
	public boolean done() {
		return false;	//no es nesesario retornar el reset pq no estamos en una FSM
	}



}