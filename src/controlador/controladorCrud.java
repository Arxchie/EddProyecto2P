/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import edd_hospital_.modelo.Navegador;
import edd_hospital_.modelo.NavegadorException;
import edd_hospital_.modelo.Niveles;
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
import interfaces.VentanaEditable;
import java.awt.event.ActionEvent;

/**
 *
 * @author HP
 */
public class controladorCrud
{

    private static final int NUMERO_NIVELES_ENTRABLES_NAVEGADOR = 3;
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
        navegador = new Navegador(NUMERO_NIVELES_ENTRABLES_NAVEGADOR);
        inicializarVtnGeneral();
        actualizarVista();
    }

    public void actualizarVista()
    {
        MostrableEnTabla modeloTabla = ModeloTablaFactory.crearModeloDeTabla(navegador.getTipoNivelActual());
        if (navegador.getTipoNivelActual() == Niveles.DEPENDENCIA)
        {
            ventanaGeneral.mostrarEnLaTabla(modeloTabla, multilista.getR());
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

            VentanaRegistrable v = VentanaRegistrableFactory.crearVentanaRegistrble(navegador.getTipoNivelActual());
            v.setVisible(true);
            if (v.getNodoRegistrado() != null)
            {
                Crudable crud = CrudFactory.crearCrud(navegador.getTipoNivelActual());
                try
                {
                    crud.insertar(multilista, v.getNodoRegistrado(), navegador.getRutaArray());
                    JOptionPane.showMessageDialog(ventanaGeneral, "Registrado con exito");
                    actualizarVista();
                } catch (RuntimeException ex)
                {
                    JOptionPane.showMessageDialog(ventanaGeneral, ex.getMessage());
                }
            } else
            {
                JOptionPane.showMessageDialog(ventanaGeneral, "NO SE REALIZO NINGUN REGISTRO");
            }

        });
        ventanaGeneral.getBtnModificar().addActionListener((e) ->
        {
            String seleccionado = ventanaGeneral.getClaveSeleccionado();

            if (seleccionado != null)
            {
                String rutaCompleta[] = Navegador.crearRutaCompleta(navegador.getRutaArray(), seleccionado);
                NodoML nodoSeleccionado = multilista.buscarEnMultilista(rutaCompleta);
                if (nodoSeleccionado != null)
                {
                    VentanaEditable v = VentanaEditableFactory.crearVentanaEditable(navegador.getTipoNivelActual());
                    v.cargarDatos(nodoSeleccionado);
                    v.setVisible(true);
                    Object objetoEditado = v.getObjetoEditado();
                    if (objetoEditado != null)
                    {
                        Crudable crud = CrudFactory.crearCrud(navegador.getTipoNivelActual());
                        try
                        {
                            crud.actualizarNodo(multilista, objetoEditado, rutaCompleta);
                            JOptionPane.showMessageDialog(ventanaGeneral, "Datos actualizados con éxito");
                            actualizarVista();

                        } catch (RuntimeException ex)
                        {
                            JOptionPane.showMessageDialog(ventanaGeneral, ex.getMessage());
                        }
                    }

                } else
                {
                    JOptionPane.showMessageDialog(ventanaGeneral, "No se encontró el elemento seleccionado");
                }

            } else
            {
                JOptionPane.showMessageDialog(ventanaGeneral, "Debe seleccionar un elemento");
            }

//            VentanaEditable v = VentanaEditableFactory.crearVentanaEditable(navegador.getTipoNivelActual());
//            v.setVisible(true);
        });
        ventanaGeneral.getBtnEliminar().addActionListener((ActionEvent e) ->
        {
            String cveSeleccionado = ventanaGeneral.getClaveSeleccionado();
            if (cveSeleccionado != null)
            {
                int opcion = JOptionPane.showConfirmDialog(
                        ventanaGeneral,
                        "¿Estas seguro de que deseas eliminar el elemento seleccionado?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.YES_OPTION)
                {
                    Crudable crud = CrudFactory.crearCrud(navegador.getTipoNivelActual());
                    crud.eliminar(multilista, navegador.crearRutaCompleta(navegador.getRutaArray(), cveSeleccionado));
                    actualizarVista();
                }
            } else
            {
                JOptionPane.showMessageDialog(ventanaGeneral, "No hay un elemento seleccionado para eliminar.");
            }
        });

    }

    public void entrarANivel(String nombre) throws NavegadorException
    {
        navegador.entrar(nombre);
        String[] ruta = navegador.getRutaArray();
        nivelActual = multilista.buscarEnMultilista(ruta);

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
