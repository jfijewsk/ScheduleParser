import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fijewskij
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        techTable = new javax.swing.JTable();
        GTCSessionCombo = new javax.swing.JComboBox<Session>();
        GTCClassNumCombo1 = new javax.swing.JComboBox<Class>();
        selectTechLbl = new javax.swing.JLabel();
        selectAllBtn = new javax.swing.JButton();
        clearAllBtn = new javax.swing.JButton();
        createDoorSignBtn = new javax.swing.JButton();
        createNameTentBtn = new javax.swing.JButton();
        createShippingBtn = new javax.swing.JButton();
        createTechReviewBtn = new javax.swing.JButton();
        selectAClassLbl = new javax.swing.JLabel();
        selectSessionLbl = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuConfig = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(885, 527));
        setResizable(false);

        techTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Technician", "Branch"
            }
        ) {

            boolean[] canEdit = new boolean [] {
                false, false
            };


            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        techTable.setColumnSelectionAllowed(true);
        techTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        techTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        techTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(techTable);
        techTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (techTable.getColumnModel().getColumnCount() > 0) {
            techTable.getColumnModel().getColumn(1).setMinWidth(100);
            techTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            techTable.getColumnModel().getColumn(1).setMaxWidth(150);
        }

        GTCClassNumCombo1.setModel(new javax.swing.DefaultComboBoxModel<>(ScheduleDataHelper.getAllGTCClasses()));
        GTCClassNumCombo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GTCClassNumCombo1ActionPerformed(evt);
            }
        });
        GTCClassNumCombo1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                GTCClassNumCombo1PropertyChange(evt);
            }
        });
        

    	Class selectedClass = (Class) GTCClassNumCombo1.getSelectedItem();
    	System.out.println("GUI selected class: " + selectedClass);
    	System.out.println("GUI selected class shows to have: " + selectedClass.getTechnicans().size() + " techs");
    	System.out.println("GUI selected class shows to have: " + selectedClass.getSessions().size() + " sessions");
    	GTCSessionCombo.setModel(new javax.swing.DefaultComboBoxModel<>(ScheduleDataHelper.getAllSessions(selectedClass)));
        GTCSessionCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GTCSessionComboActionPerformed(evt);
            }
        });

        selectTechLbl.setText("Select the specific technicians needed:");

        selectAllBtn.setText("Select All");
        selectAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllBtnActionPerformed(evt);
            }
        });

        clearAllBtn.setText("Clear All");
        clearAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAllBtnActionPerformed(evt);
            }
        });

        createDoorSignBtn.setText("Create Door Sign");
        createDoorSignBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createDoorSignBtnActionPerformed(evt);
            }
        });

        createNameTentBtn.setText("Create Name Tent");
        createNameTentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNameTentBtnActionPerformed(evt);
            }
        });

        createShippingBtn.setText("Create Shipping Form");
        createShippingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createShippingBtnActionPerformed(evt);
            }
        });

        createTechReviewBtn.setText("Create Technician Review Form");
        createTechReviewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTechReviewBtnActionPerformed(evt);
            }
        });

        selectAClassLbl.setText("Select a class:");

        selectSessionLbl.setText("Select a session:");

        jMenu1.setText("File");

        jMenuExit.setText("Exit");
        jMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Config");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuConfig.setText("Configure");
        jMenuConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuConfigActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuConfig);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(GTCClassNumCombo1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GTCSessionCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createDoorSignBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createNameTentBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(createShippingBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(createTechReviewBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(selectAClassLbl))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(selectSessionLbl)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(selectAllBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clearAllBtn))
                            .addComponent(selectTechLbl))
                        .addGap(191, 191, 191))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(selectTechLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clearAllBtn)
                            .addComponent(selectAllBtn))
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(selectAClassLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GTCClassNumCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(selectSessionLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GTCSessionCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(createDoorSignBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(createNameTentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(createShippingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(createTechReviewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void GTCSessionComboActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    // After user selects a GTC class populate the tech table and the combo box
    // for selecting the different sessions.
    private void GTCClassNumCombo1ActionPerformed(java.awt.event.ActionEvent evt) {  
    	        
    	Class selectedClass = (Class) GTCClassNumCombo1.getSelectedItem();
    	GTCSessionCombo.setModel(new javax.swing.DefaultComboBoxModel<>(ScheduleDataHelper.getAllSessions(selectedClass)));

    }                                                 

    private void selectAllBtnActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void createDoorSignBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void createNameTentBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void createShippingBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void createTechReviewBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
    }                                                   

    private void jMenuExitActionPerformed(java.awt.event.ActionEvent evt) {                                          
        System.exit(0);
    }                                         

    private void jMenuConfigActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void GTCClassNumCombo1PropertyChange(java.beans.PropertyChangeEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void clearAllBtnActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JComboBox<Class> GTCClassNumCombo1;
    private javax.swing.JComboBox<Session> GTCSessionCombo;
    private javax.swing.JButton clearAllBtn;
    private javax.swing.JButton createDoorSignBtn;
    private javax.swing.JButton createNameTentBtn;
    private javax.swing.JButton createShippingBtn;
    private javax.swing.JButton createTechReviewBtn;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuConfig;
    private javax.swing.JMenuItem jMenuExit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel selectAClassLbl;
    private javax.swing.JButton selectAllBtn;
    private javax.swing.JLabel selectSessionLbl;
    private javax.swing.JLabel selectTechLbl;
    private javax.swing.JTable techTable;
    // End of variables declaration                   
}
