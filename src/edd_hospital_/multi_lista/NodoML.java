/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.multi_lista;

/**
 *
 * @author Joabp
 */
public class NodoML<T>
{

    private T obj;
    private String etiqueta;
    private NodoML siguiente, anterior, arriba, abajo;

    public NodoML(T obj, String etiqueta)
    {
        this.obj = obj;
        this.etiqueta = etiqueta;
    }

    public NodoML()
    {
    }

    public String getEt()
    {
        return etiqueta;
    }

    public void setEt(String etiqueta)
    {
        this.etiqueta = etiqueta;
    }

    public T getObj()
    {
        return obj;
    }

    public void setObj(T obj)
    {
        this.obj = obj;
    }

    public NodoML getSig()
    {
        return siguiente;
    }

    public void setSig(NodoML siguiente)
    {
        this.siguiente = siguiente;
    }

    public NodoML getAnt()
    {
        return anterior;
    }

    public void setAnt(NodoML anterior)
    {
        this.anterior = anterior;
    }

    public NodoML getArb()
    {
        return arriba;
    }

    public void setArb(NodoML arriba)
    {
        this.arriba = arriba;
    }

    public NodoML getAbj()
    {
        return abajo;
    }

    public void setAbj(NodoML abajo)
    {
        this.abajo = abajo;
    }

}
