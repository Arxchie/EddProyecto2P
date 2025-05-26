/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo.modelosDeTablas;

import edd_hospital_.modelo.Paciente;

/**
 *
 * @author HP
 */
public class ModeloTablaPaciente extends ModeloTablaGenerico<Paciente>

{

    public ModeloTablaPaciente()
    {
        super(Paciente.class);
    }

    @Override
    public String[] getNombreCabeceras()
    {
        return new String[]
        {
            "Clave", "Nomre", "Sexo", "Estatus", "Vigencia"
        };
    }

    @Override
    protected Object[] extraerFila(Paciente obj)
    {
        return new Object[]
        {
            obj.getClaveP(),
            obj.getNombre(),
            obj.getSexo(),
            obj.getEstatus(),
            obj.getVigencia(),
        };
    }

}
