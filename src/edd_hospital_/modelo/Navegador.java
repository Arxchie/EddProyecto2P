/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Navegador
{

    private List<String> ruta; // Empieza con la raíz
    private int numeroNiveles;
    private String raiz = null;

    public Navegador()
    {
        this.ruta = new ArrayList<>();
    }

    public Navegador(String raiz, int numeroNiveles)
    {
        this.raiz = raiz;
        this.ruta = new ArrayList<>(List.of(raiz));
        this.numeroNiveles = numeroNiveles;
    }

    public Navegador(int numeroNiveles)
    {
        this.numeroNiveles = numeroNiveles;
        this.ruta = new ArrayList<>();
    }

    public void entrar(String nombre) throws NavegadorException
    {
        if (getRuta().size() < getNumeroNiveles())
        {
            getRuta().add(nombre);
        } else
        {
            throw new NavegadorException("Ya estás en el último nivel.");
        }
    }

    public void volver() throws NavegadorException
    {
        if (getRuta().size() >= 1 && !ruta.get(ruta.size() - 1).equals(raiz))
        {
            getRuta().remove(getRuta().size() - 1);
        } else
        {
            throw new NavegadorException("No es posible volver mas");
        }
    }

    public String getNivelActual()
    {
        if (!ruta.isEmpty())
        {
            return getRuta().get(getRuta().size() - 1);
        }
        return null;
    }

    public void mostrarRuta()
    {
        System.out.println("Ruta completa: " + getRuta());
        System.out.println("Ubicacion actual: " + getNivelActual());
    }

    public int getNivelIndex(String nivel)
    {
        int contador = 0;
        for (String s : getRuta())
        {
            if (s.equals(nivel))
            {
                return contador;
            }
            contador++;

        }
        return -1; // No encontrados
    }

    public int getNivelActualIndex()
    {
        return ruta.size();
    }

    public static void main(String[] args)
    {
        Navegador navegador = new Navegador("Dependencias ", 4);
        navegador.mostrarRuta();
        try
        {
            navegador.entrar("Especialidades");
            navegador.entrar("Pacientes");
            navegador.mostrarRuta();
            ;
        } catch (NavegadorException e)
        {
            System.out.println(e.getMessage());
        }

        navegador.mostrarRuta();
        System.out.println(navegador.getRuta().size());

    }

    /**
     * @return the ruta
     */
    public List<String> getRuta()
    {
        return ruta;
    }

    public String[] getRutaArray()
    {
        String[] arrayRuta = ruta.toArray(new String[ruta.size()]);
        for (int i = 0; i < ruta.size(); i++)
        {
            arrayRuta[i] = ruta.get(i);
        }
        return arrayRuta;
    }

    /**
     * @return the numeroNiveles
     */
    public int getNumeroNiveles()
    {
        return numeroNiveles;
    }

    /**
     * @return the raiz
     */
    public String getRaiz()
    {
        return raiz;
    }

    /**
     * @param numeroNiveles the numeroNiveles to set
     */
    public void setNumeroNiveles(int numeroNiveles)
    {
        this.numeroNiveles = numeroNiveles;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(String raiz)
    {
        this.raiz = raiz;
    }
}
