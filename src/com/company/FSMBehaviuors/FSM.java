package com.company.FSMBehaviuors;

import jade.core.AID;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.FSMBehaviour;
import jade.lang.acl.ACLMessage;

public class FSM extends FSMBehaviour{

	public static final String AID_OPONENTE = "aid-oponente";
	public static final String REQUEST_INITIAL = "request-initial";
	private static final String ENVIAR_PROPUESTA = "enviar-propuesta";
	private static final String EVALUAR_PROPUESTA = "evaluar-propuesta";
	private static final String ESPERAR_RESPUESTA = null;
	private static final String ENVIAR_ZEUTHEN = null;
	private static final String RECIBIR_ZEUTHEN = null;
	private static final String ESPERAR_PROPUESTA = null;
	private static final String ACUERDO = null;
	private static final String CONFLICTO = null;


	// CONTRUCTOR DEL INICIATOR
	public FSM(AID aid, boolean iniciador) {
		DataStore ds = new DataStore();
		ds.put(AID_OPONENTE, aid);	//creo el data store y agrego el id de mi oponente
		this.crearFSM(ds, iniciador);
	}
	
	// CONSTRUCTOR DEL RESPONDER
	public FSM(ACLMessage proposeInicial) {
		DataStore ds = new DataStore();
		ds.put(REQUEST_INITIAL, proposeInicial);
		this.crearFSM(ds, false);
	}

	private void crearFSM(DataStore ds, boolean iniciador) {

		// Instanciamos los comportamientos de la maquina de estados
		EnviarPropuesta enviar_propuesta = new EnviarPropuesta();
		enviar_propuesta.setDataStore(ds);

		EsperarRespuesta esperar_respuesta = new EsperarRespuesta();
		esperar_respuesta.setDataStore(ds);

		EnviarZeuthen enviar_zeuthen = new EnviarZeuthen();
		enviar_zeuthen.setDataStore(ds);

		RecibirZeuthen recibir_zeuthen = new RecibirZeuthen();
		recibir_zeuthen.setDataStore(ds);

		EsperarPropuesta esperar_propuesta = new EsperarPropuesta();
		esperar_propuesta.setDataStore(ds);

		EvaluarPropuesta evaluar_propuesta = new EvaluarPropuesta();
		evaluar_propuesta.setDataStore(ds);

		Acuerdo acuerdo = new Acuerdo();
		acuerdo.setDataStore(ds);

		Conflicto conflicto = new Conflicto();
		conflicto.setDataStore(ds);

		// Definimos los estados de la FSM
		if (iniciador) { // Es el agente iniciador
			this.registerFirstState(enviar_propuesta, ENVIAR_PROPUESTA);
			this.registerState(evaluar_propuesta, EVALUAR_PROPUESTA);
		}
		else { // Es el agente Responder
			this.registerFirstState(evaluar_propuesta, EVALUAR_PROPUESTA);
			this.registerState(enviar_propuesta, ENVIAR_PROPUESTA);
		}
		
		this.registerState(esperar_respuesta, ESPERAR_RESPUESTA);
		//this.registerState(esperar_propuesta, ESPERAR_PROPUESTA);
	
		this.registerState(enviar_zeuthen, ENVIAR_ZEUTHEN);		
		this.registerState(recibir_zeuthen, RECIBIR_ZEUTHEN);
		
		
		this.registerLastState(acuerdo, ACUERDO);
		this.registerLastState(conflicto, CONFLICTO);
		
		// Definir transiciones

		this.registerTransition(ENVIAR_PROPUESTA, ESPERAR_RESPUESTA, 0);
		this.registerTransition(ENVIAR_PROPUESTA, CONFLICTO, 1); // No tengo mas propuestas y debo conceder

		String[] resets_1= {ESPERAR_RESPUESTA};
		this.registerTransition(ESPERAR_RESPUESTA, ENVIAR_ZEUTHEN, 0, resets_1); // Reject
		this.registerTransition(ESPERAR_RESPUESTA, ACUERDO, 1, resets_1); // Accept

		String[] resets_2= {RECIBIR_ZEUTHEN};
		this.registerDefaultTransition(ENVIAR_ZEUTHEN, RECIBIR_ZEUTHEN);
		this.registerTransition(RECIBIR_ZEUTHEN, ESPERAR_PROPUESTA, 0, resets_2); 	// Mi Z es Mayor
		this.registerTransition(RECIBIR_ZEUTHEN, ENVIAR_PROPUESTA, 1, resets_2); 	// Mi Z es Menor

		String[] resets_3= {ESPERAR_PROPUESTA};
		this.registerTransition(ESPERAR_PROPUESTA, EVALUAR_PROPUESTA, 0, resets_3); // Recibi propuesta
		this.registerTransition(ESPERAR_PROPUESTA, CONFLICTO, 1, resets_3); // Recibi Cancel
		
		this.registerTransition(EVALUAR_PROPUESTA, ENVIAR_ZEUTHEN, 0); // Reject
		this.registerTransition(EVALUAR_PROPUESTA, ACUERDO, 1); // Accept
		
			
	}

}
