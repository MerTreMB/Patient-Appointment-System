import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SettingsFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblHospitalName;
	private JLabel lblNameSurName;
	private JLabel lblText;
	private JLabel lblTime;
	private JLabel lblEmpty;
	private JLabel lblEmpty2;
	private JLabel lblField;
	private JLabel lblPassword;
	private JTextField tfField;
	private JTextField tfPassword;
	private JButton btnActiveEmployees;
	private JButton btnAddNewEmployee;
	private JButton btnSettings;
	private JButton btnContinueDoctor;
	private JButton btnAddField;
	private JButton btnDeleteField;
	private JButton btnAddPass;
	private JButton btnDeletePass;
	private JButton btnRefresh;
	private JButton btnLogout;
	private JPanel pnlTop;
	private JPanel pnlLeftSide;
	private JPanel pnlCenter;
	private JPanel pnlCenterTop;
	private JPanel pnlCenterCenter;
	private JPanel pnlCenterBottom;
	private JPanel pnlBottom;
	private JPanel pnlFields;
	private JPanel pnlFieldsButton;
	private JPanel pnlNotAllowedPasswords;
	private JPanel pnlNotAllowedPasswordsButton;
	private JTable tblFields;
	private JTable tblNotAllowedPasswords;
	private BorderLayout frameBorderLayout;
	private FlowLayout pnlTopFlowLayout;
	private GridLayout leftSideGridLayout;
	private BorderLayout pnlCenterBorderLayout;
	private FlowLayout pnlCenterTopFlowLayout;
	private GridLayout pnlCenterCenterGridLayout;
	private FlowLayout pnlCenterBottomFlowLayout;
	private FlowLayout pnlBottomFlowLayout;
	private BorderLayout pnlFieldsBorderLayout;
	private FlowLayout pnlFieldsButtonFlowLayout;
	private BorderLayout pnlNotAllowedPasswordsBorderLayout;
	private FlowLayout pnlNotAllowedPasswordsButtonFlowLayout;
	private DefaultTableModel fieldModel;
	private DefaultTableModel passwordModel;
	private ArrayList<String[]> fields;
	private ArrayList<String[]> notAllowedPasswords;
	private String docTitle = null;
	private String name = null;
	private String surName = null;
	@SuppressWarnings("unused")
	private String docID;
	private String tcKimlik;
	
	public SettingsFrame(final LoginForm lf, final ActiveEmployeesFrame ae, final AddNewEmployeeFrame an, String docID, String tcKimlik) {
		this.docID = docID;
		this.tcKimlik = tcKimlik;
		
		Container cp = getContentPane();
		frameBorderLayout = new BorderLayout();
		setLayout(frameBorderLayout);
		
		pnlTop = new JPanel();
		pnlTopFlowLayout = new FlowLayout (FlowLayout.LEFT, 3, 3);
		pnlTop.setLayout(pnlTopFlowLayout);
		
		lblHospitalName = new JLabel("  Hospital", SwingConstants.LEFT);
		Font title = new Font("Courier", Font.BOLD, 25);
		lblHospitalName.setForeground(Color.WHITE);
		lblHospitalName.setFont(title);
		lblHospitalName.setBackground(Color.decode("#375EEB"));
		pnlTop.add(lblHospitalName);
		
		BufferedReader br = null;
		FileReader fr = null;
		
		try {
			fr = new FileReader("doctors.txt");
			br = new BufferedReader(fr);
			String line;
			String[] strArray;
			
			while ((line = br.readLine()) != null) {
				strArray = line.split(",");
				
				if (strArray.length == 7 && tcKimlik.equals(strArray[4])) {
					docTitle = strArray[1];
					name = strArray[2];
					surName = strArray[3];
					break;
				}
			}
		}
		
		catch (IOException ex) {
			System.out.println("There was a problem reading the file.");
	        JOptionPane.showMessageDialog(null, "There was a problem reading the file.");
		}
		
		finally {
			
			if (fr != null) {
				
				try {
					fr.close();
				}
				
				catch (IOException exp) {
					System.out.println("The file read but not closed.");
				}
			}
		}

		lblNameSurName = new JLabel(docTitle + " " + name + " " + surName, SwingConstants.RIGHT);
		lblNameSurName.setForeground(Color.WHITE);
		lblNameSurName.setPreferredSize(new Dimension (800, 50));
		lblNameSurName.setOpaque(true);
		lblNameSurName.setBackground(Color.decode("#375EEB"));
		pnlTop.add(lblNameSurName);
		
		pnlTop.setBackground(Color.decode("#375EEB"));
		cp.add(pnlTop, BorderLayout.NORTH);
		
		pnlLeftSide = new JPanel();
		leftSideGridLayout = new GridLayout(8, 1, 3, 3);
		pnlLeftSide.setLayout(leftSideGridLayout);
		
		btnActiveEmployees = new JButton("Active Employees");
		BtnActiveEmployeesListener activeEmployeesListener = new BtnActiveEmployeesListener();
		btnActiveEmployees.addActionListener(activeEmployeesListener);
		pnlLeftSide.add(btnActiveEmployees);
		
		btnAddNewEmployee = new JButton("Add New Employee");
		BtnAddNewEmployeeListener addNewEmployeeListener = new BtnAddNewEmployeeListener();
		btnAddNewEmployee.addActionListener(addNewEmployeeListener);
		pnlLeftSide.add(btnAddNewEmployee);
		
		btnSettings = new JButton("Settings");
		BtnSettingsListener settingsListener = new BtnSettingsListener();
		btnSettings.addActionListener(settingsListener);
		pnlLeftSide.add(btnSettings);
		
		btnContinueDoctor = new JButton("Continue as a Doctor");
		BtnContinueDoctorListener continueDoctorListener = new BtnContinueDoctorListener();
		btnContinueDoctor.addActionListener(continueDoctorListener);
		pnlLeftSide.add(btnContinueDoctor);
		
		pnlLeftSide.setBackground(Color.decode("#1640d9"));
		cp.add(pnlLeftSide, BorderLayout.WEST);
		
		pnlCenter = new JPanel();
		pnlCenterBorderLayout = new BorderLayout();
		pnlCenter.setLayout(pnlCenterBorderLayout);
		
		pnlCenterTop = new JPanel();
		pnlCenterTopFlowLayout = new FlowLayout(3, 3, 3);
		pnlCenterTop.setLayout(pnlCenterTopFlowLayout);
		lblText = new JLabel(" Settings", SwingConstants.LEFT);
		lblText.setFont(new Font("", Font.BOLD, 25));
		pnlCenterTop.add(lblText);
		
	    LocalDateTime date = LocalDateTime.now();
	    DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    String strDate = date.format(formattedDate);
	    
	    lblTime = new JLabel(strDate + "  ", SwingConstants.RIGHT);
	    lblTime.setPreferredSize(new Dimension (680, 50));
	    lblTime.setOpaque(true);
	    pnlCenterTop.add(lblTime);
	    pnlCenter.add(pnlCenterTop, BorderLayout.NORTH);

		lblEmpty = new JLabel("                                   ");
		pnlCenter.add(lblEmpty, BorderLayout.EAST);
		   
		lblEmpty2 = new JLabel("                                  ");
		pnlCenter.add(lblEmpty2, BorderLayout.WEST);
		
	    pnlCenterCenter = new JPanel();
	    pnlCenterCenterGridLayout = new GridLayout(1, 2, 3, 3);
	    pnlCenterCenter.setLayout(pnlCenterCenterGridLayout);
	    
	    pnlFields = new JPanel();
	    pnlFieldsBorderLayout = new BorderLayout();
	    pnlFields.setLayout(pnlFieldsBorderLayout);
	    
	    lblField = new JLabel("FIELDS LIST", SwingConstants.CENTER);
	    lblField.setFont(new Font("Courier", Font.BOLD, 15));
	    pnlFields.add(lblField, BorderLayout.NORTH);
	    
	    String[] columnNames = {"ID", "Fields"};
	    fields = listFields("fields.txt");
	    fieldModel = new DefaultTableModel(fields.toArray(new Object[0][0]), columnNames);
	    tblFields = new JTable(fieldModel);
	    tblFields.getColumnModel().getColumn(0).setPreferredWidth(10);
	    tblFields.getColumnModel().getColumn(1).setPreferredWidth(90);
	    tblFields.setDefaultEditor(Object.class, null);
	    tblFields.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    JScrollPane scrollPane = new JScrollPane(tblFields);
	    pnlFields.add(scrollPane);
 
	    pnlFieldsButton = new JPanel();
	    pnlFieldsButtonFlowLayout = new FlowLayout(FlowLayout.CENTER , -5, 20);
	    pnlFieldsButton.setLayout(pnlFieldsButtonFlowLayout);
	    
	    tfField = new JTextField(8);
	    pnlFieldsButton.add(tfField);

	    btnAddField = new JButton("Add");
	    BtnAddFieldListener btnAddFieldListener = new BtnAddFieldListener();
	    btnAddField.addActionListener(btnAddFieldListener);
	    pnlFieldsButton.add(btnAddField);

	    btnDeleteField = new JButton("Delete");
	    BtnDeleteFieldListener btnDeleteFieldListener = new BtnDeleteFieldListener();
	    btnDeleteField.addActionListener(btnDeleteFieldListener);
	    pnlFieldsButton.add(btnDeleteField);
	    
	    pnlFields.add(pnlFieldsButton, BorderLayout.SOUTH);
	    pnlCenterCenter.add(pnlFields);
 
	    pnlNotAllowedPasswords = new JPanel();
	    pnlNotAllowedPasswordsBorderLayout = new BorderLayout();
	    pnlNotAllowedPasswords.setLayout(pnlNotAllowedPasswordsBorderLayout);
	    
	    lblPassword = new JLabel("NOT ALLOWED PASSWORD LIST", SwingConstants.CENTER);
	    lblPassword.setFont(new Font("Courier", Font.BOLD, 15));
	    pnlNotAllowedPasswords.add(lblPassword, BorderLayout.NORTH);
	    
	    String[] columnNames2 = {"ID", "Passwords"};
	    notAllowedPasswords = listNotAllowedPasswords("notAllowedPasswords.txt");
	    passwordModel = new DefaultTableModel(notAllowedPasswords.toArray(new Object[0][0]), columnNames2);
	    tblNotAllowedPasswords = new JTable(passwordModel);
	    tblNotAllowedPasswords.getColumnModel().getColumn(0).setPreferredWidth(10);
	    tblNotAllowedPasswords.getColumnModel().getColumn(1).setPreferredWidth(90);
	    tblNotAllowedPasswords.setDefaultEditor(Object.class, null);
	    tblNotAllowedPasswords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    JScrollPane scrollPane2 = new JScrollPane(tblNotAllowedPasswords);
	    pnlNotAllowedPasswords.add(scrollPane2);

	    pnlNotAllowedPasswordsButton = new JPanel();
	    pnlNotAllowedPasswordsButtonFlowLayout = new FlowLayout(FlowLayout.CENTER, -5, 20);
	    pnlNotAllowedPasswordsButton.setLayout(pnlNotAllowedPasswordsButtonFlowLayout);
	    
	    tfPassword = new JTextField(8);
	    pnlNotAllowedPasswordsButton.add(tfPassword);
	    
	    btnAddPass = new JButton("Add");
	    BtnAddNAPassListener btnAddNAPassListener = new BtnAddNAPassListener();
	    btnAddPass.addActionListener(btnAddNAPassListener);
	    pnlNotAllowedPasswordsButton.add(btnAddPass);
	    
	    btnDeletePass = new JButton("Delete");
	    BtnDeleteNAPassListener btnDeleteNAPassListener = new BtnDeleteNAPassListener();
	    btnDeletePass.addActionListener(btnDeleteNAPassListener);
	    pnlNotAllowedPasswordsButton.add(btnDeletePass);
	    
	    pnlNotAllowedPasswords.add(pnlNotAllowedPasswordsButton, BorderLayout.SOUTH);
	    
	    pnlCenterCenter.add(pnlNotAllowedPasswords);
	    pnlCenter.add(pnlCenterCenter);
	     
	    pnlCenterBottom = new JPanel();
	    pnlCenterBottomFlowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
	    pnlCenterBottom.setLayout(pnlCenterBottomFlowLayout);
	    
	    btnRefresh = new JButton("Refresh");
	    BtnRefreshListener btnRefreshListener = new BtnRefreshListener();
	    btnRefresh.addActionListener(btnRefreshListener);
	    pnlCenterBottom.add(btnRefresh);
	    pnlCenter.add(pnlCenterBottom, BorderLayout.SOUTH);
	    
	    cp.add(pnlCenter, BorderLayout.CENTER);

		pnlBottom = new JPanel();
		pnlBottomFlowLayout = new FlowLayout(FlowLayout.RIGHT, 3, 3);
		pnlBottom.setLayout(pnlBottomFlowLayout);
			
		btnLogout = new JButton("Logout");
		BtnLogoutListener logOutListener = new BtnLogoutListener(lf);
		btnLogout.addActionListener(logOutListener);
		pnlBottom.add(btnLogout);
		
		pnlBottom.setBackground(Color.decode("#375EEB"));
		cp.add(pnlBottom, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Patient Appointment System   |   Active Employees");
        setResizable(false);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class BtnActiveEmployeesListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			dispose();
			new ActiveEmployeesFrame(null, null, null, null, tcKimlik);
		}
	}
	
	private class BtnAddNewEmployeeListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			dispose();
			new AddNewEmployeeFrame(null, null, null, null, tcKimlik);
		}
	}
	
	private class BtnSettingsListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
		}
	}
	
	private class BtnContinueDoctorListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			dispose();
			new DoctorFrame(null, null, null, tcKimlik);
		}
	}
	
	private ArrayList<String[]> listFields(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
		fields = new ArrayList<>();
		String line;
		String[] row;
		
		try {
	        File fieldsFile = new File(fileName);
	        
	        if (!fieldsFile.exists()) {
	        	fieldsFile.createNewFile();
	        }
	        
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			while ((line = br.readLine()) != null) {
				row = line.split(",");
				fields.add(row);
			}
		}
		
		catch (IOException exp){
			System.out.println("There was a problem reading the file.");
	        JOptionPane.showMessageDialog(null, "There was a problem reading the file.");
        }
		
		finally {
			
			if (fr != null) {
				
				try {
					fr.close();
				}
				
				catch (IOException exp) {
					System.out.println("The file read but not closed.");
				}
			}
		}
		return fields;
	}
	
	private ArrayList<String[]> listNotAllowedPasswords(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
		notAllowedPasswords = new ArrayList<>();
		String line;
		String[] row;
		
		try {
	        File notAllowedPasswordsFile = new File(fileName);
	        
	        if (!notAllowedPasswordsFile.exists()) {
	        	notAllowedPasswordsFile.createNewFile();
	        }
	        
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			while ((line = br.readLine()) != null) {
				row = line.split(",");
				notAllowedPasswords.add(row);
			}
		}
		
		catch (IOException exp){
			System.out.println("There was a problem reading the file.");
	        JOptionPane.showMessageDialog(null, "There was a problem reading the file.");
        }
		
		finally {
			
			if (fr != null) {
				
				try {
					fr.close();
				}
				
				catch (IOException exp) {
					System.out.println("The file read but not closed.");
				}
			}
		}
		return notAllowedPasswords;
	}
	
	private void deleteField(String fileName, int selectedIndex) {
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			ArrayList<String> lines = new ArrayList<>();
			String line;
			
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			
			br.close();
			
			lines.remove(selectedIndex);
			fw = new FileWriter(fileName);
			for (String updatedLine : lines) {
				fw.write(updatedLine + "\n");
			}
		}

		
		catch (IOException exp){
			System.out.println("There was a problem writing the file.");
	        JOptionPane.showMessageDialog(null, "There was a problem writing the file.");
        }
		
		finally {
			
			if (br != null) {
				
				try {
					br.close();
				}
				
				catch (IOException exp) {
					System.out.println("The file read but not closed.");
				}
			}
			
			if (fw!= null) {
				
				try {
					fw.close();
				}
				
				catch (IOException exp) {
					System.out.println("The file write but not closed.");
				}
			}
		}
	}
	
	private void deletePassword(String fileName, int selectedIndex) {
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			ArrayList<String> lines = new ArrayList<>();
			String line;
			
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			
			br.close();
			
			lines.remove(selectedIndex);
			fw = new FileWriter(fileName);
			for (String updatedLine : lines) {
				fw.write(updatedLine + "\n");
			}
		}

		
		catch (IOException exp){
			System.out.println("There was a problem writing the file.");
	        JOptionPane.showMessageDialog(null, "There was a problem writing the file.");
        }
		
		finally {
			
			if (br != null) {
				
				try {
					br.close();
				}
				
				catch (IOException exp) {
					System.out.println("The file read but not closed.");
				}
			}
			
			if (fw!= null) {
				
				try {
					fw.close();
				}
				
				catch (IOException exp) {
					System.out.println("The file write but not closed.");
				}
			}
		}
	}
	
	private class BtnAddFieldListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			BufferedReader br = null;
			FileReader fr = null;
			FileWriter fw = null; 
			int tempID = 0;
			boolean isFound = false;
			boolean isInformed = false;
		
			try {
		        File fieldsFile = new File("fields.txt");
		        
		        if (!fieldsFile.exists()) {
		        	fieldsFile.createNewFile();
		        }
		        
		        else if (fieldsFile.exists() && fieldsFile.length() == 0) {
					if (tfField.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You can not continue the process without filling in the field.",
								"ERROR : Unfilled Field", JOptionPane.ERROR_MESSAGE);
						isInformed = true;
					}
		        }
		        
				fr = new FileReader(fieldsFile);
				br = new BufferedReader(fr);
				String line;
				String[] strArray;
				
				while ((line = br.readLine()) != null) {
				    strArray = line.split(",");
				    tempID = Integer.parseInt(strArray[0]);
				    
				    if (strArray.length == 2) {
				    	
				    	if (tfField.getText().trim().equals(strArray[1].trim())) {
				    		isFound = true;
					        JOptionPane.showMessageDialog(null, "You can not add an field that has already been added.");
					        break;
				    	}
				    }
				}
				
				if (isInformed == false && isFound == false) {
					
					if (!tfField.getText().trim().isEmpty()) {
						tempID++;
						String temp = tempID + "," + tfField.getText().trim() + "\r\n";
						fw = new FileWriter(fieldsFile, true);
						fw.write(temp);
				        JOptionPane.showMessageDialog(null, "You added a new field.");
				        Timer timer = new Timer(100, new ActionListener() {
		                    @Override
		                    public void actionPerformed(ActionEvent e) {
		    			        refreshFieldsTable();
		                    }
		                });
		                timer.setRepeats(false);
		                timer.start();
					}
					
					else {
						JOptionPane.showMessageDialog(null, "You can not continue the process without filling in the field.",
								"ERROR : Unfilled Field", JOptionPane.ERROR_MESSAGE);
						isInformed = true;
					}
				}
			}
			
			catch (IOException ex) {
		        JOptionPane.showMessageDialog(null, "You could not add a new field.");
			}
			
			finally {
				
				if(fr != null) {
					
					try {
						fr.close();
					}
					
					catch (IOException exp){
						System.out.println("There was a problem closing the file.");
				        JOptionPane.showMessageDialog(null, "There was a problem closing the file.");
					}
				}
				
				if(fw != null) {
					
					try {
						fw.close();
					}
					
					catch (IOException exp){
						System.out.println("There was a problem closing the file.");
				        JOptionPane.showMessageDialog(null, "There was a problem closing the file.");
					}
				}
			}
		}
	}
	
	private class BtnDeleteFieldListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			int selectedRow = tblFields.getSelectedRow();
			
			if (selectedRow != -1 && selectedRow < fields.size()) {
				int option = JOptionPane.showConfirmDialog(null,
		                "Do you want to delete the selected field from the system?",
		                "Delete the Doctor", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					fieldModel.removeRow(selectedRow);
					fields.remove(selectedRow);
					deleteField("fields.txt", selectedRow);
					refreshFieldsTable();
				}
				
				else if (option == JOptionPane.NO_OPTION || option == JOptionPane.CLOSED_OPTION) {
					
				}
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Please select the field you want to delete!",
						"ERROR : Delete the Field", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class BtnAddNAPassListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			BufferedReader br = null;
			FileReader fr = null;
			FileWriter fw = null; 
			int tempID = 0;
			boolean isFound = false;
			boolean isInformed = false;
		
			try {
		        File passwordsFile = new File("notAllowedPasswords.txt");
		        
		        if (!passwordsFile.exists()) {
		        	passwordsFile.createNewFile();
		        }
		        
		        else if (passwordsFile.exists() && passwordsFile.length() == 0) {
					if (tfPassword.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You can not continue the process without filling in the field.",
								"ERROR : Unfilled Field", JOptionPane.ERROR_MESSAGE);
						isInformed = true;
					}
		        }
		        
				fr = new FileReader(passwordsFile);
				br = new BufferedReader(fr);
				String line;
				String[] strArray;
				
				while ((line = br.readLine()) != null) {
				    strArray = line.split(",");
				    tempID = Integer.parseInt(strArray[0]);
				    
				    if (strArray.length == 2) {
				    	
				    	if (tfPassword.getText().trim().equals(strArray[1].trim())) {
				    		isFound = true;
					        JOptionPane.showMessageDialog(null, "You can not add an password that has already been blocked.");
					        break;
						}
				    }
				}
				
				if (isInformed == false && isFound == false) {
					
					if (!tfPassword.getText().trim().isEmpty()) {
						tempID++;
						String temp = tempID + "," + tfPassword.getText().trim() + "\r\n";
						fw = new FileWriter(passwordsFile, true);
						fw.write(temp);
				        JOptionPane.showMessageDialog(null, "You added a blocked password.");
				        Timer timer = new Timer(100, new ActionListener() {
		                    @Override
		                    public void actionPerformed(ActionEvent e) {
		    			        refreshNotAllowedPasswordsTable();
		                    }
		                });
		                timer.setRepeats(false);
		                timer.start();
					}
					
					else {
						JOptionPane.showMessageDialog(null, "You can not continue the process without filling in the field.",
								"ERROR : Unfilled Field", JOptionPane.ERROR_MESSAGE);
						isInformed = true;
					}
				}
			}
			
			catch (IOException ex) {
		        JOptionPane.showMessageDialog(null, "You could not add a new field.");
			}
			
			finally {
				
				if(fr != null) {
					
					try {
						fr.close();
					}
					
					catch (IOException exp){
						System.out.println("There was a problem closing the file.");
				        JOptionPane.showMessageDialog(null, "There was a problem closing the file.");
					}
				}
				
				if(fw != null) {
					
					try {
						fw.close();
					}
					
					catch (IOException exp){
						System.out.println("There was a problem closing the file.");
				        JOptionPane.showMessageDialog(null, "There was a problem closing the file.");
					}
				}
			}
		}
	}
	
	private class BtnDeleteNAPassListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			int selectedRow = tblNotAllowedPasswords.getSelectedRow();
			
			if (selectedRow != -1 && selectedRow < notAllowedPasswords.size()) {
				int option = JOptionPane.showConfirmDialog(null,
		                "Do you want to delete the selected password from the system?",
		                "Delete the Doctor", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					passwordModel.removeRow(selectedRow);
					notAllowedPasswords.remove(selectedRow);
					deletePassword("notAllowedPasswords.txt", selectedRow);
					refreshNotAllowedPasswordsTable();
				}
				
				else if (option == JOptionPane.NO_OPTION || option == JOptionPane.CLOSED_OPTION) {
					
				}
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Please select the password you want to delete!",
						"ERROR : Delete the Password", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void refreshFieldsTable() {
		fields = listFields("fields.txt");
		fieldModel.setDataVector(fields.toArray(new Object[0][0]), new String[]{"ID", "Fields"});
	    tblFields.getColumnModel().getColumn(0).setPreferredWidth(10);
	    tblFields.getColumnModel().getColumn(1).setPreferredWidth(90);
	}

	private void refreshNotAllowedPasswordsTable() {
		notAllowedPasswords = listNotAllowedPasswords("notAllowedPasswords.txt");
		passwordModel.setDataVector(notAllowedPasswords.toArray(new Object[0][0]), new String[]{"ID", "Passwords"});
	    tblNotAllowedPasswords.getColumnModel().getColumn(0).setPreferredWidth(10);
	    tblNotAllowedPasswords.getColumnModel().getColumn(1).setPreferredWidth(90);
	}
	
	private class BtnRefreshListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			refreshFieldsTable();
			refreshNotAllowedPasswordsTable();
		}
	}

	private class BtnLogoutListener implements ActionListener {
		private LoginForm lf;
		
		public BtnLogoutListener(LoginForm l) {
			lf = l;
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			int option = JOptionPane.showConfirmDialog(lf,
	                "Do you want to logout?",
	                "Logout the System",
	                JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_NO_OPTION) {
				dispose();
				new LoginForm();
			}
			
			else if (option == JOptionPane.NO_OPTION || option == JOptionPane.CLOSED_OPTION) {
				
			}
		}
	}
}
