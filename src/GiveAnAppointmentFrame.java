import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;

public class GiveAnAppointmentFrame extends JFrame implements ItemListener {
	private static final long serialVersionUID = 1L;
	private JLabel lblHospitalName;
	private JLabel lblNameSurName;
	private JLabel lblText;
	private JLabel lblTime;
	private JLabel lblEmpty;
	private JLabel lblEmpty2;
	private JLabel lblPatient;
	private JButton btnGiveAnAppointment;
	private JButton btnAvailableAppointment;
	private JButton btnSubmit;
	private JButton btnClear;
	private JButton btnLogout;
	private JComboBox<String> cmbTcKimlik;
	private JComboBox<String> cmbDay;
	private JComboBox<String> cmbMonth;
	private JComboBox<String> cmbYear;
	private JComboBox<String> cmbTime;
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
	private String docFullName = null;
	private String docField = null;
	private String tcKimlik;
	
	public GiveAnAppointmentFrame(final LoginForm lf, final GiveAnAppointmentFrame ga, final AvailableAppointmentFrame aa, String tcKimlik) {
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
					docField = strArray[5];
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
		
		btnGiveAnAppointment = new JButton("Give An Appointment");
		BtnGiveAnAppointmentListener giveAnAppointmentListener = new BtnGiveAnAppointmentListener();
		btnGiveAnAppointment.addActionListener(giveAnAppointmentListener);
		pnlLeftSide.add(btnGiveAnAppointment);
		
		btnAvailableAppointment = new JButton("Available Appointment");
		BtnAvailableAppointmentListener availableAppointmentListener = new BtnAvailableAppointmentListener();
		btnAvailableAppointment.addActionListener(availableAppointmentListener);
		pnlLeftSide.add(btnAvailableAppointment);
		
		pnlLeftSide.setBackground(Color.decode("#1640d9"));
		cp.add(pnlLeftSide, BorderLayout.WEST);
		
		pnlCenter = new JPanel();
		pnlCenterBorderLayout = new BorderLayout();
		pnlCenter.setLayout(pnlCenterBorderLayout);
		
		pnlCenterTop = new JPanel();
		pnlCenterTopFlowLayout = new FlowLayout(3, 3, 3);
		pnlCenterTop.setLayout(pnlCenterTopFlowLayout);
		
		lblText = new JLabel(" Give An Appointment", SwingConstants.LEFT);
		lblText.setFont(new Font("", Font.BOLD, 25));
		pnlCenterTop.add(lblText);
		
	    LocalDateTime date = LocalDateTime.now();
	    DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    String strDate = date.format(formattedDate);
	    
	    lblTime = new JLabel(strDate + "  ", SwingConstants.RIGHT);
	    lblTime.setPreferredSize(new Dimension (480, 50));
	    lblTime.setOpaque(true);
	    pnlCenterTop.add(lblTime);
	    pnlCenter.add(pnlCenterTop, BorderLayout.NORTH);


		lblEmpty = new JLabel("                                   ");
		pnlCenter.add(lblEmpty, BorderLayout.EAST);
		   
		lblEmpty2 = new JLabel("                                  ");
		pnlCenter.add(lblEmpty2, BorderLayout.WEST);
	    
	    pnlCenterCenter = new JPanel();
	    pnlCenterCenterGridLayout = new GridLayout(3, 2, 3, 3);
	    pnlCenterCenter.setLayout(pnlCenterCenterGridLayout);
	    
		LinkedHashSet<String> patientList = new LinkedHashSet<>();
	    patientList.add("Select Patient's TR-ID");
	    fr = null;
	    br = null;
	    
	    try {
			fr = new FileReader("appointments.txt");
			br = new BufferedReader(fr);
			String line;
			String[] strArray;
			
			while ((line = br.readLine()) != null) {
				strArray = line.split(",");
				
				if (strArray.length == 8 && tcKimlik.equals(strArray[3])) {
					patientList.add(strArray[1]);
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

		String[] patientsID = patientList.toArray(new String[0]);
		cmbTcKimlik = new JComboBox<>(patientsID);
		cmbTcKimlik.setModel(new DefaultComboBoxModel<>(patientsID));
		pnlCenterCenter.add(cmbTcKimlik);
	    
		cmbTcKimlik.addItemListener(this);

	    lblPatient = new JLabel("- Select Patient -", SwingConstants.CENTER);
		Font lblPatientFont = new Font("", Font.PLAIN, 14);
		lblPatient.setFont(lblPatientFont);
	    fr = null;
	    br = null;
	    
		try {
			fr = new FileReader("users.txt");
			br = new BufferedReader(fr);
			String line;
			String[] strArray;
			
			while ((line = br.readLine()) != null) {
				strArray = line.split(",");
				
				if (strArray.length == 8 && cmbTcKimlik.getSelectedItem().equals(strArray[3])) {
				    lblPatient.setText("<html>" + "<B>Selected Patient:</B> " + strArray[1] + " " + strArray[2] + "</html>");
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
	    pnlCenterCenter.add(lblPatient);
	    
		LocalDate currentDate = LocalDate.now();
		LocalDate daysLater = currentDate.plusDays(30);
		ArrayList<String> selectableMonths = new ArrayList<>();
		
		if (currentDate.getMonth().equals(daysLater.getMonth())) {
		    selectableMonths.add("Select Month");
		    selectableMonths.add(currentDate.getMonth().toString());
		}
		
		else {
		    selectableMonths.add("Select Month");
		    selectableMonths.add(currentDate.getMonth().toString());
		    selectableMonths.add(daysLater.getMonth().toString());
		}
		
		cmbMonth = new JComboBox<>(selectableMonths.toArray(new String[0]));
		
		cmbMonth.removeAllItems();
		
		for (String month : selectableMonths) {
		    cmbMonth.addItem(month);
		}
		pnlCenterCenter.add(cmbMonth);
		
	    String[] days = new String[32];
	    days[0] = "Select Day";
	    for (int i = 0; i < 31; i++) {
	    	days[i + 1] = String.valueOf(i + 1);
	    }
	    cmbDay = new JComboBox<>(days);
	    pnlCenterCenter.add(cmbDay);
	    
	    String[] years = new String[2];
	    years[0] = "Select Year";
	    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	    for (int y = 0; y < 1; y++) {
	    	years[y + 1] = String.valueOf(currentYear + y);
	    }
	    
	    cmbYear = new JComboBox<>(years);
	    pnlCenterCenter.add(cmbYear);
	    
	    String[] times = {"Select Time", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30",
	    		"10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "14:00",
	    		"14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45"};
	    cmbTime = new JComboBox<>(times);
	    pnlCenterCenter.add(cmbTime);
	    pnlCenter.add(pnlCenterCenter, BorderLayout.CENTER);
	    
	    pnlCenterBottom = new JPanel();
	    pnlCenterBottomFlowLayout = new FlowLayout();
	    pnlCenterBottom.setLayout(pnlCenterBottomFlowLayout);
	    
	    btnSubmit = new JButton("Submit");
		BtnSubmitListener submitListener = new BtnSubmitListener();
		btnSubmit.addActionListener(submitListener);
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
		setTitle("Patient Appointment System   |   Give An Appointment");
        setResizable(false);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
    
	private class BtnGiveAnAppointmentListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			
		}
	}
	
	private class BtnAvailableAppointmentListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			dispose();
			new AvailableAppointmentFrame(null, null, null, tcKimlik);
		}
	}
	
	private class BtnSubmitListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			BufferedReader br = null;
			FileReader fr = null;
			int tempID = 0;
			boolean isFound = false;
			boolean isFull = false;
			String selectedDayString = (String) cmbDay.getSelectedItem();
			String selectedYearString = (String) cmbYear.getSelectedItem();
			
		    int selectedDay = 0;
	        String selectedMonth = null;
	        int selectedMonthInt = 0;
	        int selectedYear = 0;
	        LocalDate selectedDate = null;
	        LocalDate currentDate = LocalDate.now();
	        
			try {
		        File appointments = new File("appointments.txt");
		        docFullName = docTitle + " " + name + " " + surName;
		        
		        if (!appointments.exists()) {
		            appointments.createNewFile();
		        }
		        
				fr = new FileReader(appointments);
				br = new BufferedReader(fr);
				String line;
				String[] strArray;
				
				while ((line = br.readLine()) != null) {
				    strArray = line.split(",");
				    tempID = Integer.parseInt(strArray[0]);
				    if (strArray.length == 8) {
				    	if (cmbTcKimlik.getSelectedItem().equals(strArray[1].trim())) {
				    		
				    		if (docField.trim().equals(strArray[2]) && cmbMonth.getSelectedItem().equals(strArray[4])
				    			&& cmbDay.getSelectedItem().equals(strArray[5]) && cmbYear.getSelectedItem().equals(strArray[6])) {
				    			isFound = true;
								JOptionPane.showMessageDialog(null, "The patient already has an appointment for the day selected from the your field.",
										"ERROR : Rejected Request", JOptionPane.ERROR_MESSAGE);
						        break;
				    		}
					    	
					    	else if (cmbMonth.getSelectedItem().equals(strArray[4]) && cmbDay.getSelectedItem().equals(strArray[5]) 
					    			&& cmbYear.getSelectedItem().equals(strArray[6]) && cmbTime.getSelectedItem().equals(strArray[7])) {
					    		isFound = true;
								JOptionPane.showMessageDialog(null, "You can make a single appointment at the same time on the same day.",
										"ERROR : Rejected Request", JOptionPane.ERROR_MESSAGE);
							    break;
					    	}
				    	}
			    		
				    	else if (docField.trim().equals(strArray[2]) && docFullName.trim().equals(strArray[3]) 
				    			&& cmbMonth.getSelectedItem().equals(strArray[4]) && cmbDay.getSelectedItem().equals(strArray[5]) 
				    			&& cmbYear.getSelectedItem().equals(strArray[6]) && cmbTime.getSelectedItem().equals(strArray[7])) {
				    		isFull = true;
							JOptionPane.showMessageDialog(null, "You are examining another patient at this time.\nPlease choose another time!",
									"ERROR : Rejected Request", JOptionPane.ERROR_MESSAGE);
						    break;
				    	}
				    }
				    
				    else {
				    	isFound = false;
				    	isFull = false;
				    	break;
				    }
				}
			}
			
			catch (IOException ex) {
		        JOptionPane.showMessageDialog(null, "You could not give an appointment.");
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
			
			if (isFound == false && isFull == false) {
				FileWriter fw = null;
				
				if (!selectedDayString.equals("Select Day") && !selectedYearString.equals("Select Year")) {
					selectedDay = Integer.parseInt(selectedDayString);
					selectedMonth = (String) cmbMonth.getSelectedItem();
					selectedMonthInt = getMonthNumber(selectedMonth);
	   	     		selectedYear = Integer.parseInt(selectedYearString);
				}
				
				try {
					tempID++;
					
					if (cmbTcKimlik.getSelectedItem().equals("Select Patient's TR-ID"))  {
						JOptionPane.showMessageDialog(null, "You did not select the patient's TR-ID.",
								"ERROR : Insufficient Information", JOptionPane.ERROR_MESSAGE);
					}
					
					else {
							
						if (!isValidDate(selectedYear, selectedMonthInt, selectedDay) || cmbTime.getSelectedItem().equals("Select Time")) {
							JOptionPane.showMessageDialog(null, "You did not select a valid date/time.",
									"ERROR : Insufficient Information", JOptionPane.ERROR_MESSAGE);
						}
						
						else {
					        selectedDate = LocalDate.of(selectedYear, getMonthNumber(selectedMonth), selectedDay);
								
							if (selectedDate.isBefore(currentDate) || selectedDate.equals(currentDate)) {
								JOptionPane.showMessageDialog(null, "You can not make an appointment for a past date.",
										"ERROR : Insufficient Information", JOptionPane.ERROR_MESSAGE);
					        }
							
							else {
								String temp = tempID + "," + cmbTcKimlik.getSelectedItem() + "," + docField.trim() + "," 
											+ tcKimlik + "," + cmbMonth.getSelectedItem() + "," 
											+ cmbDay.getSelectedItem() + "," + cmbYear.getSelectedItem() + "," 
											+ cmbTime.getSelectedItem() + "\r\n";
								fw = new FileWriter("appointments.txt", true);
								fw.write(temp);
						        JOptionPane.showMessageDialog(null, "You give an appointment successfully.");
						        Timer timer = new Timer(200, new ActionListener() {
				                    @Override
				                    public void actionPerformed(ActionEvent e) {
				                    	dispose();
								        new AvailableAppointmentFrame(null, null, null, tcKimlik);
				                    }
				                });
				                timer.setRepeats(false);
				                timer.start();
							}
						}
					}
				}
					
				catch (Exception ex) {
			        JOptionPane.showMessageDialog(null, "You could not make an appointment.");
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
		}
	}
	
	private class BtnClearListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			cmbTcKimlik.setSelectedIndex(0);
			cmbMonth.setSelectedIndex(0);
			cmbDay.setSelectedIndex(0);
			cmbYear.setSelectedIndex(0);
			cmbTime.setSelectedIndex(0);
			lblPatient.setText("- Select Patient -");
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

	@Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
        	updateNameSurName();
        }
	}
	
	private void updateNameSurName() {
	    BufferedReader br = null;
	    FileReader fr = null;
	    
	    try {
	        fr = new FileReader("users.txt");
	        br = new BufferedReader(fr);
	        String line;
	        String[] strArray;
	        
	        while ((line = br.readLine()) != null) {
	            strArray = line.split(",");
	            
				if (strArray.length == 8 && cmbTcKimlik.getSelectedItem().equals(strArray[3])) {
				    lblPatient.setText("<html>" + "<B>Selected Patient:</B> " + strArray[1] + " " + strArray[2] + "</html>");
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
	}
	
	private int getMonthNumber(String month) {
        switch (month) {
	        case "JANUARY":
	            return 1;
	        case "FEBRUARY":
	            return 2;
	        case "MARCH":
	            return 3;
	        case "APRIL":
	            return 4;
	        case "MAY":
	            return 5;
	        case "JUNE":
	            return 6;
	        case "JULY":
	            return 7;
	        case "AUGUST":
	            return 8;
	        case "SEPTEMBER":
	            return 9;
	        case "OCTOBER":
	            return 10;
	        case "NOVEMBER":
	            return 11;
	        case "DECEMBER":
	            return 12;
	        case "Select Month":
	        default:
	            return 0;
        }
	}
	
	private boolean isValidDate(int year, int month, int day) {
		
	    try {
	        LocalDate.of(year, month, day);
	        return true;
	    }
	    
	    catch (DateTimeException e) {
	        return false;
	    }
	}
}
