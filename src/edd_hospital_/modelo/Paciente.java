/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo;

/**
 *
 * @author saulo
 */
public class Paciente extends Datos
{
    private String claveP;
    private String estatus;
    private String vigencia;
    private char sexo;
    
    

    public Paciente(String estatus, String vigencia, char sexo, String nombre)
    {
        super( nombre);
        this.estatus = estatus;
        this.vigencia = vigencia;
        this.sexo = sexo;
        this.claveP=generarClave('P');
    }

    /**
     * @return the estatus
     */
    public String getEstatus()
    {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(String estatus)
    {
        this.estatus = estatus;
    }

    /**
     * @return the vigencia
     */
    public String getVigencia()
    {
        return vigencia;
    }

    /**
     * @param vigencia the vigencia to set
     */
    public void setVigencia(String vigencia)
    {
        this.vigencia = vigencia;
    }

    /**
     * @return the sexo
     */
    public char getSexo()
    {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(char sexo)
    {
        this.sexo = sexo;
    }
    

    /**
     * @return the claveP
     */
    public String getClaveP()
    {
        return claveP;
    }

    /**
     * @param claveP the claveP to set
     */
    public void setClaveP(String claveP)
    {
        this.claveP = claveP;
    }
    
}
