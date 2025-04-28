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

    List<String> ruta = new ArrayList<>(List.of("Dependencias")); // Empieza con la raíz
    String[] niveles =
    {
        "Dependencias", "Hospitales", "Especialidades", "Pacientes"
    };

    public void entrar()
    {
        int index = getNivelIndex(ubicacionActual());
        if (index < niveles.length - 1)
        {
            ruta.add(niveles[index + 1]);
        } else
        {
            System.out.println("Ya estás en el último nivel.");
        }
    }

    public void volver()
    {
        if (ruta.size() > 1)
        {
            ruta.remove(ruta.size() - 1); // Eliminamos el último
        } else
        {
            System.out.println("Ya estás en el nivel raíz.");
        }
    }

    public String ubicacionActual()
    {
        return ruta.get(ruta.size() - 1);
    }

    public void mostrarRuta()
    {
        System.out.println("Ruta completa: " + ruta);
        System.out.println("Ubicacion actual: " + ubicacionActual());
    }

    private int getNivelIndex(String nivel)
    {
        for (int i = 0; i < niveles.length; i++)
        {
            if (niveles[i].equals(nivel))
            {
                return i;
            }
        }
        return -1; // No encontrado
    }

    public static void main(String[] args)
    {

    }
}
