/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo.modelosDeTablas;

import interfaces.MostrableEnTabla;
import edd_hospital_.modelo.Niveles;

/**
 *
 * @author HP
 */
public class ModeloTablaFactory
{
    public static MostrableEnTabla crearModeloDeTabla(Niveles nivel)
    {
        switch (nivel)
        {
            case DEPENDENCIA ->
            {
                return new ModeloTablaDependencias();
            }
            case HOSPITAL ->
            {
                return new ModeloTablaHospitales();
            }
            case ESPECIALIDAD ->
            {
                return new ModeloTablaEspecialidades();
            }
            case PACIENTE ->
            {
                return new ModeloTablaPaciente();
            }
            default -> throw new RuntimeException("Nivel desconocido");
                
        }
    }

}
