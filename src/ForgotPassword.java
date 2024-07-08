import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ForgotPassword extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblWelcomeText;
	private JLabel lblTcKimlik;
	private JLabel lblSecurityQuestion;
	private JLabel lblNewPassword;
	private JLabel lblNewPassword2;
	private JLabel lblEmpty;
	private JLabel lblEmpty2;
	private JTextField tfTcKimlik;
	private JTextField tfSecurityQuestionAnswer;
	private JPasswordField pfNewPassword;
	private JPasswordField pfNewPassword2;
	private JCheckBox cbShowPassword;
	private JPanel pnlTop;
	private JPanel pnlBottom;
	private JPanel pnlShowPassword;
	private JPanel pnlBottomButtons;
	private JButton btnSubmit;
	private JButton btnReturnLogin;
	private BorderLayout frameBorderLayout;
	private GridLayout pnlTopGridLayout;
	private GridLayout pnlBottomGridLayout;
	private FlowLayout cbShowPasswordFlowLayout;
	private FlowLayout pnlBottomButtonsFlowLayout;
	
	public ForgotPassword(final LoginForm lf) {
		
		Container cp = getContentPane();
		frameBorderLayout = new BorderLayout();
		setLayout(frameBorderLayout);
		
		lblWelcomeText = new JLabel("Patient Appointment System", SwingConstants.CENTER);
		Font text = new Font("Courier", Font.BOLD, 25);
		lblWelcomeText.setPreferredSize(new Dimension (3, 100));
		lblWelcomeText.setFont(text);
		cp.add(lblWelcomeText, BorderLayout.NORTH);

		lblEmpty = new JLabel("        ");
		cp.add(lblEmpty, BorderLayout.EAST);

		lblEmpty2 = new JLabel("        ");
		cp.add(lblEmpty2, BorderLayout.WEST);
		
		pnlTop = new JPanel();
		pnlTopGridLayout = new GridLayout(4, 2, 3, 3);
		pnlTop.setLayout(pnlTopGridLayout);
		
		lblTcKimlik = new JLabel("TR-ID: ", SwingConstants.CENTER);
		Font text1 = new Font("Courier", Font.BOLD, 13);
		lblTcKimlik.setFont(text1);
		pnlTop.add(lblTcKimlik);
		tfTcKimlik = new JTextField();
		pnlTop.add(tfTcKimlik);
		
		lblNewPassword = new JLabel("NEW PASSWORD: ", SwingConstants.CENTER);
		lblNewPassword.setFont(text1);
		pnlTop.add(lblNewPassword);
		pfNewPassword = new JPasswordField();
		pfNewPassword.setEchoChar('●');
		pnlTop.add(pfNewPassword);
		
		lblNewPassword2 = new JLabel("CONFIRM NEW PASSWORD: ", SwingConstants.CENTER);
		lblNewPassword2.setFont(text1);
		pnlTop.add(lblNewPassword2);
		pfNewPassword2 = new JPasswordField();
		pfNewPassword2.setEchoChar('●');
		pnlTop.add(pfNewPassword2);

		lblSecurityQuestion = new JLabel("What's your mother's maiden name?", SwingConstants.CENTER);
		lblSecurityQuestion.setFont(text1);
		pnlTop.add(lblSecurityQuestion);
		tfSecurityQuestionAnswer = new JTextField();
		pnlTop.add(tfSecurityQuestionAnswer);
		
		cp.add(pnlTop, BorderLayout.CENTER);
		
		pnlBottom = new JPanel();
		pnlBottomGridLayout = new GridLayout(3, 1, 3, 3);
		pnlBottom.setLayout(pnlBottomGridLayout);
		
		pnlShowPassword = new JPanel();
		cbShowPasswordFlowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
		pnlShowPassword.setLayout(cbShowPasswordFlowLayout);
		
		cbShowPassword = new JCheckBox("Show Password");
		CbShowPasswordListener cbShowPasswordListener = new CbShowPasswordListener();
        cbShowPassword.addItemListener(cbShowPasswordListener);
        
        pnlShowPassword.add(cbShowPassword);
        pnlBottom.add(pnlShowPassword);
        
		pnlBottomButtons = new JPanel();
		pnlBottomButtonsFlowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
		pnlBottomButtons.setLayout(pnlBottomButtonsFlowLayout);
		
		btnSubmit = new JButton("Submit");
		BtnSubmitListener fPassListener = new BtnSubmitListener(lf);
		btnSubmit.addActionListener(fPassListener);
		
		TCCheckListener tcListener = new TCCheckListener();
		tfTcKimlik.addKeyListener(tcListener);

		PassCheckListener passListener = new PassCheckListener();
		pfNewPassword.addKeyListener(passListener);
		pfNewPassword2.addKeyListener(passListener);
		pnlBottomButtons.add(btnSubmit);
		
		btnReturnLogin = new JButton("Return Login");
		BtnReturnLoginListener rtrnLoginListener= new BtnReturnLoginListener(lf);
		btnReturnLogin.addActionListener(rtrnLoginListener);
		pnlBottomButtons.add(btnReturnLogin);

		pnlBottom.add(pnlBottomButtons);
		cp.add(pnlBottom, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Forgot Password");
        setResizable(false);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class BtnSubmitListener implements ActionListener {
		private LoginForm lf;
		
		public BtnSubmitListener(LoginForm l) {
			lf = l;
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			ArrayList<User> usersList = readUsersFromFile("users.txt");
			FileReader fr = null;
			BufferedReader br = null;
			boolean isFound = false;
			boolean isSame = false;
			boolean isInformed = false;
			String newPassword = new String(pfNewPassword.getPassword());
			String newPassword2 = new String(pfNewPassword2.getPassword());
			
			ArrayList<String> listPasswords = new ArrayList<>();
		    br = null;
		    fr = null;
        	listPasswords.add(tfTcKimlik.getText().trim());
		    
		    try {
		        fr = new FileReader("notAllowedPasswords.txt");
		        br = new BufferedReader(fr);
		        String line;
		        String[] strArray;
		        
		        while ((line = br.readLine()) != null) {
		            strArray = line.split(",");
		            
		            if (strArray.length == 2) {
		            	listPasswords.add(strArray[1]);
		            }
		        }
		    } 
		    
		    catch (IOException ex) {
				System.out.println("There was a problem reading the file.");
		        JOptionPane.showMessageDialog(lf, "There was a problem reading the file.");
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
		    
		    String[] notAcceptedPasswords = listPasswords.toArray(new String[0]);
			
			try {
				File usersFile = new File("users.txt");
				
		        if (!usersFile.exists()) {
		        	usersFile.createNewFile();
		        }
		        
		        else if (usersFile.exists() && usersFile.length() == 0) {
					if (tfTcKimlik.getText().trim().isEmpty() || tfSecurityQuestionAnswer.getText().trim().isEmpty()
							|| newPassword.trim().isEmpty() || newPassword2.trim().isEmpty()) {
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

					if (tfTcKimlik.getText().trim().isEmpty() || tfSecurityQuestionAnswer.getText().trim().isEmpty()
							|| newPassword.trim().isEmpty() || newPassword2.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You can not continue the process without filling in all fields.",
								"ERROR : Unfilled Fields", JOptionPane.ERROR_MESSAGE);
						isInformed = true;
						break;
					}
					
					else {
						
						if (strArray.length == 8 && tfTcKimlik.getText().trim().equals(strArray[3]) && tfTcKimlik.getText().length() == 11) {
							isFound= true;
							
							if (tfSecurityQuestionAnswer.getText().trim().equals(strArray[5])) {

								boolean isPasswordAccepted = true;
								
								if (newPassword.trim().equals(newPassword2.trim())) {
									
									if (newPassword.length() > 4) {
										
										for (User user : usersList) {
											
											if (user.getTcKimlik().equals(tfTcKimlik.getText().trim()) 
												&& user.getSecurityQuestionAnswer().equals(tfSecurityQuestionAnswer.getText().trim())) {
												
												if (user.getPassword().trim().equals(newPassword)) {
											        JOptionPane.showMessageDialog(lf, "The new password can not be the same as the old password.\n"
											        		+ "Please enter a new password that you have not use before.","ERROR : Same Password", JOptionPane.ERROR_MESSAGE);
											        isSame = true;
													break;
												}
												
												else {
													user.setPassword(newPassword);
													break;	
												}
											}
										}
										
										if (isSame == false) {
											
											for (String i : notAcceptedPasswords) {
												
												if (i.equals(newPassword)) {
													isPasswordAccepted = false;
											        JOptionPane.showMessageDialog(lf, "You can not enter a simple password like \"123456\" or one that contains personal information!",
											        		"ERROR : Basic Password", JOptionPane.ERROR_MESSAGE);
											        break;
												}
											}
												
											if (isPasswordAccepted) {
												writeUsersToFile("users.txt", usersList);
										        JOptionPane.showMessageDialog(lf, "Your password is updated!",
								        		"Password Change", JOptionPane.INFORMATION_MESSAGE);
										        dispose();
										        lf.setVisible(true);
											}
										}
									}
									
									else {
								        JOptionPane.showMessageDialog(lf, "You can not enter password shorter than 5 characters.",
								        		"ERROR : Insufficient Password", JOptionPane.ERROR_MESSAGE);
								        isInformed = true;
								        break;
									}
								}
								
								else {
							        JOptionPane.showMessageDialog(lf, "The entered passwords do not match each other.",
					        		"ERROR : Different Passwords", JOptionPane.ERROR_MESSAGE);
								}
							}
							
							else {
								JOptionPane.showMessageDialog(lf, "No account with this information was found.",
										"ERROR : Not Found", JOptionPane.ERROR_MESSAGE);
								break;
							}
						}
					}
				}
				
				if (isInformed == false && isFound == false) {
					JOptionPane.showMessageDialog(lf, "Please enter a valid Turkish Republic Identity Number.\n● TR-ID must consist of 11 digits."
			        		+ "\n● TR-ID can not start with '0' (Zero)." + "\n● TR-ID can not end with an odd number.",
			        		"ERROR : Invalid TR-ID", JOptionPane.ERROR_MESSAGE);
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
					
					catch (IOException e) {
						System.out.println("The file read but not closed.");
					}
				}
			}
		}
	}
	
	private static ArrayList<User> readUsersFromFile(String fileName) {
		ArrayList<User> users = new ArrayList<>();
		FileReader fr = null; 
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line;
			
			while ((line = br.readLine()) != null) {
				 String[] strArray = line.split(",");
				 
				 if (strArray.length == 8) {
					 users.add(new User(Integer.parseInt(strArray[0]), strArray[1], strArray[2],
							 strArray[3], strArray[4], strArray[5], strArray[6], strArray[7]));
				 }
			}
			
			br.close();
		}
		
		catch (IOException exp) {
			System.out.println("There was a problem reading the file.");
	        JOptionPane.showMessageDialog(null, "There was a problem closing the file.");
		}
		
		return users;
	}
	
	private static void writeUsersToFile(String fileName, ArrayList<User> users) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			
			for(User user : users) {
				bw.write(user.toString());
				bw.newLine();
			}
			
			bw.close();
		} 
		
		catch (Exception e) {
			System.out.println("There was a problem writing the file.");
	        JOptionPane.showMessageDialog(null, "There was a problem writing the file.");
		}
	}
	
	private class BtnReturnLoginListener implements ActionListener {
		private LoginForm lf;
		
		public BtnReturnLoginListener(LoginForm l) {
			lf = l;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			lf.setVisible(true);
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
			if (((key == KeyEvent.VK_COMMA) || (key == KeyEvent.VK_SPACE)) && (pfNewPassword.getPassword().length < 16)) {
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
                pfNewPassword.setEchoChar((char) 0);
                pfNewPassword2.setEchoChar((char) 0);
            } 
            
            else {
            	pfNewPassword.setEchoChar('●');
            	pfNewPassword2.setEchoChar('●');
            }
		}
	}
}
