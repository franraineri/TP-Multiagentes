package com.company.Ontologias;

import jade.content.Concept;
//class Comida with atributes NOMBRE_COMIDA , TIPO_COMIDA , and INGREDIENTES

public class Comida implements Concept{
    private String nombre_comida;
    private String tipo_comida;
    private String ingredientes;
    public Comida(String nombre_comida, String tipo_comida, String ingredientes){
        this.nombre_comida = nombre_comida;
        this.tipo_comida = tipo_comida;
        this.ingredientes = ingredientes;
    }

    public String getNombre_comida() {
        return nombre_comida;
    }

    //public ponderacionComida returns a double value that represents the weight of the food
    public double ponderacionComida(){
        double ponderacion = 0;
        if(this.tipo_comida.equals("Desayuno")){
            ponderacion = 0.5;
        }
        else if(this.tipo_comida.equals("Almuerzo")){
            ponderacion = 0.7;
        }
        else if(this.tipo_comida.equals("Cena")){
            ponderacion = 0.9;
        }
        return ponderacion;
    }

    public void setNombre_comida(String nombre_comida) {
        this.nombre_comida = nombre_comida;
    }

    public String getTipo_comida() {
        return tipo_comida;
    }

    public void setTipo_comida(String tipo_comida) {
        this.tipo_comida = tipo_comida;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return "Comida{" + "nombre_comida=" + nombre_comida + ", tipo_comida=" + tipo_comida + ", ingredientes=" + ingredientes + '}';
    }

}