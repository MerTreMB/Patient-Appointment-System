import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ActiveEmployeesFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblHospitalName;
	private JLabel lblNameSurName;
	private JLabel lblText;
	private JLabel lblTime;
	private JLabel lblEmpty;
	private JLabel lblEmpty2;
	private JButton btnActiveEmployees;
	private JButton btnAddNewEmployee;
	private JButton btnSettings;
	private JButton btnContinueDoctor;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnRefresh;
	private JButton btnLogout;
	private JPanel pnlTop;
	private JPanel pnlLeftSide;
	private JPanel pnlCenter;
	private JPanel pnlCenterTop;
	private JPanel pnlCenterCenter;
	private JPanel pnlCenterBottom;
	private JPanel pnlBottom;
	private JTable tblDoctors;
	private BorderLayout frameBorderLayout;
	private FlowLayout pnlTopFlowLayout;
	private GridLayout leftSideGridLayout;
	private BorderLayout pnlCenterBorderLayout;
	private FlowLayout pnlCenterTopFlowLayout;
	private BoxLayout pnlCenterCenterBoxLayout;
	private FlowLayout pnlCenterBottomFlowLayout;
	private FlowLayout pnlBottomFlowLayout;
	private DefaultTableModel model;
	private ArrayList<String[]> doctors;
	private String docTitle = null;
	private String name = null;
	private String surName = null;
	private String docID;
	private String tcKimlik;
	
	public ActiveEmployeesFrame(final LoginForm lf, final ActiveEmployeesFrame ae, final AddNewEmployeeFrame an, String docID, String tcKimlik) {
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
		lblText = new JLabel(" Active Employees", SwingConstants.LEFT);
		lblText.setFont(new Font("", Font.BOLD, 25));
		pnlCenterTop.add(lblText);
		
	    LocalDateTime date = LocalDateTime.now();
	    DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    String strDate = date.format(formattedDate);
	    
	    lblTime = new JLabel(strDate + "  ", SwingConstants.RIGHT);
	    lblTime.setPreferredSize(new Dimension (555, 50));
	    lblTime.setOpaque(true);
	    pnlCenterTop.add(lblTime);
	    pnlCenter.add(pnlCenterTop, BorderLayout.NORTH);

		lblEmpty = new JLabel("                                   ");
		pnlCenter.add(lblEmpty, BorderLayout.EAST);
		   
		lblEmpty2 = new JLabel("                                  ");
		pnlCenter.add(lblEmpty2, BorderLayout.WEST);
		
	    pnlCenterCenter = new JPanel();
	    pnlCenterCenterBoxLayout = new BoxLayout(pnlCenterCenter, BoxLayout.X_AXIS);
	    pnlCenterCenter.setLayout(pnlCenterCenterBoxLayout);
	    
	    String[] columnNames = {"ID", "Title", "Name", "Surname", "Field", "Phone Number"};
	    doctors = listDoctors("doctors.txt");
	    model = new DefaultTableModel(doctors.toArray(new Object[0][0]), columnNames);
	    tblDoctors = new JTable(model);
	    tblDoctors.getColumnModel().getColumn(0).setPreferredWidth(35);
	    tblDoctors.getColumnModel().getColumn(1).setPreferredWidth(100);
	    tblDoctors.getColumnModel().getColumn(2).setPreferredWidth(85);
	    tblDoctors.getColumnModel().getColumn(3).setPreferredWidth(85);
	    tblDoctors.getColumnModel().getColumn(4).setPreferredWidth(115);
	    tblDoctors.getColumnModel().getColumn(5).setPreferredWidth(100);
	    tblDoctors.setDefaultEditor(Object.class, null);
	    tblDoctors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    JScrollPane scrollPane = new JScrollPane(tblDoctors);
	    pnlCenterCenter.add(scrollPane);
	    pnlCenter.add(pnlCenterCenter);
	        
	    pnlCenterBottom = new JPanel();
	    pnlCenterBottomFlowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
	    pnlCenterBottom.setLayout(pnlCenterBottomFlowLayout);

	    btnDelete = new JButton("Delete the Employee");
	    BtnDeleteListener btnDeleteListener = new BtnDeleteListener();
	    btnDelete.addActionListener(btnDeleteListener);
	    pnlCenterBottom.add(btnDelete);
	    
	    btnEdit = new JButton("Edit the Employee");
	    BtnEditListener btnEditListener = new BtnEditListener();
	    btnEdit.addActionListener(btnEditListener);
	    pnlCenterBottom.add(btnEdit);
	    
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
	
	private ArrayList<String[]> listDoctors(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
		doctors = new ArrayList<>();
		String line;
		String[] row;
		String[] reorderedRow;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			while ((line = br.readLine()) != null) {
				row = line.split(",");
				reorderedRow = new String[]{row[0], row[1], row[2], row[3], row[5], row[6]};
				doctors.add(reorderedRow);
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
		return doctors;
	}
	
	private void deleteDoctor(String fileName, int selectedIndex) {
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
	
	private class BtnDeleteListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			int selectedRow = tblDoctors.getSelectedRow();
			
			if (selectedRow != -1) {
				int option = JOptionPane.showConfirmDialog(null,
		                "Do you want to delete the doctor from the system?",
		                "Delete the Doctor", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					model.removeRow(selectedRow);
					doctors.remove(selectedRow);
					deleteDoctor("doctors.txt", selectedRow);
					dispose();
					new ActiveEmployeesFrame(null, null, null, null, tcKimlik);
				}
				
				else if (option == JOptionPane.NO_OPTION || option == JOptionPane.CLOSED_OPTION) {
					
				}
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Please select the doctor you want to delete!",
						"ERROR : Delete the Doctor", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class BtnEditListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			int selectedRow = tblDoctors.getSelectedRow();
			docID = String.valueOf(selectedRow);
			
			if (selectedRow != -1) {
					dispose();
					new EditEmployeeFrame(null, null, null, docID, tcKimlik);
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Please select the doctor you want to edit!",
						"ERROR : Edit the Doctor", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class BtnRefreshListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			refreshDoctorsTable();
		}
	}
	
	private void refreshDoctorsTable() {
		doctors = listDoctors("doctors.txt");
		model.setDataVector(doctors.toArray(new Object[0][0]), new String[]{"ID", "Title", "Name", "Surname", "Field", "Phone Number"});
	    tblDoctors.getColumnModel().getColumn(0).setPreferredWidth(35);
	    tblDoctors.getColumnModel().getColumn(1).setPreferredWidth(100);
	    tblDoctors.getColumnModel().getColumn(2).setPreferredWidth(85);
	    tblDoctors.getColumnModel().getColumn(3).setPreferredWidth(85);
	    tblDoctors.getColumnModel().getColumn(4).setPreferredWidth(115);
	    tblDoctors.getColumnModel().getColumn(5).setPreferredWidth(100);
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
