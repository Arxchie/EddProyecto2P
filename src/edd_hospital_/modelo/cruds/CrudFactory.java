/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo.cruds;

import edd_hospital_.modelo.Niveles;
import interfaces.Crudable;

/**
 *
 * @author HP
 */
public class CrudFactory {

    public static Crudable crearCrud(Niveles nivel) {
        switch (nivel) {
            case DEPENDENCIA -> {
                return new CrudGenerico();
            }
            case HOSPITAL -> {
                return new CrudHospital();
            }
            case ESPECIALIDAD -> {

                return new CrudEspecialidad();
            }

            case PACIENTE -> {
                return new CrudPaciente();
            }

            default ->
                throw new AssertionError("Aun no se define como CRUDear en ese nivel");
        }

    }

}
