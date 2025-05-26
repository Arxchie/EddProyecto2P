/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo.modelosDeTablas;

import edd_hospital_.modelo.Hospitales;

/**
 *
 * @author HP
 */
public class ModeloTablaHospitales extends ModeloTablaGenerico<Hospitales>

{

    public ModeloTablaHospitales()
    {
        super(Hospitales.class);
    }

    @Override
    public String[] getNombreCabeceras()
    {
        return new String[]
        {
            "Clave", "Nombre", "Nivel", "Direccion"
        };
    }

    @Override
    protected Object[] extraerFila(Hospitales obj)
    {
        return new Object[]
        {
            obj.getClaveH(),
            obj.getNombre(),
            obj.getNivel(),
            obj.getDireccion()
        };
    }

}
