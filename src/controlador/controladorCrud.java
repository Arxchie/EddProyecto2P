/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import edd_hospital_.modelo.Navegador;
import edd_hospital_.modelo.NavegadorException;
import edd_hospital_.modelo.Niveles;
import edd_hospital_.modelo.Nodos;
import edd_hospital_.modelo.cruds.CrudFactory;
import edd_hospital_.modelo.modelosDeTablas.ModeloTablaFactory;
import interfaces.MostrableEnTabla;
import edd_hospital_.multi_lista.MultiListaDL;
import edd_hospital_.multi_lista.NodoML;

import edd_hospital_.vista.VtnGeneral2;
import interfaces.VentanaRegistrable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import interfaces.Crudable;

/**
 *
 * @author HP
 */
public class controladorCrud
{

    private static final int NUMERO_NIVELES_NAVEGADOR = 4;
    VtnGeneral2 ventanaGeneral;
    Navegador navegador;
    MultiListaDL multilista;
    NodoML nivelActual;

    public controladorCrud()
    {
        inicializar();
    }

    private void inicializar()
    {
        ventanaGeneral = new VtnGeneral2();
        multilista = new MultiListaDL<>();
        navegador = new Navegador(NUMERO_NIVELES_NAVEGADOR);
        // llenarConDatosDePrueba();
        // nivelActual = multilista.getR();
        inicializarVtnGeneral();
        actualizarVista();
    }

    private void llenarConDatosDePrueba()
    {
        Nodos n = new Nodos();
        NodoML nodo1 = n.NodoDependencia("Estatal", "IMMS");
        NodoML nodo2 = n.NodoDependencia("Estatal", "ISTE");
        NodoML nodo3 = n.NodoDependencia("Estatal", "ISEMYN");
        NodoML nodo4 = n.NodoHospitales("matamoros n23", 1, "san juan");
        NodoML nodo5 = n.NodoHospitales("san ROman n32", 2, " Koto Hospital");
        multilista.inserta(nodo1);
        multilista.inserta(nodo2);
        multilista.inserta(nodo3);
        multilista.inserta(nodo4, nodo1.getEt());
        multilista.inserta(nodo5, nodo1.getEt(), nodo4.getEt());
        System.out.println(multilista.desp());
    }

    public void entrarANivel(String nombre) throws NavegadorException
    {
        navegador.entrar(nombre);
        System.out.println("nnnnnnnn" + nombre);
        String[] ruta = navegador.getRutaArray();
        nivelActual = multilista.buscarEnMultilista(ruta);
    }

    public void actualizarVista()
    {
        MostrableEnTabla modeloTabla = ModeloTablaFactory.crearModeloDeTabla(navegador.getTipoNivelActual());
        System.out.println(navegador.getTipoNivelActual());
        if (navegador.getTipoNivelActual() == Niveles.DEPENDENCIA)
        {
            nivelActual = multilista.getR();
            ventanaGeneral.mostrarEnLaTabla(modeloTabla, nivelActual);
        } else
        {
            ventanaGeneral.mostrarEnLaTabla(modeloTabla, nivelActual.getAbj());
        }
        ventanaGeneral.actualizarPanelNavegacion(navegador.getRuta());
        actualizarTituloNivel();
    }

    private void actualizarTituloNivel()
    {
        switch (navegador.getTipoNivelActual())
        {
            case DEPENDENCIA ->
            {
                ventanaGeneral.getLblTitulo().setText("DEPENDENCIAS");
            }
            case HOSPITAL ->
            {
                ventanaGeneral.getLblTitulo().setText("HOSPITALES");
            }
            case ESPECIALIDAD ->
            {
                ventanaGeneral.getLblTitulo().setText("ESPECIALIDADES");
            }
            case PACIENTE ->
            {
                ventanaGeneral.getLblTitulo().setText("PACIENTES");
            }
            default ->
                throw new AssertionError();
        }
    }

    private void inicializarVtnGeneral()
    {
        ventanaGeneral.getJtbTabla().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2)
                {
                    try
                    {
                        String seleccionado = ventanaGeneral.getClaveSeleccionado();
                        entrarANivel(seleccionado);
                        actualizarVista();
                    } catch (NavegadorException ex)
                    {
                        JOptionPane.showMessageDialog(ventanaGeneral, ex.getMessage());
                    }

                }
            }
        });
        ventanaGeneral.getBtnVolver().addActionListener((e) ->
        {
            volver();
            actualizarVista();
        });
        ventanaGeneral.getBtnNuevo().addActionListener((e) ->
        {
            System.out.println("Nuevo");
            VentanaRegistrable v = VentanaRegistrableFactory.crearVentanaRegistrble(navegador.getTipoNivelActual());

            if (v.getNodoRegistrado() != null)
            {

                Crudable crud = CrudFactory.crearCrud(navegador.getTipoNivelActual());
                crud.insertar(multilista, v.getNodoRegistrado(), navegador.getRutaArray());
                JOptionPane.showMessageDialog(ventanaGeneral, "Registrado con exito");
                actualizarVista();
            } else
            {
                JOptionPane.showMessageDialog(ventanaGeneral, "NO SE REALIZO NINGUN REGISTRO");
            }

        });
    }

    private void volver()
    {
        try
        {
            if (navegador.getTipoNivelActual() == Niveles.HOSPITAL)
            {
                nivelActual = multilista.getR();
            } else
            {
                if (navegador.getTipoNivelActual() != Niveles.DEPENDENCIA)
                {
                    nivelActual = nivelActual.getArb();
                }
            }
            navegador.volver();
        } catch (NavegadorException ex)
        {
            JOptionPane.showMessageDialog(ventanaGeneral, ex.getMessage());
        }

    }

    public void iniciar()
    {
        ventanaGeneral.setVisible(true);
    }

    public static void main(String[] args)
    {
        controladorCrud controlador = new controladorCrud();
        controlador.iniciar();

    }
}
