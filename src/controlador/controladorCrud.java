package controlador;

import cjb.ci.CtrlInterfaz;
import cjb.ci.Mensaje;
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
import edd_hospital_.modelo.Datos;
import edd_hospital_.modelo.GeneradorEtiquetasHospitalesTemporales;
import edd_hospital_.modelo.LogicaNegocioJose;
import edd_hospital_.modelo.RemodelacionHospitales;
import edd_hospital_.multi_lista.ListaDLML;
import poo.ManipulacionArchivos;

/**
 *
 * @author HP
 */
public class controladorCrud
{

    private static final int NUMERO_NIVELES_ENTRABLES_NAVEGADOR = 3;
    private static final String archivos = "datos.dat";
    private static final String archivoEtiquetas = "etiquetas.dat";
    private static final String archivoRegistros = "archivoRegistros.dat";
    private static final String archivoListaHospitalesTemporales = "archivoHospitalesTemporales.dat";
    VtnGeneral2 ventanaGeneral;
    Navegador navegador;
    MultiListaDL multilista;
    NodoML nivelActual;

    public controladorCrud()
    {
        inicializar();
    }

    private void cargarDatos()
    {

        multilista = (MultiListaDL) ManipulacionArchivos.carga(ventanaGeneral, archivos);
        Integer numeroEtiquetas = (Integer) ManipulacionArchivos.carga(ventanaGeneral, archivoEtiquetas);
        Integer nRegistros = (Integer) ManipulacionArchivos.carga(ventanaGeneral, archivoRegistros);
        RemodelacionHospitales.listaRemodelacion = (ListaDLML) ManipulacionArchivos.carga(ventanaGeneral, archivoListaHospitalesTemporales);

        if (nRegistros == null)
        {
            Datos.setNumeroDeRegistros(0);
        } else
        {

            Datos.setNumeroDeRegistros(nRegistros);
        }

        if (numeroEtiquetas == null)
        {
            GeneradorEtiquetasHospitalesTemporales.etiqueta = 0;
        } else
        {

            GeneradorEtiquetasHospitalesTemporales.etiqueta = numeroEtiquetas;
        }
        if (RemodelacionHospitales.listaRemodelacion == null)
        {
            RemodelacionHospitales.listaRemodelacion = new ListaDLML();
            System.out.println("No se encontraron datos guardados en la lista");
        } else
        {
            System.out.println("Datos cargados exitosamente.");
        }
        if (multilista == null)
        {
            multilista = new MultiListaDL<>();
            System.out.println("No se encontraron datos guardados. Se inicializó una nueva MultiListaDL.");
        } else
        {
            System.out.println("Datos cargados exitosamente.");
        }

    }

    private void inicializar()
    {
        ventanaGeneral = new VtnGeneral2();
        navegador = new Navegador(NUMERO_NIVELES_ENTRABLES_NAVEGADOR);
        cargarDatos();
        nivelActual = multilista.getR();
        inicializarVtnGeneral();
        actualizarVista();
    }
//    private void inicializar() {
//        ventanaGeneral = new VtnGeneral2();
//        multilista = new MultiListaDL<>();
//        navegador = new Navegador(NUMERO_NIVELES_ENTRABLES_NAVEGADOR);
//        inicializarVtnGeneral();
//        actualizarVista();
//    }

