/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edd_hospital_.vista;

import javax.swing.table.DefaultTableModel;
import edd_hospital_.multi_lista.*;

import edd_hospital_.modelo.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author saulo
 */
public class VtnGeneral extends javax.swing.JFrame
{

    Navegador navegador = new Navegador();
    MultiListaDL multilista = new MultiListaDL();
    NodoML ubicacionActual = multilista.getR();

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
        jtbTabla.getTableHeader().setReorderingAllowed(false);
        mostrarDatosEnTabla(ubicacionActual);

    }

    @SuppressWarnings("unchecked")
    private void mostrarDatosEnTabla(NodoML<Object> r)
    {
        if (r == null)
        {
            JOptionPane.showMessageDialog(this, "No hay elementos");
            return;
        }

        modeloTabla.setRowCount(0); // Limpiar tabla
        resetearModeloTabla();
        switch (navegador.ubicacionActual())
        {
            case "Dependencias" ->
            {
                colocarModeloDependencia();
                mostrarDependencias(r);
            }
            case "Hospitales" ->
            {
                colocarModeloHospitales();
                mostrarHospitales(r);
            }
            case "Especialidades" ->
            {
                colocarModeloEspecialidad();
                mostrarEspecialidades(r);
            }
            case "Pacientes" ->
            {
                colocarModeloPacientes();
                mostrarPacientes(r);
            }
            default ->
                System.out.println("Ubicación desconocida");
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jpnLateral = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jpnBotones = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jpnSuperior = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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

        javax.swing.GroupLayout jpnLateralLayout = new javax.swing.GroupLayout(jpnLateral);
        jpnLateral.setLayout(jpnLateralLayout);
        jpnLateralLayout.setHorizontalGroup(
            jpnLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 181, Short.MAX_VALUE)
        );
        jpnLateralLayout.setVerticalGroup(
            jpnLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
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
                .addContainerGap(247, Short.MAX_VALUE)
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

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 15));

        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1);

        jLabel2.setText("jLabel2");
        jPanel2.add(jLabel2);

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
                .addGroup(jpnSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnSuperiorLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnSuperiorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jpnSuperiorLayout.setVerticalGroup(
            jpnSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnSuperiorLayout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.add(jpnSuperior, java.awt.BorderLayout.PAGE_START);

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
        int filaSeleccionada = jtbTabla.getSelectedRow();
        if (filaSeleccionada != -1)
        {

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    @SuppressWarnings("unchecked")
    private void jtbTablaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jtbTablaMouseClicked
    {//GEN-HEADEREND:event_jtbTablaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt))
        {
            int filaSeleccionada = jtbTabla.getSelectedRow();
            int columna = 0;
            if (filaSeleccionada != -1 && ubicacionActual.getAbj() != null)
            {
                Object valor = jtbTabla.getValueAt(filaSeleccionada, columna);
                String clave = (String) valor;
                NodoML siguienteUbicacion = multilista.busca(ubicacionActual, clave);
                if (siguienteUbicacion != null)
                {
                    ubicacionActual = siguienteUbicacion.getAbj();
                    navegador.entrar();
                    mostrarDatosEnTabla(ubicacionActual);

                }

            }
        }
    }//GEN-LAST:event_jtbTablaMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton5ActionPerformed
    {//GEN-HEADEREND:event_jButton5ActionPerformed
        // TODO add your handling code here:
        navegador.volver();
        ubicacionActual = ubicacionActual.getArb();
        mostrarDatosEnTabla(ubicacionActual);

    }//GEN-LAST:event_jButton5ActionPerformed

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
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(VtnGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(VtnGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(VtnGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(VtnGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new VtnGeneral().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpnBotones;
    private javax.swing.JPanel jpnLateral;
    private javax.swing.JPanel jpnSuperior;
    private javax.swing.JTable jtbTabla;
    // End of variables declaration//GEN-END:variables
}
