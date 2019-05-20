import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author James
 */
/**
 * @author fijewskij
 *
 */
public class ConfigDoorSignDialog extends javax.swing.JDialog {

	Properties prop = Properties.getInstance();
	private Frame parentFrame;

	public ConfigDoorSignDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		parentFrame = parent;
		initComponents();

		this.addWindowListener((WindowListener) new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				parent.dispose();
				Main.main(null);
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

		doorSignJLabel = new javax.swing.JLabel();
		doorSignTextField = new javax.swing.JTextField();
		saveBtn = new javax.swing.JButton();
		doorSignBrowseBtn = new javax.swing.JButton();
		cancelBtn = new javax.swing.JButton();
		session1DescriptionJlabel = new javax.swing.JLabel();
		session1DescriptionTextField = new javax.swing.JTextField();
		session2DescriptionJlabel = new javax.swing.JLabel();
		session2DescriptionTextField = new javax.swing.JTextField();
		session3DescriptionJlabel = new javax.swing.JLabel();
		session3DescriptionTextField = new javax.swing.JTextField();
		session4DescriptionJlabel = new javax.swing.JLabel();
		session4DescriptionTextField = new javax.swing.JTextField();
		trainingRoomsJlabel = new javax.swing.JLabel();
		trainingRoomsTextField = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		String[] currentSettings = prop.getDoorSignSettings();

		doorSignJLabel.setText("Door Sign PDF File:");
		session1DescriptionJlabel.setText("Session 1 Description:");
		session2DescriptionJlabel.setText("Session 2 Description:");
		session3DescriptionJlabel.setText("Session 3 Description:");
		session4DescriptionJlabel.setText("Session 4 Description:");
		trainingRoomsJlabel.setText("Training Rooms:");


		doorSignTextField.setText(currentSettings[0]);
		session1DescriptionTextField.setText(currentSettings[1]);
		session2DescriptionTextField.setText(currentSettings[2]);
		session3DescriptionTextField.setText(currentSettings[3]);
		session4DescriptionTextField.setText(currentSettings[4]);       
		trainingRoomsTextField.setText(currentSettings[5]);

		saveBtn.setText("Save");
		saveBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveBtnActionPerformed(evt);
			}
		});

		doorSignBrowseBtn.setText("Browse");
		doorSignBrowseBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doorSignBrowseBtnActionPerformed(evt);
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
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(trainingRoomsJlabel)
								.addComponent(session4DescriptionJlabel)
								.addComponent(session3DescriptionJlabel)
								.addComponent(session2DescriptionJlabel)
								.addComponent(session1DescriptionJlabel)
								.addComponent(doorSignJLabel))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(doorSignTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
								.addComponent(session1DescriptionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
								.addComponent(session2DescriptionTextField)
								.addComponent(session3DescriptionTextField)
								.addComponent(session4DescriptionTextField)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(94, 94, 94)
										.addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(157, 157, 157))
								.addComponent(trainingRoomsTextField))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(doorSignBrowseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(22, 22, 22))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(31, 31, 31)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(doorSignJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(doorSignTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(doorSignBrowseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(session1DescriptionJlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(session1DescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(session2DescriptionJlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(session2DescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(session3DescriptionJlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(session3DescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(session4DescriptionJlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(session4DescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
								.addGroup(layout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(trainingRoomsJlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(trainingRoomsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				);

		pack();
	}// </editor-fold>                        

	private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {       
		String[] values = new String[6];
		values[0] = doorSignTextField.getText();
		values[1] = session1DescriptionTextField.getText();
		values[2] = session2DescriptionTextField.getText();
		values[3] = session3DescriptionTextField.getText();
		values[4] = session4DescriptionTextField.getText();
		values[5] = trainingRoomsTextField.getText();

		prop.saveDoorSign(values);
		JOptionPane.showMessageDialog(this,
				"Settings changed!");

		parentFrame.dispose();

		Main.main(null);
	}                                       

	private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
		parentFrame.dispose();
		Main.main(null);

	}                                              

	private void doorSignBrowseBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                  
		// TODO add your handling code here:
	}                                                 

	private void session1DescriptionBrowseBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                             
		// TODO add your handling code here:
	}                                                            

	private void session2DescriptionBrowseBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                             
		// TODO add your handling code here:
	}                                                            

	private void session3DescriptionBrowseBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                             
		// TODO add your handling code here:
	}                                                            

	private void session4DescriptionBrowseBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                             
		// TODO add your handling code here:
	}                                                            

	private void trainingRoomsBrowseBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                       
		// TODO add your handling code here:
	}                                                      

	// Variables declaration - do not modify                     
	private javax.swing.JButton cancelBtn;
	private javax.swing.JButton doorSignBrowseBtn;
	private javax.swing.JLabel doorSignJLabel;
	private javax.swing.JTextField doorSignTextField;
	private javax.swing.JButton saveBtn;
	private javax.swing.JLabel session1DescriptionJlabel;
	private javax.swing.JTextField session1DescriptionTextField;
	private javax.swing.JLabel session2DescriptionJlabel;
	private javax.swing.JTextField session2DescriptionTextField;
	private javax.swing.JLabel session3DescriptionJlabel;
	private javax.swing.JTextField session3DescriptionTextField;
	private javax.swing.JLabel session4DescriptionJlabel;
	private javax.swing.JTextField session4DescriptionTextField;
	private javax.swing.JLabel trainingRoomsJlabel;
	private javax.swing.JTextField trainingRoomsTextField;
	// End of variables declaration                 


	public static void askTrainingRoom() {

		Properties prop = Properties.getInstance();

		String[] trainingRoomOptions = prop.getAllTrainingRooms();

		JComboBox trainingRoom = new JComboBox(trainingRoomOptions);

		trainingRoom.setEditable(true);

		int selection = JOptionPane.showConfirmDialog(
				null
				, "Please select the training room:"
				, "Selection : "
				, JOptionPane.OK_CANCEL_OPTION
				, JOptionPane.INFORMATION_MESSAGE);
		System.out.println("I be written" +
				" after you close, the JOptionPane");      
		if (selection == JOptionPane.OK_OPTION)
		{
			// Code to use when OK is PRESSED.
			System.out.println("Selected Option is OK : " + selection);
		}
		else if (selection == JOptionPane.CANCEL_OPTION)
		{
			// Code to use when CANCEL is PRESSED.
			System.out.println("Selected Option Is CANCEL : " + selection);
		}
	}          
	

	/*
	 * String[] date=
	 * {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17",
	 * "18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	 * String[] month= {"1","2","3","4","5","6","7","8","9","10","11","12"};
	 * String[] year={"2016","2017","2018","2019","2020"}; JComboBox jcd = new
	 * JComboBox(date); JComboBox jcm = new JComboBox(month); JComboBox jcy = new
	 * JComboBox(year);
	 * 
	 * jcd.setEditable(true); jcm.setEditable(true); jcy.setEditable(true);
	 * 
	 * //create a JOptionPane Object[] options = new Object[] {}; JOptionPane jop =
	 * new JOptionPane("Please Select", JOptionPane.QUESTION_MESSAGE,
	 * JOptionPane.DEFAULT_OPTION, null,options, null);
	 * 
	 * //add combos to JOptionPane jop.add(jcd); jop.add(jcm); jop.add(jcy);
	 * 
	 * //create a JDialog and add JOptionPane to it JDialog diag = new JDialog();
	 * diag.getContentPane().add(jop); diag.pack(); diag.setVisible(true);
	 */




}



