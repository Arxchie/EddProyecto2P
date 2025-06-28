/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo.cruds;

import edd_hospital_.modelo.Especialidad;
import edd_hospital_.modelo.Hospitales;
import edd_hospital_.modelo.CreadorDeNodos;
import edd_hospital_.multi_lista.MultiListaDL;
import edd_hospital_.multi_lista.NodoML;
import interfaces.Crudable;
import java.util.Set;

/**
 *
 * @author HP
 */
public class CrudEspecialidad implements Crudable
{

    @Override
    public void insertar(MultiListaDL multilista, NodoML nodoAInsertar, String... ruta)
    {
        verificarInsercion(multilista, nodoAInsertar, ruta);
        multilista.inserta(nodoAInsertar, ruta);
    }

    private void verificarInsercion(MultiListaDL multilista, NodoML nodoAInsertar, String[] ruta)
    {
        if (ruta == null || ruta.length == 0)
        {
            throw new IllegalArgumentException("La ruta no puede ser nula ni vacía.");
        }

        NodoML hospitalPadre = multilista.buscarEnMultilista(ruta);
        if (hospitalPadre == null || hospitalPadre.getObj() == null)
        {
            throw new IllegalStateException("La ruta no fue encontrada o el nodo padre no tiene un objeto asociado.");
        }

        if (nodoAInsertar == null || nodoAInsertar.getObj() == null)
        {
            throw new IllegalStateException("El nodo a insertar y su objeto no pueden ser nulos.");
        }

        Object objetoPadre = hospitalPadre.getObj();
        Object objetoHijo = nodoAInsertar.getObj();

        if (objetoPadre instanceof Hospitales h && objetoHijo instanceof Especialidad e)
        {
            String nombreEspecialidad = e.getNombre().toLowerCase().trim();
            Set<String> especialidadesNoPermitidas = Set.of("neurocirugia", "neurocirugía", "oncologia", "oncología");

            if (h.getNivel() < 3 && especialidadesNoPermitidas.contains(nombreEspecialidad))
            {
                throw new IllegalArgumentException("Un hospital menor a nivel 3 no puede tener esa especialidad.");
            }
        }
    }

    @Override
    public NodoML eliminar(MultiListaDL multilista, String[] ruta)
    {
        return multilista.elimina(ruta);
    }

    @Override
    public NodoML buscarConRutaDeEtiquetas(MultiListaDL multilista, String[] ruta)
    {
        return multilista.buscarEnMultilista(ruta);
    }

    @Override
    public void actualizarNodo(MultiListaDL multilista, Object nuevo, String... ruta)
    {
        NodoML nodoAActualizar = multilista.buscarEnMultilista(ruta);
        if (nodoAActualizar == null)
        {
            throw  new IllegalArgumentException("No se encontro el nodo a actualizar");
        }
        if (nuevo != null)
        {
            throw  new IllegalArgumentException("El nuevo objeto no puede ser null");
        }
        nodoAActualizar.setObj(nuevo);

    }

    public static void main(String[] args)
    {
        CrudEspecialidad c = new CrudEspecialidad();
        MultiListaDL m = new MultiListaDL();
        CreadorDeNodos n = new CreadorDeNodos();
        m.inserta(n.NodoDependencia("Estatal", "IMSS"));
        m.inserta(n.NodoHospitales("San juan", 1, "Hospital san calos"), "D001");
        try
        {
            c.insertar(m, n.NodoEspecialidades(5, 2, "ONCOLOGÍA"), "D001", "H002");

        } catch (RuntimeException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println(m.desp());
    }
}