    public void actualizarVista()
    {

        MostrableEnTabla modeloTabla = ModeloTablaFactory.crearModeloDeTabla(navegador.getTipoNivelActual());
        if (navegador.getTipoNivelActual() == Niveles.DEPENDENCIA)
        {
            ventanaGeneral.mostrarEnLaTabla(modeloTabla, multilista.getR());
        } else
        {
            if (nivelActual != null)
            {
                ventanaGeneral.mostrarEnLaTabla(modeloTabla, nivelActual.getAbj());
            }

        }
        ventanaGeneral.actualizarPanelNavegacion(navegador.getRuta());
        actualizarTituloNivel();
        ventanaGeneral.mostrarBotonAltaPaciente(navegador.getTipoNivelActual());
        ventanaGeneral.mostrarBotonesRemodelacion(navegador.getTipoNivelActual());
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
                        String seleccionado = ventanaGeneral.getNombreSeleccionado(navegador.getTipoNivelActual(), navegador.getElementoActual());
                        entrarANivel(seleccionado);
                        actualizarVista();
                    } catch (NavegadorException ex)
                    {
                        JOptionPane.showMessageDialog(ventanaGeneral, ex.getMessage());
                    }

                }
            }
        });
        ventanaGeneral.getBtnVolver().addActionListener((e)
                ->
        {
            volver();
            actualizarVista();
        });
        ventanaGeneral.getBtnNuevo().addActionListener((e)
                ->
        {
            System.out.println(Datos.getNumeroDeRegistros());
            VentanaRegistrable v = VentanaRegistrableFactory.crearVentanaRegistrble(navegador.getTipoNivelActual());
            v.setVisible(true);
            if (v.getNodoRegistrado() != null)
            {
                Crudable crud = CrudFactory.crearCrud(navegador.getTipoNivelActual());
                try
                {

                    crud.insertar(multilista, v.getNodoRegistrado(), navegador.getRutaArray());
                    JOptionPane.showMessageDialog(ventanaGeneral, "Registrado con exito");
                    ManipulacionArchivos.guarda(ventanaGeneral, Datos.getNumeroDeRegistros(), archivoRegistros);
                    ManipulacionArchivos.guarda(ventanaGeneral, multilista, archivos);
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
        ventanaGeneral.getBtnModificar().addActionListener((e)
                ->
        {
            String seleccionado = ventanaGeneral.getNombreSeleccionado(navegador.getTipoNivelActual(), navegador.getElementoActual());

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
                            ManipulacionArchivos.guarda(ventanaGeneral, multilista, archivos);
                            actualizarVista();
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
        ventanaGeneral.getBtnEliminar().addActionListener((ActionEvent e)
                ->
        {
            String cveSeleccionado = ventanaGeneral.getNombreSeleccionado(navegador.getTipoNivelActual(), navegador.getElementoActual());
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
                    try
                    {
                        Crudable crud = CrudFactory.crearCrud(navegador.getTipoNivelActual());
                        crud.eliminar(multilista, Navegador.crearRutaCompleta(navegador.getRutaArray(), cveSeleccionado));
                        ManipulacionArchivos.guarda(ventanaGeneral, Datos.getNumeroDeRegistros(), archivoRegistros);
                        ManipulacionArchivos.guarda(ventanaGeneral, multilista, archivos);
                        actualizarVista();

                    } catch (RuntimeException ex)
                    {
                        JOptionPane.showMessageDialog(ventanaGeneral, ex.getMessage());
                    }
                }
            } else
            {
                JOptionPane.showMessageDialog(ventanaGeneral, "No hay un elemento seleccionado para eliminar.");
            }
        });

        ventanaGeneral.getBtnAltaPaciente().addActionListener((e) ->
        {
            String nombreSeleccionado = ventanaGeneral.getNombreSeleccionado(navegador.getTipoNivelActual(), navegador.getElementoActual());
            if (nombreSeleccionado != null)
            {
                int opcion = JOptionPane.showConfirmDialog(
                        ventanaGeneral,
                        "¿Estas seguro de que quieres darlo de alta?",
                        "Confirmar alta",
                        JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.YES_OPTION)
                {
                    try
                    {
                        LogicaNegocioJose.darAltaPaciente(multilista, navegador.getRutaArray(), nombreSeleccionado);
                        ManipulacionArchivos.guarda(ventanaGeneral, Datos.getNumeroDeRegistros(), archivoRegistros);
                        ManipulacionArchivos.guarda(ventanaGeneral, multilista, archivos);
                        actualizarVista();

                    } catch (RuntimeException ex)
                    {
                        JOptionPane.showMessageDialog(ventanaGeneral, ex.getMessage());
                    }
                }
            } else
            {
                JOptionPane.showMessageDialog(ventanaGeneral, " No hay un elemento seleccionado para eliminar. ");
            }
        });
        inicializarBotonesRemodelacion();

    }

    private void inicializarBotonesRemodelacion()
    {
        ventanaGeneral.getBtnRemodelacion().addActionListener((e) ->
        {
            String nombreSeleccionado = ventanaGeneral.getNombreSeleccionado2();
            if (nombreSeleccionado != null)
            {

                try
                {
                    String etiqueta = GeneradorEtiquetasHospitalesTemporales.obtenerSiguienteEtiqueta();
                    RemodelacionHospitales.remodelacion(navegador.getRutaArray()[0], nombreSeleccionado, etiqueta, multilista);
                    Mensaje.exito(ventanaGeneral,
                            "Se mando a remodelación el hospital " + nombreSeleccionado);
                    ManipulacionArchivos.guarda(ventanaGeneral, multilista, archivos);
                    ManipulacionArchivos.guarda(ventanaGeneral, GeneradorEtiquetasHospitalesTemporales.etiqueta, archivoEtiquetas);
                    ManipulacionArchivos.guarda(ventanaGeneral, RemodelacionHospitales.listaRemodelacion, archivoListaHospitalesTemporales);
                    actualizarVista();
                    System.out.println(multilista.desp());
                } catch (RuntimeException ex)
                {
                    JOptionPane.showMessageDialog(ventanaGeneral, ex.getMessage());
                    
                }
            } else
            {
                JOptionPane.showMessageDialog(ventanaGeneral, " No hay un elemento seleccionado para remodelar. ");
            }
        });
        ventanaGeneral.getBtnRemodelacionTerminada().addActionListener((e) ->
        {
            String nombreSeleccionado = ventanaGeneral.getNombreSeleccionado2();
            if (nombreSeleccionado != null)
            {

                try
                {
                    RemodelacionHospitales.remodelacionTErminada(navegador.getElementoActual(), nombreSeleccionado, multilista);
                    Mensaje.exito(ventanaGeneral,
                            "Hospital remodelado " + nombreSeleccionado);
                    ManipulacionArchivos.guarda(ventanaGeneral, multilista, archivos);
                    actualizarVista();
                    System.out.println(multilista.desp());
                } catch (RuntimeException ex)
                {
                    JOptionPane.showMessageDialog(ventanaGeneral, ex.getMessage());
                }
            } else
            {
                JOptionPane.showMessageDialog(ventanaGeneral, " No hay un elemento seleccionado para terminar remodelación. ");
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
