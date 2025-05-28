package controlador;

import edd_hospital_.modelo.Niveles;
import edd_hospital_.vista.vtnDependencia;
import edd_hospital_.vista.vtnEspecialidad;
import edd_hospital_.vista.vtnHospitales;
import edd_hospital_.vista.vtnPacientes;
import interfaces.VentanaRegistrable;

public class VentanaRegistrableFactory
{

     public static VentanaRegistrable crearVentanaRegistrble(Niveles nivel)
     {
          switch (nivel)
          {
               case DEPENDENCIA ->
               {
                    System.out.println("ventana Dependencias");
                    return new vtnDependencia(null, true);

               }
               case HOSPITAL ->
               {
                    System.out.println("ventana Hospitales");
                    return new vtnHospitales(null, true);
               }
               case ESPECIALIDAD ->
               {
                    System.out.println("ventana especialidad");
                    return new vtnEspecialidad(null, true);

               }
               case PACIENTE ->
               {
                    System.out.println("ventana pacientes");

                    return new vtnPacientes(null, true);
               }
               default ->
                    throw new AssertionError("Ese tipo no esta especificado");
          }

     }

}
