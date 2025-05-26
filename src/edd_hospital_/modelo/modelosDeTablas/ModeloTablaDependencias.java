/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo.modelosDeTablas;

import edd_hospital_.modelo.Dependencia;

/**
 *
 * @author HP
 */
public class ModeloTablaDependencias extends ModeloTablaGenerico<Dependencia>
{

    public ModeloTablaDependencias()
    {
        super(Dependencia.class);
    }

    @Override
    public String[] getNombreCabeceras()
    {
        return new String[]
        {
            "Clave", "Nombre", "Tipo"
        };
    }

    @Override
    protected Object[] extraerFila(Dependencia dep)
    {
        return new Object[]
        {
            dep.getClaveD(),
            dep.getNombre(),
            dep.getTipo()
        };
    }
}
