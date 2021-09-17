package com.company.BehaivoursPack.FMS;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.FSMBehaviour;

import java.util.ArrayList;

public class MyFSM extends FSMBehaviour {

    public MyFSM() {
        //instancio los estados de mi Transicion de Estados
        Proponer eInic = new Proponer();  //comportamientos comunes
        EsperarRespuesta eInt = new EsperarRespuesta();
        EstadoFinal eFin = new EstadoFinal();
        String Inicio = "Estado Inicial";
        String Intermedio = "Estado de espera de respuesta";
        String Final = "Estado Final";


        //Iniciamos Lista de Comidas// eventualmente podria llegar por parametro
        ArrayList<String> list = new ArrayList<>();
        list.add("fideos con tuco");
        list.add("guiso de lentejas");
        list.add("milanesa de soja");
        list.add("pizza napolitana");

        DataStore ds = new DataStore();
        eFin.setDataStore(ds);
        eInic.setDataStore(ds);
        eInt.setDataStore(ds);//ahora los estados pueden utilizar esos datos compartidos con el metodo put y get
        ds.put("comidas", list);


        //registro de los estados del FMS
        this.registerFirstState(eInic, Inicio);
        this.registerState(eInt, Intermedio);
        this.registerLastState(eFin, Final);

        //registro de las trancisiones del FMS
        this.registerDefaultTransition(Inicio, Intermedio);
        this.registerTransition(Intermedio, Inicio,0); //Si no acepta responder//
        this.registerTransition(Intermedio,Final,1);    //Si acepta responder//
    }
}
