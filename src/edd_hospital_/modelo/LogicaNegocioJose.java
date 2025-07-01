/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo;

import edd_hospital_.multi_lista.ListaDLML;
import edd_hospital_.multi_lista.MultiListaDL;
import edd_hospital_.multi_lista.NodoML;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class LogicaNegocioJose
{

    public static NodoML subirNodoANivel3(NodoML nodoASubir)
    {
        if (nodoASubir == null)
        {
            throw new IllegalArgumentException("El nodo a subir no puede ser null");
        }
        CreadorDeNodos n = new CreadorDeNodos();
        ListaDLML auxiliar = new ListaDLML(nodoASubir.getAbj());
        NodoML neuro = n.NodoEspecialidades(10, 3, "Neurologia");
        NodoML onco = n.NodoEspecialidades(10, 3, "Oncologia");
        neuro.setArb(nodoASubir);
        onco.setArb(nodoASubir);
        auxiliar.inserta(neuro);
        auxiliar.inserta(onco);
        nodoASubir.setAbj(auxiliar.getR());

        return nodoASubir;
    }

    public static NodoML buscarOncologia(NodoML hospital)
    {
        ListaDLML auxiliar = new ListaDLML(hospital.getAbj());
        NodoML nodoAuxiliar = auxiliar.getR();
        NodoML oncologia = null;
        while (nodoAuxiliar != null)
        {
            String nombre = nodoAuxiliar.getEt().toLowerCase().trim();
            if (EspecialidadesNivel3.oncologia.contains(nombre))
            {
                oncologia = nodoAuxiliar;
            }
            nodoAuxiliar = nodoAuxiliar.getSig();
        }
        return oncologia;

    }

    public static NodoML buscarNeurologia(NodoML hospital)
    {
        ListaDLML auxiliar = new ListaDLML(hospital.getAbj());
        NodoML nodoAuxiliar = auxiliar.getR();
        NodoML neurologia = null;
        while (nodoAuxiliar != null)
        {
            String nombre = nodoAuxiliar.getEt().toLowerCase().trim();

            if (EspecialidadesNivel3.neurologia.contains(nombre))
            {
                neurologia = nodoAuxiliar;
            }
            nodoAuxiliar = nodoAuxiliar.getSig();
        }
        return neurologia;
    }

    /**
     *
     * @param nodoHospital nodo de tipos hospital tipo3
     * @return un arreglo con [0]= nodo con las dos especialidades eliminadas
     * [1]=NodoOncologia eliminado [2]=Nodo NeurocirugiaEliminado
     */
    public static NodoML[] eliminarOncologiaYNeurologia(NodoML nodoHospital)
    {
        if (nodoHospital == null)
        {
            throw new IllegalArgumentException("El nodo a bajar no puede ser null");
        }
        if (nodoHospital.getAbj() == null)
        {
            throw new IllegalStateException("El nodo deberia tener almenos dos especialidades (Oncologia y neurologia)");
        }

        ListaDLML auxiliar = new ListaDLML(nodoHospital.getAbj());
        NodoML nodoAuxiliar = auxiliar.getR();
        NodoML oncologiaEliminada = null;
        NodoML neurocirugiaEliminada = null;
        while (nodoAuxiliar != null)
        {
            String nombre = nodoAuxiliar.getEt().toLowerCase().trim();
            if (EspecialidadesNivel3.oncologia.contains(nombre))
            {
                oncologiaEliminada = auxiliar.elimina(nodoAuxiliar.getEt());

                break;
            }
            nodoAuxiliar = nodoAuxiliar.getSig();
        }
        nodoAuxiliar = auxiliar.getR();
        while (nodoAuxiliar != null)
        {
            String nombre = nodoAuxiliar.getEt().toLowerCase().trim();

            if (EspecialidadesNivel3.neurologia.contains(nombre))
            {
                neurocirugiaEliminada = auxiliar.elimina(nodoAuxiliar.getEt());
                break;
            }
            nodoAuxiliar = nodoAuxiliar.getSig();
        }
        nodoHospital.setAbj(auxiliar.getR());

        return new NodoML[]
        {
            nodoHospital, oncologiaEliminada, neurocirugiaEliminada
        };
    }

    /**
     * Metodo para buscar los hospitales vecinos que sean de nivel 3
     *
     * @param hospital nodo de hospital del que se van a buscar los vecinos
     * @return un arreglo de dos posiciones [0] = vecinoIzquierdo y [1] =
     * vecinoDerecho Si no tiene vecinos las posiciones almacenan nulo
     */
    public static NodoML[] getHospitalesVecinosNivel3(NodoML hospital)
    {
        if (hospital == null)
        {
            throw new IllegalArgumentException("No se puede buscar vecinos por que es nulo");
        }
        NodoML aux1 = hospital.getAnt();
        NodoML aux2 = hospital.getSig();
        NodoML[] hospitalesVecinos = new NodoML[2];

        while (aux1 != null)
        {
            aux1.getObj();
            if (aux1.getObj() instanceof Hospitales h)
            {

                if (h.isNivelTres())
                {
                    System.out.println("vecino izq encontrado");
                    hospitalesVecinos[0] = aux1;

                    break;
                }
                aux1 = aux1.getAnt();
            }
        }

        while (aux2 != null)
        {
            aux2.getObj();
            if (aux2.getObj() instanceof Hospitales h)
            {
                if (h.isNivelTres())
                {
                    System.out.println("vecino der encontrado");
                    hospitalesVecinos[1] = aux2;
                    break;
                }
                aux2 = aux2.getSig();
            }
        }
        return hospitalesVecinos;
    }

    /**
     *
     * @param hospital
     *
     */
    public static void bajarHospitalNivel3(NodoML hospital)
    {

        NodoML[] especialidadesNivel3Eliminadas = eliminarOncologiaYNeurologia(hospital);
        if (especialidadesNivel3Eliminadas[1] == null || especialidadesNivel3Eliminadas[2] == null)
        {

            throw new IllegalStateException("El nodo no tiene las especialidades del nivel 3");
        }
        if (especialidadesNivel3Eliminadas[1].getAbj() == null && especialidadesNivel3Eliminadas[2].getAbj() == null)
        {
            return;
        }
        NodoML vecinosNivel3[] = getHospitalesVecinosNivel3(hospital);

        if (vecinosNivel3[0] != null)
        {
            System.out.println("Se van a mover al vecino izq");
            if (especialidadesNivel3Eliminadas[1].getAbj() != null)
            {
                moverPacientesOncoAHospitalVecino(especialidadesNivel3Eliminadas[1], vecinosNivel3[0]);

            }

            if (especialidadesNivel3Eliminadas[2].getAbj() != null)
            {
                moverPacientesNeuroAHospitalVecino(especialidadesNivel3Eliminadas[2], vecinosNivel3[0]);
            }
        }
        if (vecinosNivel3[1] != null)
        {
            System.out.println("Se van a mover al vecino derecho");

            if (especialidadesNivel3Eliminadas[1].getAbj() != null)
            {
                moverPacientesOncoAHospitalVecino(especialidadesNivel3Eliminadas[1], vecinosNivel3[1]);

            }
            if (especialidadesNivel3Eliminadas[2].getAbj() != null)
            {
                moverPacientesNeuroAHospitalVecino(especialidadesNivel3Eliminadas[2], vecinosNivel3[1]);
            }
        }

        ListaDLML auxiliar1 = new ListaDLML(especialidadesNivel3Eliminadas[1].getAbj());
        while (especialidadesNivel3Eliminadas[1].getAbj() != null)
        {
            System.out.println("Se va mover onco a la bicola");
            NodoML eliminado;

            if (auxiliar1.getR() != null)
            {
                eliminado = auxiliar1.elimina(auxiliar1.getR().getEt());
                if (eliminado != null)
                {
                    eliminado.setArb(null);
                    BicolaPacientes.insertaPacienteOncologia(eliminado);
                }
            }
            especialidadesNivel3Eliminadas[1].setAbj(auxiliar1.getR());
        }

        ListaDLML auxiliar2 = new ListaDLML(especialidadesNivel3Eliminadas[2].getAbj());

        while (especialidadesNivel3Eliminadas[2].getAbj() != null)
        {

            System.out.println("Se va mover neuro a la bicola");
            NodoML eliminado;
            if (auxiliar2.getR() != null)
            {
                eliminado = auxiliar2.elimina(auxiliar2.getR().getEt());
                if (eliminado != null)
                {
                    eliminado.setArb(null);
                    BicolaPacientes.insertaPacienteNeuro(eliminado);
                }
            }
            especialidadesNivel3Eliminadas[2].setAbj(auxiliar2.getR());
        }

    }

    public static boolean tieneOncoYNeuro(NodoML hospital)
    {
        NodoML onco = buscarOncologia(hospital);
        NodoML neuro = buscarNeurologia(hospital);
        return onco != null && neuro != null;
    }

    public static void moverPacientesOncoAHospitalVecino(NodoML onco, NodoML hospitalDestino)
    {
        if (onco == null || hospitalDestino == null)
        {
            throw new IllegalArgumentException("Los parametros no deben de ser nulos");

        }

        NodoML oncoVecino;
        oncoVecino = buscarOncologia(hospitalDestino);
        if (oncoVecino == null)
        {
            throw new IllegalStateException("El hospital deberia de tener Oncologia ");
        }
        moverpacientes(onco, oncoVecino);
    }

    public static void moverPacientesNeuroAHospitalVecino(NodoML neuro, NodoML hospitalDestino)
    {
        if (neuro == null || hospitalDestino == null)
        {
            throw new IllegalArgumentException("Los parametros no deben de ser nulos");

        }

        NodoML neuroVecino;
        neuroVecino = buscarNeurologia(hospitalDestino);
        if (neuroVecino == null)
        {
            throw new IllegalStateException("El hospital deberia de tener  neurologia");
        }
        moverpacientes(neuro, neuroVecino);
    }

    public static void moverpacientes(NodoML especialidadOrigen, NodoML especialidadDestino)
    {

        if (especialidadDestino == null || especialidadOrigen == null)
        {
            throw new IllegalArgumentException("Los nodos mo pueden ser nulos");
        }
        if (especialidadOrigen.getObj() instanceof Especialidad && especialidadDestino.getObj() instanceof Especialidad destino)
        {

            System.out.println("pacientes origen" + Especialidad.getNumeroDePacientes(especialidadOrigen));
            System.out.println("pacientes destino" + Especialidad.getNumeroDePacientes(especialidadDestino));
            while (Especialidad.getNumeroDePacientes(especialidadOrigen) > 0
                    && destino.getNumeroDeCamas() > Especialidad.getNumeroDePacientes(especialidadDestino))

            {
                NodoML eliminado = null;
                ListaDLML auxiliar1 = new ListaDLML(especialidadOrigen.getAbj());
                if (auxiliar1.getR() != null)
                {
                    eliminado = auxiliar1.elimina(auxiliar1.getR().getEt());
                    if (eliminado != null)
                    {
                        eliminado.setArb(null);
                        System.out.println("Eliminado: " + eliminado.getEt());
                        System.out.println("siguiente: " + eliminado.getSig());
                    }
                }
                especialidadOrigen.setAbj(auxiliar1.getR());

                if (eliminado != null)
                {
                    ListaDLML auxiliar = new ListaDLML(especialidadDestino.getAbj());
                    eliminado.setArb(especialidadDestino);
                    auxiliar.inserta(eliminado);
                    especialidadDestino.setAbj(auxiliar.getR());
                    System.out.println(auxiliar.desp());
                }
            }
            //
            System.out.println("pacientes movidos");

            System.out.println("pacientes origen" + Especialidad.getNumeroDePacientes(especialidadOrigen));
            System.out.println("pacientes destino" + Especialidad.getNumeroDePacientes(especialidadDestino));
        }
    }

    public static void darAltaPaciente(MultiListaDL multilista, String rutaActual[], String nombrepaciente)
    {
        if (multilista == null || rutaActual == null || nombrepaciente == null)
        {
            throw new IllegalArgumentException("Los parametros no pueden ser nulos");
        }
        NodoML especialidadPadre = multilista.buscarEnMultilista(rutaActual);

        if (especialidadPadre == null)

        {

            throw new IllegalArgumentException("La especialidad padre es nula");

        }
        if (multilista.elimina(Navegador.crearRutaCompleta(rutaActual, nombrepaciente)) == null)
        {
            throw new IllegalStateException("No se encontro el paciente a dar de alta");
        }
        JOptionPane.showMessageDialog(null, "Paciente dado de alta " + nombrepaciente);
        if (EspecialidadesNivel3.oncologia.contains(especialidadPadre.getEt().toLowerCase().trim()))
        {
            NodoML pacienteEnEspera = BicolaPacientes.eliminaNodoPacienteOncologia();

            if (pacienteEnEspera != null)
            {
                multilista.inserta(pacienteEnEspera, rutaActual);
                JOptionPane.showMessageDialog(null, "Se asigno el paciente: " + pacienteEnEspera.getEt() + " a una cama de oncologia");
            } else
            {
                System.out.println("no hay paciente de onco en espera");
            }

        }

        if (EspecialidadesNivel3.neurologia.contains(especialidadPadre.getEt().toLowerCase().trim()))
        {
            NodoML pacienteEnEspera = BicolaPacientes.eliminaNodoPacienteNeuro();

            if (pacienteEnEspera != null)
            {
                multilista.inserta(pacienteEnEspera, rutaActual);
                JOptionPane.showMessageDialog(null, "Se asigno el paciente: " + pacienteEnEspera.getEt() + " a una cama de neurologia");
            } else
            {
                System.out.println("no hay paciente de neurologia en espera");
            }
        }

    }

    public static void main(String[] args)
    {
        CreadorDeNodos n = new CreadorDeNodos();
        NodoML nodo = n.NodoHospitales("San juan n34", 3, "3 canales");
        ListaDLML lista = new ListaDLML(nodo.getAbj());
        System.out.println(lista.despRecursivo(nodo.getAbj(), ""));
        NodoML datos[] = eliminarOncologiaYNeurologia(nodo);

        System.out.println(lista.despRecursivo(nodo.getAbj(), ""));
        System.out.println(datos[1].getEt());
        System.out.println(datos[2].getEt());
    }
}
