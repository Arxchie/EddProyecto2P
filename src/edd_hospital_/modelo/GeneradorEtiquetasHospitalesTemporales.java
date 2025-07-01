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
public class GeneradorEtiquetasHospitalesTemporales implements Serializable
{
    public  static int etiqueta = 0;
    public static String obtenerSiguienteEtiqueta()
    {
        return String.valueOf(etiqueta += 1);
    }
}
