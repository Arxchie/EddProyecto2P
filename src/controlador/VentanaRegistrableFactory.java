/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import edd_hospital_.modelo.Niveles;
import edd_hospital_.vista.vtnDependencia;
import edd_hospital_.vista.vtnEspecialidad;
import edd_hospital_.vista.vtnHospitales;
import edd_hospital_.vista.vtnPacientes;
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
                return new vtnDependencia(null,true);

            }
            case HOSPITAL ->
            {
                System.out.println("ventana Hospitales");
                return new vtnHospitales();
            }
            case ESPECIALIDAD ->
            {
                System.out.println("ventana especialidad");
                return new vtnEspecialidad();
 
            }
            case PACIENTE ->
            {
                System.out.println("ventana pacientes");
            
                return new vtnPacientes();
            }
            default ->
                throw new AssertionError("Ese tipo no esta especificado");
        }

    }

}


