/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controlador.controladorCrud;
import edd_hospital_.modelo.Dependencia;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Main
{

    public static void main(String[] args)
    {

        try
        {
            controladorCrud controlador = new controladorCrud();
            controlador.iniciar();
            System.out.println("inicio");
        } catch (Exception e )
        {
            JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado");
            System.out.println(e.getMessage());
        }

    }
}
