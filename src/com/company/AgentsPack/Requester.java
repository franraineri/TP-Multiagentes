package com.company.AgentsPack;

import com.company.BehaivoursPack.FMS.MyFSM;
import jade.core.Agent;

public class Requester extends Agent {

    @Override
    protected void setup(){
        this.addBehaviour(new MyFSM());
    }

    @Override
    protected void takeDown() {
        System.out.println("Apagando agente Requester");
        super.takeDown();
    }
}
