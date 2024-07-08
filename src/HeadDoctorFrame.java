import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class HeadDoctorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblHospitalName;
	private JLabel lblNameSurName;
	private JLabel lblWelcomeText;
	private JLabel lblWelcomeText2;
	private JButton btnActiveEmployees;
	private JButton btnAddNewEmployee;
	private JButton btnSettings;
	private JButton btnContinueDoctor;
	private JButton btnLogout;
	private JPanel pnlTop;
	private JPanel pnlLeftSide;
	private JPanel pnlCenter;
	private JPanel pnlBottom;
	private BorderLayout frameBorderLayout;
	private FlowLayout pnlTopFlowLayout;
	private FlowLayout pnlBottomFlowLayout;
	private GridLayout pnlCenterGridLayout;
	private GridLayout leftSideGridLayout;
	private String docTitle = null;
	private String name = null;
	private String surName = null;
	private String tcKimlik;
	
	public HeadDoctorFrame(final LoginForm lf, final ActiveEmployeesFrame ae, final AddNewEmployeeFrame an, final EditEmployeeFrame ee, String tcKimlik) {
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
		pnlCenterGridLayout = new GridLayout(2, 3, 3, 3);
		pnlCenter.setLayout(pnlCenterGridLayout);
		lblWelcomeText = new JLabel("Welcome to Hospital Application.", SwingConstants.CENTER);
		lblWelcomeText.setFont(new Font("", Font.BOLD, 25));
		pnlCenter.add(lblWelcomeText);
		lblWelcomeText2 = new JLabel("You can select categories on left side buttons.", SwingConstants.CENTER);
		lblWelcomeText2.setForeground(Color.GRAY);
		pnlCenter.add(lblWelcomeText2);
		cp.add(pnlCenter, BorderLayout.CENTER);
		
		pnlBottom = new JPanel();
		pnlBottomFlowLayout = new FlowLayout(FlowLayout.RIGHT, 3, 3);
		pnlBottom.setLayout(pnlBottomFlowLayout);
		
		btnLogout = new JButton("Logout");
		BtnLogoutListener logOutListener = new BtnLogoutListener(lf);
		btnLogout.addActionListener(logOutListener);
		pnlBottom.add(btnLogout);
		
		pnlBottom.setBackground(Color.decode("#1438BE"));
		cp.add(pnlBottom, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Patient Appointment System For HeadDoctors");
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
