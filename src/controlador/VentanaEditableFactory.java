package controlador;

import edd_hospital_.modelo.CreadorDeNodos;
import edd_hospital_.modelo.Niveles;
import edd_hospital_.modelo.Paciente;
import edd_hospital_.vista.vtnDependencia;
import edd_hospital_.vista.vtnEspecialidad;
import edd_hospital_.vista.vtnHospitales;
import edd_hospital_.vista.vtnPacientes;
import interfaces.VentanaEditable;

public class VentanaEditableFactory
{

    public static VentanaEditable crearVentanaEditable(Niveles nivel)
    {
        switch (nivel)
        {
            case DEPENDENCIA ->
            {
                System.out.println("ventana Dependencias");
                vtnDependencia v = new vtnDependencia(null, true);
                v.configurarParaEditable();
                return v;
            }
            case HOSPITAL ->
            {
                System.out.println("ventana Hospitales");
                vtnHospitales v = new vtnHospitales(null, true);
                v.configurarParaEditable();
                return v;

            }
            case ESPECIALIDAD ->
            {
                System.out.println("ventana especialidad");
                vtnEspecialidad e = new vtnEspecialidad(null, true);
                e.configurarParaEditable();
                return e;

            }
            case PACIENTE ->
            {
                System.out.println("ventana pacientes");

                vtnPacientes p = new vtnPacientes(null, true);
                p.configurarParaEditable();
                return p;

            }
            default ->
                throw new AssertionError("Ese tipo no esta especificado");
        }

    }

    public static void main(String[] args)
    {
        VentanaEditable v = VentanaEditableFactory.crearVentanaEditable(Niveles.PACIENTE);
        v.cargarDatos(new CreadorDeNodos().NodoPaciente("Activo", "22/02/2020", 'M', "san juan"));
        v.setVisible(true);
        System.out.println(((Paciente) v.getObjetoEditado()).getEstatus());
        System.out.println(((Paciente) v.getObjetoEditado()).getSexo());
        System.out.println(((Paciente) v.getObjetoEditado()).getVigencia());
    }
}
