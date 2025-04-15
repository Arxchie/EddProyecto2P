/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo;

import java.io.Serializable;

/**
 *
 * @author saulo
 */
public class Datos implements Serializable
{

    private String nombre;
    private static int numeroDeRegistros = 0;

    public Datos(String nombre)
    {
        this.nombre = nombre;
    }

    public void setClave(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the numeroDeRegistros
     */
    public static int getNumeroDeRegistros()
    {
        return numeroDeRegistros;
    }

    /**
     * @param aContadorClaves the numeroDeRegistros to set
     */
    public static void setNumeroDeRegistros(int aContadorClaves)
    {
        numeroDeRegistros = aContadorClaves;
    }

    public static String generarClave(Character inicial)
    {
        if (inicial == null)
        {
            throw new IllegalArgumentException("El nombre no puede ser null");
        }

        if (numeroDeRegistros >= 999)
        {
            throw new IllegalStateException("Se ha alcanzado el numero maximo de registros (999)");
        }

        inicial = Character.toUpperCase(inicial);
        return String.format("%c%03d", inicial, ++numeroDeRegistros);
    }

}
