package GUI;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JOptionPane;

import Backend.Properties;
import main.Main;


/**
 * Class that sets up config GUI screen for chnaging the file location of the training schedule 
 * word document. 
 * @author James
 */
public class ConfigWordDialog extends javax.swing.JDialog {

    /**
     * Creates new form ConfigPDFDialog
     */
	private Frame parentFrame;
	static Properties prop = Properties.getInstance();

    public ConfigWordDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    	parentFrame = parent;
        initComponents();
        
        this.addWindowListener((WindowListener) new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent arg0) {
            	dispose();
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        fileLocationJLabel = new javax.swing.JLabel();
        fileLocationTxtField = new javax.swing.JTextField();
        saveBtn = new javax.swing.JButton();
        browseBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        fileLocationJLabel.setText("Training Schedule Word File:");

        fileLocationTxtField.setText(prop.getScheduleFileName());

        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        browseBtn.setText("Browse");
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileLocationJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fileLocationTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(browseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(251, 251, 251))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileLocationJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileLocationTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        
        
    }// </editor-fold>                        

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {                                        
    	prop.saveScheduleFileName(fileLocationTxtField.getText());
    	JOptionPane.showMessageDialog(this,
    		    "File location saved as: " + fileLocationTxtField.getText() + ".",
    		    "Saved File Successfully",
    		    JOptionPane.PLAIN_MESSAGE);
    	
    	parentFrame.dispose();
    	
    	Main.main(null);
    }                                       

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	dispose();

    }                                         

    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	
    	
    	FileDialog fd = new FileDialog(this, "Open", FileDialog.LOAD); 
    	fd.show();    	

    	if (fd.getFile() == null) {
    		return;
    	}
    	
    	else {
    	String fileName = new File(fd.getFile()).getName();
    	String directory = fd.getDirectory();
    	fileLocationTxtField.setText(directory + fileName);
    	
    	System.out.println("path " + directory + fileName);

    	}
    	

    }                                    
    



    // Variables declaration - do not modify                     
    private javax.swing.JButton browseBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel fileLocationJLabel;
    private javax.swing.JTextField fileLocationTxtField;
    private javax.swing.JButton saveBtn;
    // End of variables declaration                   
}
