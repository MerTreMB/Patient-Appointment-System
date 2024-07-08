import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddNewEmployeeFrame extends JFrame {
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
	private JButton btnClear;
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
	private String empName = null;
	private String empSurName = null;
	private String tcKimlik;
	
	public AddNewEmployeeFrame(final LoginForm lf, final ActiveEmployeesFrame ae, final AddNewEmployeeFrame an, final EditEmployeeFrame ee, String tcKimlik) {
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
		lblText = new JLabel(" Add New Employee", SwingConstants.LEFT);
		lblText.setFont(new Font("", Font.BOLD, 25));
		pnlCenterTop.add(lblText);
		
	    LocalDateTime date = LocalDateTime.now();
	    DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    String strDate = date.format(formattedDate);
	    
	    lblTime = new JLabel(strDate + "  ", SwingConstants.RIGHT);
	    lblTime.setPreferredSize(new Dimension (530, 50));
	    lblTime.setOpaque(true);
	    pnlCenterTop.add(lblTime);
	    pnlCenter.add(pnlCenterTop, BorderLayout.NORTH);


		lblEmpty = new JLabel("                                   ");
		pnlCenter.add(lblEmpty, BorderLayout.EAST);
		   
		lblEmpty2 = new JLabel("                                  ");
		pnlCenter.add(lblEmpty2, BorderLayout.WEST);
		
	    pnlCenterCenter = new JPanel();
	    pnlCenterCenterGridLayout = new GridLayout(10, 2, 3, 3);
	    pnlCenterCenter.setLayout(pnlCenterCenterGridLayout);
	    
	    ArrayList<String> listFields = new ArrayList<>();
	    br = null;
	    fr = null;
	    listFields.add("Select Field");
	    
	    try {
	        fr = new FileReader("fields.txt");
	        br = new BufferedReader(fr);
	        String line;
	        String[] strArray;
	        
	        while ((line = br.readLine()) != null) {
	            strArray = line.split(",");
	            
	            if (strArray.length == 2) {
	                listFields.add(strArray[1]);
	            }
	        }
	    } 
	    
	    catch (IOException ex) {
			System.out.println("There was a problem reading the file.");
	        JOptionPane.showMessageDialog(this, "There was a problem reading the file.");
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
	    
	    String[] fields = listFields.toArray(new String[0]);
	    cmbFields = new JComboBox<>(fields);
	    pnlCenterCenter.add(cmbFields);
	    
	    String[] titles = {"Select Title", "Dr.", "Assoc. Prof.", "Asst. Prof.", "Prof. Dr.", "Head Dr."};
	    cmbTitle = new JComboBox<>(titles);
	    pnlCenterCenter.add(cmbTitle);
	    
	    lblName = new JLabel(" Name: ");
	    pnlCenterCenter.add(lblName);
	    
		tfName = new JTextField();
	    pnlCenterCenter.add(tfName);
	    
	    lblSurName = new JLabel(" Surname: ");
	    pnlCenterCenter.add(lblSurName);
	    
	    tfSurName = new JTextField();
	    pnlCenterCenter.add(tfSurName);
	    
	    lblTcKimlik = new JLabel(" TR-ID: ");
	    pnlCenterCenter.add(lblTcKimlik);

		tfTcKimlik = new JTextField();
		tfTcKimlik.setColumns(11);
		tfTcKimlik.setSize(200, 24);
	    pnlCenterCenter.add(tfTcKimlik);
	    
	    lblPhoneNumber = new JLabel(" Phone Number: ");
	    pnlCenterCenter.add(lblPhoneNumber);

		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setColumns(11);
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
	    
	    btnClear = new JButton(" Clear ");
		BtnClearListener clearListener = new BtnClearListener();
		btnClear.addActionListener(clearListener);
	    pnlCenterBottom.add(btnClear);
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
		setTitle("Patient Appointment System   |   Add New Employee");
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
			BufferedReader br = null;
			FileReader fr = null;
			int tempID = 0;
			boolean isFound = false;
			boolean isInformed = false;
			boolean isMatch = false;
			
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
				    tempID = Integer.parseInt(strArray[0]);
				    
				    if (strArray.length == 7) {
				    	
				    	if (tfTcKimlik.getText().trim().equals(strArray[4].trim())) {
				    		isFound = true;
					        JOptionPane.showMessageDialog(null, "You can not add an employee who has already been added.");
					        break;
				    	}
				    }
				}

				
				if (cmbFields.getSelectedItem().equals("Select Field") || cmbTitle.getSelectedItem().equals("Select Title")
					|| tfName.getText().trim().equals("") || tfSurName.getText().trim().equals("") 
					|| tfTcKimlik.getText().trim().equals("") || tfPhoneNumber.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "You can not continue the process without filling in all field.",
							"ERROR : Unfilled Fields", JOptionPane.ERROR_MESSAGE);
					isInformed = true;
				}
			}
			
			catch (IOException ex) {
		        JOptionPane.showMessageDialog(null, "You could not add a new employee.");
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
			}
			
			fr = null;
			br = null;
			
			try {
				fr = new FileReader("users.txt");
				br = new BufferedReader(fr);
				String line;
				String[] strArray;
				
				while ((line = br.readLine()) != null) {
					strArray = line.split(",");
					
					if (strArray.length == 8 && tfTcKimlik.getText().trim().equals(strArray[3].trim())) {
						isMatch = true;
						strArray[1].trim().equals(empName);
						empName = strArray[1].trim();
						strArray[2].trim().equals(empSurName);
						empSurName = strArray[2];
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
			
			if (isFound == false && isMatch == true && isInformed == false) {
				
				if ((tfTcKimlik.getText().length() == 11) && (tfTcKimlik.getText().charAt(0) != '0') 
						&& (Character.getNumericValue(tfTcKimlik.getText().charAt(10)) % 2 == 0)) {
					FileWriter fw = null;
				
					try {
						tempID++;
								
						if (!tfName.getText().trim().equals(empName) || !tfSurName.getText().trim().equals(empSurName)) {
							JOptionPane.showMessageDialog(null, "You did not enter a valid name/surname",
									"ERROR : Insufficient Information", JOptionPane.ERROR_MESSAGE);
							System.out.println(tfName.getText().trim());
							System.out.println(empName);
							System.out.println(tfSurName.getText().trim());
							System.out.println(empSurName);
						}
						
						else {
							if ((tfPhoneNumber.getText().length() == 11) && (tfPhoneNumber.getText().charAt(0) == '0')) {
								String temp = tempID + "," + cmbTitle.getSelectedItem() + "," + tfName.getText().trim()
										+ "," + tfSurName.getText().trim() + "," + tfTcKimlik.getText().trim() 
										+ "," + cmbFields.getSelectedItem() + "," + tfPhoneNumber.getText().trim() + "\r\n";
								fw = new FileWriter("doctors.txt", true);
								fw.write(temp);
						        JOptionPane.showMessageDialog(null, "You added a new employee.");
						        Timer timer = new Timer(200, new ActionListener() {
				                    @Override
				                    public void actionPerformed(ActionEvent e) {
				                    	dispose();
								        new ActiveEmployeesFrame(null, null, null, null, tcKimlik);
				                    }
				                });
				                timer.setRepeats(false);
				                timer.start();
							}
							
							else {
								JOptionPane.showMessageDialog(null, "You did not enter a valid phone number. \nPhone numbers must start with '0'",
										"ERROR : Insufficient Information", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
						
					catch (Exception ex) {
						System.out.println("There was a problem reading the file.");
				        JOptionPane.showMessageDialog(null, "There was a problem reading the file.");
					}
						
					finally {
						
						if (fw != null) {
							
							try {
								fw.close();
							}
							
							catch (IOException exp) {
								System.out.println("There was a problem closing the file.");
						        JOptionPane.showMessageDialog(null, "There was a problem closing the file.");
							}
						}
					}
				}
				
				else {
			        JOptionPane.showMessageDialog(null, "Please enter a valid Turkish Republic Identity Number.\n● TR-ID must consist of 11 digits."
			        		+ "\n● TR-ID can not start with '0' (Zero)." + "\n● TR-ID can not end with an odd number." 
			        		+ "\n● You can add person who have already registered.", "ERROR : Invalid TR-ID", JOptionPane.ERROR_MESSAGE);
				}
			}
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
	
	private class BtnClearListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			cmbFields.setSelectedIndex(0);
			cmbTitle.setSelectedIndex(0);
			tfName.setText("");
			tfSurName.setText("");
			tfTcKimlik.setText("");
			tfPhoneNumber.setText("");
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
