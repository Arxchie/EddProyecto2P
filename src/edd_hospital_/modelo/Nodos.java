/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo;

import edd_hospital_.multi_lista.ListaDLML;
import edd_hospital_.multi_lista.NodoML;

/**
 *
 * @author saulo
 */
public class Nodos
{

    public NodoML NodoDependencia(String tipo, String nombre)
    {
        Dependencia objDependencia = new Dependencia(tipo, nombre);
        NodoML nodoDependencia = new NodoML(objDependencia, objDependencia.getClaveD());
        return nodoDependencia;
    }

    public NodoML NodoHospitales(String direccion, int nivel, String nombre)
    {
        Hospitales objHospitales = new Hospitales(direccion, nivel, nombre);
        NodoML nodoHospitales = new NodoML(objHospitales, objHospitales.getClaveH());
        if (nivel == 3)
        {
            ListaDLML auxiliar = new ListaDLML(nodoHospitales.getAbj());
            NodoML neuro = NodoEspecialidades(10, 3, "Neurocirugia");
            NodoML onco = NodoEspecialidades(10, 3, "Oncologia");
            auxiliar.inserta(neuro);
            auxiliar.inserta(onco);
            nodoHospitales.setAbj(auxiliar.getR());
        }
        return nodoHospitales;
    }

    public NodoML NodoEspecialidades(int camas, int medicos, String nombre)
    {
        Especialidad objEspecialidad = new Especialidad(camas, medicos, nombre);
        NodoML nodoEspecialidad = new NodoML(objEspecialidad, objEspecialidad.getClaveE());
        return nodoEspecialidad;
    }

    public NodoML NodoPaciente(String estatus, String vigencia, char sexo, String nombre)
    {
        Paciente objPaciente = new Paciente(estatus, vigencia, sexo, nombre);
        NodoML nodoPaciente = new NodoML(objPaciente, objPaciente.getClaveP());
        return nodoPaciente;
    }

    public static void main(String[] args)
    {
        Nodos n = new Nodos();
        NodoML nuevoHospital = n.NodoHospitales("San lucas n32", 3, "4 cadenas");
        System.out.println(nuevoHospital.getAbj().getEt());
        System.out.println(nuevoHospital.getAbj().getSig().getEt());

    }
}
