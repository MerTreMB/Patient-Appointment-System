import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EditEmployeeFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblHospitalName;
	private JLabel lblNameSurName;
	private JLabel lblText;
	private JLabel lblTime;
	private JLabel lblEmpty;
	private JLabel lblEmpty2;
	private JLabel lblName;
	private JLabel lblSurName;
	private JLabel lblTcKimlik;
	private JLabel lblPhoneNumber;
	private JTextField tfName;
	private JTextField tfSurName;
	private JTextField tfTcKimlik;
	private JTextField tfPhoneNumber;
	private JButton btnActiveEmployees;
	private JButton btnAddNewEmployee;
	private JButton btnSettings;
	private JButton btnContinueDoctor;
	private JButton btnSubmit;
	private JButton btnBack;
	private JButton btnLogout;
	private JComboBox<String> cmbFields;
	private JComboBox<String> cmbTitle;
	private JPanel pnlTop;
	private JPanel pnlLeftSide;
	private JPanel pnlCenter;
	private JPanel pnlCenterTop;
	private JPanel pnlCenterCenter;
	private JPanel pnlCenterBottom;
	private JPanel pnlBottom;
	private BorderLayout frameBorderLayout;
	private FlowLayout pnlTopFlowLayout;
	private GridLayout leftSideGridLayout;
	private BorderLayout pnlCenterBorderLayout;
	private FlowLayout pnlCenterTopFlowLayout;
	private GridLayout pnlCenterCenterGridLayout;
	private FlowLayout pnlCenterBottomFlowLayout;
	private FlowLayout pnlBottomFlowLayout;
	private String docTitle = null;
	private String name = null;
	private String surName = null;
	private String selDocTitle = null;
	private String selDocName = null;
	private String selDocSurName = null;
	private String selDocTcKimlik = null;
	private String selDocField = null;
	private String selDocPhoneNumber = null;
	@SuppressWarnings("unused")
	private String docID;
	private String tcKimlik;
	
	public EditEmployeeFrame(final LoginForm lf, final ActiveEmployeesFrame ae, final AddNewEmployeeFrame an, String docID, String tcKimlik) {
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
		lblText = new JLabel(" Edit the Employee", SwingConstants.LEFT);
		lblText.setFont(new Font("", Font.BOLD, 25));
		pnlCenterTop.add(lblText);
		
	    LocalDateTime date = LocalDateTime.now();
	    DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    String strDate = date.format(formattedDate);
	    
	    lblTime = new JLabel(strDate + "  ", SwingConstants.RIGHT);
	    lblTime.setPreferredSize(new Dimension (550, 50));
	    lblTime.setOpaque(true);
	    pnlCenterTop.add(lblTime);
	    pnlCenter.add(pnlCenterTop, BorderLayout.NORTH);


		lblEmpty = new JLabel("                                   ");
		pnlCenter.add(lblEmpty, BorderLayout.EAST);
		   
		lblEmpty2 = new JLabel("                                  ");
		pnlCenter.add(lblEmpty2, BorderLayout.WEST);
		
		fr = null;
		br = null;
		
		try {
			fr = new FileReader("doctors.txt");
			br = new BufferedReader(fr);
			String line;
			String[] strArray;
			int currentLine = 0;
			int docIDInt = Integer.parseInt(docID);
			
			while ((line = br.readLine()) != null) {
				
				if (currentLine == docIDInt) {
					strArray = line.split(",");
					
					if (strArray.length == 7) {
						selDocTitle = strArray[1];
						selDocName = strArray[2];
						selDocSurName = strArray[3];
						selDocTcKimlik = strArray[4];
						selDocField = strArray[5];
						selDocPhoneNumber = strArray[6];
					}
					break;
				}
				currentLine++;
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
		
	    pnlCenterCenter = new JPanel();
	    pnlCenterCenterGridLayout = new GridLayout(10, 2, 3, 3);
	    pnlCenterCenter.setLayout(pnlCenterCenterGridLayout);
	    
	    String[] fields = {"Select Field", "Cardiology", "Dermatology", "Neurology", "Orthopedics", "Otolaryngology"};
	    cmbFields = new JComboBox<>(fields);
	    cmbFields.setSelectedItem(selDocField);
	    pnlCenterCenter.add(cmbFields);
	    
	    String[] titles = {"Select Title", "Dr.", "Assoc. Prof.", "Asst. Prof.", "Prof. Dr.", "Head Dr."};
	    cmbTitle = new JComboBox<>(titles);
	    cmbTitle.setSelectedItem(selDocTitle);
	    pnlCenterCenter.add(cmbTitle);
	    
	    lblName = new JLabel(" Name: ");
	    pnlCenterCenter.add(lblName);
	    
		tfName = new JTextField();
		tfName.setText(selDocName);
	    pnlCenterCenter.add(tfName);
	    
	    lblSurName = new JLabel(" Surname: ");
	    pnlCenterCenter.add(lblSurName);
	    
	    tfSurName = new JTextField();
	    tfSurName.setText(selDocSurName);
	    pnlCenterCenter.add(tfSurName);
	    
	    lblTcKimlik = new JLabel(" TR-ID: ");
	    pnlCenterCenter.add(lblTcKimlik);

		tfTcKimlik = new JTextField();
		tfTcKimlik.setColumns(11);
		tfTcKimlik.setSize(200, 24);
		tfTcKimlik.setText(selDocTcKimlik);
		tfTcKimlik.setEditable(false);
		tfTcKimlik.setEnabled(false);
	    pnlCenterCenter.add(tfTcKimlik);
	    
	    lblPhoneNumber = new JLabel(" Phone Number: ");
	    pnlCenterCenter.add(lblPhoneNumber);

		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setColumns(11);
		tfPhoneNumber.setText(selDocPhoneNumber);
	    pnlCenterCenter.add(tfPhoneNumber);
	    pnlCenter.add(pnlCenterCenter, BorderLayout.CENTER);
	    
	    pnlCenterBottom = new JPanel();
	    pnlCenterBottomFlowLayout = new FlowLayout();
	    pnlCenterBottom.setLayout(pnlCenterBottomFlowLayout);
	    
	    btnSubmit = new JButton("Submit");
		BtnSubmitListener submitListener = new BtnSubmitListener();
		btnSubmit.addActionListener(submitListener);
		
		TCCheckListener tcListener = new TCCheckListener();
		tfTcKimlik.addKeyListener(tcListener);
		
		NameSurNameListener nameSurNameListener = new NameSurNameListener();
		tfName.addKeyListener(nameSurNameListener);
		tfSurName.addKeyListener(nameSurNameListener);
		
		PhoneNumberListener phoneNumberListener = new PhoneNumberListener();
		tfPhoneNumber.addKeyListener(phoneNumberListener);
		
	    pnlCenterBottom.add(btnSubmit);
	    
	    btnBack = new JButton(" Back ");
		BtnBackListener backListener = new BtnBackListener();
		btnBack.addActionListener(backListener);
	    pnlCenterBottom.add(btnBack);
	    pnlCenter.add(pnlCenterBottom, BorderLayout.SOUTH);
	    
	    cp.add(pnlCenter);
		
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
		setTitle("Patient Appointment System   |   Edit the Employee");
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
		}
	}
	
	private class BtnSettingsListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			dispose();
			new SettingsFrame(null, null, null, null, tcKimlik);
		}
	}
	
	private class BtnContinueDoctorListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			dispose();
			new DoctorFrame(null, null, null, tcKimlik);
		}
	}
	
	private class BtnSubmitListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Doctor> doctorsList = readDoctorsFromFile("doctors.txt");
			BufferedReader br = null;
			FileReader fr = null;
			boolean isFound = false;
			boolean isSame = false;
			boolean isInformed = false;
			String newName = String.valueOf(tfName.getText().trim());
			String newSurName = String.valueOf(tfSurName.getText().trim());
			String newPhoneNumber = String.valueOf(tfPhoneNumber.getText().trim());
			String newTitle = String.valueOf(cmbTitle.getSelectedItem());
			String newField = String.valueOf(cmbFields.getSelectedItem());
			
			try {
		        File doctorsFile = new File("doctors.txt");
		        
		        if (!doctorsFile.exists()) {
		        	doctorsFile.createNewFile();
		        }
		        
		        else if (doctorsFile.exists() && doctorsFile.length() == 0) {
					if (cmbFields.getSelectedItem().equals("Select Field") || cmbTitle.getSelectedItem().equals("Select Title")
							|| tfName.getText().trim().isEmpty() || tfSurName.getText().trim().isEmpty() 
							|| tfTcKimlik.getText().trim().isEmpty() || tfPhoneNumber.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You can not continue the process without filling in all fields.",
								"ERROR : Unfilled Fields", JOptionPane.ERROR_MESSAGE);
						isInformed = true;
					}
		        }
		        
				fr = new FileReader(doctorsFile);
				br = new BufferedReader(fr);
				String line;
				String[] strArray;
				
				while ((line = br.readLine()) != null) {
				    strArray = line.split(",");
				    
					if (cmbFields.getSelectedItem().equals("Select Field") || cmbTitle.getSelectedItem().equals("Select Title")
							|| tfName.getText().trim().isEmpty() || tfSurName.getText().trim().isEmpty() 
							|| tfTcKimlik.getText().trim().isEmpty() || tfPhoneNumber.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You can not continue the process without filling in all fields.",
								"ERROR : Unfilled Fields", JOptionPane.ERROR_MESSAGE);
						isInformed = true;
						break;
					}
					
					else {
						
					    if (strArray.length == 7 && tfTcKimlik.getText().trim().equals(strArray[4].trim())) {
					    	isFound = true;
					    	
					    	for (Doctor doctor : doctorsList) {
					    		
					    		if (doctor.getTcKimlik().trim().equals(tfTcKimlik.getText().trim())) {
					    		
						    		if (doctor.getField() != newField || doctor.getTitle() != newTitle || !doctor.getName().equals(newName)
						    				|| !doctor.getSurName().equals(newSurName) || !doctor.getPhoneNumber().equals(newPhoneNumber)) {
										doctor.setField(newField);
										doctor.setTitle(newTitle);
						    			doctor.setName(newName);
						    			doctor.setSurName(newSurName);
						    			doctor.setPhoneNumber(newPhoneNumber);
						    			break;
									}
						    		
						    		else {
						    			isSame = true;
						    			break;
						    		}
					    		}
					    	}
					    	
					    	if (isSame == false) {
								writeDoctorsToFile("doctors.txt", doctorsList);
						        JOptionPane.showMessageDialog(null, "The doctor's information updated!",
				        		"Information Updated", JOptionPane.INFORMATION_MESSAGE);
						        dispose();
						        new ActiveEmployeesFrame(null, null, null, null, tcKimlik);
					    	}
					    }
					}	
				}
				
				if (isInformed == false && isFound == false) {
					JOptionPane.showMessageDialog(null, "Please enter a valid Turkish Republic Identity Number.\n● TR-ID must consist of 11 digits."
			        		+ "\n● TR-ID can not start with '0' (Zero)." + "\n● TR-ID can not end with an odd number.",
			        		"ERROR : Invalid TR-ID", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			catch (IOException ex) {
				System.out.println("There was a problem reading the file.");
		        JOptionPane.showMessageDialog(null, "There was a problem closing the file.");
			}
			
			finally {
				
				if(fr != null) {
					try {
						fr.close();
					}
					
					catch (IOException exp){
						System.out.println("The file read but not closed.");
					}
				}
			}
		} 
	}
	
	public static ArrayList<Doctor> readDoctorsFromFile(String fileName) {
		ArrayList<Doctor> doctorsList = new ArrayList<>();
		FileReader fr = null; 
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line;
			
			while ((line = br.readLine()) != null) {
				 String[] strArray = line.split(",");
				 
				 if (strArray.length == 7) {
					 doctorsList.add(new Doctor(Integer.parseInt(strArray[0]), strArray[1], strArray[2],
							 strArray[3], strArray[4], strArray[5], strArray[6]));
				 }
			}
			
			br.close();
		}
		
		catch (IOException exp) {
			System.out.println("There was a problem reading the file.");
	        JOptionPane.showMessageDialog(null, "There was a problem closing the file.");
		}
		
		return doctorsList;
	}
	
	public static void writeDoctorsToFile(String fileName, ArrayList<Doctor> doctorsList) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			
			for(Doctor doctor : doctorsList) {
				bw.write(doctor.toString());
				bw.newLine();
			}
			
			bw.close();
		} 
		
		catch (Exception e) {
			System.out.println("There was a problem writing the file.");
	        JOptionPane.showMessageDialog(null, "There was a problem writing the file.");
		}
	}

	
	private class TCCheckListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent evt) {
			char key = evt.getKeyChar();
			if (!((Character.isDigit(key) || (key == KeyEvent.VK_BACK_SPACE) || (key == KeyEvent.VK_DELETE)) && (tfTcKimlik.getText().length() < 11))) {
				evt.consume();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
	
	private class NameSurNameListener implements KeyListener {
	    @Override
	    public void keyTyped(KeyEvent evt) {
	        char key = evt.getKeyChar();
	        if (!Character.isLetter(key) && !(key == KeyEvent.VK_BACK_SPACE) && !(key == KeyEvent.VK_DELETE) && !(key == KeyEvent.VK_SPACE)) {
	            evt.consume();
	        }
	    }

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
	
	private class PhoneNumberListener implements KeyListener {
	    @Override
	    public void keyTyped(KeyEvent evt) {
			char key = evt.getKeyChar();
			if (!((Character.isDigit(key) || (key == KeyEvent.VK_BACK_SPACE) || (key == KeyEvent.VK_DELETE)) && (tfPhoneNumber.getText().length() < 11))) {
				evt.consume();
			}
	    }

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
	
	private class BtnBackListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new ActiveEmployeesFrame(null, null, null, null, tcKimlik);
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
	                "Logout",
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
