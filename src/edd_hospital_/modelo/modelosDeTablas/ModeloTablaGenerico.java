/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo.modelosDeTablas;

import interfaces.MostrableEnTabla;
import edd_hospital_.multi_lista.NodoML;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public abstract class ModeloTablaGenerico<T> implements MostrableEnTabla
{

    private final Class<T> tipo;

    public ModeloTablaGenerico(Class<T> tipo)
    {
        this.tipo = tipo;
    }


    protected abstract Object[] extraerFila(T obj);

    @Override
    public Object[][] getDatos(NodoML raiz)
    {
        if (raiz == null)
        {
            return null;
        }

        List<Object[]> filas = new ArrayList<>();
        NodoML aux = raiz;
        while (aux != null)
        {
            if (tipo.isInstance(aux.getObj()))
            {
                T obj = tipo.cast(aux.getObj());
                filas.add(extraerFila(obj));
            }
            aux = aux.getSig();
        }

        return filas.toArray(new Object[0][]);
    }
}
