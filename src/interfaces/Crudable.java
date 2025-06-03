/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import edd_hospital_.modelo.Datos;
import edd_hospital_.multi_lista.MultiListaDL;
import edd_hospital_.multi_lista.NodoML;

/**
 *
 * @author HP
 */
public interface Crudable
{

    public abstract void insertar(MultiListaDL multilista,NodoML nodoAInsertar, String...ruta);

    public abstract NodoML eliminar(MultiListaDL multilista,String... ruta);

    public abstract NodoML buscarConRutaDeEtiquetas(MultiListaDL multilista,String... ruta);

    public abstract void actualizarNodo(MultiListaDL multilista, Object nuevo,String ...ruta);

    public static NodoML buscarPorNombreEnNivelActual(NodoML nivelActual, String nombre)
    {
        if (nivelActual == null || nombre == null || nombre.isEmpty())
        {
            return null;
        }

        NodoML aux = nivelActual;
        while (aux != null)
        {
            Object objeto = aux.getObj();
            if (objeto instanceof Datos o)
            {
                if (nombre.equals(o.getNombre()))
                {
                    return aux;
                }
            }
            aux = aux.getSig();
        }
        return null;
    }

}
