/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edd_hospital_.vista;

import javax.swing.table.DefaultTableModel;
import edd_hospital_.multi_lista.*;

import edd_hospital_.modelo.*;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author saulo
 */
public class VtnGeneral extends javax.swing.JFrame
{

     Navegador navegador;
     MultiListaDL multilista = new MultiListaDL();
     NodoML ubicacionActual;
     private final int DEPENDENCIAS = 0;
     private final int HOSPITALES = 1;
     private final int ESPECIALIDADES = 2;
     private final int PACIENTES = 3;

     /**
      * Creates new form VtnGeneral
      */
     DefaultTableModel modeloTabla = new DefaultTableModel()
     {
          @Override
          public boolean isCellEditable(int row, int column)
          {
               return false; // ninguna celda editable
          }
     };

     public VtnGeneral()
     {

          initComponents();
          navegador = new Navegador(4);
          jtbTabla.getTableHeader().setReorderingAllowed(false);
          multilista.inserta(new NodoML(new Dependencia("1", "San Juan"), "D001"));
          multilista.inserta(new NodoML(new Hospitales("San lucas n34", 1, "24 noviembre"), "H002"), "D001");
          multilista.inserta(new NodoML(new Dependencia("2", "San Lucas"), "D003"));
          ubicacionActual = multilista.getR();
          actualizarVista();

     }

     private void actualizarPanelNavegacion()
     {
          panelNavegacion.removeAll(); // Limpiar el panel antes de actualizarlo
          List<String> ruta = navegador.getRuta(); // Obtener la ruta

          for (int i = 0; i < ruta.size(); i++)
          {
               String s = ruta.get(i);
               JLabel ubicacion = new JLabel(s);
               ubicacion.setFont(new Font("Arial", Font.PLAIN, 14));
               panelNavegacion.add(ubicacion);
               // Si no es el último elemento, agregar flecha
               try
               {
                    if (i < ruta.size() - 1)
                    {

                         JLabel flecha = new JLabel(new ImageIcon(getClass().getResource("/edd_hospital_/vista/imagenes/flecha.png")));
                         panelNavegacion.add(flecha);
                    }
               } catch (Exception e)
               {
                    JLabel flecha = new JLabel(">");
                    panelNavegacion.add(flecha);
                    System.out.println(e.getMessage());
               }
          }
          panelNavegacion.revalidate(); // Actualiza la interfaz
          panelNavegacion.repaint();    // Repinta el panel para mostrar los cambios
     }

     private void actualizarVista()
     {
          if (ubicacionActual == null)
          {
               return;
          }
          if (navegador.getNivelActualIndex() == DEPENDENCIAS)
          {
               mostrarDatosEnTabla(ubicacionActual);
          } else
          {
               mostrarDatosEnTabla(ubicacionActual.getAbj());
          }
          actualizarPanelNavegacion();
     }

     @SuppressWarnings("unchecked")
     private void mostrarDatosEnTabla(NodoML raiz)
     {

          modeloTabla.setRowCount(0); // Limpiar tabla
          resetearModeloTabla();
          switch (navegador.getNivelActualIndex())
          {
               case DEPENDENCIAS ->
               {
                    System.out.println("Dependencias");
                    colocarModeloDependencia();
                    mostrarDependencias(raiz);
               }
               case HOSPITALES ->
               {
                    System.out.println("Hospitales");
                    colocarModeloHospitales();
                    mostrarHospitales(raiz);
               }
               case ESPECIALIDADES ->
               {
                    System.out.println("Especialidades");
                    colocarModeloEspecialidad();
                    mostrarEspecialidades(raiz);
               }
               case PACIENTES ->
               {
                    System.out.println("Pacientes");
                    colocarModeloPacientes();
                    mostrarPacientes(raiz);
               }
               default ->
                    JOptionPane.showMessageDialog(this, "Nivel desconocido" + navegador.getNivelActualIndex());

          }

     }

