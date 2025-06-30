/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo;

import edd_hospital_.multi_lista.NodoML;

/**
 *
 * @author ivanh
 */
public class BicolaPacientes
{

    private static Bicola<NodoML> pacientesEnEspera = new Bicola(new Paciente[100]);

    public BicolaPacientes()
    {
        this.pacientesEnEspera = new Bicola(new Paciente[100]);
    }

    public static void insertaPacienteOncologia(NodoML nodoPaciente)
    {

        pacientesEnEspera.setA1(pacientesEnEspera.inserta(nodoPaciente, pacientesEnEspera.getA1(), pacientesEnEspera.getA2(), 1));
    }

    public static void insertaPacienteNeuro(NodoML nodoPaciente)
    {
        pacientesEnEspera.setA2(pacientesEnEspera.inserta(nodoPaciente, pacientesEnEspera.getA2(), pacientesEnEspera.getA1(), -1));
    }

    public static NodoML eliminaNodoPacienteOncologia()
    {
        Object[] datos = pacientesEnEspera.elimina(pacientesEnEspera.getA1(), pacientesEnEspera.getA2(), 1);
        pacientesEnEspera.setA1((int) datos[1]);
        return (NodoML) datos[0];
    }

    public static NodoML eliminaNodoPacienteNeuro()
    {
        Object[] datos = pacientesEnEspera.elimina(pacientesEnEspera.getA2(), pacientesEnEspera.getA1(), 1);
        pacientesEnEspera.setA2((int) datos[1]);
        return (NodoML) datos[0];
    }

    public static void despIzq()
    {
        pacientesEnEspera.despIzq();
    }

    public static void despDer()
    {
        pacientesEnEspera.despDer();
    }

    public static void main(String[] args)
    {
//        BicolaPacientes bp = new BicolaPacientes();
//        Paciente p1 = new Paciente("Vigente", "Neuro", 'M', "Jose");
//        Paciente p2 = new Paciente("Vigente", "Onco", 'M', "Saul");
//        Paciente p3 = new Paciente("Vigente", "Onco", 'M', "Ivan");
//        Paciente p4 = new Paciente("Vigente", "Neuro", 'F', "Teresa");
//
//        bp.insertaPacienteNeuro(p1);
//        bp.insertaPacienteNeuro(p4);
//        bp.insertaPacienteOncologia(p2);
//        bp.insertaPacienteOncologia(p3);
//        bp.eliminaPacienteNeuro(p1);
//
//        bp.despDer();

    }

}
