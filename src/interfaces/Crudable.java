/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import edd_hospital_.modelo.Navegador;
import edd_hospital_.multi_lista.MultiListaDL;
import edd_hospital_.multi_lista.NodoML;

/**
 *
 * @author HP
 */
public interface Crudable
{

    public abstract void insertar(MultiListaDL multilista, NodoML nodoAInsertar, String... ruta);

    public abstract NodoML eliminar(MultiListaDL multilista, String... ruta);

    public abstract NodoML buscarConRutaDeEtiquetas(MultiListaDL multilista, String... ruta);

    public abstract void actualizarNodo(MultiListaDL multilista, Object nuevo, String... ruta);

    public static boolean existeEseNombreEnRuta(MultiListaDL multilista, NodoML nodoAInsertar, String... ruta)
    {
        if (multilista == null || nodoAInsertar == null || ruta == null)
        {
            throw new IllegalArgumentException("No pueden ser nulos");

        }
        String rutaCompleta[] = Navegador.crearRutaCompleta(ruta, nodoAInsertar.getEt());
        NodoML nodoExistente = multilista.buscarEnMultilista(rutaCompleta);
        return nodoExistente != null;
    }

}
