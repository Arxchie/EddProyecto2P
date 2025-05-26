/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo.modelosDeTablas;

import edd_hospital_.modelo.Especialidad;

/**
 *
 * @author HP
 */
public class ModeloTablaEspecialidades extends ModeloTablaGenerico<Especialidad>

{
    public ModeloTablaEspecialidades()
    {
        super(Especialidad.class);
    }

    @Override
    public String[] getNombreCabeceras()
    {
        return new String[]
        {
            "Clave", "Nombre", "No.Camas", "No.Medicos"
        };
    }

    @Override
    protected Object[] extraerFila(Especialidad obj)
    {
        return new Object[]
        {
            obj.getClaveE(),
            obj.getNombre(),
            obj.getNumeroDeCamas(),
            obj.getNumeroDeMedicos()
        };
    }

}
