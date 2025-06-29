/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo;

/**
 *
 * @author saulo
 */
public class Dependencia extends Datos
{
    private String tipo;
    private String claveD;

    public Dependencia(String tipo, String nombre) {
        super(nombre);
        this.tipo = tipo;
        this.claveD = generarClave('D');
    }

    public Dependencia(String tipo, String nombre, String clave) {
        super(nombre);
        this.tipo = tipo;
        this.claveD = clave;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClaveD() {
        return claveD;
    }

    public void setClaveD(String claveD) {
        this.claveD = claveD;
    }

}
