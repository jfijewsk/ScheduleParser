import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author fijewskij
 */
public class GUI extends javax.swing.JFrame {

	ArrayList<Integer> selectedRows;
	ListSelectionModel model;
	
	Properties prop = Properties.getInstance();
	
	String fileName = prop.getScheduleFileName();
	String doorSignFileName = prop.getDoorSignFileName();
	
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
        techTable = new JTable();
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
        jMenuConfigWord = new javax.swing.JMenuItem();
        jMenuConfigPDF = new javax.swing.JMenuItem();

        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1185, 527));
        setResizable(false);

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
        
        MouseListener[] listeners = techTable.getMouseListeners();
        for (MouseListener l : listeners)
        {
        	techTable.removeMouseListener(l);
        }
        
        MouseMotionListener[] moveListeners = techTable.getMouseMotionListeners();
		for (MouseMotionListener l : moveListeners) {
        	techTable.removeMouseMotionListener(l);
        }
        

    	Class selectedClass = (Class) GTCClassNumCombo1.getSelectedItem();
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

        jMenuConfigWord.setText("Configure Schedule File Location");
        jMenuConfigWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuConfigWordActionPerformed(evt);
            }
        });
        
        jMenuConfigPDF.setText("Configure PDF Fields");
        jMenuConfigPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuConfigPDFActionPerformed(evt);
            }
        });

    	model = techTable.getSelectionModel();
        
        techTable.addMouseListener( new MouseAdapter()
        {
        	
        	
        	@Override
        	public void mouseDragged(MouseEvent e) {
        		return;

        	}
        	
        	@Override
            public void mouseReleased(MouseEvent e)
            {
                int row = techTable.rowAtPoint( e.getPoint() );
        		
            	clickedRow(row);
              
            }
        });
        
        refreshTable();
        
        jMenu2.add(jMenuConfigWord);
        jMenu2.add(jMenuConfigPDF);

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
                        .addGap(425, 425, 425))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    	// Deselect currently selected
    	techTable.clearSelection();
    	
    	// Populate the session combo
    	Class selectedClass = (Class) GTCClassNumCombo1.getSelectedItem();
    	GTCSessionCombo.setModel(new javax.swing.DefaultComboBoxModel<>(ScheduleDataHelper.getAllSessions(selectedClass)));
    	
    	// Populate the tech table
    	refreshTable();
    }                                                 

    private void selectAllBtnActionPerformed(java.awt.event.ActionEvent evt) {       
        // Reselect all selected rows.
    	Class selectedClass = (Class) GTCClassNumCombo1.getSelectedItem();
    	int numOfTechs = selectedClass.getTechnicans().size();
    	
        for (int i = 0; i < numOfTechs; i++) {
        	if (!selectedRows.contains(i)) {
        		selectedRows.add((Integer)i);
        	}
        	}
        
    	model.addSelectionInterval(0, selectedClass.getTechnicans().size());

    }                                            

    private void createDoorSignBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                  
		PDFEditor.fillDoorSign(doorSignFileName, (Class)GTCClassNumCombo1.getSelectedItem());


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

    private void jMenuConfigWordActionPerformed(java.awt.event.ActionEvent evt) { 
    	ConfigWordDialog wordDiag = new ConfigWordDialog(this, rootPaneCheckingEnabled);
    	wordDiag.setLocationRelativeTo(this);
    	wordDiag.setVisible(true);
    	System.out.println("Got here");
    }     
    
    private void jMenuConfigPDFActionPerformed(java.awt.event.ActionEvent evt) {                                            
    }           

    private void GTCClassNumCombo1PropertyChange(java.beans.PropertyChangeEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void clearAllBtnActionPerformed(java.awt.event.ActionEvent evt) {                                            
    	Class selectedClass = (Class) GTCClassNumCombo1.getSelectedItem();
    	
    	selectedRows.clear();
        
    	model.removeSelectionInterval(0, selectedClass.getTechnicans().size());
    }        
    
    private void techTableAction() {                                            
        // Get the clicked row


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
    private javax.swing.JMenuItem jMenuConfigWord;
    private javax.swing.JMenuItem jMenuConfigPDF;
    private javax.swing.JMenuItem jMenuExit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel selectAClassLbl;
    private javax.swing.JButton selectAllBtn;
    private javax.swing.JLabel selectSessionLbl;
    private javax.swing.JLabel selectTechLbl;
    private javax.swing.JTable techTable;
    // End of variables declaration                   
    
    private Object[][] getTechData() {
    	Class selectedClass = (Class) GTCClassNumCombo1.getSelectedItem();
    	int numOfTechs = selectedClass.getTechnicans().size();
    	Object[][] data = new Object[numOfTechs][3];
    	
    	// Populate the tech table
    	for (int i = 0; i < numOfTechs; i++) {
    		data[i][0] = selectedClass.getTechnicans().get(i).getName();
    		data[i][1] = selectedClass.getTechnicans().get(i).getBranch();
    		data[i][2] = selectedClass.getTechnicans().get(i).getStartDate();


    	}
    	
    	return data;
    }
    
    private void refreshTable() {
    	
    	Object data[][] = getTechData();
    	
        techTable.setModel(new javax.swing.table.DefaultTableModel(
        		data,
                new String [] {
                    "Technician", "Branch", "Misc"
                }
            ) {

                boolean[] canEdit = new boolean [] {
                    false, false
                };


                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [1];
                }
            });
        
        		techTable.setFocusable(false);
//        		techTable.setDefaultRenderer( Object.class, new BorderLessTableCellRenderer() );
    	        techTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    	        techTable.getTableHeader().setReorderingAllowed(false);
    	        jScrollPane1.setViewportView(techTable);
    	        if (techTable.getColumnModel().getColumnCount() > 0) {  
    	            techTable.getColumnModel().getColumn(0).setMinWidth(300);
    	            techTable.getColumnModel().getColumn(0).setPreferredWidth(350);
    	            techTable.getColumnModel().getColumn(0).setMaxWidth(400);
    	            techTable.getColumnModel().getColumn(1).setMinWidth(200);
    	            techTable.getColumnModel().getColumn(1).setPreferredWidth(200);
    	            techTable.getColumnModel().getColumn(1).setMaxWidth(250);
    	            techTable.setFont(new Font("TimesRoman", Font.PLAIN, 18));
    	            techTable.setRowHeight(35); 
    	            techTable.setRowSelectionAllowed(true);
    	            techTable.setColumnSelectionAllowed(false);
    	            techTable.setDragEnabled(false);

            }
    	        // When the table is refreshed, refresh selected rows too
    	    	selectedRows = new ArrayList<Integer>();
    	    	
    	    	// Select all techs at the init of the table by defualt
    	    	Class selectedClass = (Class) GTCClassNumCombo1.getSelectedItem();
    	    	int numOfTechs = selectedClass.getTechnicans().size();
    	    	
    	        for (int i = 0; i < numOfTechs; i++) {
    	        	if (!selectedRows.contains(i)) {
    	        		selectedRows.add((Integer)i);
    	        	}
    	        	}
    	        
    	    	model.addSelectionInterval(0, selectedClass.getTechnicans().size());
    	    	

    	        
    }
    
    public void clickedRow(int row) {
		
    	model.clearSelection();
    	        
        System.out.println(row);

       
        
        if (selectedRows.contains(row)) {
        	System.out.println(row + " already selected!");               	
        	selectedRows.remove((Object) row);                	

        }
        
        else {
        	selectedRows.add(row);
           	model.addSelectionInterval(row, row);

        }               
        
        // Reselect all selected rows.
        for (int x : selectedRows) {
        	model.addSelectionInterval(x, x);;
        }
    }
    


    

}


