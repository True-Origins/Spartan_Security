/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author migue
 */
public class V_Puesto extends javax.swing.JInternalFrame {

    /**
     * Creates new form Puesto
     */
    public V_Puesto() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSueldo = new javax.swing.JTextField();
        txtPuesto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_id = new javax.swing.JLabel();
        lbl_accion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpuesto = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbltituloprincipal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_guardar = new rojeru_san.RSButton();
        btncancelar = new rojeru_san.RSButton();
        btnactualizar = new rojeru_san.RSButton();
        btneliminar = new rojeru_san.RSButton();

        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txtSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 205, 25));
        getContentPane().add(txtPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 205, 25));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Puesto");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Sueldo");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        lbl_id.setText("ID");
        getContentPane().add(lbl_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 21, -1));

        lbl_accion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_accion.setText("Nuevo puesto");
        getContentPane().add(lbl_accion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 220, -1));

        tblpuesto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Puestos", "Sueldo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblpuesto);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 640, 420));

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));

        lbltituloprincipal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbltituloprincipal.setForeground(new java.awt.Color(255, 255, 255));
        lbltituloprincipal.setText("Puestos");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Buscar puesto");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Puesto.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltituloprincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(766, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(lbltituloprincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 60));

        btn_guardar.setText("Guardar");
        getContentPane().add(btn_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 170, -1));

        btncancelar.setBackground(new java.awt.Color(249, 23, 6));
        btncancelar.setText("Cancelar");
        btncancelar.setColorHover(new java.awt.Color(255, 59, 59));
        getContentPane().add(btncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 170, -1));

        btnactualizar.setText("Actualizar");
        getContentPane().add(btnactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 510, 170, -1));

        btneliminar.setBackground(new java.awt.Color(249, 23, 6));
        btneliminar.setText("Eliminar");
        btneliminar.setColorHover(new java.awt.Color(255, 59, 59));
        getContentPane().add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 510, 170, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public rojeru_san.RSButton btn_guardar;
    public rojeru_san.RSButton btnactualizar;
    public rojeru_san.RSButton btncancelar;
    public rojeru_san.RSButton btneliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lbl_accion;
    public javax.swing.JLabel lbl_id;
    public javax.swing.JLabel lbltituloprincipal;
    public javax.swing.JTable tblpuesto;
    public javax.swing.JTextField txtPuesto;
    public javax.swing.JTextField txtSueldo;
    public javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