     @SuppressWarnings("unchecked")
     private void mostrarDependencias(NodoML<Object> r)
     {
          NodoML<Object> aux = r;
          while (aux != null)
          {
               Object dato = aux.getObj();
               if (dato instanceof Dependencia d)
               {
                    modeloTabla.addRow(new Object[]
                    {
                         d.getClaveD(),
                         d.getNombre(),
                         d.getTipo()
                    });
               }
               aux = aux.getSig();
          }
     }

     @SuppressWarnings("unchecked")
     private void mostrarEspecialidades(NodoML<Object> r)
     {
          NodoML<Object> aux = r;
          while (aux != null)
          {
               Object dato = aux.getObj();
               if (dato instanceof Especialidad e)
               {
                    modeloTabla.addRow(new Object[]
                    {
                         e.getClaveE(),
                         e.getNombre(),
                         e.getNumeroDeMedicos(),
                         e.getNumeroDeCamas()
                    });

               }
               aux = aux.getSig();
          }
     }

     @SuppressWarnings("unchecked")
     private void mostrarHospitales(NodoML<Object> r)
     {
          NodoML<Object> aux = r;
          while (aux != null)
          {
               Object dato = aux.getObj();
               if (dato instanceof Hospitales h)
               {

                    modeloTabla.addRow(new Object[]
                    {
                         h.getClaveH(),
                         h.getNombre(),
                         h.getDireccion(),
                         h.getNivel()
                    });
               }
               aux = aux.getSig();
          }
     }

     @SuppressWarnings("unchecked")
     private void mostrarPacientes(NodoML<Object> r)
     {
          NodoML<Object> aux = r;
          while (aux != null)
          {
               Object dato = aux.getObj();
               if (dato instanceof Paciente p)
               {
                    modeloTabla.addRow(new Object[]
                    {
                         p.getClaveP(),
                         p.getNombre(),
                         p.getEstatus(),
                         p.getVigencia(),
                         p.getSexo()
                    });

               }

               aux = aux.getSig();
          }
     }

     private void colocarModeloDependencia()
     {
          modeloTabla.addColumn("Clave");
          modeloTabla.addColumn("Nombre");
          modeloTabla.addColumn("Tipo");
          jtbTabla.setModel(modeloTabla);
     }

     private void colocarModeloEspecialidad()
     {
          modeloTabla.addColumn("Clave");
          modeloTabla.addColumn("Nombre");
          modeloTabla.addColumn("Numero de Medicos");
          modeloTabla.addColumn("Numero de Camas");
          jtbTabla.setModel(modeloTabla);
     }

     private void colocarModeloHospitales()
     {
          modeloTabla.addColumn("Clave");
          modeloTabla.addColumn("Nombre");
          modeloTabla.addColumn("Direccion");
          modeloTabla.addColumn("Nivel");
          jtbTabla.setModel(modeloTabla);
     }

     private void colocarModeloPacientes()
     {
          modeloTabla.addColumn("Clave");
          modeloTabla.addColumn("Nombre");
          modeloTabla.addColumn("Estatus");
          modeloTabla.addColumn("Vigencia");
          modeloTabla.addColumn("Sexo");
          jtbTabla.setModel(modeloTabla);
     }

     private void resetearModeloTabla()
     {
          modeloTabla.setColumnCount(0);
     }

