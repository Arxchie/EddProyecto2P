/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import edd_hospital_.modelo.Niveles;
import edd_hospital_.modelo.Nodos;
import edd_hospital_.multi_lista.NodoML;
import interfaces.VentanaRegistrable;

/**
 *
 * @author HP
 */
public class VentanaRegistrableFactory
{

    public static VentanaRegistrable crearVentanaRegistrble(Niveles nivel)
    {
        switch (nivel)
        {
            case DEPENDENCIA ->
            {
                System.out.println("ventana Dependencias");
                return new prueba();

            }
            case HOSPITAL ->
            {
                System.out.println("ventana Hospitales");
                return new pruebaHospital();
            }
            case ESPECIALIDAD ->
            {
                System.out.println("ventana especialidad");
                return null;
            }
            case PACIENTE ->
            {
                System.out.println("ventana pacientes");
                return null;
            }
            default ->
                throw new AssertionError("Ese tipo no esta especificado");
        }

    }

}

class prueba implements VentanaRegistrable
{

    @Override
    public NodoML getNodoRegistrado()
    {
        Nodos n = new Nodos();
        NodoML nodo1 = n.NodoDependencia("Estatal", "IMMS");
        return nodo1;
    }
}

class pruebaHospital implements VentanaRegistrable
{

    @Override
    public NodoML getNodoRegistrado()
    {
        Nodos n = new Nodos();

        NodoML nodo4 = n.NodoHospitales("matamoros n23", 1, "san juan");
        return nodo4;
    }
}
