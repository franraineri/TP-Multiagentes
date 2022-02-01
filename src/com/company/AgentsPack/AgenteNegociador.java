package com.company.AgentsPack;

import  com.company.Ontologias.*;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AgenteNegociador extends Agent{

    protected Map<Comida, Float> utilidades = new HashMap<Comida, Float>();
    protected ArrayList<Comida> comidas_pedidas = new ArrayList<Comida>();

    public double getUtilidad(String name){
        return utilidades.get(name);
    }

    public void llenar_utilidades(){
        String[] comidas = {"panchos", "hamburguesas", "papitas"};
        String[] tipos = {"take away", "bar" , "picada"};
        String[] ingredientes = {"salchicha", "carne" , "papa"};

        for (int i = 0 ; i < comidas.length; i++ ){
            Comida c = new Comida(comidas[i],tipos[i],ingredientes[i] );
            utilidades.put( c , (float) (Math.random() *10));
            comidas_pedidas.add(c);
        }
    }

    public ArrayList<Comida> getListaComidas(){
      return comidas_pedidas;
    }

    public Comida getProximaComida(){
        return comidas_pedidas.remove(0);
    }
}
