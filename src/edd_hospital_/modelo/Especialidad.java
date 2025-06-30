/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo;

import edd_hospital_.multi_lista.NodoML;

/**
 *
 * @author saulo
 */
public class Especialidad extends Datos
{

    private int numeroDeCamas;
    private int numeroDeMedicos;
    private String claveE;

    public Especialidad(int camas, int medicos, String nombre)
    {
        super(nombre);
        if (camas < 0 || medicos < 0)
        {
            throw new IllegalArgumentException("El número de camas y médicos no puede ser negativo");
        }
        this.numeroDeCamas = camas;
        this.numeroDeMedicos = medicos;
        this.claveE = generarClave('E');
    }

    /**
     * @return the numeroDeCamas
     */
    public int getNumeroDeCamas()
    {
        return numeroDeCamas;
    }

    /**
     * @param numeroDeCamas the numeroDeCamas to set
     */
    public void setNumeroDeCamas(int numeroDeCamas)
    {
        this.numeroDeCamas = numeroDeCamas;
    }

    /**
     * @return the numeroDeMedicos
     */
    public int getNumeroDeMedicos()
    {
        return numeroDeMedicos;
    }

    /**
     * @param numeroDeMedicos the numeroDeMedicos to set
     */
    public void setNumeroDeMedicos(int numeroDeMedicos)
    {
        this.numeroDeMedicos = numeroDeMedicos;
    }

    /**
     * @return the claveE
     */
    public String getClaveE()
    {
        return claveE;
    }

    /**
     * @param claveE the claveE to set
     */
    public void setClaveE(String claveE)
    {
        this.claveE = claveE;
    }

    public static int getNumeroDePacientes(NodoML especialidad)
    {

        if (especialidad == null)
        {
            throw new IllegalArgumentException("el nodo no debe de ser nulo");
        }
        NodoML aux = especialidad.getAbj();
        int numeroDePacientes = 0;
        while (aux != null)
        {
            numeroDePacientes += 1;
            aux = aux.getSig();
        }
        return numeroDePacientes;
    }

}