     /**
      * This method is called from within the constructor to initialize the
      * form. WARNING: Do NOT modify this code. The content of this method is
      * always regenerated by the Form Editor.
      */
     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents()
     {

          jPanel1 = new javax.swing.JPanel();
          jpnLateral = new javax.swing.JPanel();
          DependenciasBtn = new javax.swing.JButton();
          EspecialidadesBtn = new javax.swing.JButton();
          HospitalesBtn = new javax.swing.JButton();
          PacientesBtn = new javax.swing.JButton();
          jPanel4 = new javax.swing.JPanel();
          jpnBotones = new javax.swing.JPanel();
          jButton1 = new javax.swing.JButton();
          jButton2 = new javax.swing.JButton();
          jButton3 = new javax.swing.JButton();
          jButton4 = new javax.swing.JButton();
          jpnSuperior = new javax.swing.JPanel();
          panelNavegacion = new javax.swing.JPanel();
          jButton5 = new javax.swing.JButton();
          jScrollPane1 = new javax.swing.JScrollPane();
          jtbTabla = new javax.swing.JTable();

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
          getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

          jPanel1.setBackground(new java.awt.Color(255, 255, 0));
          jPanel1.setPreferredSize(new java.awt.Dimension(890, 530));
          jPanel1.setRequestFocusEnabled(false);
          jPanel1.setLayout(new java.awt.BorderLayout());

          jpnLateral.setBackground(new java.awt.Color(102, 204, 255));

          DependenciasBtn.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
          DependenciasBtn.setText("Dependencias");
          DependenciasBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          DependenciasBtn.addActionListener(new java.awt.event.ActionListener()
          {
               public void actionPerformed(java.awt.event.ActionEvent evt)
               {
                    DependenciasBtnActionPerformed(evt);
               }
          });

          EspecialidadesBtn.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
          EspecialidadesBtn.setText("Especialidades");
          EspecialidadesBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          EspecialidadesBtn.addActionListener(new java.awt.event.ActionListener()
          {
               public void actionPerformed(java.awt.event.ActionEvent evt)
               {
                    EspecialidadesBtnActionPerformed(evt);
               }
          });

          HospitalesBtn.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
          HospitalesBtn.setText("Hospitales");
          HospitalesBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          HospitalesBtn.addActionListener(new java.awt.event.ActionListener()
          {
               public void actionPerformed(java.awt.event.ActionEvent evt)
               {
                    HospitalesBtnActionPerformed(evt);
               }
          });

          PacientesBtn.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
          PacientesBtn.setText("Pacientes");
          PacientesBtn.setToolTipText("");
          PacientesBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
          PacientesBtn.addActionListener(new java.awt.event.ActionListener()
          {
               public void actionPerformed(java.awt.event.ActionEvent evt)
               {
                    PacientesBtnActionPerformed(evt);
               }
          });

          javax.swing.GroupLayout jpnLateralLayout = new javax.swing.GroupLayout(jpnLateral);
          jpnLateral.setLayout(jpnLateralLayout);
          jpnLateralLayout.setHorizontalGroup(
               jpnLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jpnLateralLayout.createSequentialGroup()
                    .addContainerGap(20, Short.MAX_VALUE)
                    .addGroup(jpnLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(DependenciasBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(EspecialidadesBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(HospitalesBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(PacientesBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
          );
          jpnLateralLayout.setVerticalGroup(
               jpnLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jpnLateralLayout.createSequentialGroup()
                    .addGap(88, 88, 88)
                    .addComponent(DependenciasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(EspecialidadesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(HospitalesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(PacientesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(161, Short.MAX_VALUE))
          );

          jPanel1.add(jpnLateral, java.awt.BorderLayout.LINE_START);

          jPanel4.setBackground(new java.awt.Color(255, 102, 204));
          jPanel4.setLayout(new java.awt.BorderLayout());

          jButton1.setText("Nuevo");
          jButton1.addActionListener(new java.awt.event.ActionListener()
          {
               public void actionPerformed(java.awt.event.ActionEvent evt)
               {
                    jButton1ActionPerformed(evt);
               }
          });

          jButton2.setText("Eliminar");

          jButton3.setText("Modificar");

          jButton4.setText("Abir");
          jButton4.addActionListener(new java.awt.event.ActionListener()
          {
               public void actionPerformed(java.awt.event.ActionEvent evt)
               {
                    jButton4ActionPerformed(evt);
               }
          });

          javax.swing.GroupLayout jpnBotonesLayout = new javax.swing.GroupLayout(jpnBotones);
          jpnBotones.setLayout(jpnBotonesLayout);
          jpnBotonesLayout.setHorizontalGroup(
               jpnBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jpnBotonesLayout.createSequentialGroup()
                    .addContainerGap(274, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGap(52, 52, 52)
                    .addComponent(jButton3)
                    .addGap(45, 45, 45)
                    .addComponent(jButton2)
                    .addGap(45, 45, 45)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(44, 44, 44))
          );
          jpnBotonesLayout.setVerticalGroup(
               jpnBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnBotonesLayout.createSequentialGroup()
                    .addContainerGap(34, Short.MAX_VALUE)
                    .addGroup(jpnBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                         .addComponent(jButton1)
                         .addComponent(jButton2)
                         .addComponent(jButton3)
                         .addComponent(jButton4))
                    .addGap(16, 16, 16))
          );

          jPanel4.add(jpnBotones, java.awt.BorderLayout.PAGE_END);

          jpnSuperior.setBackground(new java.awt.Color(153, 153, 255));

          panelNavegacion.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 15));

          jButton5.setText("jButton5");
          jButton5.addActionListener(new java.awt.event.ActionListener()
          {
               public void actionPerformed(java.awt.event.ActionEvent evt)
               {
                    jButton5ActionPerformed(evt);
               }
          });

          javax.swing.GroupLayout jpnSuperiorLayout = new javax.swing.GroupLayout(jpnSuperior);
          jpnSuperior.setLayout(jpnSuperiorLayout);
          jpnSuperiorLayout.setHorizontalGroup(
               jpnSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jpnSuperiorLayout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addGroup(jpnSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(jButton5)
                         .addComponent(panelNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(89, Short.MAX_VALUE))
          );
          jpnSuperiorLayout.setVerticalGroup(
               jpnSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnSuperiorLayout.createSequentialGroup()
                    .addContainerGap(7, Short.MAX_VALUE)
                    .addComponent(jButton5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(panelNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
          );

          jPanel4.add(jpnSuperior, java.awt.BorderLayout.PAGE_START);

          jtbTabla.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
          jtbTabla.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][]
               {

               },
               new String []
               {

               }
          ));
          jtbTabla.setDragEnabled(true);
          jtbTabla.setRequestFocusEnabled(false);
          jtbTabla.setRowHeight(25);
          jtbTabla.setSelectionBackground(new java.awt.Color(153, 153, 255));
          jtbTabla.addMouseListener(new java.awt.event.MouseAdapter()
          {
               public void mouseClicked(java.awt.event.MouseEvent evt)
               {
                    jtbTablaMouseClicked(evt);
               }
          });
          jtbTabla.addKeyListener(new java.awt.event.KeyAdapter()
          {
               public void keyPressed(java.awt.event.KeyEvent evt)
               {
                    jtbTablaKeyPressed(evt);
               }
          });
          jScrollPane1.setViewportView(jtbTabla);

          jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

          jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

          getContentPane().add(jPanel1);

          pack();
     }// </editor-fold>//GEN-END:initComponents

    private void jtbTablaKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jtbTablaKeyPressed
    {//GEN-HEADEREND:event_jtbTablaKeyPressed
         // TODO add your handling code here:

    }//GEN-LAST:event_jtbTablaKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton4ActionPerformed
    {//GEN-HEADEREND:event_jButton4ActionPerformed
         // TODO add your handling code here:

    }//GEN-LAST:event_jButton4ActionPerformed

     @SuppressWarnings("unchecked")
    private void jtbTablaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jtbTablaMouseClicked
    {//GEN-HEADEREND:event_jtbTablaMouseClicked
         // TODO add your handling code here:
         if (esDobleClickIzquierdo(evt))
         {
              manejarDobleClick();
         }

    }//GEN-LAST:event_jtbTablaMouseClicked

     private boolean esDobleClickIzquierdo(MouseEvent evt)
     {
          return evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt);
     }

     private void manejarDobleClick()
     {
          String clave = getClaveSeleccionado();
          if (clave != null)
          {
               intentarEntrar(clave);
          }
     }

     private void intentarEntrar(String clave)
     {
          try
          {
               navegador.entrar(clave);
               NodoML ubicacion = multilista.buscarEnMultilista(navegador.getRutaArray());
               if (ubicacion != null)
               {
                    ubicacionActual = ubicacion;
                    actualizarVista();
                    navegador.mostrarRuta();
               }
          } catch (NavegadorException ex)
          {
               JOptionPane.showMessageDialog(this, ex.getMessage());
          }
     }

     private String getClaveSeleccionado()
     {
          String claveSeleccionado = null;
          int filaSeleccionada = jtbTabla.getSelectedRow();
          int columna = 0;
          if (filaSeleccionada != -1)
          {
               Object valor = jtbTabla.getValueAt(filaSeleccionada, columna);
               claveSeleccionado = (String) valor;
          }
          return claveSeleccionado;
     }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton5ActionPerformed
    {//GEN-HEADEREND:event_jButton5ActionPerformed
         // TODO add your handling code here:
         volver();
         actualizarVista();


    }//GEN-LAST:event_jButton5ActionPerformed

     private void DependenciasBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_DependenciasBtnActionPerformed
     {//GEN-HEADEREND:event_DependenciasBtnActionPerformed
          vtnDependencia segundoFrame = new vtnDependencia();
          segundoFrame.setVisible(true);
          this.dispose();
     }//GEN-LAST:event_DependenciasBtnActionPerformed

     private void EspecialidadesBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_EspecialidadesBtnActionPerformed
     {//GEN-HEADEREND:event_EspecialidadesBtnActionPerformed
          vtnEspecialidad segundoFrame = new vtnEspecialidad();
          segundoFrame.setVisible(true);
          this.dispose();
     }//GEN-LAST:event_EspecialidadesBtnActionPerformed

     private void HospitalesBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_HospitalesBtnActionPerformed
     {//GEN-HEADEREND:event_HospitalesBtnActionPerformed
          vtnHospitales segundoFrame = new vtnHospitales();
          segundoFrame.setVisible(true);
          this.dispose();
     }//GEN-LAST:event_HospitalesBtnActionPerformed

     private void PacientesBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_PacientesBtnActionPerformed
     {//GEN-HEADEREND:event_PacientesBtnActionPerformed
          // TODO add your handling code here:
     }//GEN-LAST:event_PacientesBtnActionPerformed
     private void volver()
     {

          if (navegador.getNivelActualIndex() == DEPENDENCIAS)
          {
               JOptionPane.showMessageDialog(this, "Estas en el nivel principal");
               return;
          }
          try
          {
               if (navegador.getNivelActualIndex() == HOSPITALES)
               {
                    ubicacionActual = multilista.getR();

               } else
               {
                    ubicacionActual = ubicacionActual.getArb();
               }
               navegador.volver();
          } catch (NavegadorException ex)
          {
               JOptionPane.showMessageDialog(this, ex.getMessage());
          }

     }

     /**
      * @param args the command line arguments
      */
     public static void main(String args[])
     {
          /* Set the Nimbus look and feel */
          //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
          /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
           */
          try
          {
               for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
               {
                    if ("Nimbus".equals(info.getName()))
                    {
                         javax.swing.UIManager.setLookAndFeel(info.getClassName());
                         break;

                    }
               }
          } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
          {
               java.util.logging.Logger.getLogger(VtnGeneral.class
                       .getName()).log(java.util.logging.Level.SEVERE, null, ex);

          }
          //</editor-fold>

          //</editor-fold>

          /* Create and display the form */
          java.awt.EventQueue.invokeLater(() ->
          {
               new VtnGeneral().setVisible(true);
          });

     }

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JButton DependenciasBtn;
     private javax.swing.JButton EspecialidadesBtn;
     private javax.swing.JButton HospitalesBtn;
     private javax.swing.JButton PacientesBtn;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JButton jButton3;
     private javax.swing.JButton jButton4;
     private javax.swing.JButton jButton5;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JPanel jPanel4;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JPanel jpnBotones;
     private javax.swing.JPanel jpnLateral;
     private javax.swing.JPanel jpnSuperior;
     private javax.swing.JTable jtbTabla;
     private javax.swing.JPanel panelNavegacion;
     // End of variables declaration//GEN-END:variables
}
