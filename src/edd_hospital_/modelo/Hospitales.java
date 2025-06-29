/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo;

/**
 *
 * @author saulo
 */
public class Hospitales extends Datos {


    private String direccion;
    private int nivel;
    private String claveH;

    public Hospitales(String direc, int nivel, String nombre) {
        super(nombre);
        this.direccion = direc;
        this.nivel = nivel;
        this.claveH = generarClave('H');
    }

    public Hospitales(String direc, int nivel, String nombre, String clave) {
        super(nombre);
        this.direccion = direc;
        this.nivel = nivel;
        this.claveH = clave;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the claveH
     */
    public String getClaveH() {
        return claveH;
    }

    /**
     * @param claveH the claveH to set
     */
    public void setClaveH(String claveH) {
        this.claveH = claveH;
    }

}
