/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo;

import edd_hospital_.multi_lista.ListaDLML;
import edd_hospital_.multi_lista.MultiListaDL;
import edd_hospital_.multi_lista.NodoML;
import java.io.Serializable;

/**
 *
 * @author saulo
 */
public class RemodelacionHospitales
{

    public static ListaDLML listaRemodelacion;

    /**
     * elimina las especialidades y pacientes del hospital seleccionado en base
     * a su dependencia crea un hospital temporal en el mismo nivel e inserta
     * las especialidades y pacientes previamente eliminados guarda en la
     * listaRemodelacion un nodo que contiente en su etiqueta el nombre del
     * hospital remodelado y el objeto guardo un arreglo de 3 posiciones tipo
     * String [0] = nombre del hospital remodelado [1] = nombre del hospital
     * auxiliar [2] = nombre de la depencia padre
     *
     * @param dependencia nombre de la dependencia padre
     * @param hospital nombre del hospital a remodelar
     * @param hospitalTemporal nombre del hospital destino
     * @param multilista
     */
    public static void remodelacion(String dependencia, String hospital, String hospitalTemporal, MultiListaDL multilista)
    {
        if (listaRemodelacion == null)
        {
            listaRemodelacion = new ListaDLML();
        }
        if (dependencia == null || hospital == null || hospitalTemporal == null || multilista == null)
        {
            throw new IllegalArgumentException("Los datos son nulos");
        }
        if (esTemporal(dependencia, hospital))
        {
            throw new IllegalMonitorStateException("No puedes mandar remodelacion un hospital temporal");
        }

        if (estaEnRemodelacion(dependencia, hospital))
        {
            throw new IllegalMonitorStateException("El hospital ya esta en remodelación");
        }

        NodoML auxL;
        NodoML auxL2;
        NodoML auxHospitalRemodelar;

        String s[] = new String[3];
        s[0] = hospital;
        s[1] = hospitalTemporal;
        s[2] = dependencia;
        NodoML nodoRemodelacion = new NodoML<>(s, hospital);
        System.out.println(multilista.desp());
        listaRemodelacion.inserta(nodoRemodelacion);
        auxHospitalRemodelar = multilista.buscarEnMultilista(dependencia, hospital);
        if (auxHospitalRemodelar == null)
        {
            throw new IllegalArgumentException("No se encontro el hospital a remodelar");
        }
        auxL = auxHospitalRemodelar.getAbj();

        if (auxHospitalRemodelar.getObj() instanceof Hospitales h)
        {
            multilista.inserta(new NodoML(new Hospitales(h.getDireccion(), h.getNivel(), hospitalTemporal), hospitalTemporal), dependencia);
            while (auxL != null)
            {
                auxL2 = multilista.elimina(dependencia, hospital, auxL.getEt());
                multilista.inserta(auxL2, dependencia, hospitalTemporal);
                auxL = multilista.buscarEnMultilista(dependencia, hospital).getAbj();

            }
        }
        System.out.println("Hospitales remodelacion: " + listaRemodelacion.desp());
        System.out.println(multilista.desp());
    }

    /**
     * elimina las especialidades y pacientes del hospital temporal inserta las
     * especialidades y pacientes previamente eliminadas en el hospital
     * remodelado elimina el nodo de la lista listaRemodelacion elimina el
     * hospital temporal de la multilista
     *
     * @param dependencia nombre de la dependencia padre
     * @param hospital nombre del hospital remodelado
     * @param multilista
     */
    public static void remodelacionTErminada(String dependencia, String hospital, MultiListaDL multilista)
    {
        if (dependencia == null || hospital == null || multilista == null)
        {
            throw new IllegalArgumentException("Los datos son nulos");
        }
        if (esTemporal(dependencia, hospital))
        {
            throw new IllegalStateException("Un hospital temporal no puede estar en remodelacion");
        }
        if (!estaEnRemodelacion(dependencia, hospital))
        {
            throw new IllegalStateException("El hospital no esta en remodelación");
        }

        NodoML auxL;
        NodoML auxL2;
        NodoML hospitalTemporal;
        NodoML datoEliminado = eliminaHospitalTemporalLista(dependencia, hospital);
        if (datoEliminado == null)
        {
            throw new IllegalStateException("Hospital temporal no encontrado");
        }
        String[] valores = (String[]) datoEliminado.getObj();
        System.out.println(hospital);
        System.out.println("dependencia: " + dependencia + " [1] = " + valores[1]);
        hospitalTemporal = multilista.buscarEnMultilista(dependencia, valores[1]);
        if (hospitalTemporal == null)
        {
            throw new IllegalStateException("No se encontro el hospital temporal");
        }
        auxL = hospitalTemporal.getAbj();
        while (auxL != null)
        {
            auxL2 = multilista.elimina(dependencia, valores[1], auxL.getEt());
            multilista.inserta(auxL2, dependencia, hospital);
            auxL = multilista.buscarEnMultilista(dependencia, valores[1]).getAbj();
        }
        System.out.println("Hospitales Remoelados: " + listaRemodelacion.desp());
        multilista.elimina(dependencia, valores[1]);

    }

    public static NodoML eliminaHospitalTemporalLista(String dependenciaPadre, String hospitalOrigen)
    {
        NodoML aux = listaRemodelacion.getR();
        NodoML eliminado = null;

        while (aux != null)
        {
            String[] obj = (String[]) aux.getObj();
            if (obj != null)
            {
                if (obj[2].equals(dependenciaPadre) && obj[0].equals(hospitalOrigen))
                {
                    aux.setEt("eliminar1234");
                    ListaDLML listaAux = new ListaDLML(listaRemodelacion.getR());
                    eliminado = listaAux.elimina("eliminar1234");
                    listaRemodelacion.setR(listaAux.getR());
                    System.out.println("Hospitales remodelados: :" + listaRemodelacion.desp());
                    return eliminado;
                }
            }
            aux = aux.getSig();
        }
        return eliminado;
    }

    public static boolean estaEnRemodelacion(String dependencia, String hospital)
    {
        NodoML aux = listaRemodelacion.getR();
        while (aux != null)
        {
            String[] obj = (String[]) aux.getObj();
            if (obj != null)
            {
                if (obj[2].equals(dependencia) && obj[0].equals(hospital))
                {
                    return true;
                }
            }
            aux = aux.getSig();
        }
        return false;
    }

    public static String buscaSuTemporal(String dependencia, String hospitalOrigen)
    {
        NodoML aux = listaRemodelacion.getR();
        while (aux != null)
        {
            String[] obj = (String[]) aux.getObj();
            if (obj != null)
            {
                if (obj[2].equals(dependencia) && obj[0].equals(hospitalOrigen))
                {
                    return obj[1];
                }
            }
            aux = aux.getSig();
        }
        return null;
    }

    public static boolean esTemporal(String dependencia, String hospital)
    {
        NodoML aux = listaRemodelacion.getR();
        while (aux != null)
        {
            String[] obj = (String[]) aux.getObj();
            if (obj != null)
            {
                if (obj[2].equals(dependencia) && obj[1].equals(hospital))
                {
                    return true;
                }
            }
            aux = aux.getSig();
        }
        return false;
    }

}
