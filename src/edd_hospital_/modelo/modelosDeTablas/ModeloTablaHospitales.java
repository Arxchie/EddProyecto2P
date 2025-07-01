/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo.modelosDeTablas;

import edd_hospital_.modelo.Hospitales;
import edd_hospital_.modelo.RemodelacionHospitales;
import edd_hospital_.multi_lista.NodoML;
import interfaces.MostrableEnTabla;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class ModeloTablaHospitales implements MostrableEnTabla

{

    public ModeloTablaHospitales()
    {
    }

    @Override
    public String[] getNombreCabeceras()
    {
        return new String[]
        {
            "Clave", "Nombre", "Nivel", "Direccion"
        };
    }

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

    @Override
    public Object[][] getDatos(NodoML raiz)
    {
        if (raiz == null)
        {
            return null;
        }

        List<Object[]> filas = new ArrayList<>();
        NodoML aux = raiz;
        NodoML nodoPadre ;
        while (aux != null)
        {
            nodoPadre = aux.getArb();
            if (aux.getObj() instanceof Hospitales h && nodoPadre != null)
            {
                if (!RemodelacionHospitales.esTemporal(nodoPadre.getEt(), aux.getEt()))
                {
                    filas.add(extraerFila(h));
                }
                
            }
            aux = aux.getSig();
        }
        return filas.toArray(new Object[0][]);
    }

}
