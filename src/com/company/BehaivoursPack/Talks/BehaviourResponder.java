package com.company.BehaivoursPack.Talks;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;

public class BehaviourResponder extends Behaviour {

	private boolean recibido = false;
<<<<<<< HEAD
	private ArrayList<String> Respuestas;
	public float prob = 0.1;	//probabilidad de que salga "no me gusta" | "me gusta"
=======
	private boolean loRecibi = false;
	private ArrayList<String> Respuestas;
>>>>>>> 0cfb3c9495ab74926422fdf37277372c875dde6f

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

<<<<<<< HEAD
			if(Math.random() > prob){
				String Respuesta = Respuestas.get(1);
			}
			System.out.println(Respuesta);

			recibido = true;
=======
			loRecibi = true;

			String Respuesta = Respuestas.get((int) (Math.random() * Respuestas.size()));
>>>>>>> 0cfb3c9495ab74926422fdf37277372c875dde6f

			ACLMessage resp = msg.createReply();
			resp.setPerformative(ACLMessage.INFORM);
			resp.setContent(Respuesta);

			System.out.println(Respuesta + " la comida " + msg.getContent());

<<<<<<< HEAD
			myAgent.send(resp); // Envio la respuesta a la propuesta
		}
		else
			block(); 	// Si no lo recibi, se bloquea el comportamiento
=======
			// Envio la respuesta
			myAgent.send(resp);
		}
		else
			block(); // Si no lo recibi, se bloquea el comportamiento
>>>>>>> 0cfb3c9495ab74926422fdf37277372c875dde6f
	}

	//PARA NOSOTROS Verificar en caso de que no funcione//
	@Override
	public void reset() {
		recibido = false;
		super.reset();
	}

	@Override
	public boolean done() {
<<<<<<< HEAD
		return recibido;
=======
		return loRecibi;
>>>>>>> 0cfb3c9495ab74926422fdf37277372c875dde6f
	}



}