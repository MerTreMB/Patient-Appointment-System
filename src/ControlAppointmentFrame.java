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

public class ControlAppointmentFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblHospitalName;
	private JLabel lblNameSurName;
	private JLabel lblText;
	private JLabel lblTime;
	private JLabel lblEmpty;
	private JLabel lblEmpty2;
	private JButton btnMakeAnAppointment;
	private JButton btnControlAppointment;
	private JButton btnCancel;
	private JButton btnRefresh;
	private JButton btnLogout;
	private JPanel pnlTop;
	private JPanel pnlLeftSide;
	private JPanel pnlCenter;
	private JPanel pnlCenterTop;
	private JPanel pnlCenterCenter;
	private JPanel pnlCenterBottom;
	private JPanel pnlBottom;
	private JComboBox<String> cmbIdIndex;
	private JTable tblAppointments;
	private BorderLayout frameBorderLayout;
	private FlowLayout pnlTopFlowLayout;
	private GridLayout leftSideGridLayout;
	private BorderLayout pnlCenterBorderLayout;
	private FlowLayout pnlCenterTopFlowLayout;
	private BoxLayout pnlCenterCenterBoxLayout;
	private FlowLayout pnlCenterBottomFlowLayout;
	private FlowLayout pnlBottomFlowLayout;
	private DefaultTableModel model;
	private ArrayList<String[]> listAppointments;
	private String name = null;
	private String surName = null;
	private String tcKimlik;
	
	public ControlAppointmentFrame(final LoginForm lf, final MakeAnAppointmentFrame ma, final ControlAppointmentFrame ca, String tcKimlik) {
		this.tcKimlik = tcKimlik;
		listAppointments = new ArrayList<String[]>();
		
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
			fr = new FileReader("users.txt");
			br = new BufferedReader(fr);
			String line;
			String[] strArray;
			
			while ((line = br.readLine()) != null) {
				strArray = line.split(",");
				
				if (strArray.length == 8 && tcKimlik.equals(strArray[3])) {
					name = strArray[1];
					surName = strArray[2];
					break;
				}
			}
		}
		
		catch (IOException ex) {
			System.out.println("There was a problem reading the file.");
	        JOptionPane.showMessageDialog(ma, "There was a problem reading the file.");
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

		lblNameSurName = new JLabel(name + " " + surName + " ", SwingConstants.RIGHT);
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
		
		btnMakeAnAppointment = new JButton("Make An Appointment");
		BtnMakeAnAppointmentListener makeAnAppointmentListener = new BtnMakeAnAppointmentListener();
		btnMakeAnAppointment.addActionListener(makeAnAppointmentListener);
		pnlLeftSide.add(btnMakeAnAppointment);
		
		btnControlAppointment = new JButton("Control Appointment");
		BtnControlAppointmentListener controlAppointmentListener = new BtnControlAppointmentListener();
		btnControlAppointment.addActionListener(controlAppointmentListener);
		pnlLeftSide.add(btnControlAppointment);
		
		pnlLeftSide.setBackground(Color.decode("#1640d9"));
		cp.add(pnlLeftSide, BorderLayout.WEST);
		
		pnlCenter = new JPanel();
		pnlCenterBorderLayout = new BorderLayout();
		pnlCenter.setLayout(pnlCenterBorderLayout);
		
		pnlCenterTop = new JPanel();
		pnlCenterTopFlowLayout = new FlowLayout(3, 3, 3);
		pnlCenterTop.setLayout(pnlCenterTopFlowLayout);
		lblText = new JLabel(" Control Appointment", SwingConstants.LEFT);
		lblText.setFont(new Font("", Font.BOLD, 25));
		pnlCenterTop.add(lblText);
		
	    LocalDateTime date = LocalDateTime.now();
	    DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    String strDate = date.format(formattedDate);
	    
	    lblTime = new JLabel(strDate + "  ", SwingConstants.RIGHT);
	    lblTime.setPreferredSize(new Dimension (500, 50));
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
	    
	    String[] columnNames = {"ID", "Doctor", "Field", "Date", "Time"};
	    listAppointments = listAppointments("appointments.txt");
	    model = new DefaultTableModel(listAppointments.toArray(new Object[0][0]), columnNames);
	    tblAppointments = new JTable(model);
	    tblAppointments.getColumnModel().getColumn(0).setPreferredWidth(25);
	    tblAppointments.getColumnModel().getColumn(1).setPreferredWidth(150);
	    tblAppointments.getColumnModel().getColumn(2).setPreferredWidth(125);
	    tblAppointments.getColumnModel().getColumn(3).setPreferredWidth(125);
	    tblAppointments.getColumnModel().getColumn(4).setPreferredWidth(50);
	    tblAppointments.setDefaultEditor(Object.class, null);
	    tblAppointments.setEnabled(false);
	    
	    JScrollPane scrollPane = new JScrollPane(tblAppointments);
	    pnlCenterCenter.add(scrollPane);
	    pnlCenter.add(pnlCenterCenter);
	      
	    pnlCenterBottom = new JPanel();
	    pnlCenterBottomFlowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
	    pnlCenterBottom.setLayout(pnlCenterBottomFlowLayout);
	    
		BufferedReader sbr = null;
		FileReader sfr = null;
		ArrayList<String> idList = new ArrayList<>(); 
		idList.add("Select ID");
		
		try {
			sfr = new FileReader("appointments.txt");
			sbr = new BufferedReader(sfr);
			String sline;
			String[] sstrArray;
			
			while ((sline = sbr.readLine()) != null) {
				sstrArray = sline.split(",");
				
				if (sstrArray.length == 8 && tcKimlik.equals(sstrArray[1])) {
					idList.add(sstrArray[0]);
				}
			}
		}
		
		catch (IOException ex) {
			System.out.println("There was a problem reading the file.");
	        JOptionPane.showMessageDialog(ca, "There was a problem reading the file.");
		}
		
		finally {
			
			if (sfr != null) {
				
				try {
					sfr.close();
				}
				
				catch (IOException exp) {
					System.out.println("The file read but not closed.");
				}
			}
		}
		
		String[] ids = idList.toArray(new String[0]);
		cmbIdIndex = new JComboBox<>(ids);
		cmbIdIndex.setModel(new DefaultComboBoxModel<>(ids));
		pnlCenterBottom.add(cmbIdIndex);

	    btnCancel = new JButton("Cancel the Appointment");
	    BtnCancelListener btnCancelListener = new BtnCancelListener();
	    btnCancel.addActionListener(btnCancelListener);
	    pnlCenterBottom.add(btnCancel);
	    
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
		setTitle("Patient Appointment System   |   Control Appointment");
        setResizable(false);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class BtnMakeAnAppointmentListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			dispose();
			new MakeAnAppointmentFrame(null, null, null, tcKimlik);
		}
	}
	
	private class BtnControlAppointmentListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			
		}
	}
	
	private ArrayList<String[]> listAppointments(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
		listAppointments = new ArrayList<>();
		String line;
		String[] row;
		String[] reorderedRow;
		
		try {
	        File appointmentsFile = new File(fileName);
	        
	        if (!appointmentsFile.exists()) {
	        	appointmentsFile.createNewFile();
	        }
	        
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			while ((line = br.readLine()) != null) {
				row = line.split(",");
				
				if (tcKimlik.equals(row[1])) {
					reorderedRow = new String[]{row[0], getDoctorFullName(row[3]), row[2], row[5] + " " + row[4] + " " + row[6], row[7]};
					listAppointments.add(reorderedRow);
				}
			}
		}
		
		catch (IOException exp){
			System.out.println("There was a problem reading the files.");
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
		return listAppointments;
	}
	
	private void cancelAppointment(String fileName, String selectedIndex) {
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
			
			for (int i = 0; i < lines.size(); i++) {
				String[] parts = lines.get(i).split(",");
				
				if (parts[0].equals(selectedIndex)) {
					lines.remove(i);
					break;
				}
			}
			
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
	
	private class BtnCancelListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			
			String selectedId = (String) cmbIdIndex.getSelectedItem();
			
			if (selectedId != null && !selectedId.equals("Select ID")) {
				int option = JOptionPane.showConfirmDialog(null,
		                "Do you want to cancel the appointment?",
		                "Cancel the Appointment",
		                JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_NO_OPTION) {
//					model.removeRow(selectedRow);
//					listAppointments.remove(selectedRow);
					cancelAppointment("appointments.txt", selectedId);
					dispose();
					new ControlAppointmentFrame(null, null, null, tcKimlik);
				}
				
				else if (option == JOptionPane.NO_OPTION || option == JOptionPane.CLOSED_OPTION) {
					
				}
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Please select the appointment you want to cancel from the \"Select ID\" section!",
						"ERROR : Cancel the Appointment", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class BtnRefreshListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			refreshAppointmentsTable();
		}
	}
	protected void refreshAppointmentsTable() {
		listAppointments = listAppointments("Appointments.txt");
		model.setDataVector(listAppointments.toArray(new Object[0][0]), new String[]{"ID", "Doctor", "Field", "Date", "Time"});
	    tblAppointments.getColumnModel().getColumn(0).setPreferredWidth(25);
	    tblAppointments.getColumnModel().getColumn(1).setPreferredWidth(150);
	    tblAppointments.getColumnModel().getColumn(2).setPreferredWidth(125);
	    tblAppointments.getColumnModel().getColumn(3).setPreferredWidth(125);
	    tblAppointments.getColumnModel().getColumn(4).setPreferredWidth(50);
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
	
	private String getDoctorFullName(String row) {
		
		FileReader fr = null;
		BufferedReader br = null;
		String docFullName = null;
		
		try {
			fr = new FileReader("doctors.txt");
			br = new BufferedReader(fr);
			String line;
			String[] strArray;
			
			while ((line = br.readLine()) != null) {
				strArray = line.split(",");
				
				if (strArray.length == 7 && row.equals(strArray[4])) {
					docFullName = strArray[1] + " " + strArray[2] + " " + strArray[3];
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
		return docFullName;
	}
}
