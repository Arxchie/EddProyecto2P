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

    private static Bicola<NodoML> pacientesEnEspera = new Bicola(new NodoML[100]);

    public BicolaPacientes()
    {
        this.pacientesEnEspera = new Bicola(new NodoML[100]);
    }

    public static void insertaPacienteOncologia(NodoML nodoPaciente)
    {

        pacientesEnEspera.setA1(pacientesEnEspera.inserta(nodoPaciente, pacientesEnEspera.getA1(), pacientesEnEspera.getA2(), 1));
        System.out.println("Insertado en oncologia");
    }

    public static void insertaPacienteNeuro(NodoML nodoPaciente)
    {
        pacientesEnEspera.setA2(pacientesEnEspera.inserta(nodoPaciente, pacientesEnEspera.getA2(), pacientesEnEspera.getA1(), -1));
        System.out.println("insertado en neuro");
    }

    public static NodoML eliminaNodoPacienteOncologia()
    {
        Object[] datos = pacientesEnEspera.elimina(pacientesEnEspera.getA1(), - 1, 1);
        pacientesEnEspera.setA1((int) datos[1]);
        return (NodoML) datos[0];
    }

    public static NodoML eliminaNodoPacienteNeuro()
    {
        Object[] datos = pacientesEnEspera.elimina(pacientesEnEspera.getA2(), pacientesEnEspera.getArr().length, -1);
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
//        NodoML n1 = new NodoML("1", "1");
//        NodoML n2 = new NodoML("1", "1");
//        NodoML n3 = new NodoML("2", "2");
//        NodoML n4 = new NodoML("2", "2");
//
//        BicolaPacientes.insertaPacienteNeuro(n1);
//        BicolaPacientes.insertaPacienteNeuro(n2);
//        BicolaPacientes.insertaPacienteOncologia(n3);
//        BicolaPacientes.insertaPacienteOncologia(n4);
//
//        System.out.println("pacientes neuro");
//        BicolaPacientes.despDer();
//        System.out.println("pacientes onco");
//        BicolaPacientes.despIzq();
//
//        System.out.println(BicolaPacientes.eliminaNodoPacienteNeuro().getEt());
//        System.out.println(BicolaPacientes.eliminaNodoPacienteOncologia().getEt());

    }

}
