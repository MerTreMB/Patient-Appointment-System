import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class LoginForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblWelcomeText;
	private JLabel lblTcKimlik;
	private JLabel lblPassword;
	private JLabel lblForgotPassword;
	private JLabel lblEmpty;
	private JLabel lblEmpty2;
	private JTextField tfTcKimlik;
	private JPasswordField pfPassword;
	private JCheckBox cbShowPassword;
	private JPanel pnlAll;
	private JPanel pnlTop;
	private JPanel pnlShowPassword;
	private JPanel pnlBottom;
	private JPanel pnlBottomMix;
	private JPanel pnlBottomBottom;
	private JButton btnLogin;
	private JButton btnRegister;
	private JButton btnForgotPassword;
	private BorderLayout frameBorderLayout;
	private GridLayout pnlAllGridLayout;
	private GridLayout pnlTopGridLayout;
	private GridLayout pnlBottomMixGridLayout;
	private FlowLayout pnlBottomBottomFlowLayout;
	private FlowLayout cbShowPasswordFlowLayout;
	private FlowLayout pnlBottomFlowLayout;
	
	public LoginForm() {
		Container cp = getContentPane();
		frameBorderLayout = new BorderLayout();
		cp.setLayout(frameBorderLayout);

		pnlAll = new JPanel();
		pnlAllGridLayout = new GridLayout(4, 1, 3, 3);
		pnlAll.setLayout(pnlAllGridLayout);
		
		lblWelcomeText = new JLabel("Patient Appointment System", SwingConstants.CENTER);
		Font text = new Font("Courier", Font.BOLD, 25);
		lblWelcomeText.setPreferredSize(new Dimension (3, 100));
		lblWelcomeText.setFont(text);
		pnlAll.add(lblWelcomeText);


		lblEmpty = new JLabel("        ");
		cp.add(lblEmpty, BorderLayout.EAST);
		lblEmpty2 = new JLabel("        ");
		cp.add(lblEmpty2, BorderLayout.WEST);
		
		pnlTop = new JPanel();
		pnlTopGridLayout = new GridLayout(2, 2, 3, 3);
		pnlTop.setLayout(pnlTopGridLayout);
		lblTcKimlik = new JLabel("TR-ID: ", SwingConstants.CENTER);
		Font text1 = new Font("Courier", Font.BOLD, 13);
		lblTcKimlik.setFont(text1);
		pnlTop.add(lblTcKimlik);
		tfTcKimlik = new JTextField();
		tfTcKimlik.setColumns(11);
		pnlTop.add(tfTcKimlik);
		lblPassword = new JLabel("PASSWORD: ", SwingConstants.CENTER);
		lblPassword.setFont(text1);
		pnlTop.add(lblPassword);
		pfPassword = new JPasswordField(16);
		pfPassword.setEchoChar('●');
		pnlTop.add(pfPassword);
		pnlAll.add(pnlTop);
		
		pnlBottomMix = new JPanel();
		pnlBottomMixGridLayout = new GridLayout(2, 1, 3, 3);
		pnlBottomMix.setLayout(pnlBottomMixGridLayout);
		
		pnlShowPassword = new JPanel();
		cbShowPasswordFlowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
		pnlShowPassword.setLayout(cbShowPasswordFlowLayout);
		
		cbShowPassword = new JCheckBox("Show Password");
		CbShowPasswordListener cbShowPasswordListener = new CbShowPasswordListener();
        cbShowPassword.addItemListener(cbShowPasswordListener);
        
        pnlShowPassword.add(cbShowPassword);
        pnlBottomMix.add(pnlShowPassword);
        
		pnlBottom = new JPanel();
		pnlBottomFlowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
		pnlBottom.setLayout(pnlBottomFlowLayout);
		
		btnLogin = new JButton("Login");
		BtnLoginListener loginListener = new BtnLoginListener();
		btnLogin.addActionListener(loginListener);
		
		TCCheckListener tcListener = new TCCheckListener();
		tfTcKimlik.addKeyListener(tcListener);
		
		PassCheckListener passListener = new PassCheckListener();
		pfPassword.addKeyListener(passListener);
		pnlBottom.add(btnLogin);
		
		btnRegister = new JButton("Register");
		BtnRegisterListener regListener = new BtnRegisterListener(this);
		btnRegister.addActionListener(regListener);
		pnlBottom.add(btnRegister);
		pnlBottomMix.add(pnlBottom);
		pnlAll.add(pnlBottomMix);
		
		pnlBottomBottom = new JPanel();
		pnlBottomBottomFlowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
		pnlBottomBottom.setLayout(pnlBottomBottomFlowLayout);
		
		lblForgotPassword = new JLabel("If you forgot your password, click following button: ");
		Font text2 = new Font(null ,Font.BOLD, 12);
		lblForgotPassword.setFont(text2);
		pnlBottomBottom.add(lblForgotPassword);
		
		btnForgotPassword = new JButton("Forgot Password");
		BtnForgotPasswordListener fPassListener = new BtnForgotPasswordListener(this);
		btnForgotPassword.addActionListener(fPassListener);
		pnlBottomBottom.add(btnForgotPassword);
		pnlAll.add(pnlBottomBottom);
		cp.add(pnlAll);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Log in to continue");
        setResizable(false);
		setSize(500,360);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class BtnLoginListener implements ActionListener {
		private LoginForm lf;
		 
		public void actionPerformed(ActionEvent evt) {
			BufferedReader br = null;
			FileReader fr = null;
			BufferedReader sbr = null;
			FileReader sfr = null;
			String password = new String (pfPassword.getPassword());
			boolean isFound = false;
			boolean isInformed = false;
			
			try {
				File usersFile = new File("users.txt");
		        
		        if (!usersFile.exists()) {
		        	usersFile.createNewFile();
		        }
		        
		        else if (usersFile.exists() && usersFile.length() == 0) {
					if (tfTcKimlik.getText().trim().isEmpty() || password.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You can not continue the process without filling in all fields.",
								"ERROR : Unfilled Fields", JOptionPane.ERROR_MESSAGE);
						isInformed = true;
					}
		        }
				fr = new FileReader(usersFile);
				br = new BufferedReader(fr);
				String line;
				String[] strArray;
				
				while ((line = br.readLine()) != null) {
					strArray = line.split(",");

					if (tfTcKimlik.getText().trim().isEmpty() || password.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You can not continue the process without filling in all fields.",
								"ERROR : Unfilled Fields", JOptionPane.ERROR_MESSAGE);
						isInformed = true;
						break;
					}
					
					else if (strArray.length == 8 && strArray[3].equals(tfTcKimlik.getText().trim())) {
						isFound = true;
						
						if (strArray[4].equals(password)) {
							System.out.println(strArray[1] + " " + strArray[2] + " logged in successfully.");
							
							try {
								sfr = new FileReader("doctors.txt");
								sbr = new BufferedReader(sfr);
								String sline;
								String[] sstrArray;
								boolean isAuthorized = false;
								
								while ((sline = sbr.readLine()) != null) {
									sstrArray = sline.split(",");
									
									if (sstrArray.length == 7 && sstrArray[4].equals(tfTcKimlik.getText().trim())) {
										isAuthorized = true;
										
										switch (sstrArray[1]) {
											case "Dr.":
											case "Assoc. Prof.":
											case "Asst. Prof.":
											case "Prof. Dr.":
												dispose();
												new DoctorFrame(lf, null, null, tfTcKimlik.getText().trim());
												break;
											case "Head Dr.":
												dispose();
												new HeadDoctorFrame(lf, null, null, null, tfTcKimlik.getText().trim());
												break;
											default:
												System.out.println("An incorrect type was found while reading the file: " + sstrArray[2]);
												dispose();
												new PatientFrame(lf, null, null, tfTcKimlik.getText().trim());
												break;
										}
									}
								}
								
								if (isAuthorized == false) {
									dispose();
									new PatientFrame(lf, null, null, tfTcKimlik.getText().trim());
									break;
								}
							}
							
							catch (IOException e) {
								System.out.println("There was a problem reading the file.");
						        JOptionPane.showMessageDialog(lf, "There was a problem closing the file.");
						        break;
							}
							
							finally {
								
								if (sfr != null) {
									
									try {
										sfr.close();
										break;
									}
									
									catch (IOException exp) {
										System.out.println("The file read but not closed.");
								        JOptionPane.showMessageDialog(lf, "The file read but not closed.");
										break;
									}
								}
							}
							
							break;
						}
						
						else {
					        JOptionPane.showMessageDialog(lf, "You entered a wrong password. \nIf you forgot your password, click Forgot Password.",
							"ERROR : Wrong Password", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
				}
				if (isInformed == false) {
					if (isFound == false) {
						
						if (tfTcKimlik.getText().length() != 11) {
					        JOptionPane.showMessageDialog(lf, "Please enter a valid Turkish Republic Identity Number.\n● TR-ID must consist of 11 digits."
					        		+ "\n● TR-ID can not start with '0' (Zero)." + "\n● TR-ID can not end with an odd number.",
					        		"ERROR : Invalid Password", JOptionPane.ERROR_MESSAGE);
						}
						
						else {
					        JOptionPane.showMessageDialog(lf, "There is no user registered with TR-ID " + tfTcKimlik.getText().trim() + ".",
									"ERROR : Unknown TR-ID", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
			
			catch (IOException e) {
				System.out.println("There was a problem reading the file.");
		        JOptionPane.showMessageDialog(lf, "There was a problem closing the file.");
			}
			
			finally {
				
				if (fr != null) {
					
					try {
						fr.close();
					}
					
					catch (IOException exp) {
						System.out.println("The file read but not closed.");
				        JOptionPane.showMessageDialog(lf, "The file read but not closed.");
					}
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
	
	private class PassCheckListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent evt) {
			char key = evt.getKeyChar();
			if (((key == KeyEvent.VK_COMMA) || (key == KeyEvent.VK_SPACE)) && (pfPassword.getPassword().length < 16)) {
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
	
	private class CbShowPasswordListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                pfPassword.setEchoChar((char) 0);
            } 
            
            else {
                pfPassword.setEchoChar('●');
            }
		}
	}
	
	private class BtnRegisterListener implements ActionListener {
		private LoginForm lf;
		
		public BtnRegisterListener(LoginForm l) {
			lf = l;
		}
		 
		public void actionPerformed(ActionEvent evt) {
			setVisible(false);
			new RegisterForm(lf);
		}
	}
	
	private class BtnForgotPasswordListener implements ActionListener {
		private LoginForm lf;
		
		public BtnForgotPasswordListener(LoginForm l) {
			lf = l;
		}
		
		public void actionPerformed(ActionEvent evt) {
			setVisible(false);
			new ForgotPassword(lf);
		}
	}
}
